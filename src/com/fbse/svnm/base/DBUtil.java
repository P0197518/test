package com.fbse.svnm.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fbse.common.FBSELogHandler;
import com.fbse.common.FBSEXmlHandler;

public class DBUtil {
    /** ドライブ名 */
    private String driverName;

    /** ユーザー名 */
    private String username;

    /** データベースと接続するURL */
    private String DBurl;

    /** パスワード */
    private String passworde;

    /**
     * データベースの操作
     * <p>
     * <b>処理概要:</b><br>
     * <ul>
     * <li>データベースの操作</li>
     * </ul>
     * @param    なし
     * @return    なし
     * @exception    なし
     */
    public DBUtil() {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "DBUtil", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "setUser", "Start");
        String basePath = Common.getAppPath(FileOperator.class);
        try {
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/DataBaseConfig.xml");
            // 
            driverName = svnXml.xmlSelectNode("//DataBase/DriverName");
            // データベースと接続するURL
            DBurl = svnXml.xmlSelectNode("//DataBase/URL");

            // ユーザー名
            username = svnXml.xmlSelectNode("//DataBase/User");

            // パスワード
            passworde = svnXml.xmlSelectNode("//DataBase/Password");
            Class.forName(driverName).newInstance();
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "DBUtil", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "DBUtil", e
                    .toString());
        }
    }

    /**
     * Connection接続オブジェクトを返す。
     * @return
     */
    public Connection getConnection(){
        Connection conn = null;
        try {
            // データベースとの接続を開く。
            conn = DriverManager.getConnection(DBurl,username,passworde);
        }
        catch (SQLException e) {
        }

        return conn;
    }

    /**
     * Connectionをクローズ
     * @return
     */
    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PreStatementオブジェクトをクローズ
     * @return
     */
    public void closePreStatement(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ResultSetオブジェクトをクローズ
     * @return
     */
    public void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}