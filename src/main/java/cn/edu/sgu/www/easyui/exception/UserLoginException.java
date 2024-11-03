package cn.edu.sgu.www.easyui.exception;

import cn.edu.sgu.www.easyui.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.AuthenticationException;

/**
 * 用户登录异常
 * @author heyunlin
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginException extends AuthenticationException  {

    private ResponseCode responseCode;

    public UserLoginException(ResponseCode responseCode, String message) {
        super(message);

        setResponseCode(responseCode);
    }

}