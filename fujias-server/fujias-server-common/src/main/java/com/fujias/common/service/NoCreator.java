package com.fujias.common.service;

import java.util.UUID;

import com.fujias.common.util.DateUtil;
import com.fujias.common.util.StringUtil;

/**
 * 採番工具类
 * 
 * @author zhangxq
 *
 */
public class NoCreator {

    public enum NoType {
        订单编号("QH"), 出库计划编号("02"), 返品编号("03"), 标签打印记录编号("04"), 库存批次编号("05"), 采购订单编号("06"), 委外加工编号("07"), 出入库记录("IO"), 采购单打印("QHT-"), 委外加工单打印(
                        "QHT-"), 指令书编号订单("QH-S"), 指令书编号计划(
                                        "QH-J"), 指令书编号实验("QH-T"), LotNo顺番号("LOT"), 联系人顺番号("SCP"), 参数分类编号("00"), 材质品番顺番号("10"), 材质图纸编号顺番号("10");

        private String code;

        private NoType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return this.name();
        }

        /**
         * 根据code获取枚举
         * 
         * @param code code
         * @return 枚举
         */
        public static NoType getByCode(String code) {
            for (NoType item : NoType.values()) {
                if (code.equals(item.getCode())) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * 获取uuid
     * 
     * @return uuid
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 4桁順番数字 例：0001
     * 
     * @param noType noType
     * @return 番号
     */
    public static String getSaibanNo4(NoType noType) {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return oprator.getSaibanNo4(noType);
    }

    /**
     * 12桁 例：000000000001
     * 
     * @param noType noType
     * @return 番号
     */
    public static String getSaibanNo12(NoType noType) {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return oprator.getSaibanNo12(noType);
    }

    /**
     * 11桁 例SS200101001
     * 
     * @param noType noType
     * @return 出庫予定番号
     */
    public static String getConcatNo11(NoType noType) {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return noType.getCode().concat(oprator.getSaibanDateNo(noType, 3));
    }

    /**
     * 获取子信息id
     * 
     * @param index 子信息顺序
     * @param baseNo 父表no
     * @return 子信息id
     */
    public static String getDetailNo(String baseNo, int index) {
        return baseNo + StringUtil.padLeft(String.valueOf(index + 1), 3, '0');
    }

    /**
     * 获取子信息id
     * 
     * @param index 子信息顺序
     * @param baseNo 父表no
     * @return 子信息id
     */
    public static String getDetailNo2(String baseNo, int index) {
        return baseNo + StringUtil.padLeft(String.valueOf(index + 1), 2, '0');
    }

    /**
     * 获取子信息id
     * 
     * @param oldNo 参照作成自编号
     * @param childNoLength 自编号长度
     * @return 子信息id
     */
    public static String autoAddDetailNo(String oldNo, int childNoLength) {
        if (oldNo.length() < childNoLength) {
            return "";
        }
        String mainNo = oldNo.substring(0, oldNo.length() - childNoLength);
        String maxListNo = StringUtil.leftTrim(oldNo.substring(oldNo.length() - childNoLength), '0').get();
        maxListNo = StringUtil.padLeft(String.valueOf(Integer.parseInt(maxListNo) + 1), childNoLength, '0');

        return mainNo + maxListNo;
    }

    /**
     * 获取子信息id
     * 
     * @param detailNo detailNo
     * @return 子信息id
     */
    public static String getDetailNoMulOne(String detailNo) {
        int number = Integer.parseInt(StringUtil.leftTrim(detailNo.substring(detailNo.length() - 3), '0').get()) + 1;
        return detailNo.substring(0, detailNo.length() - 3) + StringUtil.padLeft(String.valueOf(number), 3, '0');
    }

    /**
     * 3桁順番数字 例：001
     * 
     * @param noType noType
     * @return 番号
     */
    public static String getSaibanNo3(NoType noType) {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return oprator.getSaibanDateNo2(noType, 3, DateUtil.getNowDate());
    }

    /**
     * 11桁 例SS200101001
     * 
     * @param noType noType
     * @return 出庫予定番号
     * @throws Exception Exception
     */
    public static String getLotNo(NoType noType) throws Exception {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return oprator.getLotNo(noType);
    }

    /**
     * 2桁 例01
     * 
     * @param noType noType
     * @return 联系人定番号
     */
    public static String getSCPNo(NoType noType) {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return oprator.getSCPNo(noType);
    }

    /**
     * 获取采购单号
     * 
     * @param noType noType
     * @param supplierId 供应商编号
     * @return 采购单号
     */
    public static String getPurchaseNo(NoType noType, String supplierId) {
        NoCreatorOprator oprator = new NoCreatorOprator();
        return supplierId.concat(oprator.getPurchaseNo(noType, supplierId, 3));
    }

}
