package com.fujias.common.db.entity;

import java.util.Date;

/**
 * t0202workers
 * @author AutoGenerator
 */
public class T0202Workers extends T0202WorkersKey {
    /**
     * null
     */
    private String departmentId;

    /**
     * null
     */
    private String workType;

    /**
     * null
     */
    private Boolean shareFlag;

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
     * @return departmentId departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * null
     * @param departmentId departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    /**
     * null
     * @return workType workType
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * null
     * @param workType workType
     */
    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    /**
     * null
     * @return shareFlag shareFlag
     */
    public Boolean getShareFlag() {
        return shareFlag;
    }

    /**
     * null
     * @param shareFlag shareFlag
     */
    public void setShareFlag(Boolean shareFlag) {
        this.shareFlag = shareFlag;
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