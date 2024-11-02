package com.fujias.business.service.p07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujias.business.dao.p07.P0709ItemListMapper;

import cn.afterturn.easypoi.handler.inter.IExcelExportServer;

@Service
public class P0709ItemListService implements IExcelExportServer{
    @Autowired
    private P0709ItemListMapper p0709ItemListMapper;

    @Override
    public List<Object> selectListForExcelExport(Object queryParams, int page) {
        return p0709ItemListMapper.getReportData(queryParams, page);
    }

}
