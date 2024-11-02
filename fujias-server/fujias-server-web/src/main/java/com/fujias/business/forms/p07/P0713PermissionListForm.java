package com.fujias.business.forms.p07;

import java.util.List;

import com.fujias.common.db.entity.MRoles;

/**
 * 角色资源管理的编辑用Form类
 * 
 * @author chenqiang
 *
 */
public class P0713PermissionListForm extends MRoles {

    private String mode;

    private List<P0713PermissionListItem> resources;

    private Boolean delflg;

    private String rolename;

    /**
     * resourcesを取得する。
     * 
     * @return the resources
     */
    public List<P0713PermissionListItem> getResources() {
        return resources;
    }

    /**
     * resourcesを設定する。
     * 
     * @param resources the resources to set
     */
    public void setResources(List<P0713PermissionListItem> resources) {
        this.resources = resources;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Boolean getDelflg() {
        return delflg;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}
