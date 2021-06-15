package com.zsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Admin;
import com.zsw.entity.Student;
import com.zsw.entity.Teacher;
import com.zsw.service.AdminService;
import com.zsw.service.StudentService;
import com.zsw.service.TeacherService;
import com.zsw.service.impl.AdminServiceImpl;
import com.zsw.service.impl.StudentServiceImpl;
import com.zsw.service.impl.TeacherServiceImpl;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet{
	AdminService adservice = new AdminServiceImpl();
	TeacherService teaservice=new TeacherServiceImpl();
	StudentService stuservice=new StudentServiceImpl();
	int id=0;
	String name;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String Type=req.getParameter("Type");
		JSONObject json=new JSONObject();
		
		if("系统管理员".equals(Type)) {
			id=adservice.CheckLogin(username, password);			
			if(id!=0) {
				Admin admin=adservice.QuerySelectById(id);
				json.put("code",200);
				json.put("data", admin);
			    resp.getWriter().print(json);
			}
		}else if("宿管员".equals(Type)) {
			id=teaservice.CheckLogin(username, password);			
			if(id!=0) {
				Teacher teacher=teaservice.QuerySelectById(id);
				json.put("code",200);
				json.put("data", teacher);
			    resp.getWriter().print(json);
			}
		}else if("学生".equals(Type)) {
			id=stuservice.CheckLogin(username, password);			
			if(id!=0) {
				Student student=stuservice.QuerySelectById(id);
				if(student.getStudent_State().equals("入住")) {
					json.put("code",200);
					json.put("data", student);
				    resp.getWriter().print(json);
				}else {
					json.put("code",100);
					resp.getWriter().print(json);
				}
				
			}
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
