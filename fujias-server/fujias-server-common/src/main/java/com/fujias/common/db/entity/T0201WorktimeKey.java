package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * t0201worktime
 * @author AutoGenerator
 */
public class T0201WorktimeKey extends BaseEntity {
    /**
     * null
     */
    private String yearMonth;

    /**
     * null
     */
    private String workerName;

    /**
     * null
     */
    private String departmentId;

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
     * @return workerName workerName
     */
    public String getWorkerName() {
        return workerName;
    }

    /**
     * null
     * @param workerName workerName
     */
    public void setWorkerName(String workerName) {
        this.workerName = workerName == null ? null : workerName.trim();
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
}