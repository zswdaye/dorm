package com.zsw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Log;
import com.zsw.entity.Student;
import com.zsw.service.LogService;
import com.zsw.service.impl.LogServiceImpl;

@WebServlet("/logservlet")
public class LogServlet extends HttpServlet{
	LogService service=new LogServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		
		String log=req.getParameter("logs");
		if(log.equals("stulack")) {
			stulack(req,resp);
		}else if(log.equals("tearecord")) {
			tearecord(req,resp);
		}else if(log.equals("addlog")) {
			addlog(req,resp);
		}
		
	}
	private void stulack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		HttpSession session = req.getSession();
		int id= (int)session.getAttribute("id");
		
		List<Log> logs1 = service.QuerySelectById(id);
		List<Log> logs2 = service.QuerySelectByPage(id, page, limit);
		
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", logs1.size());
		json.put("data", logs2);
		
	    resp.getWriter().print(json);
	}
	private void tearecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int buildID=Integer.parseInt(req.getParameter("buildID"));
		String domi=req.getParameter("domi");
		String opt=req.getParameter("opt");
		String title=req.getParameter("title");
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		Student stu=new Student();
		stu.setDomitory_Name(domi);
		if(opt!=null && !opt.trim().isEmpty()) {
			if(opt.equals("name")) {
				stu.setStudent_Name(title);
			}else if(opt.equals("username")) {
				stu.setStudent_Username(title);
			}else if(opt.equals("clas")) {
				stu.setStudent_Class(title);
			}
		}		
		List<Log> logs1=service.QuerySelectbdById(buildID, stu);
		List<Log> logs2=service.QuerySelectbdByPage(buildID, stu, page, limit);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", logs1.size());
		json.put("data", logs2);
		
	    resp.getWriter().print(json);
	}
	private void addlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int studentid=Integer.parseInt(req.getParameter("studentid"));
		HttpSession session = req.getSession();
		int teaid= (int)session.getAttribute("id");
		String date=req.getParameter("date");
		String remark=req.getParameter("remark");
		
		Log log =new Log();
		log.setLog_StudentID(studentid);
		log.setLog_TeacherID(teaid);
		log.setLog_Date(date);
		log.setLog_Remark(remark);
		
		Boolean a=service.addLog(log);
		if(a) {
			resp.getWriter().print("<script>alert('登记成功');window.history.go(-2);</script>");
		}else {
			resp.getWriter().print("<script>alert('登记失败');window.history.go(-2);</script>");
		}
	}
}
