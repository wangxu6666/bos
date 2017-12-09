package com.itheima.bos.service.fore;  
/**  
 * ClassName:OrderService <br/>  
 * Function:  <br/>  
 * Date:     Dec 8, 2017 3:14:13 PM <br/>       
 */

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.itheima.bos.domain.take_delivery.Order;


@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface OrderService {
    
    @POST
    @Path("/order")
    public void save(Order order);

}
  
