package com.fujias.business.forms.p01;

import com.fujias.common.db.entity.T0101DailyReport;
import com.fujias.common.entity.Pager;

/**
 * @author FSPC274
 *
 */
public class P0123DailyReportForm extends T0101DailyReport{
    private Pager pager;
    
    private String classification;
    
    private String setting;
    
    private String departmentId;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    
    

}
