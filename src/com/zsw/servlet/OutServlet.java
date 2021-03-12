package com.zsw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Out;
import com.zsw.service.OutService;
import com.zsw.service.impl.OutServiceImpl;

@WebServlet("/outservlet")
public class OutServlet extends HttpServlet{
	OutService service=new OutServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		String out=req.getParameter("out");
		if(out.equals("queryall")) {
			queryall(req,resp);
		}
	}
	private void queryall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		List<Out> outs1=service.QuerySelect();
		List<Out> outs2=service.QuerySelectPage(page, limit);
		
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", outs1.size());
		json.put("data", outs2);
	    resp.getWriter().print(json);
	}
	
}
