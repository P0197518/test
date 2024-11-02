package com.fujias.common.entity;

import java.util.List;

/**
 * 改ページ用設定Beanクラスです。
 * 
 * @author 陳強
 *
 */
public class Pager {

    /**
     * 最大のページサイズ
     */
    public static final Integer MAX_PAGE_SIZE = 500;

    /**
     * Order Byの種類
     * 
     * @author 陳強
     *
     */
    public enum Order {
        asc, desc
    }

    public static final String FIELD_NAME = "pager";
    private boolean autoGetTotalCount = true;
    /**
     * 現在のページ数
     */
    private int pageIndex = 1;
    /**
     * 毎ページ表示件数
     */
    private int pageSize = 20;
    /**
     * 検索キー
     */
    private String searchBy;
    /**
     * キーワード
     */
    private String keyword;
    /**
     * Order項目
     */
    private String sortField;
    /**
     * Order Byの種類
     */
    private Order sortOrder;
    /**
     * 総件数
     */
    private int totalCount;
    /**
     * 取得結果
     */
    private List<?> result;

    /**
     * コンストラクタです。
     */
    public Pager() {

    }

    /**
     * PageCountを取得する。
     * 
     * @return PageCount
     */
    public int getPageCount() {
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            pageCount++;
        }
        return pageCount;
    }

    /**
     * PageSizeを取得する
     * 
     * @return PageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * PageSizeを設定する。
     * 
     * @param pageSize PageSize
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * FirstRowIndexを取得する
     * 
     * @return FirstRowIndex
     */
    public int getFirstRowIndex() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }

    /**
     * SearchByを取得する
     * 
     * @return SearchBy
     */
    public String getSearchBy() {
        return searchBy;
    }

    /**
     * SearchByを設定する
     * 
     * @param searchBy searchBy
     */
    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    /**
     * Keywordを取得する
     * 
     * @return Keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * keywordを設定する
     * 
     * @param keyword keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * TotalCountを取得する
     * 
     * @return TotalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * totalCountを設定する
     * 
     * @param totalCount totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Resultを取得する
     * 
     * @return Result
     */
    public List<?> getResult() {
        return result;
    }

    /**
     * resultを設定する
     * 
     * @param result result
     */
    public void setResult(List<?> result) {
        this.result = result;
    }

    /**
     * AutoGetTotalCountを取得する
     * 
     * @return AutoGetTotalCount
     */
    public boolean isAutoGetTotalCount() {
        return autoGetTotalCount;
    }

    /**
     * autoGetTotalCountを設定する
     * 
     * @param autoGetTotalCount autoGetTotalCount
     */
    public void setAutoGetTotalCount(boolean autoGetTotalCount) {
        this.autoGetTotalCount = autoGetTotalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Order getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Order sortOrder) {
        this.sortOrder = sortOrder;
    }

}
