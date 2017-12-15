package com.itheima.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.User;

/**  
 * ClassName:MenuReposotories <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 4:47:29 PM <br/>       
 */
public interface MenuReposotories extends JpaRepository<Menu, Long> {
   List<Menu>  findByParentMenuIsNull();
   
   
    @Query("select m from Menu m join m.roles r join r.users u on u.id=?")
    List<Menu> findByUser(Long id);
}
  
