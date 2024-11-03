package cn.edu.sgu.www.easyui.strategy.login.impl;

import cn.edu.sgu.www.easyui.dto.UserLoginDTO;
import cn.edu.sgu.www.easyui.strategy.login.LoginStrategy;
import cn.edu.sgu.www.easyui.util.UserUtils;

/**
 * @author heyunlin
 * @version 1.0
 */
@Slf4j
@Component
public class LoginStrategyImpl implements LoginStrategy {

    private UserLoginDTO loginDTO;

    @Override
    public void setLoginDTO(UserLoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    @Override
    public void doLogin() {
        // 得到用户名
        String username = loginDTO.getUsername();
        log.debug("用户{}正在登录...", username);

        // shiro登录认证
        UsernamePasswordToken token = new UsernamePasswordToken(username, loginDTO.getPassword());
        Subject subject = UserUtils.getSubject();

        subject.login(token);
        subject.getSession().setTimeout(-1001); // 设置session失效时间：永不超时
    }

}