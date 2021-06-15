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
import com.zsw.entity.Domitory;
import com.zsw.entity.Student;
import com.zsw.service.BuildService;
import com.zsw.service.DomitoryService;
import com.zsw.service.LogService;
import com.zsw.service.OutService;
import com.zsw.service.StudentService;
import com.zsw.service.impl.BuildServiceImpl;
import com.zsw.service.impl.DomitoryServiceImpl;
import com.zsw.service.impl.LogServiceImpl;
import com.zsw.service.impl.OutServiceImpl;
import com.zsw.service.impl.StudentServiceImpl;

@WebServlet("/studentservlet")
public class StudentServlet extends HttpServlet{
	StudentService service=new StudentServiceImpl();
	DomitoryService doservice=new DomitoryServiceImpl();
	BuildService bservice=new BuildServiceImpl();
	LogService lservice=new LogServiceImpl();
	OutService oservice=new OutServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String stu=req.getParameter("stu");
		if(stu.equals("query")) {
			query(req,resp);
		}else if(stu.equals("update")) {
			update(req,resp);
		}else if(stu.equals("studomi")) {
			studomi(req,resp);
		}else if(stu.equals("querydo")) {
			querydo(req,resp);
		}else if(stu.equals("queryall")) {
			queryall(req,resp);
		}else if(stu.equals("add")) {
			add(req,resp);
		}else if(stu.equals("del")) {
			del(req,resp);
		}else if(stu.equals("queryad")){
			queryad(req,resp);
		}else if(stu.equals("updatead")) {
			updatead(req,resp);
		}else if(stu.equals("move")) {
			move(req,resp);
		}else if(stu.equals("queryus")) {
			queryus(req,resp);
		}
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		Student student=service.QuerySelectById(id);
		String json=JSON.toJSONString(student);
	    resp.getWriter().print(json);
	}
	private void querydo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("studentid"));
		Student student=service.QuerySelectdoByid(id);
		String json=JSON.toJSONString(student);
	    resp.getWriter().print(json);
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password1=req.getParameter("password1");
		String password2=req.getParameter("password2");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String clas=req.getParameter("clas");
		
		JSONObject json=new JSONObject();
		
		Student student=new Student();
		student.setStudent_Username(username);
		student.setStudent_Password(password2);
		student.setStudent_Name(name);
		student.setStudent_Sex(sex);
		student.setStudent_Class(clas);
		int id=service.CheckLogin(username, password1);
		if(id==0) {
			json.put("code", 100);
			resp.getWriter().print(json);
		}else {
			service.update(id, student);
			json.put("code", 200);
			resp.getWriter().print(json);
		}
		
	}
	//查找学生和宿舍
	private void studomi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int buildID=Integer.parseInt(req.getParameter("buildID"));
		String domi=req.getParameter("domi");
		String opt=req.getParameter("opt");
		String title=req.getParameter("title");
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		Student stu=new Student();
		stu.setDomitory_Name(domi);
		if(opt!=null && !opt.trim().isEmpty()) {
			if(opt.equals("name")) {
				stu.setStudent_Name(title);
			}else if(opt.equals("username")) {
				stu.setStudent_Username(title);
			}else if(opt.equals("clas")) {
				stu.setStudent_Class(title);
			}
		}		
		List<Student> students1=service.QuerySelectBybdId(buildID, stu);
		List<Student> students2=service.QuerySelectBybdPage(buildID, stu, page, limit);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", students1.size());
		json.put("data", students2);
		
	    resp.getWriter().print(json);
	}
	private void queryall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page=Integer.parseInt(req.getParameter("page")) ;
		int limit=Integer.parseInt(req.getParameter("limit"));
		String sta=req.getParameter("sta");
		String opt=req.getParameter("opt");
		String title=req.getParameter("title");
		Student student=new Student();
		if(sta!=null && !sta.trim().isEmpty()) {
			student.setStudent_State(sta);
		}
		if(opt!=null && !opt.trim().isEmpty()) {
			if(opt.equals("name")) {
				student.setStudent_Name(title);
			}else if(opt.equals("username")) {
				student.setStudent_Username(title);
			}else if(opt.equals("tel")) {
				student.setStudent_Class(title);
			}
		}
		List<Student> students1=service.QuerySelectAll(student);
		List<Student> students2=service.QuerySelectAllPage(student, page, limit);
		JSONObject json= new JSONObject(); 
		json.put("code", 0);
		json.put("msg", "ok");
		json.put("count", students1.size());
		json.put("data", students2);
		
	    resp.getWriter().print(json);
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password1=req.getParameter("password1");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String clas=req.getParameter("clas");
		Student student =new Student();
		student.setStudent_Username(username);
		student.setStudent_Password(password1);
		student.setStudent_Name(name);
		student.setStudent_Sex(sex);
		student.setStudent_Class(clas);
		student.setStudent_State("未入住");
		JSONObject json =new JSONObject();
		int num=service.Checkuser(username);
		if(num==0) {
			service.add(student);
			json.put("code", 200);
			resp.getWriter().print(json);
		}else {
			json.put("code", 100);
			resp.getWriter().print(json);
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
//		lservice.delbystuid(id);
//		oservice.delbystuid(id);
		boolean a=service.del(id);
				
	    resp.getWriter().print(JSON.toJSONString(a));
	}
	private void queryad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Student student=service.QuerySelectById(id);
		String json=JSON.toJSONString(student);
	    resp.getWriter().print(json);
	}
	private void updatead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String username=req.getParameter("username");
		String password2=req.getParameter("password2");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String clas=req.getParameter("clas");
		Student student=new Student();
		student.setStudent_Username(username);
		student.setStudent_Password(password2);
		student.setStudent_Name(name);
		student.setStudent_Sex(sex);
		student.setStudent_Class(clas);

		Boolean a=service.update(id, student);
		resp.getWriter().print(JSON.toJSONString(a));
	}
	private void move(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("opt"));
		int bid=Integer.parseInt(req.getParameter("build"));
		String username=req.getParameter("title");
		
		Domitory domi=doservice.QuerySelectById(id);
		int num=Integer.parseInt(domi.getDomitory_Number());
		
		Building build = bservice.queryselect(bid);
		String bname=build.getBuilding_Name();
		String bnamesex=bname.substring(bname.length()-1,bname.length());
		
		int snum=service.selectnum(id);
		
		Student stu= service.QuerySelectByuser(username);
		
		JSONObject json =new JSONObject();
		if(stu!=null) {
			if(stu.getStudent_State().equals("未入住")) {
				if(bnamesex.equals(stu.getStudent_Sex())){
					if(num>snum) {
						service.move(id, "入住", username);
						json.put("code",200);
						resp.getWriter().print(json);
					}else {
						json.put("code",100);
						resp.getWriter().print(json);
					}
				}else {
					json.put("code",400);
					resp.getWriter().print(json);
				}		
			}else {
				json.put("code",300);
				resp.getWriter().print(json);
			}
		}else {
			json.put("code",500);
			resp.getWriter().print(json);
		}
		
	}
	private void queryus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		Student stu= service.QuerySelectByuser(username);
		String json=JSON.toJSONString(stu);
	    resp.getWriter().print(json);
	}
}
