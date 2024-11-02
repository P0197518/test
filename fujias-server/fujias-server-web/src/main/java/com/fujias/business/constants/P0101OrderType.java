package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0101OrderType {

    客户订单("0001"), 加工订单("0002"), 索赔订单("0003"), 样品订单("0004"), 税率区分("0001");

    private String code;

    P0101OrderType(String code) {
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
