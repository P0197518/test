package cn.edu.sgu.www.easyui.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
@TableName("user")
public class User implements Serializable {
	private static final long serialVersionUID = 18L;

	@ApiModelProperty(value = "ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;

	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private Integer gender;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;

	/**
	 * 是否启用
	 */
	@ApiModelProperty(value = "是否启用")
	private Integer enable;

	/**
	 * 最后一次登录时间
	 */
	@ApiModelProperty(value = "最后一次登录时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime lastLoginTime;
}