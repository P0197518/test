package com.fujias.common.db.entity;

import com.fujias.common.base.BaseEntity;

/**
 * mkbn
 * @author AutoGenerator
 */
public class MKbnKey extends BaseEntity {
    /**
     * null
     */
    private String codeTypeCd;

    /**
     * null
     */
    private String codeCd;

    /**
     * null
     * @return codeTypeCd codeTypeCd
     */
    public String getCodeTypeCd() {
        return codeTypeCd;
    }

    /**
     * null
     * @param codeTypeCd codeTypeCd
     */
    public void setCodeTypeCd(String codeTypeCd) {
        this.codeTypeCd = codeTypeCd == null ? null : codeTypeCd.trim();
    }

    /**
     * null
     * @return codeCd codeCd
     */
    public String getCodeCd() {
        return codeCd;
    }

    /**
     * null
     * @param codeCd codeCd
     */
    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd == null ? null : codeCd.trim();
    }
}