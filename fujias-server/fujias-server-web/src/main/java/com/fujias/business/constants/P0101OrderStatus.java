package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0101OrderStatus {

    草稿中("1"), 已签约("2"), 生产中("3"), 部分发货("4"), 发货完成("5"), 已取消("7");

    private String code;

    P0101OrderStatus(String code) {
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
