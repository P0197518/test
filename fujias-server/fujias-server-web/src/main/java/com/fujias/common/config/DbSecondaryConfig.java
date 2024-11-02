package com.fujias.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.fujias.common.dboprate.PageInterceptor;
import com.fujias.common.dboprate.ParameterInterceptor;

@Configuration
@MapperScan(basePackages = {"com.fujias.business.daoerp"}, sqlSessionFactoryRef = "secSqlSessionFactory")
public class DbSecondaryConfig {

    /**
     * 获取 secSqlSessionFactory
     * 
     * @param dataSource dataSource
     * @return secSqlSessionFactory
     * @throws Exception Exception
     */
    @Bean(name = "secSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        Interceptor pageInterceptor = new PageInterceptor();

        Interceptor parameterInterceptor = new ParameterInterceptor();
        sessionFactoryBean.setPlugins(new Interceptor[] {pageInterceptor, parameterInterceptor});
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "secTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("secDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
