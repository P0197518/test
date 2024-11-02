/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名       :マスタメンテ 
 * プログラム名        :従業員編集画面
 * モジュール名        :SVNM0412Dao.java
 * 担当者           :FBSE)馬春晶
 * 作成日           :2008.12.17
 * 機能概要          :従業員の編集
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.17   :FBSE)馬春晶
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
import com.fbse.svnm.base.Common;
import com.fbse.svnm.base.DBUtil;
import com.fbse.svnm.base.FileOperator;
import com.fbse.svnm.bean.SelectBean;

/**
 * <HR>
 * SVNM0412Daoの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0412画面の初期表示を行う</li>
 * <li>従業員情報の編集を行う</li>
 * <li>従業員登録名の正確性のチェックを行う</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0412Dao extends BaseDao {

    public String pjPrivilege = "";

    public String masterPrivilege = "";
    
    /**
     * 従業員登録名の正確性のチェックを行う。<p>
     * <b>処理概要:</b><br>
     * 従業員登録名の正確性のチェックを行う
     * @param    svnLoginName    SVN登録名
     * @param    userNo    従業員番号
     * @return    boolean
     *                   false SVN登録名がデータベースにある
     *                   true  SVN登録名がデータベースにない
     * @exception    Exception    異常
     */
    public boolean checkSvnUser (String svnLoginName, 
                                 String userNo)
                                 throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0412Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "checkSvnUser", "Start");
        // 引数を設定する
        ArrayList list = new ArrayList();
        // 従業員番号を設定する
        list.add(selectSqlReplace(svnLoginName));
        list.add(userNo);
        // sql文を取得する
        String sql = FBSEDBHandler.getSql("SVNM0412S002", list);
        // FBSEDBHandlerオブジェクトを宣言する
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // sql文を実行する
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // 登録名が重複した場合
        if (result.getRecordCount("table1") != 0) {
            // falseを返す
            return false;
        }
        // ログを出力
        log.printLog("INFO", "SVNM0412Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "checkSvnUser", "End");
        // 登録名が重複じゃない
        return true;
    }

    /**
     * 初期化のデータを検索する。<p>
     * <b>処理概要:</b><br>
     * 初期化のデータを検索する。
     * @param    userNo    従業員番号
     * @return    FBSEDataResultSet    検索するデータを保存する
     * @exception    Exception    異常
     */
    public FBSEDataResultSet selectUser (String userNo) throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0412Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "Start");
        //FBSEDataResultSetオブジェクトを宣言する
        FBSEDataResultSet result = null ;
        try {
            // 引数を設定する
            ArrayList list = new ArrayList();
            // 従業員番号を設定する
            list.add(selectSqlReplace(userNo));
            // sql文を取得する
            String sql = FBSEDBHandler.getSql("SVNM0412S001", list);
            // FBSEDBHandlerオブジェクトを宣言する
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // sql文を実行する
            result = dbUtil.executeSelect(sql);
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0412Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "selectSvnUser", e.toString());
            throw e;
        }
        // ログを出力
        log.printLog("INFO", "SVNM0412Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "End");
        return result;
    }

    /**
     * 初期化の権限データを検索する。<p>
     * <b>処理概要:</b><br>
     * 初期化の権限データを検索する。
     * @param    なし
     * @return    SelectBean[]    検索するデータを保存する
     * @exception    Exception    異常
     */
    public SelectBean[] selectDownList () throws Exception {
        return getCommbox();
    }

    /**
     * 従業員の情報の編集を行う。<p>
     * <b>処理概要:</b><br>
     * 従業員の情報をデータベースに更新する
     * 従業員の情報をSVNユーザーファイルに更新する
     * 従業員の情報を権限ファイルに更新する
     * @param    userNo    従業員番号
     * @param    userName    従業員名前
     * @param    systemPassword    新しいシステム登録パスワード
     * @param    oldSysPassword    元のシステム登録パスワード
     * @param    svnLoginName    SVN登録名
     * @param    mail    メール
     * @param    svnLoginPassword    新しいのSVNパスワード
     * @param    oldPassword    元のSVNパスワード
     * @param    privilegeno    権限番号
     * @param    comment    コメント
     * @param    updateUser    修正者
     * @param    updateDate    修正日
     * @return    int
     *               1    更新しているデータは他の人が更新された
     *               2    異常が発生する
     *               3    更新することが成功
     *               4    登録名が重複だ
     * @exception    Exception    異常
     */
    public int updateUser (String userNo,
                           String userName,
                           String systemPassword,
                           String svnLoginName,
                           String oldLoginName,
                           String mail,
                           String svnLoginPassword,
                           String oldPassword,
                           String privilegeno,
                           String comment,
                           String updateUser,
                           String updateDate)
                           throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0412Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updateSvnUser", "Start");
        // 操作結果
        int state = 1;
        // 接続を宣言する
        Connection con = null;
        // Statementを宣言する
        Statement statement = null;
        try {
            // 接続を取得する
            con = new DBUtil().getConnection();
            // Statementを設置する
            statement = con.createStatement();
            // 登録名は重複した場合
            if (!checkSvnUser(updateSqlReplace(svnLoginName), userNo)) {
                // 削除フラグを4に設定する
                state = 4;
                // ログを出力
                log.printLog("INFO", "SVNM0412Dao", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "updateSvnUser", "End");
                // 結果を返す
                return 4;
            }
            // 元のsql文の引数
            StringBuffer updateSql = new StringBuffer();
            // システム登録パスワードは空ではない場合
            if (!systemPassword.equals("")) {
                // 入力する従業員登録パスワード(MD5)を取得する
                String logSysPass = Common.convertToMD5(systemPassword);
                // システム登録パスワードを修正ＳＱＬを追加する
                updateSql.append("SM_USERMASTER.SYSTEMPASSWORD='" + logSysPass + "', ");
            }
            // 元のｓｑｌ文の引数
            StringBuffer svnPassSql = new StringBuffer();
            // svnパスワードを設定する
            String password = oldPassword;
            // 更新する操作を行う
            if (!svnLoginPassword.equals("")) {
                // 入力する従業員登録パスワード(MD5)を取得する
                String svnLogPass = FileOperator.getFilePassword(svnLoginName, svnLoginPassword);
                password = svnLogPass;
                // SVNパスワードを修正ｓｑｌ文を追加する
                svnPassSql.append("SM_USERMASTER.SVNPASSWORD='" + svnLogPass + "', ");
            }
            // 元の表の引数を設定するリストの宣言する
            ArrayList list = new ArrayList();
            // 元の表のｓｑｌ文に従業員名前を設定する
            list.add(userName);
            // 元の表のｓｑｌ文に従業員登録パスワードを設定する
            list.add(updateSql.toString());
            // 元の表のｓｑｌ文にSVN登録名を設定する
            list.add(updateSqlReplace(svnLoginName));
            // 元の表のsql文にSVNパスワードを設定する
            list.add(svnPassSql.toString());
            // 元の表のｓｑｌ文にメールを設定する
            list.add(updateSqlReplace(mail));
            // 臨時表のｓｑｌ文にメールを設定する
            // 元の表のsql文に権限番号を設定する
            list.add(privilegeno);
            // 元の表のｓｑｌ文にコメントを設定する
            list.add(updateSqlReplace(comment));
            // 元の表のｓｑｌ文に更新者を設定する
            list.add(updateUser);
            // 元の表のｓｑｌ文に更新時間を設定する
            list.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
            // 元の表のsql文に更新されているユーザー番号を設定する
            list.add(userNo);
            // 元の表のsql文に更新時間を設定する（排他）
            list.add(updateDate);
            // 元の表のsql文を取得する
            String sql = FBSEDBHandler.getSql("SVNM0412U001", list);
            // AutoCommitにfalseを設定する
            con.setAutoCommit(false);
            // sql文を実行する
            int result = statement.executeUpdate(sql);
            // 更新することが成功した場合
            if (result > 0 ) {
                // SVNユーザーファイルから対応するデータを成功に更新した場合
                if(FileOperator.removeUser(oldLoginName)) {
                    // SVNユーザーファイルに対応するデータを成功に設定する
                    if(FileOperator.setUser(svnLoginName, password)) {
                        // トランザクションを提出する。
                        con.commit();
                        // SVN登録名を更新された
                        if (!svnLoginName.equals(oldLoginName)) {
                            // 権限ファイルを三回に更新する
                            for (int i = 0 ;i < 2; i ++ ) {
                                // 権限ファイルを新くする
                                if(FileOperator.accessReflash()) {
                                    // 結果を設定
                                    state = 3;
                                    break ;
                                } else {
                                    // 結果を設定
                                    state = 2;
                                }
                            }
                        }
                        // SVN登録名を更新されなくてSVNユーザーファイルを成功に更新された
                        else {
                            // トランザクションを提出する。
                            con.commit();
                            // 結果を設定
                            state = 3;
                        }
                    }
                }
                // SVNユーザーファイルから対応するデータの修復に失敗した場合
                else {
                    // トランザクションを提出しない。
                    con.rollback();
                    // 結果を設定
                    state = 2;
                }
            }
            // 更新しているデータは他の人に更新された場合
            else {
                // 結果を設定
                state = 1;
            }
        }catch (Exception e) {
            // トランザクションを提出しない。
            con.rollback();
            // ログを出力
            log.printLog("ERROR", "SVNM0412Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updateSvnUser", e.toString());
            // 結果を戻す
            return 2;
        } finally {
            // 接続を閉める
            new DBUtil().closeConnection(con);
        }
        if (state == 3 && userNo.equals(updateUser)) {
            // 検索条件を設定する。
            ArrayList list = new ArrayList();
            // ユーザー番号を設定する。
            list.add(userNo);
            // sql文を取得する。
            String sql = FBSEDBHandler.getSql("SVNM0412S003", list);
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // sql文を実行する。
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            // 検索データが存在する場合。（番号とパスワードが正しい。）
            if(result.getNext("table1")) {
                // プロジェクト管理の権限を取得する。
                pjPrivilege = (String)result.getObject("PJPRIVILEGE");
                // マスタメンテの権限を取得する。
                masterPrivilege = (String)result.getObject("MASTERPRIVILEGE");
            }
        }
        // ログを出力
        log.printLog("INFO", "SVNM0412Dao", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updateSvnUser", "End");
        // 結果を戻す
        return state;
    }
}