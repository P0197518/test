package com.fujias.business.dao.p07;

import java.util.List;

import com.fujias.business.forms.p07.P0713PermissionListForm;
import com.fujias.business.forms.p07.P0713PermissionListItem;
import com.fujias.common.db.entity.MRoleResource;
import com.fujias.common.db.entity.MRoles;
import com.fujias.common.entity.SelectItem;

public interface P0713PermissionListMapper {

    List<MRoles> getRoles(P0713PermissionListForm condation);

    List<P0713PermissionListItem> getRoleResources(P0713PermissionListForm condation);

    int deleteByRoleId(String roleid);

    int updateDelflgByRoleId(MRoleResource roleResource);

    Integer checkExist(MRoles roles);

    List<SelectItem> getRoleOptions();
}
