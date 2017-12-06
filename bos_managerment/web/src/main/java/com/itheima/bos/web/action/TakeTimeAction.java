package com.itheima.bos.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.TakeTimeService;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 5, 2017 3:09:12 PM <br/>       
 */
@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class TakeTimeAction extends CommonAction<TakeTime> {
    
    
    private TakeTime model=getModel();
    @Autowired
    private TakeTimeService service;
    
    @Action("takeTimeAction_listajax")
    public String listajax() {
    List<TakeTime> list= service.findAll();
        listToJSON(list, null);
        return NONE;
    }
    
    

}
  
