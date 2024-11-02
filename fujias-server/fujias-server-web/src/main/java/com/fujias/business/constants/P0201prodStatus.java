package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum P0201prodStatus {

    草稿("1"), 未开始("2"), 生产中("3"), 生产完成("4");

    private String code;

    P0201prodStatus(String code) {
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
