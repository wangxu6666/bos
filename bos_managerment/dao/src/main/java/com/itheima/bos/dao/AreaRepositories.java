package com.itheima.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.domain.base.Area;

/**  
 * ClassName:AreaRepositories <br/>  
 * Function:  <br/>  
 * Date:     Nov 27, 2017 8:31:42 PM <br/>       
 */
public interface AreaRepositories extends JpaRepository<Area, Long>{

    public Area findByCity(String city);  
    
    
    @Query("from Area where city=? and id=?")
    public Area findByXXX(String city,Long id);
    
    
    @Query("from Area where city=?2 and id=?1")
    public Area findByXXX(Long id,String city);
    

    @Modifying
    @Transactional
    @Query("delete from Area where city=?")
    public void remove(String city) ;
     
    @Modifying
    @Transactional
    @Query("update Area set province=?2 where city=?1")
    public void update(String city,String province );

    
    @Query("from Area a where a.province like ?1 or a.city like ?1 or a.district like ?1"
            + " or a.citycode like ?1 or a.postcode like ?1 or a.shortcode like ?1")
    public List<Area> findQ(String q);

    @Query("from Area a where a.province like ?1 and a.city like ?2 and a.district like ?3")
    public Area findByPCD(String province, String city, String district);
    
}
  
