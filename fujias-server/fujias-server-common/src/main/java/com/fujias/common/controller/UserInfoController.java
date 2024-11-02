package com.fujias.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fujias.common.base.BaseController;
import com.fujias.common.dao.CommonUserMapper;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.form.CommonResourceFunctions;
import com.fujias.common.form.CommonUserResourceForm;

/**
 * Inner画面の遷移用コントローラークラスです。
 * 
 * @author 陳強
 *
 */
@RequestMapping("/user")
@Controller
public class UserInfoController extends BaseController {

    @Autowired
    private CommonUserMapper commonUserMapper;

    /**
     * 画面别的权限按钮列表获取
     * 
     * @param formData formData
     * @return 权限按钮列表
     */
    @RequestMapping(value = "/getUserPageButtons", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getUserPageButtons(@RequestBody CommonUserResourceForm formData) {
        formData.setUserid(this.getLoginUser().getUsername());
        List<CommonResourceFunctions> buttons = commonUserMapper.getUserResourcesByOne(formData);
        return this.backListData(buttons);
    }

}
