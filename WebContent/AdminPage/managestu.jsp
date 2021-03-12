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
<title>学生管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<style type="text/css">
	.form1{
		margin:10px 0 0 10px;
	}
	.add{
		float:left;
	}
	.layui-form-label{
		width:50px;
	}
</style>
</head>
<body>
<div class="layui-row form1">
	<a class="layui-btn add" href="addstu.jsp">添加学生</a>
	<label class="layui-form-label">查询：</label>
	<div class="layui-input-inline">
		<select name="sta" lay-verify="">
		  <option value="">请选择</option>
		  <option value="入住">入住</option>
		  <option value="未入住">未入住</option>
		  <option value="迁出">迁出</option>
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
<table id="lack" lay-filter="test"></table>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="detail">修改</a>
	<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table,
  	  $ = layui.jquery;
  
  var tableIns=table.render({
    elem: '#lack'
    ,url: '<%=basePath%>studentservlet?stu=queryall' //数据接口
    ,method:'get'		
    ,page: true //开启分页
    ,limit:5
    ,limits:[5,10,20]
    ,cols: [[ //表头
   	   {field: 'student_ID', title: 'id',sort:true, fixed: 'left' }
      ,{field: 'student_Username', title: '学号'}
      ,{field: 'student_Name', title: '姓名'}
      ,{field: 'student_Sex', title: '性别' }
      ,{field: 'student_Class', title: '班级'} 
      ,{field: 'student_State', title: '状态' }
      ,{fixed: 'right', title:'操作', toolbar: "#barDemo", width:150}
    ]]
  });
  table.on('tool(test)',function(obj){
	  var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
      var tr = obj.tr; //获得当前行 tr 的DOM对象
	  if(layEvent === 'del'){
		  layer.confirm('真的删除姓名为：'+data.student_Name+"的学生吗?", function(index){
			  $.ajax({
				  url:'<%=basePath%>studentservlet?stu=del',
				  type:'get',
				  data:{'id':data.student_ID},
				  success:function(suc){
					  if(suc){
						  obj.del();
						  layer.msg("删除成功",{icon:1});
					  }else{
						  layer.msg("删除失败",{icon:5});
					  }					  
				  }
			  });
			  table.reload('lack');
			  layer.close(index);
		  });
	  }else if(layEvent === 'detail'){
		  layer.open({
			  type:2,
			  title:"修改管理员",
			  area:['500px','570px'],
			  content:'updatestu.jsp?id='+data.student_ID+'',
			  
			  end:function(){
				  table.reload('lack');
			  }
		  })
	  }
  });
  //查询
  $('.layui-btn').click(function(){
	  var sta=$('select[name=sta]').val();
	  var opt=$('select[name=opt]').val();
	  var title=$('input[name=title]').val();
	  tableIns.reload({
		  where:{
			  sta:sta,
			  opt:opt,
			  title:title
		  }
		  ,page:{
			  curr:1
		  }
	  })
  });
});
</script>
</body>
</html>