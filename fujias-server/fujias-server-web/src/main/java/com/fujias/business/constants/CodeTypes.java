package com.fujias.business.constants;

/**
 * 区分表类型
 * 
 * @author wangbaoxin
 *
 */
public enum CodeTypes {
    订单类型("01"), 订单状态("02"), 货币种类("03"), 税率区分("04"), 退货来源("05"), 退货类型("06"), 不良问题原因("07"), 出货计划状态("08"), 出货海运区分("09"), 制造种类("10"), 采购委外状态("11"), 出入库区分(
                    "12"), 出入库种类("13"), 包装托盘规格("14"), 盘点状态("15"), 物料大分类("17"), 物料小分类("18"), 产品性质("19"), 性别("20"), 社员类型(
                                    "21"), 物料区分("22"), 钢柄材质("23"), 合金材质("24"), 客户地区("25"), 采购大分类("26"), 采购小分类("27"), 日元汇率("28"), 显示状态("39");

    private String code;

    CodeTypes(String code) {
        this.code = code;
    }

    /**
     * 获取类型对应的code
     * 
     * @return 类型对应的code
     */
    public String getCode() {
        return this.code;
    }
}
