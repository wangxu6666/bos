package com.itheima.bos.service;  
/**  
 * ClassName:TakeTimeServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 5, 2017 3:08:02 PM <br/>       
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.TakeTimeRepositories;
import com.itheima.bos.domain.base.TakeTime;
@Transactional
@Service
public class TakeTimeServiceImp implements TakeTimeService {
    @Autowired
    private TakeTimeRepositories dao;

    @Override
    public List<TakeTime> findAll() {
          
        // TODO Auto-generated method stub  
        return dao.findAll();
    }
    
    

}
  
