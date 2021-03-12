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
<title>个人信息</title>
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
	<div class="layui-col-md12 imation">个人信息</div>
</div>
<form class="layui-form message" action="<%=basePath %>teaservlet?tea=update" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input type="text" name="username" required  lay-verify="required" class="layui-input" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">原密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password1" required lay-verify="required" placeholder="请输入原密码" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">新密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password2" required lay-verify="required" placeholder="请输入新密码" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-inline">
      <input type="text" name="name" required  lay-verify="required" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="男" title="男">
      <input type="radio" name="sex" value="女" title="女">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系方式</label>
    <div class="layui-input-inline">
      <input type="tel" name="phone" required lay-verify="required|phone" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
      <button id="reset" class="layui-btn layui-btn-primary">重置</button>
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
      url: "<%=basePath%>teaservlet?tea=query",
      dataType: "json",
      success: function (data) {
        $("input[name=username]").val(data.teacher_Username);
        $("input[name=name]").val(data.teacher_Name);
        $("input[name=phone]").val(data.teacher_Tel);
        $("input[name=sex][value=男]").attr("checked", data.teacher_Sex == "男" ? true : false);
        $("input[name=sex][value=女]").attr("checked", data.teacher_Sex == "女" ? true : false);        
        form.render(); //更新全部         
      }
   });
  $("#reset").click(function () {
    $("input[name=password1]").val("");
    $("input[name=password2]").val("");
    form.render();
  })
});
</script>
</body>
</html>