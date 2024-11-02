package com.fujias.common.security.springsecurity.token;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.util.JsonUtil;

/**
 * 没有访问权限时的处理。
 * 
 * @author 陳強
 *
 */
@Component
public class CustomTokenErrorHandler implements AccessDeniedHandler {

    /**
     * 构造函数。
     */
    public CustomTokenErrorHandler() {
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
                    throws IOException, ServletException {

        // 没有权限时，通过json返回错误数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setStatus(StateTypes.ERROR);
        resultInfo.setText(accessDeniedException.getMessage());
        out.write(JsonUtil.toJson(resultInfo));
        out.flush();
        out.close();
    }
}
