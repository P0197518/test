package com.fujias.business.service.s01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.s01.S0101101CodeListMapper;
import com.fujias.business.forms.s01.S0101101CodeListForm;
import com.fujias.common.db.dao.MKbnMapper;
import com.fujias.common.db.entity.MKbn;
import com.fujias.common.util.BeanUtilsExtends;
import com.fujias.common.util.StringUtil;

/**
 * 区分管理的Service类
 * 
 * @author 薛秋凤
 *
 */
@Service
public class S0101101CodeListService {

    @Autowired
    private MKbnMapper kbnMapper;

    @Autowired
    private S0101101CodeListMapper s0101101CodeListMapper;

    /**
     * 删除区分
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(S0101101CodeListForm formData) {
        MKbn updateEntity = new MKbn();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        updateEntity.setDelFlg("1");
        return kbnMapper.updateByPrimaryKeySelective(updateEntity);
    }

    /**
     * 还原区分
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int reuse(S0101101CodeListForm formData) {
        MKbn updateEntity = new MKbn();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        updateEntity.setDelFlg("0");
        return kbnMapper.updateByPrimaryKeySelective(updateEntity);
    }

    /**
     * 添加区分
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(S0101101CodeListForm formData) {

        String code = "0001";
        S0101101CodeListForm checkData = s0101101CodeListMapper.getMaxCodeCd(formData);
        if (checkData != null) {
            code = checkData.getCodeCd();
        }
        code = String.valueOf(Integer.parseInt(StringUtil.leftTrim(code, '0').get()) + 1);
        code = StringUtil.padLeft(code, 4, '0');

        // 添加roles表
        MKbn updateEntity = new MKbn();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        updateEntity.setCanEdit("1");
        updateEntity.setCodeCd(code);
        updateEntity.setDelFlg("0");
        return kbnMapper.insertSelective(updateEntity) > 0;
    }

    /**
     * 更新区分
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(S0101101CodeListForm formData) {
        // 更新roles表
        MKbn updateEntity = new MKbn();
        BeanUtilsExtends.copyProperties(updateEntity, formData);
        updateEntity.setCanEdit("1");
        return kbnMapper.updateByPrimaryKeySelective(updateEntity);
    }

}
