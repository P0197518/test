package com.fujias.common.component;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.fujias.common.entity.NoticeInfoForm;

/**
 * メール送信Componentクラスです。
 * 
 * @author 陳強
 *
 */
@Component
public class MailComponent {

    protected static final Logger LORRER = LoggerFactory.getLogger(MailComponent.class);

    @Value("${spring.mail.username}")
    private String senderAddress;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * メール送信を行う。
     * 
     * @param receiver receiver
     * @param title title
     * @param content content
     * 
     * @return 送信成功フラグ
     */
    public boolean sendHtmlEmail(String receiver, String title, String content) {
        NoticeInfoForm item = new NoticeInfoForm();
        item.setMail(receiver);
        item.setTitle(title);
        item.setContent(content);
        List<NoticeInfoForm> receiverArr = new ArrayList<NoticeInfoForm>();
        receiverArr.add(item);
        return sendHtmlEmail(receiverArr, false);
    }

    /**
     * メール送信を行う。
     * 
     * @param receivers receivers
     * @param htmlFlag htmlFlag
     * 
     * @return 送信成功フラグ
     */
    public boolean sendHtmlEmail(List<NoticeInfoForm> receivers, boolean htmlFlag) {

        long startTimestamp = System.currentTimeMillis();
        LORRER.info("Start send email ...");

        try {
            MimeMessage[] messages = new MimeMessage[receivers.size()];
            for (int i = 0; i < receivers.size(); i++) {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message);

                messageHelper.setFrom(senderAddress);
                messageHelper.setTo(receivers.get(i).getMail());

                messageHelper.setSubject(receivers.get(i).getTitle());
                messageHelper.setText(receivers.get(i).getContent(), htmlFlag);
                messages[i] = message;
            }

            mailSender.send(messages);
            LORRER.info("Send email success, cost {} million seconds", System.currentTimeMillis() - startTimestamp);
        } catch (Exception e) {
            LORRER.error("Send email failed, error message is {} \n", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * メール送信を行う。
     * 
     * @param receiver 宛先
     * @param carbonCopy CC者
     * @param subject タイトル
     * @param content 送信内容
     * @return 送信成功フラグ
     */
    public boolean sendHtmlEmail(List<String> receiver, List<String> carbonCopy, String subject, String content) {
        return sendHtmlEmail(receiver, carbonCopy, subject, content, false);
    }

    /**
     * メール送信を行う。
     * 
     * @param receiver 宛先
     * @param carbonCopy CC者
     * @param subject タイトル
     * @param content 送信内容
     * @param isHtml Html内容フラグ
     * @return 送信成功フラグ
     */
    public boolean sendHtmlEmail(List<String> receiver, List<String> carbonCopy, String subject, String content, boolean isHtml) {
        long startTimestamp = System.currentTimeMillis();
        LORRER.info("Start send email ...");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            String[] receiverArr = new String[receiver.size()];
            receiverArr = receiver.toArray(receiverArr);

            messageHelper.setFrom(senderAddress);
            messageHelper.setTo(receiverArr);
            if (carbonCopy != null && !carbonCopy.isEmpty()) {
                String[] carbonCopyArr = new String[carbonCopy.size()];
                carbonCopyArr = carbonCopy.toArray(carbonCopyArr);
                messageHelper.setCc(carbonCopyArr);
            }

            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);

            mailSender.send(message);
            LORRER.info("Send email success, cost {} million seconds", System.currentTimeMillis() - startTimestamp);
        } catch (Exception e) {
            LORRER.error("Send email failed, error message is {} \n", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
