package com.fujias.business.dao.p07;

import java.util.List;

import com.fujias.business.forms.p07.P0711UserListForm;
import com.fujias.common.db.entity.MUserRoles;
import com.fujias.common.entity.SelectItem;

public interface P0711UserListMapper {

    List<P0711UserListForm> getListByPage(P0711UserListForm condation);

    P0711UserListForm getSingleById(String id);

    Integer deleteDepsByUserId(String id);

    // 获取社员下拉列表
    List<SelectItem> getEmployeeOptions();

    int checkExist(P0711UserListForm roles);

    int checkUserRoleExist(MUserRoles roles);

    int updateUserRoles(MUserRoles roles);

}
