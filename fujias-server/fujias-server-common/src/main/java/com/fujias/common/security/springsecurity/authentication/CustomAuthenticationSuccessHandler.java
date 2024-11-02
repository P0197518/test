package com.fujias.common.security.springsecurity.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.entity.ResultWithTokenInfo;
import com.fujias.common.form.UserInfoBackForm;
import com.fujias.common.util.JsonUtil;
import com.fujias.common.util.TokenAuthenticationUtil;

/**
 * ログイン成功の場合の処理クラスです。
 * 
 * @author 陳強
 *
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {

        // Ajax請求の場合、状態とエラー情報を返却する
        response.setContentType("application/json;charset=utf-8");

        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority authorityItem : authentication.getAuthorities()) {
            roles.add(authorityItem.getAuthority());
        }

        ResultWithTokenInfo resultInfo = new ResultWithTokenInfo();
        resultInfo.setStatus(StateTypes.SUCCESS);

        UserInfoBackForm backInfo = new UserInfoBackForm();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        backInfo.setName(loginUser.getName());
        resultInfo.setText(JsonUtil.toJson(backInfo));

        String tokenKey = TokenAuthenticationUtil.getAuthenticatiotoToken(authentication);

        resultInfo.setToken(tokenKey);

        PrintWriter out = response.getWriter();
        out.write(JsonUtil.toJson(resultInfo));
        out.flush();
        out.close();
    }

}
