package com.itheima.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.FixedArea;

/**  
 * ClassName:FixedAreaService <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 8:08:27 PM <br/>       
 */
public interface FixedAreaService {

    void save(FixedArea model);

 

    Page<FixedArea> findByPage(Pageable pageable);



    void linkCourierToFixedArea(Long id, long courierId, long takeTimeId);

}
  
