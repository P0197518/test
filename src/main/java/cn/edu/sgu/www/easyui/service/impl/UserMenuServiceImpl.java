package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.consts.RedisKeyPrefixes;
import cn.edu.sgu.www.easyui.dto.MenuTreeDTO;
import cn.edu.sgu.www.easyui.entity.Menu;
import cn.edu.sgu.www.easyui.entity.UserMenu;
import cn.edu.sgu.www.easyui.entity.UserRole;
import cn.edu.sgu.www.easyui.enums.MenuType;
import cn.edu.sgu.www.easyui.mapper.MenuMapper;
import cn.edu.sgu.www.easyui.mapper.UserMenuMapper;
import cn.edu.sgu.www.easyui.mapper.UserRoleMapper;
import cn.edu.sgu.www.easyui.redis.RedisUtils;
import cn.edu.sgu.www.easyui.service.UserMenuService;
import cn.edu.sgu.www.easyui.util.UserUtils;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class UserMenuServiceImpl implements UserMenuService {

    private final MenuMapper menuMapper;
    private final RedisUtils redisUtils;
    private final UserMenuMapper userMenuMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserMenuServiceImpl(
            MenuMapper menuMapper,
            RedisUtils redisUtils,
            UserMenuMapper userMenuMapper,
            UserRoleMapper userRoleMapper) {
        this.menuMapper = menuMapper;
        this.redisUtils = redisUtils;
        this.userMenuMapper = userMenuMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void init() {
        String userId = UserUtils.getUserId();

        // 删除用户的角色菜单信息
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("user_id", userId);

        userMenuMapper.deleteByMap(objectMap);

        // 查询用户的角色
        UserRole userRole = userRoleMapper.selectByUserId(userId);
        // 查询角色所有菜单
        List<Menu> list = menuMapper.selectRoleMenus(userRole.getRoleId());

        for (Menu menu : list) {
            UserMenu userRoleMenu = new UserMenu();

            userRoleMenu.setId(null);
            userRoleMenu.setDisplay(true);
            userRoleMenu.setUserId(userId);
            userRoleMenu.setMenuId(menu.getId());

            userMenuMapper.insert(userRoleMenu);
        }
    }

    @Override
    public void control(MenuTreeDTO menuTreeDTO) {
        // 获取数据
        List<String> displayIds = menuTreeDTO.getDisplayIds();
        List<String> hideIds = menuTreeDTO.getHideIds();
        String userId = UserUtils.getUserId();

        // 构造条件对象
        UpdateWrapper<UserMenu> wrapper;

        if (hideIds != null && displayIds != null) {
            Set<String> show = new HashSet<>(displayIds);
            Set<String> hide = new HashSet<>(hideIds);
            // hide和show的交集
            Set<String> inter = new HashSet<>(show);
            inter.retainAll(hide);

            show.removeAll(inter);
            hide.removeAll(inter);

            wrapper = new UpdateWrapper<>();

            for (String menuId : hide) {
                wrapper.eq("menu_id", menuId);
                wrapper.eq("user_id", userId);

                wrapper.set("display", 0);

                userMenuMapper.update(wrapper.getEntity(), wrapper);
            }
            for (String menuId : show) {
                wrapper.eq("menu_id", menuId);
                wrapper.eq("user_id", userId);

                List<UserMenu> list = userMenuMapper.selectList(wrapper);

                if (list.isEmpty()) {
                    UserMenu userMenu = new UserMenu();

                    userMenu.setId(null);
                    userMenu.setDisplay(true);
                    userMenu.setUserId(userId);
                    userMenu.setMenuId(menuId);

                    userMenuMapper.insert(userMenu);
                } else {
                    wrapper.set("display", 1);

                    userMenuMapper.update(wrapper.getEntity(), wrapper);
                }
            }
        } else if (displayIds != null) {
            for (String displayId : displayIds) {
                wrapper = new UpdateWrapper<>();

                wrapper.eq("menu_id", displayId);
                wrapper.eq("user_id", userId);

                List<UserMenu> list = userMenuMapper.selectList(wrapper);

                if (list.isEmpty()) {
                    UserMenu userMenu = new UserMenu();

                    userMenu.setId(null);
                    userMenu.setDisplay(true);
                    userMenu.setUserId(userId);
                    userMenu.setMenuId(displayId);

                    userMenuMapper.insert(userMenu);
                } else {
                    wrapper.set("display", 1);

                    userMenuMapper.update(wrapper.getEntity(), wrapper);
                }
            }
        } else if (hideIds != null) {
            for (String hideId : hideIds) {
                wrapper = new UpdateWrapper<>();

                wrapper.eq("menu_id", hideId);
                wrapper.eq("user_id", userId);
                wrapper.set("display", 0);

                userMenuMapper.update(wrapper.getEntity(), wrapper);
            }
        }

        // 删除缓存
        redisUtils.delete(RedisKeyPrefixes.PREFIX_ROLE_MENU_TREE + userId);
        redisUtils.delete(RedisKeyPrefixes.PREFIX_USER_LEFT_MENUS + userId);
    }

    @Override
    public void update(UserMenu userMenu) {
        // 得到菜单ID
        String menuId = userMenu.getMenuId();
        Menu menu = menuMapper.selectById(menuId);

        // 得到用户ID
        String userId = UserUtils.getUserId();

        if (MenuType.MULU.getValue().equals(menu.getType())) {
            userMenu.setUserId(userId);

            userMenuMapper.updateByParentId(userMenu);
        } else {
            UpdateWrapper<UserMenu> wrapper = new UpdateWrapper<>();

            wrapper.eq("user_id", userId);
            wrapper.eq("menu_id", menuId);
            wrapper.set("pxh", userMenu.getPxh());

            userMenuMapper.update(wrapper.getEntity(), wrapper);
        }

        // 删除缓存
        redisUtils.delete(RedisKeyPrefixes.PREFIX_ROLE_MENU_TREE + userId);
        redisUtils.delete(RedisKeyPrefixes.PREFIX_USER_LEFT_MENUS + userId);
    }

}