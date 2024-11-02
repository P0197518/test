package com.fujias.common.base;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.CheckMessage;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.entity.ResultHeaderListInfo;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.ResultListInfo;
import com.fujias.common.entity.ResultMapInfo;
import com.fujias.common.entity.ResultSingleInfo;
import com.fujias.common.util.CheckErrorUtil;
import com.fujias.common.util.JsonUtil;

/**
 * コントロールクラスの親クラス
 * 
 * @version 1.0
 * @author 陳強
 */
public class BaseController {

    private static final String FORM_DATA_KEY = "formdata";
    private static final Logger LORRER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     * 日付と時刻のデフォルトフォーマットを設定する
     * 
     * @param binder binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * 新規、更新、削除する場合、操作状態を戻る
     * 
     * @param stateTypes stateTypes
     * @return ResultInfo
     */
    protected ResultInfo backUpdateState(StateTypes stateTypes) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setStatus(stateTypes);
        return resultInfo;
    }

    /**
     * 新規、更新、削除する場合、操作状態を戻る
     * 
     * @param state state
     * @param code code
     * @return ResultInfo
     */
    protected ResultInfo backUpdateState(StateTypes state, String code) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setStatus(state);
        resultInfo.setText(code);
        return resultInfo;
    }

    /**
     * 新規、更新、削除する場合、操作状態を戻る
     * 
     * @param state state
     * @param code code
     * @param args args
     * @return ResultInfo
     */
    protected ResultInfo backUpdateState(StateTypes state, String code, String[] args) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setStatus(state);
        resultInfo.setText(code);
        resultInfo.setArgs(args);
        return resultInfo;
    }

    protected <T> ResultListInfo<T> backUpdateState(StateTypes state, String code, List<T> listData) {
        ResultListInfo<T> resultInfo = new ResultListInfo<T>();
        resultInfo.setStatus(state);
        resultInfo.setText(code);
        resultInfo.setData(listData);
        return resultInfo;
    }

    /**
     * List型のデータをJson文字列に変更して、返却する
     * 
     * @param listData listData
     * @return Json文字列
     */
    protected String backJsonString(List<?> listData) {
        return JsonUtil.toJson(listData);
    }

    /**
     * 単一対象のデータをJson文字列に変更して、返却する
     * 
     * @param singleData singleData
     * @return Json文字列
     */
    protected String backJsonString(Object singleData) {
        return JsonUtil.toJson(singleData);
    }

    /**
     * hashmap型のデータをJson文字列に変更して、返却する
     * 
     * @param hashmap hashmap
     * @return Json文字列
     */
    protected String backJsonString(HashMap<String, Object> hashmap) {
        return JsonUtil.toJson(hashmap);
    }

    /**
     * チェックエラーが存在する場合、エラー状態とエラーメッセーを返却する
     * 
     * @param checkMessages checkMessages
     * @return ResultInfo
     */
    protected ResultInfo backCheckError(List<CheckMessage> checkMessages) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setStatus(StateTypes.CHECKERROR);
        resultInfo.setCheckMessages(checkMessages);
        return resultInfo;
    }

    /**
     * List型のデータを返却する
     * 
     * @param <T> T
     * @param listData listData
     * @return ResultListInfo
     */
    protected <T> ResultListInfo<T> backListData(List<T> listData) {
        ResultListInfo<T> resultInfo = new ResultListInfo<T>();
        resultInfo.setStatus(StateTypes.SUCCESS);
        resultInfo.setData(listData);
        return resultInfo;
    }

    /**
     * List型のデータと総件数を返却する(改ページが存在する場合使う)
     * 
     * @param <T> T
     * @param listData listData
     * @param totalCount totalCount
     * @return ResultListInfo
     */
    protected <T> ResultListInfo<T> backListData(List<T> listData, int totalCount) {
        ResultListInfo<T> resultInfo = new ResultListInfo<T>();
        resultInfo.setStatus(StateTypes.SUCCESS);
        resultInfo.setData(listData);
        resultInfo.setTotal(totalCount);
        return resultInfo;
    }

    /**
     * map型のデータを返却する
     * 
     * @param mapData mapData
     * @return ResultMapInfo
     */
    protected ResultMapInfo backHashMap(List<Map<String, Object>> mapData) {
        ResultMapInfo resultInfo = new ResultMapInfo();
        resultInfo.setStatus(StateTypes.SUCCESS);
        resultInfo.setData(mapData);
        return resultInfo;
    }

    /**
     * 単一対象のデータを返却する
     * 
     * @param <T> T
     * @param singleData singleData
     * @return ResultSingleInfo
     */
    protected <T> ResultSingleInfo<T> backSingleData(T data) {
        ResultSingleInfo<T> resultInfo = new ResultSingleInfo<T>();
        resultInfo.setStatus(StateTypes.SUCCESS);
        resultInfo.setData(data);
        return resultInfo;
    }

    /**
     * ヘッダとリストデータ両方とも返却する
     * 
     * @param <T> T
     * @param headerData headerData
     * @param listData listData
     * @return ResultHeaderListInfo
     */
    protected <T> ResultHeaderListInfo<T> backHeaderAndListData(T headerData, List<?> listData) {
        ResultHeaderListInfo<T> resultInfo = new ResultHeaderListInfo<T>();
        resultInfo.setStatus(StateTypes.SUCCESS);
        resultInfo.setHeaderData(headerData);
        resultInfo.setData(listData);
        return resultInfo;
    }

    /**
     * ヘッダとリストデータ両方とも返却する
     * 
     * @param <T> T
     * @param headerData headerData
     * @param listData listData
     * @param totalCount totalCount
     * @return ResultHeaderListInfo
     */
    protected <T> ResultHeaderListInfo<T> backHeaderAndListData(T headerData, List<?> listData, int totalCount) {
        ResultHeaderListInfo<T> resultInfo = new ResultHeaderListInfo<T>();
        resultInfo.setStatus(StateTypes.SUCCESS);
        resultInfo.setHeaderData(headerData);
        resultInfo.setData(listData);
        resultInfo.setTotalCount(totalCount);
        return resultInfo;
    }

    /**
     * ViewNameとデータを設定して、Viewを開く
     * 
     * @param viewname viewname
     * @param formData formData
     * @return ModelAndView
     */
    protected ModelAndView backView(String viewname, Object formData) {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName(viewname);
        modelView.addObject(FORM_DATA_KEY, formData);
        return modelView;
    }

    /**
     * 入力データを検証して、エラー状態を返却。
     * 
     * @param forms forms
     * @return エラー状態
     */
    protected List<CheckMessage> validateForm(Object... forms) {
        List<CheckMessage> checkMessages = CheckErrorUtil.validateForm(forms);
        if (checkMessages == null) {
            return new ArrayList<CheckMessage>();
        } else {
            return checkMessages;
        }
    }

    /**
     * 登録されたユーザーの情報を取得する
     * 
     * @return T01User
     */
    protected LoginUser getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object objLoginUser = auth.getPrincipal();
        if (objLoginUser == null) {
            return null;
        } else {
            return (LoginUser) objLoginUser;
        }
    }

    /**
     * パラメータのキーによって、JSON対象からパラメータ値を取得する
     * 
     * @param <T> T
     * @param paramname paramname
     * @param classtype classtype
     * @return パラメータ値
     */
    protected <T> T getUrlParam(String paramname, Class<T> classtype) {
        String param = this.getRequest().getParameter(paramname);
        if (param == null) {
            return null;
        }

        String paramDecode = "";
        try {
            paramDecode = java.net.URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        T entity = JsonUtil.toObject(paramDecode, classtype);
        return entity;
    }

    /**
     * request対象を取得する。
     * 
     * @return the request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * response対象を取得する。
     * 
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * lorrerを取得する。
     * 
     * @return the lorrer
     */
    public Logger getLorrer() {
        return LORRER;
    }
}
