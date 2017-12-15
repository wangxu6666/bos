package com.itheima.bos.service.system;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.MenuReposotories;
import com.itheima.bos.dao.PermissionRepositories;
import com.itheima.bos.dao.RoleRepositories;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;

/**  
 * ClassName:RoleServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 3:02:01 PM <br/>       
 */
@Service
@Transactional
public class RoleServiceImp implements RoleService {
    
    @Autowired
    private RoleRepositories roleDao;
    @Autowired
    private PermissionRepositories pDao;
    @Autowired
    private MenuReposotories mDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role model, List<Long> permissionIds, String menus) {
          
     if (!permissionIds.isEmpty()) {
        for (Long pid : permissionIds) {
            //获取持久对象
           // Permission permission = pDao.findOne(pid);
            Permission permission2 = new Permission();
            permission2.setId(pid);
            model.getPermissions().add(permission2);
        }
        
    }
     
     if (StringUtils.isNotEmpty(menus)&&menus.length()>0) {
         String[] split = menus.split(",");
         for (String mid : split) {
             //获取持久对象
          //  Menu menu = mDao.findOne(Long.parseLong(mid));
             Menu menu = new  Menu();
             menu.setId(Long.parseLong(mid));
             model.getMenus().add(menu);
        }
         
       roleDao.save(model);  
        
    }
     
        
    }

}
  
