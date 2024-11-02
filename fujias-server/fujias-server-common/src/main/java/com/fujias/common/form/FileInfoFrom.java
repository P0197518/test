package com.fujias.common.form;

/**
 * FileInfoFrom
 * 
 * @author fujias
 *
 */
public class FileInfoFrom {

    /**
     * 文件编号
     */
    private String fileId;

    /**
     * 删除区分
     */
    private Boolean delflg;

    /**
     * 文件类型{1:模型文件,2：工作附件,3：知识库,4:图片类}
     */
    private String fileType;

    /**
     * 原文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 扩展名
     */
    private String extension;

    /**
     * 保存路径
     */
    private String savePath;

    /**
     * 保存文件名
     */
    private String saveFileName;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Boolean getDelflg() {
        return delflg;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }
}
