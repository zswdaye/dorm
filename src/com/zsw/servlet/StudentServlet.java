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
import com.zsw.entity.Student;
import com.zsw.service.StudentService;
import com.zsw.service.impl.StudentServiceImpl;

@WebServlet("/studentservlet")
public class StudentServlet extends HttpServlet{
	StudentService service=new StudentServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
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
		}
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id=(int)session.getAttribute("id");
		Student student=service.QuerySelectById(id);
		String json=JSON.toJSONString(student);
	    resp.getWriter().print(json);
//		req.setAttribute("student", student);
//		req.getRequestDispatcher("Page/stuupdate.jsp").forward(req,resp);
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
		Student student=new Student();
		student.setStudent_Username(username);
		student.setStudent_Password(password2);
		student.setStudent_Name(name);
		student.setStudent_Sex(sex);
		student.setStudent_Class(clas);
		int id=service.CheckLogin(username, password1);
		if(id==0) {
			resp.getWriter().print("<script>alert('原密码错误');window.location.href='Page/stuupdate.jsp';</script>");
		}
		Boolean a=service.update(id, student);
		if(a) {
			resp.getWriter().print("<script>alert('修改成功！');window.location.href='Page/stuupdate.jsp';</script>");
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
		int num=service.Checkuser(username);
		if(num==0) {
			boolean t=service.add(student);
			if(t) {
				resp.getWriter().print("<script>alert('添加成功！');window.location.href='AdminPage/managestu.jsp';</script>");
			}else {
				resp.getWriter().print("<script>alert('添加失败！');window.location.href='AdminPage/managestu.jsp';</script>");
			}
		}else {
			resp.getWriter().print("<script>alert('用户名已存在！');window.location.href='AdminPage/addstu.jsp';</script>");
		}
	}
	private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		boolean a=service.del(id);
				
	    resp.getWriter().print(a);
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
		String password1=req.getParameter("password1");
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
		resp.getWriter().print(a);
	}
}
