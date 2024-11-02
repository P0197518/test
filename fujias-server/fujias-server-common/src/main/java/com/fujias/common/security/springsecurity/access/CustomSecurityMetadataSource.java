package com.fujias.common.security.springsecurity.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fujias.common.form.CommonResourceRoles;

/**
 * 获取权限数据
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 根据url获取对应的权限数据
     * 
     * @param requestData 中包含用户请求的request 信息
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object requestData) throws IllegalArgumentException {

        HashMap<String, CommonResourceRoles> map = RoleResourceCache.getAll();

        HashMap<String, CommonResourceRoles> whiteMap = RoleResourceCache.getAllWhite();

        HttpServletRequest request = ((FilterInvocation) requestData).getHttpRequest();

        // String url = request.getServletPath();

        Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
        //
        // if (request.getPathInfo() != null) {
        // url += request.getPathInfo();
        // }
        // if (whiteMap.containsKey(url)) {
        // ConfigAttribute roleConfig = new SecurityConfig(CommonConstants.ALL_ROLE);
        // list.add(roleConfig);
        // return list;
        // }

        for (Map.Entry<String, CommonResourceRoles> entry : whiteMap.entrySet()) {
            String resUrl = entry.getKey();
            if (resUrl == null) {
                continue;
            }
            String setUrl = resUrl;
            if (!setUrl.startsWith("/")) {
                setUrl = "/" + setUrl;
            }

            AntPathRequestMatcher matcher = new AntPathRequestMatcher(setUrl);
            if (matcher.matches(request)) {

                if (whiteMap.get(resUrl) == null || whiteMap.get(resUrl).getRoles() == null) {
                    return null;
                }

                for (String role : whiteMap.get(resUrl).getRoles()) {
                    ConfigAttribute roleConfig = new SecurityConfig(role);
                    list.add(roleConfig);
                }
                return list;
            }
        }

        for (Map.Entry<String, CommonResourceRoles> entry : map.entrySet()) {
            String resUrl = entry.getKey();
            if (resUrl == null) {
                continue;
            }

            String setUrl = resUrl;
            if (!setUrl.startsWith("/")) {
                setUrl = "/" + setUrl;
            }

            AntPathRequestMatcher matcher = new AntPathRequestMatcher(setUrl);
            if (matcher.matches(request)) {

                if (map.get(resUrl) == null || map.get(resUrl).getRoles() == null) {
                    return null;
                }

                for (String role : map.get(resUrl).getRoles()) {
                    ConfigAttribute roleConfig = new SecurityConfig(role);
                    list.add(roleConfig);
                }
                return list;
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
