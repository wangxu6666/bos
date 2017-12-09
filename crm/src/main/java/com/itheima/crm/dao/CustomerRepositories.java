package com.itheima.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 3, 2017 3:27:16 PM <br/>       
 */
public interface CustomerRepositories extends JpaRepository<Customer, Long> {
    @Query("from Customer where fixedAreaId is null")
    List<Customer> findUnBind();
    
    @Query("from Customer where fixedAreaId = ?")
    List<Customer> findBinded(String id);
    
    @Modifying
    @Query("update Customer set fixedAreaId=? where id=?")
    void bindFixedArea(String fixedAreaId,long id);
    
    @Modifying
    @Query("update Customer set fixedAreaId = null where fixedAreaId=?")
    void updateNull(String fixedAreaId);
    
  @Query("from Customer where telephone=? and type=1")
  Customer findActived(String telephone);
  
   @Modifying
   @Query("update Customer set type=1 where telephone=?")
   void active(String telephone);
   
   @Query("from Customer where telephone=?1 and password=?2 and type=1")
   Customer findByTelephoneAndPassword(String telephone, String password);
    @Query("select fixedAreaId from Customer where address = ? ")
    String findFixedIdByAddress(String address );

}

