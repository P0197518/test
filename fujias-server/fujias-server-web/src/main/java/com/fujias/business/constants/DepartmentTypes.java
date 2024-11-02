package com.fujias.business.constants;

/**
 * 部门
 * 
 * @author wangbaoxin
 *
 */
public enum DepartmentTypes {
    冲压("1000"), 研磨("2000"), 喷砂("2500"), 钎焊("5000"), 导环("6000"), 条形锁扣("7000"), 诸品TC("7200"), DPS("7500"), 清洗("7600"), 磨脚("8000"), 铅坠("9000");

    private String code;

    DepartmentTypes(String code) {
        this.code = code;
    }

    /**
     * 获取类型对应的code
     * 
     * @return 类型对应的code
     */
    public String getCode() {
        return this.code;
    }
}
