package com.fujias.business.forms.p02;

import com.fujias.common.validation.ValidateIsReal;
import com.fujias.common.validation.ValidateMaxLength;
import com.fujias.common.validation.ValidateNotEmpty;

import cn.afterturn.easypoi.excel.annotation.Excel;



public class P0221WorkTimeImportForm {
    private String yearMonth;
    @Excel(name = "姓名")
    @ValidateNotEmpty(messageArgs = {"姓名"})
    @ValidateMaxLength(messageArgs = {"姓名"}, intLength = 20)
    private String workerName;
    @Excel(name = "出勤时间汇总")
    @ValidateMaxLength(messageArgs = {"出勤时间汇总"}, intLength = 20)
    @ValidateIsReal(messageArgs = {"出勤时间汇总"}, intLength = 3, decLength = 2)
    private String workHours;
    private String departmentId;
    @Excel(name = "部门")
    private String departmentName;
    @Excel(name = "是否间接",replace = {"是_0001", "否_0002"})
    private String workType;
    private String workTypeName;
    public String getWorkerName() {
        return workerName;
    }
    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
    public String getWorkHours() {
        return workHours;
    }
    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getWorkType() {
        return workType;
    }
    public void setWorkType(String workType) {
        this.workType = workType;
    }
    public String getYearMonth() {
        return yearMonth;
    }
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getWorkTypeName() {
        return workTypeName;
    }
    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }
    
}
