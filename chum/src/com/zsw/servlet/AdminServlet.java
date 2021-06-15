package com.zsw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Admin;
import com.zsw.service.AdminService;
import com.zsw.service.impl.AdminServiceImpl;

@WebServlet("/adminservlet")
public class AdminServlet extends HttpServlet{
	AdminService service = new AdminServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String admin=req.getParameter("admin");
		if(admin.equals("query")) {
			query(req,resp);
		}else if(admin.equals("update")) {
			update(req,resp);
		}
		
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
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
		JSONObject json=new JSONObject();
		int id=service.CheckLogin(username, password1);
		if(id==0) {
			json.put("code", 100);
			resp.getWriter().print(json);
		}else {
			service.Update(id, admin);
			json.put("code", 200);
			resp.getWriter().print(json);
		}
	}
}
