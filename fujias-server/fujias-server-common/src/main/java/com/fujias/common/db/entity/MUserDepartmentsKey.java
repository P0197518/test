package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * muserdepartments
 * @author AutoGenerator
 */
public class MUserDepartmentsKey extends BaseEntity {
    /**
     * null
     */
    private String userId;

    /**
     * null
     */
    private String depId;

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
     * @return depId depId
     */
    public String getDepId() {
        return depId;
    }

    /**
     * null
     * @param depId depId
     */
    public void setDepId(String depId) {
        this.depId = depId == null ? null : depId.trim();
    }
}