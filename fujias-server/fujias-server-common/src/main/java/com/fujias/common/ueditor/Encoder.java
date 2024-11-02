package com.fujias.common.ueditor;

/**
 * Encoder
 * 
 * @author fujias
 *
 */
public class Encoder {

    /**
     * 格式化转换
     * 
     * @param input input
     * @return 转码结果
     */
    public static String toUnicode(String input) {

        StringBuilder builder = new StringBuilder();
        char[] chars = input.toCharArray();

        for (char ch : chars) {

            if (ch < 256) {
                builder.append(ch);
            } else {
                builder.append("\\u" + Integer.toHexString(ch & 0xffff));
            }

        }
        return builder.toString();
    }

}
