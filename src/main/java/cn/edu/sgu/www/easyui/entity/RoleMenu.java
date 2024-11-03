package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("role_menu")
public class RoleMenu implements Serializable {
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
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String menuId;
}