package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.PermissionRepositories;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;

/**  
 * ClassName:PermissionServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 7:58:58 PM <br/>       
 */
@Service
@Transactional
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionRepositories pDao;
    
    @Override
    public Page<Permission> findByPage(Pageable pageable) {
          
        // TODO Auto-generated method stub  
        return pDao.findAll(pageable);
    }

    @Override
    public void save(Permission model) {
         pDao.save(model); 
    }

    @Override
    public List<Permission> findAll() {
          
        // TODO Auto-generated method stub  
        return pDao.findAll();
    }

}
  
