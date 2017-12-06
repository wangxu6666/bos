package com.itheima.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubAreaRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 3:58:08 PM <br/>       
 */
public interface SubAreaRepositories extends JpaRepository<SubArea, Long> {
    @Query("from SubArea where fixedArea.id =?")
    List<SubArea> findBindFixedArea(long id);
    @Query("from SubArea where fixedArea.id is null")
    List<SubArea> findNoBindFixedArea();
    
    @Query("update SubArea set fixedArea.id=null where fixedArea.id=?")
    @Modifying
    void updateNull(long id);
    
    @Query("update SubArea set fixedArea.id=?1 where id=?2")
    @Modifying
    void bindFixedArea(Long id, Long subId);

}
  
