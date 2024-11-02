package com.fujias.common.ueditor.upload;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.fujias.common.ueditor.PathFormat;
import com.fujias.common.ueditor.define.AppInfo;
import com.fujias.common.ueditor.define.BaseState;
import com.fujias.common.ueditor.define.FileType;
import com.fujias.common.ueditor.define.State;

/**
 * Base64 类型数据保存
 * 
 * @author chenqiang
 *
 */
public final class Base64Uploader {

    /**
     * 保存
     * 
     * @param content content
     * @param conf conf
     * @return 结果
     */
    public static State save(String content, Map<String, Object> conf) {

        byte[] data = decode(content);

        long maxSize = ((Long) conf.get("maxSize")).longValue();

        if (!validSize(data, maxSize)) {
            return new BaseState(false, AppInfo.MAX_SIZE);
        }

        String suffix = FileType.getSuffix("JPG");

        String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));

        savePath = savePath + suffix;
        String physicalPath = (String) conf.get("rootPath") + savePath;

        State storageState = StorageManager.saveBinaryFile(data, physicalPath);

        if (storageState.isSuccess()) {
            storageState.putInfo("url", PathFormat.format(savePath));
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }

        return storageState;
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }

}
