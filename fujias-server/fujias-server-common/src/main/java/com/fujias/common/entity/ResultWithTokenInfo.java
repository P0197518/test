package com.fujias.common.entity;

/**
 * 請求状態基本情報の戻り値定義クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public class ResultWithTokenInfo extends ResultInfo {

    private String token;

    /**
     * tokenを取得する。
     * 
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * tokenを設定する。
     * 
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
