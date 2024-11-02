package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;
import java.util.Date;

/**
 * t0602batcherrorreport
 * @author AutoGenerator
 */
public class T0602BatchErrorReport extends BaseEntity {
    /**
     * null
     */
    private String id;

    /**
     * null
     */
    private String lotId;

    /**
     * null
     */
    private String errorTarget;

    /**
     * null
     */
    private String errorContent;

    /**
     * null
     */
    private Date createtime;

    /**
     * null
     */
    private String createuser;

    /**
     * null
     */
    private Date updatetime;

    /**
     * null
     */
    private String updateuser;

    /**
     * null
     */
    private Boolean delflg;

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
     * @return lotId lotId
     */
    public String getLotId() {
        return lotId;
    }

    /**
     * null
     * @param lotId lotId
     */
    public void setLotId(String lotId) {
        this.lotId = lotId == null ? null : lotId.trim();
    }

    /**
     * null
     * @return errorTarget errorTarget
     */
    public String getErrorTarget() {
        return errorTarget;
    }

    /**
     * null
     * @param errorTarget errorTarget
     */
    public void setErrorTarget(String errorTarget) {
        this.errorTarget = errorTarget == null ? null : errorTarget.trim();
    }

    /**
     * null
     * @return errorContent errorContent
     */
    public String getErrorContent() {
        return errorContent;
    }

    /**
     * null
     * @param errorContent errorContent
     */
    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent == null ? null : errorContent.trim();
    }

    /**
     * null
     * @return createtime createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * null
     * @param createtime createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * null
     * @return createuser createuser
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * null
     * @param createuser createuser
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /**
     * null
     * @return updatetime updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * null
     * @param updatetime updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * null
     * @return updateuser updateuser
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * null
     * @param updateuser updateuser
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    /**
     * null
     * @return delflg delflg
     */
    public Boolean getDelflg() {
        return delflg;
    }

    /**
     * null
     * @param delflg delflg
     */
    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }
}