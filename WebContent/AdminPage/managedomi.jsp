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
<title>宿舍管理</title>
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
	<a class="layui-btn add" href="adddomi.jsp">添加宿舍</a>
	<label class="layui-form-label">查询：</label>
	<div class="layui-input-inline">
		<select name="build" lay-verify="" class="build">
		  <option value="">全部楼栋</option>
		  
		</select>
	</div>
	<div class="layui-input-inline">
		<select name="opt" lay-verify="">
		  <option value="">请选择</option>
		  <option value="name">寝室号</option>
		  <option value="tel">电话</option>
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
      }
  });
  var tableIns=table.render({
    elem: '#lack'
    ,url: '<%=basePath%>domiservlet?domit=queryall' //数据接口
    ,method:'get'		
    ,page: true //开启分页
    ,limit:5
    ,limits:[5,10,20]
    ,cols: [[ //表头
   	   {field: 'domitory_ID', title: 'id',sort:true, fixed: 'left' }     
      ,{field: 'domitory_Name', title: '寝室号'}
      ,{field: 'domitory_Type', title: '寝室类型' }
      ,{field: 'domitory_Number', title: '人数'} 
      ,{field: 'domitory_Tel', title: '电话' }
      ,{field: 'building_Name', title: '楼栋'}
      ,{fixed: 'right', title:'操作', toolbar: "#barDemo", width:150}
    ]]
  });
  table.on('tool(test)',function(obj){
	  var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
      var tr = obj.tr; //获得当前行 tr 的DOM对象
	  if(layEvent === 'del'){
		  layer.confirm('真的删除寝室号为：'+data.domitory_Name+"的寝室吗?", function(index){
			  $.ajax({
				  url:'<%=basePath%>domiservlet?domit=del',
				  type:'get',
				  data:{'id':data.domitory_ID},
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
			  title:"修改宿舍",
			  area:['500px','570px'],
			  content:'updatedomi.jsp?id='+data.domitory_ID+'',
			  
			  end:function(){
				  table.reload('lack');
			  }
		  })
	  }
  });
  //查询
  $('.layui-btn').click(function(){
	  var build=$('select[name=build]').val();
	  var opt=$('select[name=opt]').val();
	  var title=$('input[name=title]').val();
	  tableIns.reload({
		  where:{
			  build:build,
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