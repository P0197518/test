package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.pager.PermissionPager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface PermissionService {

	/**
	 * 添加权限
	 * @param permission 权限信息
	 */
	void insert(Permission permission);
	
	/**
	 * 通过id删除权限
	 * @param id 权限id
	 */
	void deleteById(String id);

	/**
	 * 通过id修改权限信息
	 * @param permission 菜单信息
	 */
	void updateById(Permission permission);

	/**
	 * 查询全部权限
	 * @return List<Permission>
	 */
	List<Permission> selectAll();

	/**
	 * 通过id查询权限信息
	 * @param id 权限id
	 * @return Permission
	 */
	Permission selectById(String id);

	/**
	 * 通过类型查询权限列表
	 * @param type 权限类型
	 * @return List<Permission>
	 */
	List<Permission> selectByType(Integer type);

	/**
	 * 分页查询权限列表
	 * @param pager 分页参数
	 * @return Page<Permission>
	 */
	Page<Permission> selectByPage(PermissionPager pager);

	/**
	 * 扫描控制器类信息，保存到权限表
	 */
	@Transactional(rollbackFor = Exception.class)
	void resources() throws ClassNotFoundException;

	/**
	 * 查询所有非匿名子权限
	 * @return List<Permission>
	 */
	List<Permission> selectPermissions();

	/**
	 * 查询匿名接口权限
	 * @return List<String>
	 */
	List<String> selectAnonymityPermissions();
}