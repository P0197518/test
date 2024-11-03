package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Role;
import cn.edu.sgu.www.easyui.mapper.RoleMapper;
import cn.edu.sgu.www.easyui.service.RoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public void insert(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void deleteById(Integer id) {
        roleMapper.deleteById(id);
    }

    @Override
    public void updateById(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public Role selectById(String id) {
        return roleMapper.selectById(id);
    }

    @Override
    public Page<Role> selectByPage(Pager<Role> pager) {
        Page<Role> page = Pager.ofPage(pager);

        return roleMapper.selectPage(page, null);
    }

}