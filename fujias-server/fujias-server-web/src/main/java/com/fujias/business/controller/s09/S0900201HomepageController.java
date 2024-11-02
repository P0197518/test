package com.fujias.business.controller.s09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.s09.S0900201HomepageMapper;
import com.fujias.business.forms.s09.S0900201HomepageForm;
import com.fujias.common.base.BaseController;

/**
 * 订单管理的Controller类
 * 
 * @author fujias
 *
 */
@Controller
@RequestMapping("/S0900201Index")
public class S0900201HomepageController extends BaseController {

    @Autowired
    private S0900201HomepageMapper mapper;

    /**
     * 订单列表查看的初期化
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        S0900201HomepageForm formData = new S0900201HomepageForm();
        return this.backView("pages/homepage", formData);
    }

}
