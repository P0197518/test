package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-菜单表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("user_menu")
public class UserMenu implements Serializable {
    private static final long serialVersionUID = 18L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer pxh;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String menuId;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示")
    private boolean display;
}