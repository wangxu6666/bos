package com.itheima.bos.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.system.RoleService;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:RoleAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 15, 2017 2:58:30 PM <br/>       
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class RoleAction extends CommonAction<Role> {
    
    /**  
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
     * @since JDK 1.6  
     */
    private static final long serialVersionUID = 6864261368768315420L;
    private String menuIds;
    private List<Long> permissionIds;

  
    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
    @Autowired
    private RoleService roleService;
    
    @Action("roleAction_findAll")
    public String findAll() {
    List<Role> list= roleService.findAll();
        listToJSON(list, new String[] {"permissions","menus","users"});
        return NONE;
    }
    
    @Action(value="roleAction_save",results= {@Result(name="success",location="/pages/system/role.html",type="redirect")})
    public String save() {
        roleService.save(getModel(),permissionIds,menuIds);
      return SUCCESS;  
    }
    
    

}
  
