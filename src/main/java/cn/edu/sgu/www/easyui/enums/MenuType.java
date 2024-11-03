package cn.edu.sgu.www.easyui.enums;

/**
 * 菜单类型枚举
 * @author heyunlin
 * @version 1.0
 */
public enum MenuType {

    /**
     * 目录
     */
    MULU(0, "目录"),
    /**
     * 菜单
     */
    CAIDAN(1, "菜单");

    private final Integer value;
    private final String name;

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    MenuType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

}