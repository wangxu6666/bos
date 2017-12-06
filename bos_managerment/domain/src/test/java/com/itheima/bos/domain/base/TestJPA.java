/*package com.itheima.bos.domain.base;  
*//**  
 * ClassName:TestJPA <br/>  
 * Function:  <br/>  
 * Date:     Nov 27, 2017 7:38:11 PM <br/>       
 *//*

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.itheima.bos.utils.JPAUtil;


public class TestJPA {
     @Test
    public void test1() {
       Area area = new  Area();
       area.setCitycode("001");
       area.setCity("南山");
       area.setDistrict("10km");
       area.setId(1000L);
       EntityManager manager = JPAUtil.createEntityManager();
       EntityTransaction transaction = manager.getTransaction();
       transaction.begin();
       manager.persist(area);
       transaction.commit();
       manager.close();
    }
     @Test
    public void test() {
       Area area = new  Area();
       area.setCitycode("001");
       area.setCity("南山");
       area.setDistrict("10km");
       area.setId(1001L);
       EntityManager manager = JPAUtil.createEntityManager();
       EntityTransaction transaction = manager.getTransaction();
       transaction.begin();
       manager.merge(area);
       transaction.commit();
       manager.close();
    }
    
     @Test
    public void test2() {
       
       EntityManager manager = JPAUtil.createEntityManager();
       EntityTransaction transaction = manager.getTransaction();
       transaction.begin();
       Area area = manager.find(Area.class, 1000L);
       System.out.println(area);
       transaction.commit();
       manager.close();
    }
     
     @Test
    public void test3() {
       
       EntityManager manager = JPAUtil.createEntityManager();
       EntityTransaction transaction = manager.getTransaction();
       transaction.begin();
       Area area = manager.getReference(Area.class, 1001L);
       System.out.println(area);
       transaction.commit();
       manager.close();
    }
     
}
  
*/