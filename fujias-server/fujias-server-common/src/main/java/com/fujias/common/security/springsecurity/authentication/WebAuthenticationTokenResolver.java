package com.fujias.common.security.springsecurity.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.fujias.common.util.RequestUtil;
import com.fujias.common.util.StringUtil;

/**
 * Ajax以外の場合、登録ユーザー情報の入力情報を取得するクラスです。
 * 
 * @author 陳強
 * 
 */
public class WebAuthenticationTokenResolver implements AuthenticationTokenResolver {

    /**
     * ログインユーザーに対する社員CDのキー
     */
    public static final String USERNAME = "username";
    /**
     * ログインユーザーに対するパスワードのキー
     */
    public static final String PASSWORD = "password";

    @Override
    public Authentication resolve(HttpServletRequest request) {

        if (RequestUtil.isAjaxRequest(request)) {
            return null;
        }

        if (StringUtil.isEmpty(request.getParameter(USERNAME))) {
            return null;
        }
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        return new CustomUsernamePasswordAuthenticationToken(username, password);
    }

}
