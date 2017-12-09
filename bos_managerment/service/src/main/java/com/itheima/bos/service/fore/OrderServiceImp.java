package com.itheima.bos.service.fore;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.AreaRepositories;
import com.itheima.bos.dao.FixedAreaRepositories;
import com.itheima.bos.dao.OrderRepositories;
import com.itheima.bos.dao.SubAreaRepositories;
import com.itheima.bos.dao.WorkBillRepositories;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.domain.take_delivery.WorkBill;

/**  
 * ClassName:OrderServiceImp <br/>  
 * Function:  <br/>  
 * Date:     Dec 8, 2017 3:14:24 PM <br/>       
 */

@Service
@Transactional
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepositories orderDao;
    @Autowired
    private AreaRepositories areaDao;
    @Autowired
    private FixedAreaRepositories fixedDao;
    @Autowired
    private WorkBillRepositories workBillDao;
    @Autowired
    private SubAreaRepositories subAreaDao;
    @Override
    public void save(Order order) {
        //先检查地址 是否在收派范围
       Area sendArea = order.getSendArea();
       if (sendArea!=null) {
           System.out.println(sendArea.toString());
           Area areaDB= areaDao.findByPCD(sendArea.getProvince(),sendArea.getCity(),sendArea.getDistrict());
           order.setSendArea(areaDB);
        }
       
       Area recArea = order.getRecArea();
       if (recArea!=null) {
           System.out.println(recArea.toString());
           Area recDb=  areaDao.findByPCD(recArea.getProvince(),recArea.getCity(),recArea.getDistrict());
           order.setRecArea(recDb);
        }
       //保存订单
       orderDao.save(order);
       //根据详细地址精准匹配分区ID
       String fixedId = WebClient.create("http://localhost:8180/crm/webservice/cs/customer_fixedId")
       .type(MediaType.APPLICATION_JSON)
       .accept(MediaType.APPLICATION_JSON)
       .query("address", order.getSendAddress())
       .get(String.class);
       //匹配到分区
       if (StringUtils.isNotEmpty(fixedId)) {
           //找出分区里面的所有快递员
        FixedArea area = fixedDao.findOne(Long.parseLong(fixedId));
        Set<Courier> couriers = area.getCouriers();
       if (!couriers.isEmpty()) {
           Iterator<Courier> iterator = couriers.iterator();
           Courier courier = iterator.next();
               // 为订单指定快递员
            WorkBill workBill = new WorkBill();
            workBill.setBuildtime(new Date());
            workBill.setCourier(courier);
            workBill.setOrder(order);
            workBill.setType("新");
            workBill.setAttachbilltimes(0);
            workBill.setRemark(order.getRemark());
            workBill.setSmsNumber("123456");
            workBill.setPickstate("新单");
             workBillDao.save(workBill);
             System.out.println("匹配地址分单完成");
             order.setOrderType("自动分单");
             return;
        
          
          
         }
           
    }  
       
       
       if (order.getSendArea()!=null) {
       //根据分区的关键字匹配
       //找到定区下所有的分区
        Set<SubArea> subareas = order.getSendArea().getSubareas();
        for (SubArea subArea : subareas) {
            String keyWords = subArea.getKeyWords();
            String assistKeyWords = subArea.getAssistKeyWords();
            //用寄件地址去匹配分区的关键字
            if (order.getSendAddress().contains(keyWords)||order.getSendAddress().contains(assistKeyWords)) {
           Set<Courier> couriers = subArea.getFixedArea().getCouriers();
          
            if (!couriers.isEmpty()) {
                Courier next = couriers.iterator().next();
                WorkBill workBill = new WorkBill();
                workBill.setBuildtime(new Date());
                workBill.setCourier(next);
                workBill.setOrder(order);
                workBill.setType("新");
                workBill.setAttachbilltimes(0);
                workBill.setRemark(order.getRemark());
                workBill.setSmsNumber("123456");
                workBill.setPickstate("新单");
                 workBillDao.save(workBill);
                 System.out.println("地址匹配关键字分配订单"); 
                 order.setOrderType("自动分单");
                 return;
            }
            }
        }
    }
      //手动分单 
        order.setOrderType("手动分单");
    
    }
    
  
}
  
