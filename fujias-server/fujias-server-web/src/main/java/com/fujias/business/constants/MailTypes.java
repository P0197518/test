package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum MailTypes {

    订单创建("1"), 订单修改("2"), 订单取消("3"), 指令书创建("4"), 指令书修改("5"), 指令书取消("6");

    private String code;

    MailTypes(String code) {
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
