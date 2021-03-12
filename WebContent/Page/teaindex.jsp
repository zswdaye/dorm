<%@page import="com.zsw.entity.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>校园宿舍管理系统</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<% Teacher teacher=(Teacher)request.getAttribute("teacher"); %>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">校园宿舍管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">        
          <%=teacher.getTeacher_Name() %>
      </li>
      <li class="layui-nav-item"><a href="login.jsp">退出登录</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">  
      	<li class="layui-nav-item"><a href="javascript:;" data-src="Page/teaselect.jsp?select=manage">学生缺寝管理</a></li>
      	<li class="layui-nav-item"><a href="javascript:;" data-src="Page/teaselect.jsp?select=record">学生缺寝记录</a></li>
        <li class="layui-nav-item"><a href="javascript:;" data-src="Page/teaupdate.jsp">个人信息</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe frameborder="0" scrolling="yes" style="width: 100%" src="" id="aa"></iframe>
  </div>
  
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element,
  	  $ = layui.jquery;
  $(function(){	
		//获取src值
      $(".layui-nav-item a").on("click",function(){
          var address =$(this).attr("data-src");
          $("iframe").attr("src",address);
      });
      var frame = $("#aa");
	
      var frameheight = $(".layui-body").height()-5;
      frame.css("height",frameheight);
  });
  
});
</script>
</body>
</html>