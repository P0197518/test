package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0602BatchErrorReport;

public interface T0602BatchErrorReportMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0602BatchErrorReport record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0602BatchErrorReport record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return T0602BatchErrorReport
     */
    T0602BatchErrorReport selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0602BatchErrorReport record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0602BatchErrorReport record);
}