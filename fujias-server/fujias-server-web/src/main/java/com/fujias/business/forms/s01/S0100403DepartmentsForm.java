package com.fujias.business.forms.s01;

import com.fujias.common.db.entity.MDepartment;
import com.fujias.common.entity.Pager;

/**
 * 部门一览的Form类
 * 
 * @author wul
 *
 */
public class S0100403DepartmentsForm extends MDepartment {

    private Pager pager;
    private String mode;

    /**
     * @return the pager
     */
    public Pager getPager() {
        return pager;
    }

    /**
     * @param pager the pager to set
     */
    public void setPager(Pager pager) {
        this.pager = pager;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
}
