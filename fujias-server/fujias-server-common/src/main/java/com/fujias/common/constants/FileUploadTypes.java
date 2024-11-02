package com.fujias.common.constants;

/**
 * 文件上传类型的枚举类
 * 
 * @version 1.0
 * @author 陳強
 */
public enum FileUploadTypes {

    模型文件("1"), 工作附件("2"), 知识库("3"), 进度汇报附件("4"), 建设方附件("5"), 项目图片文件("6"), 会议附件("7"), 检测附件("8"), 培训附件("9"), 头像文件("10"), 客服反馈("11"), 系统显示LOGO(
                    "12"), 公司专享知识库("13"), 其他文件("99");

    private final String code;

    FileUploadTypes(String code) {
        this.code = code;
    }

    /**
     * 状態に対するコードを取得する。
     * 
     * @return 状態コード
     */
    public String getCode() {
        return code;
    }

}
