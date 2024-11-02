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
 * 文字列の最大文字数チェックを行う
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateMaxLength.MaxLengthValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateMaxLength {

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
    String message() default MessageCodes.EC0002;

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
     * 文字数チェックを行う
     * 
     */
    class MaxLengthValidator implements ConstraintValidator<ValidateMaxLength, String> {

        /** 整数桁数 */
        private int intLength;

        @Override
        public void initialize(ValidateMaxLength validate) {
            this.intLength = validate.intLength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            return (item.length() <= intLength);
        }
    }
}
