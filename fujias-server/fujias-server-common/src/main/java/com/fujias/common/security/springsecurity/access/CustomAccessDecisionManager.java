package com.fujias.common.security.springsecurity.access;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.fujias.common.constants.CommonConstants;
import com.fujias.common.constants.MessageCodes;

/**
 * 访问时用户权限判断管理器
 * 
 * @author 陳強
 *
 */
@Service("roleAccessDecisionManager")
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /**
     * 决策方法： 如果方法执行完毕没有抛出异常,则说明可以放行, 否则抛出异常 AccessDeniedException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
                    throws AccessDeniedException, InsufficientAuthenticationException {
        // 用户权限列表
        Collection<? extends GrantedAuthority> myRoles = authentication.getAuthorities();

        // 判断访问url是否没有限制
        for (ConfigAttribute urlRoles : configAttributes) {
            if (CommonConstants.ALL_ROLE.equals(urlRoles.getAttribute())) {
                // 说明此URL地址符合权限,可以放行
                return;
            }
        }

        // 判断自己是否拥有访问权限
        for (GrantedAuthority myRole : myRoles) {
            for (ConfigAttribute urlRoles : configAttributes) {
                if (myRole.getAuthority().equals(urlRoles.getAttribute())) {
                    // 说明此URL地址符合权限,可以放行
                    return;
                }
            }
        }

        throw new AccessDeniedException(MessageCodes.EC0091);
    }

    /**
     * 返回true表示支持
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 返回true表示支持
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
