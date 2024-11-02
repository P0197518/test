/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト確認画面
 * モジュール名        :SVNM0213Dao.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.17
 * 機能概要          :プロジェクト情報を新規、
 *                  プロジェクト情報を更新し、
 *                  SVNフォルダが存在するかどうかを判断する。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.17 :FBSE)張志明
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import com.fbse.common.FBSEChecker;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.common.FBSEDateHandler;
import com.fbse.common.FBSEXmlHandler;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.base.Common;
import com.fbse.svnm.base.DBUtil;
import com.fbse.svnm.base.FileOperator;
import com.fbse.svnm.base.SendMail;
import com.fbse.svnm.bean.SVNM0213Bean;

/**
 * <HR>
 * SVNM0213Daoのデータベースの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト情報を新規する。</li>
 * <li>プロジェクト情報を更新する。</li>
 * <li>SVNフォルダーが存在するかどうかを判断する。</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0213Dao extends BaseDao implements Runnable {

    private SVNM0213Bean bean;
    private String userId;

    public void run() {
        try {
            update(bean, userId);
        }
        catch (Exception e) {
        }
    }

    /**
     * SVNフォルダが存在するかどうかを判断する。
     * <p>
     * <b>処理概要:</b><br>
     * SVNフォルダが存在するかどうかを判断する。<br>
     * 
     * @param    folderName    SVNフォルダー名
     * @param    projectNo    プロジェクトＩＤ
     * @param    flag    true：編集する時 false:新規する時
     * @return    boolean
     *                   true  SVNフォルダーが存在する
     *                   false SVNフォルダーが存在しない
     * @exception    Exception
     */
    public boolean folderExist(String folderName, String projectNo, boolean flag)
            throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "folderExist", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        para.add(selectSqlReplace(folderName));
        // ＳＱＬ文ＩＤの宣言
        String sqlId = "";
        // 新規する場合
        if (!flag) {
            // パラメータとＳＱＬ文の設定
            sqlId = "SVNM0213S001";
        }
        // 編集する場合
        else {
            // パラメータとＳＱＬ文の設定
            para.add(projectNo);
            sqlId = "SVNM0213S002";
        }
        // SQL文の取得
        String sql = FBSEDBHandler.getSql(sqlId, para);
        // SQL文の実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // データがある場合
        if (result.getNext("table1")) {
            // ログ出力
            log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "folderExist", "End");
            // SVNフォルダが存在次第、trueあるいはfalseを戻る
            return (result.getObject("NUM").toString().equals("1"));
        }
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "folderExist", "End");
        throw new Exception();
    }

    /**
     * プロジェクト情報を新規する。
     * <p>
     * <b>処理概要:</b><br>
     * <ul>
     * プロジェクト情報を新規する。<br>
     * バージョンライブラリを作成する。<br>
     * accessファイルを再作成<br>
     * </ul>
     * 
     * @param    bean    SVNM0213Beanオブジェクト
     * @param    userId    更新者
     * @return    boolean
     *                    true  操作成功
     *                    false 操作失敗
     * @exception    Exception
     */
    public void projectInsert(SVNM0213Bean bean, String userId)
            throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectInsert", "Start");
        // Connectionオブジェクトの宣言
        DBUtil util = new DBUtil();
        Connection con = util.getConnection();
        // パラメータの宣言
        ArrayList para = new ArrayList();
        // 現在の時間を取得
        String now = Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // プロジェクト番号を取得
        String projectNo = getProjectId();
        // メンバーを取得
        String[] member = bean.getMemberNo().split(",");
        // ＳＱＬ文の宣言
        String[] sql = new String[member.length + 1];
        // プロジェクト番号を設定
        para.add(projectNo);
        // SVNフォルダー名を設定
        para.add(updateSqlReplace(Common.trim(bean.getSvnFolderName())));
        // プロジェクト名を設定
        para.add(updateSqlReplace(Common.trim(bean.getProjectName())));
        // ＰＪ責任者を設定
        para.add(bean.getPjMasterNo());
        // ＰＪリーダを設定
        para.add(bean.getPjLeaderNo());
        // 開始日付を設定する
        para.add(bean.getPjStartDate());
        // 終了日付を設定する
        para.add(bean.getPjEndDate());
        // プロジェクト転出日を設定
        para.add(bean.getPjRemoveDate());
        // バックアップ時間間隔を設定
        para.add(FBSEChecker.isEmpty(bean.getPjBackupTime()) ? "0" : bean.getPjBackupTime());
        // 最終バックアップ時間を設定
        para.add(now);
        // コメントを設定
        para.add(updateSqlReplace(Common.trim(bean.getComment())));
        // 作成者IDを設定
        para.add(userId);
        // 作成日を設定
        para.add(now);
        // 更新者IDを設定
        para.add(userId);
        // 更新日を設定
        para.add(now);
        // プロジェクト情報の新規するＳＱＬ文を取得
        sql[0] = FBSEDBHandler.getSql("SVNM0213I001", para);
        for (int i = 0; i < member.length; i++) {
            para = new ArrayList();
            // プロジェクト番号を取得
            para.add(projectNo);
            // メンバーを設定
            para.add(member[i]);
            // 作成者を設定する
            para.add(userId);
            // 作成日を設定
            para.add(now);
            // 更新者IDを設定
            para.add(userId);
            // 更新日を設定
            para.add(now);
            // メンバー情報の新規するＳＱＬ文を取得
            sql[i + 1] = FBSEDBHandler.getSql("SVNM0213I002", para);
        }
        try {
            // AutoCommitにfalseを設定する。
            con.setAutoCommit(false);
            // 更新結果を格納用の変数を宣言し、初期化する
            int updateResult = 0;
            // Statementを設置する。
            Statement state = con.createStatement();
            // Sqlを実行する。
            for (int sqlIndex = 0; sqlIndex < sql.length; sqlIndex++) {
                updateResult = state.executeUpdate(sql[sqlIndex]);
                // 更新に失敗した場合
                if (updateResult < 1 && sqlIndex == 0) {
                    // トランザクションを提出しない。
                    con.rollback();
                    // ログ出力
                    log.printLog("INFO", "SVNM0213Dao", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "projectInsert", "End");
                    throw new Exception();
                }
            }
            // バージョンライブラリーの作成に成功した場合
            if (FileOperator.projectCreate(bean.getSvnFolderName())) {
                // トランザクションを提出する。
                con.commit();
                bean.setProjectNo(projectNo);
                // 権限ファイルの作成フラグを設定する
                boolean flag = false;
                // 権限ファイルを作成
                for(int i=0; i <= 3; i++) {
                    // 作成に成功した場合
                    if (FileOperator.accessReflash()) {
                        flag = true;
                        break;
                    }
                }
                // 権限ファイルを作成成功かどうかの判断
                if (flag) {
                    // ログ出力
                    log.printLog("INFO", "SVNM0213Dao", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "projectInsert", "End");
                    // 結実の戻る。
                    return;
                } else {
                    // ログ出力
                    log.printLog("INFO", "SVNM0213Dao", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "projectInsert", "End");
                    throw new Exception();
                }
            } else {
                // トランザクションを提出しない。
                con.rollback();
                // ログ出力
                log.printLog("INFO", "SVNM0213Dao", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "projectInsert", "End");
                throw new Exception();
            }
        } catch (Exception e) {
            con.rollback();
            // ログ出力
            log.printLog("ERROR", "SVNM0213Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectInsert", e.toString());
            throw e;
        } finally {
            util.closeConnection(con);
        }
    }

    /**
     * プロジェクト情報を更新する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト情報を更新する。<br>
     * 
     * @param    bean    SVNM0213Beanオブジェクト
     * @param    userId    更新者
     * @return    boolean
     *                   true  操作成功
     *                   false 操作失敗
     * @throws Exception 
     * @exception    Exception
     */
    public boolean projectUpdate(SVNM0213Bean bean, String userId) throws Exception{
     // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectUpdate", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        DBUtil util = new DBUtil();
        Connection con = util.getConnection();
        // 現在の時間を取得
        String now = Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // update結果を格納する。
        int updateResult = 0;
        // Statementを設置する。
        Statement state = con.createStatement();
        ArrayList list = new ArrayList();
        // 削除フラグを設定する。
        list.add("1");
        // システムユーザ番号を設定する。
        list.add(userId);
        // システム時間を設定する。
        list.add(now);
        // プロジェクト番号を設定する。
        list.add(bean.getProjectNo());
        // プロジェクト更新日を設定する。
        list.add(bean.getUpdateDate());
        // プロジェクト更新sql文を取得する。
        String sqlLock = FBSEDBHandler.getSql("SVNM0213U006",list);
        try{
            updateResult = state.executeUpdate(sqlLock);
        }catch (SQLException sqlEx){
            if(sqlEx.getSQLState().equals("41000")){
                // ログを出力する。
                log.printLog("INFO", "SVNM0213Dao", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "projectUpdate", "End");
                return false;
            }
        } finally {
            util.closeConnection(con);
        }
        if(updateResult == 0) {
            return false;
        }
        bean.setUpdateDate(now);
        this.bean = bean;
        this.userId = userId;
        new Thread(this).start();
        return true;
    }

    /**
     * プロジェクト情報を更新する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト情報を更新する。<br>
     * 
     * @param    bean    SVNM0213Beanオブジェクト
     * @param    userId    更新者
     * @return    boolean
     *                   true  操作成功
     *                   false 操作失敗
     * @exception    Exception
     */
    public boolean update(SVNM0213Bean bean, String userId)
            throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectUpdate", "Start");
        boolean lockFlag = true;
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbHandler = new FBSEDBHandler();
        DBUtil util = new DBUtil();
        Connection con = util.getConnection();
        // システム日付を取得する
        String now = bean.getUpdateDate();
        // update結果を格納する。
        int updateResult = 0;
        // Statementを設置する。
        Statement state = con.createStatement();
        ArrayList list = new ArrayList();
        // 削除フラグを設定する。
        list.add("0");
        // システムユーザ番号を設定する。
        list.add(userId);
        // システム時間を設定する。
        list.add(bean.getUpdateDate());
        // プロジェクト番号を設定する。
        list.add(bean.getProjectNo());
        // プロジェクト更新日を設定する。
        list.add(bean.getUpdateDate());
        // プロジェクト更新sql文を取得する。
        String sqlUnlock = FBSEDBHandler.getSql("SVNM0213U006",list);
        list.set(0, "3");
        String sqlFail = FBSEDBHandler.getSql("SVNM0213U006",list);
        // メンバー削除フラグ
        int sqlFlag = 0;
        String oldFolderName = getOldFolderName(bean.getProjectNo());
        // パラメータを宣言
        ArrayList para = new ArrayList();
        // パラメータを設定
        para.add(bean.getProjectNo());
        // ＳＱＬ文を取得
        String sqlRemove = FBSEDBHandler.getSql("SVNM0213S006", para);
        // ＳＱＬ文を実行
        FBSEDataResultSet resultRemove = dbHandler.executeSelect(sqlRemove);
        // 結果を処理
        resultRemove.getNext("table1");
        String removeFlag = resultRemove.getObject("REMOVEFLG").toString();
        // パラメータの宣言
        para = new ArrayList();
        // メンバーを取得
        String[] member = bean.getMemberNo().split(",");
        HashSet hsMember = new HashSet();
        StringBuffer bufferMember = new StringBuffer();
        // メンバーをStringBufferに追加する
        for (int i = 0; i < member.length; i++) {
            // メンバーの間に「,」を入れる
            if (bufferMember.length() > 0) {
                bufferMember.append(",");
            }
            // メンバーを追加する
            hsMember.add(member[i]);
            bufferMember.append("'" + member[i] + "'");
        }
        // プロジェクトIDを設定
        para.add(bean.getProjectNo());
        // メンバーを設定
        para.add(bufferMember.toString());
        // SQL文の取得
        String sqlMember = FBSEDBHandler.getSql("SVNM0213S004", para);
        // SQL文を実行
        FBSEDataResultSet result = dbHandler.executeSelect(sqlMember);
        // データが存在している場合
        while (result.getNext("table1")) {
            hsMember.remove(result.getObject("USERNO"));
            sqlFlag = 1;
        }
        // ＳＱＬ文の宣言
        String[] sql = new String[hsMember.size() + 4 + sqlFlag];
        para = new ArrayList();
        // SVNフォルダー名を設定
        para.add(updateSqlReplace(Common.trim(bean.getSvnFolderName())));
        // プロジェクト名を設定
        para.add(updateSqlReplace(Common.trim(bean.getProjectName())));
        // ＰＪ責任者を設定
        para.add(bean.getPjMasterNo());
        // ＰＪリーダを設定
        para.add(bean.getPjLeaderNo());
        // 開始日付を設定する
        para.add(bean.getPjStartDate());
        // 終了日付を設定する
        para.add(bean.getPjEndDate());
        // プロジェクト転出日を設定
        para.add(bean.getPjRemoveDate());
        // バックアップ時間間隔を設定
        para.add(FBSEChecker.isEmpty(bean.getPjBackupTime()) ? "0" : bean.getPjBackupTime());
        // コメントを設定
        para.add(updateSqlReplace(Common.trim(bean.getComment())));
        // 更新者IDを設定
        para.add(userId);
        // 更新日を設定
        para.add(bean.getUpdateDate());
        // プロジェクト番号を設定
        para.add(bean.getProjectNo());
        // 更新日を設定
        para.add(bean.getUpdateDate());
        // プロジェクト情報の新規するＳＱＬ文を取得
        sql[0] = FBSEDBHandler.getSql("SVNM0213U001", para);
        // パラメータを設定
        para = new ArrayList();
        // 移動フラグの設定
        if(FBSEDateHandler.isGreaterEquals(bean.getPjEndDate().replace("/", ""), Common.getCurrentTime("yyyyMMdd"))){
            para.add("0");
        } else {
            para.add("1");
        }
        // 更新者の設定
        para.add(userId);
        // 更新日の設定
        para.add(now);
        // プロジェクトIDの設定
        para.add(bean.getProjectNo());
        // SQL文を取得
        sql[1] = FBSEDBHandler.getSql("SVNM0213U003", para);
        para = new ArrayList();
        // 移動フラグの設定
        if(FBSEDateHandler.isGreaterEquals(bean.getPjRemoveDate().replace("/", ""), Common.getCurrentTime("yyyyMMdd"))){
            para.add("0");
        } else {
            para.add("1");
        }
        // 更新者を設定
        para.add(userId);
        // 更新日の設定
        para.add(now);
        // プロジェクトIDの設定
        para.add(bean.getProjectNo());
        // SQL文を取得
        sql[2] = FBSEDBHandler.getSql("SVNM0213U004", para);
        para = new ArrayList();
        // 更新者IDを設定
        para.add(userId);
        // 更新日時を設定
        para.add(now);
        // プロジェクトIDの設定
        para.add(bean.getProjectNo());
        // メンバーの設定
        para.add(bufferMember.toString());
        // SQL文の取得
        sql[3] = FBSEDBHandler.getSql("SVNM0213D001", para);
        // SQL文の追加
        if (sqlFlag > 0) {
            // SQL文の取得
            sql[4] = FBSEDBHandler.getSql("SVNM0213U002", para);
        }
        Object[] objMember = hsMember.toArray();
        // ループでSQL文を作成
        for (int i = 0; i < objMember.length; i++) {
            para = new ArrayList();
            // プロジェクトIDの取得
            para.add(bean.getProjectNo());
            // メンバーを取得
            para.add(objMember[i]);
            // 作成者IDを設定
            para.add(userId);
            // 作成日時を設定
            para.add(now);
            // 更新者IDを設定
            para.add(userId);
            // 更新日時を設定
            para.add(now);
            // SQL文の取得
            sql[i + 4 + sqlFlag] = FBSEDBHandler.getSql("SVNM0213I002", para);
        }
        try {
            // AutoCommitにfalseを設定する。
            con.setAutoCommit(false);
            // Sqlを実行する。
            for (int sqlIndex = 0; sqlIndex < sql.length; sqlIndex++) {
                updateResult = state.executeUpdate(sql[sqlIndex]);
                // 更新に失敗した場合
                if (updateResult < 1 && sqlIndex == 0) {
                    // トランザクションを提出しない。
                    con.rollback();
                    // ログ出力
                    log.printLog("INFO", "SVNM0213Dao", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "projectUpdate", "End");
                    return false;
                }
            }
            int exeFlag;
            // バージョンライブラリーの名前を変更
            if (FileOperator.projectRename(oldFolderName, bean
                    .getSvnFolderName())) {
                // プロジェクトの移動かどうかの判断
                if (FBSEDateHandler.isGreaterEquals(bean.getPjRemoveDate().replace("/", ""),
                        Common.getCurrentTime("yyyyMMdd"))&& removeFlag.equals("1")) {
                    // プロジェクトを移動
                    exeFlag = FileOperator.projectRemove(bean.getSvnFolderName(), false);
                    if(exeFlag == 0) {
                        // トランザクションを提出する。
                        con.commit();
                        // 権限ファイルを作成フラグ
                        boolean flag = false;
                        // 権限ファイルを作成
                        for(int i=0; i <= 3; i++) {
                            // 権限ファイルを作成成功かどうかの判断
                            if (FileOperator.accessReflash()) {
                                flag = true;
                                break;
                            }
                        }
                        // 権限ファイルの作成
                        if (flag) {
                            SendMail mail = new SendMail();
                            mail.sendMail(bean.getProjectNo(), 0);
                            // ログ出力
                            log.printLog("INFO", "SVNM0213Dao", String
                                    .valueOf((new Throwable()).getStackTrace()[0]
                                            .getLineNumber()), "projectUpdate", "End");
                            // 結実の戻る。
                            return true;
                        }
                        else{
                            SendMail mail = new SendMail();
                            mail.sendMail(bean.getProjectNo(), 5);
                            // ログ出力
                            log.printLog("INFO", "SVNM0213Dao", String
                                    .valueOf((new Throwable()).getStackTrace()[0]
                                            .getLineNumber()), "projectUpdate", "End");
                        }
                    }
                    else if(exeFlag == 1){
                        // トランザクションを提出しない。
                        con.rollback();
                        SendMail mail = new SendMail();
                        mail.sendMail(bean.getProjectNo(), 5);
                        // バージョンライブラリーの名前を変更
                        FileOperator.projectRename(bean.getSvnFolderName(),
                                oldFolderName);
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Dao", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "projectUpdate", "End");
                    } else if(exeFlag == 2) {
                        // トランザクションを提出しない。
                        con.rollback();
                        lockFlag = false;
                        SendMail mail = new SendMail();
                        mail.sendMail(bean.getProjectNo(), 10);
                        // バージョンライブラリーの名前を変更
                        FileOperator.projectRename(bean.getSvnFolderName(),
                                oldFolderName);
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Dao", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "projectUpdate", "End");
                    } 
                }
                // プロジェクトを移動かどうかの判断
                else if(FBSEDateHandler.isLessThan(bean.getPjRemoveDate().replace("/", ""),
                        Common.getCurrentTime("yyyyMMdd"))&& removeFlag.equals("0")) {
                    // プロジェクトを移動
                    exeFlag = FileOperator.projectRemove(bean.getSvnFolderName(), true);
                    if(exeFlag == 0) {
                        // トランザクションを提出する。
                        con.commit();
                        // 権限ファイルを作成フラグ
                        boolean flag = false;
                        // 権限ファイルを作成
                        for(int i=0; i <= 3; i++) {
                            // 権限ファイルを作成成功かどうかの判断
                            if (FileOperator.accessReflash()) {
                                flag = true;
                                break;
                            }
                        }
                        // 権限ファイルの作成
                        if (flag) {
                            SendMail mail = new SendMail();
                            mail.sendMail(bean.getProjectNo(), 0);
                            // ログ出力
                            log.printLog("INFO", "SVNM0213Dao", String
                                    .valueOf((new Throwable()).getStackTrace()[0]
                                            .getLineNumber()), "projectUpdate", "End");
                            // 結実の戻る。
                            return true;
                        }
                        else{
                            SendMail mail = new SendMail();
                            mail.sendMail(bean.getProjectNo(), 5);
                            // ログ出力
                            log.printLog("INFO", "SVNM0213Dao", String
                                    .valueOf((new Throwable()).getStackTrace()[0]
                                            .getLineNumber()), "projectUpdate", "End");
                        }
                    }
                    else if(exeFlag == 1) {
                        // トランザクションを提出しない。
                        con.rollback();
                        SendMail mail = new SendMail();
                        mail.sendMail(bean.getProjectNo(), 5);
                        // バージョンライブラリーの名前を変更
                        FileOperator.projectRename(bean.getSvnFolderName(),
                                oldFolderName);
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Dao", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "projectUpdate", "End");
                    } else {
                        // トランザクションを提出しない。
                        con.rollback();
                        lockFlag = false;
                        SendMail mail = new SendMail();
                        mail.sendMail(bean.getProjectNo(), 10);
                        // バージョンライブラリーの名前を変更
                        FileOperator.projectRename(bean.getSvnFolderName(),
                                oldFolderName);
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Dao", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "projectUpdate", "End");
                    }
                }else{
                 // トランザクションを提出する。
                    con.commit();
                    // 権限ファイルを作成フラグ
                    boolean flag = false;
                    // 権限ファイルを作成
                    for(int i=0; i <= 3; i++) {
                        // 権限ファイルの作成に成功した場合
                        if (FileOperator.accessReflash()) {
                            flag = true;
                            break;
                        }
                    }
                    // 権限ファイルの作成
                    if (flag) {
                        SendMail mail = new SendMail();
                        mail.sendMail(bean.getProjectNo(), 0);
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Dao", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "projectUpdate", "End");
                        // 結実の戻る。
                        return true;
                    }
                    else{
                        SendMail mail = new SendMail();
                        mail.sendMail(bean.getProjectNo(), 5);
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Dao", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "projectUpdate", "End");
                    }
                }
            } else {
                // トランザクションを提出しない。
                con.rollback();
                SendMail mail = new SendMail();
                mail.sendMail(bean.getProjectNo(), 5);
                // ログ出力
                log.printLog("INFO", "SVNM0213Dao", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "projectUpdate", "End");
            }
            throw new Exception();
        } catch (Exception e) {
            con.rollback();
            SendMail mail = new SendMail();
            mail.sendMail(bean.getProjectNo(), 5);
            // ログ出力
            log.printLog("INFO", "SVNM0213Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectUpdate", "End");
            throw e;
        } finally {
            // AutoCommitにtrueを設定する。
            con.setAutoCommit(true);
            if(lockFlag) {
                state.execute(sqlUnlock);
            } else {
                state.execute(sqlFail);
            }
            util.closeConnection(con);
        }
    }

    /**
     * 新規するプロジェクトのプロジェクト番号を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * <ul>
     * 新規するプロジェクトのプロジェクト番号を取得する<br>
     * </ul>
     * 
     * @param    なし
     * @return    String    プロジェクト番号
     * @exception    Exception
     */
    public String getProjectId() throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "getProjectId", "Start");
        // プロジェクト番号の上4桁を取得する
        String leftProjectId = "P" + Common.getCurrentTime("yyyy");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータを宣言
        ArrayList para = new ArrayList();
        // パラメータを設定
        para.add(leftProjectId);
        // ＳＱＬ文を取得
        String sql = FBSEDBHandler.getSql("SVNM0213S003", para);
        // ＳＱＬ文を実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // 結果を処理
        result.getNext("table1");
        // 今年のプロジェクトＩＤの最大値を取得
        String maxNo;
        try {
            maxNo = result.getObject("MAXNO").toString();
        } catch(Exception e) {
            maxNo = "0";
        }
        int tempNo = Integer.parseInt(maxNo) + 1;
        maxNo = String.valueOf(tempNo);
        // 4桁の番号を作成
        while (maxNo.length() < 4) {
            maxNo = "0" + maxNo;
        }
        // プロジェクト番号を取得
        String projectId = leftProjectId + maxNo;
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "getProjectId", "End");
        // プロジェクト番号を戻す
        return projectId;
    }

    /**
     * バージョンライブラリーを削除
     * 
     * @param    projectName    削除したいバージョンライブラリー
     */
    private void projectDelete(String projectName) throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectDelete", "Start");
        String basePath = Common.getAppPath(FileOperator.class);
        // XMLハンドラーを宣言
        FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath + "/SVNConfig.xml");
        // バージョンライブラリーを置くパスを取得
        String library = svnXml.xmlSelectNode("//File/library");
        // バージョンライブラリーを宣言
        File fLibrary = new File(library + "\\" + projectName);
        delDir(fLibrary);
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "projectDelete", "End");
    }

    /**
     * ディレクトリを削除
     * 
     * @param    dir    削除したいディレクトリ
     */
    private void delDir(File dir) {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "delDir", "Start");
        // 該当フォルダからすべてのファイルを取得する
        File files[] = dir.listFiles();
        // 最後のファイルまで行う
        for (int i = 0; i < files.length; i++) {
            // ディレクトリかファイルかの判断
            if (files[i].isDirectory() && files[i].listFiles().length != 0) {
                delDir(files[i]);
            }
            files[i].delete();
        }
        // 該当フォルダを削除する
        dir.delete();
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "delDir", "End");
    }

    /**
     * プロジェクトのフォルダー名の取得
     * @param    projectNo    プロジェクトID
     * @return    なし
     * @exception    Exception
     */
    private String getOldFolderName(String projectNo) throws Exception {
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "getOldFolderName", "Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータを宣言
        ArrayList para = new ArrayList();
        // パラメータを設定
        para.add(projectNo);
        // ＳＱＬ文を取得
        String sql = FBSEDBHandler.getSql("SVNM0213S005", para);
        // ＳＱＬ文を実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // 結果を処理
        result.getNext("table1");
        // ログ出力
        log.printLog("INFO", "SVNM0213Dao", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "getOldFolderName", "End");
        return result.getObject("SVNFOLDERNAME").toString();
    }
}