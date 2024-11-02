package com.fujias.business.controller.p01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.business.common.form.FileUploadForm;
import com.fujias.business.dao.p01.P0123DailyReportMapper;
import com.fujias.business.forms.p01.P0123BadImportForm;
import com.fujias.business.forms.p01.P0123CompareDiffForm;

import com.fujias.business.forms.p01.P0123DailyReportExcelForm;
import com.fujias.business.forms.p01.P0123DailyReportForm;
import com.fujias.business.forms.p02.P0221WorkTimeForm;

import com.fujias.business.service.p01.P0123DailyReportService;

import com.fujias.common.base.BaseController;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.CheckMessage;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.util.CheckErrorUtil;
import com.fujias.common.util.JsonUtil;
import com.fujias.common.util.poi.CommonDownloadParam;
import com.fujias.common.util.poi.PoiUtil;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * 生产日报
 * 
 * @author wang
 *
 */
@Controller
@RequestMapping("/P0123DailyReport")
public class P0123DailyReportController extends BaseController {

    @Autowired
    private P0123DailyReportMapper p0123DailyReportMapper;


    @Autowired
    private P0123DailyReportService p0123DailyReportService;

    /**
     * 生产日报的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0123DailyReportForm formData = new P0123DailyReportForm();
        /* formData.setDelflg("0"); */
        return this.backView("pages/P0123DailyReport", formData);
    }

    /**
     * 入库比较的初期化方法
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0123CompareDiffForm formData) {
        return this.backView("pages/P0123DailyReportList", formData);
    }



    /**
     * 获取列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0123DailyReportForm formData) {
        List<P0123DailyReportForm> resourceList = p0123DailyReportMapper.getListByPage(formData);
        return this.backListData(resourceList, formData.getPager().getTotalCount());
    }

    /**
     * 工序下拉列表
     * 
     * @return 工序列表
     */
    @RequestMapping(value = "/getTechnicsCD", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getTechnicsCD() {
        List<SelectItem> selectItems = p0123DailyReportMapper.getTechnicsCD();
        return this.backListData(selectItems);
    }
    
    /**
     * 部门下拉列表
     * 
     * @return 
     */
    @RequestMapping(value = "/getDepartList", method = RequestMethod.POST)
    @ResponseBody
    public String getDepartList() {
        List<SelectItem> selectItems = p0123DailyReportMapper.getDepartList();
        return this.backJsonString(selectItems);
    }

    /**
     * 获取列表
     * 
     * @param formData formData
     * @return 
     * @throws IOException IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(CommonDownloadParam param, HttpServletRequest request, HttpServletResponse response) throws IOException {

        P0123DailyReportForm formData = JsonUtil.toObject(param.getSubmitData(), P0123DailyReportForm.class);
        PoiUtil.downloadBigData("日报数据", "日报数据", P0123DailyReportExcelForm.class, p0123DailyReportService, formData, request, response);
    }

    /**
     * 获取列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/compareDiff", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo compareDiff(@RequestBody P0123CompareDiffForm formData) {
        List<P0123CompareDiffForm> resourceList = p0123DailyReportService.compareDiff(formData);
        return this.backListData(resourceList,resourceList.size());
    }
    
    /**
     * 不良导入
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/badImport", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0221WorkTimeForm formData) {

        return this.backView("pages/P0123BadImport", formData);
    }
    
    /**
     * 上传数据
     * 
     * @param fileData picture
     * @param formData formData
     * @return ResultInfo
     * @throws Exception Exception
     * @throws IOException IOException
     */
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo importData(@RequestParam("importData") MultipartFile fileData, FileUploadForm formData) throws IOException, Exception {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setStartSheetIndex(0);

        List<P0123BadImportForm> listData = ExcelImportUtil.importExcel(fileData.getInputStream(), P0123BadImportForm.class, params);

        List<CheckMessage> errors = new ArrayList<CheckMessage>();
        for (int i = 0; i < listData.size(); i++) {

            List<CheckMessage> itemErrors = CheckErrorUtil.validateForm(listData.get(i));

            if (!itemErrors.isEmpty()) {
                for (CheckMessage item : itemErrors) {
                    item.setRow(String.valueOf(i + 1));
                }
                errors.addAll(itemErrors);
            }
            String itemName = listData.get(i).getItemName().trim();
            P0123DailyReportForm dailyReportForm = p0123DailyReportMapper.badExist(itemName);
            if (dailyReportForm == null) {
                CheckMessage checkDepart = new CheckMessage();
                checkDepart.setField("itemName");
                checkDepart.setCode("EC0110");
                List<String> itemList = new ArrayList<String>();
                itemList.add(itemName);
                checkDepart.setArgs(itemList);
                checkDepart.setRow(Integer.toString(i+3));
                errors.add(checkDepart);
            }

       }


        if (!errors.isEmpty()) {
            return this.backCheckError(errors);
        } else {
            if (p0123DailyReportService.add(listData)) {
                return this.backUpdateState(StateTypes.SUCCESS);
            } else {
                return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0018);
            }
            
        }

    }
}
