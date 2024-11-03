package cn.edu.sgu.www.easyui.util;

import cn.edu.sgu.www.easyui.entity.User;
import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 获取用户信息的工具类
 * @author heyunlin
 * @version 1.0
 */
public class UserUtils {

    /**
     * 得到Subject对象
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登录的用户信息
     * @return User
     */
    public static User getLoginUser() {
        Object object =  getSubject().getPrincipal();

        if (object == null) {
            throw new GlobalException(ResponseCode.BAD_REQUEST, "获取登录信息失败，当前没有用户登录。");
        }

        return (User) object;
    }

    /**
     * 获取登录用户的ID
     * @return String
     */
    public static String getUserId() {
        return getLoginUser().getId();
    }

    /**
     * 获取登录的用户名
     * @return String
     */
    public static String getLoginUsername() {
        return getLoginUser().getUsername();
    }

}