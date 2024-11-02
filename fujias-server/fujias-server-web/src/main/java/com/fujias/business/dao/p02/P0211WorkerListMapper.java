package com.fujias.business.dao.p02;

import java.util.List;

import com.fujias.business.forms.p02.P0211WorkerListForm;



public interface P0211WorkerListMapper {

    List<P0211WorkerListForm> getListByPage(P0211WorkerListForm formData);
    
    List<P0211WorkerListForm> departList(String codeCd);
    
    String userDepart(String depId);
    
    List<P0211WorkerListForm> exportList(P0211WorkerListForm formData);



}
