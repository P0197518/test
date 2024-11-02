package com.fujias.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.TestMapper;
import com.fujias.common.db.dao.MRoleResourceMapper;
import com.fujias.common.db.entity.MResource;
import com.fujias.common.db.entity.MRoleResource;

/**
 * TestService
 * 
 * @author chenqiang
 *
 */
@Service
public class TestService {

    @Autowired
    TestMapper testMapper;
    @Autowired
    MRoleResourceMapper t0021mapper;

    /**
     * 添加所有权限给admin用户
     * 
     * @return 处理结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addAllResourceToAdmin() {

        List<MResource> resources = testMapper.selectAllResource();
        MRoleResource roleResource = new MRoleResource();
        roleResource.setRoleId("admin");
        testMapper.deleteOldRoleResource(roleResource);

        for (MResource item : resources) {
            MRoleResource updateEntity = new MRoleResource();
            updateEntity.setResourceId(item.getId());
            updateEntity.setRoleId("admin");
            t0021mapper.insertSelective(updateEntity);
        }
        return true;
    }
}
