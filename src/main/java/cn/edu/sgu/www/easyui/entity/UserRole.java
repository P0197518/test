package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-角色表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 18L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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