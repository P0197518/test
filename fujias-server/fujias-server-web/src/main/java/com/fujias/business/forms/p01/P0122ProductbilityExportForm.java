package com.fujias.business.forms.p01;

import java.math.BigDecimal;

/**
 * 年度生产性导出用
 * 
 * @author wangbaoxin
 *
 */
public class P0122ProductbilityExportForm {

    private String itemName;
    private String countFormula;

    private BigDecimal month1prodCount;
    private BigDecimal month2prodCount;
    private BigDecimal month3prodCount;
    private BigDecimal month4prodCount;
    private BigDecimal month5prodCount;
    private BigDecimal month6prodCount;
    private BigDecimal month7prodCount;
    private BigDecimal month8prodCount;
    private BigDecimal month9prodCount;
    private BigDecimal month10prodCount;
    private BigDecimal month11prodCount;
    private BigDecimal month12prodCount;

    private BigDecimal month1directTime;
    private BigDecimal month2directTime;
    private BigDecimal month3directTime;
    private BigDecimal month4directTime;
    private BigDecimal month5directTime;
    private BigDecimal month6directTime;
    private BigDecimal month7directTime;
    private BigDecimal month8directTime;
    private BigDecimal month9directTime;
    private BigDecimal month10directTime;
    private BigDecimal month11directTime;
    private BigDecimal month12directTime;

    private BigDecimal month1indirectTime;
    private BigDecimal month2indirectTime;
    private BigDecimal month3indirectTime;
    private BigDecimal month4indirectTime;
    private BigDecimal month5indirectTime;
    private BigDecimal month6indirectTime;
    private BigDecimal month7indirectTime;
    private BigDecimal month8indirectTime;
    private BigDecimal month9indirectTime;
    private BigDecimal month10indirectTime;
    private BigDecimal month11indirectTime;
    private BigDecimal month12indirectTime;

    private BigDecimal month1grindCount;
    private BigDecimal month2grindCount;
    private BigDecimal month3grindCount;
    private BigDecimal month4grindCount;
    private BigDecimal month5grindCount;
    private BigDecimal month6grindCount;
    private BigDecimal month7grindCount;
    private BigDecimal month8grindCount;
    private BigDecimal month9grindCount;
    private BigDecimal month10grindCount;
    private BigDecimal month11grindCount;
    private BigDecimal month12grindCount;

    private BigDecimal month1wideTime;
    private BigDecimal month2wideTime;
    private BigDecimal month3wideTime;
    private BigDecimal month4wideTime;
    private BigDecimal month5wideTime;
    private BigDecimal month6wideTime;
    private BigDecimal month7wideTime;
    private BigDecimal month8wideTime;
    private BigDecimal month9wideTime;
    private BigDecimal month10wideTime;
    private BigDecimal month11wideTime;
    private BigDecimal month12wideTime;

    private BigDecimal month1middleTime;
    private BigDecimal month2middleTime;
    private BigDecimal month3middleTime;
    private BigDecimal month4middleTime;
    private BigDecimal month5middleTime;
    private BigDecimal month6middleTime;
    private BigDecimal month7middleTime;
    private BigDecimal month8middleTime;
    private BigDecimal month9middleTime;
    private BigDecimal month10middleTime;
    private BigDecimal month11middleTime;
    private BigDecimal month12middleTime;

    private BigDecimal month1driedTime;
    private BigDecimal month2driedTime;
    private BigDecimal month3driedTime;
    private BigDecimal month4driedTime;
    private BigDecimal month5driedTime;
    private BigDecimal month6driedTime;
    private BigDecimal month7driedTime;
    private BigDecimal month8driedTime;
    private BigDecimal month9driedTime;
    private BigDecimal month10driedTime;
    private BigDecimal month11driedTime;
    private BigDecimal month12driedTime;

    private BigDecimal month1fineTime;
    private BigDecimal month2fineTime;
    private BigDecimal month3fineTime;
    private BigDecimal month4fineTime;
    private BigDecimal month5fineTime;
    private BigDecimal month6fineTime;
    private BigDecimal month7fineTime;
    private BigDecimal month8fineTime;
    private BigDecimal month9fineTime;
    private BigDecimal month10fineTime;
    private BigDecimal month11fineTime;
    private BigDecimal month12fineTime;

    private BigDecimal month1t2Time;
    private BigDecimal month2t2Time;
    private BigDecimal month3t2Time;
    private BigDecimal month4t2Time;
    private BigDecimal month5t2Time;
    private BigDecimal month6t2Time;
    private BigDecimal month7t2Time;
    private BigDecimal month8t2Time;
    private BigDecimal month9t2Time;
    private BigDecimal month10t2Time;
    private BigDecimal month11t2Time;
    private BigDecimal month12t2Time;

    private BigDecimal month1bcTime;
    private BigDecimal month2bcTime;
    private BigDecimal month3bcTime;
    private BigDecimal month4bcTime;
    private BigDecimal month5bcTime;
    private BigDecimal month6bcTime;
    private BigDecimal month7bcTime;
    private BigDecimal month8bcTime;
    private BigDecimal month9bcTime;
    private BigDecimal month10bcTime;
    private BigDecimal month11bcTime;
    private BigDecimal month12bcTime;

    private BigDecimal month1gmTime;
    private BigDecimal month2gmTime;
    private BigDecimal month3gmTime;
    private BigDecimal month4gmTime;
    private BigDecimal month5gmTime;
    private BigDecimal month6gmTime;
    private BigDecimal month7gmTime;
    private BigDecimal month8gmTime;
    private BigDecimal month9gmTime;
    private BigDecimal month10gmTime;
    private BigDecimal month11gmTime;
    private BigDecimal month12gmTime;

    private BigDecimal month1ccTime;
    private BigDecimal month2ccTime;
    private BigDecimal month3ccTime;
    private BigDecimal month4ccTime;
    private BigDecimal month5ccTime;
    private BigDecimal month6ccTime;
    private BigDecimal month7ccTime;
    private BigDecimal month8ccTime;
    private BigDecimal month9ccTime;
    private BigDecimal month10ccTime;
    private BigDecimal month11ccTime;
    private BigDecimal month12ccTime;

    private BigDecimal month1newColorTime;
    private BigDecimal month2newColorTime;
    private BigDecimal month3newColorTime;
    private BigDecimal month4newColorTime;
    private BigDecimal month5newColorTime;
    private BigDecimal month6newColorTime;
    private BigDecimal month7newColorTime;
    private BigDecimal month8newColorTime;
    private BigDecimal month9newColorTime;
    private BigDecimal month10newColorTime;
    private BigDecimal month11newColorTime;
    private BigDecimal month12newColorTime;

    // 全年总生产数量
    @SuppressWarnings("unused")
    private BigDecimal allYearProdCount;
    // 全年设备总生产数量
    @SuppressWarnings("unused")
    private BigDecimal allYearGrindCount;
    // 全年总时间（直接）
    @SuppressWarnings("unused")
    private BigDecimal allYearProdCountDirectTime;
    // 全年总时间（直接+间接）
    @SuppressWarnings("unused")
    private BigDecimal allYearProdCountAllTime;
    // 全年总设备时间
    @SuppressWarnings("unused")
    private BigDecimal allYearRobitTime;

    // 总生产性（直接+间接）
    @SuppressWarnings("unused")
    private BigDecimal allYearbility;
    // 总生产性（直接）
    @SuppressWarnings("unused")
    private BigDecimal allYearbilityDirectTime;
    // 总生产性（设备）
    @SuppressWarnings("unused")
    private BigDecimal allYearbilityRobit;

    /**
     * 总生产性（直接+间接）
     * 
     * @return 总生产性（直接+间接）
     */
    public BigDecimal getAllYearbility() {
        return this.getAllYearProdCount().divide(this.getAllYearProdCountAllTime(), 9, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("60"))
                        .setScale(0, BigDecimal.ROUND_UP);
    }

    public void setAllYearbility(BigDecimal allYearbility) {
        this.allYearbility = allYearbility;
    }

    /**
     * 总生产性（直接）
     * 
     * @return 总生产性（直接）
     */
    public BigDecimal getAllYearbilityDirectTime() {
        return this.getAllYearProdCount().divide(this.getAllYearProdCountDirectTime(), 9, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("60"))
                        .setScale(0, BigDecimal.ROUND_UP);
    }

    public void setAllYearbilityDirectTime(BigDecimal allYearbilityDirectTime) {
        this.allYearbilityDirectTime = allYearbilityDirectTime;
    }

    /**
     * 总生产性（设备）
     * 
     * @return 总生产性（设备）
     */
    public BigDecimal getAllYearbilityRobit() {
        return this.getAllYearGrindCount().divide(this.getAllYearRobitTime(), 9, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("60")).setScale(0,
                        BigDecimal.ROUND_UP);
    }

    public void setAllYearbilityRobit(BigDecimal allYearbilityRobit) {
        this.allYearbilityRobit = allYearbilityRobit;
    }

    /**
     * 全年总生产数量
     * 
     * @return 全年总生产数量
     */
    public BigDecimal getAllYearProdCount() {
        return month1prodCount.add(month2prodCount).add(month3prodCount).add(month4prodCount).add(month5prodCount).add(month6prodCount)
                        .add(month7prodCount).add(month8prodCount).add(month9prodCount).add(month10prodCount).add(month11prodCount)
                        .add(month12prodCount);
    }

    public void setAllYearProdCount(BigDecimal allYearProdCount) {
        this.allYearProdCount = allYearProdCount;
    }

    /**
     * 全年设备总生产数量
     * 
     * @return 全年设备总生产数量
     */
    public BigDecimal getAllYearGrindCount() {
        return month1grindCount.add(month2grindCount).add(month3grindCount).add(month4grindCount).add(month5grindCount).add(month6grindCount)
                        .add(month7grindCount).add(month8grindCount).add(month9grindCount).add(month10grindCount).add(month11grindCount)
                        .add(month12grindCount);
    }

    public void setAllYearGrindCount(BigDecimal allYearGrindCount) {
        this.allYearGrindCount = allYearGrindCount;
    }

    /**
     * 全年总时间（直接）
     * 
     * @return 全年总时间（直接）
     */
    public BigDecimal getAllYearProdCountDirectTime() {
        return month1directTime.add(month2directTime).add(month3directTime).add(month4directTime).add(month5directTime).add(month6directTime)
                        .add(month7directTime).add(month8directTime).add(month9directTime).add(month10directTime).add(month11directTime)
                        .add(month12directTime);
    }

    public void setAllYearProdCountDirectTime(BigDecimal allYearProdCountDirectTime) {
        this.allYearProdCountDirectTime = allYearProdCountDirectTime;
    }

    /**
     * 全年总时间（直接+间接）
     * 
     * @return 全年总时间（直接+间接）
     */
    public BigDecimal getAllYearProdCountAllTime() {
        return month1directTime.add(month2directTime).add(month3directTime).add(month4directTime).add(month5directTime).add(month6directTime)
                        .add(month7directTime).add(month8directTime).add(month9directTime).add(month10directTime).add(month11directTime)
                        .add(month12directTime).add(month1indirectTime).add(month2indirectTime).add(month3indirectTime).add(month4indirectTime)
                        .add(month5indirectTime).add(month6indirectTime).add(month7indirectTime).add(month8indirectTime).add(month9indirectTime)
                        .add(month10indirectTime).add(month11indirectTime).add(month12indirectTime);
    }

    public void setAllYearProdCountAllTime(BigDecimal allYearProdCountAllTime) {
        this.allYearProdCountAllTime = allYearProdCountAllTime;
    }

    /**
     * 全年总设备时间
     * 
     * @return 全年总设备时间
     */
    public BigDecimal getAllYearRobitTime() {
        return month1wideTime.add(month2wideTime).add(month3wideTime).add(month4wideTime).add(month5wideTime).add(month6wideTime).add(month7wideTime)
                        .add(month8wideTime).add(month9wideTime).add(month10wideTime).add(month11wideTime).add(month12wideTime).add(month1middleTime)
                        .add(month2middleTime).add(month3middleTime).add(month4middleTime).add(month5middleTime).add(month6middleTime)
                        .add(month7middleTime).add(month8middleTime).add(month9middleTime).add(month10middleTime).add(month11middleTime)
                        .add(month12middleTime).add(month1driedTime).add(month2driedTime).add(month3driedTime).add(month4driedTime)
                        .add(month5driedTime).add(month6driedTime).add(month7driedTime).add(month8driedTime).add(month9driedTime)
                        .add(month10driedTime).add(month11driedTime).add(month12driedTime).add(month1fineTime).add(month2fineTime).add(month3fineTime)
                        .add(month4fineTime).add(month5fineTime).add(month6fineTime).add(month7fineTime).add(month8fineTime).add(month9fineTime)
                        .add(month10fineTime).add(month11fineTime).add(month12fineTime).add(month1t2Time).add(month2t2Time).add(month3t2Time)
                        .add(month4t2Time).add(month5t2Time).add(month6t2Time).add(month7t2Time).add(month8t2Time).add(month9t2Time)
                        .add(month10t2Time).add(month11t2Time).add(month12t2Time).add(month1bcTime).add(month2bcTime).add(month3bcTime)
                        .add(month4bcTime).add(month5bcTime).add(month6bcTime).add(month7bcTime).add(month8bcTime).add(month9bcTime)
                        .add(month10bcTime).add(month11bcTime).add(month12bcTime).add(month1gmTime).add(month2gmTime).add(month3gmTime)
                        .add(month4gmTime).add(month5gmTime).add(month6gmTime).add(month7gmTime).add(month8gmTime).add(month9gmTime)
                        .add(month10gmTime).add(month11gmTime).add(month12gmTime).add(month1ccTime).add(month2ccTime).add(month3ccTime)
                        .add(month4ccTime).add(month5ccTime).add(month6ccTime).add(month7ccTime).add(month8ccTime).add(month9ccTime)
                        .add(month10ccTime).add(month11ccTime).add(month12ccTime).add(month1newColorTime).add(month2newColorTime)
                        .add(month3newColorTime).add(month4newColorTime).add(month5newColorTime).add(month6newColorTime).add(month7newColorTime)
                        .add(month8newColorTime).add(month9newColorTime).add(month10newColorTime).add(month11newColorTime).add(month12newColorTime);
    }

    public void setAllYearRobitTime(BigDecimal allYearRobitTime) {
        this.allYearRobitTime = allYearRobitTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getMonth1prodCount() {
        return month1prodCount;
    }

    public void setMonth1prodCount(BigDecimal month1prodCount) {
        this.month1prodCount = month1prodCount;
    }

    public BigDecimal getMonth2prodCount() {
        return month2prodCount;
    }

    public void setMonth2prodCount(BigDecimal month2prodCount) {
        this.month2prodCount = month2prodCount;
    }

    public BigDecimal getMonth3prodCount() {
        return month3prodCount;
    }

    public void setMonth3prodCount(BigDecimal month3prodCount) {
        this.month3prodCount = month3prodCount;
    }

    public BigDecimal getMonth4prodCount() {
        return month4prodCount;
    }

    public void setMonth4prodCount(BigDecimal month4prodCount) {
        this.month4prodCount = month4prodCount;
    }

    public BigDecimal getMonth5prodCount() {
        return month5prodCount;
    }

    public void setMonth5prodCount(BigDecimal month5prodCount) {
        this.month5prodCount = month5prodCount;
    }

    public BigDecimal getMonth6prodCount() {
        return month6prodCount;
    }

    public void setMonth6prodCount(BigDecimal month6prodCount) {
        this.month6prodCount = month6prodCount;
    }

    public BigDecimal getMonth7prodCount() {
        return month7prodCount;
    }

    public void setMonth7prodCount(BigDecimal month7prodCount) {
        this.month7prodCount = month7prodCount;
    }

    public BigDecimal getMonth8prodCount() {
        return month8prodCount;
    }

    public void setMonth8prodCount(BigDecimal month8prodCount) {
        this.month8prodCount = month8prodCount;
    }

    public BigDecimal getMonth9prodCount() {
        return month9prodCount;
    }

    public void setMonth9prodCount(BigDecimal month9prodCount) {
        this.month9prodCount = month9prodCount;
    }

    public BigDecimal getMonth10prodCount() {
        return month10prodCount;
    }

    public void setMonth10prodCount(BigDecimal month10prodCount) {
        this.month10prodCount = month10prodCount;
    }

    public BigDecimal getMonth11prodCount() {
        return month11prodCount;
    }

    public void setMonth11prodCount(BigDecimal month11prodCount) {
        this.month11prodCount = month11prodCount;
    }

    public BigDecimal getMonth12prodCount() {
        return month12prodCount;
    }

    public void setMonth12prodCount(BigDecimal month12prodCount) {
        this.month12prodCount = month12prodCount;
    }

    public BigDecimal getMonth1directTime() {
        return month1directTime;
    }

    public void setMonth1directTime(BigDecimal month1directTime) {
        this.month1directTime = month1directTime;
    }

    public BigDecimal getMonth2directTime() {
        return month2directTime;
    }

    public void setMonth2directTime(BigDecimal month2directTime) {
        this.month2directTime = month2directTime;
    }

    public BigDecimal getMonth3directTime() {
        return month3directTime;
    }

    public void setMonth3directTime(BigDecimal month3directTime) {
        this.month3directTime = month3directTime;
    }

    public BigDecimal getMonth4directTime() {
        return month4directTime;
    }

    public void setMonth4directTime(BigDecimal month4directTime) {
        this.month4directTime = month4directTime;
    }

    public BigDecimal getMonth5directTime() {
        return month5directTime;
    }

    public void setMonth5directTime(BigDecimal month5directTime) {
        this.month5directTime = month5directTime;
    }

    public BigDecimal getMonth6directTime() {
        return month6directTime;
    }

    public void setMonth6directTime(BigDecimal month6directTime) {
        this.month6directTime = month6directTime;
    }

    public BigDecimal getMonth7directTime() {
        return month7directTime;
    }

    public void setMonth7directTime(BigDecimal month7directTime) {
        this.month7directTime = month7directTime;
    }

    public BigDecimal getMonth8directTime() {
        return month8directTime;
    }

    public void setMonth8directTime(BigDecimal month8directTime) {
        this.month8directTime = month8directTime;
    }

    public BigDecimal getMonth9directTime() {
        return month9directTime;
    }

    public void setMonth9directTime(BigDecimal month9directTime) {
        this.month9directTime = month9directTime;
    }

    public BigDecimal getMonth10directTime() {
        return month10directTime;
    }

    public void setMonth10directTime(BigDecimal month10directTime) {
        this.month10directTime = month10directTime;
    }

    public BigDecimal getMonth11directTime() {
        return month11directTime;
    }

    public void setMonth11directTime(BigDecimal month11directTime) {
        this.month11directTime = month11directTime;
    }

    public BigDecimal getMonth12directTime() {
        return month12directTime;
    }

    public void setMonth12directTime(BigDecimal month12directTime) {
        this.month12directTime = month12directTime;
    }

    public BigDecimal getMonth1indirectTime() {
        return month1indirectTime;
    }

    public void setMonth1indirectTime(BigDecimal month1indirectTime) {
        this.month1indirectTime = month1indirectTime;
    }

    public BigDecimal getMonth2indirectTime() {
        return month2indirectTime;
    }

    public void setMonth2indirectTime(BigDecimal month2indirectTime) {
        this.month2indirectTime = month2indirectTime;
    }

    public BigDecimal getMonth3indirectTime() {
        return month3indirectTime;
    }

    public void setMonth3indirectTime(BigDecimal month3indirectTime) {
        this.month3indirectTime = month3indirectTime;
    }

    public BigDecimal getMonth4indirectTime() {
        return month4indirectTime;
    }

    public void setMonth4indirectTime(BigDecimal month4indirectTime) {
        this.month4indirectTime = month4indirectTime;
    }

    public BigDecimal getMonth5indirectTime() {
        return month5indirectTime;
    }

    public void setMonth5indirectTime(BigDecimal month5indirectTime) {
        this.month5indirectTime = month5indirectTime;
    }

    public BigDecimal getMonth6indirectTime() {
        return month6indirectTime;
    }

    public void setMonth6indirectTime(BigDecimal month6indirectTime) {
        this.month6indirectTime = month6indirectTime;
    }

    public BigDecimal getMonth7indirectTime() {
        return month7indirectTime;
    }

    public void setMonth7indirectTime(BigDecimal month7indirectTime) {
        this.month7indirectTime = month7indirectTime;
    }

    public BigDecimal getMonth8indirectTime() {
        return month8indirectTime;
    }

    public void setMonth8indirectTime(BigDecimal month8indirectTime) {
        this.month8indirectTime = month8indirectTime;
    }

    public BigDecimal getMonth9indirectTime() {
        return month9indirectTime;
    }

    public void setMonth9indirectTime(BigDecimal month9indirectTime) {
        this.month9indirectTime = month9indirectTime;
    }

    public BigDecimal getMonth10indirectTime() {
        return month10indirectTime;
    }

    public void setMonth10indirectTime(BigDecimal month10indirectTime) {
        this.month10indirectTime = month10indirectTime;
    }

    public BigDecimal getMonth11indirectTime() {
        return month11indirectTime;
    }

    public void setMonth11indirectTime(BigDecimal month11indirectTime) {
        this.month11indirectTime = month11indirectTime;
    }

    public BigDecimal getMonth12indirectTime() {
        return month12indirectTime;
    }

    public void setMonth12indirectTime(BigDecimal month12indirectTime) {
        this.month12indirectTime = month12indirectTime;
    }

    public BigDecimal getMonth1grindCount() {
        return month1grindCount;
    }

    public void setMonth1grindCount(BigDecimal month1grindCount) {
        this.month1grindCount = month1grindCount;
    }

    public BigDecimal getMonth2grindCount() {
        return month2grindCount;
    }

    public void setMonth2grindCount(BigDecimal month2grindCount) {
        this.month2grindCount = month2grindCount;
    }

    public BigDecimal getMonth3grindCount() {
        return month3grindCount;
    }

    public void setMonth3grindCount(BigDecimal month3grindCount) {
        this.month3grindCount = month3grindCount;
    }

    public BigDecimal getMonth4grindCount() {
        return month4grindCount;
    }

    public void setMonth4grindCount(BigDecimal month4grindCount) {
        this.month4grindCount = month4grindCount;
    }

    public BigDecimal getMonth5grindCount() {
        return month5grindCount;
    }

    public void setMonth5grindCount(BigDecimal month5grindCount) {
        this.month5grindCount = month5grindCount;
    }

    public BigDecimal getMonth6grindCount() {
        return month6grindCount;
    }

    public void setMonth6grindCount(BigDecimal month6grindCount) {
        this.month6grindCount = month6grindCount;
    }

    public BigDecimal getMonth7grindCount() {
        return month7grindCount;
    }

    public void setMonth7grindCount(BigDecimal month7grindCount) {
        this.month7grindCount = month7grindCount;
    }

    public BigDecimal getMonth8grindCount() {
        return month8grindCount;
    }

    public void setMonth8grindCount(BigDecimal month8grindCount) {
        this.month8grindCount = month8grindCount;
    }

    public BigDecimal getMonth9grindCount() {
        return month9grindCount;
    }

    public void setMonth9grindCount(BigDecimal month9grindCount) {
        this.month9grindCount = month9grindCount;
    }

    public BigDecimal getMonth10grindCount() {
        return month10grindCount;
    }

    public void setMonth10grindCount(BigDecimal month10grindCount) {
        this.month10grindCount = month10grindCount;
    }

    public BigDecimal getMonth11grindCount() {
        return month11grindCount;
    }

    public void setMonth11grindCount(BigDecimal month11grindCount) {
        this.month11grindCount = month11grindCount;
    }

    public BigDecimal getMonth12grindCount() {
        return month12grindCount;
    }

    public void setMonth12grindCount(BigDecimal month12grindCount) {
        this.month12grindCount = month12grindCount;
    }

    public BigDecimal getMonth1wideTime() {
        return month1wideTime;
    }

    public void setMonth1wideTime(BigDecimal month1wideTime) {
        this.month1wideTime = month1wideTime;
    }

    public BigDecimal getMonth2wideTime() {
        return month2wideTime;
    }

    public void setMonth2wideTime(BigDecimal month2wideTime) {
        this.month2wideTime = month2wideTime;
    }

    public BigDecimal getMonth3wideTime() {
        return month3wideTime;
    }

    public void setMonth3wideTime(BigDecimal month3wideTime) {
        this.month3wideTime = month3wideTime;
    }

    public BigDecimal getMonth4wideTime() {
        return month4wideTime;
    }

    public void setMonth4wideTime(BigDecimal month4wideTime) {
        this.month4wideTime = month4wideTime;
    }

    public BigDecimal getMonth5wideTime() {
        return month5wideTime;
    }

    public void setMonth5wideTime(BigDecimal month5wideTime) {
        this.month5wideTime = month5wideTime;
    }

    public BigDecimal getMonth6wideTime() {
        return month6wideTime;
    }

    public void setMonth6wideTime(BigDecimal month6wideTime) {
        this.month6wideTime = month6wideTime;
    }

    public BigDecimal getMonth7wideTime() {
        return month7wideTime;
    }

    public void setMonth7wideTime(BigDecimal month7wideTime) {
        this.month7wideTime = month7wideTime;
    }

    public BigDecimal getMonth8wideTime() {
        return month8wideTime;
    }

    public void setMonth8wideTime(BigDecimal month8wideTime) {
        this.month8wideTime = month8wideTime;
    }

    public BigDecimal getMonth9wideTime() {
        return month9wideTime;
    }

    public void setMonth9wideTime(BigDecimal month9wideTime) {
        this.month9wideTime = month9wideTime;
    }

    public BigDecimal getMonth10wideTime() {
        return month10wideTime;
    }

    public void setMonth10wideTime(BigDecimal month10wideTime) {
        this.month10wideTime = month10wideTime;
    }

    public BigDecimal getMonth11wideTime() {
        return month11wideTime;
    }

    public void setMonth11wideTime(BigDecimal month11wideTime) {
        this.month11wideTime = month11wideTime;
    }

    public BigDecimal getMonth12wideTime() {
        return month12wideTime;
    }

    public void setMonth12wideTime(BigDecimal month12wideTime) {
        this.month12wideTime = month12wideTime;
    }

    public BigDecimal getMonth1middleTime() {
        return month1middleTime;
    }

    public void setMonth1middleTime(BigDecimal month1middleTime) {
        this.month1middleTime = month1middleTime;
    }

    public BigDecimal getMonth2middleTime() {
        return month2middleTime;
    }

    public void setMonth2middleTime(BigDecimal month2middleTime) {
        this.month2middleTime = month2middleTime;
    }

    public BigDecimal getMonth3middleTime() {
        return month3middleTime;
    }

    public void setMonth3middleTime(BigDecimal month3middleTime) {
        this.month3middleTime = month3middleTime;
    }

    public BigDecimal getMonth4middleTime() {
        return month4middleTime;
    }

    public void setMonth4middleTime(BigDecimal month4middleTime) {
        this.month4middleTime = month4middleTime;
    }

    public BigDecimal getMonth5middleTime() {
        return month5middleTime;
    }

    public void setMonth5middleTime(BigDecimal month5middleTime) {
        this.month5middleTime = month5middleTime;
    }

    public BigDecimal getMonth6middleTime() {
        return month6middleTime;
    }

    public void setMonth6middleTime(BigDecimal month6middleTime) {
        this.month6middleTime = month6middleTime;
    }

    public BigDecimal getMonth7middleTime() {
        return month7middleTime;
    }

    public void setMonth7middleTime(BigDecimal month7middleTime) {
        this.month7middleTime = month7middleTime;
    }

    public BigDecimal getMonth8middleTime() {
        return month8middleTime;
    }

    public void setMonth8middleTime(BigDecimal month8middleTime) {
        this.month8middleTime = month8middleTime;
    }

    public BigDecimal getMonth9middleTime() {
        return month9middleTime;
    }

    public void setMonth9middleTime(BigDecimal month9middleTime) {
        this.month9middleTime = month9middleTime;
    }

    public BigDecimal getMonth10middleTime() {
        return month10middleTime;
    }

    public void setMonth10middleTime(BigDecimal month10middleTime) {
        this.month10middleTime = month10middleTime;
    }

    public BigDecimal getMonth11middleTime() {
        return month11middleTime;
    }

    public void setMonth11middleTime(BigDecimal month11middleTime) {
        this.month11middleTime = month11middleTime;
    }

    public BigDecimal getMonth12middleTime() {
        return month12middleTime;
    }

    public void setMonth12middleTime(BigDecimal month12middleTime) {
        this.month12middleTime = month12middleTime;
    }

    public BigDecimal getMonth1driedTime() {
        return month1driedTime;
    }

    public void setMonth1driedTime(BigDecimal month1driedTime) {
        this.month1driedTime = month1driedTime;
    }

    public BigDecimal getMonth2driedTime() {
        return month2driedTime;
    }

    public void setMonth2driedTime(BigDecimal month2driedTime) {
        this.month2driedTime = month2driedTime;
    }

    public BigDecimal getMonth3driedTime() {
        return month3driedTime;
    }

    public void setMonth3driedTime(BigDecimal month3driedTime) {
        this.month3driedTime = month3driedTime;
    }

    public BigDecimal getMonth4driedTime() {
        return month4driedTime;
    }

    public void setMonth4driedTime(BigDecimal month4driedTime) {
        this.month4driedTime = month4driedTime;
    }

    public BigDecimal getMonth5driedTime() {
        return month5driedTime;
    }

    public void setMonth5driedTime(BigDecimal month5driedTime) {
        this.month5driedTime = month5driedTime;
    }

    public BigDecimal getMonth6driedTime() {
        return month6driedTime;
    }

    public void setMonth6driedTime(BigDecimal month6driedTime) {
        this.month6driedTime = month6driedTime;
    }

    public BigDecimal getMonth7driedTime() {
        return month7driedTime;
    }

    public void setMonth7driedTime(BigDecimal month7driedTime) {
        this.month7driedTime = month7driedTime;
    }

    public BigDecimal getMonth8driedTime() {
        return month8driedTime;
    }

    public void setMonth8driedTime(BigDecimal month8driedTime) {
        this.month8driedTime = month8driedTime;
    }

    public BigDecimal getMonth9driedTime() {
        return month9driedTime;
    }

    public void setMonth9driedTime(BigDecimal month9driedTime) {
        this.month9driedTime = month9driedTime;
    }

    public BigDecimal getMonth10driedTime() {
        return month10driedTime;
    }

    public void setMonth10driedTime(BigDecimal month10driedTime) {
        this.month10driedTime = month10driedTime;
    }

    public BigDecimal getMonth11driedTime() {
        return month11driedTime;
    }

    public void setMonth11driedTime(BigDecimal month11driedTime) {
        this.month11driedTime = month11driedTime;
    }

    public BigDecimal getMonth12driedTime() {
        return month12driedTime;
    }

    public void setMonth12driedTime(BigDecimal month12driedTime) {
        this.month12driedTime = month12driedTime;
    }

    public BigDecimal getMonth1fineTime() {
        return month1fineTime;
    }

    public void setMonth1fineTime(BigDecimal month1fineTime) {
        this.month1fineTime = month1fineTime;
    }

    public BigDecimal getMonth2fineTime() {
        return month2fineTime;
    }

    public void setMonth2fineTime(BigDecimal month2fineTime) {
        this.month2fineTime = month2fineTime;
    }

    public BigDecimal getMonth3fineTime() {
        return month3fineTime;
    }

    public void setMonth3fineTime(BigDecimal month3fineTime) {
        this.month3fineTime = month3fineTime;
    }

    public BigDecimal getMonth4fineTime() {
        return month4fineTime;
    }

    public void setMonth4fineTime(BigDecimal month4fineTime) {
        this.month4fineTime = month4fineTime;
    }

    public BigDecimal getMonth5fineTime() {
        return month5fineTime;
    }

    public void setMonth5fineTime(BigDecimal month5fineTime) {
        this.month5fineTime = month5fineTime;
    }

    public BigDecimal getMonth6fineTime() {
        return month6fineTime;
    }

    public void setMonth6fineTime(BigDecimal month6fineTime) {
        this.month6fineTime = month6fineTime;
    }

    public BigDecimal getMonth7fineTime() {
        return month7fineTime;
    }

    public void setMonth7fineTime(BigDecimal month7fineTime) {
        this.month7fineTime = month7fineTime;
    }

    public BigDecimal getMonth8fineTime() {
        return month8fineTime;
    }

    public void setMonth8fineTime(BigDecimal month8fineTime) {
        this.month8fineTime = month8fineTime;
    }

    public BigDecimal getMonth9fineTime() {
        return month9fineTime;
    }

    public void setMonth9fineTime(BigDecimal month9fineTime) {
        this.month9fineTime = month9fineTime;
    }

    public BigDecimal getMonth10fineTime() {
        return month10fineTime;
    }

    public void setMonth10fineTime(BigDecimal month10fineTime) {
        this.month10fineTime = month10fineTime;
    }

    public BigDecimal getMonth11fineTime() {
        return month11fineTime;
    }

    public void setMonth11fineTime(BigDecimal month11fineTime) {
        this.month11fineTime = month11fineTime;
    }

    public BigDecimal getMonth12fineTime() {
        return month12fineTime;
    }

    public void setMonth12fineTime(BigDecimal month12fineTime) {
        this.month12fineTime = month12fineTime;
    }

    public BigDecimal getMonth1t2Time() {
        return month1t2Time;
    }

    public void setMonth1t2Time(BigDecimal month1t2Time) {
        this.month1t2Time = month1t2Time;
    }

    public BigDecimal getMonth2t2Time() {
        return month2t2Time;
    }

    public void setMonth2t2Time(BigDecimal month2t2Time) {
        this.month2t2Time = month2t2Time;
    }

    public BigDecimal getMonth3t2Time() {
        return month3t2Time;
    }

    public void setMonth3t2Time(BigDecimal month3t2Time) {
        this.month3t2Time = month3t2Time;
    }

    public BigDecimal getMonth4t2Time() {
        return month4t2Time;
    }

    public void setMonth4t2Time(BigDecimal month4t2Time) {
        this.month4t2Time = month4t2Time;
    }

    public BigDecimal getMonth5t2Time() {
        return month5t2Time;
    }

    public void setMonth5t2Time(BigDecimal month5t2Time) {
        this.month5t2Time = month5t2Time;
    }

    public BigDecimal getMonth6t2Time() {
        return month6t2Time;
    }

    public void setMonth6t2Time(BigDecimal month6t2Time) {
        this.month6t2Time = month6t2Time;
    }

    public BigDecimal getMonth7t2Time() {
        return month7t2Time;
    }

    public void setMonth7t2Time(BigDecimal month7t2Time) {
        this.month7t2Time = month7t2Time;
    }

    public BigDecimal getMonth8t2Time() {
        return month8t2Time;
    }

    public void setMonth8t2Time(BigDecimal month8t2Time) {
        this.month8t2Time = month8t2Time;
    }

    public BigDecimal getMonth9t2Time() {
        return month9t2Time;
    }

    public void setMonth9t2Time(BigDecimal month9t2Time) {
        this.month9t2Time = month9t2Time;
    }

    public BigDecimal getMonth10t2Time() {
        return month10t2Time;
    }

    public void setMonth10t2Time(BigDecimal month10t2Time) {
        this.month10t2Time = month10t2Time;
    }

    public BigDecimal getMonth11t2Time() {
        return month11t2Time;
    }

    public void setMonth11t2Time(BigDecimal month11t2Time) {
        this.month11t2Time = month11t2Time;
    }

    public BigDecimal getMonth12t2Time() {
        return month12t2Time;
    }

    public void setMonth12t2Time(BigDecimal month12t2Time) {
        this.month12t2Time = month12t2Time;
    }

    public BigDecimal getMonth1bcTime() {
        return month1bcTime;
    }

    public void setMonth1bcTime(BigDecimal month1bcTime) {
        this.month1bcTime = month1bcTime;
    }

    public BigDecimal getMonth2bcTime() {
        return month2bcTime;
    }

    public void setMonth2bcTime(BigDecimal month2bcTime) {
        this.month2bcTime = month2bcTime;
    }

    public BigDecimal getMonth3bcTime() {
        return month3bcTime;
    }

    public void setMonth3bcTime(BigDecimal month3bcTime) {
        this.month3bcTime = month3bcTime;
    }

    public BigDecimal getMonth4bcTime() {
        return month4bcTime;
    }

    public void setMonth4bcTime(BigDecimal month4bcTime) {
        this.month4bcTime = month4bcTime;
    }

    public BigDecimal getMonth5bcTime() {
        return month5bcTime;
    }

    public void setMonth5bcTime(BigDecimal month5bcTime) {
        this.month5bcTime = month5bcTime;
    }

    public BigDecimal getMonth6bcTime() {
        return month6bcTime;
    }

    public void setMonth6bcTime(BigDecimal month6bcTime) {
        this.month6bcTime = month6bcTime;
    }

    public BigDecimal getMonth7bcTime() {
        return month7bcTime;
    }

    public void setMonth7bcTime(BigDecimal month7bcTime) {
        this.month7bcTime = month7bcTime;
    }

    public BigDecimal getMonth8bcTime() {
        return month8bcTime;
    }

    public void setMonth8bcTime(BigDecimal month8bcTime) {
        this.month8bcTime = month8bcTime;
    }

    public BigDecimal getMonth9bcTime() {
        return month9bcTime;
    }

    public void setMonth9bcTime(BigDecimal month9bcTime) {
        this.month9bcTime = month9bcTime;
    }

    public BigDecimal getMonth10bcTime() {
        return month10bcTime;
    }

    public void setMonth10bcTime(BigDecimal month10bcTime) {
        this.month10bcTime = month10bcTime;
    }

    public BigDecimal getMonth11bcTime() {
        return month11bcTime;
    }

    public void setMonth11bcTime(BigDecimal month11bcTime) {
        this.month11bcTime = month11bcTime;
    }

    public BigDecimal getMonth12bcTime() {
        return month12bcTime;
    }

    public void setMonth12bcTime(BigDecimal month12bcTime) {
        this.month12bcTime = month12bcTime;
    }

    public BigDecimal getMonth1gmTime() {
        return month1gmTime;
    }

    public void setMonth1gmTime(BigDecimal month1gmTime) {
        this.month1gmTime = month1gmTime;
    }

    public BigDecimal getMonth2gmTime() {
        return month2gmTime;
    }

    public void setMonth2gmTime(BigDecimal month2gmTime) {
        this.month2gmTime = month2gmTime;
    }

    public BigDecimal getMonth3gmTime() {
        return month3gmTime;
    }

    public void setMonth3gmTime(BigDecimal month3gmTime) {
        this.month3gmTime = month3gmTime;
    }

    public BigDecimal getMonth4gmTime() {
        return month4gmTime;
    }

    public void setMonth4gmTime(BigDecimal month4gmTime) {
        this.month4gmTime = month4gmTime;
    }

    public BigDecimal getMonth5gmTime() {
        return month5gmTime;
    }

    public void setMonth5gmTime(BigDecimal month5gmTime) {
        this.month5gmTime = month5gmTime;
    }

    public BigDecimal getMonth6gmTime() {
        return month6gmTime;
    }

    public void setMonth6gmTime(BigDecimal month6gmTime) {
        this.month6gmTime = month6gmTime;
    }

    public BigDecimal getMonth7gmTime() {
        return month7gmTime;
    }

    public void setMonth7gmTime(BigDecimal month7gmTime) {
        this.month7gmTime = month7gmTime;
    }

    public BigDecimal getMonth8gmTime() {
        return month8gmTime;
    }

    public void setMonth8gmTime(BigDecimal month8gmTime) {
        this.month8gmTime = month8gmTime;
    }

    public BigDecimal getMonth9gmTime() {
        return month9gmTime;
    }

    public void setMonth9gmTime(BigDecimal month9gmTime) {
        this.month9gmTime = month9gmTime;
    }

    public BigDecimal getMonth10gmTime() {
        return month10gmTime;
    }

    public void setMonth10gmTime(BigDecimal month10gmTime) {
        this.month10gmTime = month10gmTime;
    }

    public BigDecimal getMonth11gmTime() {
        return month11gmTime;
    }

    public void setMonth11gmTime(BigDecimal month11gmTime) {
        this.month11gmTime = month11gmTime;
    }

    public BigDecimal getMonth12gmTime() {
        return month12gmTime;
    }

    public void setMonth12gmTime(BigDecimal month12gmTime) {
        this.month12gmTime = month12gmTime;
    }

    public BigDecimal getMonth1ccTime() {
        return month1ccTime;
    }

    public void setMonth1ccTime(BigDecimal month1ccTime) {
        this.month1ccTime = month1ccTime;
    }

    public BigDecimal getMonth2ccTime() {
        return month2ccTime;
    }

    public void setMonth2ccTime(BigDecimal month2ccTime) {
        this.month2ccTime = month2ccTime;
    }

    public BigDecimal getMonth3ccTime() {
        return month3ccTime;
    }

    public void setMonth3ccTime(BigDecimal month3ccTime) {
        this.month3ccTime = month3ccTime;
    }

    public BigDecimal getMonth4ccTime() {
        return month4ccTime;
    }

    public void setMonth4ccTime(BigDecimal month4ccTime) {
        this.month4ccTime = month4ccTime;
    }

    public BigDecimal getMonth5ccTime() {
        return month5ccTime;
    }

    public void setMonth5ccTime(BigDecimal month5ccTime) {
        this.month5ccTime = month5ccTime;
    }

    public BigDecimal getMonth6ccTime() {
        return month6ccTime;
    }

    public void setMonth6ccTime(BigDecimal month6ccTime) {
        this.month6ccTime = month6ccTime;
    }

    public BigDecimal getMonth7ccTime() {
        return month7ccTime;
    }

    public void setMonth7ccTime(BigDecimal month7ccTime) {
        this.month7ccTime = month7ccTime;
    }

    public BigDecimal getMonth8ccTime() {
        return month8ccTime;
    }

    public void setMonth8ccTime(BigDecimal month8ccTime) {
        this.month8ccTime = month8ccTime;
    }

    public BigDecimal getMonth9ccTime() {
        return month9ccTime;
    }

    public void setMonth9ccTime(BigDecimal month9ccTime) {
        this.month9ccTime = month9ccTime;
    }

    public BigDecimal getMonth10ccTime() {
        return month10ccTime;
    }

    public void setMonth10ccTime(BigDecimal month10ccTime) {
        this.month10ccTime = month10ccTime;
    }

    public BigDecimal getMonth11ccTime() {
        return month11ccTime;
    }

    public void setMonth11ccTime(BigDecimal month11ccTime) {
        this.month11ccTime = month11ccTime;
    }

    public BigDecimal getMonth12ccTime() {
        return month12ccTime;
    }

    public void setMonth12ccTime(BigDecimal month12ccTime) {
        this.month12ccTime = month12ccTime;
    }

    public BigDecimal getMonth1newColorTime() {
        return month1newColorTime;
    }

    public void setMonth1newColorTime(BigDecimal month1newColorTime) {
        this.month1newColorTime = month1newColorTime;
    }

    public BigDecimal getMonth2newColorTime() {
        return month2newColorTime;
    }

    public void setMonth2newColorTime(BigDecimal month2newColorTime) {
        this.month2newColorTime = month2newColorTime;
    }

    public BigDecimal getMonth3newColorTime() {
        return month3newColorTime;
    }

    public void setMonth3newColorTime(BigDecimal month3newColorTime) {
        this.month3newColorTime = month3newColorTime;
    }

    public BigDecimal getMonth4newColorTime() {
        return month4newColorTime;
    }

    public void setMonth4newColorTime(BigDecimal month4newColorTime) {
        this.month4newColorTime = month4newColorTime;
    }

    public BigDecimal getMonth5newColorTime() {
        return month5newColorTime;
    }

    public void setMonth5newColorTime(BigDecimal month5newColorTime) {
        this.month5newColorTime = month5newColorTime;
    }

    public BigDecimal getMonth6newColorTime() {
        return month6newColorTime;
    }

    public void setMonth6newColorTime(BigDecimal month6newColorTime) {
        this.month6newColorTime = month6newColorTime;
    }

    public BigDecimal getMonth7newColorTime() {
        return month7newColorTime;
    }

    public void setMonth7newColorTime(BigDecimal month7newColorTime) {
        this.month7newColorTime = month7newColorTime;
    }

    public BigDecimal getMonth8newColorTime() {
        return month8newColorTime;
    }

    public void setMonth8newColorTime(BigDecimal month8newColorTime) {
        this.month8newColorTime = month8newColorTime;
    }

    public BigDecimal getMonth9newColorTime() {
        return month9newColorTime;
    }

    public void setMonth9newColorTime(BigDecimal month9newColorTime) {
        this.month9newColorTime = month9newColorTime;
    }

    public BigDecimal getMonth10newColorTime() {
        return month10newColorTime;
    }

    public void setMonth10newColorTime(BigDecimal month10newColorTime) {
        this.month10newColorTime = month10newColorTime;
    }

    public BigDecimal getMonth11newColorTime() {
        return month11newColorTime;
    }

    public void setMonth11newColorTime(BigDecimal month11newColorTime) {
        this.month11newColorTime = month11newColorTime;
    }

    public BigDecimal getMonth12newColorTime() {
        return month12newColorTime;
    }

    public void setMonth12newColorTime(BigDecimal month12newColorTime) {
        this.month12newColorTime = month12newColorTime;
    }

    public String getCountFormula() {
        return countFormula;
    }

    public void setCountFormula(String countFormula) {
        this.countFormula = countFormula;
    }

}
