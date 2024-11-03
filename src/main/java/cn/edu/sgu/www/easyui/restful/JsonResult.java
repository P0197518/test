package cn.edu.sgu.www.easyui.restful;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应实体类
 * @param <T>
 * @author heyunlin
 * @version 1.0
 */
@Data
@ApiModel
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 18L;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 响应状态码
     */
    @ApiModelProperty(value = "响应状态码")
    private Integer code;

    /**
     * 响应提示信息
     */
    @ApiModelProperty(value = "响应提示信息")
    private String message;

    /**
     * 成功提示
     */
    private static final String successMessage = "请求成功";

    public static JsonResult<Void> success() {
        return success(successMessage);
    }

    public static JsonResult<Void> success(String message) {
        return success(message, null);
    }

    public static <T> JsonResult<T> success(String message, T data) {
        JsonResult<T> jsonResult = new JsonResult<>();

        jsonResult.setCode(ResponseCode.OK.getValue());
        jsonResult.setMessage(message);
        jsonResult.setData(data);

        return jsonResult;
    }

    public static JsonResult<Void> error(String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();

        jsonResult.setCode(ResponseCode.ERROR.getValue());
        jsonResult.setMessage(message);

        return jsonResult;
    }

    public static JsonResult<Void> error(ResponseCode responseCode, Throwable e) {
        return error(responseCode, e.getMessage() != null ? e.getMessage() : "系统发生异常，请联系管理员！");
    }

    public static JsonResult<Void> error(ResponseCode responseCode, String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();

        jsonResult.setCode(responseCode.getValue());
        jsonResult.setMessage(message);

        return jsonResult;
    }

    public static <T> JsonResult<JsonPage<T>> restPage(Page<T> page) {
        JsonPage<T> jsonPage = JsonPage.restPage(page);

        return success(successMessage, jsonPage);
    }

}