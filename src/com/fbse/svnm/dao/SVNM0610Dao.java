/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名     :パスワード変更
 * プログラム名       :システムユーザパスワード編集
 * モジュール名       :SVNM0610Dao.java
 * 担当者             :FBSE)王志龍
 * 作成日             :2008.12.17
 * 機能概要           :データベースから元パスワードを取得する
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)王志龍
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;

/**
 * <HR>
 * SVNM0610Daoのデータベースの処理がある。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>データを検索する。</li>
 * <li>データを更新する。</li>
 * </ul>
 * @version V1.0
 * @author FBSE)王志龍
 * @see com.fbse.svnm.base.BaseDao
 */
public class SVNM0610Dao extends BaseDao {

    /** クラス名の宣言と初期化*/
    private static String className = "SVNM0610Dao";

    /**
     * 元パスワードの値を取得する
     * <p>
     * <b>処理概要:</b><br>
     * 元パスワード値の取得メソッド<br>
     * @param    sysUserId    システムユーザーＩＤ
     * @return    String    元のパスワード値
     */
    public String getOldPassword(String sysUserId) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "getOldPassword","Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        try {
            // パラメータを設定
            ArrayList para = new ArrayList();
            // パラメータにシステムユーザー番号を保存する
            para.add(sysUserId);
            // SQL文を取得
            String sql = dbUtil.getSql("SVNM0610S001",para);
            // SQL文を実行する
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            // システムパスワードが存在している場合
            while (result.getNext("table1")) {
                // システムパスワードを取得する
                String sysUserOldPsw = result.getObject("SYSTEMPASSWORD").toString();
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "getOldPassword", "End");
                // 元のパスワードを戻す
                return sysUserOldPsw;
            }
        // 異常時
        } catch (Exception e) {
            // ログの出力
            log.printLog("ERROR", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "getOldPassword", e.toString());
            // nullを戻す
            return null;
        }
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "getOldPassword", "End");
        return null;
    }

    /**
     * パスワードを更新する
     * <p>
     * <b>処理概要:</b><br>
     * パスワード更新のメソッド<br>
     * @param    para    更新情報
     * @return    更新成功時、「1」を戻す;
     *            失敗時、「0」を戻す;
     *            データベース異常時、「-1」を戻す
     */
    public int updateSysPassword(ArrayList para) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "updateSysPassword","Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        try {
            // SQL文を取得
            String sqlUpdatePsw = FBSEDBHandler.getSql("SVNM0610U001", para);
            // SQL文を実行
            int updateFlg = dbUtil.executeUpdate(sqlUpdatePsw);
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "getOldPassword", "End");
            // 更新成功時、「1」を戻す。失敗時、「0」を戻す
            return updateFlg;
        // 異常時
        } catch (Exception e) {
            // ログの出力
            log.printLog("ERROR", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "updateSysPassword", e.toString());
            // 「-1」を戻す
            return -1;
        }
    }
}