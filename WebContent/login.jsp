<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="loginservlet" method="post">
		<select name="Type" id="Type">
           <option value="">请选择</option>
           <option value="系统管理员">系统管理员</option>
           <option value="楼栋管理员">楼栋管理员</option>
           <option value="学生">学生</option>
        </select>
		用户名：<input type="text" name="username" >
		密码：<input type="password" name="password">
		<input type="submit" value="登录">
	</form>
</body>
</html>