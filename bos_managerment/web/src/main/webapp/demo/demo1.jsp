<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%-- <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <shiro:authenticated>
       您已通过认证
  </shiro:authenticated>
   <hr />
    <!-- 判断当前用户是否用于对应的权限,如有则可以看到标签中的内容 -->
    <shiro:hasPermission name="findByPage">
           您拥有findByPage权限
    </shiro:hasPermission>
    <hr />
    <!-- 判断当前用户是否是某个角色,如是则可以看到标签中的内容 -->
    <shiro:hasRole name="admin">
       您是管理员角色
    </shiro:hasRole>
</body>
</html>