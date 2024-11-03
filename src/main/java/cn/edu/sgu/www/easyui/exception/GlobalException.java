package cn.edu.sgu.www.easyui.exception;

import cn.edu.sgu.www.easyui.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义全局异常
 * @author heyunlin
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {

    private ResponseCode responseCode;

    public GlobalException(ResponseCode responseCode, String message) {
        super(message);

        setResponseCode(responseCode);
    }

}