package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * t0104productbilityitems
 * @author AutoGenerator
 */
public class T0104ProductBilityItemsKey extends BaseEntity {
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
    private String itemName;

    /**
     * null
     */
    private String technicsCD;

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
     * @return itemName itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * null
     * @param itemName itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * null
     * @return technicsCD technicsCD
     */
    public String getTechnicsCD() {
        return technicsCD;
    }

    /**
     * null
     * @param technicsCD technicsCD
     */
    public void setTechnicsCD(String technicsCD) {
        this.technicsCD = technicsCD == null ? null : technicsCD.trim();
    }
}