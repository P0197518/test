package com.fujias.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * String処理の共通クラス。
 *
 * @version 1.0
 * @author 陳強
 */
public final class StringUtil {

    /** ナンバーフォーマット */
    public static final String REGEX_NUMBER_FORMAT = "[-+]?(?:([1-9][0-9]{0,2}){1}(,[0-9]{3})+|[1-9][0-9]*|0)(\\.\\d+)?";

    /** 符号表記（＋） */
    public static final String SIGN_POSITIVE = "+";

    /** 符号表記（-） */
    public static final String SIGN_NEGATIVE = "-";

    /** 符号表記（－） */
    public static final String SIGN_NEGATIVE_ZENKAKU = "－";

    /** 符号表記（カンマ） */
    public static final String COMMA_MARK = ",";

    /** 符号表記（二重引用符） */
    public static final String DOUBLE_QUOTATION_MARK = "\"";

    /** 符号表記（単引用符） */
    public static final String SINGLE_QUOTATION_MARK = "'";

    /** 符号表記（ドット） */
    public static final String DOT_MARK = ".";

    /** 符号表記（:） */
    public static final String COLON_MARK = ":";

    /** 符号表記（全角：） */
    public static final String COLON_FULL_MARK = "：";

    /** 符号表記（;） */
    public static final String SEMICOLON_MARK = ";";

    /** 全角スペース */
    public static final String FULL_SPACE = "　";

    /** 半角スペース */
    public static final String HALF_SPACE = " ";

    /** ブランク */
    public static final String BLANK = "";

    /** スラッシュ */
    public static final String SLASH = "/";

    /** 読点 */
    public static final String TOUTEN = "、";

    /** 左カッコ（全角） */
    public static final String LEFT_PARENTHESIS_ZENKAKU = "（";

    /** 右カッコ（全角） */
    public static final String RIGHT_PARENTHESIS_ZENKAKU = "）";

    /** 左カッコ（半角） */
    public static final String LEFT_PARENTHESIS_HANKAKU = "(";

    /** 右カッコ（半角） */
    public static final String RIGHT_PARENTHESIS_HANKAKU = ")";

    /** 波形 */
    public static final String NAMIGATA = "～";

    /** キャラセット: Windows-31J */
    public static final String CHARSET_WIN31J = "Windows-31J";

    /** キャラセット: MS932 */
    public static final String CHARSET_MS932 = "MS932";

    /** キャラセット: UTF-8 */
    public static final String CHARSET_UTF8 = "UTF-8";

    /** キャラセット: SJIS */
    public static final String CHARSET_SJIS = "SJIS";

    /** キャラセット: Shift-JIS */
    public static final String CHARSET_SHIFTJIS = "Shift-JIS";

    /** 改行文字: \r\n */
    public static final String NEWLINE_CRLF = "\r\n";

    /** 改行文字: \n */
    public static final String NEWLINE_LF = "\n";

    /** パス区切り文字: UNIX: <code>'/'</code>;Microsoft: <code>'\\'</code>. */
    public static final String SEPARATOR = File.separator;
    // 获取密码组合
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private StringUtil() {

    }

    /**
     * toString処理を再実装する
     * 
     * @param obj obj
     * @return 結果文字列
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    /**
     * リスト文字列の間に、区切りを追加する
     * 
     * @param source source
     * @param delimiter delimiter
     * @return 編集された文字列
     */
    public static String join(Collection<String> source, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator<?> iter = source.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

    /**
     * 文字列が空であるかどうかを返す。
     * <p>
     * 対象文字列がNULL、又は空文字列である場合、trueを返す。<br>
     * 上記以外の場合、falseを返す。
     * </p>
     * 
     * @param value 対象文字列
     * @return チェック結果
     */
    public static boolean isEmpty(String value) {
        return (value == null || BLANK.equals(value));
    }

    /**
     * 文字列が非空であるかどうかを返す。
     * <p>
     * 対象文字列がNULL、又は空文字列である場合、falseを返す。<br>
     * 上記以外の場合、trueを返す。
     * </p>
     * 
     * @param value 対象文字列
     * @return チェック結果
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * 文字列が空（Trim後）であるかどうかを返す。
     * <p>
     * 対象文字列がNULL、又は空白文字である場合、trueを返す。<br>
     * 上記以外の場合、falseを返す。
     * </p>
     * 
     * @param value 対象文字列
     * @return チェック結果
     */
    public static boolean isEmptyOrWhitespace(String value) {
        return isEmpty(trim(value).orElse(null));
    }

    /**
     * 文字列が非空（Trim後）であるかどうかを返す。
     * <p>
     * 対象文字列がNULL、又は空白文字である場合、falseを返す。<br>
     * 上記以外の場合、trueを返す。
     * </p>
     * 
     * @param value 対象文字列
     * @return チェック結果
     */
    public static boolean isNotEmptyOrWhitespace(String value) {
        return !isEmptyOrWhitespace(value);
    }

    /**
     * 文字列同士が同じ文字列を表現しているかどうかを返します。
     * <p>
     * null 同士の場合、true が返るので注意。
     * </p>
     * 
     * @param val1 対象その１
     * @param val2 対象その２
     * @return 文字列同士が同じ文字列を表現しているかどうか。両方 null の場合、true が返る。
     */
    public static boolean equals(String val1, String val2) {

        return val1 == null ? val2 == null : val1.equals(val2);
    }

    /**
     * 文字列同士が大文字小文字を無視して同じ文字列を表現しているかどうかを返します。
     * <p>
     * null 同士の場合、true が返るので注意。
     * </p>
     * 
     * @param val1 対象その１
     * @param val2 対象その２
     * @return 文字列同士が大文字小文字を無視して同じ文字列を表現しているかどうか。両方 null の場合、true が返る。
     */
    public static boolean equalsIgnoreCase(String val1, String val2) {

        return val1 == null ? val2 == null : val1.equalsIgnoreCase(val2);
    }

    /**
     * 数値として妥当かチェックします。
     * 
     * @param value チェック項目
     * @return 数値として妥当ならtrue
     */
    public static boolean isNumber(String value) {
        return isNumber(value, true, true);
    }

    /**
     * 数値として妥当かチェックします。
     * <p>
     * 符号表記には、符号を含む文字列を数値として判断するかどうかを設定します。<br>
     * 符号表記にtrueを指定してチェック項目に符号が含まれなかった場合は、trueを返却します。<br>
     * 符号表記にfalseを指定してチェック項目に符号が含まれた場合は、falseを返却します。<br>
     * <br>
     * カンマ表記には、カンマを含む文字列を数値として判断するかどうかを設定します。<br>
     * カンマ表記にtrueを指定してチェック項目にカンマが含まれなかった場合は、trueを返却します。<br>
     * カンマ表記にfalseを指定してチェック項目にカンマが含まれた場合、falseを返却します。<br>
     * </p>
     * 
     * @param value チェック項目
     * @param isSigned 符号表記（符号を含む場合はtrueを指定してください）
     * @param isComma カンマ表記（カンマを含む場合はtrueを指定してください）
     * @return 数値として妥当ならtrue
     */
    public static boolean isNumber(String value, boolean isSigned, boolean isComma) {
        if (isEmpty(value)) {
            return false;
        }
        if (!value.matches(REGEX_NUMBER_FORMAT)) {
            return false;
        }
        if (!isSigned) {
            if (StringUtils.startsWithIgnoreCase(value, SIGN_POSITIVE) || StringUtils.startsWithIgnoreCase(value, SIGN_NEGATIVE)) {
                return false;
            }
        }
        if (!isComma) {
            if (value.contains(COMMA_MARK)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数字として妥当かチェックします。
     * 
     * @param value チェック項目
     * @return 数字として妥当ならtrue
     */
    public static boolean isNumric(String value) {
        return value.matches("[0-9]*");
    }

    /**
     * 数字文字列のフォーマット処理（カッマを追加）
     *
     * @param numberStr 数字文字列
     * @return フォーマット後の文字列
     */
    public static Optional<String> addComma(String numberStr) {
        if (isEmpty(numberStr)) {
            return Optional.ofNullable(numberStr);
        }
        if (!isNumber(numberStr, true, false)) {
            throw new IllegalArgumentException("正しい数値ではない:" + numberStr);
        }
        StringBuilder result = new StringBuilder();
        String target = numberStr;
        String fraction = BLANK;
        String sign = BLANK;
        int idx = numberStr.indexOf(DOT_MARK);
        if (idx != -1) {
            target = numberStr.substring(0, idx);
            fraction = numberStr.substring(idx);
        }
        if (numberStr.charAt(0) == '-') {
            sign = "-";
            target = target.substring(1);
        }
        StringBuilder buff = new StringBuilder(target).reverse();
        for (int i = 1; i <= buff.length(); i++) {
            result.append(buff.charAt(i - 1));
            if (i % 3 == 0 && i != buff.length()) {
                result.append(COMMA_MARK);
            }
        }
        return Optional.of(result.reverse().insert(0, sign).append(fraction).toString());
    }

    /**
     * 文字列のフォーマット処理（カッマを除く）
     * 
     * @param str 文字列
     * @return フォーマット後の文字列
     */
    public static Optional<String> deleteComma(String str) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        String resultStr = str.replaceAll(COMMA_MARK, BLANK);
        return Optional.ofNullable(resultStr);

    }

    /**
     * 文字列のフォーマット処理（カッマを除く）
     * 
     * @param str 文字列
     * @return フォーマット後の文字列
     */
    public static Optional<String> deleteSlash(String str) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        String resultStr = str.replaceAll(SLASH, BLANK);
        return Optional.ofNullable(resultStr);

    }

    /**
     * 郵便番号フォーマッタ
     * 
     * @param numberStr 半角数字文字列
     * @return フォーマット後の文字列
     */
    public static Optional<String> mailNoFormat(String numberStr) {
        if (isEmpty(numberStr)) {
            return Optional.ofNullable(numberStr);
        }
        Pattern pattern = Pattern.compile("^\\d{7}$");
        Matcher matcher = pattern.matcher(numberStr);
        if (!matcher.find()) {
            throw new IllegalArgumentException("正しい郵便番号ではない(9999999):" + numberStr);
        }
        StringBuilder result = new StringBuilder(numberStr);
        result.insert(3, SIGN_NEGATIVE);
        return Optional.ofNullable(result.toString());
    }

    /**
     * 文字列を指定のバイト数以内になるように削る。
     * 
     * @param str 元文字列
     * @param maxBytes 最大バイト数
     * @return 切り出し後文字列
     */
    public static Optional<String> byteSubstring(String str, int maxBytes) {
        return byteSubstring(str, maxBytes, "Windows-31J");
    }

    /**
     * 文字列を指定のバイト数以内になるように削る。
     * 
     * @param str 元文字列
     * @param maxBytes 最大バイト数
     * @param charaset キャラセット
     * @return 切り出し後文字列
     */
    public static Optional<String> byteSubstring(String str, int maxBytes, String charaset) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        if (maxBytes <= 0) {
            throw new IllegalArgumentException("引数のバイト数不正:" + maxBytes);
        }
        StringBuilder buf = new StringBuilder(str);
        while (getByteLength(buf.toString(), charaset) > maxBytes) {
            buf.setLength(buf.length() - 1);
        }
        return Optional.ofNullable(buf.toString());
    }

    /**
     * 文字列を指定のバイト数以内になるように削る（バイト数未満の場合半角スペースで補足する）。
     * 
     * @param str 元文字列
     * @param maxBytes 最大バイト数
     * @return 切り出し後文字列
     */
    public static String byteSubstringWithHfSpace(String str, int maxBytes) {
        return byteSubstringWithHfSpace(str, maxBytes, "Windows-31J");
    }

    /**
     * 文字列を指定のバイト数以内になるように削る（バイト数未満の場合半角スペースで補足する）。
     * 
     * @param str 元文字列
     * @param maxBytes 最大バイト数
     * @param charaset キャラセット
     * @return 切り出し後文字列
     */
    public static String byteSubstringWithHfSpace(String str, int maxBytes, String charaset) {
        String result = byteSubstring(str, maxBytes, charaset).orElse(BLANK);
        int length = getByteLength(result, charaset);
        return length < maxBytes ? result.concat(getStrs(maxBytes - length, HALF_SPACE)) : result;
    }

    /**
     * 指定バイト数以降の文字列取得
     * 
     * @param str 対象文字列
     * @param byteLength 指定バイト数
     * @param charaset エンコード
     * @return 切り出し後文字列
     */
    public static Optional<String> subStrByByteLength(String str, int byteLength, String charaset) {
        if (isEmpty(str) || byteLength == 0) {
            return Optional.ofNullable(str);
        }
        if (byteLength < 0) {
            throw new IllegalArgumentException("引数の指定バイト数が0以上でなければいけません：" + byteLength);
        }
        int slen = getByteLength(str, charaset);
        if (slen <= byteLength) {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder(str);
        while (slen - getByteLength(sb.toString()) < byteLength) {
            sb.deleteCharAt(0);
        }
        return Optional.ofNullable(sb.toString());
    }

    /**
     * 指定バイト数範囲の文字列取得
     * 
     * @param str 対象文字列
     * @param byteFrom 指定バイト数From
     * @param byteTo 指定バイト数To
     * @param charaset エンコード
     * @return 切り出し後文字列
     */
    public static Optional<String> subStrByByteRange(String str, int byteFrom, int byteTo, String charaset) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        if (byteFrom < 0) {
            throw new IllegalArgumentException("引数の指定バイト数Fromが0以上でなければいけません：" + byteFrom);
        }
        if (byteTo < 0) {
            throw new IllegalArgumentException("引数の指定バイト数Toが0以上でなければいけません：" + byteTo);
        }
        if (byteFrom > byteTo) {
            throw new IllegalArgumentException("引数の指定バイト数Fromが指定バイト数To以下でなければいけません：" + byteFrom + "、" + byteTo);
        }
        String sb = null;
        sb = subStrByByteLength(str, byteFrom > 0 ? byteFrom - 1 : byteFrom, charaset).get();
        sb = byteSubstring(sb, byteTo - byteFrom + 1).get();
        return Optional.ofNullable(sb.toString());
    }

    /**
     * 文字列のバイト数を取得する。
     * 
     * @param str 文字列
     * @return バイトサイズ
     */
    public static int getByteLength(String str) {
        return getByteLength(str, "Windows-31J");
    }

    /**
     * 文字列のバイト数を取得する。
     * 
     * @param str 文字列
     * @param charaset キャラセット
     * @return バイトサイズ
     */
    public static int getByteLength(String str, String charaset) {
        if (isEmpty(str)) {
            return 0;
        }
        if (isEmpty(charaset)) {
            throw new IllegalArgumentException("引数のエンコードが未指定");
        }
        byte[] bytes;
        try {
            bytes = str.getBytes(charaset);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("文字列バイト数の取得に失敗しました");
        }
        return bytes.length;
    }

    /**
     * 対象がヌルの場合、ブランクに変換
     * 
     * @param str convert対象
     * @return 処理結果
     */
    public static String nvl(String str) {
        if (str == null) {
            return BLANK;
        }
        return str;
    }

    /**
     * SHORT型の数字を文字列に変換する。
     * 
     * @param num SHORT型の数字
     * @return 処理結果
     */
    public static String shortToStr(Short num) {
        if (num == null) {
            return BLANK;
        }
        return String.valueOf(num);
    }

    /**
     * 数字文字列を数字(int)に変換する。
     * 
     * @param num 数字文字列
     * @return 処理結果
     */
    public static int stringToInt(String num) {
        if (isEmpty(num)) {
            return 0;
        }
        int result = 0;
        try {
            result = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("引数がintに変換できません：" + num);
        }
        return result;
    }

    /**
     * 数字文字列を数字(long)に変換する。
     * 
     * @param num 数字文字列
     * @return 処理結果
     */
    public static long stringToLong(String num) {
        if (isEmpty(num)) {
            return 0L;
        }
        long result = 0;
        try {
            result = Long.parseLong(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("引数がlongに変換できません：" + num);
        }
        return result;
    }

    /**
     * 数字文字列を数字(double)に変換する。
     * 
     * @param num 数字文字列
     * @return 処理結果
     */
    public static double stringToDouble(String num) {
        if (isEmpty(num)) {
            return 0L;
        }
        double result = 0L;
        try {
            result = Double.parseDouble(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("引数がdoubleに変換できません：" + num);
        }
        return result;
    }

    /**
     * オブジェクト（Integer）型を基本型（int）に変換する。
     * 
     * @param num オブジェクト（Integer）型
     * @return 処理結果
     */
    public static int integerToInt(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /**
     * 左から文字の追加
     * 
     * @param value 対象文字列
     * @param ch 追加文字
     * @param length 追加後文字数
     * @return 文字列
     */
    public static String leftPad(String value, char ch, int length) {
        if (value == null) {
            return BLANK;
        }
        if (value.length() >= length || ch == '\0') {
            return value;
        }
        StringBuilder sb = new StringBuilder(value);
        for (int i = 0; i < length - value.length(); i++) {
            sb.insert(0, ch);
        }
        return sb.toString();
    }

    /**
     * 右から文字の追加
     * 
     * @param value 対象文字列
     * @param ch 追加文字
     * @param length 追加後文字数
     * @return 文字列
     */
    public static String rightPad(String value, char ch, int length) {
        if (value == null) {
            return BLANK;
        }
        if (value.length() >= length || ch == '\0') {
            return value;
        }
        StringBuilder sb = new StringBuilder(value);
        for (int i = 0; i < length - value.length(); i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 指定文字数の文字列取得
     * 
     * @param len 指定文字数
     * @param str 指定文字
     * @return 文字列
     */
    public static String getStrs(int len, String str) {
        if (len < 0) {
            throw new IllegalArgumentException("引数の指定文字数が0以上でなければいけません：" + len);
        }
        StringBuilder result = new StringBuilder();
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                result.append(str);
            }
        }
        return result.toString();
    }

    /**
     * 前後スペース除く
     * 
     * @param str 対象文字列
     * @return 編集後文字列
     */
    public static Optional<String> trim(String str) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        return Optional.ofNullable(str.trim());
    }

    /**
     * 前スペース除く
     * 
     * @param str 対象文字列
     * @return 編集後文字列
     */
    public static Optional<String> leftTrim(String str) {
        return leftTrim(str, ' ');
    }

    /**
     * 前スペース除く
     * 
     * @param str 対象文字列
     * @param trimStr trimStr
     * @return 編集後文字列
     */
    public static Optional<String> leftTrim(String str, char trimStr) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        int pos;
        for (pos = 0; pos < str.length(); pos++) {
            if (str.charAt(pos) != trimStr) {
                break;
            }
        }
        return Optional.ofNullable(str.substring(pos));
    }

    /**
     * 後スペース除く
     * 
     * @param str 対象文字列
     * @return 編集後文字列
     */
    public static Optional<String> rightTrim(String str) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        int pos;
        for (pos = str.length() - 1; pos >= 0; pos--) {
            if (str.charAt(pos) != ' ') {
                break;
            }
        }
        return Optional.ofNullable(str.substring(0, pos + 1));
    }

    /**
     * 全スペース除く
     * 
     * @param str 対象文字列
     * @return 編集後文字列
     */
    public static Optional<String> allTrim(String str) {
        if (isEmpty(str)) {
            return Optional.ofNullable(str);
        }
        return Optional.ofNullable(str.replaceAll(HALF_SPACE, BLANK));
    }

    /**
     * メッセージ内容を返す
     * 
     * @param message メッセージテンプレート
     * @param args 文字列アレイ
     * @return メッセージ内容
     */
    public static String messageFormat(String message, String... args) {
        if (isEmpty(message) || args.length == 0) {
            return message;
        }
        for (int i = 0; i < args.length; i++) {
            String replaceStr = "\\{" + i + "\\}";
            message = message.replaceAll(replaceStr, args[i]);
        }
        return message;
    }

    /**
     * 引数文字列は半角英数字のみで構成されているかどうかチェックします。
     *
     * @param str 文字列
     * @return True:半角英数字のみで構成されている
     */
    public static boolean isAlphaNumeric(String str) {
        if (isEmpty(str)) {
            return true;
        }
        String regex = "[A-Za-z0-9]+$";
        return str.matches(regex);
    }

    /**
     * 引数文字列は半角英文のみで構成されているかどうかチェックします。
     *
     * @param str 文字列
     * @return True:半角英文のみで構成されている
     */
    public static boolean isEnNumeric(String str) {
        if (isEmpty(str)) {
            return true;
        }
        char[] chars = String.valueOf(str).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!(0x0020 <= chars[i] && chars[i] <= 0x007E)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 引数文字列は実数、金額（小数がある）のみで構成されているかどうかチェックします。
     *
     * @param str 文字列
     * @return True:実数、金額（小数がある）のみで構成されている
     */
    public static boolean isRealNumber(String str) {
        return isRealNumber(str, -1, -1);
    }

    /**
     * 引数文字列は実数、金額（小数がある）のみで構成されているかどうかチェックします。
     *
     * @param str 文字列
     * @param intLength 整数桁数
     * @param decLength 小数桁数
     * @return True:実数、金額（小数がある）のみで構成されている
     */
    public static boolean isRealNumber(String str, int intLength, int decLength) {
        if (isEmpty(str)) {
            return true;
        }
        if (!str.matches("[-]?(?:[1-9][0-9]*|0)(\\.\\d+)?")) {
            return false;
        }
        String[] posRealValue = str.split("\\.");
        if (intLength > -1) {
            int realintLength = intLength;
            if (str.startsWith("-")) {
                realintLength = realintLength + 1;
            }
            if (posRealValue[0].length() > realintLength) {
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

    /**
     * 引数文字列は正実数、正金額（小数がある）のみで構成されているかどうかチェックします。
     *
     * @param str 文字列
     * @return True:正実数、正金額（小数がある）のみで構成されている
     */
    public static boolean isPosRealNumber(String str) {
        if (isEmpty(str)) {
            return true;
        }
        return str.matches("(?:[1-9][0-9]*|0)(\\.\\d+)?");
    }

    /**
     * 引数文字列は郵便番号のみで構成されているかどうかチェックします。
     *
     * @param str 文字列
     * @return True:郵便番号のみで構成されている
     */
    public static boolean isMailNo(String str) {
        if (isEmpty(str)) {
            return true;
        }
        return str.matches("^([0-9]{3}-[0-9]{4})$");
    }

    /**
     * 改行文字エスケープ。
     * 
     * @param str 対象文字列
     * @return 変換後文字列
     */
    public static String newLineEscape(String str) {
        if (isEmpty(str)) {
            return BLANK;
        }
        return str.replaceAll(NEWLINE_CRLF, BLANK).replaceAll(NEWLINE_LF, BLANK);
    }

    /**
     * 右补位，左对齐
     * 
     * @param oriStr 原字符串
     * @param len 目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    public static String padLeft(String oriStr, int len, char alexin) {
        int strlen = oriStr.length();
        String tmpStr = "";
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                tmpStr = tmpStr + alexin;
            }
        }
        tmpStr = tmpStr + oriStr;
        return tmpStr;
    }

    /**
     * 英文字母check
     * 
     * @param charSrc 字符
     * @return false
     */
    public static boolean checkEnglish(char charSrc) {
        int i = (int) charSrc;
        if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 按照指定位数分割字符串
     * 
     * @param src 要分割的字符串
     * @param size 指定大小
     * @return false
     */
    public static List<String> splitWithSize(String src, int size) {
        List<String> paths = new ArrayList<String>();

        double arrSize = Math.ceil((double) src.length() / (double) size);
        for (int i = 0; i < arrSize; i++) {
            if ((i + 1) * size < src.length()) {
                paths.add(src.substring(i * size, (i + 1) * size));
            } else {
                paths.add(src.substring(i * size));
            }
        }
        return paths;
    }

    /**
     * 获取随机密码，len为密码长度
     * 
     * @param len len
     * @return password
     */
    public static String generateStr(int len) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

}
