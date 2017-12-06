package com.itheima.bos.utils;  
/**  
 * ClassName:JPAUtil <br/>  
 * Function:  <br/>  
 * Date:     Nov 27, 2017 7:32:27 PM <br/>       
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
 private static EntityManagerFactory factory;   
  static {
      factory=Persistence.createEntityManagerFactory("myJPAUnit");
  }
  
 public static EntityManager   createEntityManager() {
      return factory.createEntityManager();
 }
 
 public static void main(String[] args) {
     createEntityManager();
 }

}
  
