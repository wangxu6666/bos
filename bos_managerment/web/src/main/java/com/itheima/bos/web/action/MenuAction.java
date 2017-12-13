package com.itheima.bos.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.service.system.MenuService;
import com.itheima.bos.web.common.CommonAction;



/**  
 * ClassName:MenuAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 13, 2017 4:44:08 PM <br/>       
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class MenuAction extends CommonAction<Menu> {
    
    private Menu model=getModel();
    @Autowired
    private MenuService menuService;
    
    @Action("menu_findAll")
    public String findAll() {
     List<Menu> list=  menuService.findAll();
        listToJSON(list, new String[] {"roles","parentMenu","childrenMenus"});
        return NONE;
    }
    
    @Action(value="menuAction_save",results= {@Result(name="success",location="/pages/system/menu.html",type="redirect")})
    public String save() {
      menuService.save(model);
        
        return SUCCESS;
    }
    
    @Action("menuAction_findByPage")
    public String findByPage() {
        //menu类中存在属性为page,属性会优先封装给模型
        int page=Integer.parseInt(model.getPage());
        Pageable pageable = new PageRequest(page-1, rows);
        Page<Menu> p=  menuService.findByPage(pageable);
        pageToJSON(p, new String[] {"roles","parentMenu","childrenMenus"});
        return NONE;
    }
    
    

}
  
