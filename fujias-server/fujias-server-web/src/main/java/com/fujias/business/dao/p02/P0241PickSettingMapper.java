package com.fujias.business.dao.p02;

import java.util.List;

import com.fujias.business.forms.p02.P0241PickSettingForm;
import com.fujias.common.entity.SelectItem;

public interface P0241PickSettingMapper {
    
    List<P0241PickSettingForm> getListByPage(P0241PickSettingForm condation);
    
    List<SelectItem> getSettingType();

    List<P0241PickSettingForm> getAllByParentId(String parentid);
    
    List<SelectItem> getItemName();
    
    String userDepart(String depId);
    
    List<P0241PickSettingForm> getSettingList(P0241PickSettingForm formData);

}
