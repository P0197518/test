package com.fujias.business.constants;

public enum CurrencyType {
    人民币("0001"), 日元("0002");

    private String code;

    CurrencyType(String code) {
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
