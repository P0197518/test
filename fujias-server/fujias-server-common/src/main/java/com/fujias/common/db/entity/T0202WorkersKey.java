package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * t0202workers
 * @author AutoGenerator
 */
public class T0202WorkersKey extends BaseEntity {
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
}