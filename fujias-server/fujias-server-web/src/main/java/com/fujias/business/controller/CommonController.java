package com.fujias.business.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fujias.business.common.dao.CommonDaoMapper;
import com.fujias.business.common.entity.CodeEntity;
import com.fujias.business.common.form.CommonCustomForm;
import com.fujias.business.common.form.CommonForm;
import com.fujias.business.common.form.CommonSupplierForm;
import com.fujias.business.entity.dms002001.SelectDataEntity;
import com.fujias.business.forms.CommonSearchCondationForm;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.db.dao.MSearchCondationMapper;
import com.fujias.common.db.entity.MSearchCondation;
import com.fujias.common.db.entity.MSearchCondationKey;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.BeanUtilsExtends;
import com.fujias.common.util.JsonUtil;
import com.fujias.common.util.StringUtil;

/**
 * 系统共通action
 * 
 * @author chenqiang
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Autowired
    private CommonDaoMapper commonDaoMapper;

    @Autowired
    private MSearchCondationMapper searchCondationMapper;

    /**
     * 获取区分信息
     * 
     * @param formData formData
     * 
     * @return view
     */
    @RequestMapping(value = "/getKbn", method = RequestMethod.POST)
    @ResponseBody
    public String getKbn(CodeEntity formData) throws Exception {
        if ("16".equals(formData.getCodeTypeCd())) {

            SelectItem item1 = new SelectItem();
            item1.setValue("0");
            item1.setText("使用中");
            SelectItem item2 = new SelectItem();
            item2.setValue("1");
            item2.setText("已删除");

            List<SelectItem> list = new ArrayList<SelectItem>();
            list.add(item1);
            list.add(item2);
            return this.backJsonString(list);
        } else if ("39".equals(formData.getCodeTypeCd())) {
            SelectItem item1 = new SelectItem();
            item1.setValue("1");
            item1.setText("显示");
            SelectItem item2 = new SelectItem();
            item2.setValue("0");
            item2.setText("隐藏");

            List<SelectItem> list = new ArrayList<SelectItem>();
            list.add(item1);
            list.add(item2);
            return this.backJsonString(list);
        } else {
            List<SelectItem> list = commonDaoMapper.getCodeList(formData);
            return this.backJsonString(list);
        }

    }

    /**
     * 根据用户获取部门下拉框
     * 
     * @param formData formData
     * 
     * @return 部门列表
     */
    @RequestMapping(value = "/getDepList", method = RequestMethod.POST)
    @ResponseBody
    public String getDepList(CodeEntity formData) throws Exception {
        LoginUser userInfo = this.getLoginUser();
        if (!userInfo.getRoles().contains("adminrole")) {
            formData.setUserId(userInfo.getUsername());
        }
        List<SelectItem> list = commonDaoMapper.getDepListByUserId(formData);
        return this.backJsonString(list);
    }

    /**
     * 获取材质信息
     * 
     * @param formData formData
     * 
     * @return view
     */
    @RequestMapping(value = "/getTexture", method = RequestMethod.POST)
    @ResponseBody
    public String getTexture(CodeEntity formData) throws Exception {
        List<SelectItem> list = commonDaoMapper.getTextureList();
        return this.backJsonString(list);
    }

    /**
     * 获取用户信息列表
     * 
     * @param request request
     * @return 用户信息
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public String getUserList(HttpServletRequest request) {

        List<SelectItem> resutlData = commonDaoMapper.selectUsers();
        return this.backJsonString(resutlData);

    }

    /**
     * 获取画面的检索条件
     * 
     * @param formData formData
     * @return 用户信息
     */
    @RequestMapping(value = "/saveSearchCondation", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo saveSearchCondation(@RequestBody CommonSearchCondationForm formData) throws BusinessException {
        // 问合数据更新订单id
        MSearchCondation quotationEntity = new MSearchCondation();
        BeanUtilsExtends.copyProperties(quotationEntity, formData);
        quotationEntity.setUserId(this.getLoginUser().getUsername());

        MSearchCondationKey key = new MSearchCondationKey();
        key.setPageId(formData.getPageId());
        key.setUserId(this.getLoginUser().getUsername());
        if (searchCondationMapper.selectByPrimaryKey(key) == null) {
            searchCondationMapper.insertSelective(quotationEntity);
        } else {
            searchCondationMapper.updateByPrimaryKeySelective(quotationEntity);
        }

        return this.backUpdateState(StateTypes.SUCCESS);
    }

    /**
     * 获取该画面的检索条件
     * 
     * @param formData formData
     * @return data
     */
    @RequestMapping(value = "/getSearchCondation", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSearchCondation(@RequestBody CommonSearchCondationForm formData) {
        MSearchCondationKey key = new MSearchCondationKey();
        key.setPageId(formData.getPageId());
        key.setUserId(this.getLoginUser().getUsername());
        MSearchCondation getData = searchCondationMapper.selectByPrimaryKey(key);
        if (getData != null) {
            formData.setSearchCondation(getData.getSearchCondation());
        }
        return this.backSingleData(formData);
    }

    /**
     * 根据图号获取下拉物料列表
     * 
     * @param request request
     * @return 製品情報
     */
    @RequestMapping(value = "/getMaterialsByDarwingNo", method = RequestMethod.POST)
    @ResponseBody
    public String getMaterialsByDarwingNo(HttpServletRequest request) {

        Object delflg = null;
        Object materialType = null;
        String requestData = "";

        Iterator<String> iterator = request.getParameterMap().keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            if ("delFlg".equals(key)) {
                delflg = request.getParameter(key);
            } else if ("materialType".equals(key)) {
                materialType = request.getParameter(key);
            } else {
                requestData = key;
            }
        }

        CommonForm formData = JsonUtil.toObject(requestData, CommonForm.class);
        if (StringUtil.isEmpty(formData.getKey()) || formData.getKey().length() < 2) {
            return this.backJsonString(new ArrayList<SelectItem>());
        }

        if (delflg != null) {
            formData.setDelFlg(Boolean.parseBoolean(delflg.toString()));
        }
        if (materialType != null) {
            formData.setMaterialType(materialType.toString());
        }

        List<SelectItem> resutlData = commonDaoMapper.getAutoMaterialsByDrawingNo(formData);
        return this.backJsonString(resutlData);

    }

    /**
     * 根据品番获取下拉物料列表
     * 
     * @param request request
     * @return 製品情報
     */
    @RequestMapping(value = "/getMaterialsByProductNo", method = RequestMethod.POST)
    @ResponseBody
    public String getMaterialsByProductNo(HttpServletRequest request) {

        Object delflg = null;
        String requestData = "";

        Iterator<String> iterator = request.getParameterMap().keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            if ("delFlg".equals(key)) {
                delflg = request.getParameter(key);
            } else {
                requestData = key;
            }
        }

        CommonForm formData = JsonUtil.toObject(requestData, CommonForm.class);
        if (StringUtil.isEmpty(formData.getKey()) || formData.getKey().length() < 2) {
            return this.backJsonString(new ArrayList<SelectItem>());
        }

        if (delflg != null) {
            formData.setDelFlg(Boolean.parseBoolean(delflg.toString()));
        }

        List<SelectItem> resutlData = commonDaoMapper.getAutoMaterialsByProductNo(formData);
        return this.backJsonString(resutlData);

    }

    /**
     * 获取客户信息
     * 
     * @param formData formData
     * @return data
     */
    @RequestMapping(value = "/getCustomInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getCustomInfo(@RequestBody CommonCustomForm formData) {
        CommonCustomForm result = commonDaoMapper.getCustomInfoById(formData.getCustomNo());
        if (result != null) {
            List<SelectItem> list = commonDaoMapper.getAddressById(formData);
            result.setAddress(list);
        }
        return this.backSingleData(result);
    }

    /**
     * 获取供应商信息
     * 
     * @param formData formData
     * @return data
     */
    @RequestMapping(value = "/getSupplierInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSupplierInfo(@RequestBody CommonSupplierForm formData) {
        CommonSupplierForm result = commonDaoMapper.getSupplierInfoById(formData.getSupplierId());
        return this.backSingleData(result);
    }

    /**
     * 获取供应商信息
     * 
     * @param formData formData
     * 
     * @return view
     */
    @RequestMapping(value = "/getSupplierInfoData", method = RequestMethod.POST)
    @ResponseBody
    public String getSupplierInfoData(CodeEntity formData) throws Exception {
        List<SelectItem> list = commonDaoMapper.getSupplierInfoData();
        return this.backJsonString(list);
    }

    /**
     * 获取用户信息列表
     * 
     * @param request request
     * @return 用户信息
     */
    @RequestMapping(value = "/getSelectData", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getSelectData(HttpServletRequest request) {

        List<SelectDataEntity> resutlData = commonDaoMapper.selectData();
        return this.backListData(resutlData);

    }

}
