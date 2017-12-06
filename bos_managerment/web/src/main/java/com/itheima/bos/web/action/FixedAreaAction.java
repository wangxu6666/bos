package com.itheima.bos.web.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Customer;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.service.FixedAreaService;
import com.itheima.bos.web.common.CommonAction;


/**  
 * ClassName:FixedAreaAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 8:04:01 PM <br/>       
 */
@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class FixedAreaAction extends CommonAction<FixedArea> {
    
    /**  
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
     * @since JDK 1.6  
     */
    private static final long serialVersionUID = 6952218777898659593L;
    private FixedArea model=getModel();
    private List<Long> customerIds;
    private long courierId;
    private long takeTimeId;
    
    public void setCourierId(long courierId) {
        this.courierId = courierId;
    }
    public void setTakeTimeId(long takeTimeId) {
        this.takeTimeId = takeTimeId;
    }
    
    
    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    @Autowired
    private FixedAreaService service;
    
    /**
     * 
     * save:.保存 <br/>  
     *  
     * @return
     */
    @Action(value="fixedAreaAction_save",results= {@Result(name="success",location="/pages/base/fixed_area.html",type="redirect")})
    public String save() {
       
        service.save(model);
        
        return SUCCESS;
    }
    /**
     * 
     * pageQuery:.分页查询 <br/>  
     *  
     * @return
     */
    @Action("fixedAreaAction_pageQuery")
    public String pageQuery() {
        Pageable pageable = new PageRequest(page-1, rows);
        Page<FixedArea> list=service.findByPage(pageable);
         String[] excludes= {"subareas","couriers"};
        pageToJSON(list, excludes);
        
        return NONE;
    }
    /**
     * 
     * findNoBind:.找到没有绑定过的客户 <br/>  
     *  
     * @return
     */
    @Action("fixedAreaAction_findNoBind")
    public String findNoBind() {
       List<Customer> list = (List<Customer>) WebClient.create("http://localhost:8180/crm/webservice/cs/customer")
        .accept(MediaType.APPLICATION_JSON)
        .getCollection(Customer.class);
        listToJSON(list, null);
        
        return NONE;
    }
    /**
     * 
     * findBindFixedAreaId:. 找到定区已经绑定的客户<br/>  
     *  
     * @return
     */
    @Action("fixedAreaAction_findBindFixedAreaId")
    public String findBindFixedAreaId() {
        List<Customer> list=(List<Customer>) WebClient.create("http://localhost:8180/crm/webservice/cs/customer/fixedAreaId")
                .query("fixedAreaId", getModel().getId())
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);
        listToJSON(list, null);
        return NONE;
    }
    /**
     * 
     * fixedAreaBindCustomer:.绑定定区和客户 <br/>  
     *  
     * @return
     */
    @Action(value="fixedAreaAction_fixedAreaBindCustomer",results= {@Result(name="success",location="/pages/base/fixed_area.html",type="redirect")})
    public String fixedAreaBindCustomer() {
        if (customerIds==null) {
            customerIds=new ArrayList<>();
        }
        WebClient.create("http://localhost:8180/crm/webservice/cs/customer/")
        .query("fixedAreaId", getModel().getId())
        .query("ids", customerIds)
        .put(null);
       
        return SUCCESS;
    }
    
   /**
    *      定区绑定快递员
    */
 
    @Action(value="fixedAreaAction_linkCourierToFixedArea",results= {@Result(name="success",location="/pages/base/fixed_area.html",type="redirect")})
    public String linkCourierToFixedArea() {
       service.linkCourierToFixedArea(model.getId(),courierId,takeTimeId);
        return SUCCESS;
    }
    
    

}
  
