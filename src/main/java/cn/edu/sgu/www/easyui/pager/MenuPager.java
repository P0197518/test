package cn.edu.sgu.www.easyui.pager;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Menu;
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
public class MenuPager extends Pager<Menu> implements Serializable {
	private static final long serialVersionUID = 18L;

	/**
	 * 菜单类型
	 */
	@ApiModelProperty(value = "菜单类型")
	private Integer type;

	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "父菜单ID")
	private String parentId;

	/**
	 * 是否显示
	 */
	@ApiModelProperty(value = "是否显示")
	private Integer display;
}