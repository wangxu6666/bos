package com.itheima.bos.jobs;  
/**  
 * ClassName:SendEmail <br/>  
 * Function:  <br/>  
 * Date:     Dec 16, 2017 2:55:25 PM <br/>       
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.bos.dao.WorkBillRepositories;
import com.itheima.bos.domain.take_delivery.WorkBill;
import com.itheima.bos.utils.MailUtils;

public class SendEmail {
    
    @Autowired
    private WorkBillRepositories wDao;
    
    public void send() {
        List<WorkBill> all = wDao.findAll();
        String receiver="aa@wangxu.com";
        String subject="工单信息";
        String emailBody="工单编号\t工单类型 \t取件状态\t快递员</br>";
        for (WorkBill workBill : all) {
            emailBody+=workBill.getId()+"\t"+workBill.getType()+"\t"
        +workBill.getPickstate()+"\t"+workBill.getCourier().getName()+"</br>";
        }
        
        MailUtils.sendMail(receiver, subject, emailBody);
    }
    

}
  
