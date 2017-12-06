package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
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
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.StandardService;
import com.itheima.bos.web.common.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default") 
@Controller
public class StandardAction extends BaseAction<Standard> {
    public StandardAction() {
          
        super(Standard.class);  
        // TODO Auto-generated constructor stub  
    }
    private static final long serialVersionUID = -490805997641529189L;
    
    private Standard model = getModel();
    @Autowired
    private StandardService service;
    
  /*  private int page;
    private int rows;*/

    @Action(value = "standardAction_save", results = {@Result(name = "success",
            location = "/pages/base/standard.html", type = "redirect")})
    public String save() {
        service.add(model);
        return SUCCESS;
    }
    
    @Action(value="standardAction_queryByPage")
    public  String queryByPage() throws IOException {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Standard> byPage = service.findByPage(pageable);
      
        pageToJSON(byPage, null);
        return NONE;
    }
    @Action(value="standard_findAll")
    public String findAll() throws IOException {
       List<Standard> list= service.findAll();
       
      String json = JSONArray.fromObject(list).toString();
      ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(json);
       
       return NONE;
    }

}
