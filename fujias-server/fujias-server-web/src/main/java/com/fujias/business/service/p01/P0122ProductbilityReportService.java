package com.fujias.business.service.p01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.constants.BatchLogStatusTypes;
import com.fujias.business.constants.DepartmentTypes;
import com.fujias.business.dao.p01.P0122ProductbilityReportMapper;
import com.fujias.business.forms.p01.P0122ProductbilityReportForm;
import com.fujias.common.db.dao.T0102ProductBilityInfoMapper;
import com.fujias.common.db.dao.T0103ProductBilityWorkersMapper;
import com.fujias.common.db.dao.T0104ProductBilityItemsMapper;
import com.fujias.common.db.dao.T0601BatchLogMapper;
import com.fujias.common.db.dao.T0602BatchErrorReportMapper;
import com.fujias.common.db.entity.T0102ProductBilityInfo;
import com.fujias.common.db.entity.T0103ProductBilityWorkers;
import com.fujias.common.db.entity.T0104ProductBilityItems;
import com.fujias.common.db.entity.T0601BatchLog;
import com.fujias.common.db.entity.T0602BatchErrorReport;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.AccesslogUtil;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.JsonUtil;

/**
 * 生产性计算
 * 
 * @author wangbaoxin
 *
 */
@Service
public class P0122ProductbilityReportService {

    @Autowired
    private T0601BatchLogMapper t0601BatchLogMapper;
    @Autowired
    private P0122ProductbilityReportMapper p0122ProductbilityReportMapper;
    @Autowired
    private T0602BatchErrorReportMapper t0602BatchErrorReportMapper;
    @Autowired
    private T0103ProductBilityWorkersMapper t0103ProductBilityWorkersMapper;
    @Autowired
    private T0102ProductBilityInfoMapper t0102ProductBilityInfoMapper;
    @Autowired
    private T0104ProductBilityItemsMapper t0104ProductBilityItemsMapper;

    /**
     * 生产性执行前检查
     * 
     * @param formData 参数
     * @return 结果
     */
    public int checkRun(P0122ProductbilityReportForm formData) {

        // 判断日报数据有没有导入
        if (0 >= p0122ProductbilityReportMapper.checkWorkReport(formData)) {
            return 6;
        }

        // 判断考勤有没有导入
        if (0 >= p0122ProductbilityReportMapper.checkWorkTime(formData)) {
            return 1;
        }

        // 检查工作人员设置是否存在
        if (0 >= p0122ProductbilityReportMapper.checkWorkUser(formData)) {
            return 2;
        }

        // 检查执行部门的默认取数规则是否存在
        if (0 >= p0122ProductbilityReportMapper.checkGetCount(formData)) {
            return 3;
        }

        // 检查该部门 月份是否有执行中状态的处理
        if (0 < p0122ProductbilityReportMapper.checkRunStatus(formData)) {
            return 4;
        }

        // 检查是否已经执行过生产性
        if (0 < p0122ProductbilityReportMapper.checkRepeat(formData)) {
            return 5;
        }
        return 0;
    }

    /**
     * 生产性执行
     * 
     * @param userid 执行人ID
     * @param formData 执行参数
     * @throws BusinessException 异常
     */
    @Async
    public void run(String userid, P0122ProductbilityReportForm formData) throws BusinessException {
        formData.setUserId(userid);
        String batchlogId = UUID.randomUUID().toString().replaceAll("-", "");
        AccesslogUtil.log(batchlogId + "生产性计算开始执行...");
        // 添加执行记录
        T0601BatchLog batchLog = new T0601BatchLog();
        batchLog.setId(batchlogId);
        batchLog.setRunuser(userid);
        batchLog.setDepid(formData.getDepid());
        batchLog.setYearmonth(formData.getYearmonth());
        batchLog.setRunparam(JsonUtil.toJson(formData));
        batchLog.setStatus(BatchLogStatusTypes.実行中.getCode());
        batchLog.setStarttime(DateUtil.getNowDate());
        batchLog.setDelflg(false);
        t0601BatchLogMapper.insert(batchLog);

        // 计算执行
        try {
            doRunCalculateCount(formData, batchLog);
        } catch (Exception e) {
            e.printStackTrace();

            batchLog.setStatus(BatchLogStatusTypes.エラー終了.getCode());
            batchLog.setEndtime(DateUtil.getNowDate());

            if (e.getMessage().length() > 200) {
                batchLog.setErrormessage(e.getMessage().substring(0, 200));
            } else {
                batchLog.setErrormessage(e.getMessage());
            }

            t0601BatchLogMapper.updateByPrimaryKeySelective(batchLog);
            AccesslogUtil.log(batchlogId + "生产性计算执行出现异常：" + e.getMessage());
            throw new BusinessException("生产性计算执行出现异常");
        }

        batchLog.setStatus(BatchLogStatusTypes.実行成功終了.getCode());
        batchLog.setEndtime(DateUtil.getNowDate());
        t0601BatchLogMapper.updateByPrimaryKeySelective(batchLog);

        // 手动删除临时表 执行结束后不会即时删除临时表 这里手动删除下
        p0122ProductbilityReportMapper.deleteTempTable();

        AccesslogUtil.log(batchlogId + "生产性计算成功执行完成。");
    }

    /**
     * 计算执行
     * 
     * @param formData 执行参数
     * @param batchlog 执行记录
     * @throws BusinessException 执行异常
     */
    @Transactional(rollbackFor = Exception.class)
    private void doRunCalculateCount(P0122ProductbilityReportForm formData, T0601BatchLog batchlog) throws BusinessException {

        // 检查该部门日报中的人员是否存在于考勤记录中 不存在插入警告信息到错误log表 继续执行
        checkReportDepUser(formData, batchlog);

        // 设置执行批次号
        formData.setId(batchlog.getId());

        // 删除上次计算结果
        p0122ProductbilityReportMapper.deleteCaclResulfByYearMonthAndDepId(formData);

        // 创建数据暂存临时表
        p0122ProductbilityReportMapper.createTableByTemp_caclresulf(formData);

        // 汇总品目和工序单位的 数量 和 时间
        List<P0122ProductbilityReportForm> itemCountList = new ArrayList<P0122ProductbilityReportForm>();
        if (DepartmentTypes.冲压.getCode().equals(formData.getDepid())) {
            // 冲压部门时按照品目和工序单位 汇总工作时间 结果插入临时表
            p0122ProductbilityReportMapper.getProdTimeByItemNameByPress(formData);
            // 冲压部门时汇总品目总生产数量
            itemCountList = p0122ProductbilityReportMapper.getProdCountByItemNameByPress(formData);
        } else {
            // 其他情况按照品目单位 汇总工作时间 结果插入临时表
            p0122ProductbilityReportMapper.getProdTimeByItemName(formData);
            // 其他情况汇总生产数量
            itemCountList = p0122ProductbilityReportMapper.getProdCountByItemName(formData);
        }

        // Set<String> keys = new HashSet<String>();

        // 判断完成数量
        for (P0122ProductbilityReportForm entity : itemCountList) {
            entity.setId(formData.getId());
            if (DepartmentTypes.冲压.getCode().equals(formData.getDepid())) {
                // 品目取数工序设置是 true
                if (entity.isItemSetting()) {
                    if (null != entity.getTenchnicsItemCount() && BigDecimal.ZERO.compareTo(entity.getTenchnicsItemCount()) != 0) {
                        entity.setTechnicsCD("normal");
                        p0122ProductbilityReportMapper.updTemp_caclresulf_prodCount(entity);
                    } else {
                        // 错误信息插入表
                        insError(batchlog, entity);
                    }
                    // 品目单独计算工序
                } else if (entity.isItemSys()) {
                    p0122ProductbilityReportMapper.updTemp_caclresulf_prodCount(entity);
                    // 修理工序
                } else if ("repair".equals(entity.getTechnicsCD())) {
                    p0122ProductbilityReportMapper.updTemp_caclresulf_outRepairCount(entity);
                } else {
                    // 取任意指示号的数量作为完成数量
                    p0122ProductbilityReportMapper.updTemp_caclresulf_prodCount(entity);
                }
            } else {
                // 品目取数工序设置是 true
                if (entity.isItemSetting()) {
                    if (null != entity.getTenchnicsItemCount() && BigDecimal.ZERO.compareTo(entity.getTenchnicsItemCount()) != 0) {
                        // 更新临时表汇总生产数量
                        p0122ProductbilityReportMapper.updTemp_caclresulf_prodCount(entity);
                    } else {
                        // 错误信息插入表
                        insError(batchlog, entity);
                    }
                } else {
                    // 更新临时表汇总生产数量
                    p0122ProductbilityReportMapper.updTemp_caclresulf_prodCount(entity);
                }
            }
        }

        List<P0122ProductbilityReportForm> dateList = new ArrayList<P0122ProductbilityReportForm>();
        if (DepartmentTypes.钎焊.getCode().equals(formData.getDepid())) {
            // 获取钎焊的全自动工作时间和完成数量 并更新临时表
            dateList = p0122ProductbilityReportMapper.getBrazingTimeAndCount(formData);
            for (P0122ProductbilityReportForm brazing : dateList) {
                brazing.setId(formData.getId());
                p0122ProductbilityReportMapper.updTemp_caclresulf_automachine(brazing);
            }
        } else if (DepartmentTypes.导环.getCode().equals(formData.getDepid())) {
            // 获取导环的全自动工作时间和完成数量 并更新临时表
            dateList = p0122ProductbilityReportMapper.getGuideRingTimeAndCount(formData);
            for (P0122ProductbilityReportForm guideRing : dateList) {
                guideRing.setId(formData.getId());
                p0122ProductbilityReportMapper.updTemp_caclresulf_automachine(guideRing);
            }
        } else if (DepartmentTypes.研磨.getCode().equals(formData.getDepid())) {
            // 获取各类研磨时间和数量 并更新临时表
            dateList = p0122ProductbilityReportMapper.getGrindTimeAndCount(formData);
            for (P0122ProductbilityReportForm grind : dateList) {
                grind.setId(formData.getId());
                p0122ProductbilityReportMapper.updTemp_caclresulf_grind(grind);
            }

            dateList = null;
            // 获取研磨的机器时间和数量 并更新临时表
            dateList = p0122ProductbilityReportMapper.getGrindMachineTimeAndCount(formData);
            for (P0122ProductbilityReportForm grindMachine : dateList) {
                grindMachine.setId(formData.getId());
                p0122ProductbilityReportMapper.updTemp_caclresulf_machine(grindMachine);
            }
        }

        // 考勤时间计算
        // 部门间人员调整计算

        // 部门月份生产总时间
        BigDecimal depMonthAllTime = BigDecimal.ZERO;
        // 部门月份总生产数量
        BigDecimal depMonthAllProdCount = BigDecimal.ZERO;
        // 差异间接时间
        BigDecimal diffIndirectTime = BigDecimal.ZERO;

        // 他部门投入人员直接时间
        BigDecimal otherDepUserTime = p0122ProductbilityReportMapper.getOtherDepUserTime(formData);
        // 协助他部门人员直接时间
        BigDecimal helpOtherDepUserTime = p0122ProductbilityReportMapper.getHelpOtherDepUserTime(formData);
        // 组立2部门投入人员间接时间
        BigDecimal combinationDepUserTime = p0122ProductbilityReportMapper.getCombinationDepUserTime(formData);
        // TODO 王吉帅的时间计算
        // 组立2部门调出间接时间
        // BigDecimal combinationDepUserTimeOut = combinationDepUserTime.multiply(new BigDecimal("0.4"));
        // 考勤总时间
        BigDecimal allWorkHours = p0122ProductbilityReportMapper.getAllWorkTime(formData).multiply(new BigDecimal("60"));
        // 部门月份总直接时间
        BigDecimal allDirectTime = p0122ProductbilityReportMapper.getDepMonthAllWorkTime(formData);
        // 考勤间接工作时间
        BigDecimal allIndirect = p0122ProductbilityReportMapper.getIndirectAllWorkTime(formData).multiply(new BigDecimal("60"));
        // DPS 60%
        if (DepartmentTypes.DPS.getCode().equals(formData.getDepid())) {
            combinationDepUserTime = combinationDepUserTime.multiply(new BigDecimal("0.6"));
            // 条形锁扣 10%，铅坠 10%，清洗10%，喷砂10%
        } else if (DepartmentTypes.条形锁扣.getCode().equals(formData.getDepid()) || DepartmentTypes.铅坠.getCode().equals(formData.getDepid())
                        || DepartmentTypes.清洗.getCode().equals(formData.getDepid()) || DepartmentTypes.喷砂.getCode().equals(formData.getDepid())) {
            combinationDepUserTime = combinationDepUserTime.multiply(new BigDecimal("0.1"));
        } else {
            combinationDepUserTime = BigDecimal.ZERO;
        }

        // 计算部门月份生产总时间
        depMonthAllTime = allWorkHours.add(otherDepUserTime).subtract(helpOtherDepUserTime).add(combinationDepUserTime);
        // .add(combinationDepUserTimeOut)

        // 考勤和日报差异间接时间计算
        diffIndirectTime = depMonthAllTime.subtract(allIndirect).subtract(allDirectTime);

        // 间接时间计算
        dateList = null;
        // 获取执行部门的所有间接人员时间
        dateList = p0122ProductbilityReportMapper.getDepIndirectWorkTime(formData);
        for (P0122ProductbilityReportForm entity : dateList) {
            // 时间均摊标志位 是true
            if (entity.isShareFlag()) {
                entity.setAllWorkHours(entity.getAllWorkHours().multiply(new BigDecimal("0.6")).setScale(1, BigDecimal.ROUND_UP));
            }
            // 插入生产性间接人员信息表
            T0103ProductBilityWorkers workers = new T0103ProductBilityWorkers();
            workers.setYearMonth(formData.getYearmonth());
            workers.setDepartmentId(formData.getDepid());
            workers.setWokerName(entity.getWorkerName());
            workers.setWorkTime(entity.getAllWorkHours());
            workers.setDelflg(false);
            t0103ProductBilityWorkersMapper.insert(workers);
        }
        // 获取部门月份总生产数量
        depMonthAllProdCount = p0122ProductbilityReportMapper.getDepMonthProdCount(formData);
        // 插入生产性基本信息表
        T0102ProductBilityInfo info = new T0102ProductBilityInfo();
        info.setYearMonth(formData.getYearmonth());
        info.setDepartmentId(formData.getDepid());
        info.setAllCount(depMonthAllProdCount.setScale(0, BigDecimal.ROUND_UP));
        info.setWorkTime(allWorkHours.setScale(1, BigDecimal.ROUND_UP));
        info.setAdjuestTime(depMonthAllTime.subtract(allWorkHours).setScale(1, BigDecimal.ROUND_UP));
        info.setAllTime(depMonthAllTime.setScale(1, BigDecimal.ROUND_UP));
        info.setProductbilityCount(depMonthAllProdCount.divide(isNullToOne(depMonthAllTime), 9, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_UP));
        info.setDirectTime(allDirectTime.setScale(1, BigDecimal.ROUND_UP));
        info.setDirectProductbilityCount(depMonthAllProdCount.divide(isNullToOne(allDirectTime), 9, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_UP));
        info.setIndirectTime(depMonthAllTime.subtract(allDirectTime).setScale(1, BigDecimal.ROUND_UP));
        info.setDelflg(false);
        t0102ProductBilityInfoMapper.insert(info);

        dateList = null;
        // 获取临时表所有数据
        dateList = p0122ProductbilityReportMapper.getAllTempTableData(formData);
        T0104ProductBilityItems bilityItems = new T0104ProductBilityItems();
        for (P0122ProductbilityReportForm item : dateList) {
            // 计算品目别的间接时间 = 间接总时间 * 临时表该品目 汇总数量 / 部门的总生产数量
            BigDecimal indirectTime = info.getIndirectTime().multiply(item.getProdCount()).divide(depMonthAllProdCount, 9, BigDecimal.ROUND_HALF_UP)
                            .setScale(0, BigDecimal.ROUND_UP);
            bilityItems.setYearMonth(formData.getYearmonth());
            bilityItems.setDepartmentId(formData.getDepid());
            bilityItems.setItemName(item.getItemName());
            bilityItems.setTechnicsCD(item.getTechnicsCD());
            bilityItems.setProdCount(item.getProdCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setDirectTime(item.getDirectTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setDirectProductbilityCount(item.getProdCount().divide(isNullToOne(bilityItems.getDirectTime()), 9, BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setIndirectTime(indirectTime.setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setAllTime(bilityItems.getDirectTime().add(bilityItems.getIndirectTime()).setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setAllProductbilityCount(bilityItems.getProdCount().divide(isNullToOne(bilityItems.getAllTime()), 9, BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setWideTime(item.getWideTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setWideCount(item.getWideCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setWideProductbilityCount(item.getWideCount().divide(isNullToOne(item.getWideTime()), 9, BigDecimal.ROUND_HALF_UP).setScale(0,
                            BigDecimal.ROUND_UP));
            bilityItems.setDriedTime(item.getDriedTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setDriedCount(item.getDriedCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setDriedProductbilityCount(item.getDriedCount().divide(isNullToOne(item.getDriedTime()), 9, BigDecimal.ROUND_HALF_UP)
                            .setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setMiddleTime(item.getMiddleTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setMiddleCount(item.getMiddleCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setMiddleProductbilityCount(item.getMiddleCount().divide(isNullToOne(item.getMiddleTime()), 9, BigDecimal.ROUND_HALF_UP)
                            .setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setFineTime(item.getFineTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setFineCount(item.getFineCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setFineProductbilityCount(item.getFineCount().divide(isNullToOne(item.getFineTime()), 9, BigDecimal.ROUND_HALF_UP).setScale(0,
                            BigDecimal.ROUND_UP));
            bilityItems.setBcTime(item.getBcTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setBcCount(item.getBcCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setBcProductbilityCount(
                            item.getBcCount().divide(isNullToOne(item.getBcTime()), 9, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setT2Time(item.getT2Time().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setT2Count(item.getT2Count().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setT2ProductbilityCount(
                            item.getT2Count().divide(isNullToOne(item.getT2Time()), 9, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setGmTime(item.getGmTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setGmCount(item.getGmCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setGmProductbilityCount(
                            item.getGmCount().divide(isNullToOne(item.getGmTime()), 9, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setCcTime(item.getCcTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setCcCount(item.getCcCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setCcProductbilityCount(
                            item.getCcCount().divide(isNullToOne(item.getCcTime()), 9, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setNewColorTime(item.getNewColorTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setNewColorCount(item.getNewColorCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setNewColorProductbilityCount(item.getNewColorCount().divide(isNullToOne(item.getNewColorTime()), 9, BigDecimal.ROUND_HALF_UP)
                            .setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setAutomationTime(item.getAutomationTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setAutomationCount(item.getAutomationCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setAutomationProductbilityCount(item.getAutomationCount()
                            .divide(isNullToOne(item.getAutomationTime()), 9, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setMachineTime(item.getMachineTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setMiddleCount(item.getMiddleCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setMachineProductbilityCount(item.getMiddleCount().divide(isNullToOne(item.getMachineTime()), 9, BigDecimal.ROUND_HALF_UP)
                            .setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setInRepairTime(item.getInRepairTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setOutRepairTime(item.getOutRepairTime().setScale(1, BigDecimal.ROUND_UP));
            bilityItems.setOutRepairCount(item.getOutRepairCount().setScale(0, BigDecimal.ROUND_UP));
            bilityItems.setDelflg(false);
            t0104ProductBilityItemsMapper.insert(bilityItems);

        }
    }

    /**
     * 检查该部门日报中的人员是否存在于考勤记录中
     * 
     * @param formData formData
     * @param batchlog batchlog
     */
    private void checkReportDepUser(P0122ProductbilityReportForm formData, T0601BatchLog batchlog) {
        List<P0122ProductbilityReportForm> dailyReportUserList = p0122ProductbilityReportMapper.checkDailyReportUser(formData);
        T0602BatchErrorReport errorMsg = new T0602BatchErrorReport();
        for (P0122ProductbilityReportForm user : dailyReportUserList) {
            if (!user.isFlag()) {
                errorMsg.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                errorMsg.setLotId(batchlog.getId());
                errorMsg.setErrorTarget(user.getWorkerName());
                errorMsg.setErrorContent("指示书号:" + user.getProdInstrNo() + ",工序：" + user.getTechnicsCD() + "的工作人员在考勤中不存在，请确认是否正确。");
                errorMsg.setDelflg(false);
                t0602BatchErrorReportMapper.insert(errorMsg);
            }
        }
    }

    /**
     * 错误信息插入表
     * 
     * @param batchlog batchlog
     * @param entity entity
     */
    private void insError(T0601BatchLog batchlog, P0122ProductbilityReportForm entity) {
        // 错误信息插入表
        T0602BatchErrorReport error = new T0602BatchErrorReport();
        error.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        error.setLotId(batchlog.getId());
        error.setErrorTarget(entity.getItemName());
        error.setErrorContent("品目的取数工序不存在，或者没有完成数量，请确认。");
        error.setDelflg(false);
        t0602BatchErrorReportMapper.insert(error);
    }

    /**
     * 被除数为0或null时转1
     * 
     * @param source 被除数
     * @return 结果
     */
    public static BigDecimal isNullToOne(BigDecimal source) {
        if (source == null || BigDecimal.ZERO.compareTo(source) == 0) {
            return BigDecimal.ONE;
        }
        return source;
    }

}
