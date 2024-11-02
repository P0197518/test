package com.fujias.common.util.poi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import cn.afterturn.easypoi.entity.vo.BigExcelConstants;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.entity.vo.TemplateExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import cn.afterturn.easypoi.view.PoiBaseView;

/**
 * Poi的操作工具类
 * 
 * @author chenqiang
 *
 */
public class PoiUtil {

    private static final String ERROR_MESSAGE = "<html><script type='text/javascript'>alert('导出数据不存在，请确认。');</script></html>";

    public static <T> void downloadByTemplate(String fileName, String templatePath, Map<String, Object> headerData, List<T> listData,
                    HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (listData == null || listData.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(ERROR_MESSAGE);
            return;
        }
        Map<String, Object> map = headerData;

        map.put("listData", listData);
        ModelMap modelMap = new ModelMap();
        modelMap.put(TemplateExcelConstants.FILE_NAME, fileName);
        modelMap.put(TemplateExcelConstants.PARAMS, new TemplateExportParams("excelTemplate/" + templatePath));
        modelMap.put(TemplateExcelConstants.MAP_DATA, map);
        PoiBaseView.render(modelMap, request, response, TemplateExcelConstants.EASYPOI_TEMPLATE_EXCEL_VIEW);

    }

    /**
     * 下载excel数据
     * 
     * @param fileName fileName
     * @param templatePath templatePath
     * @param headerData headerData
     * @param listData listData
     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    public static void downloadByTemplate(String fileName, String templatePath, Map<String, Object> headerData, Map<String, List<?>> listData,
                    String[] sheetNames, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (listData == null || listData.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");

            response.getWriter().write(ERROR_MESSAGE);
            return;
        }

        Map<String, Object> map = headerData;

        listData.keySet().forEach((item) -> {
            map.put(item, listData.get(item));
        });

        ModelMap modelMap = new ModelMap();
        modelMap.put(TemplateExcelConstants.FILE_NAME, fileName);
        TemplateExportParams templateExportParams = new TemplateExportParams("excelTemplate/" + templatePath, true);
        if (sheetNames != null && sheetNames.length > 0) {
            templateExportParams.setSheetName(sheetNames);
        }
        modelMap.put(TemplateExcelConstants.PARAMS, templateExportParams);
        modelMap.put(TemplateExcelConstants.MAP_DATA, map);
        PoiBaseView.render(modelMap, request, response, TemplateExcelConstants.EASYPOI_TEMPLATE_EXCEL_VIEW);

    }

    public static <T> void downloadByDefault(String fileName, String sheetName, String titleName, Map<String, Object> headerData, List<T> listData,
                    HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (listData == null || listData.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(ERROR_MESSAGE);
            return;
        }

        ExportParams params = new ExportParams(titleName, sheetName, ExcelType.XSSF);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TemplateExcelConstants.FILE_NAME, fileName);
        map.put(NormalExcelConstants.DATA_LIST, listData);
        map.put(NormalExcelConstants.CLASS, listData.get(0).getClass());
        map.put(NormalExcelConstants.PARAMS, params);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 大数据量情况下导出excel
     * 
     * @param fileName fileName
     * @param title excel标题
     * @param classType 带出数据类型
     * @param dataService 执行分步检索的sql
     * @param queryParams 检索条件
     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    public static void downloadBigData(String fileName, String title, Class<?> classType, IExcelExportServer dataService, Object queryParams,
                    HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelMap map = new ModelMap();
        ExportParams params = new ExportParams(title, title, ExcelType.XSSF);
        map.put(BigExcelConstants.FILE_NAME, fileName);
        map.put(BigExcelConstants.CLASS, classType);
        map.put(BigExcelConstants.PARAMS, params);
        // 就是我们的查询参数,会带到接口中,供接口查询使用
        map.put(BigExcelConstants.DATA_PARAMS, queryParams);
        map.put(BigExcelConstants.DATA_INTER, dataService);
        PoiBaseView.render(map, request, response, BigExcelConstants.EASYPOI_BIG_EXCEL_VIEW);
    }

}
