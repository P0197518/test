package cn.edu.sgu.www.easyui.component;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单树
 * @author heyunlin
 * @version 1.0
 */
@Data
public class MenuTree implements Serializable {
	private static final long serialVersionUID = 18L;

	/**
	 * 菜单ID
	 */
	private String id;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 页面url
	 */
	private String url;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 父级菜单ID
	 */
	private String parentId;

	/**
	 * 子菜单
	 */
	List<MenuTree> menus;
}