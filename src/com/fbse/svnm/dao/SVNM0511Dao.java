/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ 
 * プログラム名        :SVNパスワード定期維持画面
 * モジュール名        :SVNM0511Dao.java
 * 担当者            :FBSE)馬春晶
 * 作成日            :2008.12.18
 * 機能概要          :全部SVNユーザーのSVNパスワードを変更する
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.18    :FBSE)馬春晶
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
 * SVNM0511Daoの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>全部のSVNユーザーを検索する</li>
 * <li>全部SVNユーザーのSVNパスワードを変更する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0511Dao extends BaseDao {

    /**
     * SVNユーザーを検索する。
     * @param    mapping    画面の遷移
     * @param    request    リクエスト
     * @return    ArrayList    ArrayListプロジェクト
     * @throws    Exception    異常
     * @exception    なし
     */
    private ArrayList selectSvnUser() throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0511Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "Start");
        // プロジェクトを格納用の配列を宣言する
        ArrayList list = new ArrayList();
        try {
            // SVNユーザーの検索するｓｑｌ文を取得する
            String sql = FBSEDBHandler.getSql("SVNM0511S001");
            // FBSEDBHandlerオブジェクトを宣言する
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // sqｌ文を実行する
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            // 結果を配列に保存する
            while(result.getNext("table1")) {
                // 結果を保存する
                list.add(result.getObject("SVNLOGINNAME"));
            }
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0511Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "selectSvnUser", e.toString());
            throw e;
        }
        // ログを出力
        log.printLog("INFO", "SVNM0511Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "End");
        return list;
    }

    /**
     * 新SVNパスワードを更新する。
     * @param    newSvnPassword    新SVNパスワード
     * @param    updateUser    更新者
     * @param    updateDate    更新時間
     * @return    boolean    操作結果
     *                   true    更新することが成功だ
     *                   false   更新することが失敗だ
     * @throws    Exception    異常
     * @exception    なし
     */
    public boolean updateSvnPassword (String newSvnPassword,
                                      String updateUser,
                                      String updateDate) 
                                      throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0511Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "Start");
        // 戻る操作結果
        boolean state = false;
        // ArrayListのプロジェクトを宣言する
        ArrayList list = new ArrayList();
        // 接続を宣言する
        Connection con = null;
        // Statementを宣言する
        Statement statement = null;
        try {
            // 接続を取得する
            con = new DBUtil().getConnection();
            // Statementを設置する
            statement = con.createStatement();
            // AutoCommitにfalseを設定する。
            con.setAutoCommit(false);
            // 全部のSVNユーザーの登録名の配列を取得する
            ArrayList svnList = selectSvnUser();
            // 新SVNパスワードを暗号化
            String filePassword = FileOperator.getFilePassword(svnList.get(0).toString(), newSvnPassword);
            // 新SVNパスワードを設定する
            list.add(filePassword);
            // 更新者を設定する
            list.add(updateUser);
            // 更新時間を設定する
            list.add(updateDate);
            // SVNパスワードの更新するｓｑｌ文を取得する
            String sql = FBSEDBHandler.getSql("SVNM0511U001", list);
            // sqｌ文を実行する
            int result = statement.executeUpdate(sql);
            // データがある場合
            if (result > 0) {
                // SVNユーザーの登録名の最後まで行う
                for (int i = 0;i < svnList.size(); i++) {
                    // SVN登録名を取得する
                    String svnLoginName = svnList.get(i).toString();
                    // SVNユーザーファイルの更新に成功した場合
                    if (FileOperator.setUser(svnLoginName, filePassword)) {
                        // 戻る操作結果を設定する
                        state = true;
                    }
                    // SVNユーザーファイルの更新に失敗した場合
                    else {
                        // トランザクションを戻す。
                        con.rollback();
                        // 戻る操作結果を設定する
                        state = false;
                        // forを終われる
                        break;
                    }
                }
                // 全部のデータを成功にデータベースとファイルに修正した場合
                if (state) {
                    // トランザクションを提出する。
                    con.commit();
                }
            }
        }catch (Exception e) {
            // トランザクションを戻す。
            con.rollback();
            // 戻る操作結果を設定する
            state = false;
            // ログを出力
            log.printLog("ERROR", "SVNM0511Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "selectSvnUser", e.toString());
        }
        // ログを出力
        log.printLog("INFO", "SVNM0511Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "End");
        return state;
    }
}