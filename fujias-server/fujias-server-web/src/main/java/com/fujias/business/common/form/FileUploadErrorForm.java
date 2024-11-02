package com.fujias.business.common.form;

import com.fujias.common.entity.CheckMessage;

/**
 * 文件上传结构体
 * 
 * @author chenqiang
 *
 */
public class FileUploadErrorForm {

    private String importYearAndMonth;

    private int errorLine;

    private CheckMessage errorContent;

    public String getImportYearAndMonth() {
        return importYearAndMonth;
    }

    public void setImportYearAndMonth(String importYearAndMonth) {
        this.importYearAndMonth = importYearAndMonth;
    }

    public int getErrorLine() {
        return errorLine;
    }

    public void setErrorLine(int errorLine) {
        this.errorLine = errorLine;
    }

    public CheckMessage getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(CheckMessage errorContent) {
        this.errorContent = errorContent;
    }

}
