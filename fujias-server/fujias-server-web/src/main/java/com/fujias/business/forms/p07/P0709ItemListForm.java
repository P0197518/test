package com.fujias.business.forms.p07;

import com.fujias.common.db.entity.T0203AdjustSettings;
import com.fujias.common.entity.Pager;

public class P0709ItemListForm extends T0203AdjustSettings {
    private Pager pager;

    private String itemName;

    private String gc;

    private String dpsKubun;

    private String itaSheetKubun;

    private String ringSize;

    private String guideKubun;

    private String dpsSize;

    private String foot;

    private String frameName;
    
    private String department;
    
    private String classification;
    
    private String departmentId;
    

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    public String getDpsKubun() {
        return dpsKubun;
    }

    public void setDpsKubun(String dpsKubun) {
        this.dpsKubun = dpsKubun;
    }

    public String getItaSheetKubun() {
        return itaSheetKubun;
    }

    public void setItaSheetKubun(String itaSheetKubun) {
        this.itaSheetKubun = itaSheetKubun;
    }

    public String getRingSize() {
        return ringSize;
    }

    public void setRingSize(String ringSize) {
        this.ringSize = ringSize;
    }

    public String getGuideKubun() {
        return guideKubun;
    }

    public void setGuideKubun(String guideKubun) {
        this.guideKubun = guideKubun;
    }

    public String getDpsSize() {
        return dpsSize;
    }

    public void setDpsSize(String dpsSize) {
        this.dpsSize = dpsSize;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    

}
