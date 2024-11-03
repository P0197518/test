package cn.edu.sgu.www.easyui.strategy.login;

import cn.edu.sgu.www.easyui.dto.UserLoginDTO;
import cn.edu.sgu.www.easyui.strategy.Strategy;

/**
 * 登录策略
 * @author heyunlin
 * @version 1.0
 */
public interface LoginStrategy extends Strategy {

    void setLoginDTO(UserLoginDTO loginDTO);

    void doLogin();
}