package com.fujias.common.exception;

import java.util.List;

import org.springframework.security.core.AuthenticationException;

import com.fujias.common.entity.CheckMessage;

/**
 * 全体異常の共通処理クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public class CheckErrorException extends AuthenticationException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private List<CheckMessage> checkMessages;

    /**
     * 构造函数
     * 
     * @param msg 错误内容
     */
    public CheckErrorException(String msg) {
        super(msg);
    }

    /**
     * 构造函数
     * 
     * @param msg 错误内容
     * @param checkMessages 错误列表
     */
    public CheckErrorException(String msg, List<CheckMessage> checkMessages) {
        super(msg);
        this.checkMessages = checkMessages;
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

}
