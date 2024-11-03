package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.dto.UserLoginDTO;
import cn.edu.sgu.www.easyui.dto.UserPassUpdateDTO;
import cn.edu.sgu.www.easyui.entity.User;
import cn.edu.sgu.www.easyui.pager.UserPager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface UserService {
	/**
	 * 退出登录
	 */
	void logout();

	/**
	 * 用户登录
	 * @param loginDTO 登录信息
	 */
	void login(UserLoginDTO loginDTO);


	/**
	 * 添加用户
	 * @param user 用户信息
	 */
	void insert(User user);

	/**
	 * 通过id删除用户
	 * @param userId 用户id
	 */
	void deleteById(String userId);

	/**
	 * 根据id修改用户信息
	 * @param user 用户信息
	 */
	void updateById(User user);

	/**
	 * 修改密码
	 * @param userPassUpdateDTO 用户的密码信息
	 */
	void updatePassword(UserPassUpdateDTO userPassUpdateDTO);

	/**
	 * 查询全部用户
	 * @return List<User>
	 */
	List<User> selectAll();

	/**
	 * 通过id查询用户信息
	 * @param id 用户id
	 * @return User
	 */
	User selectById(String id);

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return User
	 */
	User selectByUsername(String username);

	/**
	 * 分页查询用户列表
	 * @param pager 分页参数
	 * @return Page<Admin>
	 */
	Page<User> selectByPage(UserPager pager);
}