package com.fujias.business.dao.s01;

import java.util.List;

import com.fujias.business.forms.s01.S0100505ResourcesForm;
import com.fujias.common.db.entity.MResource;

public interface S0100505ResourcesMapper {

    List<S0100505ResourcesForm> selectAll();

    List<S0100505ResourcesForm> getActionByParentId(String parentid);

    List<S0100505ResourcesForm> getAllByParentId(String parentid);

    S0100505ResourcesForm selectById(String id);

    Integer checkExist(MResource res);

    Integer checkChildrenExist(MResource res);
}
