package com.fujias.common.entity;

/**
 * 消息发送用
 * 
 * @author chenqiang
 *
 */
public class NoticeInfoForm {
    private String mail;

    private String title;

    private String content;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
