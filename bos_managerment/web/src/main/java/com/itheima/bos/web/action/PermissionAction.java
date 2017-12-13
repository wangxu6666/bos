package com.itheima.bos.web.action;

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

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.service.system.PermissionService;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:PermissionAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 7:57:26 PM <br/>       
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class PermissionAction  extends CommonAction<Permission>{
    
    private Permission model=getModel();
    @Autowired
    private PermissionService pService;
    
    @Action("permissionAction_findByPage")
    public String findByPage() {
        Pageable pageable = new PageRequest(page-1, rows);
        Page<Permission> p=  pService.findByPage(pageable);
        pageToJSON(p, new String[] {"roles"});
        return NONE;
    }
    
    @Action(value="permissionAction_save",results= {@Result(name="success",location="/pages/system/permission.html",type="redirect")})
    public String save() {
        pService.save(model);
        return SUCCESS;
    }

}
  
