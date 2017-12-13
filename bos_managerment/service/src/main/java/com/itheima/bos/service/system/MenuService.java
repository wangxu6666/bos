package com.itheima.bos.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.system.Menu;

/**  
 * ClassName:MenuService <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 4:46:04 PM <br/>       
 */
public interface MenuService {

    List<Menu> findAll();

    void save(Menu model);

    Page<Menu> findByPage(Pageable pageable);

}
  
