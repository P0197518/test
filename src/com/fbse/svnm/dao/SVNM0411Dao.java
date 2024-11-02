/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名     :マスタメンテ（従業員管理）
 * プログラム名       :従業員新規画面
 * モジュール名       :SVNM0411Dao.java
 * 担当者             :FBSE)王志龍
 * 作成日             :2008.12.17
 * 機能概要           :従業員新規画面に対してデータベースの操作
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)王志龍
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.base.DBUtil;
import com.fbse.svnm.base.FileOperator;

/**
 * <HR>
 * SVNM0411Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>データを検索する。</li>
 * <li>データを新規する。</li>
 * <li>SVNファイルに該当するSVN登録名とパスワードを追加する。</li>
 * </ul>
 * @version  V1.0
 * @author   FBSE)王志龍
 * @see com.fbse.svnm.base.BaseDao
 */
public class SVNM0411Dao extends BaseDao {

    /** クラス名の宣言と初期化*/
    private static String className = "SVNM0411Dao";

    /**
     * 従業員番号が存在するかどうかを判断する
     * <p>
     * <b>処理概要:</b><br>
     * 従業員番号が存在するかどうかの判断メソッド<br>
     *
     * @param    sysUserNo    従業員番号
     * @return    Boolean:true    該当する従業員番号が存在する;
     *            Boolean:false    該当する従業員番号が存在しない;
     *            null    データベース異常
     */
    public Boolean isSysUserNoExist(String sysUserNo) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "isSysUserNoExist","Start");
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "isSysUserNoExist", "End");
        // 存在している場合、trueを戻る。存在しない場合、falseを戻る
        return isExist(sysUserNo,"SVNM0411S001");
    }

    /**
     * SVN登録名が存在するかどうかの判断メソッド。
     * <p>
     * <b>処理概要:</b><br>
     * SVN登録名が存在するかどうかの判断メソッド<br>
     *
     * @param    sysUserNo    SVN登録名
     * @return    Boolean:true    該当するSVN登録名が存在する;
     *            Boolean:false    該当するSVN登録名が存在しない;
     *            null    データベース異常
     */
    public Boolean isSvnLoginNameExist(String svnLoginName) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "isSvnLoginNameExist","Start");
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "isSvnLoginNameExist", "End");
        // isExistを戻す
        return isExist(svnLoginName,"SVNM0411S002");
    }

    /**
     * 入力した内容が存在するかどうかの判断メソッド。
     * <p>
     * <b>処理概要:</b><br>
     * 入力した内容が存在するかどうかの判断メソッド<br>
     *
     * @param    strName    入力した内容;
     *           sqlId    SQL文ID
     * @return    Boolean:true    該当する入力した内容が存在する;
     *            Boolean:false    該当する入力した内容が存在しない;
     *            null    データベース異常
     */
    private Boolean isExist(String strName, String sqlId){
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "isExist","Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // 存在性の変数の宣言と初期化
        Boolean isExist = false;
        try {
            // パラメータを設定
            ArrayList para = new ArrayList();
            // パラメータにシステムユーザー番号を保存する
            para.add(strName);
            // SQL文を取得
            String sql = dbUtil.getSql(sqlId,para);
            // SQL文を実行する
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            // データが存在している場合
            if(result.getRecordCount("table1") > 0){
                // isExistの値を「true」に設定する
                isExist = true;
            }
        // 異常時
        } catch (Exception e) {
            // ログの出力
            log.printLog("ERROR", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "isExist", e.toString());
            // nullを戻す
            return null;
        }
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "isExist", "End");
        // isExistを戻す
        return isExist;
    }

    /**
     * 従業員新規のメソッド。
     * <p>
     * <b>処理概要:</b><br>
     * 従業員新規<br>
     * SVNファイルに該当するSVN登録名とパスワードを追加する<br>
     *
     * @param    para    新規情報
     * @return    新規成功時、「1」を戻す;
     *            失敗時、「0」を戻す;
     *            データベース異常時、「-1」を戻す
     */
    public int insertSysUser(ArrayList para) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "insertSysUser","Start");
        // Connectionオブジェクトを宣言する
        Connection con = null;
        // DBUtilオブジェクトの宣言と初期化
        DBUtil dbUtil = new DBUtil();
        try {
            // 接続を取得する
            con = dbUtil.getConnection();
            // AutoCommitにfalseを設定する
            con.setAutoCommit(false);
            // Statementを設定する
            Statement statement = con.createStatement();
            // SQL文を取得
            String sql = FBSEDBHandler.getSql("SVNM0411I001", para);
            // SQL文を実行する
            int insertFlg = statement.executeUpdate(sql);
            // 新規に成功した場合
            if (insertFlg > 0 ) {
                // SVNユーザーファイルで対応するデータを成功に新規した場合
                if(FileOperator.setUser(para.get(3).toString()         // SVN登録名
                        ,para.get(4).toString())) {                    // SVNパスワード
                    // トランザクションをコミットする
                    con.commit();
                    // insertFlgに「1」を保存する
                    insertFlg = 1;
                }
                // SVNユーザーファイルから対応するデータの新規に失敗した場合
                else {
                    // トランザクションをロールバックする
                    con.rollback();
                    // insertFlgに「-1」を保存する
                    insertFlg = -1;
                }
            }
            // データベースを閉じる
            dbUtil.closeConnection(con);
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "insertSysUser", "End");
            // 新規成功時、「1」を戻す。失敗時、「0」を戻す。ファイル異常時、「-1」を戻す
            return insertFlg;
        // 異常時
        } catch (Exception e) {
            // データベースを閉じる
            dbUtil.closeConnection(con);
            // ログの出力
            log.printLog("ERROR", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "insertSysUser", e.toString());
            // 「-1」を戻す
            return -1;
        }
    }
}