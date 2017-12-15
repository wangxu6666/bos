package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.UserRepositories;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 7:48:04 PM <br/>       
 */
@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepositories uDao;

    @Override
    public void save(User model, Long[] roleIds) {
        String username = model.getUsername();
        User user = uDao.findByUsername(username);
        if (user!=null) {
            return;
         }
      if (roleIds!=null) {
        for (Long roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            model.getRoles().add(role);
        }
        uDao.save(model);
    }
        
    }

    @Override
    public Page<User> findByPage(Pageable pageable) {
        return uDao.findAll(pageable);
    }
    
    
    
}
  
