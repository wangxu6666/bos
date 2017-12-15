package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserService <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 7:47:51 PM <br/>       
 */
public interface UserService {

    void save(User model, Long[] roleIds);

    Page<User> findByPage(Pageable pageable);

}
  
