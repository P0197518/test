package com.fujias.common.ueditor.define;

import java.util.HashMap;
import java.util.Map;

/**
 * FileType
 * 
 * @author fujias
 *
 */
public class FileType {

    public static final String JPG = "JPG";

    @SuppressWarnings("serial")
    private static final Map<String, String> types = new HashMap<String, String>() {
        {

            put(FileType.JPG, ".jpg");

        }
    };

    public static String getSuffix(String key) {
        return FileType.types.get(key);
    }

    /**
     * 根据给定的文件名,获取其后缀信息
     * 
     * @param filename filename
     * @return 后缀
     */
    public static String getSuffixByFilename(String filename) {

        return filename.substring(filename.lastIndexOf(".")).toLowerCase();

    }

}
