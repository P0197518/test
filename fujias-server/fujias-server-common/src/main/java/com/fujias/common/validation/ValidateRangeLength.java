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
 * 最小と最大文字数チェックを行う
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateRangeLength.RangeLengthValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateRangeLength {

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
     * 最大文字数を取得します。
     * 
     * @return 最大文字数
     */
    int maxLength() default -1;

    /**
     * 最小文字数を取得します。
     * 
     * @return 最小文字数
     */
    int minLength() default -1;

    /**
     * 文字数チェックを行う
     * 
     */
    class RangeLengthValidator implements ConstraintValidator<ValidateRangeLength, String> {

        /** 最大文字数 */
        private int maxLength;

        /** 最小文字数 */
        private int minLength;

        @Override
        public void initialize(ValidateRangeLength validate) {
            this.maxLength = validate.maxLength();
            this.minLength = validate.minLength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            return (item.length() <= maxLength && item.length() >= minLength);
        }
    }
}
