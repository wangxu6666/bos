package com.itheima.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     Nov 27, 2017 9:54:42 PM <br/>       
 */
public interface StandardService {
    void add(Standard standard);

    Page<Standard> findByPage(Pageable pageable);

    List<Standard> findAll();
}
  
