package com.fujias.business.service.p07;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.p07.P0713PermissionListMapper;
import com.fujias.business.forms.p07.P0713PermissionListForm;
import com.fujias.business.forms.p07.P0713PermissionListItem;
import com.fujias.common.db.dao.MRoleResourceMapper;
import com.fujias.common.db.dao.MRolesMapper;
import com.fujias.common.db.entity.MRoleResource;
import com.fujias.common.db.entity.MRoles;
import com.fujias.common.util.BeanUtilsExtends;

/**
 * 角色管理的Service类
 * 
 * @author chenqiang
 *
 */
@Service
public class P0713PermissionListService {

    @Autowired
    private MRolesMapper rolesMapper;

    @Autowired
    private P0713PermissionListMapper s0100503RoleMapper;

    @Autowired
    private MRoleResourceMapper roleResourceMapper;

    /**
     * 删除权限
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoles(P0713PermissionListForm formData) {
        MRoles updateEntity = new MRoles();
        updateEntity.setId(formData.getId());
        updateEntity.setDelFlg("1");

        rolesMapper.updateByPrimaryKeySelective(updateEntity);

        MRoleResource roleResource = new MRoleResource();
        roleResource.setRoleId(formData.getId());
        roleResource.setDelFlg("1");
        s0100503RoleMapper.updateDelflgByRoleId(roleResource);
        return true;
    }

    /**
     * 还原角色
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean reuseRoles(P0713PermissionListForm formData) {
        MRoles updateEntity = new MRoles();
        updateEntity.setId(formData.getId());
        updateEntity.setDelFlg("0");
        rolesMapper.updateByPrimaryKeySelective(updateEntity);

        MRoleResource roleResource = new MRoleResource();
        roleResource.setRoleId(formData.getId());
        roleResource.setDelFlg("0");
        s0100503RoleMapper.updateDelflgByRoleId(roleResource);
        return true;
    }

    /**
     * 获取角色权限列表
     * 
     * @param formData formData
     * @return 角色权限列表
     */
    public List<P0713PermissionListItem> getRoleResource(P0713PermissionListForm formData) {
        List<P0713PermissionListItem> resourceList = s0100503RoleMapper.getRoleResources(formData);
        return resourceList;
    }

    /**
     * 添加角色以及对应的可操作权限
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(P0713PermissionListForm formData) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        formData.setId(uuid);

        // 更新roles表
        MRoles updateEntity = new MRoles();
        BeanUtilsExtends.copyProperties(updateEntity, formData);

        // updateEntity.setId("normal");
        updateEntity.setDelFlg("0");
        rolesMapper.insertSelective(updateEntity);

        updateRoleResource(formData);
        return true;
    }

    /**
     * 更新角色以及对应的可操作权限
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(P0713PermissionListForm formData) {

        // 更新roles表
        MRoles updateEntity = new MRoles();
        formData.setDelFlg("0");
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        rolesMapper.updateByPrimaryKeySelective(updateEntity);

        // 删除roleresource关联表的旧数据
        s0100503RoleMapper.deleteByRoleId(formData.getId());

        updateRoleResource(formData);
        return true;
    }

    private void updateRoleResource(P0713PermissionListForm formData) {

        // List<String> addMenuIds = new ArrayList<String>();

        // 添加roleresource关联表的新数据
        for (P0713PermissionListItem pageItem : formData.getResources()) {

            // if (pageItem.getFunctionList() != null) {
            //
            // for (S0100503RoleResourceItem functionItem : pageItem.getFunctionList()) {
            // if (functionItem.isUseFlag()) {
            //
            // MRoleResource functionResource = new MRoleResource();
            // functionResource.setResourceId(functionItem.getResourceId());
            // functionResource.setRoleId(formData.getId());
            // roleResourceMapper.insertSelective(functionResource);
            // }
            // }
            //
            // }

            if (pageItem.isUseFlag()) {
                MRoleResource pageresource = new MRoleResource();
                pageresource.setResourceId(pageItem.getResourceId());
                pageresource.setRoleId(formData.getId());
                pageresource.setDelFlg("0");
                roleResourceMapper.insertSelective(pageresource);

                // if (!addMenuIds.contains(pageItem.getParentId())) {
                // MRoleResource menusource = new MRoleResource();
                // menusource.setResourceId(pageItem.getParentId());
                // menusource.setRoleId(formData.getId());
                //
                // roleResourceMapper.insertSelective(menusource);
                //
                // addMenuIds.add(pageItem.getParentId());
                // }

            }

        }
    }

}
