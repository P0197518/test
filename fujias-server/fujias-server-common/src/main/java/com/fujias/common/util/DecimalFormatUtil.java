package com.fujias.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 浮点数字格式化工具类
 * 
 * @author 陳強
 *
 */
public final class DecimalFormatUtil {

    public static final String NUMBER_FORMAT4 = "###.####";
    public static final String NUMBER_FORMAT2 = "###.##";
    public static final String NUMBER_FORMAT0 = "###";

    public static final String MONEY_FORMAT = "#,###";

    /**
     * 格式化decimal类型
     * 
     * @param source source
     * @param format format
     * @return 格式化结果
     */
    public static String parseDecimalToString(BigDecimal source, String format) {
        if (source == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(format);
        return df.format(source);
    }

    /**
     * 格式化decimal类型
     * 
     * @param source source
     * @return 格式化结果
     */
    public static BigDecimal parseDecimal(String source) {
        return new BigDecimal(source.replace(",", ""));
    }

    /**
     * 格式化decimal类型
     * 
     * @param source source
     * @return 格式化结果
     */
    public static BigDecimal isNull(BigDecimal source) {
        if (source == null) {
            return BigDecimal.ZERO;
        }
        return source;
    }

}
