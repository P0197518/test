package com.fujias.common.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.configurationprocessor.json.JSONException;

import com.fujias.common.ueditor.define.ActionMap;
import com.fujias.common.ueditor.define.AppInfo;
import com.fujias.common.ueditor.define.BaseState;
import com.fujias.common.ueditor.define.State;
import com.fujias.common.ueditor.hunter.FileManager;
import com.fujias.common.ueditor.hunter.ImageHunter;
import com.fujias.common.ueditor.upload.Uploader;

/**
 * ActionEnter
 * 
 * @author fujias
 *
 */
public class ActionEnter {

    private HttpServletRequest request = null;

    private String rootPath = null;
    private String contextPath = null;

    private String actionType = null;

    private ConfigManager configManager = null;

    /**
     * 处理入口
     * 
     * @param request request
     * @param rootPath rootPath
     */
    public ActionEnter(HttpServletRequest request, String rootPath) {

        this.request = request;
        this.rootPath = rootPath;
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());

    }

    /**
     * 执行处理
     * 
     * @return 处理结果
     * @throws JSONException JSONException
     */
    public String exec() throws JSONException {

        String callbackName = this.request.getParameter("callback");

        if (callbackName != null) {

            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJsonString();
            }

            return callbackName + "(" + this.invoke() + ");";

        } else {
            return this.invoke();
        }

    }

    /**
     * 触发处理
     * 
     * @return 处理结果
     * @throws JSONException JSONException
     */
    public String invoke() throws JSONException {

        if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJsonString();
        }

        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJsonString();
        }

        State state = null;

        int actionCode = ActionMap.getType(this.actionType);

        Map<String, Object> conf = null;

        switch (actionCode) {

        case ActionMap.CONFIG:
            return this.configManager.getAllConfig().toString();

        case ActionMap.UPLOAD_IMAGE:
        case ActionMap.UPLOAD_SCRAWL:
        case ActionMap.UPLOAD_VIDEO:
        case ActionMap.UPLOAD_FILE:
            conf = this.configManager.getConfig(actionCode);
            state = new Uploader(request, conf).doExec();
            break;

        case ActionMap.CATCH_IMAGE:
            conf = configManager.getConfig(actionCode);
            String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
            state = new ImageHunter(conf).capture(list);
            break;

        case ActionMap.LIST_IMAGE:
        case ActionMap.LIST_FILE:
            conf = configManager.getConfig(actionCode);
            int start = this.getStartIndex();
            state = new FileManager(conf).listFile(start);
            break;
        default:
            break;
        }

        return state.toJsonString();

    }

    /**
     * 获取开始位置
     * 
     * @return 开始位置
     */
    public int getStartIndex() {

        String start = this.request.getParameter("start");

        try {
            return Integer.parseInt(start);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * callback参数验证
     * 
     * @param name name
     * @return 验证结果
     */
    public boolean validCallbackName(String name) {

        if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
            return true;
        }

        return false;

    }

}
