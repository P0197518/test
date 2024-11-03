package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.component.MenuTree;
import cn.edu.sgu.www.easyui.component.MenuTreeGrid;
import cn.edu.sgu.www.easyui.component.Tree;
import cn.edu.sgu.www.easyui.consts.RedisKeyPrefixes;
import cn.edu.sgu.www.easyui.entity.Menu;
import cn.edu.sgu.www.easyui.enums.MenuType;
import cn.edu.sgu.www.easyui.mapper.MenuMapper;
import cn.edu.sgu.www.easyui.redis.RedisUtils;
import cn.edu.sgu.www.easyui.service.MenuService;
import cn.edu.sgu.www.easyui.util.StringUtils;
import cn.edu.sgu.www.easyui.util.UserUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

	private final MenuMapper menuMapper;
	private final RedisUtils redisUtils;

	@Autowired
	public MenuServiceImpl(MenuMapper menuMapper, RedisUtils redisUtils) {
		this.menuMapper = menuMapper;
		this.redisUtils = redisUtils;
	}

	@Override
	public void insert(Menu menu) {
		menu.setId(StringUtils.uuid());

		if (menu.getType() == null) {
			menu.setType(MenuType.MULU.getValue());
		}
		if (StringUtils.isNotEmpty(menu.getParentId())) {
			menu.setType(MenuType.CAIDAN.getValue());
		}

		menuMapper.insert(menu);
	}
	
	@Override
	public void deleteById(String id) {
		menuMapper.deleteById(id);
	}

	@Override
	public void updateById(Menu menu) {
		menu.setType(StringUtils.isEmpty(menu.getParentId()) ? MenuType.MULU.getValue() : MenuType.CAIDAN.getValue());

		menuMapper.updateById(menu);

		/*
		 * 删除缓存
		 */
		String userId = UserUtils.getUserId();

		redisUtils.delete(RedisKeyPrefixes.PREFIX_ROLE_MENU_TREE + userId);
		redisUtils.delete(RedisKeyPrefixes.PREFIX_USER_LEFT_MENUS + userId);
	}

	@Override
	public List<Tree> listTree() {
		String userId = UserUtils.getUserId();

		/*
		 * 从缓存中获取菜单树
		 */
		String key = RedisKeyPrefixes.PREFIX_ROLE_MENU_TREE + userId;

		if (redisUtils.hasKey(key)) {
			String data = redisUtils.get(key);

			return JSON.parseArray(data, Tree.class);
		}

		/*
		 * 缓存中没有，从数据库查找
		 */
		// 查询用户的菜单
		List<Menu> menus = menuMapper.selectMenus(userId);
		List<Menu> displayMenus = menuMapper.selectDisplayMenus(userId);

		// 创建map保存菜单信息
		Map<String, List<Menu>> map = new LinkedHashMap<>();

		for (Menu menu : menus) {
			String parentId = menu.getParentId();

			// 菜单
			if (MenuType.CAIDAN.getValue().equals(menu.getType()) && StringUtils.isNotEmpty(parentId)) {
				if (map.containsKey(parentId)) {
					map.get(parentId).add(menu);
				} else {
					List<Menu> menuList = new ArrayList<>();

					menuList.add(menu);

					map.put(parentId, menuList);
				}
			} else { // 目录
				List<Menu> list = menuMapper.selectByParentId(menu.getId());

				map.put(menu.getId(), list);
			}
		}

		List<Tree> list = new ArrayList<>();

		for (Map.Entry<String, List<Menu>> entry : map.entrySet()) {
			Menu parent = menuMapper.selectById(entry.getKey());
			String parentId = parent.getId();
			Tree menuTree = new Tree();

			menuTree.setId(parentId);
			menuTree.setId(parentId);
			menuTree.setText(parent.getName());
			// 父节点不设置选中状态，根据子节点的选中状态动态显示
			// menuTree.setChecked(true);
			menuTree.setState("closed");

			List<Tree> children = new ArrayList<>();

			List<Menu> value = entry.getValue();

			for (Menu menu : value) {
				Tree child = new Tree();

				child.setId(menu.getId());
				child.setText(menu.getName());
				child.setState("open");
				child.setPxh(menu.getPxh());
				child.setChecked(displayMenus.contains(menu));

				children.add(child);
			}

			menuTree.setChildren(children);
			// 设置菜单树节点的排序号
			menuTree.setPxh(menuMapper.selectMinPxh(parentId));
			list.add(menuTree);
		}

		list.sort(new Comparator<Tree>() {
			@Override
			public int compare(Tree tree1, Tree tree2) {
				return tree1.getPxh() - tree2.getPxh();
			}
		});

		// 缓存数据
		redisUtils.set(key, JSON.toJSONString(list));

		return list;
	}

	@Override
	public List<Menu> selectAll() {
		return menuMapper.selectList(null);
	}

	@Override
	public List<MenuTree> listMenus() {
		String userId = UserUtils.getUserId();
		String key = RedisKeyPrefixes.PREFIX_USER_LEFT_MENUS + userId;

		// 查询用户的菜单
		List<Menu> list = menuMapper.selectDisplayMenus(userId);
		// 创建一个map，用于保存父菜单ID和子菜单列表
		LinkedHashMap<String, List<MenuTree>> treeMap = new LinkedHashMap<>();

		/*
		 * 查询缓存
		 */
		String data = redisUtils.get(key);

		if (data != null) {
			Object object = JSON.parse(data);

			return (List<MenuTree>) object;
		}

		/*
		 * 缓存中没有，从数据库查询
		 */
		// 遍历菜单树，通过父菜单id查询子菜单并设置到菜单树中
		for(Menu menu : list) {
			// 菜单类型为菜单
			if (MenuType.CAIDAN.getValue().equals(menu.getType())) {
				// 创建菜单树
				MenuTree child = new MenuTree();

				child.setId(menu.getId());
				child.setUrl(menu.getUrl());
				child.setName(menu.getName());
				child.setIcon(menu.getIcon());

				// 得到父菜单id
				String parentId = menu.getParentId();

				// map中已经保存了父菜单信息
				if (treeMap.containsKey(parentId)) {
					treeMap.get(parentId).add(child);
				} else {
					List<MenuTree> menus = new ArrayList<>();

					menus.add(child);
					treeMap.put(parentId, menus);
				}
			}
		}

		// 遍历map，生产菜单树
		List<MenuTree> menuTrees = new ArrayList<>();

		treeMap.forEach((entryKey, value) -> {
			// 查询父菜单信息
			Menu parent = menuMapper.selectById(entryKey);
			MenuTree tree = new MenuTree();

			tree.setId(parent.getId());
			tree.setName(parent.getName());
			tree.setIcon(parent.getIcon());
			tree.setMenus(value);

			menuTrees.add(tree);
		});

		redisUtils.set(key, JSON.toJSONString(menuTrees));

		return menuTrees;
	}

	@Override
	public Menu selectById(String id) {
		return menuMapper.selectById(id);
	}

	@Override
	public List<Menu> selectDirectory() {
		QueryWrapper<Menu> wrapper = new QueryWrapper<>();

		wrapper.eq("type", MenuType.MULU.getValue());

		return menuMapper.selectList(wrapper);
	}

	@Override
	public List<MenuTreeGrid> listTreeGrid() {
		Map<String, Menu> menuMap = new HashMap<>();
		Map<String, List<MenuTreeGrid>> listHashMap = new HashMap<>();

		List<Menu> menus = menuMapper.selectList(null);

		for (Menu menu : menus) {
			String menuId = menu.getId();

			if (MenuType.MULU.getValue().equals(menu.getType())) {
				menuMap.put(menuId, menu);

				if (!listHashMap.containsKey(menuId)) {
					List<MenuTreeGrid> list = new ArrayList<>();

					listHashMap.put(menuId, list);
				}
			} else {
				String parentId = menu.getParentId();

				if (parentId != null && !listHashMap.containsKey(parentId)) {
					List<MenuTreeGrid> list = new ArrayList<>();

					listHashMap.put(parentId, list);
				}

				MenuTreeGrid treeGrid = new MenuTreeGrid();

				treeGrid.setId(menuId);
				treeGrid.setState("open");
				treeGrid.setPxh(menu.getPxh());
				treeGrid.setUrl(menu.getUrl());
				treeGrid.setName(menu.getName());
				treeGrid.setIcon(menu.getIcon());
				treeGrid.setType(menu.getType());
				treeGrid.setParentId(menu.getParentId());

				listHashMap.get(parentId).add(treeGrid);
			}
		}

		// 构建返回值对象
		List<MenuTreeGrid> menuTreeGrids = new ArrayList<>();

		for (Map.Entry<String, List<MenuTreeGrid>> entry : listHashMap.entrySet()) {
			// 通过菜单ID获取父菜单
			Menu parent = menuMap.get(entry.getKey());

			// 封装单个MenuTreeGrid对象
			MenuTreeGrid treeGrid = new MenuTreeGrid();

			treeGrid.setId(parent.getId());
			treeGrid.setState("open");
			treeGrid.setPxh(parent.getPxh());
			treeGrid.setName(parent.getName());
			treeGrid.setIcon(parent.getIcon());
			treeGrid.setType(parent.getType());
			treeGrid.setChildren(entry.getValue());

			menuTreeGrids.add(treeGrid);
		}

		return menuTreeGrids;
	}

	@Override
	public Page<Menu> selectByPage(Pager<Menu> pager) {
		QueryWrapper<Menu> wrapper = Pager.getQueryWrapper(pager);
		Page<Menu> page = Pager.ofPage(pager);

		return menuMapper.selectPage(page, wrapper);
	}

}