package com.itheima.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.system.Permission;

/**  
 * ClassName:PermissionRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 7:59:45 PM <br/>       
 */
public interface PermissionRepositories extends JpaRepository<Permission, Long> {

}
  
