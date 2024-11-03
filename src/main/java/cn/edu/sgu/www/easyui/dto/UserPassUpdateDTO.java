package cn.edu.sgu.www.easyui.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class UserPassUpdateDTO implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不允许为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 旧密码
     */
    @NotEmpty(message = "密码不允许为空")
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPass;

    /**
     * 新密码
     */
    @NotEmpty(message = "密码不允许为空")
    @ApiModelProperty(value = "新密码", required = true)
    private String password;

    /**
     * 确认密码
     */
    @NotEmpty(message = "确认密码不允许为空")
    @ApiModelProperty(value = "确认密码", required = true)
    private String rePass;
}