package com.fujias.business.constants;

/**
 * 物質の質
 * 
 * @author 杨昇
 *
 */
public enum P0501StockMaterialType {

    正常("0001"), 不良("0002"), 半成品("0003");

    private String code;

    P0501StockMaterialType(String code) {
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
