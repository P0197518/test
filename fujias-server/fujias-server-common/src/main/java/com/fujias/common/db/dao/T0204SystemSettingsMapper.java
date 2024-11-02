package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0204SystemSettings;

public interface T0204SystemSettingsMapper {
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
    int insert(T0204SystemSettings record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0204SystemSettings record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return T0204SystemSettings
     */
    T0204SystemSettings selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0204SystemSettings record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0204SystemSettings record);
}