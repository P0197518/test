package com.fujias.common.entity;

import java.util.List;

/**
 * ヘッダとリスト情報の戻り値定義クラスです。
 * 
 * @version 1.0
 * @author 陳強
 * @param <T> T
 */
public class ResultHeaderListInfo<T> extends ResultInfo {

    private int totalCount;
    private List<?> data;

    private T headerData;

    /**
     * totalCountを取得する。
     * 
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * totalCountを設定する。
     * 
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public T getHeaderData() {
        return headerData;
    }

    public void setHeaderData(T headerData) {
        this.headerData = headerData;
    }

}
