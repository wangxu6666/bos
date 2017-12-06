package com.itheima.bos.web.action;

import java.util.List;

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

import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.SubAreaService;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:SubAreaAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 2, 2017 3:56:26 PM <br/>       
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class SubAreaAction  extends CommonAction<SubArea>{
    
    private SubArea model=getModel();
    private List<Long> subAreaId;
    

    public void setSubAreaId(List<Long> subAreaId) {
        this.subAreaId = subAreaId;
    }

    @Autowired
    private SubAreaService service;
    @Action(value="subAreaAction_save",results= {@Result(name="success",location="/pages/base/sub_area.html",type="redirect")})
    public String save() {
        service.save(model);
        
        return SUCCESS;
    }
    @Action("subAreaAction_findByPage")
    public String findBypage() {
      Pageable pageable = new PageRequest(page-1,rows);
      Page<SubArea> page=  service.findByPage(pageable);
     
      pageToJSON(page, new String[] {"fixedArea","subareas"});

        return NONE;
    }
   
    @Action("subAreaAction_findBindFixedArea")
    public String findBindFixedArea() {
        
      
     List<SubArea> list=service.findBindFixedArea(model.getId());
 
     listToJSON(list, new String[] {"area","couriers","subareas"});
        return NONE;
    }

    @Action("subAreaAction_findNoBindFixedArea")
    public String findBindNoFixedArea() {
    List<SubArea> list=service.findNoBindFixedArea();
   
    listToJSON(list, new String[] {"area"});
   
        return NONE;
    }
    
    @Action(value="subAreaAction_bindFixedArea",results= {@Result(name="success",location="/pages/base/fixed_area.html",type="redirect")})
    public String bindFixedArea() {
        
        service.BindFixedArea(model.getId(),subAreaId);
        return SUCCESS;
    }
}
  
