package com.fbse.svnm.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.common.FBSELogHandler;
import com.fbse.common.FBSEXmlHandler;

/**
 * <HR>
 * SVNファイルの操作である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>SVN配置ファイルの操作</li>
 * <li>プロジェクトのバージョンライブラリーの操作</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 */
public class FileOperator {
    // ログ出力するオブジェク
    private static FBSELogHandler log;

    /**
     * SVN登録パスワードを取得する。
     * @param userId
     *            ユーザ名
     * @param passwd
     *            パスワード
     * @return String    SVN登録パスワード
     */
    public static String getFilePassword(String userId, String passwd) throws Exception {
        log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "FileOperator", String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "setUser", "Start");
        String basePath = Common.getAppPath(FileOperator.class);
        // XMLハンドラーを宣言
        FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                + "/SVNConfig.xml");
        // パスワードファイルを処理するツールのパスを取得
        String htPasswd = svnXml.xmlSelectNode("//File/htpasswd");
        Process process=Runtime.getRuntime().exec(htPasswd + " -nb " + userId + " " + passwd);
        if(process.waitFor()!=0){
            throw new Exception();
        }
        InputStream inputStream=process.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine().split(":")[1];
    }

    /**
     * ユーザと対応するパスワードをSVNのユーザファイルに設定する。
     * 
     * @param userId
     *            ユーザ名
     * @param passwd
     *            パスワード
     * @return true :操作成功 false:操作失敗
     */
    public static boolean setUser(String userId, String passwd) {
        try {
            log = Common.getLog();
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "setUser", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // パスワードファイルのパスを取得
            String strPass = svnXml.xmlSelectNode("//File/passwords");
            // パスワードファイルを宣言
            File fPass = new File(strPass);
            // パスワードファイルはない場合、新規作成する
            if (!fPass.getParentFile().exists()) {
                fPass.getParentFile().mkdirs();
            }
            if (!fPass.exists()) {
                fPass.createNewFile();
            }
            // パスワードファイルを処理するツールのパスを取得
            String htPasswd = svnXml.xmlSelectNode("//File/htpasswd");
            // パスワードファイルを扱う
            if (Runtime.getRuntime().exec(
                    htPasswd + " -bp " + strPass + " " + userId + " " + passwd)
                    .waitFor() != 0) {
                // 操作失敗
                return false;
            }
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "setUser", "End");
            // 操作成功
            return true;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "setUser", e.toString());
            // 操作失敗
            return false;
        }
    }

    /**
     * 対応するユーザ情報をSVNのユーザファイルから削除する。
     * 
     * @param userId
     *            ユーザ名
     * @return true :操作成功 false:操作失敗
     */
    public static boolean removeUser(String userId) {
        try {
            log = Common.getLog();
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "removeUser", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // パスワードファイルのパスを取得
            String strPass = svnXml.xmlSelectNode("//File/passwords");
            // パスワードファイルを宣言
            File fPass = new File(strPass);
            // パスワードファイルはない場合、新規作成する
            if (!fPass.exists()) {
                fPass.createNewFile();
            }
            // パスワードファイルを扱うツールのパスを取得
            String htPasswd = svnXml.xmlSelectNode("//File/htpasswd");
            // パスワードファイルを扱う
            if (Runtime.getRuntime().exec(
                    htPasswd + " -D " + strPass + " " + userId).waitFor() != 0) {
                // 操作失敗
                return false;
            }
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "removeUser", "End");
            // 操作成功
            return true;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "removeUser", e.toString());
            // 操作失敗
            return false;
        }
    }

    /**
     * access権限ファイルを再作成する。
     * 
     * @return true :操作成功 false:操作失敗
     */
    public static boolean accessReflash() {
        log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "FileOperator", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "accessReflash", "Start");
        // FBSEDBHandlerオブジェクトを宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // HashMapオブジェクトを宣言
        HashMap groupInfo = new HashMap();
        HashMap svnInfo = new HashMap();
        HashMap comment=new HashMap();
        // HashSetオブジェクトを宣言
        HashSet group = new HashSet();
        try {
            // sql文を取得
            String sql = FBSEDBHandler.getSql("SVNMCOMMONS001");
            // Sql文を実行
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            Object groupName = "";
            // PJ責任者とPJリーダを取得
            while (result.getNext("table1")) {
                groupName = result.getObject("PROJECTNO");
                if(groupInfo.containsKey(groupName)){
                 // PJ責任者とPJリーダをHashSetに設定
                    try{
                    group.add(result.getObject("SVNLOGINNAME"));
                    }catch(Exception e){}
                    continue;
                }
                group = new HashSet();
                // PJ責任者とPJリーダをHashSetに設定
                try{
                group.add(result.getObject("SVNLOGINNAME"));
                }catch(Exception e){}
                // パラメータを設定
                ArrayList para = new ArrayList();
                para.add(groupName);
                // Sql文を取得
                String sqlMember = FBSEDBHandler.getSql("SVNMCOMMONS002", para);
                // Sql文を実行
                FBSEDataResultSet resultMember = dbUtil
                        .executeSelect(sqlMember);
                // メンバーをHashSetに設定
                while (resultMember.getNext("table1")) {
                    group.add(resultMember.getObject("SVNLOGINNAME"));
                }
                groupInfo.put(groupName, group);
            }
            // Sql文を取得
            String sqlSVN = FBSEDBHandler.getSql("SVNMCOMMONS003");
            String sqlInfo=FBSEDBHandler.getSql("SVNMCOMMONS011");
            ArrayList sqlSVNInfo=new ArrayList();
            sqlSVNInfo.add(sqlSVN);
            sqlSVNInfo.add(sqlInfo);
            // Sql文を実行
            FBSEDataResultSet resultSVN = dbUtil.executeSelect(sqlSVNInfo);
            // SVNフォルダ名を取得
            while (resultSVN.getNext("table1")) {
                svnInfo.put(resultSVN.getObject("PROJECTNO"), resultSVN
                        .getObject("SVNFOLDERNAME"));
            }
            while (resultSVN.getNext("table2")) {
                comment.put(resultSVN.getObject("PROJECTNO"), resultSVN
                        .getObject("INFO"));
            }
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // accessファイルのパスを取得
            String access = svnXml.xmlSelectNode("//File/access");
            // ファイルを宣言
            File fAccess = new File(access);
            File dBackup = new File(fAccess.getParent() + "\\accessBackup");
            // バックアップパスはない場合、新規作成する
            if (!dBackup.exists()) {
                dBackup.mkdirs();
            }
            File backFile=new File(dBackup.getPath() + "\\access"
                    + Common.getCurrentTime("yyyyMMddHHmmss"));
            // accessファイルをバックアップ
            if (fAccess.exists()) {
                fAccess.renameTo(backFile);
            }
            try{
            // 新accessファイルを作成する
            fAccess.createNewFile();
            // OutputStreamWriterオブジェクトを宣言する
            OutputStreamWriter accessWriter = new OutputStreamWriter(
                    new FileOutputStream(fAccess), "UTF-8");
            // accessファイルにgroups情報を出力する
            accessWriter.write("[groups]\n");
            // ユーザ番号を取得
            String strWriteUser = svnXml.xmlSelectNode("//User/admin");
            String strReadUser = svnXml.xmlSelectNode("//User/qualityTeam");
            accessWriter.write("#管理者、読む権限と書く権限\n");
            accessWriter.write("admin=");
            accessWriter.write(strWriteUser+"\n\n");
            accessWriter.write("#品質チーム、読む権限だけ\n");
            accessWriter.write("qualityTeam=");
            accessWriter.write(strReadUser+"\n\n");
            // 配列objNameにgroupInfoプロジェクト名を保存する
            Object[] objName = groupInfo.keySet().toArray();
            // 繰り返してプロジェクトの要員を取得して、access権限ファイルに保存する
            for (int i = 0; i < objName.length; i++) {
                accessWriter.write(comment.get(objName[i]).toString().replaceAll("\\(.+\\)$", "\n").replaceAll("^#", "#開発チーム_"));
                accessWriter.write(svnInfo.get(objName[i].toString()) + "=");
                // ループでプロジェクトの人員を取得する
                Object[] objMember = ((HashSet) groupInfo.get(objName[i]))
                        .toArray();
                // ループでプロジェクト人員にaccess権限ファイルに保存する
                for (int j = 0; j < objMember.length; j++) {
                    if (j < objMember.length - 1) {
                        accessWriter.write(objMember[j].toString() + ",");
                    } else {
                        accessWriter.write(objMember[j].toString() + "\n\n");
                    }
                }
            }
            // ループで対応するプロジェクトの人員の権限を設定する
            accessWriter.write("#ディフォルトアクセスルール\n");
            accessWriter.write("#管理者は読む権限と書く権限がある、品質チームは読む権限だけがある\n");
            accessWriter.write("[/]\n");
            accessWriter.write("@admin = rw\n");
            accessWriter.write("@qualityTeam = r\n\n");
            // ループで対応するプロジェクトの人員の権限を設定する
            for (int i = 0; i < objName.length; i++) {
                accessWriter.write(comment.get(objName[i])+"\n");
                accessWriter.write("[" + svnInfo.get(objName[i]).toString()
                        + ":/]\n");
                accessWriter.write("@" + svnInfo.get(objName[i]) + " = rw\n\n");
            }
            accessWriter.close();
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "accessReflash", "End");
            // 操作成功
            return true;
            }catch(Exception ex){
                if (fAccess.exists()) {
                    fAccess.delete();
                }
                backFile.renameTo(fAccess);
                return false;
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "accessReflash", e.toString());
            // 操作失敗
            return false;
        }
    }

    /**
     * プロジェクトのバージョンライブラリーを作成する。
     * 
     * @param projectName
     *            プロジェクト名
     * @return true :操作成功 false:操作失敗
     */
    public static boolean projectCreate(String projectName) {
        log = Common.getLog();
        try {
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectCreate", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // バージョンライブラリーを置くパスを取得
            String library = svnXml.xmlSelectNode("//File/library");
            // ライブラリーパスを宣言
            File fLibrary = new File(library);
            // ライブラリーパスはない場合、新規作成する
            if (!fLibrary.exists()) {
                fLibrary.mkdirs();
            }
            // バージョンライブラリーを作成
            if (Runtime.getRuntime().exec(
                    "svnadmin create --fs-type bdb " + library + "\\" + projectName)
                    .waitFor() != 0) {
                // 操作失敗
                return false;
            }
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectCreate", "End");
            // 操作成功
            return true;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectCreate", e.toString());
            // 操作失敗
            return false;
        }
    }

    /**
     * プロジェクトのバージョンライブラリーをバックアップする。
     * 
     * @param projectName
     *            プロジェクト名前
     * @return 0 :操作成功 1:操作失敗 2:操作失敗、それに一時ファイルの削除は失敗
     */
    public static int projectBackup(String projectName) {
        log = Common.getLog();
        try {
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectBackup", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // バージョンライブラリーを置くパスを取得
            String library = svnXml.xmlSelectNode("//File/library");
            // バージョンライブラリーバックアップを置くパスを取得
            String backup = svnXml.xmlSelectNode("//File/backup");
            // バージョンライブラリーを宣言
            File fLibrary = new File(library + "\\" + projectName);
            // ライブラリーパスはない場合
            if (!fLibrary.exists()) {
                // 操作失敗
                return 1;
            }
            // バックアップパスを宣言
            File fBackup = new File(backup);
            // バックアップパスはない場合、新規作成する
            if (!fBackup.exists()) {
                fBackup.mkdirs();
            }
            String backupName = backup + "\\" + projectName + Common.getCurrentTime("yyyyMMddHHmmss");
            // バージョンライブラリーをバックアップ
            if (Runtime.getRuntime().exec(
                    "svnadmin hotcopy " + library + "\\" + projectName + " " + backupName
                            + " --clean-logs").waitFor() != 0) {
                File backupFile = new File(backupName);
                if(backupFile.exists()){
                    if(!delDir(backupFile)){
                        return 2;
                    }
                }
                // 操作失敗
                return 1;
            }
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectBackup", "End");
            // 操作成功
            return 0;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectBackup", e.toString());
            // 操作失敗
            return 1;
        }
    }

    /**
     * プロジェクトのバージョンライブラリを移動する。
     * 
     * @param projectName
     *            プロジェクト名前
     * @param flag
     *            true:転出 false:修復
     * @return 0 :操作成功 1:操作失敗 2:操作失敗、それに一時ファイルの削除は失敗
     */
    public static int projectRemove(String projectName, boolean flag) {
        log = Common.getLog();
        try {
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectRemove", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // バージョンライブラリーを置くパスを取得
            String library = svnXml.xmlSelectNode("//File/library");
            // 転出したバージョンライブラリーを置くパスを取得
            String remove = svnXml.xmlSelectNode("//File/remove");
            // バージョンライブラリーを宣言
            File fLibrary = new File(library + "\\" + projectName);
            File fRemove = new File(remove + "\\" + projectName);
            // ライブラリーパスはない場合
            if (flag && !fLibrary.exists() || !flag && !fRemove.exists()) {
                // 操作失敗
                return 1;
            }
            // 転出パスを宣言
            File dRemove = new File(remove);
            // 転出パスはない場合、新規作成する
            if (!dRemove.exists()) {
                dRemove.mkdirs();
            }
            if (flag) {
                if (fRemove.exists()){
                    return 1;
                }
                // プロジェクトのバージョンライブラリーを削除する
                if (Runtime.getRuntime().exec(
                        "svnadmin hotcopy " + library + "\\" + projectName + " "
                                + remove + "\\" + projectName + " --clean-logs")
                        .waitFor() == 0) {
                    if(!delDir(fLibrary)){
                        return 2;
                    }
                }
                else{
                    if(fRemove.exists()){
                        if(!delDir(fRemove)){
                            return 2;
                        }
                    }
                    return 1;
                }
            } else {
                if(fLibrary.exists()){
                    return 1;
                }
                // プロジェクトのバージョンライブラリーを修復する
                if (Runtime.getRuntime().exec(
                        "svnadmin hotcopy " + remove + "\\" + projectName + " "
                                + library + "\\" + projectName + " --clean-logs")
                        .waitFor() == 0) {
                    if(!delDir(fRemove)){
                        return 2;
                    }
                }
                else{
                    if(fLibrary.exists()){
                        if(!delDir(fLibrary)){
                            return 2;
                        }
                    }
                    return 1;
                }
            }
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectRemove", "End");
            // 操作成功
            return 0;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectRemove", e.toString());
            // 操作失敗
            return 1;
        }
    }

    /**
     * プロジェクトのバージョンライブラリーを削除する。
     * 
     * @param projectName
     *            プロジェクト名前
     * @param flag ture:削除
     *            false:修復
     * @return 0 :操作成功 1:操作失敗 2:操作失敗、それに一時ファイルの削除は失敗
     */
    public static int projectDelete(String projectName, boolean flag) {
        log = Common.getLog();
        try {
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectDelete", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // バージョンライブラリーを置くパスを取得
            String library = svnXml.xmlSelectNode("//File/library");
            // 削除したバージョンライブラリーを置くパスを取得
            String delete = svnXml.xmlSelectNode("//File/delete");
            // 転出したバージョンライブラリーを置くパスを取得
            String remove = svnXml.xmlSelectNode("//File/remove");
            // バージョンライブラリーを宣言
            File fLibrary = new File(library + "\\" + projectName);
            File fDelete = new File(delete + "\\" + projectName);
            File fRemove = new File(remove + "\\" + projectName);
            // ライブラリーパスはない場合
            if ((flag && !fLibrary.exists() && !fRemove.exists())
                    || (!flag && !fDelete.exists())) {
                // 操作失敗
                return 1;
            }
            // 削除パスを宣言
            File DDelete = new File(delete);
            // 削除パスはない場合、新規作成する
            if (!DDelete.exists()) {
                DDelete.mkdirs();
            }
            if (flag) {
                if(fDelete.exists()){
                    return 1;
                }
                // プロジェクトのバージョンライブラリーを削除する
                if (fLibrary.exists()) {
                    if (Runtime.getRuntime().exec(
                            "svnadmin hotcopy " + library + "\\" + projectName + " "
                                    + delete + "\\" + projectName + " --clean-logs")
                            .waitFor() == 0) {
                        if(!delDir(fLibrary)){
                            return 2;
                        }
                    }
                    else{
                        if(fDelete.exists()){
                            if(!delDir(fDelete)){
                                return 2;
                            }
                        }
                        return 1;
                    }
                } else {
                    if (Runtime.getRuntime().exec(
                            "svnadmin hotcopy " + remove + "\\" + projectName + " "
                                    + delete + "\\" + projectName + " --clean-logs")
                            .waitFor() == 0) {
                        if(!delDir(fRemove)){
                            return 2;
                        }
                    }else{
                        if(fDelete.exists()){
                            if(!delDir(fDelete)){
                                return 2;
                            }
                        }
                        return 1;
                    }
                }
            } else {
                // パラメータを設定
                ArrayList para=new ArrayList();
                para.add(projectName);
                // SQL文を取得
                String sql=FBSEDBHandler.getSql("SVNMCOMMONS009", para);
                // FBSEDBHandlerを宣言
                FBSEDBHandler dbUtil=new FBSEDBHandler();
                // SQL文を実行
                FBSEDataResultSet result=dbUtil.executeSelect(sql);
                String removeFlag=null;
                // removeFlagを取得
                while(result.getNext("table1")){
                    removeFlag=result.getObject("REMOVEFLG").toString();
                }
                if(removeFlag.equals("0")){
                    if(fLibrary.exists()){
                        return 1;
                    }
                    // プロジェクトのバージョンライブラリーを修復する
                    if (Runtime.getRuntime().exec(
                            "svnadmin hotcopy " + delete + "\\" + projectName + " "
                                    + library + "\\" + projectName + " --clean-logs")
                            .waitFor() == 0) {
                        if(!delDir(fDelete)){
                            return 2;
                        }
                    }else{
                        if(fLibrary.exists()){
                            if(!delDir(fLibrary)){
                                return 2;
                            }
                        }
                        return 1;
                    }
                }else{
                    if(fRemove.exists()){
                        return 1;
                    }
                    // プロジェクトのバージョンライブラリーを修復する
                    if (Runtime.getRuntime().exec(
                            "svnadmin hotcopy " + delete + "\\" + projectName + " "
                                    + remove + "\\" + projectName + " --clean-logs")
                            .waitFor() == 0) {
                        if(!delDir(fDelete)){
                            return 2;
                        }
                    }else{
                        if(fRemove.exists()){
                            if(!delDir(fRemove)){
                                return 2;
                            }
                        }
                        return 1;
                    }
                }
            }
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectDelete", "End");
            // 操作成功
            return 0;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectDelete", e.toString());
            // 操作失敗
            return 1;
        }
    }

    /**
     * バージョンライブラリ名の修正。
     * 
     * @param oldProjectName
     *            プロジェクト名
     * @param newProjectName
     *            新プロジェクト名
     * @return true :操作成功 false:操作失敗
     */
    public static boolean projectRename(String oldProjectName, String newProjectName) {
        log = Common.getLog();
        try {
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectRename", "Start");
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // バージョンライブラリーを置くパスを取得
            String library = svnXml.xmlSelectNode("//File/library");
            // 転出したバージョンライブラリーを置くパスを取得
            String remove = svnXml.xmlSelectNode("//File/remove");
            // バージョンライブラリーを宣言
            File fLibrary = new File(library + "\\" + oldProjectName);
            File fRemove = new File(remove + "\\" + oldProjectName);
            // ライブラリーパスはない場合
            if (!fLibrary.exists()) {
                if(!fRemove.exists()) {
                    // 操作失敗
                    return false;
                }
                // バージョンライブラリな名の修正
                fRemove.renameTo(new File(remove + "\\" + newProjectName));
                // ログ出力
                log.printLog("INFO", "FileOperator", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "projectRename", "End");
                // 操作成功
                return true;
            }
            // バージョンライブラリな名の修正
            fLibrary.renameTo(new File(library + "\\" + newProjectName));
            // ログ出力
            log.printLog("INFO", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectRename", "End");
            // 操作成功
            return true;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "FileOperator", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "projectRename", e.toString());
            // 操作失敗
            return false;
        }
    }

    /**
     * ディレクトリを削除
     * 
     * @param dir
     *            削除したいディレクトリ
     */
    private static boolean delDir(File dir) {
        // 該当フォルダからすべてのファイルを取得する
        File files[] = dir.listFiles();
        // ループでファイルを削除する
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory() && files[i].listFiles().length != 0) {
                if(!delDir(files[i])){
                    return false;
                }
            }
            else if(!files[i].delete()){
                return false;
            }
        }
        // 該当フォルダを削除する
        if(dir.exists()){
            return dir.delete();
        }
        return true;
    }
}
