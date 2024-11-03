package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.mapper.PermissionMapper;
import cn.edu.sgu.www.easyui.pager.PermissionPager;
import cn.edu.sgu.www.easyui.service.PermissionService;
import cn.edu.sgu.www.easyui.util.ResourceScanner;
import cn.edu.sgu.www.easyui.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final ResourceScanner resourceScanner;
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(ResourceScanner resourceScanner, PermissionMapper permissionMapper) {
        this.resourceScanner = resourceScanner;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public void insert(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void deleteById(String id) {
        permissionMapper.deleteById(id);
    }

    @Override
    public void updateById(Permission permission) {
        permissionMapper.updateById(permission);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectList(null);
    }

    @Override
    public Permission selectById(String id) {
        return permissionMapper.selectById(id);
    }

    @Override
    public List<Permission> selectByType(Integer type) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        wrapper.eq("type", type);

        return permissionMapper.selectList(wrapper);
    }

    @Override
    public Page<Permission> selectByPage(PermissionPager pager) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        Page<Permission> page = Pager.ofPage(pager);

        wrapper.eq(
                pager.getType() != null,
                "type", pager.getType()
        );
        wrapper.like(
                StringUtils.isNotEmpty(pager.getName()),
                "name", pager.getName()
        );
        wrapper.like(
                StringUtils.isNotEmpty(pager.getValue()),
                "value", pager.getValue()
        );
        wrapper.eq(
                StringUtils.isNotEmpty(pager.getParentId()),
                "parent_id", pager.getParentId()
        );
        wrapper.eq(
                pager.getServiceId() != null,
                "service_id", pager.getServiceId()
        );

        return permissionMapper.selectPage(page, wrapper);
    }

    @Override
    public void resources() throws ClassNotFoundException {
        // 删除原来的权限
        permissionMapper.delete(null);

        // 扫描路径
        String basePackage = "cn.edu.sgu.www.easyui.controller";
        // 获取扫描结果
        List<Permission> permissions = resourceScanner.scan(basePackage);

        for (Permission permission : permissions) {
            permissionMapper.insert(permission);
        }
    }

    @Override
    public List<Permission> selectPermissions() {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        wrapper.eq("type", 1);
        wrapper.eq("anonymity", 0);

        return permissionMapper.selectList(wrapper);
    }

    @Override
    public List<String> selectAnonymityPermissions() {
        // select url from permission where anonymity = 1
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        wrapper.select("url");
        wrapper.eq("anonymity", 1);

        List<Permission> permissions = permissionMapper.selectList(wrapper);

        return permissions.stream().map(new Function<Permission, String>() {
            @Override
            public String apply(Permission permission) {
                return permission.getUrl();
            }
        }).collect(Collectors.toList());
    }

}