
package com.fujias.common.constants;

/**
 * 服务器文件的读取方式
 *
 * @version 1.0
 * @author 陳強
 */
public enum DownloadFileTypes {

    下载项目文件("1"), 图片展示("2"), 下载平台文件("3");

    private String code;

    DownloadFileTypes(String code) {
        this.code = code;
    }

    /**
     * codeを取得する。
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
