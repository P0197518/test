package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MDepartment;

public interface MDepartmentMapper {
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
    int insert(MDepartment record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MDepartment record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return MDepartment
     */
    MDepartment selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MDepartment record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MDepartment record);
}