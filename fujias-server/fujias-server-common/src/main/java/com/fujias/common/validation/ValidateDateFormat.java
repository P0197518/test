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

import com.fujias.common.util.StringUtil;

/**
 * DateFormatチェック
 *
 * <p>
 * 指定可能メッセージID:MZ106E
 * </p>
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateDateFormat.DateFormatValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateDateFormat {

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
    String message();

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
    int maxlength() default 10;

    /**
     * メールアドレスを行うバリデータです。
     * 
     */
    class DateFormatValidator implements ConstraintValidator<ValidateDateFormat, String> {

        /** dateドメイン許容 */
        private static String dateDomain = "^\\d{4}-\\d{2}-\\d{2}$";

        /** メールアドレスチェックドメイン許容 */
        private final Pattern domainPattern = java.util.regex.Pattern.compile(dateDomain);

        /** 最大桁数 */
        private int maxlength;

        @Override
        public void initialize(ValidateDateFormat validPhone) {
            maxlength = validPhone.maxlength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            if (!matchPart(item, domainPattern)) {
                return false;
            }
            if (StringUtil.getByteLength(item) > maxlength) {
                return false;
            }
            return true;
        }

        /**
         * メールアドレス妥当性をチェックします。
         * 
         * @param part チェック対象値
         * @param pattern 妥当性
         * @return false:失敗 true:成功
         */
        private boolean matchPart(String part, Pattern pattern) {
            Matcher matcher = pattern.matcher(part);
            return matcher.matches();
        }
    }
}
