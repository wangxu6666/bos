package com.itheima.bos.service;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.CourierRepositories;
import com.itheima.bos.dao.FixedAreaRepositories;
import com.itheima.bos.dao.TakeTimeRepositories;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.TakeTime;

/**  
 * ClassName:FixedAreaServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 8:08:46 PM <br/>       
 */
@Service
@Transactional
public class FixedAreaServiceImp implements FixedAreaService {

    @Autowired
    private FixedAreaRepositories fixedAreaDao;
    @Autowired
    private CourierRepositories courierDao;
    @Autowired
    private TakeTimeRepositories takeTimeDao;

    @Override
    public void save(FixedArea model) {
          
        // TODO Auto-generated method stub  
        fixedAreaDao.save(model);
    }

    @Override
    public Page<FixedArea> findByPage(Pageable pageable) {
          
        // TODO Auto-generated method stub  
        return fixedAreaDao.findAll(pageable);
    }

    @Override
    public void linkCourierToFixedArea(Long id, long courierId,
            long takeTimeId) {
        FixedArea fixedArea = fixedAreaDao.findOne(id);
        Courier courier = courierDao.findOne(courierId);
        TakeTime takeTime = takeTimeDao.findOne(takeTimeId);
        
        fixedArea.getCouriers().add(courier);
        courier.setTakeTime(takeTime);  
    }

 
    
   
    
    
}
  
