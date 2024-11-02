package com.fbse.svnm.base;

import java.io.File;
import java.security.MessageDigest;
import java.util.Date;
import com.fbse.common.FBSEFormatUtil;
import com.fbse.common.FBSELogHandler;
import com.fbse.common.FBSEXmlHandler;

/**
 * <HR>
 * 共通メソッドである。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>現在のシステム時間を取得する</li>
 * <li>文字列のMD5値の取得</li>ログオブジェクトを取得
 * <li>ログオブジェクトを取得</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 */
public class Common {

    /**
     * 現在のシステム時間を取得する
     * 
     * @param format
     *            指定した時間のフォーマット、例：yyyyMMddHHmmssSSS
     * @return 指定フォーマットの現在のシステム時間
     */
    public static String getCurrentTime(String format) {
        return FBSEFormatUtil.formatDateTime(new Date(), format);
    }

    /**
     * 入力した文字列のMD5値の取得
     * 
     * @param str
     *            MD5値を取得する文字列
     * @return 入力した文字列のMD5値
     */
    public static String convertToMD5(String str) {
        // MessageDigestオブジェクトを宣言
        MessageDigest md5;
        // 戻る値
        String strMd5 = null;
        try {
            // MessageDigestオブジェクトをインスタンス
            md5 = MessageDigest.getInstance("MD5");
            // 配列形式のMD5値
            byte[] byTemp = md5.digest(str.getBytes());
            // 一時変数
            int temp;
            // StringBufferオブジェクトを宣言
            StringBuffer buf = new StringBuffer();
            // 配列形式のMD5値を文字列形式のMD5値に切り替える
            for (int offset = 0; offset < byTemp.length; offset++) {
                temp = byTemp[offset];
                if (temp < 0) {
                    temp = temp + 256;
                }
                if (temp < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(temp));
            }
            // StringBufferを文字列に切り替える
            strMd5 = buf.toString();
        } catch (Exception e) {
        }
        return strMd5;
    }

    /**
     * 該当プログラムの実行パスを取得
     * 
     * @param className
     *            クラス
     * @return 該当プログラムの実行パス
     */
    public static String getAppPath(Class className) {
        // ユーザが伝わったパラメータが空白かをチェック
        if (className == null)
            throw new java.lang.IllegalArgumentException("パラメータは空白ではいけない！");
        ClassLoader loader = className.getClassLoader();
        // クラスの完全名を取得（パッケージを含む）
        String clsName = className.getName() + ".class";
        // 伝わったパラメータが存在するパッケージを取得
        Package pack = className.getPackage();
        String path = "";
        // 匿名パッケージかない場合、パッケージ名をパスに変換する。
        if (pack != null) {
            String packName = pack.getName();
            // システムクラスを伝送する時、異常か発生する
            if (packName.startsWith("java.") || packName.startsWith("javax.")) {
                throw new java.lang.IllegalArgumentException(
                        "異常：システムクラスを伝送しないでください。");
            }
            // クラスのファイル名を取得する
            clsName = clsName.substring(packName.length() + 1);
            // パッケージ名が簡単パッケージ名の時、このパッケージ名はパスに切り替える
            if (packName.indexOf(".") < 0) {
                path = packName + "/";
            }
            // パッケージ名の組み合わせ部分によって、パッケージ名はパスに切り替える
            else {
                int start = 0, end = 0;
                end = packName.indexOf(".");
                while (end != -1) {
                    path = path + packName.substring(start, end) + "/";
                    start = end + 1;
                    end = packName.indexOf(".", start);
                }
                path = path + packName.substring(start) + "/";
            }
        }
        // ClassLoaderのgetResourceメソッドを呼び出し、パスインフォメーションを含むクラスのファイル名を引き渡す。
        java.net.URL url = loader.getResource(path + clsName);
        // URLオブジェクトからパスインフォメーションを取得する
        String realPath = url.getPath();
        // パスインフォメーションからプロトコル名"file:"を削除する
        int pos = realPath.indexOf("file:");
        if (pos > -1)
            realPath = realPath.substring(pos + 5);
        // クラスパスを取得する
        pos = realPath.indexOf(path + clsName);
        realPath = realPath.substring(0, pos - 1);
        // クラスファイルはJARファイルにある時、対応するJARファイル名を削除する
        if (realPath.endsWith("!"))
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 該当プロジェクトが実行しているパスを戻す
        return realPath;
    }

    /**
     * ログオブジェクトを取得
     * 
     * @return ログオブジェクト
     */
    public static FBSELogHandler getLog() {
        FBSELogHandler log = null;
        try {
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // パスワードファイルのパスを取得
            String logPath = svnXml.xmlSelectNode("//Log/LogPath");
            File fLog = new File(logPath);
            if (!fLog.getParentFile().exists()) {
                fLog.getParentFile().mkdirs();
            }
            // ログ出力するオブジェクトのインスタンス
            log = new FBSELogHandler(basePath + "/SVNConfig.xml",
                    "//SVNConfig/Log/LogPath", "//SVNConfig/Log/LogFileSize",
                    "//SVNConfig/Log/IsDayLog");
        } catch (Exception e) {
        }
        // ログオブジェクト
        return log;
    }

    /**
     * 空白を削除するメッソド
     * @param    str    文字
     * @return    なし
     */
    public static String trim(String str) {
        // 文字がヌルの場合
        if(str == null){
            return str;
        }
        // 空白を削除する
        str = str.trim();
        // 文字の長さが零の場合
        if(str.length() == 0){
            return str;
        }
        // 文字の長さが零じゃない、それに文字の先頭は空白が括る場合
        while(str.length() != 0 && (str.charAt(0) == '　' || str.charAt(0) == ' ')) {
            str = str.substring(1);
        }
        // 文字の長さが零じゃない、それに文字の後部は空白が括る場合
        while(str.length() != 0 && (str.charAt(str.length()-1) == '　' || str.charAt(str.length()-1) == ' ')) {
            str = str.substring(0,str.length()-1);
        }
        return str;
    }
}
