package com.itheima.bos.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itheima.bos.dao.UserDao;
import com.itheima.bos.domain.system.User;

/**  
 * ClassName:UserRealm <br/>  
 * Function:  <br/>  
 * Date:     Dec 12, 2017 3:23:59 PM <br/>       
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserDao dao;
   //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection arg0) {
       SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo();
       info.addStringPermission("findByPage");
       info.addStringPermission("add");
       info.addStringPermission("delCourier");
       info.addRole("admin");
        // TODO Auto-generated method stub  
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
  
