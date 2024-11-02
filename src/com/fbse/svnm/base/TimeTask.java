package com.fbse.svnm.base;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.common.FBSEDateHandler;
import com.fbse.common.FBSELogHandler;

/**
 * <HR>
 * tomcat起動時メソッド実施。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクトのバックアップ</li>
 * <li>プロジェクトの移動</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 */
public class TimeTask extends HttpServlet implements Runnable {
    // メソッドの実行
    static {
        new Thread(new TimeTask()).start();
    }

    public void run() {
        FBSELogHandler log = Common.getLog();
        boolean flag = false;
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "run", "Start");
        if (!flag) {
            flag = true;
            startCheck();
            try {
                String backTime=FBSEDateHandler.addDay(Common.getCurrentTime("yyyyMMdd"), 1);
                long sleepTime=FBSEDateHandler.toTimeMinute(backTime)-new Date().getTime();
                Thread.sleep(sleepTime);
            } catch (Exception e) {
            }
        }
        while (flag) {
            try {
                String backTime=FBSEDateHandler.addDay(Common.getCurrentTime("yyyyMMdd"), 1);
                long sleepTime=FBSEDateHandler.toTimeMinute(backTime)-new Date().getTime();
                if (sleepTime < 72000000) {
                    Thread.sleep(sleepTime);
                }
                // バックアップチェック
                backupCheck();
                // 転出チェック
                removeCheck();
            } catch (Exception e) {
                // ログ出力
                log.printLog("ERROR", "TimeTask", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "run", e.toString());
            } finally {
                try {
                    Thread.sleep(86400000);
                }
                catch (InterruptedException e) {
                    // ログ出力
                    log.printLog("ERROR", "TimeTask", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "run", e.toString());
                }
            }
        }
    }

    /**
     * tomcat起動する時のチェック
     * 
     * @throws Exception
     * 
     */
    private void startCheck() {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "startCheck", "Start");
        boolean flag = false;
        try {
            // FBSEDBHandlerオブジェクトを宣言
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // Sql文を取得
            String sql = FBSEDBHandler.getSql("SVNMCOMMONS005");
            // Sql文を実行
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            // プロジェクトの転出をチェック
            while (result.getNext("table1")) {
                // 転出時間を取得
                String removeDate = result.getObject("PJREMOVEDATE").toString();
                // プロジェクトの終わる時間を取得
                String endDate = result.getObject("PJENDDATE").toString();
                // 転出をチェック
                if (removeDate.compareTo(Common.getCurrentTime("yyyy/MM/dd"))<=0
                        && result.getObject("REMOVEFLG").equals("0")) {
                    // プロジェクトを転出
                    int removeFlag = FileOperator.projectRemove(result.getObject("SVNFOLDERNAME")
                            .toString(), true);
                    if(removeFlag==1){
                        SendMail mail = new SendMail();
                        mail.sendMail(result.getObject("PROJECTNO").toString(), 9);
                        continue;
                    }else if(removeFlag==2){
                        SendMail mail = new SendMail();
                        mail.sendMail(result.getObject("PROJECTNO").toString(), 14);
                        continue;
                    }
                    // パラメータを設定
                    ArrayList para = new ArrayList();
                    para.add("1");
                    para.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
                    para.add(result.getObject("PROJECTNO"));
                    // Sql文を取得
                    String sqlRemoveProject = FBSEDBHandler.getSql(
                            "SVNMCOMMONU003", para);
                    // Sql文を実行
                    dbUtil.executeUpdate(sqlRemoveProject);
                } 
                // プロジェクトの終わりをチェック
                if (endDate.compareTo(Common.getCurrentTime("yyyy/MM/dd"))<=0) {
                    // パラメータを設定
                    ArrayList para = new ArrayList();
                    para.add("1");
                    para.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
                    para.add(result.getObject("PROJECTNO"));
                    // Sql文を取得
                    String sqlRemove = FBSEDBHandler.getSql("SVNMCOMMONU002",
                            para);
                    // Sql文を実行
                    dbUtil.executeUpdate(sqlRemove);
                    // accessファイルを再作成flagを設定
                    flag = true;
                }
            }
            if(flag){
                // accessファイルを再作成
                FileOperator.accessReflash();
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "TimeTask", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "startCheck", e.toString());
        }
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "startCheck", "End");
    }

    /**
     * プロジェクトのバックアップをチェック
     * 
     * @throws Exception
     * 
     */
    private void backupCheck() throws Exception {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "backupCheck", "Start");        
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // Sql文を取得
        String sql = FBSEDBHandler.getSql("SVNMCOMMONS004");
        // Sql文を実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // プロジェクトのバックアップをチェック
        while (result.getNext("table1")) {
            // バックアップ時間間隔を取得
            String strBackupTime = result.getObject("PJBACKUPTIME").toString();
            int backupTime = Integer.parseInt(strBackupTime);
            if(backupTime==0){
                continue;
            }
            // 先回のバックアップ時間を取得
            String lastBackTime = result.getObject("PJLASTBACKUPTIME")
                    .toString();
            // 次回のバックアップ時間を取得
            String backupDate = FBSEDateHandler
                    .addDay(lastBackTime, backupTime);
            // バックアップチェック
            if (FBSEDateHandler.isLessEquals(backupDate, Common
                    .getCurrentTime("yyyyMMddHHmmssSSS"))) {
                // プロジェクトをバックアップ
                int backupFlag=FileOperator.projectBackup(result.getObject("SVNFOLDERNAME")
                        .toString());
                if( backupFlag==1){
                    SendMail mail = new SendMail();
                    mail.sendMail(result.getObject("PROJECTNO").toString(), 6);
                    continue;
                } else if(backupFlag ==2){
                    SendMail mail = new SendMail();
                    mail.sendMail(result.getObject("PROJECTNO").toString(), 11);
                    continue;
                }
                // パラメータを設定
                ArrayList para = new ArrayList();
                para.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
                para.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
                para.add(result.getObject("PROJECTNO"));
                String sqlBackup = FBSEDBHandler.getSql("SVNMCOMMONU001", para);
                // Sql文を実行
                dbUtil.executeUpdate(sqlBackup);
                SendMail mail=new SendMail();
                mail.sendMail(result.getObject("PROJECTNO").toString(), 1);
            }
        }
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "backupCheck", "End");
    }

    /**
     * プロジェクトの転出をチェック
     * 
     * @throws Exception
     * 
     */
    private void removeCheck() throws Exception {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "removeCheck", "Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // Sql文を取得
        String sql = FBSEDBHandler.getSql("SVNMCOMMONS005");
        // Sql文を実行
        FBSEDataResultSet result = dbUtil.executeSelect(sql);
        // プロジェクトの転出をチェック
        while (result.getNext("table1")) {
            // 転出時間を取得
            String removeDate = result.getObject("PJREMOVEDATE").toString();
            // プロジェクトの終わる時間を取得
            String endDate = result.getObject("PJENDDATE").toString();
            // 転出をチェック
            if (removeDate.equals(Common.getCurrentTime("yyyy/MM/dd"))
                    && result.getObject("REMOVEFLG").equals("0")) {
                // プロジェクトを転出
                int removeFlag=FileOperator.projectRemove(result.getObject("SVNFOLDERNAME")
                        .toString(), true);
                if(removeFlag==1){
                    SendMail mail = new SendMail();
                    mail.sendMail(result.getObject("PROJECTNO").toString(), 9);
                    continue;
                }else if(removeFlag==2){
                    SendMail mail = new SendMail();
                    mail.sendMail(result.getObject("PROJECTNO").toString(), 14);
                    continue;
                }
                // パラメータを設定
                ArrayList para = new ArrayList();
                para.add("1");
                para.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
                para.add(result.getObject("PROJECTNO"));
                // Sql文を取得
                String sqlRemoveProject = FBSEDBHandler.getSql(
                        "SVNMCOMMONU003", para);
                // Sql文を実行
                dbUtil.executeUpdate(sqlRemoveProject);
            }
            // プロジェクトの終わりをチェック
            if (endDate.equals(Common.getCurrentTime("yyyy/MM/dd"))) {
                // パラメータを設定
                ArrayList para = new ArrayList();
                para.add("1");
                para.add(Common.getCurrentTime("yyyyMMddHHmmssSSS"));
                para.add(result.getObject("PROJECTNO"));
                // Sql文を取得
                String sqlRemove = FBSEDBHandler.getSql("SVNMCOMMONU002", para);
                // Sql文を実行
                dbUtil.executeUpdate(sqlRemove);
                // accessファイルを再作成
                FileOperator.accessReflash();
            }
        }
        // ログ出力
        log.printLog("INFO", "TimeTask", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "removeCheck", "End");
    }
}
