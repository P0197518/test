package com.fujias.business.common.entity;

/**
 * code取得参数类
 * 
 * @author chenqiang
 *
 */
public class CodeEntity {

    private String codeTypeCd;

    private Boolean delFlg;

    private String option1;

    private String option2;

    private String userId;

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getCodeTypeCd() {
        return codeTypeCd;
    }

    public void setCodeTypeCd(String codeTypeCd) {
        this.codeTypeCd = codeTypeCd;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
