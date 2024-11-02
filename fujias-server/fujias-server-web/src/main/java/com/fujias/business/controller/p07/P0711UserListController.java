package com.fujias.business.controller.p07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.p07.P0711UserListMapper;
import com.fujias.business.dao.p07.P0713PermissionListMapper;
import com.fujias.business.forms.p07.P0711UserListForm;
import com.fujias.business.service.p07.P0711UserListService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.exception.BusinessException;

import io.netty.util.internal.StringUtil;

/**
 * 用户管理的Controller类
 * 
 * @author han
 *
 */
@Controller
@RequestMapping("/P0711User")
public class P0711UserListController extends BaseController {

    @Autowired
    private P0711UserListMapper usersMapper;

    @Autowired
    private P0713PermissionListMapper rolesMapper;

    @Autowired
    private P0711UserListService usersService;

    /**
     * 用户管理的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0711UserListForm formData = new P0711UserListForm();
        formData.setDelFlg("0");
        return this.backView("pages/P0711UserList", formData);
    }

    /**
     * 用户编辑的初期化方法
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0711UserListForm formData) {
        if (StringUtil.isNullOrEmpty(formData.getUsername())) {
            formData.setMode(EditModeTypes.添加.getCode());
        } else {

            P0711UserListForm oldData = usersMapper.getSingleById(formData.getUsername());
            if (oldData == null) {
                formData.setMode(EditModeTypes.添加.getCode());
            } else {
                formData = oldData;
                formData.setMode(EditModeTypes.编辑.getCode());
            }
        }
        return this.backView("pages/P0712UserEdit", formData);
    }

    /**
     * 获取角色列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0711UserListForm formData) {

        List<P0711UserListForm> resourceList = usersMapper.getListByPage(formData);
        return this.backListData(resourceList, formData.getPager().getTotalCount());
    }

    /**
     * 获取社员下拉列表
     * 
     * @return 所有社员
     */
    @RequestMapping(value = "/getEmployeeOptions", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getEmployeeOptions() {
        List<SelectItem> selectItems = usersMapper.getEmployeeOptions();
        return this.backListData(selectItems);
    }

    /**
     * 获取角色下拉列表
     * 
     * @return 所有角色
     */
    @RequestMapping(value = "/getRoleOptions", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getRoleOptions() {
        List<SelectItem> selectItems = rolesMapper.getRoleOptions();
        return this.backListData(selectItems);
    }

    /**
     * 新建角色
     * 
     * @param formData formData
     * @return ResultInfo
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)

    @ResponseBody
    public ResultInfo add(@RequestBody P0711UserListForm formData) {

        if (usersMapper.checkExist(formData) > 0) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.E00001, new String[] {"登录用户名或用户编号"});
        }

        try {
            usersService.add(formData);
        } catch (BusinessException e) {
            e.printStackTrace();
            return this.backUpdateState(StateTypes.ERROR, e.getMessage());
        }
        return this.backUpdateState(StateTypes.SUCCESS);
    }

    /**
     * 删除角色
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(@RequestBody P0711UserListForm formData) {
        if (usersService.delete(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.IC0002);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 还原角色
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo recover(@RequestBody P0711UserListForm formData) {
        if (usersService.recover(formData) == 1) {
            return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.IC0003);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 更新角色以及可用的资源列表
     *
     * @param formData formData
     * @return ResultInfo
     */

    @RequestMapping(value = "/update", method = RequestMethod.POST)

    @ResponseBody
    public ResultInfo update(@RequestBody P0711UserListForm formData) {
        if (usersMapper.checkExist(formData) > 0) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.E00001, new String[] {"登录用户名或用户编号"});
        }

        try {
            usersService.update(formData);
        } catch (BusinessException e) {
            return this.backUpdateState(StateTypes.ERROR, e.getMessage());
        }
        return this.backUpdateState(StateTypes.SUCCESS);
    }

}
