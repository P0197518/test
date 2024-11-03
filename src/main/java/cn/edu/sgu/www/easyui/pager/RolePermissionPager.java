package cn.edu.sgu.www.easyui.pager;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.RolePermission;
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
public class RolePermissionPager extends Pager<RolePermission> implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    private String permissionId;
}