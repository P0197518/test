package com.fujias.common.entity;

/**
 * シングルBeanの戻り値定義クラスです。
 * 
 * @version 1.0
 * @author 陳強
 * @param <T> T
 */
public class ResultSingleInfo<T> extends ResultInfo {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
