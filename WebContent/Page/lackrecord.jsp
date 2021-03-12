<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String studentid=request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>缺寝登记</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<style type="text/css">
	p{
		text-align:center;
		color:red;
	}
	ul{
		width:200px;
		margin:10px auto;
	}
	form{
		margin-right:10px;
	}
</style>
</head>
<body>
<div>
	<p>请仔细确认信息是否正确，缺寝登记提交后将不可修改！</p>
	<ul>
      <li>
        <b class="layui-word-aux">学号:</b>
        <h3 class="layui-inline username"></h3>
      </li>
      <li>
        <b class="layui-word-aux">姓名:</b>
        <h3 class="layui-inline name"></h3>
      </li>
      <li>
        <b class="layui-word-aux">性别:</b>
        <h3 class="layui-inline sex"></h3>
      </li>
      <li>
        <b class="layui-word-aux">班级:</b>
        <h3 class="layui-inline clas"></h3>
      </li>
      <li>
        <b class="layui-word-aux">寝室:</b>
        <h3 class="layui-inline dname"></h3>
      </li>
    </ul>
    <form class="layui-form" action="<%=basePath %>logservlet?logs=addlog&studentid=<%=studentid%>" method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">缺寝日期</label>
	    <div class="layui-input-inline">
	      <input type="text" name="date" id="date" required  lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">缺寝备注</label>
	    <div class="layui-input-block">
	      <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
	    </div>
  	  </div>
  	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button type="submit" class="layui-btn submit">提交</button>
	      <button class="layui-btn layui-btn-primary back">返回上一页</button>
	    </div>
	  </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
layui.use(['form', 'laydate'], function(){
	var form = layui.form,
	  $ = layui.jquery,
	  laydate = layui.laydate;
	laydate.render({
	    elem: '#date'
	});
	$.ajax({
      type: "post",
      url: "<%=basePath%>studentservlet?stu=querydo&studentid=<%=studentid%>",
      dataType: "json",
      success: function (data) {
        $(".username").html(data.student_Username);
        $(".name").html(data.student_Name);
        $(".sex").html(data.student_Sex);
        $(".clas").html(data.student_Class);
        $(".dname").html(data.domitory_Name);              
      }
   });
	$(".back").click(function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	})
	
});
</script>
</body>
</html>