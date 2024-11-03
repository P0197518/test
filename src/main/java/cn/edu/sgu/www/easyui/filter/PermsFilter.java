package cn.edu.sgu.www.easyui.filter;

import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.util.HttpUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义PermsFilter过滤器（覆盖shiro的perms过滤器）
 * @author heyunlin
 * @version 1.0
 */
@Slf4j
@WebFilter
public class PermsFilter extends PermissionsAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws IOException {
        boolean accessAllowed = super.isAccessAllowed(req, resp, mappedValue);

        // 打印未授权提示
        if (!accessAllowed) {
            HttpServletRequest request = (HttpServletRequest) req;
            // 得到请求地址
            String requestURI = request.getRequestURI();

            String errorMessage = "正在访问未授权的资源：" + requestURI;

            log.debug(errorMessage);

            ResponseCode responseCode = ResponseCode.UNAUTHORIZED;

            // 获取HttpServletResponse对象
            HttpServletResponse response = HttpUtils.getResponse();

            response.setStatus(responseCode.getValue());
            response.setContentType("application/json;charset=UTF-8");

            // 构建返回对象
            JsonResult<Void> jsonResult = JsonResult.error(responseCode, errorMessage);

            response.getWriter().write(JSON.toJSONString(jsonResult));
        }

        return accessAllowed;
    }

}