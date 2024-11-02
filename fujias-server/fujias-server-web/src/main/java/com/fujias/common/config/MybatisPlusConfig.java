package com.fujias.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fujias.common.dboprate.PageInterceptor;
import com.fujias.common.dboprate.ParameterInterceptor;

/**
 * MybatisのConfigクラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PageInterceptor paginationInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public ParameterInterceptor parameterInterceptor() {
        return new ParameterInterceptor();
    }

}
