package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.entity.RoleMenu;
import cn.edu.sgu.www.easyui.pager.RoleMenuPager;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.RoleMenuService;
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
@Api(tags = "角色菜单管理")
@RequestMapping(path = "/role_menu", produces = "application/json;charset=utf-8")
public class RoleMenuController {

    private final RoleMenuService roleMenuService;

    @Autowired
    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @ApiOperation("添加角色菜单")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(RoleMenu permission) {
        roleMenuService.insert(permission);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过id删除角色菜单")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@RequestParam Integer id) {
        roleMenuService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("通过id修改角色菜单信息")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(RoleMenu permission) {
        roleMenuService.updateById(permission);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("分页查询角色菜单列表")
    @RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<RoleMenu>> selectByPage(RoleMenuPager pager) {
        Page<RoleMenu> page = roleMenuService.selectByPage(pager);

        return JsonResult.restPage(page);
    }

}