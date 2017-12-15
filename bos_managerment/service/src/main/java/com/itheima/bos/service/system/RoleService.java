package com.itheima.bos.service.system;

import java.util.List;

import com.itheima.bos.domain.system.Role;

/**  
 * ClassName:RoleService <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 3:01:49 PM <br/>       
 */
public interface RoleService {

    List<Role> findAll();

    void save(Role model, List<Long> permissionIds, String menus);

}
  
