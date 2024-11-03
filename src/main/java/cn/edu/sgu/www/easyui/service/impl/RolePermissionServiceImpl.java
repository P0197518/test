package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.component.Tree;
import cn.edu.sgu.www.easyui.dto.PermissionTreeDTO;
import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.entity.RolePermission;
import cn.edu.sgu.www.easyui.enums.PermissionType;
import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.mapper.PermissionMapper;
import cn.edu.sgu.www.easyui.mapper.RolePermissionMapper;
import cn.edu.sgu.www.easyui.pager.RolePermissionPager;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.service.RolePermissionService;
import cn.edu.sgu.www.easyui.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final PermissionMapper permissionMapper;
    private final RolePermissionMapper rolePermissionMapper;

    public RolePermissionServiceImpl(PermissionMapper permissionMapper, RolePermissionMapper rolePermissionMapper) {
        this.permissionMapper = permissionMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public void init(String userId) {
        // 删除用当前户所有角色的权限
        rolePermissionMapper.deleteByUserId(userId);

        // 查询全部子权限
        List<Permission> list = permissionMapper.selectByType(PermissionType.ZQX.getValue());

        list.forEach(permission -> {
            RolePermission rolePermission = new RolePermission();

            rolePermission.setId(null);
            rolePermission.setRoleId(1);
            rolePermission.setPermissionId(permission.getId());

            rolePermissionMapper.insert(rolePermission);
        });
    }

    @Override
    public void distribute(PermissionTreeDTO permissionTreeDTO) {
        // 获取前端传来的数据
        List<String> insertIds = permissionTreeDTO.getInsertIds();
        List<String> deleteIds = permissionTreeDTO.getDeleteIds();
        Integer roleId = permissionTreeDTO.getRoleId();

        UpdateWrapper<RolePermission> wrapper;

        if (insertIds != null && deleteIds != null) {
            Set<String> insert = new HashSet<>(insertIds);
            Set<String> delete = new HashSet<>(deleteIds);

            System.out.println("insert:" + insert);
            System.out.println("delete:" + delete);

            // 求交集
            Set<String> inter;
            inter = new HashSet<>(insertIds);
            inter.retainAll(delete);

            // 删除交集
            insert.removeAll(inter);
            delete.removeAll(inter);

            if (!delete.isEmpty()) {
                for (String deleteId : delete) {
                    wrapper = new UpdateWrapper<>();

                    wrapper.eq("permission_id", deleteId);
                    wrapper.eq("role_id", roleId);

                    rolePermissionMapper.delete(wrapper);
                }
            }

            if (!insert.isEmpty()) {
                for (String insertId : insert) {
                    RolePermission permission = new RolePermission();

                    permission.setId(null);
                    permission.setRoleId(roleId);
                    permission.setPermissionId(insertId);

                    rolePermissionMapper.insert(permission);
                }
            }
        } else if (deleteIds != null) {
            for (String deleteId : deleteIds) {
                wrapper = new UpdateWrapper<>();

                wrapper.eq("permission_id", deleteId);
                wrapper.eq("role_id", roleId);

                rolePermissionMapper.delete(wrapper);
            }
        } else if (insertIds != null) {
            for (String insertId : insertIds) {
                RolePermission permission = new RolePermission();

                permission.setId(null);
                permission.setRoleId(roleId);
                permission.setPermissionId(insertId);

                rolePermissionMapper.insert(permission);
            }
        }
    }

    @Override
    public void insert(RolePermission permission) {
        String permissionId = permission.getPermissionId();
        // 查询权限信息
        Permission perm = permissionMapper.selectById(permissionId);

        // 如果是父级权限，为角色添加该权限下的所有子权限
        if (perm.getType().equals(PermissionType.FQX.getValue())) {
            // 查询权限的所有子权限
            List<Permission> permissions = permissionMapper.selectByParentId(permissionId);
            // 查询角色已有权限
            List<Permission> perms = rolePermissionMapper.selectByRoleId(permission.getRoleId());

            if (perms.isEmpty()) {
                permissions.forEach(p -> {
                    permission.setId(null);
                    permission.setPermissionId(p.getId());

                    rolePermissionMapper.insert(permission);
                });
            } else if (!permissions.containsAll(perms)) {
                permissions.forEach(p -> {
                    if (!perms.contains(p)) {
                        permission.setId(null);
                        permission.setPermissionId(p.getId());

                        rolePermissionMapper.insert(permission);
                    }
                });
            } else {
                throw new GlobalException(ResponseCode.CONFLICT, "角色已经添加了此权限。");
            }
        } else {
            permission.setId(null);

            rolePermissionMapper.insert(permission);
        }
    }

    @Override
    public void deleteById(Integer id) {
        rolePermissionMapper.deleteById(id);
    }

    @Override
    public void updateById(RolePermission permission) {
        rolePermissionMapper.updateById(permission);
    }

    @Override
    public List<Tree> listTree(Integer roleId) {
        if (roleId == null) {
            return new ArrayList<>();
        }

        // 查询所有父级权限（权限类型为0），并生成权限ID和权限信息的map
        Map<String, Permission> parentMap = new HashMap<>();
        List<Permission> parentPermissions = permissionMapper.selectByType(PermissionType.FQX.getValue());

        for (Permission permission : parentPermissions) {
            parentMap.put(permission.getId(), permission);
        }

        // 查询角色的权限
        List<Permission> permissions = rolePermissionMapper.selectByRoleId(roleId);
        // 查询全部二级权限（权限类型为1）
        List<Permission> subPermissions = permissionMapper.selectByType(PermissionType.ZQX.getValue());

        // 并根据父级权限ID分组存放到map中
        Map<String, List<Tree>> listHashMap = new HashMap<>();

        // 遍历，把查询出来的权限按照parentId存到map中
        for (Permission permission : subPermissions) {
            Tree children = new Tree();

            children.setId(permission.getId());
            children.setText(permission.getName());
            children.setChecked(permissions.contains(permission));

            String parentId = permission.getParentId();

            if (listHashMap.containsKey(parentId)) {
                listHashMap.get(parentId).add(children);
            } else {
                listHashMap.put(parentId, new ArrayList<>());
            }
        }

        // 构建返回结果对象
        List<Tree> trees = new ArrayList<>();

        // 遍历map，生成菜单树
        listHashMap.forEach((key, value) -> {
            Permission parent = parentMap.get(key);

            Tree tree = new Tree();

            tree.setState("open");
            tree.setChildren(value);
            tree.setId(parent.getId());
            tree.setText(parent.getName());

            trees.add(tree);
        });

        return trees;
    }

    @Override
    public Page<RolePermission> selectByPage(RolePermissionPager pager) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        Page<RolePermission> page = Pager.ofPage(pager);

        wrapper.eq(
                pager.getRoleId() != null,
                "role_id", pager.getRoleId()
        );
        wrapper.eq(
                StringUtils.isNotEmpty(pager.getPermissionId()),
                "permission_id", pager.getPermissionId()
        );


        return rolePermissionMapper.selectPage(page, wrapper);
    }

}