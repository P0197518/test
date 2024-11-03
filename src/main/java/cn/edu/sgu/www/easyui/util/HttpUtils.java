package cn.edu.sgu.www.easyui.util;

import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http工具类
 * @author heyunlin
 * @version 1.0
 */
public class HttpUtils {

    /**
     * 获取HttpServletRequest对象
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

        if (attributes != null ) {
            return ((ServletRequestAttributes) attributes).getRequest();
        }

        throw new GlobalException(ResponseCode.ERROR, "获取request对象失败");
    }

    /**
     * 获取请求URL
     * @return HttpServletRequest
     */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * 获取HttpServletResponse对象
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

        if (attributes != null ) {
            HttpServletResponse response = ((ServletRequestAttributes) attributes).getResponse();

            if (response != null) {
                // 设置内容类型为json
                response.setContentType("application/json;charset=utf-8");

                return response;
            }
        }

        throw new GlobalException(ResponseCode.ERROR, "获取response对象失败");
    }

    /**
     * 构建HttpServletResponse对象
     * @param resp ServletResponse对象
     * @param responseCode 响应状态码
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse(ServletResponse resp, ResponseCode responseCode) {
        HttpServletResponse response = (HttpServletResponse) resp;

        // 设置内容类型为json
        response.setContentType("application/json;charset=utf-8");
        // 设置响应状态码
        response.setStatus(responseCode.getValue());

        return response;
    }

}