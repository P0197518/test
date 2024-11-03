package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.pager.PermissionPager;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.PermissionService;
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
@Api(tags = "权限管理")
@RequestMapping(path = "/permission", produces = "application/json;charset=utf-8")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation("添加权限")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(Permission permission) {
        permissionService.insert(permission);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过id删除权限")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@RequestParam String id) {
        permissionService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("通过id修改权限信息")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(Permission permission) {
        permissionService.updateById(permission);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("查询全部权限")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public JsonResult<List<Permission>> selectAll() {
        List<Permission> permissions = permissionService.selectAll();

        return JsonResult.success(null, permissions);
    }

    @ApiOperation("通过id查询权限")
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    public JsonResult<Permission> selectById(@RequestParam String id) {
        Permission permission = permissionService.selectById(id);

        return JsonResult.success(null, permission);
    }

    @ApiOperation("通过类型查询权限列表")
    @RequestMapping(value = "/selectByType", method = RequestMethod.GET)
    public JsonResult<List<Permission>> selectByType(@RequestParam Integer type) {
        List<Permission> permissions = permissionService.selectByType(type);

        return JsonResult.success(null, permissions);
    }

    @ApiOperation("分页查询权限列表")
    @RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<Permission>> selectByPage(PermissionPager pager) {
        Page<Permission> result = permissionService.selectByPage(pager);

        return JsonResult.restPage(result);
    }

    @ApiOperation("初始化权限列表")
    @RequestMapping(value = "/resources", method = RequestMethod.POST)
    public JsonResult<Void> resources() throws ClassNotFoundException {
        permissionService.resources();

        return JsonResult.success("权限加载完成。");
    }

}