package com.itheima.bos.fore.web.action;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.take_delivery.Order;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**  
 * ClassName:OrderAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 8, 2017 2:52:30 PM <br/>       
 */
@ParentPackage("struts-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
  
    private Order model=new Order();
    @Override
    public Order getModel() {
          
        // TODO Auto-generated method stub  
        return model;
    }
    
    private String sendAreaInfo;
    private String recAreaInfo;
    public void setSendAreaInfo(String sendAreaInfo) {
        this.sendAreaInfo = sendAreaInfo;
    }
    public void setRecAreaInfo(String recAreaInfo) {
        this.recAreaInfo = recAreaInfo;
    }
    @Action("orderAction_add")
    public String add() {
        //页面传过来数据为   **省/**市/**区
        if (StringUtils.isNotEmpty(sendAreaInfo)) {
             String[] split = sendAreaInfo.split("/");
             Area area = new Area();
             String province = split[0].substring(0, split[0].length()-1);
             String city = split[1].substring(0, split[1].length()-1);
             String district = split[2].substring(0, split[2].length()-1);
             area.setProvince(province);
             area.setCity(city);
             area.setDistrict(district);
             model.setSendArea(area);
        }
        if (StringUtils.isNotEmpty(recAreaInfo)) {
            String[] split =recAreaInfo .split("/");
            Area area = new Area();
            //截取最后的一个字符  省市区
            String province = split[0].substring(0, split[0].length()-1);
            String city = split[1].substring(0, split[1].length()-1);
            String district = split[2].substring(0, split[2].length()-1);
            area.setProvince(province);
            area.setCity(city);
            area.setDistrict(district);
            model.setRecArea(area);
        }
      
        //向后台发送 webservice请求   
     
        WebClient.create("http://localhost:8080/web/webservice/os/order")
        .type(MediaType.APPLICATION_JSON)
        .post(model);
        System.out.println("前端发送请求");
        return NONE;
    }
    
    

}
  
