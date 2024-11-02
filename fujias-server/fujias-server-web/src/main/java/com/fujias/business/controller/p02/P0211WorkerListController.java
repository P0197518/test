package com.fujias.business.controller.p02;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.dao.p02.P0211WorkerListMapper;
import com.fujias.business.forms.p02.P0211WorkerListForm;
import com.fujias.common.base.BaseController;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.util.JsonUtil;

import com.fujias.common.util.poi.CommonDownloadParam;
import com.fujias.common.util.poi.PoiUtil;

/**
 * 工作人员设置的Controller类
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/P0211WorkerList")
public class P0211WorkerListController extends BaseController {

    @Autowired
    private P0211WorkerListMapper workerListMapper;

    /**
     * 工作人员设置的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0211WorkerListForm formData = new P0211WorkerListForm();
        return this.backView("pages/P0211WorkerList", formData);
    }

    /**
     * 获取工作人员设置列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0211WorkerListForm formData) {
        List<P0211WorkerListForm> workerList = workerListMapper.getListByPage(formData);
        return this.backListData(workerList, formData.getPager().getTotalCount());
    }

    /**
     * 获取部门下拉列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo departmentList(@RequestBody P0211WorkerListForm formData) {
        List<P0211WorkerListForm> departList = workerListMapper.departList(formData.getCodeCd());
        return this.backListData(departList);
    }

    /**
     * 获取当前部门
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getUserDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo userDepartment(@RequestBody P0211WorkerListForm formData) {


        return this.backSingleData(formData.getDepartmentId());
    }

    /**
     * 导出工作人员设置列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    @ResponseBody
    public void exportData(CommonDownloadParam formData, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 接受画面传来的条件
        P0211WorkerListForm param = JsonUtil.toObject(formData.getSubmitData(), P0211WorkerListForm.class);
        List<P0211WorkerListForm> workList = workerListMapper.exportList(param);
        Map<String, List<?>> listData = new HashMap<String, List<?>>();
        listData.put("workList", workList);
        Map<String, Object> headerData = new HashMap<String, Object>();
        headerData.put("allCount", 1232131);
        String[] sheetNameArray = {"工作人员"};

        PoiUtil.downloadByTemplate("工作人员列表", "P0211Workers.xlsx", headerData, listData,sheetNameArray, request, response);

    }

}
