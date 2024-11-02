package com.fujias.common.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fujias.common.form.CommonResourceRoles;
import com.fujias.common.security.springsecurity.access.CustomAccessDecisionManager;
import com.fujias.common.security.springsecurity.access.CustomAccessDeniedHandler;
import com.fujias.common.security.springsecurity.access.CustomSecurityInterceptor;
import com.fujias.common.security.springsecurity.access.CustomSecurityMetadataSource;
import com.fujias.common.security.springsecurity.access.RoleResourceCache;
import com.fujias.common.security.springsecurity.authentication.AjaxAuthenticationTokenResolver;
import com.fujias.common.security.springsecurity.authentication.AuthenticationTokenResolver;
import com.fujias.common.security.springsecurity.authentication.CustomAuthenticationFailureHandler;
import com.fujias.common.security.springsecurity.authentication.CustomAuthenticationProvider;
import com.fujias.common.security.springsecurity.authentication.CustomAuthenticationSuccessHandler;
import com.fujias.common.security.springsecurity.authentication.CustomUsernamePasswordAuthenticationFilter;
import com.fujias.common.security.springsecurity.authentication.Sha256PasswordEncoder;
import com.fujias.common.security.springsecurity.authentication.WebAuthenticationTokenResolver;
import com.fujias.common.security.springsecurity.token.CustomTokenErrorHandler;
import com.fujias.common.security.springsecurity.token.JwtAuthenticationFilter;
import com.fujias.common.service.SpringsecurityUserDetailsService;

/**
 * SpringSecurityの設定クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    /*
     * @Resource private FilterInvocationSecurityMetadataSource securityMetadataSource;
     */

    @Autowired
    private CustomTokenErrorHandler tokenErrorHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomSecurityMetadataSource securityMetadataSource;

    /**
     * 配置TokenRepository
     *
     * @return jdbcTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 配置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * 定义验证用manager
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();

        authenticationProvider.setUserDetailsService(customUserDetailsService());
        authenticationProvider.setPasswordEncoder(new Sha256PasswordEncoder());
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 定义各配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 用户登录验证过滤器
        http.addFilterAt(usernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class);
        // 每次访问的权限验证过滤器
        http.addFilterAt(filterSecurityInterceptor(), CustomSecurityInterceptor.class);

        http.addFilterAt(new JwtAuthenticationFilter(this.authenticationManager(), tokenErrorHandler), JwtAuthenticationFilter.class);

        // 设置白名单
        List<CommonResourceRoles> allWhite = RoleResourceCache.getAllWhiteList();
        String[] allWhiteList = new String[allWhite.size()];
        for (int index = 0; index < allWhite.size(); index++) {
            allWhiteList[index] = allWhite.get(index).getPath();
        }
        // http.csrf().disable().cors().and().authorizeRequests().antMatchers(allWhiteList).permitAll();
        // URLベルの権限制御を作成する。
        http.csrf().disable().cors().and().authorizeRequests().antMatchers(allWhiteList).permitAll().anyRequest().authenticated();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(3600 * 24 * 14);
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    /**
     * UserDetailsServiceを取得する
     * 
     * @return UserDetailsService
     */
    @Bean(name = "UserDetailsService")
    public SpringsecurityUserDetailsService customUserDetailsService() {
        SpringsecurityUserDetailsService customUserDetailsService = new SpringsecurityUserDetailsService();
        return customUserDetailsService;
    }

    /**
     * 用户验证过滤器生成
     * 
     * @return UsernamePasswordAuthenticationFilter
     * @throws Exception Exception
     */
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
        // 処理したいパスを指定する
        usernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login/dologin", "POST"));
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        List<AuthenticationTokenResolver> tokenResolvers = new ArrayList<AuthenticationTokenResolver>();
        tokenResolvers.add(new AjaxAuthenticationTokenResolver());
        tokenResolvers.add(new WebAuthenticationTokenResolver());
        usernamePasswordAuthenticationFilter.setTokenResolvers(tokenResolvers);

        return usernamePasswordAuthenticationFilter;
    }

    private FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        CustomSecurityInterceptor filterSecurityInterceptor = new CustomSecurityInterceptor(super.authenticationManagerBean(),
                        new CustomAccessDecisionManager(), securityMetadataSource, accessDeniedHandler);
        return filterSecurityInterceptor;
    }

}
