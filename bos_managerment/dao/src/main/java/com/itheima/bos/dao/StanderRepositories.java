package com.itheima.bos.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StanderRepositories <br/>  
 * Function:  <br/>  
 * Date:     Nov 27, 2017 9:29:32 PM <br/>       
 */
public interface StanderRepositories extends JpaRepository<Standard, Long> {
    
    
   
}
  
