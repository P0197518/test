package com.fujias.business.service.s01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.s01.S0100403DepartmentsMapper;
import com.fujias.business.forms.s01.S0100403DepartmentsForm;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.db.dao.MDepartmentMapper;
import com.fujias.common.db.entity.MDepartment;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.BeanUtilsExtends;

/**
 * 部門管理Service类
 * 
 * @author wul
 *
 */
@Service
public class S0100403DepartmentsService {

    @Autowired
    private MDepartmentMapper mapper;

    @Autowired
    private S0100403DepartmentsMapper depMapper;

    /**
     * 删除
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(S0100403DepartmentsForm formData) throws BusinessException {
        List<S0100403DepartmentsForm> childList = depMapper.selectByParentId(formData.getDepId());
        for (S0100403DepartmentsForm data : childList) {
            this.delete(data);
        }

        MDepartment updateEntity = new MDepartment();
        updateEntity.setId(formData.getId());
        updateEntity.setDelFlg("1");
        if (mapper.updateByPrimaryKeySelective(updateEntity) == 0) {
            throw new BusinessException(MessageCodes.EC0010);
        }
        return true;
    }

    /**
     * 添加
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(S0100403DepartmentsForm formData) {
        // 更新roles表
        MDepartment updateEntity = new MDepartment();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        return mapper.insertSelective(updateEntity) > 0;
    }

    /**
     * 更新
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(S0100403DepartmentsForm formData) {
        // 更新roles表
        MDepartment updateEntity = new MDepartment();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        return mapper.updateByPrimaryKeySelective(updateEntity);
    }
}
