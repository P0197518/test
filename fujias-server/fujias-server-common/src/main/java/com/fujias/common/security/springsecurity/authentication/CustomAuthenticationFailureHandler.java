package com.fujias.common.security.springsecurity.authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.exception.CheckErrorException;
import com.fujias.common.util.JsonUtil;

/**
 * ログイン失敗の場合の処理クラスです。
 * 
 * @author 陳強
 *
 */
@Component
public class CustomAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                    throws IOException, ServletException {

        // Ajax請求の場合、状態とエラー情報を返却する
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultInfo resultInfo = new ResultInfo();

        if (exception instanceof CheckErrorException) {
            resultInfo.setStatus(StateTypes.CHECKERROR);
            resultInfo.setCheckMessages(((CheckErrorException) exception).getCheckMessages());
        } else {
            resultInfo.setStatus(StateTypes.ERROR);
            resultInfo.setText(exception.getMessage());
        }

        out.write(JsonUtil.toJson(resultInfo));
        out.flush();
        out.close();

    }

}
