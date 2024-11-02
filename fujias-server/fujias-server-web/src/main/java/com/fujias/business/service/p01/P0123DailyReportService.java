package com.fujias.business.service.p01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.constants.DepartmentTypes;
import com.fujias.business.dao.p01.P0123DailyReportMapper;

import com.fujias.business.daoerp.P0123ERPMapper;
import com.fujias.business.forms.p01.P0122ProductbilityReportForm;
import com.fujias.business.forms.p01.P0123BadImportForm;
import com.fujias.business.forms.p01.P0123CompareDiffForm;
import com.fujias.business.forms.p01.P0123DailyReportForm;

import com.fujias.common.db.dao.T0101DailyReportMapper;
import com.fujias.common.db.entity.T0101DailyReport;

import com.fujias.common.util.StringUtil;

import cn.afterturn.easypoi.handler.inter.IExcelExportServer;

@Service
public class P0123DailyReportService implements IExcelExportServer {

    @Autowired
    private P0123DailyReportMapper p0123DailyReportMapper;
    @Autowired
    private P0123ERPMapper p0123ERPMapper;
    @Autowired
    private T0101DailyReportMapper reportMapper;

    @Override
    public List<Object> selectListForExcelExport(Object queryParams, int page) {
        return p0123DailyReportMapper.getReportData(queryParams, page);
    }

    public List<P0123CompareDiffForm> compareDiff(P0123CompareDiffForm formData) {
        String startDate = formData.getTxtStartDate();
        String endDate = formData.getTxtEndDate();
        String departId = formData.getFormPartment();
        //品类list
        String[] itemList = null;
        //DPS,条形锁扣区分
        Boolean dpsDiffer = false;

        if (formData.getFormPartment().equals(DepartmentTypes.冲压.getCode())) {
            //获取冲压部门信息,根据当前月份,来源部门部品制造科
            formData.setFormPartment("010201");
            
        } else if (formData.getFormPartment().equals(DepartmentTypes.喷砂.getCode())) {
            //获取喷砂部门信息,根据当前月份,工序238喷砂清洗;品类:导环
            formData.setProcessCD("238");
            itemList = new String[1];
            itemList[0] = "01";
            formData.setItemTypeList(itemList);
            formData.setFormPartment(null);

        }  else if (formData.getFormPartment().equals(DepartmentTypes.导环.getCode())) {
            //获取导环部门信息,根据当前月份，工序319组立，品类导环，是完成品
            formData.setProcessCD("319");
            itemList = new String[1];
            itemList[0] = "01";
            formData.setItemTypeList(itemList);;
            formData.setStockChkGroup("90");
            formData.setFormPartment(null);

        } else if (formData.getFormPartment().equals(DepartmentTypes.条形锁扣.getCode())) {
            //获取条形锁扣部门信息,根据当前月份，工序319组立，品类条形锁扣
            formData.setProcessCD("319");
            itemList = new String[2];
            itemList[0] = "04";
            itemList[1] = "03";
            formData.setItemTypeList(itemList);
            dpsDiffer = true;
        } else if (formData.getFormPartment().equals(DepartmentTypes.诸品TC.getCode())) {
            //获取诸品(TC)部门信息，根据当前月份，工序319组立，品类鱼竿保护罩+其他
            formData.setProcessCD("319");
            itemList = new String[2];
            itemList[0] = "08";
            itemList[1] = "10";
            formData.setItemTypeList(itemList);
            formData.setFormPartment(null);

        } else if (formData.getFormPartment().equals(DepartmentTypes.DPS.getCode())) {
            //DPS部门 信息,根据当前月份，工序319组立，品类DPS
            formData.setProcessCD("319");
            itemList = new String[2];
            itemList[0] = "02";
            itemList[1] = "03";
            formData.setItemTypeList(itemList);
            dpsDiffer = true;
        } else if (formData.getFormPartment().equals(DepartmentTypes.铅坠.getCode())) {
            //获取铅坠部门信息，根据当前月份，工序319组立，品类铅坠
            formData.setProcessCD("319");
            itemList = new String[1];
            itemList[0] = "07";
            formData.setItemTypeList(itemList);
            formData.setFormPartment(null);

        }
        
        List<P0123CompareDiffForm> erpData = new ArrayList<P0123CompareDiffForm>();
        if (dpsDiffer) {
            erpData = p0123ERPMapper.getDpsData(formData);
        } else {
            erpData = p0123ERPMapper.getERPData(formData);
        }
        //日报数据汇总，根据指示号+品名
        formData.setTxtStartDate(startDate);
        formData.setTxtEndDate(endDate);
        formData.setFormPartment(departId);
        List<P0122ProductbilityReportForm> comparList = p0123DailyReportMapper.getDailyCount(formData);
        //对日报数据按照品名升序排序
        Collections.sort(comparList, new Comparator<P0122ProductbilityReportForm>() {
            public int compare(P0122ProductbilityReportForm o1,P0122ProductbilityReportForm o2) {
                if (o1.getItemName().compareTo(o2.getItemName())>0) {
                    return 1;
                } else if (o1.getItemName().compareTo(o2.getItemName())==0){
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        //对erp数据按照品名升序排序
        Collections.sort(erpData, new Comparator<P0123CompareDiffForm>() {
            public int compare(P0123CompareDiffForm o1,P0123CompareDiffForm o2) {
                if (o1.getItemName().compareTo(o2.getItemName())>0) {
                    return 1;
                } else if (o1.getItemName().compareTo(o2.getItemName())==0){
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        List<P0123CompareDiffForm> comparResult = new ArrayList<P0123CompareDiffForm>();
        //入库数据，日报数据比较
        int count = 0;
        for (int i = 0; i < erpData.size();i++) {
            for (int j = 0; j < comparList.size();j++) {
                BigDecimal erpNum=erpData.get(i).getNum();
                if (departId.equals(DepartmentTypes.冲压.getCode())) {
                    erpData.get(i).setDailyCount(comparList.get(j).getRepairItemCount());
                } else if(departId.equals(DepartmentTypes.喷砂.getCode()) && comparList.get(j).getItemName().contains("+238")) {
                    erpData.get(i).setDailyCount(comparList.get(j).getDep2ItemCount());
                } else {
                    erpData.get(i).setDailyCount(comparList.get(j).getDepItemCount());
                }
                BigDecimal dailyCount=erpData.get(i).getDailyCount();
                if (erpData.get(i).getItemName().compareTo(comparList.get(j).getItemName())==0) {
                    if (erpData.get(i).getProdInstrNo().compareTo(comparList.get(j).getProdInstrNo())==0) {
                        if (erpNum.equals(BigDecimal.ZERO)) {
                            erpData.get(i).setCompareResult("匹配的入库数量为空");
                            comparResult.add(erpData.get(i));
                            comparList.remove(comparList.get(j));
                            count++;
                            break;
                        } else if (dailyCount.equals(BigDecimal.ZERO)) {
                            erpData.get(i).setCompareResult("匹配的日报数据为空");
                            comparResult.add(erpData.get(i));
                            comparList.remove(comparList.get(j));
                            count++;
                            break;
                        } else if (erpNum.compareTo(dailyCount) == 0) {
                            comparResult.add(erpData.get(i));
                            comparList.remove(comparList.get(j));
                            count++;
                            break;
                        } else {
                            erpData.get(i).setCompareResult("数据不一致");
                            comparResult.add(erpData.get(i));
                            comparList.remove(comparList.get(j));
                            count++;
                            break;
                        }
                    }else {
                        for (int x=j;x<comparList.size();x++) {
                            if (departId.equals(DepartmentTypes.冲压.getCode())) {
                                erpData.get(i).setDailyCount(comparList.get(x).getRepairItemCount());
                            } else if(departId.equals(DepartmentTypes.喷砂.getCode()) && comparList.get(x).getItemName().contains("+238")) {
                                erpData.get(i).setDailyCount(comparList.get(x).getDep2ItemCount());
                            } else {
                                erpData.get(i).setDailyCount(comparList.get(x).getDepItemCount());
                            }
                            BigDecimal daCount=erpData.get(i).getDailyCount();
                            if (erpData.get(i).getItemName().compareTo(comparList.get(x).getItemName())==0
                                    &&erpData.get(i).getProdInstrNo().compareTo(comparList.get(x).getProdInstrNo())==0) {
                                if (erpNum.equals(BigDecimal.ZERO)) {
                                    erpData.get(i).setCompareResult("匹配的入库数量为空");
                                    comparResult.add(erpData.get(i));
                                    comparList.remove(comparList.get(x));
                                    count++;
                                    break;
                                } else if (daCount.equals(BigDecimal.ZERO)) {
                                    erpData.get(i).setCompareResult("匹配的日报数据为空");
                                    comparResult.add(erpData.get(i));
                                    comparList.remove(comparList.get(x));
                                    count++;
                                    break;
                                } else if (erpNum.compareTo(daCount) == 0) {
                                    comparResult.add(erpData.get(i));
                                    comparList.remove(comparList.get(x));
                                    count++;
                                    break;
                                } else {
                                    erpData.get(i).setCompareResult("数据不一致");
                                    comparResult.add(erpData.get(i));
                                    comparList.remove(comparList.get(x));
                                    count++;
                                    break;
                                }
                            }
                        }
                    } 
                } else if (erpData.get(i).getItemName().compareTo(comparList.get(j).getItemName()) < 0) {
                    erpData.get(i).setCompareResult("没有相应的日报数据");
                    erpData.get(i).setDailyCount(null);
                    comparResult.add(erpData.get(i));
                    count++;
                    break;
                } else if (erpData.get(i).getItemName().compareTo(comparList.get(j).getItemName()) > 0) {
                    P0123CompareDiffForm compareForm = new P0123CompareDiffForm();
                    compareForm.setCompareResult("没有相应的入库数据");
                    compareForm.setDailyCount(dailyCount);
                    compareForm.setItemName(comparList.get(j).getItemName());
                    compareForm.setProdInstrNo(comparList.get(j).getProdInstrNo());
                    comparResult.add(compareForm);
                    comparList.remove(comparList.get(j));
                    continue;
                }
                    
            }

        }
            for (int m = 0;m<comparList.size();m++) {
                P0123CompareDiffForm compareForm = new P0123CompareDiffForm();
                compareForm.setItemName(comparList.get(m).getItemName());
                compareForm.setProdInstrNo(comparList.get(m).getProdInstrNo());
                if (departId.equals(DepartmentTypes.冲压.getCode())) {
                    compareForm.setDailyCount(comparList.get(m).getRepairItemCount());
                } else if(departId.equals(DepartmentTypes.喷砂.getCode()) && comparList.get(m).getItemName().contains("+238")) {
                    compareForm.setDailyCount(comparList.get(m).getDep2ItemCount());
                } else {
                    compareForm.setDailyCount(comparList.get(m).getDepItemCount());
                }

                compareForm.setCompareResult("没有相应的入库数据");
                comparResult.add(compareForm);
            }
            if (erpData.size() > comparList.size()) {
                for (int n = count;n<erpData.size();n++) {
                    erpData.get(n).setCompareResult("没有相应的日报数据");
                    erpData.get(n).setDailyCount(null);
                    comparResult.add(erpData.get(n));
                }
            }

        Collections.sort(comparResult, new Comparator<P0123CompareDiffForm>() {
            public int compare(P0123CompareDiffForm o1,P0123CompareDiffForm o2) {
                if (StringUtil.isEmpty(o2.getCompareResult())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return comparResult;
    }
    
    
    /**
     * 更新
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(List<P0123BadImportForm> formData) {
        
        for (P0123BadImportForm badImportForm : formData) {
            P0123DailyReportForm dailyReportForm = p0123DailyReportMapper.badExist(badImportForm.getItemName().trim());
            if (dailyReportForm !=null) {
                T0101DailyReport dailyReport = new T0101DailyReport();
                dailyReport.setBadContent(badImportForm.getBadContent().trim());
                dailyReport.setBadCount(badImportForm.getBadCount());
                dailyReport.setId(dailyReportForm.getId());
                reportMapper.updateByPrimaryKeySelective(dailyReport);
            }

        }
        return true;
    }
}
    

    


