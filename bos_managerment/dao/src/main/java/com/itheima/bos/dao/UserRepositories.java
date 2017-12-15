package com.itheima.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 7:49:49 PM <br/>       
 */
public interface UserRepositories extends JpaRepository<User, Long> {
    
    User findByUsername(String username);

}
  
