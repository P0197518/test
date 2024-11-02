package com.fujias.common.security.springsecurity.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * ログインユーザー情報を取得するクラスです。
 * 
 * @author 陳強
 */
public interface AuthenticationTokenResolver {

    /**
     * ログインユーザー情報の取得を行う。
     * 
     * @param request request
     * @return ログインユーザー情報
     */
    Authentication resolve(HttpServletRequest request);

}
