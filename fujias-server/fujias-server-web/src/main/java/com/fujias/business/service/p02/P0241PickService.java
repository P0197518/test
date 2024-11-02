package com.fujias.business.service.p02;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.forms.p02.P0241PickSettingForm;
import com.fujias.common.db.dao.T0203AdjustSettingsMapper;
import com.fujias.common.db.entity.T0203AdjustSettings;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.BeanUtilsExtends;
@Service
public class P0241PickService {
    
    @Autowired
    private T0203AdjustSettingsMapper t0203AdjustSettingsMapper;

    /**
     * 删除
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(P0241PickSettingForm formData) {

        T0203AdjustSettings updateEntity = new T0203AdjustSettings();
        updateEntity.setId(formData.getId());
        updateEntity.setDelflg(true);
        return t0203AdjustSettingsMapper.updateByPrimaryKeySelective(updateEntity);
    }
    
    /**
     * 更新
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(P0241PickSettingForm formData) {
        // 更新表
        T0203AdjustSettings updateEntity = new T0203AdjustSettings();
        for (T0203AdjustSettings adjustSettings : formData.getSettingList()) {
            BeanUtilsExtends.copyProperties(updateEntity, adjustSettings);
            updateEntity.setDelflg(false);
            updateEntity.setSettingType(formData.getSettingType());
            if (adjustSettings.getId().isEmpty()) {
                t0203AdjustSettingsMapper.insert(updateEntity);
            } else {
                t0203AdjustSettingsMapper.updateByPrimaryKeySelective(updateEntity);
            }
        }
        return true;
    }

}
