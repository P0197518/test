package com.fujias.common.entity;

import java.util.List;

/**
 * リスト結果の戻り値定義クラスです。
 * 
 * 
 * @version 1.0
 * @author 陳強
 * @param <T> T
 */
public class ResultListInfo<T> extends ResultInfo {

    private int total;
    private List<?> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

}
