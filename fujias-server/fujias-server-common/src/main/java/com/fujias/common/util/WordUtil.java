package com.fujias.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author 王琦
 *
 */
public class WordUtil {

    private Configuration configuration = null;

    @SuppressWarnings("deprecation")
    public WordUtil() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    /**
     * 下载word
     * 
     * @param request request
     * @param response response
     * @param data data
     * @param fileName fileName
     * @throws UnsupportedEncodingException Exception
     */
    public void createDoc(HttpServletRequest request, HttpServletResponse response, Object data, String fileName)
                    throws UnsupportedEncodingException {

        String strDate = DateUtil.getNowDateString("yyyyMMddHHmmssSSS");
        // 设置模本装置方法和路径
        String path = request.getSession().getServletContext().getRealPath("/wordTemplate");
        // String formPath = "../word";

        Template t = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(path));
            // 选择要装载的模板
            t = configuration.getTemplate(fileName + ".ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出文档路径及名称
        String toFilePath = "/temp/" + DateUtil.getNowDateString("yyyyMMdd");
        String toMkdir = request.getSession().getServletContext().getRealPath(toFilePath);
        File fileMkdir = new File(toMkdir);
        if (!fileMkdir.exists()) {
            fileMkdir.mkdirs();
        }

        String toPath = toMkdir + "\\" + fileName + "_" + strDate + ".docx";
        File outFile = new File(toPath);
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Writer out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter owriter = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(owriter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(data, out);
            out.close();
            fos.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 下载文件
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + RequestUtil.getEncodeFileName(fileName + "_" + strDate + ".docx"));
        response.setContentType("application/x-download");

        // 设置文件路径
        File file = new File(toPath);
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                new File(toPath).delete();
            }
        }
    }
}
