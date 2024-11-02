package com.fujias.business.controller.s09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.s09.S0900101PasswordMapper;
import com.fujias.business.forms.s09.S0900101PasswordForm;
import com.fujias.business.service.s09.S0900101PasswordService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.util.Sha256Util;

/**
 * SYANAI001Controller
 * 
 * @author 陳強
 *
 */
@Controller
@RequestMapping("/S0900101Password")
public class S0900101PasswordController extends BaseController {

    @Autowired
    private S0900101PasswordService service;

    @Autowired
    private S0900101PasswordMapper mapper;

    /**
     * Index
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        S0900101PasswordForm formData = new S0900101PasswordForm();
        return this.backView("pages/S0900101Password", formData);
    }

    /**
     * 密码更新。
     * 
     * @param formData formData
     * @return グアウト結果
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo changePassword(@RequestBody S0900101PasswordForm formData) {

        formData.setOldpassword(Sha256Util.encode(formData.getOldpassword()));
        formData.setUsername(this.getLoginUser().getUsername());
        int getCount = mapper.checkPassword(formData);
        if (getCount == 0) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }

        service.update(formData, this.getLoginUser().getUsername());
        return this.backUpdateState(StateTypes.SUCCESS);
    }

}
