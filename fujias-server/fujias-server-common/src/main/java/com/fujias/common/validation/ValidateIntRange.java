package com.fujias.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.BigDecimal;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import com.fujias.common.constants.MessageCodes;
import com.fujias.common.util.StringUtil;

/**
 * 整数/金額の範囲チェック
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateIntRange.IntRange.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateIntRange {

    /**
     * メッセージパラメータを取得します。
     * 
     * @return メッセージパラメータ
     */
    String[] messageArgs();

    /**
     * メッセージを取得します。
     * 
     * @return メッセージ
     */
    String message() default MessageCodes.EC0009;

    /**
     * グループを取得します。
     * 
     * @return グループ
     */
    Class<?>[] groups() default {};

    /**
     * ペイロードを取得します。
     * 
     * @return ペイロード
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * from値を取得します。
     * 
     * @return from値
     */
    String fromValue() default "";

    /**
     * to値を取得します。
     * 
     * @return to値
     */
    String toValue() default "";

    /**
     * 整数/金額の範囲チェック。
     * 
     */
    class IntRange implements ConstraintValidator<ValidateIntRange, String> {

        /** from値 */
        private String fromValue;

        /** to値 */
        private String toValue;

        @Override
        public void initialize(ValidateIntRange validateIntRange) {
            this.fromValue = validateIntRange.fromValue();
            this.toValue = validateIntRange.toValue();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            String regex = "[-]?(?:([1-9][0-9]{0,2}){1}(,[0-9]{3})+|[1-9][0-9]*|0)";
            if (!item.matches(regex)) {
                return false;
            }
            if (StringUtil.isNotEmpty(fromValue) && StringUtil.isNumber(fromValue) && new BigDecimal(StringUtil.deleteComma(item).get())
                            .compareTo(new BigDecimal(StringUtil.deleteComma(fromValue).get())) < 0) {
                return false;
            }
            if (StringUtil.isNotEmpty(toValue) && StringUtil.isNumber(toValue) && new BigDecimal(StringUtil.deleteComma(item).get())
                            .compareTo(new BigDecimal(StringUtil.deleteComma(toValue).get())) > 0) {
                return false;
            }
            return true;
        }
    }
}
