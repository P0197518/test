package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.component.MenuTree;
import cn.edu.sgu.www.easyui.component.MenuTreeGrid;
import cn.edu.sgu.www.easyui.component.Tree;
import cn.edu.sgu.www.easyui.entity.Menu;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface MenuService {

	/**
	 * 添加菜单
	 * @param menu 菜单信息
	 */
	void insert(Menu menu);
	
	/**
	 * 通过id删除菜单
	 * @param id 菜单id
	 */
	void deleteById(String id);

	/**
	 * 通过id修改菜单信息
	 * @param menu 菜单信息
	 */
	void updateById(Menu menu);

	/**
	 * 查询角色的菜单树
	 * @return List<Tree<Menu>>
	 */
	List<Tree> listTree();

	/**
	 * 查询全部菜单
	 * @return List<Menu>
	 */
	List<Menu> selectAll();

	/**
	 * 查询角色的侧栏菜单
	 * @return List<MenuTree>
	 */
	List<MenuTree> listMenus();

	/**
	 * 通过id查询菜单信息
	 * @param id 菜单id
	 * @return Menu
	 */
	Menu selectById(String id);

	/**
	 * 查询全部目录，即type=0的菜单
	 * @return List<Menu
	 */
	List<Menu> selectDirectory();

	/**
	 * 查询菜单树形网格
	 * @return List<MenuTreeGrid>
	 */
	List<MenuTreeGrid> listTreeGrid();

	/**
	 * 分页查询菜单列表
	 * @param pager 分页参数
	 * @return Page<Menu>
	 */
	Page<Menu> selectByPage(Pager<Menu> pager);
}