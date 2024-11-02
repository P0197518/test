/*******************************************************************
 * システム名          :SVN管理システム
 * サブシステム名       :マスタメンテ
 * プログラム名         :権限新規画面
 * モジュール名         :SVNM0311Dao.java
 * 担当者             :FBSE)張志明
 * 作成日             :2008.12.18
 * 機能概要           :権限情報を新規する
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.18 :FBSE)張志明
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.base.Common;
import com.fbse.svnm.bean.SVNM0311Bean;

/**
 * <HR>
 * SVNM0311Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>データを新規する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0311Dao extends BaseDao {

    // クラス名の宣言と初期化
    private static String className = "SVNM0311Dao";

    /**
     * 権限情報の新規を行う。<p>
     * <b>処理概要:</b><br>
     * 権限情報の新規を行う。<br>
     * @param    bean    SVNM0311Beanオブジェクト
     * @param    userId    ユーザーID
     * @return     なし
     * @exception    Exception    異常
     */
    public void insertPrivilege(String userId, SVNM0311Bean bean)
    throws Exception {
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "insertPrivilege", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        // システム日付を取得する
        String now = Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // 権限IDを設定
        para.add(getMaxPrivilegeNo());
        // 権限名を設定
        para.add(updateSqlReplace(Common.trim(bean.getSvnm0311PrivilegeName())));
        // プロジェクト管理権限を設定
        para.add(bean.getProjectPrivilegeCode());
        // マスタメンテ権限を設定
        para.add(bean.getMasterPrivilegeCode());
        // コメントを設定
        para.add(updateSqlReplace(Common.trim(bean.getPrivilegeComment())));
        // 更新者を設定
        para.add(userId);
        // 更新日を設定
        para.add(now);
        // 作成者を設定
        para.add(userId);
        // 作成日を設定
        para.add(now);
        // SQL文の取得
        String sql = FBSEDBHandler.getSql("SVNM0311I001", para);
        // SQL文の実行
        if (dbUtil.executeInsert(sql) > 0) {
            // ログ出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "insertPrivilege", "End");
            return;
        }
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "insertPrivilege", "End");
        throw new Exception();
    }

    /**
     * 権限名重複のチェックを行う。<p>
     * <b>処理概要:</b><br>
     * 権限名重複性のチェックを行う。<br>
     * @param    privilegeName    権限名
     * @return    boolean
     *               false    権限名がデータベースにある。
     *               true     権限名がデータベースにない。
     * @exception    Exception    異常
     */
    public boolean checkPrivilege(String privilegeName) throws Exception {
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "checkPrivilege", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        para.add(selectSqlReplace(privilegeName));
        // SQL文の取得
        String sql = FBSEDBHandler.getSql("SVNM0311S001", para);
        // SQL文の実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // データがある場合
        if (result.getNext("table1")) {
            // ログ出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "checkPrivilege", "End");
            // 権限名は存在次第、falseかtrueか戻る
            return (result.getObject("NUM").toString().equals("0"));
        }
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "checkPrivilege", "End");
        throw new Exception();
    }

    /**
     * データベースに権限番号の最大値を取得する。<p>
     * <b>処理概要:</b><br>
     * データベースに権限番号の最大値を获得する。<br>
     * @return    maxRoleNo    権限番号の最大値。
     * @exception    Exception    異常
     */
    private String getMaxPrivilegeNo() throws Exception{
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "getMaxPrivilegeNo", "Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // ＳＱＬ文を取得
        String sql = FBSEDBHandler.getSql("SVNM0311S002");
        // ＳＱＬ文を実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // 結果を処理
        result.getNext("table1");
        // 権限番号の最大値を取得
        String maxNo;
        try {
            maxNo = result.getObject("MAXNO").toString();
        } catch (Exception e) {
            maxNo = "0";
        }
        int tempNo = Integer.parseInt(maxNo) + 1;
        maxNo = String.valueOf(tempNo);
        // 2桁の番号を取得
        while (maxNo.length() < 2) {
            maxNo = "0" + maxNo;
        }
        // 権限番号を取得
        String privilegeNo = "R" + maxNo;
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "getMaxPrivilegeNo", "End");
        // 権限番号を戻す
        return privilegeNo;
    }
}