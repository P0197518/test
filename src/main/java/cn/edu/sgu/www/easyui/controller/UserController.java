package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.annotation.AnonymityAccess;
import cn.edu.sgu.www.easyui.dto.UserLoginDTO;
import cn.edu.sgu.www.easyui.dto.UserPassUpdateDTO;
import cn.edu.sgu.www.easyui.entity.User;
import cn.edu.sgu.www.easyui.pager.UserPager;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.UserService;
import cn.edu.sgu.www.easyui.util.UserUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping(path = "/user", produces="application/json;charset=utf-8")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation("安全退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public JsonResult<Void> logout() {
		userService.logout();

		return JsonResult.success();
	}

	@AnonymityAccess
	@ApiOperation("登录认证")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult<Void> login(@Validated UserLoginDTO loginDTO) {
		userService.login(loginDTO);

		return JsonResult.success();
	}

	@ApiOperation("添加用户")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult<Void> insert(User user) {
		userService.insert(user);

		return JsonResult.success("添加成功");
	}

	@ApiOperation("通过ID删除用户")
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	public JsonResult<Void> deleteById(@RequestParam String id) {
		userService.deleteById(id);

		return JsonResult.success("删除成功");
	}

	@ApiOperation("通过ID修改用户信息")
	@RequestMapping(value = "/updateById", method = RequestMethod.POST)
	public JsonResult<Void> updateById(User user) {
		userService.updateById(user);

		return JsonResult.success("修改成功");
	}

	@ApiOperation("修改用户的密码")
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public JsonResult<Void> updatePassword(@Validated UserPassUpdateDTO userPassUpdateDTO) {
		userService.updatePassword(userPassUpdateDTO);

		return JsonResult.success("修改成功");
	}

	@ApiOperation("查询全部用户")
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public List<User> selectAll() {
		return userService.selectAll();
	}

	@ApiOperation("获取登录的用户信息")
	@RequestMapping(value = "/getLogin", method = RequestMethod.GET)
	public JsonResult<User> getLogin() {
		User user = UserUtils.getLoginUser();

		return JsonResult.success(null, user);
	}

	@ApiOperation("通过id查询用户信息")
	@RequestMapping(value = "/selectById", method = RequestMethod.GET)
	public JsonResult<User> selectById(@RequestParam String id) {
		User user = userService.selectById(id);

		return JsonResult.success(null, user);
	}

	@ApiOperation("分页查询用户列表")
	@RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
	public JsonResult<JsonPage<User>> selectByPage(UserPager pager) {
		Page<User> result = userService.selectByPage(pager);

		return JsonResult.restPage(result);
	}

}