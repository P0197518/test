/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト編集画面
 * モジュール名        :SVNM0212Dao.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.17
 * 機能概要          :プロジェクト情報を取得し、
 *                  プロジェクトが存在するかどうかを判断する。
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
 * SVNM0212Daoのデータベースの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト情報を検索する。</li>
 * <li>SVNフォルダが存在するかどうかを判断する。</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0212Dao extends BaseDao {

    /**
     * SVNフォルダが存在するかどうかを判断する。
     * <p>
     * <b>処理概要:</b><br>
     * SVNフォルダが存在するかどうかを判断する。<br>
     * 
     * @param    folderName    SVNフォルダ名
     * @return    boolean
     *                   true  SVNフォルダが存在する
     *                   false SVNフォルダが存在しない
     * @exception    Exception
     */
    public boolean folderExist(String folderName, String projectId)
            throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0212Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "folderExist", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        // フォルダ名を設定
        para.add(selectSqlReplace(folderName));
        // プロジェクトIDを設定
        para.add(projectId);
        // SQL文の取得
        String sql = FBSEDBHandler.getSql("SVNM0212S001", para);
        // SQL文の実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // データがある場合
        if (result.getNext("table1")) {
            // SVNフォルダが存在次第、trueあるいはfalseを戻る。
            return (result.getObject("NUM").toString().equals("1"));
        }
        // ログ出力
        log.printLog("INFO", "SVNM0212Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "folderExist", "End");
        throw new Exception();
    }

    /**
     * プロジェクト情報を検索する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト情報の検索メソッド<br>
     * 
     * @param    projectId    プロジェクト番号
     * @return    FBSEDataResultSet    プロジェクト情報
     * @exception    Exception
     */
    public FBSEDataResultSet projectSelect(String projectId) throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0212Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectSelect", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        // プロジェクトIDを設定
        para.add(projectId);
        // SQL文の取得
        String sqlProject = FBSEDBHandler.getSql("SVNM0212S002", para);
        String sqlMember = FBSEDBHandler.getSql("SVNM0212S003", para);
        ArrayList sql = new ArrayList();
        // SQL文の設定
        sql.add(sqlProject);
        sql.add(sqlMember);
        // SQL文の実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // ログ出力
        log.printLog("INFO", "SVNM0212Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectSelect", "End");
        // プロジェクト情報を戻す
        return result;
    }
}