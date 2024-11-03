package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.entity.UserRole;
import cn.edu.sgu.www.easyui.pager.UserRolePager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface UserRoleService {

	void insert(UserRole role);
	
	void deleteById(Integer id);

	void updateById(UserRole role);

	/**
	 * 分页查询用户角色列表
	 * @param pager 分页参数
	 * @return Page<UserRole>
	 */
	Page<UserRole> selectByPage(UserRolePager pager);
}