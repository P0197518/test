package com.fujias.business.constants;

public enum PickAlloyProcessType {
    WC出库("1"), 银片("2"), 洗合金("3"), 委外加工("4");

    private String code;

    PickAlloyProcessType(String code) {
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
