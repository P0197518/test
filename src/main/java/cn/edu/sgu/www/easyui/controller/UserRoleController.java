package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.entity.UserRole;
import cn.edu.sgu.www.easyui.pager.UserRolePager;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.UserRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyunlin
 * @version 1.0
 */
@RestController
@Api(tags = "用户角色管理")
@RequestMapping(path = "/user_role", produces = "application/json;charset=utf-8")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @ApiOperation("添加用户角色")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(UserRole userRole) {
        userRoleService.insert(userRole);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过id删除用户角色")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@RequestParam Integer id) {
        userRoleService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("通过id修改用户角色信息")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(UserRole role) {
        userRoleService.updateById(role);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("分页查询用户角色列表")
    @RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<UserRole>> selectByPage(UserRolePager pager) {
        Page<UserRole> result = userRoleService.selectByPage(pager);

        return JsonResult.restPage(result);
    }

}