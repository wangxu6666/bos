package com.itheima.bos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.StanderRepositories;
import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Nov 27, 2017 9:55:57 PM <br/>       
 */
@Service
@Transactional
public class StandardServiceImp implements StandardService {
     
    @Autowired
    private StanderRepositories standerDao;
    @Override
    public void add(Standard standard) {
          standerDao.save(standard);
        // TODO Auto-generated method stub  
        
    }
    @Override
    public Page<Standard> findByPage(Pageable pageable) {
         
        // TODO Auto-generated method stub  
        return standerDao.findAll(pageable);
    }
    @Override
    public List<Standard> findAll() {
          
       
        return standerDao.findAll();
    }

}
  
