package com.fujias.business.forms.p01;

import java.util.List;

import com.fujias.common.db.entity.T0204SystemSettings;
import com.fujias.common.entity.Pager;

public class P0124ProductbilityReportSettingControllerForm extends T0204SystemSettings{
    
    private Pager pager;

    private String grindingType;
    
    private String grindingType1;
    
    private String grindingType2;
    
    private String grindingType3;
    
    private String grindingType4;
    
    private List<T0204SystemSettings> settingList;
    
    private List<String> settingTypeList;

    public List<String> getSettingTypeList() {
        return settingTypeList;
    }

    public void setSettingTypeList(List<String> settingTypeList) {
        this.settingTypeList = settingTypeList;
    }

    public List<T0204SystemSettings> getSettingList() {
        return settingList;
    }

    public void setSettingList(List<T0204SystemSettings> settingList) {
        this.settingList = settingList;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    private String mode;

    public String getGrindingType() {
        return grindingType;
    }

    public void setGrindingType(String grindingType) {
        this.grindingType = grindingType;
    }

    public String getGrindingType1() {
        return grindingType1;
    }

    public void setGrindingType1(String grindingType1) {
        this.grindingType1 = grindingType1;
    }

    public String getGrindingType2() {
        return grindingType2;
    }

    public void setGrindingType2(String grindingType2) {
        this.grindingType2 = grindingType2;
    }

    public String getGrindingType3() {
        return grindingType3;
    }

    public void setGrindingType3(String grindingType3) {
        this.grindingType3 = grindingType3;
    }

    public String getGrindingType4() {
        return grindingType4;
    }

    public void setGrindingType4(String grindingType4) {
        this.grindingType4 = grindingType4;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

}
