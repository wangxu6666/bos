package com.itheima.bos.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.exolab.castor.xml.validators.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.system.UserService;
import com.itheima.bos.web.common.CommonAction;

/**  
 * ClassName:UserAction <br/>  
 * Function:  <br/>  
 * Date:     Dec 12, 2017 3:18:21 PM <br/>       
 */
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
@Controller
public class UserAction extends CommonAction<User> {
    
    
    /**  
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
     * @since JDK 1.6  
     */
    private static final long serialVersionUID = 2427416382257389431L;
    private User model=getModel();
    @Autowired
    private UserService userService;
    
    private Long[] roleIds;
    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    @Action(value = "userAction_login",
            results = {
                    @Result(name = "success", location = "/index.html",
                            type = "redirect"),
                    @Result(name = "login", location = "/login.html",
                            type = "redirect")})
    public String login() {
        String sCode=(String) ServletActionContext.getRequest().getSession().getAttribute("key");
        String code = ServletActionContext.getRequest().getParameter("code");
     //   if (StringUtils.isNotEmpty(code)&&StringUtils.isNotEmpty(sCode)&&code.equalsIgnoreCase(sCode)) {
       if (true) {
       
            // 验证码比对成功
            // 从框架中获取Subject,代表当前用户
            Subject subject = SecurityUtils.getSubject();
            // 创建用户名密码令牌
            AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
                return SUCCESS;
            } catch (Exception e) {
              e.printStackTrace();
            }
        }
        return "login";
    }
    
    
    @Action(value = "userAction_logout",
            results = {
                    @Result(name = "success", location = "/index.html",
                            type = "redirect")})
    public String logout() {
       Subject subject = SecurityUtils.getSubject();
       subject.logout();
        
        return SUCCESS;
    }
    
    
    @Action(value="userAction_save",results= {@Result(name="success",location="/pages/system/userlist.html",type="redirect")})
    public String save() {
        userService.save(getModel(),roleIds);
        return SUCCESS;
    }
    
    
    @Action("userAction_findByPage")
    public String findByPage() {
        Pageable pageable = new PageRequest(page-1, rows);
       Page<User> page= userService.findByPage(pageable);
        pageToJSON(page, new String[] {"roles"});
        
        return NONE;
        
    }

}
  
