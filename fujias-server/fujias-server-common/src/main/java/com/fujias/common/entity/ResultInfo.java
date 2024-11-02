package com.fujias.common.entity;

import java.util.ArrayList;
import java.util.List;

import com.fujias.common.constants.StateTypes;

/**
 * 請求状態基本情報の戻り値定義クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public class ResultInfo {
    private StateTypes status;
    private String text;
    private String[] args;

    private List<CheckMessage> checkMessages;

    /**
     * textを取得する。
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * textを設定する。
     * 
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * checkMessagesを取得する。
     * 
     * @return the checkMessages
     */
    public List<CheckMessage> getCheckMessages() {
        return checkMessages;
    }

    /**
     * checkMessagesを設定する。
     * 
     * @param checkMessages the checkMessages to set
     */
    public void setCheckMessages(List<CheckMessage> checkMessages) {
        this.checkMessages = checkMessages;
    }

    /**
     * 设置checkmessage
     * 
     * @param checkMessages checkMessages
     */
    public void setCheckMessage(CheckMessage checkMessages) {
        List<CheckMessage> messages = new ArrayList<CheckMessage>();
        messages.add(checkMessages);
        this.checkMessages = messages;
    }

    /**
     * argsを取得する。
     * 
     * @return the args
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * argsを設定する。
     * 
     * @param args the args to set
     */
    public void setArgs(String[] args) {
        this.args = args;
    }

    public StateTypes getStatus() {
        return status;
    }

    public void setStatus(StateTypes status) {
        this.status = status;
    }

}
