package com.zsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String Type=req.getParameter("Type");
		
		if(Type.equals("系统管理员")) {
			id=adservice.CheckLogin(username, password);
			Admin admin=adservice.QuerySelectById(id);
			req.setAttribute("admin", admin);
			if(id!=0) {
				req.getRequestDispatcher("Page/adindex.jsp").forward(req,resp);
			}
		}else if(Type.equals("楼栋管理员")) {
			id=teaservice.CheckLogin(username, password);
			Teacher teacher=teaservice.QuerySelectById(id);
			req.setAttribute("teacher", teacher);
			if(id!=0) {
				req.getRequestDispatcher("Page/teaindex.jsp").forward(req,resp);
			}
		}else if(Type.equals("学生")) {
			id=stuservice.CheckLogin(username, password);
			Student student=stuservice.QuerySelectById(id);
			req.setAttribute("student", student);
			if(id!=0) {
				req.getRequestDispatcher("Page/stuindex.jsp").forward(req,resp);
			}
		}
		session.setAttribute("id", id);
		if(id==0){
			resp.getWriter().print("<script>alert('用户名或密码错误');window.location.href='login.jsp';</script>");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
