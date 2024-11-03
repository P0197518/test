package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.annotation.Unused;
import cn.edu.sgu.www.easyui.dto.MenuTreeDTO;
import cn.edu.sgu.www.easyui.entity.UserMenu;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.UserMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyunlin
 * @version 1.0
 */
@RestController
@Api(tags = "用户菜单管理")
@RequestMapping(path = "/user_menu", produces = "application/json;charset=utf-8")
public class UserMenuController {

    private final UserMenuService userMenuService;

    @Autowired
    public UserMenuController(UserMenuService userMenuService) {
        this.userMenuService = userMenuService;
    }

    @Unused
    @ApiOperation("初始化用户菜单")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public JsonResult<Void> init() {
        userMenuService.init();

        return JsonResult.success("数据初始化成功~");
    }

    @ApiOperation("控制菜单显示")
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    public JsonResult<Void> control(@Validated MenuTreeDTO menuTreeDTO) {
        userMenuService.control(menuTreeDTO);

        return JsonResult.success("操作成功");
    }

    @ApiOperation("修改用户菜单信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult<Void> update(UserMenu userMenu) {
        userMenuService.update(userMenu);

        return JsonResult.success("修改成功~");
    }

}