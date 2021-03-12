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
import com.zsw.entity.Building;
import com.zsw.service.BuildService;
import com.zsw.service.TBService;
import com.zsw.service.impl.BuildServiceImpl;
import com.zsw.service.impl.TBServiceImpl;

@WebServlet("/tbservlet")
public class TBServlet extends HttpServlet{
	TBService tbservice=new TBServiceImpl();
	BuildService bdservice=new BuildServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		String tb=req.getParameter("tb");
		if(tb.equals("buildopen")) {
			buildopen(req,resp);
		}
		
	}
	private void buildopen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id= (int)session.getAttribute("id");
		//根据老师id查询buildID
		List<Integer> bs=tbservice.QueryBulidID(id);
		//根据buildID查询building
		List<Building> bd=bdservice.QuerySelectById(bs);
		String json=JSON.toJSONString(bd);
	    resp.getWriter().print(json);
	}

}
