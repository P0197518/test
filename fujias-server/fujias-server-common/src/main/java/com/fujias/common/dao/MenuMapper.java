package com.fujias.common.dao;

import java.util.List;

import com.fujias.common.entity.MenuEntity;

public interface MenuMapper {

    List<MenuEntity> getMenuList(String userId);
}
