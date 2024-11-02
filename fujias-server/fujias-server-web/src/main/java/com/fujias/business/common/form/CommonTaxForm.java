package com.fujias.business.common.form;

import java.math.BigDecimal;

/**
 * 税率结构体
 * 
 * @author chenqiang
 *
 */
public class CommonTaxForm {

    private String taxId;
    private String taxName;
    private BigDecimal taxValue;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

}
