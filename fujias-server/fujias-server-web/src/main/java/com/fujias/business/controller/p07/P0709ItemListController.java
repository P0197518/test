package com.fujias.business.controller.p07;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.p07.P0709ItemListMapper;
import com.fujias.business.forms.p07.P0709ItemListExcleForm;
import com.fujias.business.forms.p07.P0709ItemListForm;
import com.fujias.business.service.p07.P0709ItemListService;
import com.fujias.common.base.BaseController;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.util.JsonUtil;
import com.fujias.common.util.poi.CommonDownloadParam;
import com.fujias.common.util.poi.PoiUtil;

/**
 * 品目管理的Controller类
 * 
 * @author han
 *
 */
@Controller
@RequestMapping("/P0709Item")
public class P0709ItemListController extends BaseController {
    @Autowired
    private P0709ItemListMapper p0709ItemListMapper;
    
    @Autowired
    private P0709ItemListService p0709ItemListService;

    /**
     * 品目管理的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0709ItemListForm formData = new P0709ItemListForm();
        return this.backView("pages/P0709ItemList", formData);
    }

    /**
     * 品目管理获取列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0709ItemListForm formData) {
        List<P0709ItemListForm> resourceList = p0709ItemListMapper.getListByPage(formData);
        return this.backListData(resourceList, formData.getPager().getTotalCount());
    }

    /**
     * 部门下拉框
     * 
     * @return 盘点人
     */
    @RequestMapping(value = "/getDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getDepartment() {
        List<SelectItem> selectItems = p0709ItemListMapper.getDepartment();
        return this.backListData(selectItems);
    }

    /**
     * 分类下拉框
     * 
     * @return 盘点人
     */
    @RequestMapping(value = "/getClassification", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getClassification() {
        List<SelectItem> selectItems = p0709ItemListMapper.getClassification();
        return this.backListData(selectItems);
    }
    
    /**
     * 导出
     * 
     * @param formData formData
     * @return ResultInfo
     * @throws IOException IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(CommonDownloadParam param, HttpServletRequest request, HttpServletResponse response) throws IOException {

        P0709ItemListForm formData = JsonUtil.toObject(param.getSubmitData(), P0709ItemListForm.class);
        PoiUtil.downloadBigData("品目数据", "品目数据", P0709ItemListExcleForm.class, p0709ItemListService, formData, request, response);
    }

}
