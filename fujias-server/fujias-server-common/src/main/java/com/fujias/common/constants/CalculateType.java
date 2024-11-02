package com.fujias.common.constants;

public enum CalculateType {
    向上取整("1"), 四舍五入("2"), 向下取整("3");

    private String code;

    private CalculateType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return this.getName();
    }
}
