package com.fujias.common.security.springsecurity.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 用户信息token
 * 
 * @author 陳強
 *
 */
public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * remberMe
     */
    private boolean remberMe;

    /**
     * 构造函数
     * 
     * @param principal principal
     * @param credentials credentials
     */
    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    /**
     * 构造函数
     * 
     * @param principal principal
     * @param credentials credentials
     * @param remberMe remberMe
     */
    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, boolean remberMe) {
        super(principal, credentials);
        this.remberMe = remberMe;
    }

    /**
     * remberMeを取得する。
     * 
     * @return the remberMe
     */
    public boolean isRemberMe() {
        return remberMe;
    }

    /**
     * remberMeを設定する。
     * 
     * @param remberMe the remberMe to set
     */
    public void setRemberMe(boolean remberMe) {
        this.remberMe = remberMe;
    }

}
