package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.component.MenuTree;
import cn.edu.sgu.www.easyui.component.MenuTreeGrid;
import cn.edu.sgu.www.easyui.component.Tree;
import cn.edu.sgu.www.easyui.entity.Menu;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.MenuService;
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
@Api(tags = "菜单管理")
@RequestMapping(path = "/menu", produces = "application/json;charset=utf-8")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@ApiOperation("添加菜单")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult<Void> insert(Menu menu) {
		menuService.insert(menu);
		
		return JsonResult.success("添加成功");
	}

	@ApiOperation("通过id删除菜单")
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	public JsonResult<Void> deleteById(@RequestParam String id) {
		menuService.deleteById(id);

		return JsonResult.success("删除成功");
	}

	@ApiOperation("通过id修改菜单信息")
	@RequestMapping(value = "/updateById", method = RequestMethod.POST)
	public JsonResult<Void> updateById(Menu menu) {
		menuService.updateById(menu);

		return JsonResult.success("修改成功");
	}

	@ApiOperation("查询全部菜单")
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public JsonResult<List<Menu>> selectAll() {
		List<Menu> menus = menuService.selectAll();

		return JsonResult.success(null, menus);
	}

	@ApiOperation("查询用户的菜单树")
	@RequestMapping(value = "/listTree", method = RequestMethod.GET)
	public JsonResult<List<Tree>> listTree() {
		List<Tree> list = menuService.listTree();

		return JsonResult.success(null, list);
	}

	@ApiOperation("查询用户的侧栏菜单")
	@RequestMapping(value = "/listMenus", method = RequestMethod.GET)
	public JsonResult<List<MenuTree>> listMenus() {
		List<MenuTree> list = menuService.listMenus();

		return JsonResult.success(null, list);
	}

	@ApiOperation("通过id查询菜单信息")
	@RequestMapping(value = "/selectById", method = RequestMethod.GET)
	public JsonResult<Menu> selectById(@RequestParam String id) {
		Menu menu = menuService.selectById(id);

		return JsonResult.success(null, menu);
	}

	@ApiOperation("查询全部目录")
	@RequestMapping(value = "/selectDirectory", method = RequestMethod.GET)
	public List<Menu> selectDirectory() {
		return menuService.selectDirectory();
	}

	@ApiOperation("查询菜单树形网格")
	@RequestMapping(value = "/listTreeGrid", method = RequestMethod.GET)
	public JsonResult<List<MenuTreeGrid>> listTreeGrid() {
		List<MenuTreeGrid> menuTreeGrids = menuService.listTreeGrid();

		return JsonResult.success(null, menuTreeGrids);
	}

	@ApiOperation("分页查询菜单列表")
	@RequestMapping(value = "/selectByPage", method = RequestMethod.GET)
	public JsonResult<JsonPage<Menu>> selectByPage(Pager<Menu> pager) {
		Page<Menu> page = menuService.selectByPage(pager);

		return JsonResult.restPage(page);
	}

}