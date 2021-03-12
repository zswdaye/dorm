package com.zsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.zsw.entity.Admin;
import com.zsw.service.AdminService;
import com.zsw.service.impl.AdminServiceImpl;

@WebServlet("/adminservlet")
public class AdminServlet extends HttpServlet{
	AdminService service = new AdminServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		String admin=req.getParameter("admin");
		if(admin.equals("query")) {
			query(req,resp);
		}else if(admin.equals("update")) {
			update(req,resp);
		}
		
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id=(int)session.getAttribute("id");
		Admin admin=service.QuerySelectById(id);
		String json=JSON.toJSONString(admin);
	    resp.getWriter().print(json);
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password1=req.getParameter("password1");
		String password2=req.getParameter("password2");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String tel=req.getParameter("phone");
		Admin admin=new Admin(password2,name,sex,tel);
		int id=service.CheckLogin(username, password1);
		if(id==0) {
			resp.getWriter().print("<script>alert('原密码错误');window.location.href='Page/adupdate.jsp';</script>");
		}
		Boolean a=service.Update(id, admin);
		if(a) {
			resp.getWriter().print("<script>alert('修改成功！');window.location.href='Page/adupdate.jsp';</script>");
		}
	}
}
