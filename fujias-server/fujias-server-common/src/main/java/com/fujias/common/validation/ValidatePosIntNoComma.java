package com.fujias.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * 正整数（コンマなし）文字の書式、桁数チェック
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidatePosIntNoComma.PosIntNoCommaValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidatePosIntNoComma {

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
    String message() default MessageCodes.EC0006;

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
     * 最大桁数を取得します。
     * 
     * @return 最大桁数
     */
    int maxlength() default -1;

    /**
     * 正整数（コンマなし）文字の書式、桁数チェック。
     * 
     */
    class PosIntNoCommaValidator implements ConstraintValidator<ValidatePosIntNoComma, String> {

        /** 最大桁数 */
        private int maxlength;

        @Override
        public void initialize(ValidatePosIntNoComma validatePosIntNoComma) {
            this.maxlength = validatePosIntNoComma.maxlength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            Pattern pattern = Pattern.compile("^([1-9]\\d*)$");
            Matcher matcher = pattern.matcher(item);
            if (!matcher.find()) {
                return false;
            }
            if (maxlength > -1 && (item.length() > this.maxlength)) {
                return false;
            }
            return true;
        }
    }
}
