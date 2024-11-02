package com.fujias.business.controller.p02;

import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.fujias.business.dao.p02.P0221WorkerTimeMapper;

import com.fujias.business.forms.p02.P0221WorkTimeForm;

import com.fujias.business.forms.p02.P0221WorkTimeImportForm;
import com.fujias.business.service.p02.P0211WorkTimeService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.CheckMessage;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.util.CheckErrorUtil;

import com.fujias.common.util.StringUtil;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * 考勤数据管理的Controller类
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/P0221WorkTime")
public class P0221WorkTimeController extends BaseController {

    @Autowired
    private P0221WorkerTimeMapper workerTimeMapper;
    
    @Autowired
    private P0211WorkTimeService workTimeService;

    /**
     * 考勤数据初期化处理
     * 
     * @return view
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        P0221WorkTimeForm formData = new P0221WorkTimeForm();
        return this.backView("pages/P0221WorkTime", formData);
    }

    /**
     * 考勤数据导入
     * 
     * @param formData formData
     * @return view
     */
    @RequestMapping(value = "/indexEdit", method = RequestMethod.GET)
    public ModelAndView indexEdit(P0221WorkTimeForm formData) {

        return this.backView("pages/P0221WorkTimeImport", formData);
    }

    /**
     * 获取角色列表
     * 
     * @param formData formData
     * @return ResultInfo
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getList(@RequestBody P0221WorkTimeForm formData) {

        if (formData.getDepartmentId().equals("0001") || formData.getDepartmentId().equals("所有部门")) {
            formData.setDepartmentId(null);
        }
        if (StringUtil.isNotEmpty(formData.getYearMonth())) {
            String year = formData.getYearMonth();
            String yearMonth = year.substring(0, 4) + year.substring(5, 7);
            formData.setYearMonth(yearMonth);
        }

        List<P0221WorkTimeForm> workerTimeList = workerTimeMapper.getListByPage(formData);
        return this.backListData(workerTimeList, formData.getPager().getTotalCount());

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
        params.setStartSheetIndex(2);
        params.setHeadRows(1);
        params.setStartSheetIndex(2);

        List<P0221WorkTimeImportForm> listData = ExcelImportUtil.importExcel(fileData.getInputStream(), P0221WorkTimeImportForm.class, params);

        List<CheckMessage> errors = new ArrayList<CheckMessage>();

        for (int i = 0; i < listData.size(); i++) {

            List<CheckMessage> itemErrors = CheckErrorUtil.validateForm(listData.get(i));

            if (!itemErrors.isEmpty()) {
                for (CheckMessage item : itemErrors) {
                    item.setRow(String.valueOf(i + 1));
                }
                errors.addAll(itemErrors);
            }
            String departName = listData.get(i).getDepartmentName();
            List<P0221WorkTimeForm> departExist = workerTimeMapper.departExist(departName);
            if (departExist.size() == 0) {
                CheckMessage checkDepart = new CheckMessage();
                checkDepart.setField("departmentName");
                checkDepart.setCode("EC0104");
                checkDepart.setRow(Integer.toString(i+3));
                errors.add(checkDepart);
            } else {
//                boolean workerExcelExist = false;
//                for (int j=1;j<listData.size();j++) {
//                    if (listData.get(i).getWorkerName().equals(listData.get(j).getWorkerName()) 
//                            && listData.get(i).getDepartmentName().equals(listData.get(j).getDepartmentName())) {
//                        workerExcelExist = true;
//                    }
//                }
                List<P0221WorkTimeForm> departCode = workerTimeMapper.departExist(departName);
                P0221WorkTimeImportForm impForm = new P0221WorkTimeImportForm();
                impForm.setYearMonth(formData.getImportYearAndMonth());
                impForm.setDepartmentId(departCode.get(0).getDepartmentId());
                impForm.setWorkerName(listData.get(i).getWorkerName().trim());
                if (workerTimeMapper.workTimeExist(impForm) > 0  || workerTimeMapper.workExist(impForm) > 0
                        ) {
                    CheckMessage checkDepart = new CheckMessage();
                    checkDepart.setField("workerName");
                    checkDepart.setRow(Integer.toString(i+3));
                    checkDepart.setCode("EC0108");
                    errors.add(checkDepart);
                }
            }
            String workType = listData.get(i).getWorkType();
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(workType);
            if (m.find()) {
                CheckMessage checkDepart = new CheckMessage();
                checkDepart.setField("workType");
                checkDepart.setRow(Integer.toString(i+3));
                checkDepart.setCode("EC0105");
                errors.add(checkDepart);
            }
            


            

        }

        if (!errors.isEmpty()) {
            return this.backCheckError(errors);
        } else {
            if (workTimeService.add(listData,formData.getImportYearAndMonth())) {
                return this.backUpdateState(StateTypes.SUCCESS);
            } else {
                return this.backUpdateState(StateTypes.ERROR, MessageCodes.EC0018);
            }
            
        }

    }

}
