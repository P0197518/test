package com.fujias.business.forms.p01;

import java.math.BigDecimal;

import com.fujias.common.db.entity.T0601BatchLog;
import com.fujias.common.entity.Pager;

/**
 * @author wangbaoxin
 *
 */
/**
 * @author FSPC274
 *
 */
public class P0122ProductbilityReportForm extends T0601BatchLog {

    private Pager pager;

    private String mode;

    private boolean adminRole;

    private String userId;

    private String year;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 执行状态
     */
    private String statusName;

    /**
     * 品名
     */
    private String itemName;

    /**
     * 汇总直接时间
     */
    private BigDecimal directTime;

    /**
     * 指示数量
     */
    private BigDecimal prodCount;

    /**
     * 部门默认工序数1
     */
    private BigDecimal depItemCount;

    /**
     * 部门默认工序数2
     */
    private BigDecimal dep2ItemCount;

    /**
     * 品目工序数量
     */
    private BigDecimal tenchnicsItemCount;

    /**
     * 品目冲压部门单独计算工序数量
     */
    private BigDecimal tenchnicsItemCountSys;

    /**
     * 品目取数工序设置有无
     */
    private boolean isItemSetting;

    /**
     * 是否冲压单独计算工序
     */
    private boolean isItemSys;

    /**
     * 修理数量：冲压情况下，备注是 【社外】的数量合计
     */
    private BigDecimal repairItemCount;

    /**
     * 机器时间
     */
    private BigDecimal machineTime;

    /**
     * 机器数量
     */
    private BigDecimal machineCount;

    /**
     * 粗研磨直接时间
     */
    private BigDecimal wideTime;

    /**
     * 粗研磨数量
     */
    private BigDecimal wideCount;

    /**
     * 中间研磨直接时间
     */
    private BigDecimal middleTime;

    /**
     * 中间研磨数量
     */
    private BigDecimal middleCount;

    /**
     * 乾式研磨直接时间
     */
    private BigDecimal driedTime;

    /**
     * 乾式研磨数量
     */
    private BigDecimal driedCount;

    /**
     * 精研磨直接时间
     */
    private BigDecimal fineTime;

    /**
     * 精研磨数量
     */
    private BigDecimal fineCount;

    /**
     * BC色研磨直接时间
     */
    private BigDecimal bcTime;

    /**
     * BC色研磨数量
     */
    private BigDecimal bcCount;

    /**
     * T2研磨直接时间
     */
    private BigDecimal t2Time;

    /**
     * T2研磨数量
     */
    private BigDecimal t2Count;

    /**
     * GM色研磨直接时间
     */
    private BigDecimal gmTime;

    /**
     * GM色研磨数量
     */
    private BigDecimal gmCount;

    /**
     * CC色研磨直接时间
     */
    private BigDecimal ccTime;

    /**
     * CC色研磨数量
     */
    private BigDecimal ccCount;

    /**
     * 新色精研磨直接时间
     */
    private BigDecimal newColorTime;

    /**
     * 新色精研磨数量
     */
    private BigDecimal newColorCount;

    /**
     * 自动化直接时间
     */
    private BigDecimal automationTime;
    /**
     * 自动化数量
     */
    private BigDecimal automationCount;

    /**
     * 他部门投入人员直接时间
     */
    private BigDecimal otherDepUserTime;

    /**
     * 协助他部门人员直接时间
     */
    private BigDecimal helpOtherDepUserTime;

    /**
     * 组立2部门投入人员间接时间 按照 DPS 60%,条形锁扣 10%，铅坠 10%，清洗10%，喷砂10% 比率分摊
     */
    private BigDecimal combinationDepUserTime;

    /**
     * 考勤总时间
     */
    private BigDecimal allWorkHours;

    /**
     * 部门月份总直接时间
     */
    private BigDecimal allDirectTime;

    /**
     * 考勤间接时间
     */
    private BigDecimal allIndirect;

    /**
     * 工作人员
     */
    private String workerName;

    /**
     * 是否直接或间接
     */
    private boolean shareFlag;

    /**
     * 总生产数量
     */
    private BigDecimal allProdCount;

    /**
     * 汇总间接时间
     */
    private BigDecimal indirectTime;

    /**
     * 指示编号
     */
    private String prodInstrNo;

    /**
     * 工序
     */
    private String technicsCD;

    /**
     * 是否存在
     */
    private boolean flag;

    private BigDecimal allCount;

    private BigDecimal allTime;

    private BigDecimal productbilityCount;

    private BigDecimal directProductbilityCount;

    private String strStartTime;

    private String strEndTime;

    /**
     * 社内修理直接时间
     */
    private BigDecimal inRepairTime;

    /**
     * 社外修理直接时间
     */
    private BigDecimal outRepairTime;

    /**
     * 社外修理数量
     */
    private BigDecimal outRepairCount;

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getProdCount() {
        return prodCount;
    }

    public void setProdCount(BigDecimal prodCount) {
        this.prodCount = prodCount;
    }

    public BigDecimal getDepItemCount() {
        return depItemCount;
    }

    public void setDepItemCount(BigDecimal depItemCount) {
        this.depItemCount = depItemCount;
    }

    public BigDecimal getDep2ItemCount() {
        return dep2ItemCount;
    }

    public void setDep2ItemCount(BigDecimal dep2ItemCount) {
        this.dep2ItemCount = dep2ItemCount;
    }

    public BigDecimal getTenchnicsItemCount() {
        return tenchnicsItemCount;
    }

    public void setTenchnicsItemCount(BigDecimal tenchnicsItemCount) {
        this.tenchnicsItemCount = tenchnicsItemCount;
    }

    public boolean isItemSetting() {
        return isItemSetting;
    }

    public void setItemSetting(boolean isItemSetting) {
        this.isItemSetting = isItemSetting;
    }

    public BigDecimal getRepairItemCount() {
        return repairItemCount;
    }

    public void setRepairItemCount(BigDecimal repairItemCount) {
        this.repairItemCount = repairItemCount;
    }

    public BigDecimal getMachineTime() {
        return machineTime;
    }

    public void setMachineTime(BigDecimal machineTime) {
        this.machineTime = machineTime;
    }

    public BigDecimal getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(BigDecimal machineCount) {
        this.machineCount = machineCount;
    }

    public BigDecimal getWideTime() {
        return wideTime;
    }

    public void setWideTime(BigDecimal wideTime) {
        this.wideTime = wideTime;
    }

    public BigDecimal getWideCount() {
        return wideCount;
    }

    public void setWideCount(BigDecimal wideCount) {
        this.wideCount = wideCount;
    }

    public BigDecimal getMiddleTime() {
        return middleTime;
    }

    public void setMiddleTime(BigDecimal middleTime) {
        this.middleTime = middleTime;
    }

    public BigDecimal getMiddleCount() {
        return middleCount;
    }

    public void setMiddleCount(BigDecimal middleCount) {
        this.middleCount = middleCount;
    }

    public BigDecimal getDriedTime() {
        return driedTime;
    }

    public void setDriedTime(BigDecimal driedTime) {
        this.driedTime = driedTime;
    }

    public BigDecimal getDriedCount() {
        return driedCount;
    }

    public void setDriedCount(BigDecimal driedCount) {
        this.driedCount = driedCount;
    }

    public BigDecimal getFineTime() {
        return fineTime;
    }

    public void setFineTime(BigDecimal fineTime) {
        this.fineTime = fineTime;
    }

    public BigDecimal getFineCount() {
        return fineCount;
    }

    public void setFineCount(BigDecimal fineCount) {
        this.fineCount = fineCount;
    }

    public BigDecimal getBcTime() {
        return bcTime;
    }

    public void setBcTime(BigDecimal bcTime) {
        this.bcTime = bcTime;
    }

    public BigDecimal getBcCount() {
        return bcCount;
    }

    public void setBcCount(BigDecimal bcCount) {
        this.bcCount = bcCount;
    }

    public BigDecimal getT2Time() {
        return t2Time;
    }

    public void setT2Time(BigDecimal t2Time) {
        this.t2Time = t2Time;
    }

    public BigDecimal getT2Count() {
        return t2Count;
    }

    public void setT2Count(BigDecimal t2Count) {
        this.t2Count = t2Count;
    }

    public BigDecimal getGmTime() {
        return gmTime;
    }

    public void setGmTime(BigDecimal gmTime) {
        this.gmTime = gmTime;
    }

    public BigDecimal getGmCount() {
        return gmCount;
    }

    public void setGmCount(BigDecimal gmCount) {
        this.gmCount = gmCount;
    }

    public BigDecimal getCcTime() {
        return ccTime;
    }

    public void setCcTime(BigDecimal ccTime) {
        this.ccTime = ccTime;
    }

    public BigDecimal getCcCount() {
        return ccCount;
    }

    public void setCcCount(BigDecimal ccCount) {
        this.ccCount = ccCount;
    }

    public BigDecimal getNewColorTime() {
        return newColorTime;
    }

    public void setNewColorTime(BigDecimal newColorTime) {
        this.newColorTime = newColorTime;
    }

    public BigDecimal getNewColorCount() {
        return newColorCount;
    }

    public void setNewColorCount(BigDecimal newColorCount) {
        this.newColorCount = newColorCount;
    }

    public BigDecimal getAutomationTime() {
        return automationTime;
    }

    public void setAutomationTime(BigDecimal automationTime) {
        this.automationTime = automationTime;
    }

    public BigDecimal getAutomationCount() {
        return automationCount;
    }

    public void setAutomationCount(BigDecimal automationCount) {
        this.automationCount = automationCount;
    }

    public BigDecimal getOtherDepUserTime() {
        return otherDepUserTime;
    }

    public void setOtherDepUserTime(BigDecimal otherDepUserTime) {
        this.otherDepUserTime = otherDepUserTime;
    }

    public BigDecimal getHelpOtherDepUserTime() {
        return helpOtherDepUserTime;
    }

    public void setHelpOtherDepUserTime(BigDecimal helpOtherDepUserTime) {
        this.helpOtherDepUserTime = helpOtherDepUserTime;
    }

    public BigDecimal getCombinationDepUserTime() {
        return combinationDepUserTime;
    }

    public void setCombinationDepUserTime(BigDecimal combinationDepUserTime) {
        this.combinationDepUserTime = combinationDepUserTime;
    }

    public BigDecimal getAllWorkHours() {
        return allWorkHours;
    }

    public void setAllWorkHours(BigDecimal allWorkHours) {
        this.allWorkHours = allWorkHours;
    }

    public BigDecimal getAllDirectTime() {
        return allDirectTime;
    }

    public void setAllDirectTime(BigDecimal allDirectTime) {
        this.allDirectTime = allDirectTime;
    }

    public BigDecimal getAllIndirect() {
        return allIndirect;
    }

    public void setAllIndirect(BigDecimal allIndirect) {
        this.allIndirect = allIndirect;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public boolean isShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(boolean shareFlag) {
        this.shareFlag = shareFlag;
    }

    public BigDecimal getAllProdCount() {
        return allProdCount;
    }

    public void setAllProdCount(BigDecimal allProdCount) {
        this.allProdCount = allProdCount;
    }

    public BigDecimal getDirectTime() {
        return directTime;
    }

    public void setDirectTime(BigDecimal directTime) {
        this.directTime = directTime;
    }

    public BigDecimal getIndirectTime() {
        return indirectTime;
    }

    public void setIndirectTime(BigDecimal indirectTime) {
        this.indirectTime = indirectTime;
    }

    public String getProdInstrNo() {
        return prodInstrNo;
    }

    public void setProdInstrNo(String prodInstrNo) {
        this.prodInstrNo = prodInstrNo;
    }

    public String getTechnicsCD() {
        return technicsCD;
    }

    public void setTechnicsCD(String technicsCD) {
        this.technicsCD = technicsCD;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isAdminRole() {
        return adminRole;
    }

    public void setAdminRole(boolean adminRole) {
        this.adminRole = adminRole;
    }

    public BigDecimal getAllCount() {
        return allCount;
    }

    public void setAllCount(BigDecimal allCount) {
        this.allCount = allCount;
    }

    public BigDecimal getAllTime() {
        return allTime;
    }

    public void setAllTime(BigDecimal allTime) {
        this.allTime = allTime;
    }

    public BigDecimal getProductbilityCount() {
        return productbilityCount;
    }

    public void setProductbilityCount(BigDecimal productbilityCount) {
        this.productbilityCount = productbilityCount;
    }

    public BigDecimal getDirectProductbilityCount() {
        return directProductbilityCount;
    }

    public void setDirectProductbilityCount(BigDecimal directProductbilityCount) {
        this.directProductbilityCount = directProductbilityCount;
    }

    public String getStrStartTime() {
        return strStartTime;
    }

    public void setStrStartTime(String strStartTime) {
        this.strStartTime = strStartTime;
    }

    public String getStrEndTime() {
        return strEndTime;
    }

    public void setStrEndTime(String strEndTime) {
        this.strEndTime = strEndTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTenchnicsItemCountSys() {
        return tenchnicsItemCountSys;
    }

    public void setTenchnicsItemCountSys(BigDecimal tenchnicsItemCountSys) {
        this.tenchnicsItemCountSys = tenchnicsItemCountSys;
    }

    public boolean isItemSys() {
        return isItemSys;
    }

    public void setItemSys(boolean isItemSys) {
        this.isItemSys = isItemSys;
    }

    public BigDecimal getInRepairTime() {
        return inRepairTime;
    }

    public void setInRepairTime(BigDecimal inRepairTime) {
        this.inRepairTime = inRepairTime;
    }

    public BigDecimal getOutRepairTime() {
        return outRepairTime;
    }

    public void setOutRepairTime(BigDecimal outRepairTime) {
        this.outRepairTime = outRepairTime;
    }

    public BigDecimal getOutRepairCount() {
        return outRepairCount;
    }

    public void setOutRepairCount(BigDecimal outRepairCount) {
        this.outRepairCount = outRepairCount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
