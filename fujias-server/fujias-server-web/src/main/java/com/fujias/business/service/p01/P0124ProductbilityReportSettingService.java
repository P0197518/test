package com.fujias.business.service.p01;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.forms.p01.P0124ProductbilityReportSettingControllerForm;
import com.fujias.common.db.dao.T0204SystemSettingsMapper;
import com.fujias.common.db.entity.T0204SystemSettings;
import com.fujias.common.exception.BusinessException;

@Service
public class P0124ProductbilityReportSettingService {
    
    @Autowired
    private T0204SystemSettingsMapper t0204SystemSettingsMapper;
    
    /**
     * 删除
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(P0124ProductbilityReportSettingControllerForm formData) {

        T0204SystemSettings updateEntity = new T0204SystemSettings();
        updateEntity.setDelflg(true);
        updateEntity.setId(formData.getId());
        return t0204SystemSettingsMapper.updateByPrimaryKeySelective(updateEntity);
    }
    
    /**
     * 更新
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(P0124ProductbilityReportSettingControllerForm formData) {
        if (formData.getSettingList().size() > 0) {
            for (T0204SystemSettings setting : formData.getSettingList()) {
                if (setting.getSettingType().equals("冲压单独计算工序设置")) {
                    setting.setSettingType("0010");
                } else if (setting.getSettingType().equals("研磨工序分类设置")) {
                    setting.setSettingType("0011");
                }
                setting.setDepartmentId(formData.getDepartmentId());
                setting.setDelflg(false);
                if (setting.getId().isEmpty()) {
                    t0204SystemSettingsMapper.insert(setting);
                } else {
                    t0204SystemSettingsMapper.updateByPrimaryKeySelective(setting);
                }
            }
        }
        return true;
    }

}
