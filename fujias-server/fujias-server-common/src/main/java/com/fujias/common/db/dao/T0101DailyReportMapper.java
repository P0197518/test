package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0101DailyReport;

public interface T0101DailyReportMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0101DailyReport record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0101DailyReport record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return T0101DailyReport
     */
    T0101DailyReport selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0101DailyReport record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0101DailyReport record);
}