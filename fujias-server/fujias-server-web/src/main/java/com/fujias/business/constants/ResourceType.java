package com.fujias.business.constants;

/**
 * 资源类型
 * 
 * @author chenqiang
 *
 */
public enum ResourceType {
    Module("0"), Page("1"), Function("2");

    private String code;

    ResourceType(String code) {
        this.code = code;
    }

    /**
     * 获取资源对应的code
     * 
     * @return 资源对应的code
     */
    public String getCode() {
        return this.code;
    }
}
