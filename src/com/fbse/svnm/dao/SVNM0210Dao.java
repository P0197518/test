/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト検索画面
 * モジュール名        :SVNM0210Dao.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.17
 * 機能概要          :プロジェクト情報の検索、削除、修復
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)張建君
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.base.DBUtil;
import com.fbse.svnm.base.FileOperator;
import com.fbse.svnm.base.SendMail;
import com.fbse.svnm.bean.SVNM0210Bean;
import java.sql.ResultSetMetaData;

/**
 * <HR>
 * SVNM0210Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト情報を検索する。</li>
 * <li>プロジェクトを削除する。</li>
 * <li>プロジェクトを修復する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0210Dao extends BaseDao implements Runnable {

    private String projectNo;
    private String projectUpdateDate;
    private String sysUserId;
    private String sysTime;
    private String svnFolderName;
    private boolean start;

    public void run() {
        try {
            update(projectNo, projectUpdateDate, sysUserId, sysTime,
                    svnFolderName, start);
        }
        catch (Exception e) {
        }
    }

    /**
     * 結果変換（HashMap）。<P>
     * <b>処理概要:</b><br>
     * ResultSetよりHashMapを作成する。<br>
     * @param    rs    結実集を格納する
     * @param    tableName    テーブル名を格納する
     * @param    resultSetMap    取得したデータを格納する
     * @return    HashMap    取得するデータを格納するオブジェクト
     * @exception    Exception    異常
     */
    private HashMap<String, ArrayList<HashMap<String, Object>>> toHashMap(ResultSet rs,
            String tableName, HashMap<String, ArrayList<HashMap<String, Object>>> resultSetMap)
             throws Exception {
        // MetaData結実集を声明する。
        ResultSetMetaData rsmd = rs.getMetaData();
        // 列数を取得する
        int columCount = rsmd.getColumnCount();
        // columNameを格納する。
        ArrayList<String> columName = new ArrayList<String>();
        // 列名を取得する
        for(int i = 1; i <= columCount; i++) {
            columName.add(rsmd.getColumnName(i).toUpperCase());
        }
        // tableを格納する。
        ArrayList<HashMap<String, Object>> tables = new ArrayList<HashMap<String, Object>>();
        // 行を格納する
        HashMap<String, Object> rows;
        // rowsの値を設定する。
        while(rs.next()) {
            // rowsを声明する。
            rows = new HashMap<String, Object>();
            // rowsの値を格納する。
            for(int j = 1;j <= columCount;j++) {
                rows.put(columName.get(j - 1), rs.getObject(j));
            }
            // 行の値をテーブルのなかに格納する
            tables.add(rows);
        }
        // テーブルの値をテーブル集を格納する
        resultSetMap.put(tableName, tables);
        // 結果を戻る。
        return resultSetMap;
    }

    /**
     * プロジェクト情報を検索する。<P>
     * <b>処理概要:</b><br>
     * 検索条件によりプロジェクト情報を検索する。<br>
     * @param    bean    検索条件
     * @param    pjPrivilege    プロジェクト管理の権限
     * @param    sysUserNo    システムユーザー番号
     * @return    FBSEDataResultSet    プロジェクト情報
     * @exception    Exception    異常
     */
    public FBSEDataResultSet selectProject(SVNM0210Bean bean, String pjPrivilege, String sysUserNo) throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "selectProject", "Start");
        // 検索条件を設定する。
        ArrayList list = new ArrayList();
        list.add("正常");
        list.add("処理中");
        list.add("処理失敗");
        // プロジェクト状態を設定する。
        list.add(bean.getDelflgSql());
        // sql文の宣言。
        StringBuffer sqlBuffer = new StringBuffer("");
        // SVNフォルダ名は入力していた場合
        if(!bean.getSvnFolderNameSql().equals("")) {
            // SVNフォルダ名の検索条件を追加する。
            sqlBuffer.append(" AND SM_PROJECT.SVNFOLDERNAME LIKE '%" + selectSqlReplace(bean.getSvnFolderNameSql()) + "%' ");
        }
        // プロジェクト名を入力した場合。
        if(!bean.getProjectNameSql().equals("")) {
            // プロジェクト名の検索条件を追加する。
            sqlBuffer.append(" AND SM_PROJECT.PROJECTNAME LIKE '%" + selectSqlReplace(bean.getProjectNameSql()) + "%' ");
        }
        // ＰＪ責任者を入力した場合。
        if(!bean.getPjMasterNoSql().equals("")) {
            sqlBuffer.append(" AND SM_PROJECT.PJMASTER = '" + bean.getPjMasterNoSql() + "' ");
        }
        // ＰＪリーダを入力した場合。
        if(!bean.getPjLeaderNoSql().equals("")) {
            sqlBuffer.append(" AND SM_PROJECT.PJLEADER = '" + bean.getPjLeaderNoSql() + "' ");
        }
        // 開始時間は入力していた場合
        if (!bean.getPjStartDateSql().equals("")) {
            sqlBuffer.append(" AND SM_PROJECT.PJSTARTDATE >= '" + bean.getPjStartDateSql() + "' ");
        }
        // 終了時間は入力していた場合
        if (!bean.getPjEndDateSql().equals("")) {
            sqlBuffer.append(" AND SM_PROJECT.PJENDDATE <= '" + bean.getPjEndDateSql() + "' ");
        }
        // ユーザー権限は「読み権限」がある場合。
        if(pjPrivilege.equals("1")) {
            sqlBuffer.append(" AND (SM_PROJECT.PJMASTER = '" + sysUserNo + "'");
            sqlBuffer.append(" OR SM_PROJECT.PJLEADER = '" + sysUserNo + "'");
            sqlBuffer.append(" OR SM_PROJECT.PROJECTNO IN (SELECT SM_PRO_RELATION.PROJECTNO");
            sqlBuffer.append(" FROM SM_PRO_RELATION ");
            sqlBuffer.append(" WHERE SM_PRO_RELATION.USERNO = '" + sysUserNo + 
                    "' AND SM_PRO_RELATION.DELFLG='0'))");
        }
        // 検索条件を追加する。
        list.add(sqlBuffer.toString());
        // プロジェクト検索sql文を取得する。
        String sql = FBSEDBHandler.getSql("SVNM0210S001",list);
        // 接続を取得する。
        Connection con = new DBUtil().getConnection();
        // Statemenｔオブ ジェクトを取得する。
        PreparedStatement state = con.prepareStatement(sql);
        // プロジェクト情報を検索する。
        ResultSet set = state.executeQuery();
        // FBSEDataResultSetオブ ジェクトの宣言。
        FBSEDataResultSet resultSelect = new FBSEDataResultSet();
        // 結実集に取得データを格納する。
        resultSelect.setResultSet(toHashMap(set,"TABLE1",new HashMap()));
        // FBSEDataResultSetオブジェクトを宣言する。
        FBSEDataResultSet dataResultSet = new FBSEDataResultSet();
        // HashMapオブジェクトを宣言する。
        HashMap result = new HashMap();
        // ArrayListオブジェクトを宣言する。
        ArrayList records = new ArrayList();
        // HashMapオブジェクトを宣言する。
        HashMap recordValues = null;
        while (resultSelect.getNext("table1")) {
            // HashMapオブジェクトを宣言する。
            recordValues = new HashMap();
            // プロジェクト番号を設定する。
            recordValues.put("PROJECTNO", resultSelect.getObject("PROJECTNO"));
            // SVNフォルダー名を設定する。
            recordValues.put("SVNFOLDERNAME", resultSelect.getObject("SVNFOLDERNAME"));
            // プロジェクト名を設定する。
            recordValues.put("PROJECTNAME", resultSelect.getObject("PROJECTNAME"));
            recordValues.put("DEALFLG", resultSelect.getObject("DEALFLG"));
            try {
                // ＰＪ責任者を設定する。
                recordValues.put("PJMASTER", resultSelect.getObject("PJMASTER"));
            } catch(Exception e) {
                // ＰＪ責任者を設定する。
                recordValues.put("PJMASTER", "");
            }
            try {
                // ＰＪリーダを設定する。
                recordValues.put("PJLEADER", resultSelect.getObject("PJLEADER"));
            } catch(Exception e) {
                // ＰＪリーダを設定する。
                recordValues.put("PJLEADER", "");
            }
            // 始まる時間を設定する。
            recordValues.put("PJSTARTDATE", resultSelect.getObject("PJSTARTDATE"));
            // 終わる時間を設定する。
            recordValues.put("PJENDDATE", resultSelect.getObject("PJENDDATE"));
            try {
                // コメントを設定する。
                recordValues.put("COMMENT", resultSelect.getObject("COMMENT"));
            } catch(Exception e) {
                // コメントを設定する。
                recordValues.put("COMMENT", "");
            }
            // 更新日時を設定する。
            recordValues.put("UPDATEDATE", resultSelect.getObject("UPDATEDATE"));
            // ArrayListオブジェクトを宣言する。
            list = new ArrayList();
            // レコードを追加する。
            list.add(resultSelect.getObject("PROJECTNO"));
            // 従業員検索SQL文を取得する。
            sql = FBSEDBHandler.getSql("SVNM0210S002",list);
            // sql文を実行する。
            state = con.prepareStatement(sql);
            // プロジェクト情報を検索する。
            set = state.executeQuery();
            // FBSEDataResultSetオブ ジェクトの宣言。
            FBSEDataResultSet resultSelectSvnUser = new FBSEDataResultSet();
            // 結実集に取得データを格納する。
            resultSelectSvnUser.setResultSet(toHashMap(set,"TABLE1",new HashMap()));
            // 従業員番号の配列。
            String[] userNo = new String[resultSelectSvnUser.getRecordCount("table1")];
            // 従業員名前の配列。
            String[] userName = new String[resultSelectSvnUser.getRecordCount("table1")];
            //従業員番号の配列。（検索条件）
            String[] selectUserNo = bean.getMemberSql().split(",");
            // 従業員名前文字列。
            String userNames = "";
            // 従業員番号の最後まで行う
            for(int i = 0; i < userNo.length && resultSelectSvnUser.getNext("table1"); i++) {
                // 従業員番号を取得する。
                userNo[i] = (String)resultSelectSvnUser.getObject("USERNO");
                // 従業員名前を取得する。
                userName[i] = (String)resultSelectSvnUser.getObject("USERNAME");
            }
            // メンバーがある場合。
            if(userName.length > 0) {
                // 従業員名前文字列。
                userNames = userName[0];
                // 従業員名前を取得する。
                for(int i = 1; i < userName.length; i++){
                    // 従業員名前を取得する。
                    userNames += "、" + userName[i];
                }
            }
            // 従業員名前を設定する。
            recordValues.put("MEMBER", userNames);
            // 検索条件が存在する場合
            if(!bean.getMemberSql().equals("")) {
                // ループ回数。
                int i = 0;
                // 最後の従業員番号まで行う
                for(i = 0; i < selectUserNo.length; i++) {
                    // 従業員番号の存在フラグ。
                    boolean noStart = false;
                    // 最後の従業員番号まで行う
                    for(int j = 0; j < userNo.length; j++) {
                        // 従業員番号が存在する場合。
                        if(selectUserNo[i].equals(userNo[j])) {
                            // 従業員番号の存在フラグをtrueに設定する
                            noStart = true;
                            // ループ終了。
                            break;
                        }
                    }
                    // 従業員番号が存在しない場合。
                    if(!noStart) {
                        // ループ終了。
                        break;
                    }
                }
                // 従業員番号が全部存在する場合。
                if(i == selectUserNo.length) {
                    // レコードを追加する。
                    records.add(recordValues);
                }
            } else {
                // レコードを追加する。
                records.add(recordValues);
            }
        }
        // HashMapに検索結果を設定する。
        result.put("TABLE1", records);
        // FBSEDataResultSetに検索結果を設定する。
        dataResultSet.setResultSet(result);
        // ResultSetをクローズ。
        set.close();
        // PreparedStatementをクローズ。
        state.close();
        // Connectionをクローズ。
        con.close();
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "selectProject", "End");
        // 検索結果を戻る。
        return dataResultSet;
    }

    public boolean fukuGen(String projectNo, String projectUpdateDate, String sysUserId,
            String sysTime) throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "updateProject", "Start");
        // 接続を取得する。
        Connection con = null;
        // Statemenｔオブ ジェクトを取得する。
        Statement state = null;
        try {
            // 接続を取得する。
            con = new DBUtil().getConnection();
            // Statemenｔオブ ジェクトを取得する。
            state = con.createStatement();
            // update結果を格納する。
            int updateResult = 0;
            // 検索条件を設定する。 
            ArrayList list = new ArrayList();
            // 処理フラグを設定する。
            list.add("0");
            // システムユーザ番号を設定する。
            list.add(sysUserId);
            // システム時間を設定する。
            list.add(sysTime);
            // プロジェクト番号を設定する。
            list.add(projectNo);
            // プロジェクト更新日を設定する。
            list.add(projectUpdateDate);
            // プロジェクト更新sql文を取得する。
            String sqlUnlock = FBSEDBHandler.getSql("SVNM0210U005",list);
            try{
                updateResult = state.executeUpdate(sqlUnlock);
            }catch (SQLException sqlEx){
                if(sqlEx.getSQLState().equals("41000")){
                    // ログを出力する。
                    log.printLog("INFO", "SVNM0210Dao", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "updateProject", "End");
                    return false;
                }
            }
            if(updateResult == 0) {
                return false;
            }
            return true;
        } catch (Exception e){
            // ログを出力する。
            log.printLog("ERROR", "SVNM0210Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updateProject", e.toString());
            // 異常をスロー。
            throw e;
        }
        finally {
            // Statementをクローズ。
            state.close();
            // 接続をクローズ。
            con.close();
        }
    }

    /**
     * プロジェクトを削除する、またはプロジェクトを修復する。<P>
     * <b>処理概要:</b><br>
     * プロジェクトを削除する、またはプロジェクトを修復する。
     * @param    projectNo    プロジェクト番号
     * @param    projectUpdateDate    プロジェクト更新時間
     * @param    sysUserId    システムユーザ番号
     * @param    sysTime    システム時間
     * @param    svnFolderName    SVNフォルダ名
     * @param    start    操作状態<br>
     *                true  プロジェクトを削除する。<br>
     *                false プロジェクトを修復する。
     * @throws Exception 
*/
    public int updateProject(String projectNo,
                             String projectUpdateDate,
                             String sysUserId,
                             String sysTime,
                             String svnFolderName,
                             boolean start) throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "updateProject", "Start");
        // 接続を取得する。
        Connection con = null;
        // Statemenｔオブ ジェクトを取得する。
        Statement state = null;
        String sqlUnlock = null;
        try {
            // 接続を取得する。
            con = new DBUtil().getConnection();
            // Statemenｔオブ ジェクトを取得する。
            state = con.createStatement();
            // update結果を格納する。
            int updateResult = 0;
            // 検索条件を設定する。 
            ArrayList list = new ArrayList();
            // 処理フラグを設定する。
            list.add("1");
            // システムユーザ番号を設定する。
            list.add(sysUserId);
            // システム時間を設定する。
            list.add(sysTime);
            // プロジェクト番号を設定する。
            list.add(projectNo);
            // プロジェクト更新日を設定する。
            list.add(projectUpdateDate);
            // プロジェクト更新sql文を取得する。
            String sqlLock = FBSEDBHandler.getSql("SVNM0210U005",list);
            try{
                updateResult = state.executeUpdate(sqlLock);
            }catch (SQLException sqlEx){
                if(sqlEx.getSQLState().equals("41000")){
                    // ログを出力する。
                    log.printLog("INFO", "SVNM0210Dao", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "updateProject", "End");
                    return 2;
                }
            }
            if(updateResult == 0) {
                return 2;
            }
            this.projectNo = projectNo;
            this.projectUpdateDate = sysTime;
            this.sysUserId = sysUserId;
            this.sysTime = sysTime;
            this.svnFolderName = svnFolderName;
            this.start = start;
            new Thread(this).start();
            return 0;
        } catch (Exception e){
            // トランザクションを提出しない。
            if(!con.getAutoCommit()) {
                con.rollback();
            }
            // AutoCommitにtrueを設定する。
            con.setAutoCommit(true);
            state.execute(sqlUnlock);
            // ログを出力する。
            log.printLog("ERROR", "SVNM0210Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updateProject", e.toString());
            // 異常をスロー。
            throw e;
        }
        finally {
            // Statementをクローズ。
            state.close();
            // 接続をクローズ。
            con.close();
        }
    }

    /**
     * プロジェクトを削除する、またはプロジェクトを修復する。<P>
     * <b>処理概要:</b><br>
     * プロジェクトを削除する、またはプロジェクトを修復する。
     * @param    projectNo    プロジェクト番号
     * @param    projectUpdateDate    プロジェクト更新時間
     * @param    sysUserId    システムユーザ番号
     * @param    sysTime    システム時間
     * @param    svnFolderName    SVNフォルダ名
     * @param    start    操作状態<br>
     *                true  プロジェクトを削除する。<br>
     *                false プロジェクトを修復する。
     * @return    int <br>
     *               0  プロジェクトの削除またはプロジェクトの修復に成功する。<br>
     *               1  プロジェクトの削除またはプロジェクトの修復に失敗する。<br>
     *               2  排他が発生した。
     * @exception    Exception
     */
    public int update(       String projectNo,
                             String projectUpdateDate,
                             String sysUserId,
                             String sysTime,
                             String svnFolderName,
                             boolean start) throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "updateProject", "Start");
        // 接続を取得する。
        Connection con = null;
        // Statemenｔオブ ジェクトを取得する。
        Statement state = null;
        String sqlUnlock = null;
        try {
            // 接続を取得する。
            con = new DBUtil().getConnection();
            // Statemenｔオブ ジェクトを取得する。
            state = con.createStatement();
            // update結果を格納する。
            int updateResult = 0;
            // 検索条件を設定する。 
            ArrayList list = new ArrayList();
            // 処理フラグを設定する。
            list.add("1");
            // システムユーザ番号を設定する。
            list.add(sysUserId);
            // システム時間を設定する。
            list.add(sysTime);
            // プロジェクト番号を設定する。
            list.add(projectNo);
            // プロジェクト更新日を設定する。
            list.add(projectUpdateDate);
            if(start) {
                // 削除フラグを設定する。
                list.set(0, "1");
            } else {
                // 削除フラグを設定する。
                list.set(0, "0");
            }
            list.set(4, sysTime);
            String sql = FBSEDBHandler.getSql("SVNM0210U004",list);
            // 処理フラグを設定する。
            list.set(0, "0");
            sqlUnlock = FBSEDBHandler.getSql("SVNM0210U005",list);
            // 処理フラグを設定する。
            list.set(0, "2");
            String sqlFail = FBSEDBHandler.getSql("SVNM0210U005",list);
            // AutoCommitにfalseを設定する。
            con.setAutoCommit(false);
            // プロジェクト更新sql文を実行。
            updateResult = state.executeUpdate(sql);
            // 実行状態。
            int startFlag = 0;
            // 更新した場合
            if (updateResult > 0) {
                // 削除に成功した場合
                int flag = FileOperator.projectDelete(svnFolderName, start);
                if(flag == 0) {
                    // コミット。
                    con.commit();
                    SendMail mail = new SendMail();
                    mail.sendMail(projectNo, start?2:3);
                    // SVN権限ファイルを更新する。
                    for(int i = 0; i < 3; i++) {
                         // SVN権限ファイルの更新に成功した場合
                        if(FileOperator.accessReflash()) {
                            // プロジェクトの削除またはプロジェクトの修復に成功のフラグを設定する
                            startFlag = 0;
                            break;
                        }
                        // SVN権限ファイルの更新に失敗する場合。
                        else {
                            // プロジェクトの削除またはプロジェクトの修復に失敗する。
                            startFlag = 1;
                            continue;
                        }
                    }
                } else if(flag == 1){
                    SendMail mail = new SendMail();
                    mail.sendMail(projectNo, start?7:8);
                    // ロールバック。
                    con.rollback();
                    // プロジェクトの削除またはプロジェクトの修復に失敗する。
                    startFlag = 1;
                }else{
                    SendMail mail = new SendMail();
                    mail.sendMail(projectNo, start?12:13);
                    // ロールバック。
                    con.rollback();
                    con.setAutoCommit(true);
                    state.execute(sqlFail);
                    // プロジェクトの削除またはプロジェクトの修復に失敗する。
                    startFlag = 1;
                    return startFlag;
                }
            }
            // プロジェクトの更新に失敗した場合
            else {
                // 排他が発生した。
                startFlag = 2;
            }
            con.setAutoCommit(true);
            state.execute(sqlUnlock);
            // ログを出力する。
            log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "updateProject", "End");
            // 実行状態を戻る。
            return startFlag;
        }catch (Exception e) {
            // トランザクションが提出していない場合
            if(!con.getAutoCommit()) {
                con.rollback();
            }
            // AutoCommitにtrueを設定する。
            con.setAutoCommit(true);
            state.execute(sqlUnlock);
            // ログを出力する。
            log.printLog("ERROR", "SVNM0210Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updateProject", e.toString());
            // 異常をスロー。
            throw e;
        }
        finally {
            // Statementをクローズ。
            state.close();
            // 接続をクローズ。
            con.close();
        }
    }

    /**
     * プロジェクト最新のチェック。<P>
     * <b>処理概要:</b><br>
     * プロジェクト最新のチェック。
     * @param    projectNo    プロジェクト番号
     * @param    projectUpdateDate    プロジェクト更新時間
     * @return    boolean <br>
     *                   true  最新の情報である。<br>
     *                   false 最新の情報ではない。
     * @exception    Exception    異常
     */
    public boolean saisinCheck(String projectNo , String projectUpdateDate) throws Exception{
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "saisinCheck", "Start");
        // 検索条件を設定する。 
        ArrayList list = new ArrayList();
        // プロジェクト番号を設定する。
        list.add(projectNo);
        // プロジェクト更新日を設定する。
        list.add(projectUpdateDate);
        // sql文を取得する。
        String sql = FBSEDBHandler.getSql("SVNM0210S003",list);
        // FBSEDBHandlerオブジェクトを宣言する。
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // sql文を実行する。
        FBSEDataResultSet resultSelect = dbUtil.executeSelect(sql);
        // 検索結果が存在している場合
        if(resultSelect.getNext("table1")) {
            // ログを出力する。
            log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "saisinCheck", "End");
            return true;
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "saisinCheck", "End");
        return false;
    }
}