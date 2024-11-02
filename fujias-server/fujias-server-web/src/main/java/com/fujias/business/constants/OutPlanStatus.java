package com.fujias.business.constants;

public enum OutPlanStatus {
    草稿中("1"), 未出货("2"), 部分出货("3"), 出货完成("4"), 手动出货完成("5");

    private String code;

    OutPlanStatus(String code) {
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
