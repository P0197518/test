package cn.edu.sgu.www.easyui.enums;

/**
 * 权限类型枚举
 * @author heyunlin
 * @version 1.0
 */
public enum PermissionType {

    /**
     * 父权限
     */
    FQX(0, "父权限"),
    /**
     * 子权限
     */
    ZQX(1, "子权限");

    private final Integer value;
    private final String name;

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    PermissionType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

}
