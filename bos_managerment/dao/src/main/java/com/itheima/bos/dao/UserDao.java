package com.itheima.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserDao <br/>  
 * Function:  <br/>  
 * Date:     Dec 12, 2017 3:49:18 PM <br/>       
 */
public interface UserDao extends JpaRepository<User, Long> {

    
    User findByUsername(String username);

}
  
