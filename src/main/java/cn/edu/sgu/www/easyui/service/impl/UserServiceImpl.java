package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.dto.UserLoginDTO;
import cn.edu.sgu.www.easyui.dto.UserPassUpdateDTO;
import cn.edu.sgu.www.easyui.entity.User;
import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.mapper.UserMapper;
import cn.edu.sgu.www.easyui.pager.UserPager;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.service.UserService;
import cn.edu.sgu.www.easyui.strategy.login.LoginStrategy;
import cn.edu.sgu.www.easyui.util.PasswordEncoder;
import cn.edu.sgu.www.easyui.util.StringUtils;
import cn.edu.sgu.www.easyui.util.UserUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final LoginStrategy loginStrategy;

	@Autowired
	public UserServiceImpl(UserMapper userMapper, LoginStrategy loginStrategy) {
		this.userMapper = userMapper;
		this.loginStrategy = loginStrategy;
	}

	@Override
	public void login(UserLoginDTO loginDTO) {
		loginStrategy.setLoginDTO(loginDTO);
		loginStrategy.doLogin();
	}

	@Override
	public void logout() {
		Subject subject = UserUtils.getSubject();

		subject.logout();
	}

	@Override
	public void insert(User user) {
		String password = user.getPassword();

		user.setId(StringUtils.uuid());

		if (StringUtils.isNotEmpty(password)) {
			user.setPassword(PasswordEncoder.encode(password));
		} else {
			// 默认密码为12345
			user.setPassword(PasswordEncoder.encode("12345"));
		}

		userMapper.insert(user);
	}

	@Override
	public void deleteById(String userId) {
		userMapper.deleteById(userId);
	}

	@Override
	public void updateById(User user) {
		String password = user.getPassword();

		if (StringUtils.isNotEmpty(password)) {
			user.setPassword(PasswordEncoder.encode(password));
		}

		userMapper.updateById(user);
	}

	@Override
	public void updatePassword(UserPassUpdateDTO userPassUpdateDTO) {
		// 用户名
		String username = userPassUpdateDTO.getUsername();
		// 旧密码
		String oldPass = userPassUpdateDTO.getOldPass();
		// 新密码
		String password = userPassUpdateDTO.getPassword();

		// 验证两次输入的密码是否相等
		if (password.equals(userPassUpdateDTO.getRePass())) {
			// 查询用户信息
			String encodedPassword = selectByUsername(username).getPassword();

			// 验证输入的旧密码是否正确
			if (PasswordEncoder.matches(oldPass, encodedPassword)) {
				UpdateWrapper<User> wrapper = new UpdateWrapper<>();

				wrapper.eq("username", username);
				wrapper.set("password", PasswordEncoder.encode(password));

				userMapper.update(wrapper.getEntity(), wrapper);
			} else {
				throw new GlobalException(ResponseCode.FORBIDDEN, "输入的密码不正确");
			}
		} else {
			throw new GlobalException(ResponseCode.FORBIDDEN, "两次输入的密码不一样");
		}
	}

	@Override
	public List<User> selectAll() {
		return userMapper.selectList(null);
	}

	@Override
	public User selectById(String id) {
		return userMapper.selectById(id);
	}

	@Override
	public User selectByUsername(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		wrapper.eq("username", username);

		return userMapper.selectOne(wrapper);
	}

	@Override
	public Page<User> selectByPage(UserPager pager) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		Page<User> page = Pager.ofPage(pager);

		wrapper.eq(
				StringUtils.isNotEmpty(pager.getPhone()),
				"phone", pager.getPhone()
		);

		return userMapper.selectPage(page, wrapper);
	}

}