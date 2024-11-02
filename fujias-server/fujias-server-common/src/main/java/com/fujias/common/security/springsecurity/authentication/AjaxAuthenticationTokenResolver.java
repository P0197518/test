package com.fujias.common.security.springsecurity.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

import com.fujias.common.util.JsonUtil;
import com.fujias.common.util.RequestUtil;

/**
 * Ajaxの場合、登録ユーザー情報の入力情報を取得するクラスです。
 * 
 * @author 陳強
 */
public class AjaxAuthenticationTokenResolver implements AuthenticationTokenResolver {

    /**
     * ログインユーザーに対する社員CDのキー
     */
    public static final String USERNAME = "username";
    /**
     * ログインユーザーに対するパスワードのキー
     */
    public static final String PASSWORD = "password";
    /**
     * パスワード記憶
     */
    public static final String REMME = "rembMe";
    /**
     * ブラウザ
     */
    public static final String BROWSER = "browser";

    @Override
    public Authentication resolve(HttpServletRequest request) {

        if (!RequestUtil.isAjaxRequest(request)) {
            return null;
        }

        StringBuilder responseStrBuilder = new StringBuilder();

        BufferedReader streamReader;
        try {
            streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Session取得
        HttpSession session = request.getSession();
        Map<String, Object> paramData = JsonUtil.toHashMap(responseStrBuilder.toString());
        // 取得された情報をSessionに保存する
        session.setAttribute("browser", paramData.get(BROWSER));
        if (paramData.containsKey(USERNAME)) {
            String username = paramData.get(USERNAME).toString();
            String password = "";
            boolean remberMe = false;
            if (paramData.get(PASSWORD) != null) {
                password = paramData.get(PASSWORD).toString();
            }
            if (paramData.get(REMME) != null) {
                ArrayList<?> remberMeList = (ArrayList<?>) paramData.get(REMME);
                if (remberMeList.size() == 0) {
                    remberMe = false;
                } else {
                    remberMe = true;
                }
            }
            return new CustomUsernamePasswordAuthenticationToken(username, password, remberMe);
        }
        return null;
    }

}
