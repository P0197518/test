/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト新規画面
 * モジュール名        :SVNM0211Dao.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.17
 * 機能概要          :SVNフォルダが存在するかどうかを判断する
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.17 :FBSE)張志明
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
 * SVNM0211Daoのデータベースの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>SVNフォルダが存在するかどうかを判断する。</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0211Dao extends BaseDao {

    /**
     * SVNフォルダが存在するかどうかを判断する。
     * <p>
     * <b>処理概要:</b><br>
     * SVNフォルダが存在するかどうかを判断する。<br>
     * 
     * @param    folderName    SVNフォルダ名
     * @return    boolean
     *                    true  SVNフォルダが存在する
     *                    false SVNフォルダが存在しない
     * @exception    Exception
     */
    public boolean folderExist(String folderName) throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0211Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "folderExist", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        para.add(selectSqlReplace(folderName));
        // SQL文の取得
        String sql = FBSEDBHandler.getSql("SVNM0211S001", para);
        // SQL文の実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        //データがある場合
        if (result.getNext("table1")) {
            // ログ出力
            log.printLog("INFO", "SVNM0211Dao", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "folderExist", "End");
            // SVNフォルダが存在するかどうかを判断する
            return (result.getObject("NUM").toString().equals("1"));
        }
        // ログ出力
        log.printLog("INFO", "SVNM0211Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "folderExist", "End");
        throw new Exception();
    }
}