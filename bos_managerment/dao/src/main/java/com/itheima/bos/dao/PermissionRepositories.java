package com.itheima.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.Permission;

/**  
 * ClassName:PermissionRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 7:59:45 PM <br/>       
 */
public interface PermissionRepositories extends JpaRepository<Permission, Long> {

    @Query("select p from Permission p join p.roles r join r.users u where u.id=?" )
    List<Permission> findByUserId(long UserId);
    
}
  
