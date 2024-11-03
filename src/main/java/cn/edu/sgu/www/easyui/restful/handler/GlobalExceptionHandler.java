package cn.edu.sgu.www.easyui.restful.handler;

import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.util.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 全局异常处理类
 * @author heyunlin
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理GlobalException
     * @param e GlobalException
     * @return JsonResult<Void>
     */
    @ExceptionHandler
    public JsonResult<Void> handleGlobalException(GlobalException e) {
        printMessage(e);

        HttpServletResponse response = HttpUtils.getResponse();
        response.setStatus(e.getResponseCode().getValue());

        return JsonResult.error(e.getResponseCode(), e);
    }

    /**
     * 处理BindException
     * @param e BindException
     * @return JsonResult<Void>
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult<Void> handleBindException(BindException e) {
        printMessage(e);

        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = Objects.requireNonNull(fieldError).getDefaultMessage();

        return JsonResult.error(ResponseCode.BAD_REQUEST, defaultMessage);
    }

    /**
     * 处理Exception
     * @param e Exception
     * @return JsonResult<Void>
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult<Void> handleException(Exception e) {
        printMessage(e);

        return JsonResult.error(ResponseCode.ERROR, e);
    }

    private void printMessage(Exception e) {
        e.printStackTrace();
    }

}