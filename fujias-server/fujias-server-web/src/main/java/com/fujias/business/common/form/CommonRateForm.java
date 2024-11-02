package com.fujias.business.common.form;

import java.math.BigDecimal;

/**
 * 税率结构体
 * 
 * @author chenqiang
 *
 */
public class CommonRateForm {

    private String rateId;
    private String rateName;
    private BigDecimal rateValue;

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public BigDecimal getRateValue() {
        return rateValue;
    }

    public void setRateValue(BigDecimal rateValue) {
        this.rateValue = rateValue;
    }

}
