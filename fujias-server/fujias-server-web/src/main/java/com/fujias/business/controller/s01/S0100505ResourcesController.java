package com.fujias.business.controller.s01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.s01.S0100505ResourcesMapper;
import com.fujias.business.forms.s01.S0100505ResourcesForm;
import com.fujias.business.service.s01.S0100505ResourcesService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.db.entity.MResource;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.exception.BusinessException;

import io.netty.util.internal.StringUtil;

/**
 * リソース管理Controller类
 * 
 * @author wul
 *
 */

@Controller
@RequestMapping("/S0100505Resources")
public class S0100505ResourcesController extends BaseController {

    @Autowired
    private S0100505ResourcesMapper mapper;

    @Autowired
    private S0100505ResourcesService service;

    /**
     * 初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        S0100505ResourcesForm formData = new S0100505ResourcesForm();
        formData.setDelFlg("0");
        return this.backView("pages/S0100505ResourceList", formData);
    }

    /**
     * 获取资源列表
     * 
     * @param formData formData
     * @return String
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public String getList(@RequestBody S0100505ResourcesForm formData) {
        List<S0100505ResourcesForm> resourceList = mapper.selectAll();

        return this.backJsonString(resourceList);
    }

    /**
     * 获取资源列表
     * 
     * @param formData formData
     * @return String
     */
    @RequestMapping(value = "/getAction", method = RequestMethod.POST)
    @ResponseBody
    public String getAction(@RequestBody S0100505ResourcesForm formData) {
        List<S0100505ResourcesForm> resourceList = mapper.getActionByParentId(formData.getId());

        return this.backJsonString(resourceList);
    }

    /**
     * 删除
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestBody S0100505ResourcesForm formData) {
        try {
            service.delete(formData);
        } catch (BusinessException e) {

            return this.backUpdateState(StateTypes.ERROR, e.getMessage());
        }
        return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.IC0002);
    }

    /**
     * 编辑页面的初期化方法
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(S0100505ResourcesForm formData) {
        if (StringUtil.isNullOrEmpty(formData.getId())) {
            formData.setMode(EditModeTypes.添加.getCode());
        } else {

            S0100505ResourcesForm oldData = mapper.selectById(formData.getId());
            if (oldData == null) {
                formData.setMode(EditModeTypes.添加.getCode());
            } else {
                formData = oldData;
                formData.setMode(EditModeTypes.编辑.getCode());
            }
        }
        return this.backView("pages/S0100506ResourceEdit", formData);
    }

    /**
     * 父级资源画面
     * 
     * @return view
     */
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ModelAndView tree() {
        S0100505ResourcesForm formData = new S0100505ResourcesForm();
        formData.setDelFlg("0");
        return this.backView("pages/S0100506ResourceResource", formData);
    }

    /**
     * 权限画面
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/authEdit", method = RequestMethod.GET)
    public ModelAndView auth(S0100505ResourcesForm formData) {
        S0100505ResourcesForm newData = mapper.selectById(formData.getId());
        formData = newData;
        return this.backView("pages/S0100506ResourceMovement", formData);
    }

    /**
     * 新建
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(@RequestBody S0100505ResourcesForm formData) {
        if (formData.getType().equals("2")) {
            if (formData.getParentId().equals("") || (formData.getParentId() == null)) {
                formData.setType("1");
            }

            MResource checkEntity = new MResource();
            checkEntity.setResourceName(formData.getResourceName());
            if (mapper.checkExist(checkEntity) > 0) {
                return this.backUpdateState(StateTypes.ERROR, MessageCodes.E00001, new String[] {"リソース名"});
            }
        }

        if (service.add(formData)) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 更新
     *
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo update(@RequestBody S0100505ResourcesForm formData) {
        if (formData.getType().equals("2")) {
            if (formData.getParentId().equals("") || (formData.getParentId() == null)) {
                formData.setType("1");
            }
        }

        if (service.update(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }
}
