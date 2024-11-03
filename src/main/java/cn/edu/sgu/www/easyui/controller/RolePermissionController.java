package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.component.Tree;
import cn.edu.sgu.www.easyui.dto.PermissionTreeDTO;
import cn.edu.sgu.www.easyui.entity.RolePermission;
import cn.edu.sgu.www.easyui.pager.RolePermissionPager;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.RolePermissionService;
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
@Api(tags = "角色权限管理")
@RequestMapping(path = "/role_permission", produces = "application/json;charset=utf-8")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @ApiOperation("初始化角色权限")
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public JsonResult<Void> init() {
        rolePermissionService.init(UserUtils.getUserId());

        return JsonResult.success("操作成功");
    }

    @ApiOperation("为角色分配权限")
    @RequestMapping(value = "/distribute", method = RequestMethod.POST)
    public JsonResult<Void> distribute(@Validated PermissionTreeDTO permissionTreeDTO) {
        rolePermissionService.distribute(permissionTreeDTO);

        return JsonResult.success("操作成功");
    }

    @ApiOperation("添加角色权限")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(RolePermission permission) {
        rolePermissionService.insert(permission);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过id删除角色权限")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@RequestParam Integer id) {
        rolePermissionService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("通过id修改角色权限信息")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(RolePermission permission) {
        rolePermissionService.updateById(permission);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("查询角色的权限树")
    @RequestMapping(value = "/listTree", method = RequestMethod.GET)
    public JsonResult<List<Tree>> listTree(@RequestParam(required = false) Integer roleId) {
        List<Tree> trees = rolePermissionService.listTree(roleId);

        return JsonResult.success(null, trees);
    }

    @ApiOperation("分页查询角色权限列表")
    @RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<RolePermission>> selectByPage(RolePermissionPager pager) {
        Page<RolePermission> page = rolePermissionService.selectByPage(pager);

        return JsonResult.restPage(page);
    }

}