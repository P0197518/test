package cn.edu.sgu.www.easyui.config.property;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@Component
@ConfigurationProperties(prefix = "system.settings")
public class SystemSettingsProperties {

    /**
     * 是否开启鉴权
     */
    @ApiModelProperty(value = "是否开启鉴权")
    private boolean enableAuthorization;

    /**
     * 是否自动创建表
     */
    @ApiModelProperty(value = "是否自动创建表")
    private boolean enableTableAutoCreation;
}