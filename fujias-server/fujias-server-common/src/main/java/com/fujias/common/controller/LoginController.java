package com.fujias.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.common.base.BaseController;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.form.LoginForm;

/**
 * SYANAI001Controller
 * 
 * @author 陳強
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    /**
     * Index
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        LoginForm formData = new LoginForm();
        return this.backView("common/login", formData);
    }

    /**
     * login
     * 
     * @param userInfo userInfo
     * @param error error
     * @return login
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo login(@Validated @RequestBody LoginUser userInfo, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            return this.backUpdateState(StateTypes.ERROR, error);
        } else {
            return this.backUpdateState(StateTypes.SUCCESS);
        }
    }

    /**
     * ログアウトを行う。
     * 
     * @param request request
     * @param response response
     * @return グアウト結果
     */
    @RequestMapping(value = "/dologout", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return this.backUpdateState(StateTypes.SUCCESS);
    }

    /**
     * Index
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "/pdaCheckLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo pdaCheckLogin() {
        return this.backUpdateState(StateTypes.SUCCESS);
    }

}
