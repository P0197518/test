package com.fujias.business.dao.p07;

import java.util.List;

import com.fujias.business.forms.p07.P0709ItemListForm;
import com.fujias.common.entity.SelectItem;

public interface P0709ItemListMapper {
    List<SelectItem> getDepartment();
    
    List<P0709ItemListForm> getListByPage(P0709ItemListForm formData);
    
    List<SelectItem> getClassification();
    
    List<Object> getReportData(Object condation, int page);

}
