package com.itheima.bos.service.realm;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itheima.bos.dao.PermissionRepositories;
import com.itheima.bos.dao.RoleRepositories;
import com.itheima.bos.dao.UserRepositories;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;



/**  
 * ClassName:UserRealm <br/>  
 * Function:  <br/>  
 * Date:     Dec 12, 2017 3:23:59 PM <br/>       
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserRepositories dao;
    
    @Autowired
    private RoleRepositories rDao;
    
    @Autowired 
    private PermissionRepositories pDao;
   //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection arg0) {
       SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo();
       //取出认证的用户
       User user = (User) SecurityUtils.getSubject().getPrincipal();
       //判断是否为admin  是 添加所有权限  不是根据ID查找权限添加进去
       if (user.getUsername().equals("admin")) {
           List<Role> all = rDao.findAll();
          for (Role r : all) {
            info.addRole(r.getKeyword());
        }
          List<Permission> permissions = pDao.findAll();
          for (Permission permission : permissions) {
              info.addStringPermission(permission.getKeyword());
          }
    }else {
        List<Role> all = rDao.findByUserId(user.getId());
        for (Role r : all) {
          info.addRole(r.getKeyword());
      }
        List<Permission> permissions = pDao.findByUserId(user.getId());
        for (Permission permission : permissions) {
            info.addStringPermission(permission.getKeyword());
        }
    }
     Cache<Object, AuthenticationInfo> cache = getAuthenticationCache();
    if (cache==null) {
        System.out.println("没有缓存");
    }else {
        System.out.println("有缓存");
    }
        return info;
    }
    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken arg0) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) arg0;
        String username = token.getUsername();
       User user= dao.findByUsername(username);
       
       if (user==null) {
        return null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return info;
    }

}
  
