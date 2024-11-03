package cn.edu.sgu.www.easyui.config;

import cn.edu.sgu.www.easyui.config.property.SystemSettingsProperties;
import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.filter.PermsFilter;
import cn.edu.sgu.www.easyui.realm.UserRealm;
import cn.edu.sgu.www.easyui.service.PermissionService;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro配置类
 * @author heyunlin
 * @version 1.0
 */
@Configuration
public class ShiroConfig {

    private final PermissionService permissionService;
    private final SystemSettingsProperties systemSettingsProperties;

    @Autowired
    public ShiroConfig(PermissionService permissionService, SystemSettingsProperties systemSettingsProperties) {
        this.permissionService = permissionService;
        this.systemSettingsProperties = systemSettingsProperties;
    }

    @Bean(name = "cacheManager")
    public MemoryConstrainedCacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
     * 配置安全管理器
     * @param userRealm UserRealm
     * @return DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm, MemoryConstrainedCacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(cacheManager);

        return securityManager;
    }

    /**
     * 配置Shiro过滤器工厂
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    @DependsOn("flywayConfig")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 注册安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 当用户访问认证资源的时候，如果用户没有登录，那么就会跳转到指定的页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 定义资源访问规则
        Map<String, String> map = new LinkedHashMap<>();

        // 1、允许不登录访问的资源
        map.put("/js/*", "anon");
        map.put("/css/*", "anon");
        map.put("/json/*", "anon");
        map.put("/images/*", "anon");
        map.put("/login.html", "anon");

        // 2、必须登录认证才能访问的资源
        map.put("/", "authc");
        map.put("/index.html", "authc");
        map.put("/html/*.html", "authc");

        // knife4j
        map.put("/doc.html", "authc");
        map.put("/v2/api-docs", "authc");
        map.put("/swagger-resources", "authc");

        // 开启了鉴权
        if(systemSettingsProperties.isEnableAuthorization()) {
            // 覆盖shiro的perms过滤器
            Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();

            filters.put("perms", new PermsFilter());

            shiroFilterFactoryBean.setFilters(filters);

            // 查询所有非匿名子接口权限
            List<Permission> permissions = permissionService.selectPermissions();

            // 把所有权限的url交给自定义过滤器AuthorizationFilter处理
            for (Permission permission : permissions) {
                map.put(permission.getUrl(), "perms[" + permission.getValue() + "]");
            }
        }

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

}