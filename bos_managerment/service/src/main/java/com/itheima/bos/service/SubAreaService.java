package com.itheima.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubAreaService <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 3:57:09 PM <br/>       
 */

public interface SubAreaService {

    void save(SubArea model);

    Page<SubArea> findByPage(Pageable pageable);

    List<SubArea> findBindFixedArea(Long id);

    List<SubArea> findNoBindFixedArea();

    void BindFixedArea(Long id, List<Long> subAreaId);

    List<SubArea> findAll();

}
  
