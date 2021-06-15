package com.zsw.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Building;
import com.zsw.entity.Domitory;
import com.zsw.entity.Student;
import com.zsw.service.BuildService;
import com.zsw.service.DomitoryService;
import com.zsw.service.StudentService;
import com.zsw.service.TBService;
import com.zsw.service.impl.BuildServiceImpl;
import com.zsw.service.impl.DomitoryServiceImpl;
import com.zsw.service.impl.StudentServiceImpl;
import com.zsw.service.impl.TBServiceImpl;

@WebServlet("/buildservlet")
public class BuildServlet extends HttpServlet{
	BuildService service=new BuildServiceImpl();
	DomitoryService dservice=new DomitoryServiceImpl();
	StudentService sservice=new StudentServiceImpl();
	TBService tservice=new TBServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		String build=req.getParameter("build");
		if(build.equals("query")) {
			query(req,resp);
		}else if(build.equals("queryex")) {
			queryex(req,resp);
		}else if(build.equals("queryall")) {
			queryall(req,resp);
		}else if(build.equals("add")) {
			add(req,resp);
		}else if(build.equals("del")) {
			del(req,resp);
		}else if(build.equals("update")) {
			update(req,resp);
		}else if(build.equals("queryad")) {
			queryad(req,resp);
		}
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Building> builds=service.QuerySelectAll();
		String json=JSON.toJSONString(builds);		
	    resp.getWriter().print(json);
	}
	private void queryex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		Student stu= sservice.QuerySelectByuser(username);
		Domitory domit=dservice.QuerySelectById(stu.getStudent_DomitoryID());
		String dname=domit.getDomitory_Name();
		Building build=service.queryselect(domit.getDomitory_BuildingID());
		String bname=build.getBuilding_Name();
		List<Object> list=new ArrayList<>();
		list.add(stu);
		list.add(bname);
		list.add(dname);
		String json=JSON.toJSONString(list);
		resp.getWriter().print(json);
	}
	private void queryall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		String name=req.getParameter("name");
		
		List<Building> builds1=service.QuerySelectByName(name);
		List<Building> builds2=service.QuerySelectByNamePage(name, page, limit);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", builds1.size());
		json.put("data", builds2);
		
	    resp.getWriter().print(json);
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title=req.getParameter("title");
		String desc=req.getParameter("desc");
		Building build=new Building();
		build.setBuilding_Name(title);
		build.setBuilding_Introduction(desc);
		JSONObject json=new JSONObject();
		int num=service.Checkname(title);
		if(num==0) {
			service.add(build);
			json.put("code",200);
			resp.getWriter().print(json);
		}else {
			json.put("code",100);
			resp.getWriter().print(json);
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		int num=service.SelectStu(id);
		JSONObject json=new JSONObject();
		if(num>0) {
			json.put("code",100);
			resp.getWriter().print(json);
		}else {
			dservice.delbybid(id);
			tservice.delbybid(id);
			service.del(id);
			json.put("code",200);
			resp.getWriter().print(json);
		}
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("title");
		String intro=req.getParameter("desc");
		int num=service.Checkname(name);
		Building build1=service.queryselect(id);
		
		Building build2=new Building();
		build2.setBuilding_Name(name);
		build2.setBuilding_Introduction(intro);
		
		JSONObject json =new JSONObject();
		if(name.equals(build1.getBuilding_Name())|| num==0) {
			service.update(id, build2);
			json.put("code",200);
			resp.getWriter().print(json);
		}else{
			json.put("code",100);
			resp.getWriter().print(json);
		}
	}
	private void queryad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Building build=service.queryselect(id);
		String json=JSON.toJSONString(build);		
	    resp.getWriter().print(json);
	}
}
