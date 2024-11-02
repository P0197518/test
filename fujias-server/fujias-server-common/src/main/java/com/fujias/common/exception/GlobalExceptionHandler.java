package com.fujias.common.exception;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.ognl.OgnlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.CheckMessage;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.util.ExceptionUtil;
import com.fujias.common.util.ReflectionUtil;
import com.fujias.common.util.RequestUtil;

/**
 * 全体異常の共通処理クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LORRER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * すべての異常を処理する
     * 
     * @param exception 異常
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ResponseData
     */
    @ExceptionHandler(value = {Exception.class})
    public @ResponseBody Object exceptionHandler(Exception exception, HttpServletRequest request, HttpServletResponse response) {

        Throwable thr = getRootCause(exception);

        LORRER.error(ExceptionUtil.getMessage(exception));

        ResultInfo res = new ResultInfo();
        if (thr instanceof BusinessException) {
            // 業務異常場合の特殊処理
            BusinessException be = (BusinessException) thr;
            res.setStatus(StateTypes.ERROR);
            res.setText(be.getMessage());
        } else {
            res.setStatus(StateTypes.ERROR);
            res.setText(thr.getMessage());
        }
        return res;
    }

    /**
     * Ajax請求場合、FormDataの検証共通処理です。
     * 
     * @param ex ex
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return エラー返却結果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody Object handleNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request,
                    HttpServletResponse response) {
        return checkError(request, ex.getBindingResult());
    }

    /**
     * 普通請求場合、FormDataの検証共通処理です。
     * 
     * @param ex ex
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return エラー返却結果
     */
    @ExceptionHandler(BindException.class)
    public @ResponseBody Object handleBindException(BindException ex, HttpServletRequest request, HttpServletResponse response) {
        return checkError(request, ex.getBindingResult());
    }

    private Object checkError(HttpServletRequest request, BindingResult bindingResult) {
        List<FieldError> fieldError = bindingResult.getFieldErrors();

        List<CheckMessage> backMessages = new ArrayList<>();
        Object target = bindingResult.getTarget();
        if (target != null) {
            Class<?> errorClass = target.getClass();
            List<CheckMessage> errorMessage = new ArrayList<>();
            for (FieldError error : fieldError) {
                CheckMessage message = new CheckMessage();
                message.setField(error.getField());
                message.setCode(error.getDefaultMessage());
                String messageCode = error.getCode();
                if (messageCode != null && messageCode.startsWith("Validate")) {
                    List<String> messageArgs = ReflectionUtil.getMessageArgsValue(errorClass, error.getField(), error.getCode());
                    message.setArgs(messageArgs);
                } else {
                    message.setArgs(new ArrayList<String>());
                }
                errorMessage.add(message);
            }
            // FormBeanの書く順番によって、チェックエラーを表示する。
            List<Field> classFields = ReflectionUtil.getFields(errorClass);
            List<String> addedField = new ArrayList<String>();
            for (Field item : classFields) {
                for (CheckMessage errorItem : errorMessage) {
                    if (!addedField.contains(errorItem.getField()) && item.getName().equals(errorItem.getField())) {
                        backMessages.add(errorItem);
                        break;
                    }
                }
            }
        }

        if (RequestUtil.isAjaxRequest(request)) {
            ResultInfo res = new ResultInfo();
            res.setStatus(StateTypes.CHECKERROR);
            res.setCheckMessages(backMessages);
            return res;
        } else {
            ModelAndView modelView = new ModelAndView();
            modelView.setViewName("/errorpage/error");
            modelView.addObject("checkMessages", backMessages);
            return modelView;
        }
    }

    private Throwable getRootCause(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof OgnlException && ((OgnlException) throwable).getReason() != null) {
            return getRootCause(((OgnlException) throwable).getReason());
        }
        return throwable;
    }
}
