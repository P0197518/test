package com.fujias.business.controller.p01;

import java.io.IOException;
import java.util.ArrayList;
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

import com.fujias.business.common.dao.CommonDaoMapper;
import com.fujias.business.constants.DepartmentTypes;
import com.fujias.business.dao.p01.P0122ProductbilityReportMapper;
import com.fujias.business.forms.p01.P0122ProductbilityExportForm;
import com.fujias.business.forms.p01.P0122ProductbilityReportForm;
import com.fujias.business.service.p01.P0122ProductbilityReportService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.EditModeTypes;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.db.entity.T0103ProductBilityWorkers;
import com.fujias.common.db.entity.T0104ProductBilityItems;
import com.fujias.common.db.entity.T0602BatchErrorReport;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.JsonUtil;
import com.fujias.common.util.poi.CommonDownloadParam;
import com.fujias.common.util.poi.PoiUtil;

/**
 * 生产性计算
 * 
 * @author wangbaoxin
 *
 */
@Controller
@RequestMapping("/P0122ProductbilityReport")
public class P0122ProductbilityReportController extends BaseController {

    @Autowired
    private P0122ProductbilityReportMapper p0122ProductbilityReportMapper;
    @Autowired
    private P0122ProductbilityReportService service;
    @Autowired
    private CommonDaoMapper commonDaoMapper;

    /**
     * 生产性计算的初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0122ProductbilityReportForm formData = new P0122ProductbilityReportForm();
        formData.setDepid(p0122ProductbilityReportMapper.getDepidByUser(this.getLoginUser().getUsername()));
        formData.setYearmonth(DateUtil.formatDateToString(DateUtil.getNowDate(), DateUtil.DATE_FORMAT7));
        return this.backView("pages/P0122ProductbilityReport", formData);
    }

    /**
     * 获取生产性计算列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0122ProductbilityReportForm formData) {
        List<P0122ProductbilityReportForm> batchlogList = p0122ProductbilityReportMapper.getBatchlogListByPage(formData);
        return this.backListData(batchlogList, formData.getPager().getTotalCount());
    }

    /**
     * 生产性计算执行条件选择页面跳转
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/runCaclEdit", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView runCaclEdit(P0122ProductbilityReportForm formData) {
        formData.setMode(EditModeTypes.添加.getCode());
        formData.setRunuser(this.getLoginUser().getUsername());
        formData.setDepid(p0122ProductbilityReportMapper.getDepidByUser(this.getLoginUser().getUsername()));
        formData.setYearmonth(DateUtil.getLastMonthFirstDayString(DateUtil.DATE_FORMAT7));
        formData.setAdminRole(this.getLoginUser().getRoles().contains("adminrole"));
        return this.backView("pages/P0111RunCaclProductbility", formData);
    }

    /**
     * 生产性执行前检查
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/checkRun", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo checkRun(@RequestBody P0122ProductbilityReportForm formData) {
        int resulf = service.checkRun(formData);
        if (resulf == 1) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0100);
        } else if (resulf == 2) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0101);
        } else if (resulf == 3) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0102);
        } else if (resulf == 4) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0103);
        } else if (resulf == 5) {
            return this.backUpdateState(StateTypes.SUCCESS, MessageCodes.Q00028);
        } else if (resulf == 6) {
            return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0109);
        }
        return this.backUpdateState(StateTypes.SUCCESS);
    }

    /**
     * 生产性执行
     * 
     * @param formData formData
     * @return view
     * @throws BusinessException 异常
     */
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo run(@RequestBody P0122ProductbilityReportForm formData) throws BusinessException {
        p0122ProductbilityReportMapper.updBatchLogDelFlg(formData);
        service.run(this.getLoginUser().getUsername(), formData);
        return this.backUpdateState(StateTypes.SUCCESS);
    }

    /**
     * 结算结果查看
     * 
     * @param formData formData
     * @return 计算结果
     */
    @RequestMapping(value = "/indexResult", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView indexResult(P0122ProductbilityReportForm formData) {
        P0122ProductbilityReportForm from = p0122ProductbilityReportMapper.getAllProductBility(formData);
        from.setDepid(formData.getDepid());
        return this.backView("pages/P011201RunSuccessReport", from);
    }

    /**
     * 获取间接人员信息列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getIndirectList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getIndirectList(@RequestBody P0122ProductbilityReportForm formData) {
        List<T0103ProductBilityWorkers> indirectList = p0122ProductbilityReportMapper.getProductWorkersListByPage(formData);
        return this.backListData(indirectList, formData.getPager().getTotalCount());
    }

    /**
     * 获取品目别生产性列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getItemsList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getItemsList(@RequestBody P0122ProductbilityReportForm formData) {
        List<T0104ProductBilityItems> itemsList = p0122ProductbilityReportMapper.getProductItemsListByPage(formData);
        return this.backListData(itemsList, formData.getPager().getTotalCount());
    }

    /**
     * 计算错误查看
     * 
     * @param formData formData
     * @return 错误列表
     */
    @RequestMapping(value = "/indexError", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView indexError(P0122ProductbilityReportForm formData) {
        P0122ProductbilityReportForm error = p0122ProductbilityReportMapper.getBatchlog(formData);
        return this.backView("pages/P011202RunErrorReport", error);
    }

    /**
     * 获取执行错误列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getRunErrorList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getRunErrorList(@RequestBody P0122ProductbilityReportForm formData) {
        List<T0602BatchErrorReport> itemsList = p0122ProductbilityReportMapper.getRunErrorListByPage(formData);
        return this.backListData(itemsList, formData.getPager().getTotalCount());
    }

    /**
     * 年度生产性导出跳转
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexExportYear", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView indexExportYear(P0122ProductbilityReportForm formData) {
        P0122ProductbilityReportForm entity = new P0122ProductbilityReportForm();
        entity.setMode("Year");
        return this.backView("pages/P011203ExportReport", entity);
    }

    /**
     * 月度生产性导出跳转
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexExportMonth", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView indexExportMonth(P0122ProductbilityReportForm formData) {
        P0122ProductbilityReportForm entity = new P0122ProductbilityReportForm();
        entity.setMode("Month");
        return this.backView("pages/P011203ExportReport", entity);
    }

    /**
     * 月度生产性导出
     * 
     * @param param param
     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    @RequestMapping(value = "/exportMonth", method = RequestMethod.GET)
    public void exportMonth(CommonDownloadParam param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取导出参数
        P0122ProductbilityReportForm paramEntity = JsonUtil.toObject(param.getSubmitData(), P0122ProductbilityReportForm.class);
        // Excel头部数据
        Map<String, Object> headerData = new HashMap<String, Object>();
        // Excel列表数据
        Map<String, List<?>> listData = new HashMap<String, List<?>>();
        // 生产性基本信息
        P0122ProductbilityReportForm header = new P0122ProductbilityReportForm();
        // 间接人员列表
        List<T0103ProductBilityWorkers> indirectList = new ArrayList<T0103ProductBilityWorkers>();
        // 品目别生产性列表
        List<T0104ProductBilityItems> itemsList = new ArrayList<T0104ProductBilityItems>();
        List<SelectItem> depList = commonDaoMapper.getDepList();
        for (SelectItem dep : depList) {
            header = null;
            indirectList = null;
            itemsList = null;
            if (dep.getValue().equals(DepartmentTypes.冲压.getCode())) {
                paramEntity.setDepid(DepartmentTypes.冲压.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("stampHeader", header);
                listData.put("stampIndirectList", indirectList);
                listData.put("stampItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.研磨.getCode())) {
                paramEntity.setDepid(DepartmentTypes.研磨.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("grindHeader", header);
                listData.put("grindIndirectList", indirectList);
                listData.put("grindItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.喷砂.getCode())) {
                paramEntity.setDepid(DepartmentTypes.喷砂.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("sandblastHeader", header);
                listData.put("sandblastIndirectList", indirectList);
                listData.put("sandblastItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.钎焊.getCode())) {
                paramEntity.setDepid(DepartmentTypes.钎焊.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("brazingHeader", header);
                listData.put("brazingIndirectList", indirectList);
                listData.put("brazingItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.导环.getCode())) {
                paramEntity.setDepid(DepartmentTypes.导环.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("guideRingHeader", header);
                listData.put("guideRingIndirectList", indirectList);
                listData.put("guideRingItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.条形锁扣.getCode())) {
                paramEntity.setDepid(DepartmentTypes.条形锁扣.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("barLockHeader", header);
                listData.put("barLockIndirectList", indirectList);
                listData.put("barLockItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.诸品TC.getCode())) {
                paramEntity.setDepid(DepartmentTypes.诸品TC.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("variousTcHeader", header);
                listData.put("variousTcIndirectList", indirectList);
                listData.put("variousTcItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.DPS.getCode())) {
                paramEntity.setDepid(DepartmentTypes.DPS.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("dpsHeader", header);
                listData.put("dpsIndirectList", indirectList);
                listData.put("dpsItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.清洗.getCode())) {
                paramEntity.setDepid(DepartmentTypes.清洗.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("cleanHeader", header);
                listData.put("cleanIndirectList", indirectList);
                listData.put("cleanItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.磨脚.getCode())) {
                paramEntity.setDepid(DepartmentTypes.磨脚.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("grindingFeetHeader", header);
                listData.put("grindingFeetIndirectList", indirectList);
                listData.put("grindingFeetItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.铅坠.getCode())) {
                paramEntity.setDepid(DepartmentTypes.铅坠.getCode());
                header = p0122ProductbilityReportMapper.getAllProductBility(paramEntity);
                indirectList = p0122ProductbilityReportMapper.getProductWorkersList(paramEntity);
                itemsList = p0122ProductbilityReportMapper.getProductItemsList(paramEntity);
                headerData.put("weightsFeetHeader", header);
                listData.put("weightsFeetIndirectList", indirectList);
                listData.put("weightsFeetItemsList", itemsList);
            }

        }
        String[] sheetNameArray = {"冲压", "研磨", "喷砂", "钎焊", "导环", "条形锁扣", "诸品TC", "DPS", "清洗", "磨脚", "铅坠"};
        PoiUtil.downloadByTemplate("月别生产性统计", "P0122Month.xlsx", headerData, listData, sheetNameArray, request, response);
    }

    /**
     * 年度生产性导出
     * 
     * @param param param
     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    @RequestMapping(value = "/exportYear", method = RequestMethod.GET)
    @ResponseBody
    public void exportYear(CommonDownloadParam param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取导出参数
        P0122ProductbilityReportForm paramEntity = JsonUtil.toObject(param.getSubmitData(), P0122ProductbilityReportForm.class);
        // Excel列表数据
        Map<String, List<?>> listData = new HashMap<String, List<?>>();
        // 各部门年度信息列表
        List<P0122ProductbilityExportForm> itemsList = new ArrayList<P0122ProductbilityExportForm>();
        List<SelectItem> depList = commonDaoMapper.getDepList();
        for (SelectItem dep : depList) {
            itemsList = null;
            if (dep.getValue().equals(DepartmentTypes.冲压.getCode())) {
                paramEntity.setDepid(DepartmentTypes.冲压.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("stampItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.研磨.getCode())) {
                paramEntity.setDepid(DepartmentTypes.研磨.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("grindItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.喷砂.getCode())) {
                paramEntity.setDepid(DepartmentTypes.喷砂.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("sandblastItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.钎焊.getCode())) {
                paramEntity.setDepid(DepartmentTypes.钎焊.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("brazingItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.导环.getCode())) {
                paramEntity.setDepid(DepartmentTypes.导环.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("guideRingItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.条形锁扣.getCode())) {
                paramEntity.setDepid(DepartmentTypes.条形锁扣.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("barLockItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.诸品TC.getCode())) {
                paramEntity.setDepid(DepartmentTypes.诸品TC.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("variousTcItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.DPS.getCode())) {
                paramEntity.setDepid(DepartmentTypes.DPS.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("dpsItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.清洗.getCode())) {
                paramEntity.setDepid(DepartmentTypes.清洗.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("cleanItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.磨脚.getCode())) {
                paramEntity.setDepid(DepartmentTypes.磨脚.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("grindingFeetItemsList", itemsList);
            } else if (dep.getValue().equals(DepartmentTypes.铅坠.getCode())) {
                paramEntity.setDepid(DepartmentTypes.铅坠.getCode());
                itemsList = p0122ProductbilityReportMapper.exportYearProductBility(paramEntity);
                listData.put("weightsFeetItemsList", itemsList);
            }
        }
        String[] sheetNameArray = {"冲压（直接时间）", "冲压（包含间接时间）", "研磨（作业者）", "研磨（设备）", "喷砂", "钎焊", "导环", "条形锁扣", "诸品TC", "DPS", "清洗", "磨脚", "铅坠"};
        PoiUtil.downloadByTemplate("年度生产性统计", "P0122Year.xlsx", new HashMap<String, Object>(), listData, sheetNameArray, request, response);
    }
}
