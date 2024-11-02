package com.fujias.business.controller.s01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.s01.S0100403DepartmentsMapper;
import com.fujias.business.forms.s01.S0100403DepartmentsForm;
import com.fujias.business.service.s01.S0100403DepartmentsService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.exception.BusinessException;

import io.netty.util.internal.StringUtil;

/**
 * 部門管理Controller类
 * 
 * @author wul
 *
 */

@Controller
@RequestMapping("/S0100403Departments")
public class S0100403DepartmentsController extends BaseController {

    @Autowired
    private S0100403DepartmentsMapper mapper;

    @Autowired
    private S0100403DepartmentsService service;

    /**
     * 初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        S0100403DepartmentsForm formData = new S0100403DepartmentsForm();
        formData.setDelFlg("0");
        return this.backView("pages/S0100403DepartmentList", formData);
    }

    /**
     * 获取部门列表
     * 
     * @param formData formData
     * @return String
     */
    @RequestMapping(value = "/getTreeList", method = RequestMethod.POST)
    @ResponseBody
    public String getTreeList(@RequestBody S0100403DepartmentsForm formData) {
        List<S0100403DepartmentsForm> resourceList = mapper.selectAll();

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
    public ResultInfo delete(@RequestBody S0100403DepartmentsForm formData) {
        try {
            service.delete(formData);
        } catch (BusinessException e) {
            e.printStackTrace();
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
    public ModelAndView indexEdit(S0100403DepartmentsForm formData) {
        if (StringUtil.isNullOrEmpty(formData.getId())) {
            formData.setMode(EditModeTypes.添加.getCode());
        } else {

            S0100403DepartmentsForm oldData = mapper.selectById(formData.getId());
            if (oldData == null) {
                formData.setMode(EditModeTypes.添加.getCode());
            } else {
                formData = oldData;
                formData.setMode(EditModeTypes.编辑.getCode());
            }
        }
        return this.backView("pages/S0100404DepartmentEdit", formData);
    }

    /**
     * 新建
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(@RequestBody S0100403DepartmentsForm formData) {
        int count = mapper.checkExist(formData);
        if (count > 0) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.E00001, new String[] {"部门编号"});
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
    public ResultInfo update(@RequestBody S0100403DepartmentsForm formData) {
        if (mapper.checkExist(formData) > 0) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.E00001, new String[] {"部门编号"});
        }

        if (service.update(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 部门下拉列表
     * 
     * @return 盘点人
     */
    @RequestMapping(value = "/getDepartmentOptions", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getDepartmentOptions() {
        List<SelectItem> selectItems = mapper.getDepartmentOptions();
        SelectItem sitem = new SelectItem();
        sitem.setText("無");
        sitem.setValue("");
        selectItems.add(0, sitem);
        return this.backListData(selectItems);
    }

    /**
     * 部门下拉列表
     * 
     * @return 盘点人
     */
    @RequestMapping(value = "/getUserDepartmentOptions", method = RequestMethod.POST)
    @ResponseBody
    /* public ResultInfo getUserDepartmentOptions() { */
    public String getUserDepartmentOptions() {
        String json = "[{ \"id\": \"usa\", \"text\": \"冲压\"},{ \"id\": \"cn\", \"text\": \"研磨\"},{ \"id\": \"jp\", \"text\": \"DPS \"},{ \"id\": \"en\", \"text\": \"磨脚\"},{ \"id\": \"de\", \"text\": \"钎焊\"}]";
        return json;
        /*
         * List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); int i = 0; while (i < 15) { Map<String, Object> resourceList1 = new HashMap<String, Object>(); resourceList1.put("text",
         * "钎焊"); list.add(resourceList1); i++; } return this.backListData(list);
         */
        /*
         * List<SelectItem> selectItems = mapper.getUserDepartmentOptions(); return this.backListData(selectItems);
         */
    }
}
