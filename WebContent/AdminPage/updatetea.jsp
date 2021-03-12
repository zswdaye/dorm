<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id=Integer.parseInt(request.getParameter("id"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改楼栋管理员信息</title>
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
	<div class="layui-col-md12 imation">修改楼栋管理员信息</div>
</div>
<form class="layui-form message" action="" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input type="text" name="username" required  lay-verify="required" class="layui-input" readonly>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password1" required lay-verify="required" placeholder="请输入密码" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">重复密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password2" required lay-verify="required|password" placeholder="请重复密码" class="layui-input">
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
    <label class="layui-form-label">联系电话</label>
    <div class="layui-input-inline">
      <input type="tel" name="phone" required lay-verify="required|phone" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
      <a class="layui-btn layui-btn-primary back" href="javascript:;">返回上一页</a>
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
      url: "<%=basePath%>teaservlet?tea=queryad&id=<%=id%>",
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
  form.verify({
	  password:function(value,item){
		  if($("input[name=password1]").val()!==value){
			  return '密码不一致';
		  }
	  }
  });
  form.on('submit(formDemo)', function(data){
	  var username=$("input[name=username]").val();
	  var password1=$("input[name=password1]").val();
	  var password2=$("input[name=password2]").val();
	  var name=$("input[name=name]").val();
	  var sex=$("input[name=sex]").val();
	  var phone=$("input[name=phone]").val();
	  $.ajax({
		  url:'<%=basePath %>teaservlet?tea=updatead&id=<%=id%>',
		  type:'get',
		  data:{'username':username,'password1':password1,'password2':password2,'name':name,'sex':sex,'phone':phone},
		  success:function(suc){	
			  var inde = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			  parent.layer.close(inde);			  
		  }
	  });
  });
  $('.back').click(function(){
	  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	  parent.layer.close(index);
  })
});
</script>
</body>
</html>