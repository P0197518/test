package com.fujias.business.controller.p02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.p02.P0241PickSettingMapper;
import com.fujias.business.forms.p02.P0241PickSettingForm;
import com.fujias.business.service.p02.P0241PickService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;

import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;


import io.netty.util.internal.StringUtil;

/**
 * 计算调整规则设置的Controller类
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/P0241PickSetting")
public class P0241PickSettingController extends BaseController {

    @Autowired
    private P0241PickSettingMapper p0241PickSettingMapper;

    @Autowired
    private P0241PickService p0241PickService;



    /**
     * 计算调整规则设置的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0241PickSettingForm formData = new P0241PickSettingForm();
        return this.backView("pages/P0241PickSetting", formData);
    }

    /**
     * 计算调整规则设置的初期化方法
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0241PickSettingForm formData) {
        if (StringUtil.isNullOrEmpty(formData.getId())) {
            formData.setMode(EditModeTypes.添加.getCode());
        }
        formData.setDepartment("冲压");
        return this.backView("pages/P0241PickSettingEdit", formData);
    }

    /**
     * 获取一览列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0241PickSettingForm formData) {
        List<P0241PickSettingForm> resourceList = p0241PickSettingMapper.getListByPage(formData);
        return this.backListData(resourceList, formData.getPager().getTotalCount());
    }

    /**
     * 调整类型下拉框
     * 
     * @return 盘点人
     */
    @RequestMapping(value = "/getSettingType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSettingType() {
        List<SelectItem> selectItems = p0241PickSettingMapper.getSettingType();
        return this.backListData(selectItems);
    }

    /**
     * 删除
     * 
     * @param formData formData
     * @return ResultInfo
     * @throws 
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestBody P0241PickSettingForm formData) {
        if (p0241PickService.delete(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.IC0002);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 获得全部品目
     * 
     * @return 盘点人
     */
    @RequestMapping(value = "/getItemName", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getItemName() {
        List<SelectItem> selectItems = p0241PickSettingMapper.getItemName();
        return this.backListData(selectItems);
    }

    /**
     * 新建
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    
    @ResponseBody
    public ResultInfo add(@RequestBody P0241PickSettingForm formData) {
        if (p0241PickService.add(formData)) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 获取调整规则列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getSettingList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo userDepartment(@RequestBody P0241PickSettingForm formData) {
        List<P0241PickSettingForm> settingList = p0241PickSettingMapper.getSettingList(formData);
        return this.backListData(settingList,settingList.size());
    }

}
