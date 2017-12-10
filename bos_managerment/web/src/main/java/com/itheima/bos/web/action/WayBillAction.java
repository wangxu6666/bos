package com.itheima.bos.web.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.WayBillService;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:WayBillAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 10, 2017 5:13:06 PM <br/>       
 */
@Namespace("/")
@Controller
@Scope("prototype")
@ParentPackage("struts-default")
public class WayBillAction extends CommonAction<WayBill> {

   private WayBill model=getModel();
   
   @Autowired
   private WayBillService wService;
   
   @Action("waybillAction_save")
   public String save() throws IOException {
       String result="1";
       try {
           wService.save(model);
        
    } catch (Exception e) {
        // TODO: handle exception
        result="0";
        e.printStackTrace();
    }
       HttpServletResponse response = ServletActionContext.getResponse();
       response.setContentType("applcation/json;charset=utf-8");
       response.getWriter().write(result);
       return NONE;
       
   }
    
}
  
