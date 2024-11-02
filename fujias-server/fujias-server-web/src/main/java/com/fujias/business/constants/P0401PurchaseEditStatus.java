package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0401PurchaseEditStatus {

    草稿中("1"), 未入货("2"), 部分入货("3"), 入货完成("4"), 强制入货完成("5");

    private String code;

    P0401PurchaseEditStatus(String code) {
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
