package com.fujias.common.exception;

import java.util.List;

/**
 * 業務異常の定義クラスです。
 * 
 * @author 陳強
 *
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private List<?> data;

    /**
     * メッセージ内容により、業務異常を初期化する。
     */
    public BusinessException() {
    }

    /**
     * メッセージ内容により、業務異常を初期化する。
     * 
     * @param message メッセージ
     */
    public BusinessException(String message) {
        this.message = message;
    }

    /**
     * エラーコードとメッセージ内容により、業務異常を初期化する。
     * 
     * @param code エラーコード
     * @param message メッセージ
     */
    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * エラーコードとメッセージ内容により、業務異常を初期化する。
     * 
     * @param code エラーコード
     * @param message メッセージ
     * @param data データ
     */
    public BusinessException(String code, String message, List<?> data) {
        this.code = code;
        this.message = message;
        this.setData(data);
    }

    /**
     * エラーコードを取得する。
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * エラーコードを設定する。
     * 
     * @param code エラーコード
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * エラーメッセージを設定する。
     * 
     * @param message エラーメッセージ
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

}
