package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0201ProdType {

    订单生产("0001"), 计划生产("0002"), 子指令书("0003"), 补货生产("0004");

    private String code;

    P0201ProdType(String code) {
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
