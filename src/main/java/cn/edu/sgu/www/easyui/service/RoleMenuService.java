package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.entity.RoleMenu;
import cn.edu.sgu.www.easyui.pager.RoleMenuPager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface RoleMenuService {

	/**
	 * 添加角色菜单
	 * @param roleMenu 角色菜单信息
	 */
	void insert(RoleMenu roleMenu);
	
	/**
	 * 通过id删除角色菜单
	 * @param id 角色菜单id
	 */
	void deleteById(Integer id);

	/**
	 * 通过id修改角色菜单信息
	 * @param roleMenu 角色菜单信息
	 */
	void updateById(RoleMenu roleMenu);

	/**
	 * 分页查询角色菜单列表
	 * @param pager 分页查询条件
	 * @return Page<RoleMenu>
	 */
	Page<RoleMenu> selectByPage(RoleMenuPager pager);
}