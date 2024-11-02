package com.fujias.common.dboprate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MybatisのSqlHelperクラスです。
 * 
 * @author 陳強
 *
 */
public final class MybatisSqlHelper {

    private MybatisSqlHelper() {

    }

    /**
     * SQLをフォーマットする。
     * 
     * @param querySelect querySelect
     * @return 編集されたSQL
     */
    public static String getCountSql(String querySelect) {

        querySelect = compress(querySelect);
        int orderIndex = getLastOrderInsertPoint(querySelect);

        int formIndex = getAfterFormInsertPoint(querySelect);
        String select = querySelect.substring(0, formIndex);

        // DISTINCTがあれば、子検索を追加して、外層でCOUNTを行る。
        if (select.toLowerCase().indexOf("select distinct") != -1 || querySelect.toLowerCase().indexOf("group by") != -1) {
            return new StringBuffer(querySelect.length()).append("select count(1) count from (").append(querySelect.substring(0, orderIndex))
                            .append(" ) t").toString();
        } else {
            return new StringBuffer(querySelect.length()).append("select count(1) count ").append(querySelect.substring(formIndex, orderIndex))
                            .toString();
        }
    }

    /**
     * 最後のOrder Byの追加箇所を取得する
     * 
     * @return 最後のOrder Byの追加箇所
     */
    private static int getLastOrderInsertPoint(String querySelect) {
        int orderIndex = querySelect.toLowerCase().lastIndexOf("order by");
        if (orderIndex == -1) {
            orderIndex = querySelect.length();
        } else {
            if (!isBracketCanPartnership(querySelect.substring(orderIndex, querySelect.length()))) {
                throw new RuntimeException("Order By is required!!!");
            }
        }
        return orderIndex;
    }

    /**
     * SQLのフォーマットする、間は一つスペースで区切る。
     * 
     * @param sql SQL
     * @return フォーマットされたSQL
     */
    private static String compress(String sql) {
        return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }

    /**
     * 最初のFromの箇所を取得する。
     * 
     * @param querySelect querySelect
     * @return 最初のFromの箇所
     */
    private static int getAfterFormInsertPoint(String querySelect) {
        String regex = "\\s+FROM\\s+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(querySelect);
        while (matcher.find()) {
            int fromStartIndex = matcher.start(0);
            String text = querySelect.substring(0, fromStartIndex);
            if (isBracketCanPartnership(text)) {
                return fromStartIndex;
            }
        }
        return 0;
    }

    /**
     * 括弧の正確性を判定する
     * 
     * @param text 判定したいSQL
     * @return 正確性
     */
    private static boolean isBracketCanPartnership(String text) {
        if (text == null || (getIndexOfCount(text, '(') != getIndexOfCount(text, ')'))) {
            return false;
        }
        return true;
    }

    /**
     * 文字が別の文字列に存在するか回数を取得する。
     * 
     * @param text 文字列
     * @param ch 文字
     * @return 存在回数
     */
    private static int getIndexOfCount(String text, char ch) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            count = (text.charAt(i) == ch) ? count + 1 : count;
        }
        return count;
    }
}
