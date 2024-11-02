package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * mroleresource
 * @author AutoGenerator
 */
public class MRoleResourceKey extends BaseEntity {
    /**
     * null
     */
    private String resourceId;

    /**
     * null
     */
    private String roleId;

    /**
     * null
     * @return resourceId resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * null
     * @param resourceId resourceId
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    /**
     * null
     * @return roleId roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * null
     * @param roleId roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}