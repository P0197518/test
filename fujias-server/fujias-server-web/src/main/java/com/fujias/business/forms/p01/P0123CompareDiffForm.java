package com.fujias.business.forms.p01;

import java.math.BigDecimal;



/**
 * @author chenqiang
 *
 */
public class P0123CompareDiffForm {

    private String yearMonth;
    private String itemKey;
    private String itemCD;
    private BigDecimal num;
    private String prodInstrNo;
    private String itemTypeCD;
    private String processCD;
    private String formPartment;
    private String stockChkGroup;

    private String txtStartDate;
    private String txtEndDate;
    private String[] itemTypeList;
    private String compareResult;
    private BigDecimal dailyCount;
    private String itemName;


    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemCD() {
        return itemCD;
    }

    public void setItemCD(String itemCD) {
        this.itemCD = itemCD;
    }


    public String getProdInstrNo() {
        return prodInstrNo;
    }

    public void setProdInstrNo(String prodInstrNo) {
        this.prodInstrNo = prodInstrNo;
    }

    public String getItemTypeCD() {
        return itemTypeCD;
    }

    public void setItemTypeCD(String itemTypeCD) {
        this.itemTypeCD = itemTypeCD;
    }

    public String getProcessCD() {
        return processCD;
    }

    public void setProcessCD(String processCD) {
        this.processCD = processCD;
    }

    public String getFormPartment() {
        return formPartment;
    }

    public void setFormPartment(String formPartment) {
        this.formPartment = formPartment;
    }

    public String getStockChkGroup() {
        return stockChkGroup;
    }

    public void setStockChkGroup(String stockChkGroup) {
        this.stockChkGroup = stockChkGroup;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }    


    public String getCompareResult() {
        return compareResult;
    }

    public void setCompareResult(String compareResult) {
        this.compareResult = compareResult;
    }

    public String getTxtStartDate() {
        return txtStartDate;
    }

    public void setTxtStartDate(String txtStartDate) {
        this.txtStartDate = txtStartDate;
    }

    public String getTxtEndDate() {
        return txtEndDate;
    }

    public void setTxtEndDate(String txtEndDate) {
        this.txtEndDate = txtEndDate;
    }

    public String[] getItemTypeList() {
        return itemTypeList;
    }

    public void setItemTypeList(String[] itemTypeList) {
        this.itemTypeList = itemTypeList;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getDailyCount() {
        return dailyCount;
    }

    public void setDailyCount(BigDecimal dailyCount) {
        this.dailyCount = dailyCount;
    }

    
    

}
