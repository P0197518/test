package cn.edu.sgu.www.easyui.config;

import cn.edu.sgu.www.easyui.config.property.SystemSettingsProperties;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * flyway配置类
 * @author heyunlin
 * @version 1.0
 */
@Slf4j
@Configuration
public class FlywayConfig {

    private final DataSource dataSource;
    private final DataSourceProperties dataSourceProperties;
    private final SystemSettingsProperties systemSettingsProperties;

    @Autowired
    public FlywayConfig(
            DataSource dataSource,
            DataSourceProperties dataSourceProperties,
            SystemSettingsProperties systemSettingsProperties) {
        this.dataSource = dataSource;
        this.dataSourceProperties = dataSourceProperties;
        this.systemSettingsProperties = systemSettingsProperties;
    }

    @Bean
    public FlywayProperties flywayProperties() {
        return new FlywayProperties();
    }

    @PostConstruct
    public void migrate() throws SQLException {
        if (systemSettingsProperties.isEnableTableAutoCreation()) {
            String username = dataSourceProperties.getUsername();
            String password = dataSourceProperties.getPassword();

            // jdbc:mysql://localhost:3306/easyui
            String url = dataSourceProperties.getUrl();
            // jdbc:mysql://localhost:3306
            String connectUrl = url.substring(0, url.lastIndexOf("/"));
            // 数据库名：easyui-admin
            String database = url.substring(url.lastIndexOf("/") + 1);

            // 创建数据库的SQL，注意：如果数据库名包含-，则执行该SQL会报错，导致启动失败
            String sql = "create database if not exists `" + database + "` DEFAULT CHARSET utf8mb4";

            // 创建数据库连接
            Connection connection = DriverManager.getConnection(connectUrl, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);

            int update = statement.executeUpdate();

            if (update > 0) {
                log.debug("数据库{}不存在，已经完成创建...", database);
            }
        }

        FlywayProperties flywayProperties = flywayProperties();

        if (flywayProperties.isEnabled()) {
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations(flywayProperties.getLocations().toArray(new String[]{}))
                    .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
                    .load();

            flyway.migrate();
        }
    }

}