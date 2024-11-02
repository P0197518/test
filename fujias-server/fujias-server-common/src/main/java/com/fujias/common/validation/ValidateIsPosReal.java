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
 * 正実数、正金額（小数がある）の書式、桁数チェック
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateIsPosReal.IsPosRealValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateIsPosReal {

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
    String message() default MessageCodes.EC0007;

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
     * 正実数、正金額（小数がある）の書式、桁数チェック。
     * 
     */
    class IsPosRealValidator implements ConstraintValidator<ValidateIsPosReal, String> {

        /** 整数桁数 */
        private int intLength;

        /** 小数桁数 */
        private int decLength;

        @Override
        public void initialize(ValidateIsPosReal validateIsPosReal) {
            this.intLength = validateIsPosReal.intLength();
            this.decLength = validateIsPosReal.decLength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            if (!StringUtil.isPosRealNumber(item)) {
                return false;
            }
            String[] posRealValue = item.split("\\.");
            if (intLength > -1) {
                if (posRealValue[0].length() > intLength) {
                    return false;
                }
            }
            if (decLength > -1) {
                int realdeclength = posRealValue.length > 1 ? posRealValue[1].length() : 0;
                if (realdeclength > decLength) {
                    return false;
                }
            }
            return true;
        }
    }
}
