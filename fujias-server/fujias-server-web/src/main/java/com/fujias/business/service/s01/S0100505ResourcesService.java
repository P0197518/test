package com.fujias.business.service.s01;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.s01.S0100505ResourcesMapper;
import com.fujias.business.forms.s01.S0100505ResourcesForm;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.db.dao.MResourceMapper;
import com.fujias.common.db.entity.MResource;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.BeanUtilsExtends;
import com.fujias.common.util.DateUtil;

/**
 * りソース管理Service类
 * 
 * @author wul
 *
 */
@Service
public class S0100505ResourcesService {

    @Autowired
    private MResourceMapper mapper;

    @Autowired
    private S0100505ResourcesMapper resMapper;

    /**
     * 删除
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(S0100505ResourcesForm formData) throws BusinessException {
        List<S0100505ResourcesForm> childList = resMapper.getAllByParentId(formData.getId());
        for (S0100505ResourcesForm child : childList) {
            this.delete(child);
        }

        MResource updateEntity = new MResource();
        updateEntity.setId(formData.getId());
        updateEntity.setDelFlg("1");
        // mapper.deleteByPrimaryKey(formData.getId())
        // mapper.updateByPrimaryKeySelective(updateEntity);
        if (mapper.updateByPrimaryKeySelective(updateEntity) == 0) {
            throw new BusinessException(MessageCodes.E00001);
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
    public boolean add(S0100505ResourcesForm formData) {
        MResource updateEntity = new MResource();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        formData.setId(uuid);
        formData.setDelFlg("0");
        formData.setCreateTime(DateUtil.getNowDate());
        formData.setCreateUser("admin");
        formData.setUpdateTime(DateUtil.getNowDate());
        formData.setUpdateUser("admin");
        formData.setResourceNameEN(formData.getResourceName());
        formData.setResourceNameNI(formData.getResourceName());
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
    public int update(S0100505ResourcesForm formData) {
        MResource updateEntity = new MResource();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        return mapper.updateByPrimaryKeySelective(updateEntity);
    }
}
