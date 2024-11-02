package com.fujias.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アクセスログのツールクラスです。
 * 
 * @author 陳強
 *
 */
public final class AccesslogUtil {
    protected static final Logger LORRER = LoggerFactory.getLogger(AccesslogUtil.class);

    private AccesslogUtil() {

    }

    /**
     * アクセスログを出力する。
     * 
     * @param log log
     */
    public static void log(String log) {
        LORRER.info(log);
    }

}
