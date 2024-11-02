package com.fujias.business.forms;

import java.math.BigDecimal;
import java.util.Date;

/**
 * t0701material
 * 
 * @author fuxuebin
 *
 */
public class ImportT0701MaterialTexture extends ImportProductForm {
    /**
    * 
    */
    private Date createTime;

    /**
     * 
     */
    private String createUser;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String updateUser;

    /**
     * 
     */
    private Boolean delFlg;

    /**
     * 
     */
    private String id;

    private String productDrawingNo;

    private Integer bomNo;

    private String materialDrawingNo;

    private BigDecimal requireCount;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductDrawingNo() {
        return productDrawingNo;
    }

    public void setProductDrawingNo(String productDrawingNo) {
        this.productDrawingNo = productDrawingNo;
    }

    public Integer getBomNo() {
        return bomNo;
    }

    public void setBomNo(Integer bomNo) {
        this.bomNo = bomNo;
    }

    public String getMaterialDrawingNo() {
        return materialDrawingNo;
    }

    public void setMaterialDrawingNo(String materialDrawingNo) {
        this.materialDrawingNo = materialDrawingNo;
    }

    public BigDecimal getRequireCount() {
        return requireCount;
    }

    public void setRequireCount(BigDecimal requireCount) {
        this.requireCount = requireCount;
    }

}
