package com.itheima.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerRepositories;
import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 3, 2017 3:26:10 PM <br/>       
 */
@Service
@Transactional
public class CustomerServiceImp implements CustomerService{
   @Autowired
    private CustomerRepositories dao;
    @Override
    public List<Customer> findAll() {
          System.out.println("findAll");
        // TODO Auto-generated method stub  
        return dao.findAll();
    }
    @Override
    public List<Customer> findCustomersUnAssociated() {
          System.out.println("findCustomerUnAssociated");
        // TODO Auto-generated method stub  
        return dao.findUnBind();
    }
    @Override
    public List<Customer> findCustomersAssociatedToFixedArea(String fixedAreaId) {
        System.out.println("findCustomerAssociatedToFixedArea");
        return dao.findBinded(fixedAreaId);
    }
    @Override
    public void fixedAreaBindCustomer(String fixedAreaId, List<Long> ids) {
          System.out.println("fixedAreaBindCustomer");
       //先清空   再将customer设置fixedAreaId
       dao.updateNull(fixedAreaId);
     
       if (ids!=null&&ids.size()>0) {
           for (Long long1 : ids) {
               dao.bindFixedArea(fixedAreaId, long1);
            }      
    }
     
    }
    @Override
    public void save(Customer customer) {
        System.out.println("save:"+customer);
          dao.save(customer);
    }
    @Override
    public Customer find(String telephone) {
        System.out.println("find Actived"+telephone);
        return dao.findActived(telephone);
    }
    @Override
    public void active(String telephone) {
          System.out.println("active telephone"+telephone);
            dao.active(telephone);     
    }
    @Override
    public Customer find(String telephone, String password) {
           
        System.out.println("findByTelephoneAndPassword:"+telephone+"---"+password);
        return dao.findByTelephoneAndPassword(telephone,password);
    }
    @Override
    public String findFixedIdByAddress(String address) {
       return    dao.findFixedIdByAddress(address);
      
        
    }

}
