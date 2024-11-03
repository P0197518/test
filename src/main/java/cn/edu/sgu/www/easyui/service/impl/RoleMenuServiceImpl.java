package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Menu;
import cn.edu.sgu.www.easyui.entity.RoleMenu;
import cn.edu.sgu.www.easyui.enums.MenuType;
import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.mapper.MenuMapper;
import cn.edu.sgu.www.easyui.mapper.RoleMenuMapper;
import cn.edu.sgu.www.easyui.pager.RoleMenuPager;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.service.RoleMenuService;
import cn.edu.sgu.www.easyui.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Autowired
    public RoleMenuServiceImpl(MenuMapper menuMapper, RoleMenuMapper roleMenuMapper) {
        this.menuMapper = menuMapper;
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    public void insert(RoleMenu roleMenu) {
        String menuId = roleMenu.getMenuId();
        Integer roleId = roleMenu.getRoleId();
        // 检查是否已经添加该权限
        List<RoleMenu> menus;
        Menu menu = menuMapper.selectById(menuId);

        if (menu.getType().equals(MenuType.MULU.getValue())) { // 目录
            menus = roleMenuMapper.selectByCondition(roleId, menuId);

            if (!menus.isEmpty()) {
                throw new GlobalException(ResponseCode.CONFLICT, "角色已经拥有该菜单");
            }
        } else { // 菜单
            menus = roleMenuMapper.selectByCondition(roleId, menu.getParentId());

            if (!menus.isEmpty()) {
                throw new GlobalException(ResponseCode.CONFLICT, "角色已经拥有该菜单的父菜单");
            }
        }

        roleMenu.setId(null);
        roleMenuMapper.insert(roleMenu);
    }

    @Override
    public void deleteById(Integer id) {
        roleMenuMapper.deleteById(id);
    }

    @Override
    public void updateById(RoleMenu roleMenu) {
        roleMenuMapper.updateById(roleMenu);
    }

    @Override
    public Page<RoleMenu> selectByPage(RoleMenuPager pager) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        Page<RoleMenu> page = Pager.ofPage(pager);

        wrapper.eq(
                StringUtils.isNotEmpty(pager.getMenuId()),
                "menu_id", pager.getMenuId()
        );
        wrapper.eq(
                pager.getRoleId() != null,
                "role_id", pager.getRoleId()
        );

        return roleMenuMapper.selectPage(page, wrapper);
    }

}