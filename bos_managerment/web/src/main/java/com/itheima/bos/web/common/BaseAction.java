package com.itheima.bos.web.common;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.itheima.bos.domain.base.Standard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:BaseAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 1, 2017 9:58:28 AM <br/>       
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    private T model;
    private Class<T> clazz;
    protected int page;
    protected int rows;
    
    
   public void setPage(int page) {
        this.page = page;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    
  public BaseAction(Class<T> clazz) {
    this.clazz=clazz;
    try {
        model=clazz.newInstance();
    } catch (Exception e) {
        e.printStackTrace();      
    } 
  }
    @Override
    public T getModel() {
          
        // TODO Auto-generated method stub  
        return model;
    }
    
    public String pageToJSON(Page<Standard> page,String[] excludes) throws IOException {
        
        
        //手动封装
        Map<String, Object> hashedMap = new HashedMap();
        hashedMap.put("total",page.getTotalElements() );
        hashedMap.put("rows", page.getContent());
        JsonConfig config=new JsonConfig();
        config.setExcludes(excludes);
        //生成json对象
        String json = JSONObject.fromObject(hashedMap).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return NONE;
        
    }

}
  
