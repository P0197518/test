package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * muserroles
 * @author AutoGenerator
 */
public class MUserRolesKey extends BaseEntity {
    /**
     * null
     */
    private String userId;

    /**
     * null
     */
    private String roleId;

    /**
     * null
     * @return userId userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * null
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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