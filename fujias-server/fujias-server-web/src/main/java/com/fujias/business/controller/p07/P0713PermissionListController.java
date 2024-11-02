package com.fujias.business.controller.p07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.p07.P0713PermissionListMapper;
import com.fujias.business.forms.p07.P0713PermissionListForm;
import com.fujias.business.forms.p07.P0713PermissionListItem;
import com.fujias.business.service.p07.P0713PermissionListService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.db.dao.MRolesMapper;
import com.fujias.common.db.entity.MRoles;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.util.BeanUtilsExtends;

import io.netty.util.internal.StringUtil;

/**
 * 角色管理的Controller类
 * 
 * @author han
 *
 */
@Controller
@RequestMapping("/P0713Permission")
public class P0713PermissionListController extends BaseController {

    @Autowired
    private P0713PermissionListMapper p0713PermissionListMapper;

    @Autowired
    private P0713PermissionListService p0713PermissionListService;

    @Autowired
    private MRolesMapper rolesMapper;

    /**
     * 初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0713PermissionListForm formData = new P0713PermissionListForm();
        formData.setDelFlg("0");
        return this.backView("pages/P0713PermissionList", formData);
    }

    /**
     * 初期化处理
     * 
     * @param formData formData
     * @return view
     */

    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0713PermissionListForm formData) {
        if (StringUtil.isNullOrEmpty(formData.getId())) {
            formData.setMode(EditModeTypes.添加.getCode());
        } else {

            MRoles oldData = rolesMapper.selectByPrimaryKey(formData.getId());

            if (oldData == null) {
                formData.setMode(EditModeTypes.添加.getCode());
            } else {
                BeanUtilsExtends.copyProperties(formData, oldData);
                formData.setMode(EditModeTypes.编辑.getCode());
            }

        }
        return this.backView("pages/P0713PermissionEdit", formData);
    }

    /**
     * 获取角色列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getRoless(@RequestBody P0713PermissionListForm formData) {

        List<MRoles> resourceList = p0713PermissionListMapper.getRoles(formData);
        return this.backListData(resourceList);
    }

    /**
     * 删除角色
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/deleteRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteRoless(@RequestBody P0713PermissionListForm formData) {

        if (p0713PermissionListService.deleteRoles(formData)) {
            return this.backUpdateState(StateTypes.SUCCESS);
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
    @RequestMapping(value = "/reuseRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo reuseRoless(@RequestBody P0713PermissionListForm formData) {

        if (p0713PermissionListService.reuseRoles(formData)) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

    /**
     * 获取角色对应的资源列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getRoleResource", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getRoleResource(@RequestBody P0713PermissionListForm formData) {
        List<P0713PermissionListItem> resourceList = p0713PermissionListService.getRoleResource(formData);
        return this.backListData(resourceList);

    }

    /**
     * 更新角色以及可用的资源列表
     *
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/updateRoleResource", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateRoleResource(@RequestBody P0713PermissionListForm formData) {

        boolean updateFlag = false;
        if (EditModeTypes.添加.getCode().equals(formData.getMode())) {
            updateFlag = p0713PermissionListService.addRole(formData);
        } else {
            updateFlag = p0713PermissionListService.updateRole(formData);
        }

        if (updateFlag) {
            return this.backUpdateState(StateTypes.SUCCESS);
        } else {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0010);
        }
    }

}
