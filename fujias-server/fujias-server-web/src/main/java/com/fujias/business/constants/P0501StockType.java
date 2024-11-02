package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0501StockType {

    采购入货("1001"), 委外入库("1002"), 制造入库("1003"), 制造不良入库("1008"), 个别入库("1004"), 不良入库("1005"), 制造原料返库("1006"), 制造半成品入库("1007"), 完成品出货("2001"), 领料出库(
                    "2002"), 个别出库("2003"), 不良出库("2004");

    private String code;

    P0501StockType(String code) {
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
