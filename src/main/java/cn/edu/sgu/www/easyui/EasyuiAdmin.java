package cn.edu.sgu.www.easyui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

/**
 * @author heyunlin
 * @version 1.0
 */
@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class EasyuiAdmin {

    static Logger logger = LoggerFactory.getLogger(EasyuiAdmin.class);

    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("启动easyui后台管理系统...");
        }

        SpringApplication.run(EasyuiAdmin.class, args);
    }

}