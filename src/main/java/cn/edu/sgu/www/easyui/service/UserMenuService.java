package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.dto.MenuTreeDTO;
import cn.edu.sgu.www.easyui.entity.UserMenu;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface UserMenuService {

	/**
	 * 初始化用户菜单
	 */
	@Transactional(rollbackFor = Exception.class)
	void init();

	/**
	 * 控制菜单显示
	 * @param menuTreeDTO 菜单信息
	 */
	@Transactional(rollbackFor = Exception.class)
	void control(MenuTreeDTO menuTreeDTO);

	/**
	 * 修改用户菜单信息
	 * @param userMenu 用户菜单信息
	 */
    void update(UserMenu userMenu);
}