package com.fujias.common.db.entity;

import java.util.Date;

/**
 * msearchcondation
 * @author AutoGenerator
 */
public class MSearchCondation extends MSearchCondationKey {
    /**
     * null
     */
    private Date createTime;

    /**
     * null
     */
    private String createUser;

    /**
     * null
     */
    private Date updateTime;

    /**
     * null
     */
    private String updateUser;

    /**
     * null
     */
    private String delFlg;

    /**
     * null
     */
    private String searchCondation;

    /**
     * null
     * @return createTime createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * null
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * null
     * @return createUser createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * null
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * null
     * @return updateTime updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * null
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * null
     * @return updateUser updateUser
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * null
     * @param updateUser updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * null
     * @return delFlg delFlg
     */
    public String getDelFlg() {
        return delFlg;
    }

    /**
     * null
     * @param delFlg delFlg
     */
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    /**
     * null
     * @return searchCondation searchCondation
     */
    public String getSearchCondation() {
        return searchCondation;
    }

    /**
     * null
     * @param searchCondation searchCondation
     */
    public void setSearchCondation(String searchCondation) {
        this.searchCondation = searchCondation == null ? null : searchCondation.trim();
    }
}