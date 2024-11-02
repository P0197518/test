package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;
import java.util.Date;

/**
 * mdepartment
 * @author AutoGenerator
 */
public class MDepartment extends BaseEntity {
    /**
     * null
     */
    private String id;

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
    private String depId;

    /**
     * null
     */
    private String name;

    /**
     * null
     */
    private String parentId;

    /**
     * null
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * null
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    /**
     * null
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * null
     * @param name name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * null
     * @return parentId parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * null
     * @param parentId parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}