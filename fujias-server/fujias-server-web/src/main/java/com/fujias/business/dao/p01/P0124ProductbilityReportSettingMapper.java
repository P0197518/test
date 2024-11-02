package com.fujias.business.dao.p01;

import java.util.List;

import com.fujias.business.forms.p01.P0124ProductbilityReportSettingControllerForm;
import com.fujias.common.entity.SelectItem;

public interface P0124ProductbilityReportSettingMapper {
    
    List<P0124ProductbilityReportSettingControllerForm> getListByPage(P0124ProductbilityReportSettingControllerForm form);
    
    List<SelectItem> getSettingType();
    
    List<SelectItem> getSettingGrid();
    
    List<P0124ProductbilityReportSettingControllerForm> getSettingList(P0124ProductbilityReportSettingControllerForm form);
    
    List<P0124ProductbilityReportSettingControllerForm> getSettingExist(P0124ProductbilityReportSettingControllerForm form);

}
