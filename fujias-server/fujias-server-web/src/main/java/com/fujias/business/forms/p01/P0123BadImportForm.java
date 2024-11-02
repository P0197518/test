package com.fujias.business.forms.p01;

import java.math.BigDecimal;


import cn.afterturn.easypoi.excel.annotation.Excel;



public class P0123BadImportForm {
    @Excel(name = "品名")
    private String itemName;
    @Excel(name = "不良内容")
    private String badContent;
    @Excel(name = "不良数量")
    private BigDecimal badCount;


    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getBadContent() {
        return badContent;
    }
    public void setBadContent(String badContent) {
        this.badContent = badContent;
    }
    
    public BigDecimal getBadCount() {
        return badCount;
    }
    public void setBadCount(BigDecimal badCount) {
        this.badCount = badCount;
    }

    

    
}
