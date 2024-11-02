package com.fujias.common.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fujias.common.ueditor.define.State;

/**
 * 上传处理
 * 
 * @author chenqiang
 *
 */
public class Uploader {
    private HttpServletRequest request = null;
    private Map<String, Object> conf = null;

    public Uploader(HttpServletRequest request, Map<String, Object> conf) {
        this.request = request;
        this.conf = conf;
    }

    /**
     * 执行上传
     * 
     * @return 结果
     */
    public final State doExec() {
        String filedName = (String) this.conf.get("fieldName");
        State state = null;

        if ("true".equals(this.conf.get("isBase64"))) {
            state = Base64Uploader.save(this.request.getParameter(filedName), this.conf);
        } else {
            state = BinaryUploader.save(this.request, this.conf);
        }

        return state;
    }
}
