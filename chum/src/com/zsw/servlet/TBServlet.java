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
import com.zsw.entity.Building;
import com.zsw.entity.Teacher;
import com.zsw.service.BuildService;
import com.zsw.service.TBService;
import com.zsw.service.TeacherService;
import com.zsw.service.impl.BuildServiceImpl;
import com.zsw.service.impl.TBServiceImpl;
import com.zsw.service.impl.TeacherServiceImpl;

@WebServlet("/tbservlet")
public class TBServlet extends HttpServlet{
	TBService tbservice=new TBServiceImpl();
	BuildService bdservice=new BuildServiceImpl();
	TeacherService teaservice=new TeacherServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tb=req.getParameter("tb");
		if(tb.equals("buildopen")) {
			buildopen(req,resp);
		}else if(tb.equals("querytea")){
			querytea(req,resp);
		}else if(tb.equals("add")) {
			add(req,resp);
		}else if(tb.equals("del")) {
			del(req,resp);
		}
		
	}
	private void buildopen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		//根据老师id查询buildID
		List<Integer> bs=tbservice.QueryBulidID(id);
		//根据buildID查询building
		List<Building> bd=bdservice.QuerySelectById(bs);
		String json=JSON.toJSONString(bd);
	    resp.getWriter().print(json);
	}
	private void querytea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		List<Integer> tids=tbservice.querybybid(id);
		List<Teacher> teas=tbservice.QuerySelectById(tids);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", teas.size());
		json.put("data", teas);
		
	    resp.getWriter().print(json);
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bid=Integer.parseInt(req.getParameter("bid"));
		int tid=Integer.parseInt(req.getParameter("tid"));
		List<Integer> tids= tbservice.querybybid(bid);
		boolean a=tids.contains(tid);
		if(a) {
			resp.getWriter().print(JSON.toJSONString(false));
		}else {			
			boolean b=tbservice.add(tid, bid);
			resp.getWriter().print(JSON.toJSONString(b));
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bid=Integer.parseInt(req.getParameter("bid"));
		int tid=Integer.parseInt(req.getParameter("tid"));
		boolean a=tbservice.del(tid, bid);
		resp.getWriter().print(JSON.toJSONString(a));
	}
}
