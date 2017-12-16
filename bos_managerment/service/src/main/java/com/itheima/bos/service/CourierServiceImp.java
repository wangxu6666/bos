package com.itheima.bos.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.CourierRepositories;
import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Nov 30, 2017 3:20:54 PM <br/>       
 */
@Transactional
@Service
public class CourierServiceImp implements CourierService{
    @Autowired
    private CourierRepositories courierDao;
    @RequiresPermissions("courier:add")
    @Override
    public void save(Courier model) {
          courierDao.save(model);
        // TODO Auto-generated method stub  
        
    }
    @RequiresPermissions("courier:list")
    @Override
    public Page<Courier> findByPage(Pageable page) {
          
        // TODO Auto-generated method stub  
        return (Page<Courier>) courierDao.findAll(page);
    }
    
    @Override
    public void delete(String ids) {
          
       if (!StringUtils.isEmpty(ids)) {
        String[] split = ids.split(",");
        for (String id : split) {
            courierDao.updateState(Long.parseLong(id));
        }
      }
        
    }
    @RequiresPermissions("courier:list")
    @Override
    public Page<Courier> pageQuery(Specification<Courier> specification,
            Pageable pageable) {
          
        // TODO Auto-generated method stub  
        return courierDao.findAll(specification, pageable);
    }
    @Override
    public List<Courier> listajax() {
        return courierDao.findCourierNotDel();
    }
 

}
  
