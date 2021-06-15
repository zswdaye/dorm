package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.StudentDao;
import com.zsw.entity.Student;
import com.zsw.util.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public int CheckLogin(String username, String password) {
		int id =0;
		DBUtil dbUtil = new DBUtil();
		ResultSet rs =null;
		try {
			String sql="select * from student where Student_Username=? and Student_Password=?";
			Object[] params= {username,password};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				id = rs.getInt("Student_ID");
			}
			return id;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			dbUtil.closeAll();
		}
	}
	//检查是否有相同的学号
	@Override
	public int Checkuser(String username) {
		DBUtil dbUtil = new DBUtil();
		ResultSet rs =null;
	    int num=0;
		try {
			String sql="select count(*) from student where Student_Username=?";
			Object[] params= {username};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				num=rs.getInt(1);
			}
			return num;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			dbUtil.closeAll();
		}
	}
	//根据寝室id查询该寝室有多少学生入住
	@Override
	public int selectnum(int did) {
		DBUtil dbUtil = new DBUtil();
		ResultSet rs =null;
	    int num=0;
		try {
			String sql="select count(*) from student where Student_DomitoryID=?";
			Object[] params= {did};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				num=rs.getInt(1);
			}
			return num;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			dbUtil.closeAll();
		}
	}
	//查询
	@Override
	public Student QuerySelectById(int id) {
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
	    
		try {
			String sql="select * from student where Student_ID=?";
			Object[] params= {id};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				int no=rs.getInt("Student_ID");
				int domid=rs.getInt("Student_DomitoryID");
				String username=rs.getString("Student_Username");
				String password=rs.getString("Student_Password");
				String name=rs.getString("Student_Name");
				String sex=rs.getString("Student_Sex");
				String clas=rs.getString("Student_Class");
				String state=rs.getString("Student_State");
				student=new Student(no,domid,username,password,name,sex,clas,state);
			}
			return student;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	//查询全部
	@Override
	public List<Student> QuerySelectAll(Student stu) {
		List<Student> students=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select * from student where 1=1");
			ArrayList<Object> param = new ArrayList<Object>();
			String sname=stu.getStudent_Name();
			if(sname!=null && !sname.trim().isEmpty()) {
				sql.append(" and Student_Name=?");
				param.add(sname);
			}
			String susername=stu.getStudent_Username();
			if(susername!=null && !susername.trim().isEmpty()) {
				sql.append(" and Student_Username=?");
				param.add(susername);
			}
			String sclas=stu.getStudent_Class();
			if(sclas!=null && !sclas.trim().isEmpty()) {
				sql.append(" and Student_Class=?");
				param.add(sclas);
			}
			String state=stu.getStudent_State();
			if(state!=null && !state.trim().isEmpty()) {
				sql.append(" and Student_State=?");
				param.add(state);
			}
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int id=rs.getInt("Student_ID");
				int did=rs.getInt("Student_DomitoryID");
				String username=rs.getString("Student_Username");
				String password=rs.getString("Student_Password");
				String name=rs.getString("Student_Name");
				String sex=rs.getString("Student_Sex");
				String clas=rs.getString("Student_Class");
				String sstate=rs.getString("Student_State");
				student=new Student(id,did,username,password,name,sex,clas,sstate);
				students.add(student);
			}
			return students;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	@Override
	public List<Student> QuerySelectAllPage(Student stu, int currentPage, int pageSize) {
		List<Student> students=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select * from student where 1=1");
			ArrayList<Object> param = new ArrayList<Object>();
			String sname=stu.getStudent_Name();
			if(sname!=null && !sname.trim().isEmpty()) {
				sql.append(" and Student_Name=?");
				param.add(sname);
			}
			String susername=stu.getStudent_Username();
			if(susername!=null && !susername.trim().isEmpty()) {
				sql.append(" and Student_Username=?");
				param.add(susername);
			}
			String sclas=stu.getStudent_Class();
			if(sclas!=null && !sclas.trim().isEmpty()) {
				sql.append(" and Student_Class=?");
				param.add(sclas);
			}
			String state=stu.getStudent_State();
			if(state!=null && !state.trim().isEmpty()) {
				sql.append(" and Student_State=?");
				param.add(state);
			}
			sql.append(" limit ?,?");
			param.add((currentPage-1)*pageSize);
			param.add(pageSize);
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int id=rs.getInt("Student_ID");
				int did=rs.getInt("Student_DomitoryID");
				String username=rs.getString("Student_Username");
				String password=rs.getString("Student_Password");
				String name=rs.getString("Student_Name");
				String sex=rs.getString("Student_Sex");
				String clas=rs.getString("Student_Class");
				String sstate=rs.getString("Student_State");
				student=new Student(id,did,username,password,name,sex,clas,sstate);
				students.add(student);
			}
			return students;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	
	//查询包含宿舍名
	@Override
	public Student QuerySelectdoByid(int id) {
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
	    
		try {
			String sql="select s.Student_Username,s.Student_Name,s.Student_Sex,s.Student_Class,d.Domitory_Name from student s,domitory d where d.Domitory_ID=s.Student_DomitoryID and s.Student_ID=?";
			Object[] params= {id};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				String username=rs.getString("Student_Username");
				String name=rs.getString("Student_Name");
				String sex=rs.getString("Student_Sex");
				String clas=rs.getString("Student_Class");
				String dname=rs.getString("Domitory_Name");
				student=new Student(username,name,sex,clas,dname);
			}
			return student;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}

	//修改
	@Override
	public boolean update(int id, Student student) {
		DBUtil dbUtil = new DBUtil();
		String sql="update student set Student_Username=?,Student_Password=?,Student_Name=?,Student_Sex=?,Student_Class=? where Student_ID=?";
		Object[] params= {student.getStudent_Username(),student.getStudent_Password(),student.getStudent_Name(),student.getStudent_Sex(),student.getStudent_Class(),id};
		return dbUtil.executeUpdate(sql, params);
	}		

	//根据buildID查询学生和宿舍
	@Override
	public List<Student> QuerySelectBybdId(int id, Student stu) {
		List<Student> students=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
		
		try {
			StringBuilder sql =new StringBuilder("select s.Student_ID,s.Student_Name,s.Student_Sex,s.Student_Class,d.Domitory_Name,d.Domitory_Number,d.Domitory_Tel from"
					+ " domitory d,student s where d.Domitory_ID=s.Student_DomitoryID and"
					+ " d.Domitory_BuildingID=?");
			ArrayList<Object> param = new ArrayList<Object>();
			param.add(id);
			String dname=stu.getDomitory_Name();
			if(dname!=null && !dname.trim().isEmpty()) {
				sql.append(" and d.Domitory_Name=?");
				param.add(dname);
			}
			String sname=stu.getStudent_Name();
			if(sname!=null && !sname.trim().isEmpty()) {
				sql.append(" and s.Student_Name=?");
				param.add(sname);
			}
			String susername=stu.getStudent_Username();
			if(susername!=null && !susername.trim().isEmpty()) {
				sql.append(" and s.Student_Username=?");
				param.add(susername);
			}
			String sclass=stu.getStudent_Class();
			if(sclass!=null && !sclass.trim().isEmpty()) {
				sql.append(" and s.Student_Class=?");
				param.add(sclass);
			}
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int sid=rs.getInt("Student_ID");				
				String stname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String sclas=rs.getString("Student_Class");
				String doname=rs.getString("Domitory_Name");
				String donum=rs.getString("Domitory_Number");
				String dotel=rs.getString("Domitory_Tel");
				student=new Student(sid,stname,ssex,sclas,doname,donum,dotel);
				students.add(student);
			}
			return students;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	@Override
	public List<Student> QuerySelectBybdPage(int id, Student stu, int currentPage, int pageSize) {
		List<Student> students=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
		
		try {
			StringBuilder sql =new StringBuilder("select s.Student_ID,s.Student_Name,s.Student_Sex,s.Student_Class,d.Domitory_Name,d.Domitory_Number,d.Domitory_Tel from"
					+ " domitory d,student s where d.Domitory_ID=s.Student_DomitoryID and"
					+ " d.Domitory_BuildingID=?");
			ArrayList<Object> param = new ArrayList<Object>();
			param.add(id);
			String dname=stu.getDomitory_Name();
			if(dname!=null && !dname.trim().isEmpty()) {
				sql.append(" and d.Domitory_Name=?");
				param.add(dname);
			}
			String sname=stu.getStudent_Name();
			if(sname!=null && !sname.trim().isEmpty()) {
				sql.append(" and s.Student_Name=?");
				param.add(sname);
			}
			String susername=stu.getStudent_Username();
			if(susername!=null && !susername.trim().isEmpty()) {
				sql.append(" and s.Student_Username=?");
				param.add(susername);
			}
			String sclass=stu.getStudent_Class();
			if(sclass!=null && !sclass.trim().isEmpty()) {
				sql.append(" and s.Student_Class=?");
				param.add(sclass);
			}
			sql.append(" limit ?,?");
			param.add((currentPage-1)*pageSize);
			param.add(pageSize);
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int sid=rs.getInt("Student_ID");				
				String stname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String sclas=rs.getString("Student_Class");
				String doname=rs.getString("Domitory_Name");
				String donum=rs.getString("Domitory_Number");
				String dotel=rs.getString("Domitory_Tel");
				student=new Student(sid,stname,ssex,sclas,doname,donum,dotel);
				students.add(student);
			}
			return students;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	//添加
	@Override
	public boolean add(Student stu) {
		DBUtil dbUtil = new DBUtil();
		String sql="insert into student(Student_Username,Student_Password,Student_Name,Student_Sex,Student_Class,Student_State) value(?,?,?,?,?,?)";
		Object[] params = {stu.getStudent_Username(),stu.getStudent_Password(),stu.getStudent_Name(),stu.getStudent_Sex(),stu.getStudent_Class(),stu.getStudent_State()};
		return dbUtil.executeUpdate(sql, params);
	}
	//删除
	@Override
	public boolean del(int id) {
		DBUtil dbUtil = new DBUtil();
		String sql="update student set Student_State='删除' where Student_ID=?";
		Object[] params = {id};
		return dbUtil.executeUpdate(sql, params);
	}
	@Override
	public Student QuerySelectByuser(String username) {
		DBUtil dbUtil = new DBUtil();
		Student student=null;
		ResultSet rs =null;
	    
		try {
			String sql="select * from student where Student_Username=?";
			Object[] params= {username};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				int no=rs.getInt("Student_ID");
				int domid=rs.getInt("Student_DomitoryID");
				String uname=rs.getString("Student_Username");
				String password=rs.getString("Student_Password");
				String name=rs.getString("Student_Name");
				String sex=rs.getString("Student_Sex");
				String clas=rs.getString("Student_Class");
				String state=rs.getString("Student_State");
				student=new Student(no,domid,uname,password,name,sex,clas,state);
			}
			return student;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	//学生入住
	@Override
	public boolean move(int did, String state,String username) {
		DBUtil dbUtil = new DBUtil();
		String sql="update student set Student_DomitoryID=?,Student_State=? where Student_Username=?";
		Object[] params= {did,state,username};
		return dbUtil.executeUpdate(sql, params);
	}
	
}
