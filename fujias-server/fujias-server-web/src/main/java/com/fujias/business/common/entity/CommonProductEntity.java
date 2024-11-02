package com.fujias.business.common.entity;

import java.math.BigDecimal;

/**
 * 物流信息结构类
 * 
 * @author chenqiang
 *
 */
public class CommonProductEntity {

    /**
     * jancode
     */
    private String jancode;

    private Boolean delflg;

    /**
     * 商品名称
     */
    private String productname;
    /**
     * 有效期限
     */
    private BigDecimal expirydate;

    /**
     * 单品重量
     */
    private BigDecimal itemweight;

    /**
     * 输出输入区分
     */
    private String inputoutput;

    /**
     * 入数
     */
    private BigDecimal incount;

    private String tax;
    /**
     * 税率
     */
    private BigDecimal taxrate;

    private BigDecimal storagetotalnum;
    private BigDecimal canusecount;

    public String getJancode() {
        return jancode;
    }

    public void setJancode(String jancode) {
        this.jancode = jancode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public BigDecimal getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(BigDecimal expirydate) {
        this.expirydate = expirydate;
    }

    public BigDecimal getItemweight() {
        return itemweight;
    }

    public void setItemweight(BigDecimal itemweight) {
        this.itemweight = itemweight;
    }

    public String getInputoutput() {
        return inputoutput;
    }

    public void setInputoutput(String inputoutput) {
        this.inputoutput = inputoutput;
    }

    public BigDecimal getIncount() {
        return incount;
    }

    public void setIncount(BigDecimal incount) {
        this.incount = incount;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public BigDecimal getStoragetotalnum() {
        return storagetotalnum;
    }

    public void setStoragetotalnum(BigDecimal storagetotalnum) {
        this.storagetotalnum = storagetotalnum;
    }

    public BigDecimal getCanusecount() {
        return canusecount;
    }

    public void setCanusecount(BigDecimal canusecount) {
        this.canusecount = canusecount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Boolean getDelflg() {
        return delflg;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

}
