package com.itheima.bos.web.action;

import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.CourierService;
import com.itheima.bos.web.common.CommonAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CourierAction <br/>  
 * Function:  <br/>  
 * Date:     Nov 30, 2017 3:21:46 PM <br/>       
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class CourierAction extends CommonAction<Courier> {

    private Courier model=getModel();
    @Autowired
    private CourierService service;
   
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }
    
    @Action(value="courierAction_save",results= {@Result(name="success",location="/pages/base/courier.html",type="redirect")})
    public String save() {
        service.save(model);
        return SUCCESS;
    }
  
    @Action(value="courierAction_delete",
            results= {@Result(name="success",location="/pages/base/courier.html",type="redirect")})
    public String delete() {
       service.delete(ids);
        return SUCCESS;
    }
    
    @Action("courierAction_pageQuery")
    public  String pageQuery() throws IOException {
        final String courierNum = model.getCourierNum();
        final Standard standard = model.getStandard();
        final String company = model.getCompany();
        final String type = model.getType();
        //构造查询条件
        Specification<Courier> specification = new Specification<Courier>() {

            @Override
            public Predicate toPredicate(Root<Courier> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> list=new ArrayList<>();
              if (!StringUtils.isEmpty(courierNum)) {
                    Predicate predicate = cb.equal(root.get("courierNum").as(String.class), courierNum);
                    list.add(predicate);
                }
              if (!StringUtils.isEmpty(company)) {
                  Predicate predicate = cb.like(root.get("company").as(String.class), company);
                  list.add(predicate);
              }
              if (!StringUtils.isEmpty(type)) {
                  Predicate predicate = cb.equal(root.get("type").as(String.class), type);
                  list.add(predicate);
              }
              if (standard!=null&&StringUtils.isNotEmpty(standard.getName())) {
                  Join<Object, Object> join = root.join("standard");
                  Predicate predicate = cb.equal(join.get("name").as(String.class), standard.getName());
                  list.add(predicate);
              }
            
              if (list.size()==0) {
                return null;
              }
              //集合转为对象数组
              Predicate[] predicate = new Predicate[list.size()];
              list.toArray(predicate);
              
                return cb.and(predicate);
            }};
        //分页查询
        Pageable pageable = new PageRequest(page-1, rows);  
        Page<Courier> page=service.pageQuery(specification,pageable); 
        String[] excludes= {"takeTime","fixedAreas"};
        pageToJSON(page, excludes);
        return NONE;
    }
    
    @Action("courierAction_listajax")
    public String listajax() {
     List<Courier> list= service.listajax();
     listToJSON(list, new String[] {"fixedAreas"});
     
        return NONE;
    }
    
    

}
  
