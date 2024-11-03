package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.component.Tree;
import cn.edu.sgu.www.easyui.dto.PermissionTreeDTO;
import cn.edu.sgu.www.easyui.entity.RolePermission;
import cn.edu.sgu.www.easyui.pager.RolePermissionPager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface RolePermissionService {

	/**
	 * 用户权限初始化
	 */
	@Transactional(rollbackFor = Exception.class)
	void init(String userId);

	/**
	 * 为角色分配权限
	 * @param permissionTreeDTO 权限信息
	 */
	@Transactional(rollbackFor = Exception.class)
	void distribute(PermissionTreeDTO permissionTreeDTO);

	/**
	 * 添加角色权限
	 * @param permission 角色权限信息
	 */
	void insert(RolePermission permission);

	/**
	 * 通过id删除角色权限
	 * @param id 角色权限id
	 */
	void deleteById(Integer id);

	/**
	 * 通过id修改角色权限信息
	 * @param permission 角色权限信息
	 */
	void updateById(RolePermission permission);

	/**
	 * 查询角色的权限树
	 * @param roleId 角色id
	 * @return List<PermissionTreeVO>
	 */
	List<Tree> listTree(Integer roleId);

	/**
	 * 分页查询角色权限列表
	 * @param pager 分页查询条件
	 * @return Page<RolePermission>
	 */
	Page<RolePermission> selectByPage(RolePermissionPager pager);
}