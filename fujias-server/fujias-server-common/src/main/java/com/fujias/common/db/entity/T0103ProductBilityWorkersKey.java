package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * t0103productbilityworkers
 * @author AutoGenerator
 */
public class T0103ProductBilityWorkersKey extends BaseEntity {
    /**
     * null
     */
    private String yearMonth;

    /**
     * null
     */
    private String departmentId;

    /**
     * null
     */
    private String wokerName;

    /**
     * null
     * @return yearMonth yearMonth
     */
    public String getYearMonth() {
        return yearMonth;
    }

    /**
     * null
     * @param yearMonth yearMonth
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth == null ? null : yearMonth.trim();
    }

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
     * @return wokerName wokerName
     */
    public String getWokerName() {
        return wokerName;
    }

    /**
     * null
     * @param wokerName wokerName
     */
    public void setWokerName(String wokerName) {
        this.wokerName = wokerName == null ? null : wokerName.trim();
    }
}