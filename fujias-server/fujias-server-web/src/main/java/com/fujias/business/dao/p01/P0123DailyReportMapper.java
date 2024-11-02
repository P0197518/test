package com.fujias.business.dao.p01;

import java.util.List;

import com.fujias.business.forms.p01.P0122ProductbilityReportForm;
import com.fujias.business.forms.p01.P0123CompareDiffForm;
import com.fujias.business.forms.p01.P0123DailyReportForm;
import com.fujias.common.entity.SelectItem;

public interface P0123DailyReportMapper {

    List<P0123DailyReportForm> getListByPage(P0123DailyReportForm condation);

    List<Object> getReportData(Object condation, int page);

    List<SelectItem> getTechnicsCD();
    
    List<SelectItem> getDepartList();
    
    List<P0123DailyReportForm> dpsComparision(String itemName);
    
    List<P0122ProductbilityReportForm> getDailyCount(P0123CompareDiffForm formData);
    
    P0123DailyReportForm badExist(String itemName);

}
