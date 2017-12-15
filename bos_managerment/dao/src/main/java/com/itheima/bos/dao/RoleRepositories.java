package com.itheima.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.Role;

/**  
 * ClassName:RoleRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 3:02:42 PM <br/>       
 */
public interface RoleRepositories extends JpaRepository<Role, Long> {
    
    @Query("select r from Role r join r.users u where u.id=userId")
    List<Role> findByUserId(Long userId);
}
  
