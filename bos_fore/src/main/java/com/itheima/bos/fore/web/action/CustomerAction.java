package com.itheima.bos.fore.web.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.utils.SmsUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import crm.domain.bos.domain.Customer;

/**  
 * ClassName:CustomerAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 5, 2017 5:44:00 PM <br/>       
 */
@ParentPackage("struts-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    
    private Customer model=new Customer();
    private String checkcode;
    
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    
    @Override
    public Customer getModel() {
        return model;
    }
    
    @Action("customerAction_regist")
    public String regist()  {
        String code = (String) ServletActionContext.getRequest().getSession().getAttribute(model.getTelephone());
        System.out.println(code+"---"+checkcode);
        if (code.equals(checkcode)) {
            System.out.println("正在注册");
            WebClient.create("http://localhost:8180/crm/webservice/cs/customer")
            .type(MediaType.APPLICATION_JSON)
            .post(model);
        }
        return NONE;
    }
    
    @Action("customerAction_sendCode")
    public String sendCode() throws IOException {
        String code = RandomStringUtils.randomNumeric(6);
        String sms = SmsUtils.sendSmsByWebService(model.getTelephone(), "尊敬的客户你好，您本次获取的验证码为："+code);
        String flag = "failed";
        if (StringUtils.isNotEmpty(sms)&&sms.length()==16) {
            flag="ok";
           System.out.println(model.getTelephone()+"发送验证码"+code);
        }
        System.out.println(model.getTelephone()+"发送验证码"+code);
        ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), code);
        ServletActionContext.getResponse().getWriter().write(flag);
        return NONE;
    }
    
    @Action("customerAction_login")
    public String login() {
        
        
        return NONE;
    }

}
  
