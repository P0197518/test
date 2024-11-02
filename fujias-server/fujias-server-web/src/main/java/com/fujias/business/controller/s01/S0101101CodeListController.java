package com.fujias.business.controller.s01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.s01.S0101101CodeListMapper;
import com.fujias.business.forms.s01.S0101101CodeListForm;
import com.fujias.business.service.s01.S0101101CodeListService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.service.NoCreator;
import com.fujias.common.service.NoCreator.NoType;

import io.netty.util.internal.StringUtil;

/**
 * 区分管理的Controller类
 * 
 * @author 薛秋凤
 *
 */
@Controller
@RequestMapping("/S0101101CodeList")
public class S0101101CodeListController extends BaseController {

    @Autowired
    private S0101101CodeListMapper s0101101CodeListMapper;

    @Autowired
    private S0101101CodeListService s0101101CodeListService;

    /**
     * 区分管理的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        S0101101CodeListForm formData = new S0101101CodeListForm();
        formData.setDelFlg("0");
        return this.backView("pages/S0101101CodeList", formData);
    }

    /**
     * 获取区分code下拉列表
     * 
     * @return 区分code
     */

    @RequestMapping(value = "/getKbntypecd", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getKbntypecd() {
        List<S0101101CodeListForm> selectItems = s0101101CodeListMapper.getKbntypecd();
        return this.backListData(selectItems);
    }

    /**
     * 获取codeTypeCd获取下拉列表
     * 
     * @param formData formData
     * @return 区分code
     */

    @RequestMapping(value = "/getKbnByCodeTypeCd", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getKbnByCodeTypeCd(@RequestBody S0101101CodeListForm formData) {
        List<S0101101CodeListForm> selectItems = s0101101CodeListMapper.getKbnByCodeTypeCd(formData);
        return this.backListData(selectItems);
    }

    /**
     * 获取codeTypeCd获取下拉列表
     * 
     * @param formData formData
     * @return 区分code
     */

    @RequestMapping(value = "/getProcessList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getProcessList(@RequestBody S0101101CodeListForm formData) {
        List<S0101101CodeListForm> selectItems = s0101101CodeListMapper.getProcessList();
        return this.backListData(selectItems);
    }

    /**
     * 区分编辑的初期化方法
     * 
     * @param formData formData
     * @return view
     */

    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(S0101101CodeListForm formData) {

        if (StringUtil.isNullOrEmpty(formData.getCodeCd())) {
            formData.setMode(EditModeTypes.添加.getCode());
        } else {

            S0101101CodeListForm oldData = s0101101CodeListMapper.getSingleById(formData);
            if (oldData == null) {
                formData.setMode(EditModeTypes.添加.getCode());
            } else {
                formData = oldData;
                formData.setMode(EditModeTypes.编辑.getCode());
            }
        }
        return this.backView("pages/S0101102CodeEdit", formData);
    }

    /**
     * 获取区分列表
     * 
     * @return ResultInfo
     */
    @RequestMapping(value = "/getNewCodeCd", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getNewCodeCd() {

        String newCodeCd = NoCreator.getSaibanNo4(NoType.参数分类编号);
        return this.backSingleData(newCodeCd);
    }

    /**
     * 获取区分列表
     * 
     * @param formData formData
     * @return ResultInfo
     */

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody S0101101CodeListForm formData) {

        List<S0101101CodeListForm> resourceList = s0101101CodeListMapper.getListByPage(formData);
        // 循环报价单的集合
        for (int i = 0; i < resourceList.size(); i++) {
            if ("1".equals(resourceList.get(i).getDelFlg())) {
                resourceList.get(i).setDelflgt("已删除");
            } else {
                resourceList.get(i).setDelflgt("未删除");
            }
        }

        return this.backListData(resourceList, formData.getPager().getTotalCount());
    }

    /**
     * 新建区分
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(@RequestBody S0101101CodeListForm formData) {
        if (s0101101CodeListMapper.checkExist(formData) > 0) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.E00001, new String[] {"code编号"});
        }
        if (s0101101CodeListService.add(formData)) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 删除区分
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestBody S0101101CodeListForm formData) {
        if (s0101101CodeListService.delete(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.IC0002);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 还原区分
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo reuse(@RequestBody S0101101CodeListForm formData) {

        if (s0101101CodeListService.reuse(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 更新区分以及可用的资源列表
     *
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo update(@RequestBody S0101101CodeListForm formData) {
        if (s0101101CodeListService.update(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

}
