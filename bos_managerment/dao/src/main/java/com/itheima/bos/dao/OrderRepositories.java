package com.itheima.bos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.take_delivery.Order;

/**  
 * ClassName:OrderRepositories <br/>  
 * Function:  <br/>  
 * Date:     Dec 9, 2017 2:32:33 PM <br/>       
 */
public interface OrderRepositories extends JpaRepository<Order, Long> {

}
  
