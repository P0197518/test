package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0103ProductBilityWorkers;
import com.fujias.common.db.entity.T0103ProductBilityWorkersKey;

public interface T0103ProductBilityWorkersMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(T0103ProductBilityWorkersKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0103ProductBilityWorkers record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0103ProductBilityWorkers record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return T0103ProductBilityWorkers
     */
    T0103ProductBilityWorkers selectByPrimaryKey(T0103ProductBilityWorkersKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0103ProductBilityWorkers record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0103ProductBilityWorkers record);
}