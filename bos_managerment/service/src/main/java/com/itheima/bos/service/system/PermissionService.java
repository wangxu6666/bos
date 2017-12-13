package com.itheima.bos.service.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;

/**  
 * ClassName:PermissionService <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 7:58:44 PM <br/>       
 */
public interface PermissionService {

    Page<Permission> findByPage(Pageable pageable);

    void save(Permission model);

}
  
