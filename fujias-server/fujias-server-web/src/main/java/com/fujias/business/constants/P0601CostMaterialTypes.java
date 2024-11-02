package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0601CostMaterialTypes {

    完成品("0001"), 原料("0002"), 副资材("0003"), 在制半成品("0004"), 丸棒("0005");

    private String code;

    P0601CostMaterialTypes(String code) {
        this.code = code;
    }

    /**
     * codeを取得する。
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
