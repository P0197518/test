package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.UserRole;
import cn.edu.sgu.www.easyui.mapper.UserRoleMapper;
import cn.edu.sgu.www.easyui.pager.UserRolePager;
import cn.edu.sgu.www.easyui.service.UserRoleService;
import cn.edu.sgu.www.easyui.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public void insert(UserRole role) {
        userRoleMapper.insert(role);
    }

    @Override
    public void deleteById(Integer id) {
        userRoleMapper.deleteById(id);
    }

    @Override
    public void updateById(UserRole role) {
        userRoleMapper.updateById(role);
    }

    @Override
    public Page<UserRole> selectByPage(UserRolePager pager) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        Page<UserRole> page = Pager.ofPage(pager);

        wrapper.eq(
                StringUtils.isNotEmpty(pager.getUserId()),
                "user_id", pager.getUserId()
        );
        wrapper.eq(
                pager.getRoleId() != null,
                "role_id", pager.getRoleId()
        );

        return userRoleMapper.selectPage(page, wrapper);
    }

}