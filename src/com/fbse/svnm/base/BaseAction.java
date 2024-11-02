package com.fbse.svnm.base;

import org.apache.struts.action.Action;
import com.fbse.common.FBSELogHandler;

public class BaseAction extends Action {

    /**
     * ログオブジェクト
     */
    protected FBSELogHandler log;

    /**
     * ログオブジェクトの取得
     */
    public BaseAction() {
        log = Common.getLog();
    }
}
