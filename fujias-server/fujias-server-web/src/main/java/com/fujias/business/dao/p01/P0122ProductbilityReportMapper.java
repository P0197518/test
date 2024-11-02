package com.fujias.business.dao.p01;

import java.math.BigDecimal;
import java.util.List;

import com.fujias.business.forms.p01.P0122ProductbilityExportForm;
import com.fujias.business.forms.p01.P0122ProductbilityReportForm;
import com.fujias.common.db.entity.T0103ProductBilityWorkers;
import com.fujias.common.db.entity.T0104ProductBilityItems;
import com.fujias.common.db.entity.T0602BatchErrorReport;

public interface P0122ProductbilityReportMapper {

    /**
     * 获取生产性执行记录列表
     * 
     * @param form form
     * @return 列表
     */
    List<P0122ProductbilityReportForm> getBatchlogListByPage(P0122ProductbilityReportForm form);

    /**
     * 判断日报数据有没有导入
     * 
     * @param formData 参数
     * @return 结果
     */
    int checkWorkReport(P0122ProductbilityReportForm formData);

    /**
     * 判断考勤有没有导入
     * 
     * @param formData 参数
     * @return 结果
     */
    int checkWorkTime(P0122ProductbilityReportForm formData);

    /**
     * 检查工作人员设置是否存在
     * 
     * @param formData 参数
     * @return 结果
     */
    int checkWorkUser(P0122ProductbilityReportForm formData);

    /**
     * 检查执行部门的默认取数规则是否存在
     * 
     * @param formData 参数
     * @return 结果
     */
    int checkGetCount(P0122ProductbilityReportForm formData);

    /**
     * 检查是否已经执行过生产性
     * 
     * @param formData 参数
     * @return 结果
     */
    int checkRepeat(P0122ProductbilityReportForm formData);

    /**
     * 检查该部门 月份是否有执行中状态的处理
     * 
     * @param formData 参数
     * @return 结果
     */
    int checkRunStatus(P0122ProductbilityReportForm formData);

    /**
     * 检查该部门日报中的人员是否存在于考勤记录中
     * 
     * @param formData 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> checkDailyReportUser(P0122ProductbilityReportForm formData);

    /**
     * 更新执行记录表删除区分为已删除
     * 
     * @param formData 执行参数
     * @return 结果
     */
    int updBatchLogDelFlg(P0122ProductbilityReportForm formData);

    /**
     * 删除上次执行的数据
     * 
     * @param formData 执行参数
     * @return 结果
     */
    int deleteCaclResulfByYearMonthAndDepId(P0122ProductbilityReportForm formData);

    /**
     * 创建数据暂存临时表
     * 
     * @param formData 执行参数
     */
    void createTableByTemp_caclresulf(P0122ProductbilityReportForm formData);

    /**
     * 删除临时表
     * 
     * @return 结果
     */
    Integer deleteTempTable();

    /**
     * 获取品目别总生产时间 并插入临时表
     * 
     * @param formData 执行参数
     * @return 结果
     */
    Integer getProdTimeByItemName(P0122ProductbilityReportForm formData);

    /**
     * 冲压部门时 获取品目别总生产时间 并插入临时表
     * 
     * @param formData 执行参数
     * @return 结果
     */
    Integer getProdTimeByItemNameByPress(P0122ProductbilityReportForm formData);

    /**
     * 获取品目别总生产数量
     * 
     * @param form 执行参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getProdCountByItemName(P0122ProductbilityReportForm form);

    /**
     * 冲压部门时 获取品目和工序别总生产数量
     * 
     * @param form 执行参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getProdCountByItemNameByPress(P0122ProductbilityReportForm form);

    /**
     * 更新临时表字段 生产数量
     * 
     * @param formData 参数
     * @return 结果
     */
    int updTemp_caclresulf_prodCount(P0122ProductbilityReportForm formData);

    /**
     * 更新临时表字段 社外修理数量
     * 
     * @param formData 参数
     * @return 结果
     */
    int updTemp_caclresulf_outRepairCount(P0122ProductbilityReportForm formData);

    /**
     * 获取钎焊的全自动工作时间和完成数量
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getBrazingTimeAndCount(P0122ProductbilityReportForm form);

    /**
     * 获取导环的全自动工作时间和完成数量
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getGuideRingTimeAndCount(P0122ProductbilityReportForm form);

    /**
     * 更新临时表全自动时间和数量
     * 
     * @param formData 参数
     * @return 结果
     */
    int updTemp_caclresulf_automachine(P0122ProductbilityReportForm formData);

    /**
     * 获取各类研磨时间和数量
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getGrindTimeAndCount(P0122ProductbilityReportForm form);

    /**
     * 更新各类研磨时间和数量到临时表
     * 
     * @param formData 参数
     * @return 结果
     */
    int updTemp_caclresulf_grind(P0122ProductbilityReportForm formData);

    /**
     * 获取研磨的机器时间和数量
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getGrindMachineTimeAndCount(P0122ProductbilityReportForm form);

    /**
     * 更新临时表研磨机器时间和数量
     * 
     * @param formData 参数
     * @return 结果
     */
    int updTemp_caclresulf_machine(P0122ProductbilityReportForm formData);

    /**
     * 获取他部门投入人员直接时间
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getOtherDepUserTime(P0122ProductbilityReportForm formData);

    /**
     * 获取协助他部门人员直接时间
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getHelpOtherDepUserTime(P0122ProductbilityReportForm formData);

    /**
     * 组立2部门投入人员间接时间
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getCombinationDepUserTime(P0122ProductbilityReportForm formData);

    /**
     * 获取考勤总时间
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getAllWorkTime(P0122ProductbilityReportForm formData);

    /**
     * 获取部门月份总直接时间
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getDepMonthAllWorkTime(P0122ProductbilityReportForm formData);

    /**
     * 获取考勤间接工作时间
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getIndirectAllWorkTime(P0122ProductbilityReportForm formData);

    /**
     * 获取执行部门的所有间接人员时间
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getDepIndirectWorkTime(P0122ProductbilityReportForm form);

    /**
     * 获取部门总生产数量
     * 
     * @param formData 参数
     * @return 结果
     */
    BigDecimal getDepMonthProdCount(P0122ProductbilityReportForm formData);

    /**
     * 获取临时表所有数据
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityReportForm> getAllTempTableData(P0122ProductbilityReportForm form);

    /**
     * 获取当前用户的部门
     * 
     * @param userId 参数
     * @return 结果
     */
    String getDepidByUser(String userId);

    /**
     * 获取间接人员信息列表 分页
     * 
     * @param form 参数
     * @return 结果
     */
    List<T0103ProductBilityWorkers> getProductWorkersListByPage(P0122ProductbilityReportForm form);

    /**
     * 获取间接人员信息列表 不分页
     * 
     * @param form 参数
     * @return 结果
     */
    List<T0103ProductBilityWorkers> getProductWorkersList(P0122ProductbilityReportForm form);

    /**
     * 获取品目别生产性列表 分页
     * 
     * @param form 参数
     * @return 结果
     */
    List<T0104ProductBilityItems> getProductItemsListByPage(P0122ProductbilityReportForm form);

    /**
     * 获取品目别生产性列表 不分页
     * 
     * @param form 参数
     * @return 结果
     */
    List<T0104ProductBilityItems> getProductItemsList(P0122ProductbilityReportForm form);

    /**
     * 获取总的生产性信息
     * 
     * @param form 参数
     * @return 结果
     */
    P0122ProductbilityReportForm getAllProductBility(P0122ProductbilityReportForm form);

    /**
     * 获取执行错误列表
     * 
     * @param form 参数
     * @return 结果
     */
    List<T0602BatchErrorReport> getRunErrorListByPage(P0122ProductbilityReportForm form);

    /**
     * 获取执行记录
     * 
     * @param form 参数
     * @return 结果
     */
    P0122ProductbilityReportForm getBatchlog(P0122ProductbilityReportForm form);

    /**
     * 导出年度生产性
     * 
     * @param form 参数
     * @return 结果
     */
    List<P0122ProductbilityExportForm> exportYearProductBility(P0122ProductbilityReportForm form);
}
