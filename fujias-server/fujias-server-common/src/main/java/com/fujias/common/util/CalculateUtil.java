package com.fujias.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fujias.common.constants.CalculateType;

/**
 * 计算共通类
 * 
 * @author fujias
 *
 */
public class CalculateUtil {

    private Integer digits;
    private RoundingMode methodsBigdecimal;

    /**
     * 构造函数
     * 
     */
    public CalculateUtil() {
        this(0, CalculateType.四舍五入.getCode());
    }

    /**
     * 构造函数
     * 
     * @param methods 计算方法
     */
    public CalculateUtil(String methods) {
        this(0, methods);
    }

    /**
     * 构造函数
     * 
     * @param digits 小数位数
     */
    public CalculateUtil(Integer digits) {
        this(digits, CalculateType.四舍五入.getCode());
    }

    /**
     * 构造函数
     * 
     * @param digits 小数位数
     * @param methods 计算方法
     */
    public CalculateUtil(Integer digits, String methods) {
        this.digits = digits;
        if (CalculateType.向上取整.getCode().equals(methods)) {
            setMethodsBigdecimal(RoundingMode.UP);
        } else if (CalculateType.四舍五入.getCode().equals(methods)) {
            setMethodsBigdecimal(RoundingMode.HALF_UP);
        } else if (CalculateType.向下取整.getCode().equals(methods)) {
            setMethodsBigdecimal(RoundingMode.DOWN);
        }
    }

    /**
     * 加
     * 
     * @param num1 num1
     * @param num2 num2
     * @return 计算结果 num1 + num2
     */
    public BigDecimal bigAdd(BigDecimal num1, BigDecimal num2) {
        return bigAddWithDigits(num1, num2, digits);
    }

    /**
     * 减
     * 
     * @param num1 num1
     * @param num2 num2
     * @return 计算结果 num1 - num2
     */
    public BigDecimal bigSub(BigDecimal num1, BigDecimal num2) {
        return bigSubWithDigits(num1, num2, digits);
    }

    /**
     * 乘
     * 
     * @param num1 num1
     * @param num2 num2
     * @return 计算结果 num1 * num2
     */
    public BigDecimal bigMul(BigDecimal num1, BigDecimal num2) {
        return bigMulWithDigits(num1, num2, digits);
    }

    /**
     * 除
     * 
     * @param num1 num1
     * @param num2 num2
     * @return 计算结果 num1 / num2
     */
    public BigDecimal bigDiv(BigDecimal num1, BigDecimal num2) {
        return bigDivWithDigits(num1, num2, digits);
    }

    /**
     * 加
     * 
     * @param num1 num1
     * @param num2 num2
     * @param pdigits pdigits
     * @return 计算结果 num1 + num2
     */
    public BigDecimal bigAddWithDigits(BigDecimal num1, BigDecimal num2, int pdigits) {
        return (num1.add(num2)).setScale(pdigits, RoundingMode.HALF_UP);
    }

    /**
     * 减
     * 
     * @param num1 num1
     * @param num2 num2
     * @param pdigits pdigits
     * @return 计算结果 num1 - num2
     */
    public BigDecimal bigSubWithDigits(BigDecimal num1, BigDecimal num2, int pdigits) {
        return (num1.subtract(num2)).setScale(pdigits, RoundingMode.HALF_UP);
    }

    /**
     * 乘
     * 
     * @param num1 num1
     * @param num2 num2
     * @param pdigits pdigits
     * @return 计算结果 num1 * num2
     */
    public BigDecimal bigMulWithDigits(BigDecimal num1, BigDecimal num2, int pdigits) {
        return (num1.multiply(num2)).setScale(pdigits, RoundingMode.HALF_UP);
    }

    /**
     * 除
     * 
     * @param num1 num1
     * @param num2 num2
     * @param pdigits pdigits
     * @return 计算结果 num1 / num2
     */
    public BigDecimal bigDivWithDigits(BigDecimal num1, BigDecimal num2, int pdigits) {
        return num1.divide(num2, pdigits, RoundingMode.HALF_UP);
    }

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public RoundingMode getMethodsBigdecimal() {
        return methodsBigdecimal;
    }

    public void setMethodsBigdecimal(RoundingMode methodsBigdecimal) {
        this.methodsBigdecimal = methodsBigdecimal;
    }

}
