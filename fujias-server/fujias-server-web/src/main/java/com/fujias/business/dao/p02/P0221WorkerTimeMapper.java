package com.fujias.business.dao.p02;

import java.util.List;


import com.fujias.business.forms.p02.P0221WorkTimeForm;
import com.fujias.business.forms.p02.P0221WorkTimeImportForm;



public interface P0221WorkerTimeMapper {

    List<P0221WorkTimeForm> getListByPage(P0221WorkTimeForm formData);
    
    List<P0221WorkTimeForm> departExist(String departName);

    Integer workTimeExist(P0221WorkTimeImportForm formData);

    Integer workExist(P0221WorkTimeImportForm formData);

}
