package com.fujias.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 日付の共通処理クラスです。
 * 
 * @author 陳強
 *
 */
public final class DateUtil {

    public static final String DATE_FORMAT4 = "yyyy";
    public static final String DATE_FORMAT7 = "yyyy-MM";
    public static final String DATE_FORMAT6_YEARMONTH = "yyyyMM";
    public static final String DATE_FORMAT6 = "yyMMdd";
    public static final String DATE_FORMAT8 = "yyyyMMdd";
    public static final String DATE_FORMAT10 = "yyyy/MM/dd";
    public static final String DATE_FORMAT8_2 = "yyyy/M/d";
    public static final String DATE_FORMAT16 = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT19 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT17 = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT21 = "dd-MM-yyyy";
    public static final String DATE_FORMAT22 = "yyyy-MM-dd";
    public static final String DATE_FORMAT5 = "MM/dd";

    private static final List<String> DATE_FROMATS = new ArrayList<>();

    static {
        DATE_FROMATS.add(DATE_FORMAT4);
        DATE_FROMATS.add(DATE_FORMAT6_YEARMONTH);
        DATE_FROMATS.add(DATE_FORMAT6);
        DATE_FROMATS.add(DATE_FORMAT7);
        DATE_FROMATS.add(DATE_FORMAT8);
        DATE_FROMATS.add(DATE_FORMAT10);
        DATE_FROMATS.add(DATE_FORMAT8_2);
        DATE_FROMATS.add(DATE_FORMAT16);
        DATE_FROMATS.add(DATE_FORMAT19);
        DATE_FROMATS.add(DATE_FORMAT17);
        DATE_FROMATS.add(DATE_FORMAT21);
        DATE_FROMATS.add(DATE_FORMAT22);
        DATE_FROMATS.add(DATE_FORMAT5);
    }

    private DateUtil() {

    }

    /**
     * 現在時点の日付を取得する。
     * 
     * @return 日付
     */
    public static java.util.Date getNowDate() {
        return new java.util.Date();
    }

    /**
     * 現在時点の日付の文字列を取得する。
     * 
     * @return 日付の文字列
     */
    public static String getNowDateString() {
        return getNowDateString(DATE_FORMAT8);
    }

    /**
     * 現在時点の日付の文字列を取得する。
     * 
     * @param format format
     * @return 日付の文字列
     */
    public static String getNowDateString(String format) {
        checkDateFormat(format);
        java.util.Date nowDate = new java.util.Date();
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.format(nowDate);
    }

    /**
     * 現在時点の前月の1日の文字列を取得する。
     * 
     * @return 日付の文字列
     */
    public static String getLastMonthFirstDayString() {
        return getLastMonthFirstDayString(DATE_FORMAT8);
    }

    /**
     * 現在時点の前月の1日の文字列を取得する。
     * 
     * @param format format
     * @return 日付の文字列
     */
    public static String getLastMonthFirstDayString(String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(cal.getTime());
    }

    /**
     * 日付対象を文字列に変換する
     * 
     * @param source source
     * @return 日付の文字列
     */
    public static String formatDateToString(Date source) {
        return formatDateToString(source, DATE_FORMAT8);
    }

    /**
     * 日付対象を文字列に変換する
     * 
     * @param source source
     * @param format format
     * @return 日付の文字列
     */
    public static String formatDateToString(Date source, String format) {
        checkDateFormat(format);
        if (source == null) {
            return "";
        }
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.format(source);
    }

    /**
     * 文字列を日付対象に変換する
     * 
     * @param source source
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date formatStringToDateAllFormat(String source) throws ParseException {

        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }

        if (source.matches("^\\d{4}$")) {
            return formatStringToDate(source, DATE_FORMAT4);
        } else if (source.matches("^\\d{8}$")) {
            return formatStringToDate(source, DATE_FORMAT8);
        } else if (source.matches("^\\d{4}/\\d{2}$")) {
            return formatStringToDate(source, DATE_FORMAT7);
        } else if (source.matches("^\\d{4}/\\d{2}/\\d{2}$")) {
            return formatStringToDate(source, DATE_FORMAT10);
        } else if (source.matches("^\\d{2}/\\d{2}$")) {
            return formatStringToDate(source, DATE_FORMAT5);
        } else if (source.matches("^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$")) {
            return formatStringToDate(source, DATE_FORMAT16);
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
            return formatStringToDate(source, DATE_FORMAT8_2);
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return formatStringToDate(source, DATE_FORMAT19);
        } else if (source.matches("^\\d{4}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,3}$")) {
            return formatStringToDate(source, DATE_FORMAT17);
        } else {
            throw new IllegalArgumentException("Invalid date value '" + source + "'");
        }
    }

    /**
     * 文字列を日付対象に変換する
     * 
     * @param source source
     * @param format format
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date formatStringToDate(String source, String format) throws ParseException {
        checkDateFormat(format);
        if (source == null || source.isEmpty()) {
            return null;
        }
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.parse(source);
    }

    /**
     * 文字列が時刻かを判定する
     * 
     * @param timeString timeString
     * @return 判定結果
     */
    public static boolean isTimeString(String timeString) {
        if (StringUtil.isEmptyOrWhitespace(timeString)) {
            return false;
        }
        return timeString.matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})");
    }

    /**
     * 文字列が日付に変換できるかを判定する
     * 
     * @param dateString dateString
     * @return 判定結果
     */
    public static boolean isDateString(String dateString) {
        if (dateString == null || "".equals(dateString)) {
            return true;
        }
        String tmpString = dateString.replace("/", "-");
        return isDateString(tmpString, DATE_FORMAT8);
    }

    /**
     * 文字列が日付に変換できるかを判定する
     * 
     * @param dateString dateString
     * @param formatString formatString
     * @return 判定結果
     */
    public static boolean isDateString(String dateString, String formatString) {
        checkDateFormat(formatString);
        if (dateString == null || "".equals(dateString)) {
            return true;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        boolean dateflag = true;
        try {
            format.parse(dateString);
        } catch (ParseException e) {
            dateflag = false;
        }
        return dateflag;
    }

    private static void checkDateFormat(String format) {
        if (!DATE_FROMATS.contains(format)) {
            throw new IllegalArgumentException("限定以外の日付フォーマットを使用しないでください。");
        }
    }

    /**
     * 二つ日付の時間の差異を取得する
     * 
     * @param datex datex
     * @param datey datey
     * @return 時の差異
     */
    public static BigDecimal calculateHours(java.sql.Timestamp datex, java.sql.Timestamp datey) {
        double longdays = (datex.getTime() - datey.getTime());
        BigDecimal v1 = new BigDecimal(longdays);
        BigDecimal v2 = new BigDecimal(60 * 60 * 1000);
        BigDecimal v3 = v1.divide(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return v3;
    }

    /**
     * 日付のパースに失敗した場合はnullを返す、parseです。
     * 
     * @param text 日付文字列
     * @return パースした結果
     */
    public static Optional<Date> nullableParse(String text) {
        Optional<Date> result = Optional.empty();
        result = nullableParse(text, DateUtil.DATE_FORMAT10);
        if (result.isPresent()) {
            return result;
        }
        return result;
    }

    /**
     * 日付のパースに失敗した場合はnullを返す、parseです。
     * 
     * @param text 日付文字列
     * @param pattern 日付書式
     * @return パースした結果
     */
    public static Optional<Date> nullableParse(String text, String pattern) {
        Date result = null;
        try {
            result = parse(text, pattern).orElse(null);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(result);
    }

    /**
     * 年のパースに失敗した場合はnullを返す、parseです。
     * 
     * @param text 年文字列
     * @param pattern 日付書式
     * @return パースした結果
     */
    public static Optional<String> nullableParseYear(String text, String pattern) {
        String result = null;
        try {
            result = yearFormat(text, pattern, DateUtil.DATE_FORMAT4).orElse(null);
            if (result != null) {
                if (1800 > Integer.parseInt(result) || Integer.parseInt(result) >= 2500) {
                    return Optional.empty();
                }
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(result);
    }

    /**
     * 年を指定フォーマットに転換する
     * 
     * @param dateStr データ
     * @param nowPattern 当該のフォーマット
     * @param formatPattern フォーマット
     * @return 年文字列（転換後）
     */
    public static Optional<String> yearFormat(String dateStr, String nowPattern, String formatPattern) {
        if (StringUtil.isEmpty(dateStr)) {
            return Optional.empty();
        }
        Year localDate = Year.parse(dateStr, DateTimeFormatter.ofPattern(nowPattern));
        return Optional.ofNullable(DateTimeFormatter.ofPattern(formatPattern).format(localDate));
    }

    /**
     * 日付フォーマッター
     * 
     * @param dateStr 日付文字列
     * @param pattern フォーマット
     * @return 日付
     */
    public static Optional<Date> parse(String dateStr, String pattern) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(pattern)) {
            return Optional.empty();
        }
        Date date = null;
        try {
            String realPattern = pattern.replaceAll("y", "u").replaceAll("Y", "u").replaceAll("D", "d");
            if (pattern.contains("H") || (pattern.contains("h"))) {
                LocalDateTime datetime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
                ZoneId zoneId = ZoneId.systemDefault();
                date = Date.from(datetime.atZone(zoneId).toInstant());
            } else {
                LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(realPattern).withResolverStyle(ResolverStyle.STRICT));
                ZoneId zoneId = ZoneId.systemDefault();
                date = Date.from(localDate.atStartOfDay(zoneId).toInstant());
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("正しい日付ではない(" + pattern + "):" + dateStr);
        }
        return Optional.ofNullable(date);
    }

    /**
     * 获取月的最后一天
     * 
     * @param date date
     * @return 最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // 获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }

    /**
     * 获取月的第一天
     * 
     * @param date date
     * @return 第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, 1);

        try {
            return formatStringToDateAllFormat(formatDateToString(cal.getTime()));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取制定天的最有一各时刻
     * 
     * @param date date
     * @return date
     */
    public static Date getLastSecondOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.set(year, month, day, 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 当前时间的之前月份
     * 
     * @param date date
     * @param count count
     * @return 计算后时间
     */
    public static Date addMonth(Date date, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, count);
        return cal.getTime();
    }
}
