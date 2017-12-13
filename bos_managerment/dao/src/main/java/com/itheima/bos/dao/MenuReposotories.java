package com.itheima.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.system.Menu;

/**  
 * ClassName:MenuReposotories <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 4:47:29 PM <br/>       
 */
public interface MenuReposotories extends JpaRepository<Menu, Long> {
   List<Menu>  findByParentMenuIsNull();
}
  
