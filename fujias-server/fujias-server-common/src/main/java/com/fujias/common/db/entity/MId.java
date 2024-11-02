package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;
import java.util.Date;

/**
 * mid
 * @author AutoGenerator
 */
public class MId extends BaseEntity {
    /**
     * null
     */
    private String idType;

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
    private String idName;

    /**
     * null
     */
    private String idNo;

    /**
     * null
     * @return idType idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * null
     * @param idType idType
     */
    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
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
     * @return idName idName
     */
    public String getIdName() {
        return idName;
    }

    /**
     * null
     * @param idName idName
     */
    public void setIdName(String idName) {
        this.idName = idName == null ? null : idName.trim();
    }

    /**
     * null
     * @return idNo idNo
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * null
     * @param idNo idNo
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }
}