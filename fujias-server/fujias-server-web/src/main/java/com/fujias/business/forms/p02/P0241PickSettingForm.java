package com.fujias.business.forms.p02;

import java.util.List;

import com.fujias.common.db.entity.T0203AdjustSettings;
import com.fujias.common.entity.Pager;

/**
 * @author 
 *
 */
public class P0241PickSettingForm extends T0203AdjustSettings {
    private Pager pager;

    private String id;
    
    private String codeName;

    private String remark;
    
    private String remarkTime;
    
    private String mode;
    
    private String department;
    
    private List<T0203AdjustSettings> settingList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarkTime() {
        return remarkTime;
    }

    public void setRemarkTime(String remarkTime) {
        this.remarkTime = remarkTime;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<T0203AdjustSettings> getSettingList() {
        return settingList;
    }

    public void setSettingList(List<T0203AdjustSettings> settingList) {
        this.settingList = settingList;
    }
    
    


}
