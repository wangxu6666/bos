package com.itheima.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     Nov 30, 2017 3:20:35 PM <br/>       
 */
public interface CourierService {

    void save(Courier model);

    Page<Courier> findByPage(Pageable page);

    void delete(String ids);

    Page<Courier> pageQuery(Specification<Courier> specification,
            Pageable pageable);
    

    List<Courier> listajax();

}
  
