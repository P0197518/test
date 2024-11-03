package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 18L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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