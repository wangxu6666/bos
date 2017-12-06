package snippet;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:Snippet <br/>  
 * Function:  <br/>  
 * Date:     Dec 3, 2017 3:51:22 PM <br/>       
 */

public class Snippet {
    public static void main(String[] args) {
    
     //find();
     //findAll();
     findid();
    
    }
    
    public static void findAll() {
        Collection<? extends Customer> collection = WebClient
                .create("http://localhost:8180/crm/webservice/cs/customerAll")
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
    
        for (Customer customer : collection) {
            System.out.println(customer);
        } 
    }
    public static void find() {
        Collection<? extends Customer> collection = WebClient
                .create("http://localhost:8180/crm/webservice/cs/customer")
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
    
        for (Customer customer : collection) {
            System.out.println(customer);
        } 
    }
    
    public static void findid() {
        Collection<? extends Customer> collection = WebClient
                .create("http://localhost:8180/crm/webservice/cs/customer/fixedAreaId")
                .query("fixedAreaId", 1)
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
    
        for (Customer customer : collection) {
            System.out.println(customer);
        } 
    }
    
}  
  
