<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>迁出记录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>

<table id="outrecord" lay-filter="test"></table>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table,
  	  $ = layui.jquery;
  
  var tableIns=table.render({
    elem: '#outrecord'
    ,url: '<%=basePath%>outservlet?out=queryall' //数据接口
    ,method:'get'		
    ,page: true //开启分页
    ,limit:5
    ,limits:[5,10,20]
    ,cols: [[ //表头
   	   {field: 'out_Date', title: '日期'}
      ,{field: 'student_Name', title: '姓名'}
      ,{field: 'student_Sex', title: '性别' }
      ,{field: 'student_Class', title: '班级'} 
      ,{field: 'out_Remark', title: '备注' }
    ]]
  });

});
</script>
</body>
</html>