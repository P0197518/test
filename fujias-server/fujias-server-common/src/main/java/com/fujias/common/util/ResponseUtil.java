package com.fujias.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * Responseの共通処理クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public final class ResponseUtil {

    private ResponseUtil() {
    }

    /**
     * 文字列を出力する
     * 
     * @param response response
     * @param content content
     */
    public static void writeText(HttpServletResponse response, String content) throws IOException {

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().write(content);
    }

}
