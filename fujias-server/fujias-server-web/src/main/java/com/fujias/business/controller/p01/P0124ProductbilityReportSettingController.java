package com.fujias.business.controller.p01;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.p01.P0124ProductbilityReportSettingMapper;
import com.fujias.business.forms.p01.P0124ProductbilityReportSettingControllerForm;
import com.fujias.business.service.p01.P0124ProductbilityReportSettingService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;

import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.exception.BusinessException;

import io.netty.util.internal.StringUtil;

/**
 * 生产性计算设置的Controller类
 * 
 * @author han
 *
 */
@Controller
@RequestMapping("/P0124ProductbilitySetting")
public class P0124ProductbilityReportSettingController extends BaseController {

    @Autowired
    private P0124ProductbilityReportSettingMapper p0124ProductbilityReportSettingMapper;

    @Autowired
    private P0124ProductbilityReportSettingService p0124ProductbilityReportSettingService;



    /**
     * 生产性计算设置的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0124ProductbilityReportSettingControllerForm formData = new P0124ProductbilityReportSettingControllerForm();
        /* formData.setDelFlg("0"); */
        return this.backView("pages/P0124ProductbilityReportSetting", formData);
    }

    /**
     * 生产性计算编辑的初期化方法
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0124ProductbilityReportSettingControllerForm formData) {
        if (StringUtil.isNullOrEmpty(formData.getId())) {
            formData.setMode(EditModeTypes.添加.getCode());
        }
        return this.backView("pages/P0124ProductbilityReportSettingEdit", formData);
    }

    /**
     * 获取列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0124ProductbilityReportSettingControllerForm formData) {
        List<String> settingTypeList = new ArrayList<String>();
        if (formData.getSettingType().equals("true")) {
            settingTypeList.add(new String("0010"));
        } else {
            formData.setSettingType(null);
        }
        if (formData.getGrindingType1().equals("true")) {
            String settingType = "0001";
            settingTypeList.add(settingType);
        } else {
            formData.setGrindingType1(null);
        }
        if (formData.getGrindingType2().equals("true")) {
            String settingType = "0002";
            settingTypeList.add(settingType);
        } else {
            formData.setGrindingType2(null);
        } 
        if (formData.getGrindingType3().equals("true")) {
            String settingType = "0003";
            settingTypeList.add(settingType);
        } else {
            formData.setGrindingType3(null);
        }
        if (formData.getGrindingType4().equals("true")) {
            String settingType = "0004";
            settingTypeList.add(settingType);
        }  else {
            formData.setGrindingType4(null);
        }
        formData.setSettingTypeList(settingTypeList);
        List<P0124ProductbilityReportSettingControllerForm> resourceList = p0124ProductbilityReportSettingMapper.getListByPage(formData);
        return this.backListData(resourceList, formData.getPager().getTotalCount());
    }

    /**
     * 设置类型下拉框
     * 
     * @return 
     */
    @RequestMapping(value = "/getSettingType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSettingType() {
        List<SelectItem> selectItems = p0124ProductbilityReportSettingMapper.getSettingType();
        return this.backListData(selectItems);
    }
    
    
    /**
     * 表格设置类型下拉框
     * 
     * @return 
     */
    @RequestMapping(value = "/getSettingGrid", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSettingGrid() {
        List<SelectItem> selectItems = p0124ProductbilityReportSettingMapper.getSettingGrid();
        return this.backListData(selectItems);
    }
    /**
     * 获取生产性设置列表
     * 
     * @return 
     */
    @RequestMapping(value = "/getSettingList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSettingList(@RequestBody P0124ProductbilityReportSettingControllerForm formData) {
        List<P0124ProductbilityReportSettingControllerForm> settingList = p0124ProductbilityReportSettingMapper.getSettingList(formData);
        return this.backListData(settingList,settingList.size());
    }
    
    /**
     * 查看是否存在
     * 
     * @return 
     */
    @RequestMapping(value = "/getSettingExist", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSettingExist(@RequestBody P0124ProductbilityReportSettingControllerForm formData) {
        if (formData.getSettingType().equals("冲压单独计算工序设置")) {
            formData.setSettingType("0010");
        } else if (formData.getSettingType().equals("研磨工序分类设置")) {
            formData.setSettingType("0011");
        }
        List<P0124ProductbilityReportSettingControllerForm> settingList = p0124ProductbilityReportSettingMapper.getSettingExist(formData);
        if (settingList.size() > 0) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.FAIL);
        }

    }

    /**
     * 删除
     * 
     * @param formData formData
     * @return ResultInfo
     * @throws BusinessException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestBody P0124ProductbilityReportSettingControllerForm formData) {
        if (p0124ProductbilityReportSettingService.delete(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.IC0002);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 新建
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(@RequestBody P0124ProductbilityReportSettingControllerForm formData) {
        if (p0124ProductbilityReportSettingService.add(formData)) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

}
