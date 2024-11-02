package com.fujias.common.ueditor.define;

import java.util.HashMap;
import java.util.Map;

/**
 * MimeType
 * 
 * @author fujias
 *
 */
public class MimeType {

    @SuppressWarnings("serial")
    public static final Map<String, String> types = new HashMap<String, String>() {
        {
            put("image/gif", ".gif");
            put("image/jpeg", ".jpg");
            put("image/jpg", ".jpg");
            put("image/png", ".png");
            put("image/bmp", ".bmp");
        }
    };

    public static String getSuffix(String mime) {
        return MimeType.types.get(mime);
    }

}
