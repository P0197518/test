package cn.edu.sgu.www.easyui.config.property;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@Component
@ConfigurationProperties(prefix = "response")
public class ResponseProperties {

    /**
     * 忽略的路径
     */
    @ApiModelProperty(value = "忽略的路径")
    private List<String> ignore;
}