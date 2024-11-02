package com.fujias.common.security.springsecurity.authentication;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 入力ログイン情報を取得用Filterクラスです。
 * 
 * @author 陳強
 *
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private List<AuthenticationTokenResolver> tokenResolvers;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        for (AuthenticationTokenResolver tokenResolver : tokenResolvers) {
            Authentication authentication = tokenResolver.resolve(request);
            if (authentication != null) {
                return this.getAuthenticationManager().authenticate(authentication);
            }
        }
        throw new UnsupportedOperationException("No authentication token resolver found!");
    }

    /**
     * 情報取得クラスのリストを取得する
     * 
     * @return 情報取得クラスのリスト
     */
    public List<AuthenticationTokenResolver> getTokenResolvers() {
        return tokenResolvers;
    }

    /**
     * 情報取得クラスのリストを設定する
     * 
     * @param tokenResolvers 情報取得クラスのリスト
     */
    public void setTokenResolvers(List<AuthenticationTokenResolver> tokenResolvers) {
        this.tokenResolvers = tokenResolvers;
    }

}
