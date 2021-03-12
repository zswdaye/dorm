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
<title>修改宿舍</title>
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
	<div class="layui-col-md12 imation">修改宿舍</div>
</div>
<form class="layui-form message" action="" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">楼栋</label>
	<div class="layui-input-inline">
		<select name="build" lay-verify="required" class="build" disabled>
		  <option value="">请选择楼栋</option>		  
		</select>
	</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">寝室号</label>
    <div class="layui-input-inline">
      <input type="text" name="name" required lay-verify="required" class="layui-input" readonly>
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
      <button class="layui-btn" lay-submit lay-filter="formDemo">修改宿舍</button>
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
  });
  setTimeout(function(){
	  $.ajax({
	      type: "post",
	      url: "<%=basePath%>domiservlet?domit=queryad&id=<%=id%>",
	      dataType: "json",
	      success: function (data) {
	    	$("option[value="+data.domitory_BuildingID+"]").attr("selected",true);
	        $("input[name=name]").val(data.domitory_Name);
	        $("input[name=type]").val(data.domitory_Type);
	        $("input[name=number]").val(data.domitory_Number);
	        $("input[name=tel]").val(data.domitory_Tel);        
	        form.render(); //更新全部         
	      }
	   });
  }, 500);
  form.on('submit(formDemo)', function(data){
	  var buildid=$("select[name=build]").val();
	  var name=$("input[name=name]").val();
	  var type=$("input[name=type]").val();
	  var number=$("input[name=number]").val();
	  var tel=$("input[name=tel]").val();
	  $.ajax({
		  url:'<%=basePath %>domiservlet?domit=updatead&id=<%=id%>',
		  type:'get',
		  data:{'buildid':buildid,'name':name,'type':type,'number':number,'tel':tel},
		  success:function(suc){
			var inde = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(inde);				  		
		  }
	  });
  });
});
</script>
</body>
</html>