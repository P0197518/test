package com.fujias.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户不存在异常
 * 
 * @author 陈强 on 2017/9/13.
 */
public class UsernameIsExitedException extends AuthenticationException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造函数
     * 
     * @param msg 错误信息
     */
    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    /**
     * 构造函数
     * 
     * @param msg 错误信息
     * @param t 异常
     */
    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}
