package com.itheima.bos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.AreaRepositories;
import com.itheima.bos.domain.base.Area;

/**  
 * ClassName:AreaServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Nov 30, 2017 8:56:30 PM <br/>       
 */
@Transactional
@Service
public class AreaServiceImp implements AreaService {
    @Autowired
    private AreaRepositories dao;

    @Override
    public void save(List<Area> list) {
          
        // TODO Auto-generated method stub  
        dao.save(list);
    }

    @Override
    public Page<Area> findByPage(Pageable pageable) {
          
        // TODO Auto-generated method stub  
        return dao.findAll(pageable);
    }

    @Override
    public List<Area> findAll() {
          
        // TODO Auto-generated method stub  
        return dao.findAll();
    }

    @Override
    public List<Area> finQ(String q) {
          
        // TODO Auto-generated method stub  
        q="%"+q+"%";
        return dao.findQ(q);
    }
    
}
  
