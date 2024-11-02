package com.fujias.common.entity;

import java.util.List;
import java.util.Map;

/**
 * リスト結果の戻り値定義クラスです。
 * 
 * 
 * @version 1.0
 * @author 陳強
 */
public class ResultMapInfo extends ResultInfo {

    private int total;
    private List<Map<String, Object>> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

}
