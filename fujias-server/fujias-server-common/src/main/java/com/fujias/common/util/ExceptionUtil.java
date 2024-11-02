package com.fujias.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 異常のエラー処理共通クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public final class ExceptionUtil {

    private ExceptionUtil() {

    }

    /**
     * 異常の完全情報を取得する
     * 
     * @param e 異常
     * @return 完全情報
     */
    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            try {
                if (sw != null) {
                    sw.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.toString();
    }

}
