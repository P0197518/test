package com.fujias.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

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
 * 実数、金額（小数がある）の書式、桁数チェック
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateIsReal.IsRealValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateIsReal {

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
    String message() default MessageCodes.EC0008;

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
     * 整数桁数を取得します。
     * 
     * @return 整数桁数
     */
    int intLength() default -1;

    /**
     * 小数桁数を取得します。
     * 
     * @return 小数桁数
     */
    int decLength() default -1;

    /**
     * 実数、金額（小数がある）の書式、桁数チェック。
     * 
     */
    class IsRealValidator implements ConstraintValidator<ValidateIsReal, String> {

        /** 整数桁数 */
        private int intLength;

        /** 小数桁数 */
        private int decLength;

        @Override
        public void initialize(ValidateIsReal validateIsReal) {
            this.intLength = validateIsReal.intLength();
            this.decLength = validateIsReal.decLength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            return StringUtil.isRealNumber(item, intLength, decLength);
        }
    }
}
