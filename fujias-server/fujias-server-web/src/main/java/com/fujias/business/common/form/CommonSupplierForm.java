package com.fujias.business.common.form;

import java.math.BigDecimal;

/**
 * 供应商结构体
 * 
 * @author chenqiang
 *
 */
public class CommonSupplierForm {
    private String supplierId;

    private String supplierName;
    private String currencyType;
    private BigDecimal currencyTypeValue;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setCurrencyTypeValue(BigDecimal currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

}
