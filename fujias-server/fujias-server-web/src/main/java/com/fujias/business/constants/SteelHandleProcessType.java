package com.fujias.business.constants;

public enum SteelHandleProcessType {
    WC出库("1"), 保护焊("2"), 钢柄处理("3"), 徐冷处理("4"), 委外加工("5"), 表面处理1("6"), 检查区分("7"), 银焊("8");

    private String code;

    SteelHandleProcessType(String code) {
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
