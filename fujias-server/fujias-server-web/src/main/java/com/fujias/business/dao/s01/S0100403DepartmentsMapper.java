package com.fujias.business.dao.s01;

import java.util.List;

import com.fujias.business.forms.s01.S0100403DepartmentsForm;
import com.fujias.common.db.entity.MDepartment;
import com.fujias.common.entity.SelectItem;

public interface S0100403DepartmentsMapper {

    List<S0100403DepartmentsForm> selectAll();

    S0100403DepartmentsForm selectById(String id);

    List<S0100403DepartmentsForm> selectByParentId(String parentid);

    List<SelectItem> getDepartmentOptions();

    List<SelectItem> getUserDepartmentOptions();

    Integer checkExist(S0100403DepartmentsForm dep);

    Integer checkChildrenExist(MDepartment dep);
}
