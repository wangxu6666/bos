package com.itheima.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.Area;

/**  
 * ClassName:AreaService <br/>  
 * Function:  <br/>  
 * Date:     Nov 30, 2017 8:56:19 PM <br/>       
 */
public interface AreaService {

    void save(List<Area> list);

    Page<Area> findByPage(Pageable pageable);

    List<Area> findAll();

    List<Area> finQ(String q);

}
  
