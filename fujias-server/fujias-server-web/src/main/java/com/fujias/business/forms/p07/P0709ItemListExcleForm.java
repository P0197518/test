package com.fujias.business.forms.p07;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class P0709ItemListExcleForm implements java.io.Serializable {
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
    @Excel(name = "品名", height = 20, width = 30)
    private String itemName;
    
    /**
     * null
     */
    @Excel(name = "框架", height = 20, width = 30)
    private String frameName;
    
    /**
     * null
     */
    @Excel(name = "GC", height = 20, width = 30)
    private String gc;
    
    /**
     * null
     */
    @Excel(name = "DPS区分", height = 20, width = 30)
    private String dpsKubun;
    
    /**
     * null
     */
    @Excel(name = "Ita-Sheet区分", height = 20, width = 30)
    private String itaSheetKubun;
    
    /**
     * null
     */
    @Excel(name = "RING尺寸", height = 20, width = 30)
    private String ringSize;
    
    /**
     * null
     */
    @Excel(name = "Guide区分", height = 20, width = 30)
    private String guideKubun;
    
    /**
     * null
     */
    @Excel(name = "DPS尺寸", height = 20, width = 30)
    private String dpsSize;
    
    /**
     * null
     */
    @Excel(name = "Foot", height = 20, width = 30)
    private String foot;
    
    /**
     * null
     */
    private Date createtime;

    /**
     * null
     */
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
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

    
}
