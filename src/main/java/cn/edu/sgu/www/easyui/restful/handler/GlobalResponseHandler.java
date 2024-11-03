package cn.edu.sgu.www.easyui.restful.handler;

import cn.edu.sgu.www.easyui.config.property.ResponseProperties;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * 统一数据格式返回处理类
 * @author heyunlin
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private final List<String> ignore;

    @Autowired
    public GlobalResponseHandler(ResponseProperties responseProperties) {
        ignore = responseProperties.getIgnore();
    }

    @Override
    public boolean supports(MethodParameter parameter, Class type) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter parameter, MediaType mediaType, Class type
            , ServerHttpRequest request, ServerHttpResponse response) {
        // 返回值类型为JsonResult，则直接返回
        if (body instanceof JsonResult) {
            return body;
        }

        // 忽略的请求地址
        String requestURI = HttpUtils.getRequestURI();

        if (ignore.contains(requestURI)) {
            return body;
        }

        log.debug("接口{}的返回值为：{}", requestURI, body.toString());

        // 将返回值类型修改为JsonResult
        return JsonResult.success(null, body);
    }

}