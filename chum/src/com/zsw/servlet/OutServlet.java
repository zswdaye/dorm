package com.zsw.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Out;
import com.zsw.entity.Student;
import com.zsw.service.OutService;
import com.zsw.service.StudentService;
import com.zsw.service.impl.OutServiceImpl;
import com.zsw.service.impl.StudentServiceImpl;

@WebServlet("/outservlet")
public class OutServlet extends HttpServlet{
	OutService service=new OutServiceImpl();
	StudentService stuservice=new StudentServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String out=req.getParameter("out");
		if(out.equals("queryall")) {
			queryall(req,resp);
		}else if(out.equals("isout")) {
			isout(req,resp);
		}else if(out.equals("add")) {
			add(req,resp);
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
	private void isout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		Student stu=stuservice.QuerySelectByuser(username);
		JSONObject json=new JSONObject();
		if(stu!=null) {
			if(stu.getStudent_State().equals("入住")) {
				json.put("code",200);
				resp.getWriter().print(json);
			}else {
				json.put("code",100);
				resp.getWriter().print(json);
			}
		}else {
			json.put("code",300);
			resp.getWriter().print(json);
		}
		
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String desc=req.getParameter("desc");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date=df.format(new Date());
		
		Student stu=stuservice.QuerySelectByuser(username);
		
		Out out=new Out();
		out.setOut_StudentID(stu.getStudent_ID());
		out.setOut_Date(date);
		out.setOut_Remark(desc);
		
		stuservice.move(0, "迁出", username);		
		boolean a=service.add(out);
		resp.getWriter().print(JSON.toJSONString(a));
	}
}
