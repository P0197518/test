package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum ProdTypeStatus {

    订单生产("0001"), 计划生产("0002");

    private String code;

    ProdTypeStatus(String code) {
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
