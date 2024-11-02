package com.fujias.business.forms.p02;

import java.util.List;

import com.fujias.common.db.entity.T0201Worktime;
import com.fujias.common.entity.Pager;

/**
 * @author 
 *
 */
public class P0221WorkTimeForm extends T0201Worktime {

    private String departmentId;
    
    private Pager pager;
    
    private List<String> depIds;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public List<String> getDepIds() {
        return depIds;
    }

    public void setDepIds(List<String> depIds) {
        this.depIds = depIds;
    }
    
    
    
    
}
