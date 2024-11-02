package com.fujias.common.security.springsecurity.access;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.fujias.common.constants.MessageCodes;

/**
 * 每次访问权限判断用拦截器
 * 
 * @author 陳強
 *
 */
public class CustomSecurityInterceptor extends FilterSecurityInterceptor implements Filter {

    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 构造函数
     * 
     * @param authenticationManager authenticationManager
     * @param accessDecisionManager accessDecisionManager
     * @param securityMetadataSource securityMetadataSource
     * @param accessDeniedHandler accessDeniedHandler
     */
    public CustomSecurityInterceptor(AuthenticationManager authenticationManager, CustomAccessDecisionManager accessDecisionManager,
                    FilterInvocationSecurityMetadataSource securityMetadataSource, AccessDeniedHandler accessDeniedHandler) {

        super.setAccessDecisionManager(accessDecisionManager);
        super.setSecurityMetadataSource(securityMetadataSource);
        this.accessDeniedHandler = accessDeniedHandler;
    }

    /**
     * 登陆后，每次访问资源都通过这个拦截器拦截
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                    throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(filterInvocation);
    }

    /**
     * 自定义拦截处理
     * 
     * @param fi FilterInvocation
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    public void invoke(FilterInvocation fi) throws IOException, ServletException {

        this.setRejectPublicInvocations(false);

        InterceptorStatusToken token = null;
        try {
            token = super.beforeInvocation(fi);
        } catch (AccessDeniedException ex) {
            ex.printStackTrace();
            accessDeniedHandler.handle(fi.getHttpRequest(), fi.getResponse(), ex);
            return;
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            accessDeniedHandler.handle(fi.getHttpRequest(), fi.getResponse(), new AccessDeniedException(MessageCodes.EC0091));
            return;
        }

        try {
            // 执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (AccessDeniedException ex) {
            ex.printStackTrace();
            accessDeniedHandler.handle(fi.getHttpRequest(), fi.getResponse(), new AccessDeniedException(MessageCodes.EC0091));
            return;
        } finally {
            super.afterInvocation(token, null);

        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
