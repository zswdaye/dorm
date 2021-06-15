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
import com.zsw.entity.Teacher;
import com.zsw.service.LogService;
import com.zsw.service.TBService;
import com.zsw.service.TeacherService;
import com.zsw.service.impl.LogServiceImpl;
import com.zsw.service.impl.TBServiceImpl;
import com.zsw.service.impl.TeacherServiceImpl;

@WebServlet("/teaservlet")
public class TeacherServlet extends HttpServlet{
	TeacherService service=new TeacherServiceImpl();
	LogService lservice=new LogServiceImpl();
	TBService tservice=new TBServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tea=req.getParameter("tea");
		if(tea.equals("query")) {
			query(req,resp);
		}else if(tea.equals("update")) {
			update(req,resp);
		}else if(tea.equals("queryall")) {
			queryall(req,resp);
		}else if(tea.equals("add")) {
			add(req,resp);
		}else if(tea.equals("del")) {
			del(req,resp);
		}else if(tea.equals("queryad")) {
			queryad(req,resp);
		}else if(tea.equals("updatead")) {
			updatead(req,resp);
		}else if(tea.equals("queryma")) {
			queryma(req,resp);
		}
		
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Teacher teacher=service.QuerySelectById(id);
		String json=JSON.toJSONString(teacher);
	    resp.getWriter().print(json);
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password1=req.getParameter("password1");
		String password2=req.getParameter("password2");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String tel=req.getParameter("phone");
		
		Teacher teacher=new Teacher();
		teacher.setTeacher_Username(username);
		teacher.setTeacher_Password(password2);
		teacher.setTeacher_Name(name);
		teacher.setTeacher_Sex(sex);
		teacher.setTeacher_Tel(tel);
		JSONObject json=new JSONObject();
		int id=service.CheckLogin(username, password1);
		if(id==0) {
			json.put("code", 100);
			resp.getWriter().print(json);
		}else {
			service.update(id, teacher);
			json.put("code", 200);
			resp.getWriter().print(json);
		}
		
	}
	private void queryall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		String opt=req.getParameter("opt");
		String title=req.getParameter("title");
		Teacher teacher=new Teacher();
		if(opt!=null && !opt.trim().isEmpty()) {
			if(opt.equals("name")) {
				teacher.setTeacher_Name(title);;
			}else if(opt.equals("username")) {
				teacher.setTeacher_Username(title);;
			}else if(opt.equals("tel")) {
				teacher.setTeacher_Tel(title);;
			}
		}
		List<Teacher> teas1=service.QuerySelectAll(teacher);
		List<Teacher> teas2=service.QuerySelectAllPage(teacher, page, limit);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", teas1.size());
		json.put("data", teas2);
		
	    resp.getWriter().print(json);
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password1=req.getParameter("password1");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String tel=req.getParameter("phone");
		Teacher teacher=new Teacher();
		teacher.setTeacher_Username(username);
		teacher.setTeacher_Password(password1);
		teacher.setTeacher_Name(name);
		teacher.setTeacher_Sex(sex);
		teacher.setTeacher_Tel(tel);
		JSONObject json =new JSONObject();
		int num=service.Checkuser(username);
		if(num==0) {
			service.add(teacher);
			json.put("code", 200);
			resp.getWriter().print(json);
		}else {
			json.put("code", 100);
			resp.getWriter().print(json);
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		lservice.delbyteaid(id);
		tservice.delbyteaid(id);
		boolean a=service.del(id);
				
	    resp.getWriter().print(JSON.toJSONString(a));
	}
	private void queryad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Teacher teacher=service.QuerySelectById(id);
		String json=JSON.toJSONString(teacher);
	    resp.getWriter().print(json);
	}
	private void updatead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String username=req.getParameter("username");
		String password1=req.getParameter("password1");
		String password2=req.getParameter("password2");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String tel=req.getParameter("phone");
		Teacher teacher=new Teacher();
		teacher.setTeacher_Username(username);
		teacher.setTeacher_Password(password2);
		teacher.setTeacher_Name(name);
		teacher.setTeacher_Sex(sex);
		teacher.setTeacher_Tel(tel);
		Boolean a=service.update(id, teacher);
		resp.getWriter().print(JSON.toJSONString(a));
	}
	private void queryma(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Teacher> teas=service.QuerySelectAlltea();
		String json=JSON.toJSONString(teas);
	    resp.getWriter().print(json);
	}
}
