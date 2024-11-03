package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 18L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 权限值
     */
    @ApiModelProperty(name = "权限值", required = true)
    private String value;

    /**
     * 权限类型
     * 0-父级权限（控制器类）；1-子级权限（接口方法）
     */
    @ApiModelProperty(value = "权限类型")
    private Integer type;

    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    private String url;

    /**
     * 请求方式
     * 0-get；1-post
     */
    @ApiModelProperty(value = "请求方式")
    private Integer method;

    /**
     * 父级权限ID
     */
    @ApiModelProperty(value = "父级权限ID")
    private String parentId;

    /**
     * 是否允许匿名访问
     * 0-不允许匿名访问；1-允许匿名访问
     */
    @ApiModelProperty(value = "匿名访问")
    private Integer anonymity;
}