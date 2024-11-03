package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Role;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.RoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "角色管理")
@RequestMapping(path = "/role", produces = "application/json;charset=utf-8")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(Role role) {
        roleService.insert(role);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过id删除角色")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@RequestParam Integer id) {
        roleService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("通过id修改角色信息")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(Role role) {
        roleService.updateById(role);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("查询全部角色")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public JsonResult<List<Role>> selectAll() {
        List<Role> roles = roleService.selectAll();

        return JsonResult.success(null, roles);
    }

    @ApiOperation("通过id查询角色信息")
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    public JsonResult<Role> selectById(@RequestParam String id) {
        Role role = roleService.selectById(id);

        return JsonResult.success(null, role);
    }

    @ApiOperation("分页查询角色列表")
    @RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<Role>> selectByPage(Pager<Role> pager) {
        Page<Role> page = roleService.selectByPage(pager);

        return JsonResult.restPage(page);
    }

}