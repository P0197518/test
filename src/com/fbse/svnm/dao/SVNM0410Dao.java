/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名       :マスタメンテ 
 * プログラム名        :従業員検索画面
 * モジュール名        :SVNM0410Dao.java
 * 担当者            :FBSE)馬春晶
 * 作成日            :2008.12.12
 * 機能概要          :従業員の検索、削除、修復
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:    2008.12.12   :FBSE)馬春晶
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
 * SVNM0410Daoの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0410画面の初期表示を行う</li>
 * <li>従業員の情報の検索を行う</li>
 * <li>従業員の情報の削除を行う</li>
 * <li>従業員の情報の修復を行う</li>
 * <li>従業員の情報の従業員編集画面への遷移を行う</li>
 * <li>従業員の情報の従業員新規画面への遷移を行う</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0410Dao extends BaseDao {

    /**
     * 従業員を検索する。<p>
     * <b>処理概要:</b><br>
     * 入力した検索条件により従業員の情報をデータベースから検索する。<br>
     * @param    userId    従業員番号
     * @param    userName    従業員名前
     * @param    svnLoginName    SVN登録名
     * @param    privilege    権限
     * @param    state    状態
     * @return    FBSEDataResultSet    FBSEDataResultSetに検索した結果を保存する
     * @exception    Exception    異常
     */
    public FBSEDataResultSet selectUser (String userId,
                                         String userName,
                                         String svnLoginName,
                                         String privilege,
                                         String state)
                                         throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectUser", "Start");
        try {
            // 引数を設定する
            ArrayList list = new ArrayList();
            // 従業員番号を設定する
            list.add(selectSqlReplace(userId));
            // 従業員名前を設定する
            list.add(selectSqlReplace(userName));
            // 従業員登録名を設定する
            list.add(selectSqlReplace(svnLoginName));
            // 削除フラグを設定する
            list.add(state);
            // StringBufferのプロジェクトを宣言する
            StringBuffer sql = new StringBuffer();
            // 権限がある場合
            if (privilege != null && !privilege.equals("")) {
                // 権限の条件を追加する
                list.add(" AND SM_PRIVILEGEMASTER.PRIVILEGENO LIKE '%" + privilege + "%' ");
            }
            else {
                list.add(" ");
            }
            // sql文を取得する
            sql.append(FBSEDBHandler.getSql("SVNM0410S001", list));
            String sql2 = sql.toString();
            // FBSEDBHandlerオブジェクトを宣言する
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // sql文を実行する
            FBSEDataResultSet result = dbUtil.executeSelect(sql.toString());
            // ログを出力
            log.printLog("INFO", "SVNM0410Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "selectUser", "End");
            return result;
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0410Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "selectUser", e.toString());
            throw e;
        }
    }

    /**
     * 従業員を削除する。<p>
     * <b>処理概要:</b><br>
     * 選択された従業員によって従業員の情報をデータベースから削除する。<br>
     * 選択された従業員によって対応する従業員の情報を従業員ファイルから削除する。<br>
     * 選択された従業員によって対応する従業員の情報を権限ファイルから削除する。<br>
     * @param    userId    従業員番号
     * @param    updateUser    更新者
     * @param    newUpdateDate    目下の更新時間
     * @param    oldUpdateDate    元の更新時間
     * @param    userLoginName    従業員登録名
     * @param    svnLoginPassword    SVN登録名
     * @return    boolean
     *                   true  削除成功
     *                   false 削除失敗
     * @exception    Exception    異常
     */
    public boolean deleteUser (String userId,
                               String updateUser,
                               String newUpdateDate,
                               String oldUpdateDate,
                               String userLoginName,
                               String svnLoginPassword)
                               throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "deleteUser", "Start");
        // 接続を宣言する
        Connection con = null;
        // Statementを宣言する
        Statement state = null;
        // 結果を設定
        boolean flashState = false;
        try {
            // 配列を宣言する
            String[] sql = new String[8];
            // 接続を取得する
            con = new DBUtil().getConnection();
            // Statementを設置する。
            state = con.createStatement();
            // update結果を格納する。
            int updateResult = 0;
            // ArrayListプロジェクトを宣言する
            ArrayList list = new ArrayList();
            // 更新者を設定する
            list.add(updateUser);
            // 新しい更新時間を設定する
            list.add(newUpdateDate);
            // ユーザー番号を設定する
            list.add(userId);
            // 古い更新時間
            list.add(oldUpdateDate);
            // ArrayListプロジェクトを宣言する
            ArrayList listPj = new ArrayList();
            // 更新者を設定する
            listPj.add(updateUser);
            //  新しい更新時間を設定する
            listPj.add(newUpdateDate);
            // ユーザー番号を設定する
            listPj.add(userId);
            // sql文を取得する
            sql[0] = FBSEDBHandler.getSql("SVNM0410D001", list);
            sql[1] = FBSEDBHandler.getSql("SVNM0410D002", listPj);
            sql[2] = FBSEDBHandler.getSql("SVNM0410D003", listPj);
            sql[3] = FBSEDBHandler.getSql("SVNM0410D004", listPj);
            // AutoCommitにfalseを設定する。
            con.setAutoCommit(false);
            // 従業員表を更新する
            updateResult = state.executeUpdate(sql[0]);
            if (updateResult >= 1) {
                // Sqlを実行する
                for(int sqlIndex = 1; sqlIndex < 4; sqlIndex++) {
                    updateResult = state.executeUpdate(sql[sqlIndex]);
                }
                // SVNファイルから対応するデータを成功に削除した場合
                if(FileOperator.removeUser(userLoginName)) {
                    // トランザクションを提出する。
                    con.commit();
                    // 権限ファイルを新しくする 
                    for (int i = 0 ;i < 2 ;i ++) {
                        // 権限ファイルを新しくする 
                        if(FileOperator.accessReflash()) {
                            // 結果を設定する
                            flashState = true;
                            // forを終われる
                            break;
                        } else {
                            // 結果を設定する
                            flashState = false;
                        }
                    }
                    // 権限ファイルを新しくする 操作が失敗する
                    if (!flashState) {
                        throw new Exception();
                    }
                }
                // データの削除に失敗した場合
                else {
                    // トランザクションを提出しない。
                    con.rollback();
                    // 結実の戻る。
                    throw new Exception();
                }
            }
        }catch (Exception e) {
            // トランザクションを提出しない。
            con.rollback();
            // ログを出力
            log.printLog("ERROR", "SVNM0410Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "deleteUser", e.toString());
            // 結実の戻る。
            throw e;
        } finally {
            // 接続を閉じる
            new DBUtil().closeConnection(con);
        }
        // ログを出力
        log.printLog("INFO", "SVNM0410Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "deleteUser", "End");
        return flashState;
    }

    /**
     * 従業員を修復する。<p>
     * <b>処理概要:</b><br>
     * 選択された従業員によって従業員の情報をデータベースに修復する。<br>
     * 選択された従業員によって対応する従業員の情報を従業員ファイルに修復する。<br>
     * 選択された従業員によって対応する従業員の情報を権限ファイルに修復する。<br>
     * @param    userId    従業員番号
     * @param    updateUser    更新者
     * @param    newUpdateDate    新しい更新日付
     * @param    oldUpdateDate    元の更新時間
     * @param    svnLoginPassword    SVNパスワード
     * @param    svnLoginName    SVN登録名
     * @return    boolean
     *                   true  修復成功
     *                   false 修復失敗
     * @exception    Exception    異常
     */
    public boolean backUpUser (String userId,
                               String updateUser,
                               String newUpdateDate,
                               String oldUpdateDate,
                               String svnLoginPassword,
                               String svnLoginName)
                               throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "backUpUser", "Start");
        // 接続を宣言する
        Connection con = null;
        // Statementを宣言する
        Statement state = null;
        // 操作の結果
        boolean flashState = false;
        try {
            // 配列を宣言する
            String[] sql = new String[2];
            // 接続を取得する
            con = new DBUtil().getConnection();
            // update結果を格納する。
            int updateResult = 0;
            // Statementを設置する。
            state = con.createStatement();
            // ArrayListオブジェクトを宣言する(従業員情報表の修正するArrayList)
            ArrayList list = new ArrayList();
            // 更新者を設定する
            list.add(updateUser);
            // 新更新時間
            list.add(newUpdateDate);
            // 更新されている従業員番号
            list.add(userId);
            // 元の更新時間
            list.add(oldUpdateDate);
            // ArrayListオブジェクトを宣言する(プロジェクト表の修正するArrayList)
            ArrayList listPj = new ArrayList();
            // 更新者を設定する
            listPj.add(updateUser);
            // 新更新時間
            listPj.add(newUpdateDate);
            // 更新されている従業員番号
            listPj.add(userId);
            // sql文を取得する
            sql[0] = FBSEDBHandler.getSql("SVNM0410U001", list);
            sql[1] = FBSEDBHandler.getSql("SVNM0410U002", listPj);
            // AutoCommitにfalseを設定する。
            con.setAutoCommit(false);
            // 修復を行う
            updateResult = state.executeUpdate(sql[0]);
            // 結果がある場合
            if (updateResult >= 1) {
                // Sqlを実行する。
                updateResult = state.executeUpdate(sql[1]);
                // SVNファイルから対応するデータを成功に修復した場合
                if(FileOperator.setUser(svnLoginName, svnLoginPassword)) {
                    // トランザクションを提出する
                    con.commit();
                    // 権限ファイルを新しくにする
                    for (int i = 0;i < 2 ;i++) {
                        // 権限ファイルを新しくにする
                        if(FileOperator.accessReflash()) {
                            // 結果を設定する
                            flashState = true;
                            // forを終われる
                            break;
                        } else {
                            // 結果を設定する
                            flashState = false;
                        }
                    }
                    // 権限ファイルを新しくにすることが失敗だ
                    if (!flashState) {
                        // 異常
                        throw new Exception();
                    }
                }
                // SVNユーザーファイルから対応するデータを失敗に修復する
                else {
                    // トランザクションを提出しない。
                    con.rollback();
                    // 異常
                    throw new Exception();
                }
            }
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0410Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "backUpUser", e.toString());
            // 結実の戻る。
            throw e;
        } finally {
            // 接続を閉める
            new DBUtil().closeConnection(con);
        }
        // ログを出力
        log.printLog("INFO", "SVNM0410Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "backUpUser", "End");
        return flashState;
    }
}