package com.fujias.common.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujias.common.entity.LoginUser;

/**
 * Spring対象取得用共通クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        System.out.println("---------------SpringUtilを初期化する---------------");
    }

    /**
     * ApplicationContextを取得する。
     * 
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * SpringのBeanを取得する。
     * 
     * @param name name
     * @return bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * JacksonのObjectMapperを取得する。 Jacksonを使う時、再度ObjectMapperを作成せずに、当該メソッドを使ってください。
     * 
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectManager() {
        Object objectMapper = getBean("objectMapper");
        if (objectMapper == null) {
            return null;
        } else {
            return (ObjectMapper) objectMapper;
        }
    }

    /**
     * 获取当前登录用户ID
     * 
     * @return ObjectMapper
     */
    public static String getLoginUserId() {
        String userId = "unknowuser";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() != null) {
            userId = ((LoginUser) auth.getPrincipal()).getUsername();
        }
        return userId;
    }

    /**
     * SpringのBeanを取得する。
     * 
     * @param <T> T
     * @param clazz clazz
     * @return bean
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * SpringのBeanを取得する。
     * 
     * @param <T> T
     * @param name name
     * @param clazz clazz
     * @return bean
     */
    public static <T> T getBeanByNameClass(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取开发环境还是生产环境
     * 
     * @return 运行状态区分
     */
    public static String getActiveProfile() {
        return getApplicationContext().getEnvironment().getActiveProfiles()[0];
    }

}
