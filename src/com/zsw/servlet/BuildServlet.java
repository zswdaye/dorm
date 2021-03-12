package com.zsw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Building;
import com.zsw.service.BuildService;
import com.zsw.service.impl.BuildServiceImpl;

@WebServlet("/buildservlet")
public class BuildServlet extends HttpServlet{
	BuildService service=new BuildServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		String build=req.getParameter("build");
		if(build.equals("query")) {
			query(req,resp);
		}
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Building> builds=service.QuerySelectAll();
		String json=JSON.toJSONString(builds);		
	    resp.getWriter().print(json);
	}
	
}
