package com.itheima.bos.fore.web.action;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import com.itheima.bos.utils.MailUtils;
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
    @Autowired
    private RedisTemplate<String, String> template;
    @Autowired
    private JmsTemplate jTemplate;
    private Customer model=new Customer();
    private String checkcode;
    private String random;
    
    
    
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    
    @Override
    public Customer getModel() {
        return model;
    }
    
    @Action(value = "customerAction_regist",
            results = {
                    @Result(name = "success", location = "/signup-success.html",
                            type = "redirect"),
                    @Result(name = "error", location = "/signup-fail.html",
                            type = "redirect")})
   //校验验证码，注册，发送邮件
    public String regist()  {
        String code = (String) ServletActionContext.getRequest().getSession().getAttribute(model.getTelephone());
        System.out.println(code+"---"+checkcode);
        if (code.equals(checkcode)) {
            WebClient.create("http://localhost:8180/crm/webservice/cs/customer")
            .type(MediaType.APPLICATION_JSON)
            .post(model);
            String activeCode = RandomStringUtils.randomNumeric(10);
            template.opsForValue().set(model.getTelephone(), activeCode,30,TimeUnit.MINUTES);
            MailUtils.sendMail(model.getEmail(), "激活邮件", "这是一封激活邮件，请在30分钟内"
                    + "<a href='localhost:8280/bos_fore/active.action?telephone="+model.getTelephone()+"&random="+activeCode+"'>点击</a>完成激活");
         
        }else {
            return "error";
        }
        return SUCCESS;
    }
    
    //校验邮件验证码，再将状态改为激活
    @Action(value="active",results= {@Result(name="actived",location="actived.html",type="redirect"),
            @Result(name="login",location="login.html",type="redirect")}
    )
    public String active() {
        String activeCode = template.opsForValue().get(model.getTelephone());
        if (StringUtils.isNotEmpty(activeCode)&&StringUtils.isNotEmpty(random)&&activeCode.equals(random)) {
           //查看是否已经激活
            Customer customer = WebClient.create("http://localhost:8180/crm/webservice/cs/customer")
            .accept(MediaType.APPLICATION_JSON)
            .query("telephone", model.getTelephone())
            .type(MediaType.APPLICATION_JSON)
            .get(Customer.class);
          if (customer!=null) {
            return "actived";
          }else {
              WebClient.create("http://localhost:8180/crm/webservice/cs/customer_active")
              .type(MediaType.APPLICATION_JSON)
              .query("telephone", model.getTelephone())
              .put(null);     
               return "login";
          }
        }
        return NONE;
        
    }
    
    public void setRandom(String random) {
        this.random = random;
    }

    @Action("customerAction_sendCode")
    public String sendCode() throws IOException {
      final  String code = RandomStringUtils.randomNumeric(6);
    /*    String sms = SmsUtils.sendSmsByWebService(model.getTelephone(), "尊敬的客户你好，您本次获取的验证码为："+code);
        String flag = "failed";
        if (StringUtils.isNotEmpty(sms)&&sms.length()==16) {
            flag="ok";
           System.out.println(model.getTelephone()+"发送验证码"+code);
        }
        System.out.println(model.getTelephone()+"发送验证码"+code);
      
        ServletActionContext.getResponse().getWriter().write(flag);*/
        //改写 发送信息到中间件
        jTemplate.send("sms_queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
               MapMessage mapMessage = session.createMapMessage();
               mapMessage.setString("telephone", model.getTelephone());
               mapMessage.setString("code","尊敬的客户你好，您本次获取的验证码为："+code );
                // TODO Auto-generated method stub  
                return mapMessage;
            }
        });
        //发送信息有bos_sms完成   验证码需要本服务器保存        
        ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), code);
        return NONE;
    }
    
    @Action(value="customerAction_login",results= {@Result(name="success",location="/myhome.html",type="redirect")
    ,@Result(name="failed",location="loadfail.html",type="redirect")})
    public String login() {
        String attribute = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
        if (StringUtils.isNotEmpty(attribute)&&StringUtils.isNotEmpty(checkcode)&&attribute.equals(checkcode)) {
            Customer customer = WebClient.create("http://localhost:8180/crm/webservice/cs/customer_login")
            .accept(MediaType.APPLICATION_JSON)
            .type(MediaType.APPLICATION_JSON)
            .query("telephone", model.getTelephone())
            .query("password", model.getPassword())
            .get(Customer.class);
            if (customer!=null) {
                return SUCCESS;
            }
        }        
        return "failed";
    }

}
  
