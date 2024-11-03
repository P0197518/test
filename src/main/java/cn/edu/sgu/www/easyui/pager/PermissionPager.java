package cn.edu.sgu.www.easyui.pager;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Permission;
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
public class PermissionPager extends Pager<Permission> implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 权限类型 0-父级权限（控制器类）；1-子权限（接口方法）
     */
    @ApiModelProperty(value = "权限类型")
    private Integer type;

    /**
     * 权限值
     */
    @ApiModelProperty(value = "权限值")
    private String value;

    /**
     * 父级权限ID
     */
    @ApiModelProperty(value = "父级权限ID")
    private String parentId;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID")
    private Integer serviceId;
}