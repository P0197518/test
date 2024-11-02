package com.fujias.common.util.report;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 报表出力工具类
 * 
 * @author 陳強
 *
 */
public final class ReportUtil {

    private ReportUtil() {

    }

    /**
     * 导出单张模板的报表
     * 
     * @param reportItem 报表数据
     * @return 成功标志位
     * @throws Exception Exception
     */
    public static boolean printByDownload(ReportItemEntity<?> reportItem) throws Exception {
        List<ReportItemEntity<?>> list = new ArrayList<ReportItemEntity<?>>();
        list.add(reportItem);
        return printByDownload(list);
    }

    /**
     * 导出多张模板的报表
     * 
     * @param reportItems 报表数据
     * @return 成功标志位
     * @throws Exception Exception
     */
    public static boolean printByDownload(List<ReportItemEntity<?>> reportItems) throws Exception {

        if (reportItems.size() == 0) {
            System.out.println("请指定要打印的张票信息！");
            return false;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String path = request.getServletContext().getRealPath("/reports");

        // 得到帐票数据列表
        // pdf打印列表
        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

        for (ReportItemEntity<?> item : reportItems) {
            File file = new File(path + "/" + item.getReportName() + ".jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(file);

            JRDataSource dataSource = new JRBeanCollectionDataSource(item.getReportDataSource());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, item.getReportParams(), dataSource);

            jasperPrintList.add(jasperPrint);
        }

        ByteArrayOutputStream bo = new ByteArrayOutputStream();

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, bo);
        exporter.exportReport();
        byte[] bytes = bo.toByteArray();
        if (bytes != null && bytes.length > 0) {
            response.reset();

            response.setContentType("application/x-msdownload;charset=UTF-8");

            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
            response.setHeader("Access-Control-Allow-Headers", response.getHeader("Access-Control-Request-Headers"));
            response.setHeader("Access-Control-Allow-Origin", "*");

            response.addHeader("Content-Disposition",
                            "filename=" + java.net.URLEncoder.encode(reportItems.get(0).getReportShowName(), "UTF-8") + ".pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream sos = response.getOutputStream();
            sos.write(bytes, 0, bytes.length);
            sos.flush();
            sos.close();
        }
        exporter = null;
        return true;
    }

    /**
     * 打印图片
     * 
     * @param reportItem reportItem
     * @throws JRException JRException
     * @throws IOException IOException
     */
    public static void printToImage(ReportItemEntity<?> reportItem) throws JRException, IOException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String path = request.getServletContext().getRealPath("/reports");
        File file = new File(path + "/" + reportItem.getReportName() + ".jasper");
        JasperReport report = (JasperReport) JRLoader.loadObject(file);

        JRDataSource dataSource = new JRBeanCollectionDataSource(reportItem.getReportDataSource());
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, reportItem.getReportParams(), dataSource);

        JRGraphics2DExporter exporter = new JRGraphics2DExporter();// 创建graphics输出器
        // 创建一个影像对象
        BufferedImage bufferedImage = new BufferedImage(jasperPrint.getPageWidth() * 3, jasperPrint.getPageHeight() * 3, BufferedImage.TYPE_INT_RGB);
        // 取graphics
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        // 设置相应参数信息
        exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
        exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, Float.valueOf("3"));
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.exportReport();
        g.dispose();// 释放资源信息

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // 这里的bufferedImage就是最终的影像图像信息,可以通过这个对象导入到cm中了.
        Thumbnails.of(bufferedImage).size(jasperPrint.getPageHeight() * 3, jasperPrint.getPageWidth() * 3).rotate(0).outputFormat("png")
                        .toOutputStream(response.getOutputStream());
    }

}
