package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = 18L;

	@TableId(value = "id", type = IdType.INPUT)
	@ApiModelProperty(value = "ID")
	private String id;

	/**
	 * 菜单名
	 */
	@ApiModelProperty(value = "菜单名")
	private String name;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标")
	private String icon;

	/**
	 * url地址
	 */
	@ApiModelProperty(value = "url地址")
	private String url;

	/**
	 * 菜单类型
	 * type = 0：目录
	 * type = 1：菜单
	 */
	@ApiModelProperty(value = "菜单类型")
	private Integer type;

	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "父菜单ID")
	private String parentId;

	/**
	 * 排序号
	 */
	@ApiModelProperty(value = "排序号")
	private Integer pxh;
}