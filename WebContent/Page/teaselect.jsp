<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String select=request.getParameter("select");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>选择楼宇</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<style type="text/css">
	.content{
		text-align:center;
	}
	.bulid{
		font:25px bold Serif;
		margin:10px 0;
	}
	a{
		display:block;
	}
</style>
</head>
<body>
<div class="layui-container content">
	<div class="bulid">请选择楼栋</div>
	<div id="dong" name="<%=select%>"></div>
	
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript">
layui.use('element', function(){
	  var element = layui.element,
	  	  $ = layui.jquery;
	  var b=$("#dong").attr("name");
	  $.ajax({
		  type: "post",
	      url: "<%=basePath%>tbservlet?tb=buildopen",
	      dataType: "json",
	      success: function (data) {
	    	  var a="";
	    	  for(var i in data){
	    		  a += '<a href="tea'+b+'.jsp?buildID='+data[i].building_ID+'">'+data[i].building_Name+'</a>'
	    	  }
	    	  $("#dong").html(a);
	      }
	  })
	  
	});	
</script>
</body>
</html>