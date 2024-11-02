package com.fujias.common.db.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * t0103productbilityworkers
 * @author AutoGenerator
 */
public class T0103ProductBilityWorkers extends T0103ProductBilityWorkersKey {
    /**
     * null
     */
    private BigDecimal workTime;

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
     * @return workTime workTime
     */
    public BigDecimal getWorkTime() {
        return workTime;
    }

    /**
     * null
     * @param workTime workTime
     */
    public void setWorkTime(BigDecimal workTime) {
        this.workTime = workTime;
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