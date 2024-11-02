package com.fujias.common.security.springsecurity.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fujias.common.constants.MessageCodes;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.util.TokenAuthenticationUtil;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * token验证用过滤器
 *
 * @author 陳強
 *
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private AccessDeniedHandler tokenErrorHandler;

    /**
     * 构造函数
     * 
     * @param authenticationManager authenticationManager
     * @param tokenErrorHandler tokenErrorHandler
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AccessDeniedHandler tokenErrorHandler) {
        super(authenticationManager);
        this.tokenErrorHandler = tokenErrorHandler;
    }

    /**
     * 检验客户端请求头中的token, 如果存在并合法,就把token中的信息封装到 Authentication
     *
     * @param request request
     * @param response response
     * @param chain chain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                    throws IOException, ServletException {

        String token = request.getHeader(TokenAuthenticationUtil.HEADER_STRING);

        Authentication authentication = null;
        try {
            authentication = TokenAuthenticationUtil.getAuthentication(token);
        } catch (ExpiredJwtException ex) {
            tokenErrorHandler.handle(request, response, new AccessDeniedException(MessageCodes.EC0091));
            return;
        } catch (Exception ex) {
            throw ex;
        }

        // 判断是否有token
        if (authentication == null) {
            // 放行
            chain.doFilter(request, response);
            // tokenErrorHandler.handle(request, response, new AccessDeniedException("身份信息验证失败，请重新登录验证。"));
            return;
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(authentication.getPrincipal().toString());

        SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(loginUser, null, authentication.getAuthorities()));
        // 放行
        chain.doFilter(request, response);
    }

}
