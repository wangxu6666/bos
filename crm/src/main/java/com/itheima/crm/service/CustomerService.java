package com.itheima.crm.service;

import java.util.List;  

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;



import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     Dec 3, 2017 3:21:44 PM <br/>       
 */
public interface CustomerService {
    
    //获取所有
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/customerAll")
    public List<Customer> findAll();
    
    //未绑定地区的客户
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/customer_unbind")
    public List<Customer> findCustomersUnAssociated();
    
    //绑定客户和定区
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/customer")
    public  void fixedAreaBindCustomer(@QueryParam("fixedAreaId")String fixedAreaId,
            @QueryParam("ids")List<Long> ids);
    
    
    //找到已经绑定地区的客户
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/customer_bind")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Customer> findCustomersAssociatedToFixedArea(@QueryParam("fixedAreaId")String fixedAreaId);
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/customer")
    public void save(Customer customer);
    
    @GET
    @Path("/customer")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Customer find(@QueryParam("telephone")String telephone);
    
    @PUT
    @Path("/customer_active")
    @Consumes({MediaType.APPLICATION_JSON})
    public void active(@QueryParam("telephone")String telephone);
    
    @GET
    @Path("/customer_login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Customer find(@QueryParam("telephone")String telephone,@QueryParam("password")String password);
    
   
    @GET
    @Path("/customer_fixedId")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String findFixedIdByAddress(@QueryParam("address")String address);
}
  
