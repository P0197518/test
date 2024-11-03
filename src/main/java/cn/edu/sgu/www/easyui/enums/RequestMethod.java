package cn.edu.sgu.www.easyui.enums;

import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.restful.ResponseCode;

/**
 * 请求方式枚举
 * @author heyunlin
 * @version 1.0
 */
public enum RequestMethod {

    /**
     * GET请求
     */
    GET(0, "GET"),
    /**
     * POST请求
     */
    POST(1, "POST");

    private final Integer value;
    private final String name;

    RequestMethod(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Integer getValueByName(String name) {
        if (!"get".equalsIgnoreCase(name) && !"post".equalsIgnoreCase(name)) {
            throw new GlobalException(ResponseCode.NOT_ACCEPTABLE, "参数" + name + "不合法");
        }

        return "get".equalsIgnoreCase(name) ? 0 : 1;
    }

}