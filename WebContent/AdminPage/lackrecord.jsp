<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String buildID=request.getParameter("buildID");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>缺寝记录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<style type="text/css">
	.form1{
		margin-top:10px;
	}
</style>
</head>
<body>
<div class="layui-row form1">
	<label class="layui-form-label">查询：</label>
	<input type="hidden" name="buildID" value="<%=buildID%>" />
	<div class="layui-input-inline">
		<select name="domi" lay-verify="" class="domi">
		  <option value="">请选择寝室</option>			  
		</select>
	</div>
	<div class="layui-input-inline">
		<select name="opt" lay-verify="">
		  <option value="">请选择</option>
		  <option value="name">姓名</option>
		  <option value="username">学号</option>
		  <option value="clas">班级</option>
		</select>
	</div>
	<div class="layui-input-inline">
      <input type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <button class="layui-btn">查询</button>
</div>
<table id="lack" lay-filter="test" lay-data="{id: 'idTest'}"></table>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table,
  	  $ = layui.jquery;
  $.ajax({
	  type: "post",
      url: "<%=basePath%>domiservlet?domit=selname&buildID=<%=buildID%>",
      dataType: "json",
      success: function (data) {
    	  var option="";
    	  for(var i in data){
    		  option += '<option value="'+data[i]+'">'+data[i]+'</option>';
    	  }
    	  $(".domi").append(option);
      }
  });
  var tableIns=table.render({
    elem: '#lack'
    ,url: '<%=basePath%>logservlet?logs=tearecord&buildID=<%=buildID%>' //数据接口
    ,method:'post'		
    ,page: true //开启分页
    ,limit:5
    ,limits:[5,10,20]
    ,cols: [[ //表头
      {field: 'domitory_Name', title: '寝室号', fixed: 'left'}
      ,{field: 'student_Name', title: '姓名' }
      ,{field: 'student_Sex', title: '性别' }
      ,{field: 'student_Class', title: '班级' }
      ,{field: 'log_Date', title: '日期', sort:true} 
      ,{field: 'log_Remark', title: '备注' }
      ,{field: 'teacher_Name', title: '管理员' }
    ]]
  });
  $('.layui-btn').click(function(){
	  var domi=$('select[name=domi]').val();
	  var opt=$('select[name=opt]').val();
	  var title=$('input[name=title]').val();
	  tableIns.reload({
		  where:{
			  domi:domi,
			  opt:opt,
			  title:title
		  }
		  ,page:{
			  curr:1
		  }
	  })
  })
});
</script>
</body>
</html>