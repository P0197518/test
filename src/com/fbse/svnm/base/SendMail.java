package com.fbse.svnm.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.common.FBSELogHandler;
import com.fbse.common.FBSEXmlHandler;

public class SendMail {

    // 送信者のメールアドレス
    private String sender = "";

    // 送信者のメールのパスワード
    private String password = "";

    // メールサーバー（smtp）
    private String smtpHost = "";

    public SendMail() {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "SendMail", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "SendMail", "Start");
        String basePath = Common.getAppPath(FileOperator.class);
        // XMLハンドラーを宣言
        FBSEXmlHandler svnXml;
        try {
            svnXml = new FBSEXmlHandler(basePath + "/SVNConfig.xml");
            // 送信者のメールアドレスを取得
            sender = svnXml.xmlSelectNode("//Mail/emaliSender");
            // 送信者のメールのパスワード
            password = svnXml.xmlSelectNode("//Mail/password");
            // メールサーバー（smtp）
            smtpHost = svnXml.xmlSelectNode("//Mail/server");
            // ログ出力
            log.printLog("INFO", "SendMail", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "SendMail", "End");
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SendMail", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "SendMail", e
                    .toString());
        }
    }

    /**
     * smtpを利用して、メールを送信する
     * 
     * @param receiver
     *            宛先
     * @param subject
     *            件名
     * @param content
     *            メールの内容
     * @throws MessagingException
     *             メールの送信失敗
     */
    private void smtp(String receiver, String subject, StringBuffer content)
            throws MessagingException {
        if (smtpHost == null || smtpHost.trim().equals(""))
            throw new MessagingException("smtpHost   not   found ");
        if (sender == null || sender.trim().equals(""))
            throw new MessagingException("user   not   found ");
        if (password == null || password.trim().equals(""))
            throw new MessagingException("password   not   found ");
        Properties properties = new Properties();
        // smtpホストを設定
        properties.put("mail.smtp.host", smtpHost);
        // smtp身分のチェックを使用
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });
        // MIMEメールオブジェクトを宣言
        MimeMessage mimeMsg = new MimeMessage(session);
        // 送信者のメールアドレスを設定
        mimeMsg.setFrom(new InternetAddress(sender));
        // 宛先を設定
        mimeMsg.setRecipients(Message.RecipientType.TO, receiver);
        // 件名を設定
        mimeMsg.setSubject(subject, "UTF-8");
        // メールの内容
        MimeBodyPart part = new MimeBodyPart();
        part.setText(content.toString(), "UTF-8 ");
        // メールのフォーマットをhtmlで設定
        part.setContent(content.toString(), "text/html;charset=UTF-8 ");
        Multipart multipart = new MimeMultipart();
        // メールの内容をMultipartに加える
        multipart.addBodyPart(part);
        // Multipartをインフォメーションボディーに加える
        mimeMsg.setContent(multipart);
        // 送信日付を設定
        mimeMsg.setSentDate(new Date());
        // 送信する
        Transport.send(mimeMsg);
    }

    /**
     * 送信する
     * 
     * @param mailAddress
     *            宛先
     * @param subject
     *            件名
     * @param content
     *            メールの内容
     * @return Boolean true：送信成功 false：送信失敗
     */
    private boolean sendMails(Object[] mailAddress, String subject,
            StringBuffer content) {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "SendMail", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "sendMails", "Start");
        if (mailAddress == null || subject == null || content == null
                || subject.trim().equals("") || content.equals("")) {
            return false;
        }
        // 繰り返して送信する
        for (int i = 0; i < mailAddress.length; i++) {
            try {
                this.smtp(mailAddress[i].toString(), subject, content);
            } catch (Exception e) {
                // ログ出力
                log.printLog("ERROR", "SendMail", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "sendMails", e.toString());
            }
        }
        // ログ出力
        log.printLog("INFO", "SendMail", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "sendMails", "End");
        return true;
    }

    /**
     * 送信する
     * 
     * @param proId
     *            プロジェクトID
     * @param type
     *            0:プロジェクト新規と更新成功 1:プロジェクトバックアップ成功 2:プロジェクト削除成功 3:プロジェクト修復成功 4:プロジェクト移動成功
     *            5:プロジェクト新規と更新失敗 6:プロジェクトバックアップ失敗 7:プロジェクト削除失敗 8:プロジェクト修復失敗 9:プロジェクト移動失敗
     *            10:プロジェクト新規と更新失敗、それにファイルを削除失敗 11:プロジェクトバックアップ失敗、それにファイルを削除失敗 
     *            12:プロジェクト削除失敗、それにファイルを削除失敗 13:プロジェクト修復失敗、それにファイルを削除失敗 14:プロジェクト移動失敗、それにファイルを削除失敗
     * @return true:送信成功 false:送信失敗
     */
    public boolean sendMail(String proId, int type) {
        FBSELogHandler log = Common.getLog();
        // ログ出力
        log.printLog("INFO", "SendMail", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "sendMail", "Start");
        // パラメータを設定
        ArrayList para = new ArrayList();
        para.add(proId);
        try {
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            String[] infra= svnXml.xmlSelectNode("//Mail/infra").split(",");
            String strContent=null;
            // SQL文を取得
            ArrayList sql = new ArrayList();
            String title=null;
            // FBSEDBHandlerオブジェクトを宣言
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // HashSetオブジェクトを宣言
            HashSet hsMail = new HashSet();
            switch(type) {
                case 0:
                    strContent = svnXml.xmlSelectNode("//Mail/content0");
                    String sqlLeader = FBSEDBHandler.getSql("SVNMCOMMONS006", para);
                    String sqlMember = FBSEDBHandler.getSql("SVNMCOMMONS007", para);
                    sql.add(sqlLeader);
                    sql.add(sqlMember);
                    title=svnXml.xmlSelectNode("//Mail/title0");
                    break;
                case 1:
                case 6:
                case 11:
                    strContent = svnXml.xmlSelectNode("//Mail/content1");
                    title=svnXml.xmlSelectNode("//Mail/title1");
                    break;
                default:
                    strContent = svnXml.xmlSelectNode("//Mail/content" + type);
                    String sqlOperator = FBSEDBHandler.getSql("SVNMCOMMONS013", para);
                    FBSEDataResultSet resultOperator = dbUtil.executeSelect(sqlOperator);
                    if(resultOperator.getNext("table1")){
                        hsMail.add(resultOperator.getObject("MAIL"));
                    }
                    title=svnXml.xmlSelectNode("//Mail/title" + type);
            }
            String sqlInfo = FBSEDBHandler.getSql("SVNMCOMMONS012", para);
            sql.add(sqlInfo);
            // SQL文を実行
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            String tableName="table1";
            if (type == 0) {
                // PJ責任者とPJリーダのメールアドレスをHashSetに設定
                while (result.getNext("table1")) {
                    hsMail.add(result.getObject("MAIL"));
                }
                // メンバーのメールアドレスをHashSetに設定
                while (result.getNext("table2")) {
                    hsMail.add(result.getObject("MAIL"));
                }
                tableName="table3";
            }
            if(type <= 1 || type == 6 || type == 11) {
                for(int i=0;i<infra.length;i++){
                    hsMail.add(infra[i]);
                }
            }
            // メールの内容を設定
            StringBuffer content = new StringBuffer();
            // メールの内容を取得する
            strContent = strContent.replaceAll("\\n", "<br>");
            strContent = strContent.replaceAll("<br>[ \\t]*\\*", "<br>")
                    .replaceAll("^<br>", "");
            strContent = strContent.replaceAll(" ", "&nbsp;").replace("\t",
                    "&nbsp;&nbsp;&nbsp;&nbsp;");
            // メールの内容の設定
            while (result.getNext(tableName)) {
                strContent = strContent.replace("@projectName", result
                        .getObject("PROJECTNAME").toString());
                strContent = strContent.replace("@pjStartDate", result
                        .getObject("PJSTARTDATE").toString());
                strContent = strContent.replace("@pjEndDate", result.getObject(
                        "PJENDDATE").toString());
                if (result.getObject("PJBACKUPTIME").toString().equals("0")) {
                    strContent = strContent.replace("@pjBackupTime", "");
                } else {
                    strContent = strContent.replace("@pjBackupTime", result
                            .getObject("PJBACKUPTIME").toString());
                }
                strContent = strContent.replace("@pjRemoveDate", result
                        .getObject("PJREMOVEDATE").toString());
                strContent = strContent.replace("@svnFolderName", result
                        .getObject("SVNFOLDERNAME").toString());
                try {
                    strContent = strContent.replace("@pjMaster", result
                            .getObject("PJMASTER").toString());
                } catch (Exception e) {
                    strContent = strContent.replace("@pjMaster", "");
                }
                try {
                    strContent = strContent.replace("@pjLeader", result
                            .getObject("PJLEADER").toString());
                } catch (Exception e) {
                    strContent = strContent.replace("@pjLeader", "");
                }
            }
            content = new StringBuffer(strContent);
            // メールアドレスを配列に変更
            Object[] mailAddress = hsMail.toArray();
            // 送信する
            boolean flag = sendMails(mailAddress, title, content);
            // ログ出力
            log.printLog("INFO", "SendMail", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "sendMail", "End");
            // 送信結果を戻す
            return flag;
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SendMail", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "sendMail", e
                    .toString());
            // 送信失敗
            return false;
        }
    }
}
