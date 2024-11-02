package com.fujias.business.dao.s01;

import java.util.List;

import com.fujias.business.forms.s01.S0101101CodeListForm;

public interface S0101101CodeListMapper {

    List<S0101101CodeListForm> getListByPage(S0101101CodeListForm condation);

    S0101101CodeListForm getSingleById(S0101101CodeListForm formData);

    // 获取区分code下拉列表
    List<S0101101CodeListForm> getKbntypecd();

    Integer checkExist(S0101101CodeListForm roles);

    // 获取区分code下拉列表
    List<S0101101CodeListForm> getKbnByCodeTypeCd(S0101101CodeListForm formData);

    S0101101CodeListForm getMaxCodeCd(S0101101CodeListForm formData);

    List<S0101101CodeListForm> getProcessList();
}
