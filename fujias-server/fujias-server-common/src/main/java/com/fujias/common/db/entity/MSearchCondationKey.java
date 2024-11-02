package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * msearchcondation
 * @author AutoGenerator
 */
public class MSearchCondationKey extends BaseEntity {
    /**
     * null
     */
    private String pageId;

    /**
     * null
     */
    private String userId;

    /**
     * null
     * @return pageId pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * null
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

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
}