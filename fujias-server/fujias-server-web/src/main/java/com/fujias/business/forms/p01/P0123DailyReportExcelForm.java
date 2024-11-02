package com.fujias.business.forms.p01;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author FSPC274
 *
 */
public class P0123DailyReportExcelForm implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * null
     */
    private String id;

    /**
     * null
     */
    @Excel(name = "部门编号", height = 20, width = 30)
    private String departmentId;

    /**
     * null
     */
    @Excel(name = "指示编号", height = 20, width = 30)
    private String prodInstrNo;

    /**
     * null
     */
    @Excel(name = "品名", height = 20, width = 30)
    private String itemName;

    /**
     * null
     */
    @Excel(name = "指示数", height = 20, width = 30)
    private BigDecimal prodInstrNum;

    /**
     * null
     */
    @Excel(name = "机器号", height = 20, width = 30)
    private String machineNo;

    /**
     * null
     */
    @Excel(name = "小日程", height = 20, width = 30, exportFormat = "yyyy-MM-dd")
    private Date workDay;

    /**
     * null
     */
    @Excel(name = "工序", height = 20, width = 30)
    private String technicsCD;

    /**
     * null
     */
    @Excel(name = "良品数", height = 20, width = 30)
    private BigDecimal count;

    /**
     * null
     */
    @Excel(name = "不良品数", height = 20, width = 30)
    private BigDecimal badCount;

    /**
     * null
     */
    @Excel(name = "开始时间", height = 20, width = 30)
    private String startTime;

    /**
     * null
     */
    @Excel(name = "结束时间", height = 20, width = 30)
    private String endTime;

    /**
     * null
     */
    @Excel(name = "作业时间", height = 20, width = 30)
    private BigDecimal workMinutes;

    /**
     * null
     */
    @Excel(name = "不良时间", height = 20, width = 30)
    private BigDecimal badMinutes;

    /**
     * null
     */
    @Excel(name = "不良内容", height = 20, width = 30)
    private String badContent;

    /**
     * null
     */
    @Excel(name = "备注", height = 20, width = 30)
    private String remark;

    /**
     * null
     */
    @Excel(name = "员工编号", height = 20, width = 30)
    private String workerName;

    /**
     * null
     */
    @Excel(name = "录入时间", height = 20, width = 30, exportFormat = "yyyy-MM-dd")
    private Date createtime;

    /**
     * null
     */
    @Excel(name = "录入人", height = 20, width = 30)
    private String createuser;

    /**
     * null
     */
    private Date updatetime;

    /**
     * null
     */
    private String updateuser;

    /**
     * null
     */
    private Boolean delflg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getProdInstrNo() {
        return prodInstrNo;
    }

    public void setProdInstrNo(String prodInstrNo) {
        this.prodInstrNo = prodInstrNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getProdInstrNum() {
        return prodInstrNum;
    }

    public void setProdInstrNum(BigDecimal prodInstrNum) {
        this.prodInstrNum = prodInstrNum;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public Date getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Date workDay) {
        this.workDay = workDay;
    }

    public String getTechnicsCD() {
        return technicsCD;
    }

    public void setTechnicsCD(String technicsCD) {
        this.technicsCD = technicsCD;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getBadCount() {
        return badCount;
    }

    public void setBadCount(BigDecimal badCount) {
        this.badCount = badCount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getWorkMinutes() {
        return workMinutes;
    }

    public void setWorkMinutes(BigDecimal workMinutes) {
        this.workMinutes = workMinutes;
    }

    public BigDecimal getBadMinutes() {
        return badMinutes;
    }

    public void setBadMinutes(BigDecimal badMinutes) {
        this.badMinutes = badMinutes;
    }

    public String getBadContent() {
        return badContent;
    }

    public void setBadContent(String badContent) {
        this.badContent = badContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Boolean getDelflg() {
        return delflg;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
