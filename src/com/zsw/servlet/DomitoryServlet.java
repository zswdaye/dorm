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
import com.zsw.entity.Domitory;
import com.zsw.service.DomitoryService;
import com.zsw.service.impl.DomitoryServiceImpl;

@WebServlet("/domiservlet")
public class DomitoryServlet extends HttpServlet{
	DomitoryService service= new DomitoryServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		String domit=req.getParameter("domit");
		if(domit.equals("selname")) {
			selname(req,resp);
		}else if(domit.equals("queryall")) {
			queryall(req,resp);
		}else if(domit.equals("add")) {
			add(req,resp);
		}else if(domit.equals("del")) {
			del(req,resp);
		}else if(domit.equals("queryad")) {
			queryad(req,resp);
		}else if(domit.equals("updatead")) {
			updatead(req,resp);
		}
	}
	private void selname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int buildID=Integer.parseInt(req.getParameter("buildID"));
		List<String> dnames=service.QuerySelectBybdID(buildID);
		String json=JSON.toJSONString(dnames);
	    resp.getWriter().print(json);
	}
	private void queryall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		String build=req.getParameter("build");
		String opt=req.getParameter("opt");
		String title=req.getParameter("title");
		
		Domitory domi=new Domitory();	
		if(build!=null && !build.trim().isEmpty()) {
			int bid=Integer.parseInt(build);
			domi.setDomitory_BuildingID(bid);
		}else {
			domi.setDomitory_BuildingID(0);
		}
		if(opt!=null && !opt.trim().isEmpty()) {
			if(opt.equals("name")) {
				domi.setDomitory_Name(title);
			}else if(opt.equals("tel")) {
				domi.setDomitory_Tel(title);
			}
		}
		List<Domitory> domis1=service.QueryAll(domi);
		List<Domitory> domis2=service.QueryAllPage(domi, page, limit);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", domis1.size());
		json.put("data", domis2);
		
	    resp.getWriter().print(json);
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int buildid=Integer.parseInt(req.getParameter("build"));
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		String number=req.getParameter("number");
		String tel=req.getParameter("tel");
		Domitory domi=new Domitory();
		domi.setDomitory_BuildingID(buildid);
		domi.setDomitory_Name(name);
		domi.setDomitory_Type(type);
		domi.setDomitory_Number(number);
		domi.setDomitory_Tel(tel);
		int num=service.Checkuser(buildid, name);
		if(num==0) {
			boolean t=service.add(domi);
			if(t) {
				resp.getWriter().print("<script>alert('添加成功！');window.location.href='AdminPage/managedomi.jsp';</script>");
			}else {
				resp.getWriter().print("<script>alert('添加失败！');window.location.href='AdminPage/managedomi.jsp';</script>");
			}
		}else {
			resp.getWriter().print("<script>alert('该栋已存在此寝室号！');window.location.href='AdminPage/adddomi.jsp';</script>");
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		boolean a=service.del(id);
				
	    resp.getWriter().print(a);
	}
	private void queryad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Domitory domi=service.QuerySelectById(id);
		String json=JSON.toJSONString(domi);
	    resp.getWriter().print(json);		
	}
	private void updatead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		int buildid=Integer.parseInt(req.getParameter("buildid"));
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		String number=req.getParameter("number");
		String tel=req.getParameter("tel");
		Domitory domi=new Domitory();
		domi.setDomitory_BuildingID(buildid);
		domi.setDomitory_Name(name);
		domi.setDomitory_Type(type);
		domi.setDomitory_Number(number);
		domi.setDomitory_Tel(tel);
		Boolean a=service.update(id, domi);
		resp.getWriter().print(a);
			
	}
}
