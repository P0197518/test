package cn.edu.sgu.www.easyui.pager;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author heyunlin
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class UserRolePager extends Pager<UserRole> implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
}