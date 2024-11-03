package cn.edu.sgu.www.easyui.realm;

import cn.edu.sgu.www.easyui.consts.RedisKeyPrefixes;
import cn.edu.sgu.www.easyui.entity.User;
import cn.edu.sgu.www.easyui.exception.UserLoginException;
import cn.edu.sgu.www.easyui.mapper.PermissionMapper;
import cn.edu.sgu.www.easyui.mapper.UserMapper;
import cn.edu.sgu.www.easyui.redis.RedisUtils;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.util.PasswordEncoder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

	private final RedisUtils redisUtils;
	private final UserMapper userMapper;
	private final PermissionMapper permissionMapper;

	@Autowired
	public UserRealm(
			RedisUtils redisUtils,
			UserMapper userMapper,
			PermissionMapper permissionMapper) {
		this.redisUtils = redisUtils;
		this.userMapper = userMapper;
		this.permissionMapper = permissionMapper;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		// 得到令牌
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		// 从token中获取用户的登录信息
		String username = token.getUsername();
		String password = new String(token.getPassword());
		
		// 根据用户名查询用户信息
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		wrapper.eq("username", username);

		User user = userMapper.selectOne(wrapper);

		if (user != null) {
			if (user.getEnable() == 1) {
				String encodedPassword = user.getPassword();

				if (PasswordEncoder.matches(password, encodedPassword)) {
					return new SimpleAuthenticationInfo(user, password, username);
				} else {
					// 密码错误，防止暴力破解
					String key = RedisKeyPrefixes.PREFIX_USER_LOGIN_TIMES + username;

					if (!redisUtils.hasKey(key)) {
						redisUtils.set(key, "0");
						redisUtils.expire(key, 30, TimeUnit.MINUTES);
					}

					// 30分钟内登录次数大于5，直接停用账号
					Long loginTimes = redisUtils.incrBy(key);

					if (loginTimes >= 5) {
						// 修改用户的启用状态为：0-未开启
						UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

						updateWrapper.eq("id", user.getId());
						updateWrapper.set("enable", 0);

						userMapper.update(updateWrapper.getEntity(), updateWrapper);

						// 触发停用账号后删除key
						redisUtils.delete(key);

						log.debug("账号{}在30分钟内登录失败5次，已经被锁定！", username);
					}

					throw new UserLoginException(ResponseCode.NOT_FOUND, "登录失败，用户名或密码不正确~");
				}
			} else {
				throw new UserLoginException(ResponseCode.FORBIDDEN, "账号已被锁定，禁止登录~");
			}
		} else {
			throw new UserLoginException(ResponseCode.NOT_FOUND, "登录失败，用户名不存在~");
		}
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获取登录的用户信息
		User user = (User) principals.getPrimaryPrincipal();
		// 得到用户名
		String username = user.getUsername();

		// 查询用户的权限
		List<String> permissions = permissionMapper.selectUserPermissions(username);

		authorizationInfo.setStringPermissions(new HashSet<>(permissions));

		return authorizationInfo;
	}

}