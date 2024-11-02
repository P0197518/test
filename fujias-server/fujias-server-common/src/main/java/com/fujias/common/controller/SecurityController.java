package com.fujias.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * セキュリティの共通コントローラーです。
 * 
 * @author 陳強
 *
 */
@Controller
public class SecurityController {

    /**
     * ローグインされない場合、notLogin画面へ遷移する。
     * 
     * @return View
     */
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {
        return "errorpage/notLogin";
    }

    /**
     * 権限がない場合、notRole画面へ遷移する。
     * 
     * @return View
     */
    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public String notRole() {
        return "errorpage/notRole";
    }

    /**
     * 権限がない場合、notRole画面へ遷移する。
     * 
     * @return View
     */
    @RequestMapping(value = "/exceptionpage", method = RequestMethod.GET)
    public String exception() {
        return "errorpage/error";
    }

    /**
     * 権限がない場合、notRole画面へ遷移する。
     * 
     * @return View
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "errorpage/error";
    }

    /**
     * 権限がない場合、notRole画面へ遷移する。
     * 
     * @return View
     */
    @RequestMapping(value = "/notFound", method = RequestMethod.GET)
    public String notFound() {
        return "errorpage/notFound";
    }

}
