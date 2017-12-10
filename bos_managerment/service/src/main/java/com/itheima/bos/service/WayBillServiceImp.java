package com.itheima.bos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.WayBillRepopsitories;
import com.itheima.bos.domain.take_delivery.WayBill;

/**  
 * ClassName:WayBillServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 10, 2017 5:16:15 PM <br/>       
 */
@Service
@Transactional
public class WayBillServiceImp implements WayBillService {

    @Autowired
    private WayBillRepopsitories wDao;
    @Override
    public void save(WayBill model) {
         wDao.save(model); 
        // TODO Auto-generated method stub  
        
    }

}
  
