package com.fujias.common.constants;

/**
 * 定数定義クラス
 * 
 * @author chenqiang
 *
 */
public class ActivitiConstants {

    // 自定义配置文件
    public static final String ENVIRONMENT_PROPERTIES_PATH = "config.properties";

    /**
     * 标示状态（0：禁用；1：启用）
     */
    public static Integer STATUS_ENABLE = 1;
    public static Integer STATUS_DISABLE = 0;
    /**
     * 查看权限（0：自己创建 1：所有）
     */
    public static Integer STATUS_ALL = 1;
    public static Integer STATUS_SELF = 0;

    /**
     * 是否原始版本（0：原始版本 1：变更版本）
     */
    public static Integer IS_BEGIN_VERSION_Y = 0;
    public static Integer IS_BEGIN_VERSION_N = 1;

    /**
     * 是否当前版本（0：历史版本；1：当前版本）
     */
    public static Integer IS_NEW_VERSION_Y = 1;
    public static Integer IS_NEW_VERSION_N = 0;

    /**
     * 归属类型(0：营销中心；1：区域公司；2：项目公司）
     */
    public static String BELONG_TYPE_YXZX = "0";
    public static String BELONG_TYPE_QYGS = "1";
    public static String BELONG_TYPE_XMGS = "2";

    /**
     * 附件上传类型（1：图片；2：视频；3：音频；4：文档；5：压缩；0：其他）
     */
    public static String ATTACHMENT_TYPE_OTHER = "0";
    public static String ATTACHMENT_TYPE_IMAGE = "1";
    public static String ATTACHMENT_TYPE_VIDEO = "2";
    public static String ATTACHMENT_TYPE_AUDIO = "3";
    public static String ATTACHMENT_TYPE_DOCUMENT = "4";
    public static String ATTACHMENT_TYPE_RAR = "5";

    /**
     * ACTIVITI用户操作类型
     */
    public static int OPERATION_TYPE_APPROVE = 1;// 审批
    public static int OPERATION_TYPE_AUTHORIZE = 2;// 授权审批
    public static int OPERATION_TYPE_TENANT = 3;// 3转办
    public static int OPERATION_TYPE_REMIND = 4;// 4催办
    public static int OPERATION_TYPE_CC = 5;// 5抄送
    public static int OPERATION_TYPE_CC_CONTENT = 6;// 6批注意见
    public static int OPERATION_TYPE_CONFER = 7;// 7协商
    public static int OPERATION_TYPE_CONFER_CONTENT = 8;// 8协商意见
    public static int OPERATION_TYPE_RECALL = 9;// 9撤回
    public static int OPERATION_TYPE_BBS = 10;// 10讨论
    public static int OPERATION_TYPE_BBS_CONTENT = 11;// 11讨论意见
    public static int OPERATION_TYPE_CREATEFLOW = 12;// 12发起流程
    public static int OPERATION_TYPE_ENDFLOW = 13;// 13结束流程
    public static int OPERATION_TYPE_FORUPDATE = 14;// 14打回修改，虚拟状态，数据库不会存在。

    /**
     * Activiti审批状态
     *
     */
    public static String APPROVAL_STATUS_CANCEL = "0"; // 作废
    public static String APPROVAL_STATUS_UNCOMMITTED = "1"; // 未提交
    public static String APPROVAL_STATUS_WORKING = "2"; // 审批中
    public static String APPROVAL_STATUS_SUCCESS = "3"; // 审批完成
    public static String APPROVAL_STATUS_FAIL = "4"; // 审批不通过
    public static String APPROVAL_STATUS_REJECT = "5"; // 驳回

    /**
     * 审批结果-用于记录审批记录
     */
    public static String APPROVAL_RESULT_AGREE = "0";// 同意
    public static String APPROVAL_RESULT_DISAGREE = "1"; // 不同意
    public static String APPROVAL_RESULT_TURNBACK = "2"; // 驳回到上一步
    public static String APPROVAL_RESULT_TURNSTART = "3"; // 驳回到起点

    /**
     * Activiti业务模块代码
     */
    public static String BUSSINESS_KEY_NDYS = "ndys";// 年度预算
    public static String BUSSINESS_KEY_YDYS = "ydys";// 月度预算
    public static String BUSSINESS_KEY_YSTZ = "ystz";// 预算调整
    public static String BUSSINESS_KEY_LXSQ = "lxsq";// 立项申请
    public static String BUSSINESS_KEY_LXTZ = "lxtz";// 立项调整
    public static String BUSSINESS_KEY_HTSP = "htsp";// 合同审批
    public static String BUSSINESS_KEY_FKSQ = "fksq";// 付款申请

    /**
     * 跳转来源
     */
    public static String FLOWINFO_FROM_YIBAN = "ck_yb";// 已办页面
    public static String FLOWINFO_FROM_CANCEL = "cancel";// 已办页面

    /**
     * 第一责任人审批状态
     */
    public static String FIRST_PERSON_N = null;// 不是第一责任人
    public static String FIRST_PERSON_BTG = "0";// 不通过
    public static String FIRST_PERSON_TG = "1";// 通过

    /**
     * 合同类型
     */
    public static String CONTRACT_TYPE_RC = "1"; // 非框架合同
    public static String CONTRACT_TYPE_KJ = "2"; // 框架合同
    /**
     * 工作流转办用
     */
    public static String IDENTITYLINK_TYPE = "candidate";

    /**
     * 业务数据权限（查看所有 查看自建）模块代码
     */
    public static String AUTODATA_KEY_MBDJ = "marketing_target_manage";// 目标登记
    public static String AUTODATA_KEY_NDYS = "year_budget_apply_manage";// 年度预算
    public static String AUTODATA_KEY_YDYS = "month_budget_apply_manage";// 月度预算
    public static String AUTODATA_KEY_YSTZ = "budget_adjustment_manage";// 预算调整
    public static String AUTODATA_KEY_LXTZ = "setup_apply_adjustment_manage";// 立项调整申请
    public static String AUTODATA_KEY_RLDJ = "hr_cost_manage";// 人力成本登记
    public static String AUTODATA_KEY_XZGL = "adm_cost_manage";// 行政管理费登记
    public static String AUTODATA_KEY_HTGL = "contract_manage_l2";// 合同
    public static String AUTODATA_KEY_FKSQ = "pay_apply_manage";// 付款
    /**
     * 跳过审批标志位
     */
    public static String SKIP_APPROVAL_FLAG = "skip";

}
