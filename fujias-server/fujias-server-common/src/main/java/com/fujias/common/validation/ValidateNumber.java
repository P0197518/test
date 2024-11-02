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
 * 半角数字チェック
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateNumber.NumberValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateNumber {

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
    String message() default MessageCodes.EC0003;

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
     * 半角数字の書式チェック。
     * 
     */
    class NumberValidator implements ConstraintValidator<ValidateNumber, String> {

        @Override
        public void initialize(ValidateNumber validateNumber) {
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            String str = StringUtil.newLineEscape(item);
            String regex = "[0-9]*";
            if (!str.matches(regex)) {
                return false;
            }
            return true;
        }
    }
}
