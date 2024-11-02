package com.fujias.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Springbootの起動クラスです。
 * 
 * @author 陳強
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"com.fujias.common.security.springsecurity", "com.fujias.common.exception", "com.fujias.common.config", "com.fujias.common.component",
        "com.fujias.common.dboprate", "com.fujias.business.controller", "com.fujias.business.service", "com.fujias.business.common.controller",
        "com.fujias.business.common.service", "com.fujias.common.controller", "com.fujias.common.service"})
@MapperScan({"com.fujias.common.db.dao", "com.fujias.business.dao", "com.fujias.business.common.dao", "com.fujias.common.dao"})
public class MainApplication extends SpringBootServletInitializer {

    /**
     * Springbootの起動の入り口
     * 
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Tomcat使用のWarを作成する。
        return builder.sources(MainApplication.class);
    }

}
