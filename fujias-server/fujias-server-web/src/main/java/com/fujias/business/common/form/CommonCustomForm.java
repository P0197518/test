package com.fujias.business.common.form;

import java.math.BigDecimal;
import java.util.List;

import com.fujias.common.entity.SelectItem;

/**
 * 客户信息结构体
 * 
 * @author chenqiang
 *
 */
public class CommonCustomForm {
    private String customNo;
    private String customName;
    private String currencyType;
    private BigDecimal currencyTypeValue;
    private Boolean isOfHeadOffice;
    private Boolean isHeadOffice;

    private String contactPerson;
    private String contactPhone;
    private Boolean delFlg;

    private List<SelectItem> address;

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
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

    public Boolean getIsOfHeadOffice() {
        return isOfHeadOffice;
    }

    public void setIsOfHeadOffice(Boolean isOfHeadOffice) {
        this.isOfHeadOffice = isOfHeadOffice;
    }

    public Boolean getIsHeadOffice() {
        return isHeadOffice;
    }

    public void setIsHeadOffice(Boolean isHeadOffice) {
        this.isHeadOffice = isHeadOffice;
    }

    public List<SelectItem> getAddress() {
        return address;
    }

    public void setAddress(List<SelectItem> address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

}
