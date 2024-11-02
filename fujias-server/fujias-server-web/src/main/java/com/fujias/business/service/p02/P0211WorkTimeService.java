package com.fujias.business.service.p02;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.p02.P0221WorkerTimeMapper;
import com.fujias.business.forms.p02.P0221WorkTimeForm;
import com.fujias.business.forms.p02.P0221WorkTimeImportForm;

import com.fujias.common.db.dao.T0201WorktimeMapper;
import com.fujias.common.db.dao.T0202WorkersMapper;

import com.fujias.common.db.entity.T0201Worktime;
import com.fujias.common.db.entity.T0202Workers;

@Service
public class P0211WorkTimeService {
    
    @Autowired
    private T0201WorktimeMapper workerTimeMapper;
    
    @Autowired
    private T0202WorkersMapper workersMapper;
    
    @Autowired
    private P0221WorkerTimeMapper p0221WorkerTimeMapper;


    
    /**
     * 更新
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(List<P0221WorkTimeImportForm> formData,String importYearAndMonth) {
        // 更新表
        T0201Worktime addWorkerTime = new T0201Worktime();
        T0202Workers addWorkers = new T0202Workers();
        for (P0221WorkTimeImportForm importForm : formData) {
            //插入T0201worktime
            BigDecimal bd=new BigDecimal(importForm.getWorkHours().trim());
            addWorkerTime.setWorkHours(bd);
            String departName = importForm.getDepartmentName().trim();
            List<P0221WorkTimeForm> departCode = p0221WorkerTimeMapper.departExist(departName);
            addWorkerTime.setYearMonth(importYearAndMonth);
            addWorkerTime.setDepartmentId(departCode.get(0).getDepartmentId());
            addWorkerTime.setWorkType(importForm.getWorkType().trim());
            addWorkerTime.setWorkerName(importForm.getWorkerName().trim());
            addWorkerTime.setDelflg(false);
            addWorkerTime.setWorkerDay(BigDecimal.ZERO);
            addWorkerTime.setWeekendOverHours(BigDecimal.ZERO);
            addWorkerTime.setWeekdayOverHours(BigDecimal.ZERO);
            addWorkerTime.setLeaveHours(BigDecimal.ZERO);
            addWorkerTime.setOverHours(BigDecimal.ZERO);
            addWorkerTime.setPersonalLeaveHours(BigDecimal.ZERO);
            addWorkerTime.setSickLeaveHours("0");
            //查看当前数据在T0201worktime是否存在
            P0221WorkTimeImportForm impForm = new P0221WorkTimeImportForm();
            impForm.setYearMonth(importYearAndMonth);
            impForm.setDepartmentId(departCode.get(0).getDepartmentId());
            impForm.setWorkerName(importForm.getWorkerName().trim());
            if (p0221WorkerTimeMapper.workTimeExist(impForm) > 0 ) {
                workerTimeMapper.updateByPrimaryKeySelective(addWorkerTime);
            } else {
                workerTimeMapper.insertSelective(addWorkerTime);
            }
            
            //插入T0202workers
            addWorkers.setWorkerName(importForm.getWorkerName().trim());
            addWorkers.setWorkType(importForm.getWorkType().trim());
            addWorkers.setYearMonth(importYearAndMonth);
            addWorkers.setDepartmentId(departCode.get(0).getDepartmentId());
            addWorkers.setShareFlag(false);
            addWorkers.setDelflg(false);
            if (p0221WorkerTimeMapper.workExist(impForm) > 0) {
                workersMapper.updateByPrimaryKeySelective(addWorkers);
            } else {
                workersMapper.insertSelective(addWorkers);
            }
            
        }
        return true;
    }

}
