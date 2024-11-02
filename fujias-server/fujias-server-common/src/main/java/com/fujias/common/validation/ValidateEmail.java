package com.fujias.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.net.IDN;
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
 * メールアドレスチェック
 *
 * <p>
 * 指定可能メッセージID:MZ106E
 * </p>
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Constraint(validatedBy = {ValidateEmail.EmailValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
public @interface ValidateEmail {

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
    int maxlength() default 50;

    /**
     * メールアドレスを行うバリデータです。
     * 
     */
    class EmailValidator implements ConstraintValidator<ValidateEmail, String> {

        /** メールアドレスチェック許容 */
        private static String atom = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";

        /** メールアドレスチェックドメイン許容 */
        private static String domain = atom + "+(\\." + atom + "+)*";

        /** メールアドレスチェックIPドメイン許容 */
        private static String ipDomain = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

        /** メールアドレスチェックローカル部許容 */
        private final Pattern localPattern = java.util.regex.Pattern.compile(atom + "+(\\." + atom + "+)*", CASE_INSENSITIVE);

        /** メールアドレスチェックドメイン許容 */
        private final Pattern domainPattern = java.util.regex.Pattern.compile(domain + "|" + ipDomain, CASE_INSENSITIVE);

        /** 最大桁数 */
        private int maxlength;

        @Override
        public void initialize(ValidateEmail validEmail) {
            maxlength = validEmail.maxlength();
        }

        @Override
        public boolean isValid(String item, ConstraintValidatorContext context) {
            if (StringUtil.isEmpty(item)) {
                return true;
            }
            String[] emailParts = item.split("@", 3);
            if (emailParts.length != 2) {
                return false;
            }
            if (emailParts[0].endsWith(".") || emailParts[1].endsWith(".")) {
                return false;
            }
            if (!matchPart(emailParts[0], localPattern)) {
                return false;
            }
            if (!matchPart(emailParts[1], domainPattern)) {
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
            try {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < part.length(); i += 30) {
                    sb.append(IDN.toASCII(part.substring(i, i + 30 <= part.length() ? i + 30 : part.length())));
                }
                part = sb.toString();
            } catch (IllegalArgumentException e) {
                return false;
            }
            Matcher matcher = pattern.matcher(part);
            return matcher.matches();
        }
    }
}
