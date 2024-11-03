package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Role;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface RoleService {

	/**
	 * 添加角色
	 * @param role 角色信息
	 */
	void insert(Role role);
	
	/**
	 * 通过id删除角色
	 * @param id 角色id
	 */
	void deleteById(Integer id);

	/**
	 * 通过id修改角色信息
	 * @param role 菜单信息
	 */
	void updateById(Role role);

	/**
	 * 查询全部角色
	 * @return List<Role>
	 */
	List<Role> selectAll();

	/**
	 * 通过id查询角色
	 * @param id 角色id
	 * @return Role
	 */
	Role selectById(String id);

	/**
	 * 分页查询角色列表
	 * @param pager 分页参数
	 * @return Page<Role>
	 */
	Page<Role> selectByPage(Pager<Role> pager);
}