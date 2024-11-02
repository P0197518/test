package com.fujias.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.fujias.common.dboprate.PageInterceptor;
import com.fujias.common.dboprate.ParameterInterceptor;

@Configuration
@MapperScan(
                basePackages = {"com.fujias.common.db.dao", "com.fujias.common.dao", "com.fujias.business.dao"},
                sqlSessionFactoryRef = "priSessionFactory")
public class DbPrimaryConfig {
    /**
     * 获取 priSessionFactory
     * 
     * @param dataSource dataSource
     * @return priSessionFactory
     * @throws Exception Exception
     */
    @Bean(name = "priSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("priDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        Interceptor pageInterceptor = new PageInterceptor();

        Interceptor parameterInterceptor = new ParameterInterceptor();
        sessionFactoryBean.setPlugins(new Interceptor[] {pageInterceptor, parameterInterceptor});
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "priTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("priDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
