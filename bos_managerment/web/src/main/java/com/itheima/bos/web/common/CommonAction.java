package com.itheima.bos.web.common;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CommonAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 1, 2017 10:19:16 AM <br/>       
 * @param <T>
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T> {

    private T model;
    protected int page;
    protected int rows;
    
    
    public void setPage(int page) {
        this.page = page;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    
    public CommonAction() {
       Class<? extends CommonAction> class1 = this.getClass();
       Type type = class1.getGenericSuperclass();  //获取子类所有的泛型参数
       ParameterizedType param=  (ParameterizedType)type;
       Type child = param.getActualTypeArguments()[0];  //获取泛型
       Class<T> clazz=(Class<T>) child;
       try {
        model=clazz.newInstance();
    } catch (Exception e) {
        e.printStackTrace();  
    } 
         
    }
    @Override
    public T getModel() {
        return model;
    }
    
    public void pageToJSON(Page<T> page,String[] excludes) {
       Map<String,Object> map=new HashedMap();
       map.put("total", page.getTotalElements());
       map.put("rows", page.getContent());
       JsonConfig jsonConfig=new JsonConfig();
       jsonConfig.setExcludes(excludes);
    String json = JSONObject.fromObject(map, jsonConfig).toString();
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=utf-8");
    try {
        response.getWriter().write(json);
    } catch (IOException e) {
          
        // TODO Auto-generated catch block  
        e.printStackTrace();  
        
    }
        
        
    }
    
    public void listToJSON(List list,String[] excludes) {
        JsonConfig config=new JsonConfig();
        config.setExcludes(excludes);
        String json = JSONArray.fromObject(list,config).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
              
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            
        }
    }
    
    
    
    

}
  
