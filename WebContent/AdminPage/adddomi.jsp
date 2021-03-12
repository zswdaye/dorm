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
<title>添加宿舍</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<style>
	.imation{
		text-align:center;
		color:#393D49;
		font:bold 25px sans-serif;
	}
	.message{
		width:370px;
		margin:10px auto;
	}
</style>
</head>
<body>
<div class="layui-row">
	<div class="layui-col-md12 imation">添加宿舍</div>
</div>
<form class="layui-form message" action="<%=basePath %>domiservlet?domit=add" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">楼栋</label>
	<div class="layui-input-inline">
		<select name="build" lay-verify="required" class="build">
		  <option value="">请选择楼栋</option>		  
		</select>
	</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">寝室号</label>
    <div class="layui-input-inline">
      <input type="text" name="name" required lay-verify="required" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">寝室类型</label>
    <div class="layui-input-inline">
      <input type="text" name="type" required lay-verify="required" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">人数</label>
    <div class="layui-input-inline">
      <input type="number" name="number" required  lay-verify="required|number" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">电话</label>
    <div class="layui-input-inline">
      <input type="tel" name="tel" required lay-verify="required|phone" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">添加</button>
      <a class="layui-btn layui-btn-primary" href="managedomi.jsp">返回上一页</a>
    </div>
  </div>
</form>
 
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
layui.use('form', function(){
  var form = layui.form,
  	  $ = layui.jquery;
  $.ajax({
	  type: "post",
      url: "<%=basePath%>buildservlet?build=query",
      dataType: "json",
      success: function (data) {
    	  var option="";
    	  for(var i in data){
    		  option += '<option value="'+data[i].building_ID+'">'+data[i].building_Name+'</option>';
    	  }
    	  $(".build").append(option);
    	  form.render('select');
      }
  })
});
</script>
</body>
</html>