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
import com.zsw.entity.Domitory;
import com.zsw.entity.Student;
import com.zsw.service.BuildService;
import com.zsw.service.DomitoryService;
import com.zsw.service.StudentService;
import com.zsw.service.impl.BuildServiceImpl;
import com.zsw.service.impl.DomitoryServiceImpl;
import com.zsw.service.impl.StudentServiceImpl;

@WebServlet("/domiservlet")
public class DomitoryServlet extends HttpServlet{
	DomitoryService service= new DomitoryServiceImpl();
	StudentService stuservice=new StudentServiceImpl();
	BuildService bservice=new BuildServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		}else if(domit.equals("querybyid")) {
			querybyid(req,resp);
		}else if(domit.equals("isexchange")) {
			isexchange(req,resp);
		}else if(domit.equals("exchange")) {
			exchange(req,resp);
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
		JSONObject json =new JSONObject();
		int num=service.Checkuser(buildid, name);
		if(num==0) {
			service.add(domi);
			json.put("code",200);
			resp.getWriter().print(json);			
		}else {
			json.put("code",100);
			resp.getWriter().print(json);
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		JSONObject json =new JSONObject();
		int num=service.SelectStu(id);
		if(num>0) {
			json.put("code",100);
			resp.getWriter().print(json);
		}else {
			service.del(id);
			json.put("code",200);
			resp.getWriter().print(json);
		}
	}
	private void queryad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Domitory domi=service.QuerySelectById(id);
		String json=JSON.toJSONString(domi);
	    resp.getWriter().print(json);		
	}
	private void updatead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
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
		Boolean a=service.update(id, domi);
		resp.getWriter().print(JSON.toJSONString(a));		
	}
	private void querybyid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int buildid=Integer.parseInt(req.getParameter("buildid"));
		
		List<Domitory> domis=service.QueryAllBybdID(buildid);
		String json=JSON.toJSONString(domis);
	    resp.getWriter().print(json);
	}
	private void isexchange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		JSONObject json=new JSONObject();
		int i=stuservice.Checkuser(username);
		if(i==0) {
			json.put("code",300);
			resp.getWriter().print(json);
		}else {
			Student stu=stuservice.QuerySelectByuser(username);
			
			if(stu.getStudent_State().equals("入住")) {
				json.put("code",200);
				resp.getWriter().print(json);
			}else {
				json.put("code",100);
				resp.getWriter().print(json);
			}
		}
		
	}
	private void exchange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("opt"));
		int bid=Integer.parseInt(req.getParameter("build"));
		String username=req.getParameter("username");
		
		Student stu=stuservice.QuerySelectByuser(username);
		
		Domitory domi=service.QuerySelectById(id);
		int num=Integer.parseInt(domi.getDomitory_Number());
		
		int snum=stuservice.selectnum(id);
		
		Building build = bservice.queryselect(bid);
		String bname=build.getBuilding_Name();
		String bnamesex=bname.substring(bname.length()-1,bname.length());
		
		JSONObject json=new JSONObject();
		if(bnamesex.equals(stu.getStudent_Sex())) {
			if(id==stu.getStudent_DomitoryID()) {
				json.put("code",300);
				resp.getWriter().print(json);
			}else {
				if(num>snum) {
					stuservice.move(id, "入住", username);
					json.put("code",200);
					resp.getWriter().print(json);
				}else {
					json.put("code",100);
					resp.getWriter().print(json);
				}
			}
		}else {
			json.put("code",400);
			resp.getWriter().print(json);
		}
		
		
	}
}
