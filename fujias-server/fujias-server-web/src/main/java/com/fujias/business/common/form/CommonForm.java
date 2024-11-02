package com.fujias.business.common.form;

/**
 * 共通检索用结构体
 * 
 * @author chenqiang
 *
 */
public class CommonForm {
    private String key;

    private Boolean delFlg;

    private String materialType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

}
