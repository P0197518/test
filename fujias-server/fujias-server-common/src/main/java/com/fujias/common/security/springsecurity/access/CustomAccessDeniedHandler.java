package com.fujias.common.security.springsecurity.access;

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
import com.fujias.common.util.RequestUtil;
import com.fujias.common.util.StringUtil;

/**
 * 没有访问权限时的处理。
 * 
 * @author 陳強
 *
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private String errorPage;

    /**
     * 构造函数。
     */
    public CustomAccessDeniedHandler() {
    }

    /**
     * 构造函数。
     * 
     * @param accessDeniedUrl accessDeniedUrl
     */
    public CustomAccessDeniedHandler(String accessDeniedUrl) {
        this.errorPage = accessDeniedUrl;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
                    throws IOException, ServletException {

        if (RequestUtil.isAjaxRequest(request)) {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setStatus(StateTypes.ERROR);
            // TODO 権限なし場合の処理は修正必要
            resultInfo.setText(accessDeniedException.getMessage());
            out.write(JsonUtil.toJson(resultInfo));
            out.flush();
            out.close();
        } else {
            if (StringUtil.isEmpty(errorPage)) {
                errorPage = "/notRole";
            }
            response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
                            + errorPage);
        }

    }

    /**
     * 権限がない場合の遷移先画面を取得する。
     * 
     * @return 遷移先画面
     */
    public String getErrorPage() {
        return errorPage;
    }

    /**
     * 権限がない場合の遷移先画面を設定する。
     * 
     * @param errorPage 遷移先画面
     */
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}
