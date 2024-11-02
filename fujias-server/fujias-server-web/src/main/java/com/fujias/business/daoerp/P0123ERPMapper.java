package com.fujias.business.daoerp;

import java.util.List;

import com.fujias.business.forms.p01.P0123CompareDiffForm;
import com.fujias.business.forms.p01.P0123DailyReportForm;
import com.fujias.common.entity.SelectItem;

public interface P0123ERPMapper {

    List<P0123DailyReportForm> getListByPage(P0123DailyReportForm condation);

    List<Object> getReportData(Object condation, int page);

    List<SelectItem> getTechnicsCD();

    List<P0123CompareDiffForm> getERPData(P0123CompareDiffForm param);
    
    List<P0123CompareDiffForm> getDpsData(P0123CompareDiffForm param);

}
