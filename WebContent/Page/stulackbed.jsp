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
<title>缺寝记录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>

<table id="lack" lay-filter="test"></table>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#lack'
    ,url: '<%=basePath%>logservlet?logs=stulack' //数据接口
    ,method:'get'		
    ,page: true //开启分页
    ,limit:5
    ,limits:[5,10,20]
    ,cols: [[ //表头
      {field: 'domitory_Name', title: '寝室号', fixed: 'left'}
      ,{field: 'student_Name', title: '姓名' }
      ,{field: 'student_Sex', title: '性别' }
      ,{field: 'log_Date', title: '日期', sort:true} 
      ,{field: 'log_Remark', title: '备注' }
      ,{field: 'teacher_Name', title: '登记者'}
    ]]
  });
  
});
</script>
</body>
</html>