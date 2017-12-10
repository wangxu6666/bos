package com.itheima.activeMQ;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.itheima.bos.utils.SmsUtils;

/**  
 * ClassName:Consumer <br/>  
 * Function:  <br/>  
 * Date:     Dec 10, 2017 3:37:27 PM <br/>       
 */
@Component
public class Consumer implements MessageListener {

    @Override
    public void onMessage(Message arg0) {
        MapMessage mapMessage=(MapMessage) arg0;   
        try {
            String telephone = mapMessage.getString("telephone");
            String code = mapMessage.getString("code");
            System.out.println(telephone+"--"+code);
            //发送信息
            String sms = SmsUtils.sendSmsByWebService(telephone, code);
          //  String flag = "failed";
            if (StringUtils.isNotEmpty(sms)&&sms.length()==16) {
             //   flag="ok";
                System.out.println("发送注册信息成功");
            }
            
        } catch (JMSException e) {
            e.printStackTrace();  
        }
        
    }
    
    

}
  
