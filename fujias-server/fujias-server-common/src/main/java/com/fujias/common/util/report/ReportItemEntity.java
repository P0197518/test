package com.fujias.common.util.report;

import java.util.Collection;
import java.util.HashMap;

/**
 * 张票导出的数据entity
 * 
 * @author 陳強
 * @param <T> T
 */
public class ReportItemEntity<T> {

    private String reportName;
    private String reportShowName;
    private HashMap<String, Object> reportParams;

    private Collection<T> reportDataSource;

    /**
     * 构造函数
     * 
     * @param reportName 张票模板名
     * @param reportShowName reportShowName
     * @param reportDataSource 张票数据
     */
    public ReportItemEntity(String reportName, String reportShowName, Collection<T> reportDataSource) {
        this(reportName, reportShowName, null, reportDataSource);
    }

    /**
     * 构造函数
     * 
     * @param reportName 张票模板名
     * @param reportShowName reportShowName
     * @param reportParams 张票头部数据
     * @param reportDataSource 张票数据
     */
    public ReportItemEntity(String reportName, String reportShowName, HashMap<String, Object> reportParams, Collection<T> reportDataSource) {
        this.reportName = reportName;
        this.reportShowName = reportShowName;
        this.reportParams = reportParams;
        this.reportDataSource = reportDataSource;
    }

    /**
     * reportName的取得方法。
     * 
     * @return the reportName
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * reportName的设定方法。
     * 
     * @param reportName the reportName to set
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * reportParams的取得方法。
     * 
     * @return the reportParams
     */
    public HashMap<String, Object> getReportParams() {
        return reportParams;
    }

    /**
     * reportParams的设定方法。
     * 
     * @param reportParams the reportParams to set
     */
    public void setReportParams(HashMap<String, Object> reportParams) {
        this.reportParams = reportParams;
    }

    /**
     * reportDataSource的取得方法。
     * 
     * @return the reportDataSource
     */
    public Collection<T> getReportDataSource() {
        return reportDataSource;
    }

    /**
     * reportDataSource的设定方法。
     * 
     * @param reportDataSource the reportDataSource to set
     */
    public void setReportDataSource(Collection<T> reportDataSource) {
        this.reportDataSource = reportDataSource;
    }

    public String getReportShowName() {
        return reportShowName;
    }

    public void setReportShowName(String reportShowName) {
        this.reportShowName = reportShowName;
    }

}
