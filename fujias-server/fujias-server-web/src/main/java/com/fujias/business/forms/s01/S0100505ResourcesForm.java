package com.fujias.business.forms.s01;

import com.fujias.common.db.entity.MResource;
import com.fujias.common.entity.Pager;

/**
 * 资源一览的Form类
 * 
 * @author wul
 *
 */
public class S0100505ResourcesForm extends MResource {

    private Pager pager;
    private String mode;

    private String parentname;

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

    /**
     * @return the parentname
     */
    public String getParentname() {
        return parentname;
    }

    /**
     * @param parentname the parentname to set
     */
    public void setParentname(String parentname) {
        this.parentname = parentname;
    }
}
