package com.fujias.business.dao;

import java.util.List;

import com.fujias.common.db.entity.MResource;
import com.fujias.common.db.entity.MRoleResource;

public interface TestMapper {

    List<MResource> selectAllResource();

    int deleteOldRoleResource(MRoleResource roleResource);
}
