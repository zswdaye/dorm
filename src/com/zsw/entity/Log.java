package com.zsw.entity;

public class Log {
	private int Log_ID;
	private int Log_StudentID;
	private int Log_TeacherID;
	private String Domitory_Name;
	private String Student_Name;
	private String Student_Sex;
	private String Student_Class;
    private String Log_Date ;
    private String Log_Remark ;
	private String Teacher_Name;
	
	
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(String domitory_Name, String student_Name, String student_Sex, String log_Date, String log_Remark,
			String teacher_Name) {
		super();
		Domitory_Name = domitory_Name;
		Student_Name = student_Name;
		Student_Sex = student_Sex;
		Log_Date = log_Date;
		Log_Remark = log_Remark;
		Teacher_Name = teacher_Name;
	}
	
	public Log(int log_ID,String domitory_Name, String student_Name, String student_Sex, String student_Class, String log_Date,
			String log_Remark,String teacher_Name) {
		super();
		Log_ID = log_ID;
		Domitory_Name = domitory_Name;
		Student_Name = student_Name;
		Student_Sex = student_Sex;
		Student_Class = student_Class;
		Log_Date = log_Date;
		Log_Remark = log_Remark;
		Teacher_Name=teacher_Name;
	}

	public int getLog_ID() {
		return Log_ID;
	}

	public void setLog_ID(int log_ID) {
		Log_ID = log_ID;
	}

	public int getLog_StudentID() {
		return Log_StudentID;
	}

	public void setLog_StudentID(int log_StudentID) {
		Log_StudentID = log_StudentID;
	}

	public int getLog_TeacherID() {
		return Log_TeacherID;
	}

	public void setLog_TeacherID(int log_TeacherID) {
		Log_TeacherID = log_TeacherID;
	}

	public String getDomitory_Name() {
		return Domitory_Name;
	}
	public void setDomitory_Name(String domitory_Name) {
		Domitory_Name = domitory_Name;
	}
	public String getStudent_Name() {
		return Student_Name;
	}
	public void setStudent_Name(String student_Name) {
		Student_Name = student_Name;
	}
	public String getStudent_Sex() {
		return Student_Sex;
	}
	public void setStudent_Sex(String student_Sex) {
		Student_Sex = student_Sex;
	}
	public String getStudent_Class() {
		return Student_Class;
	}

	public void setStudent_Class(String student_Class) {
		Student_Class = student_Class;
	}

	public String getLog_Date() {
		return Log_Date;
	}
	public void setLog_Date(String log_Date) {
		Log_Date = log_Date;
	}
	public String getLog_Remark() {
		return Log_Remark;
	}
	public void setLog_Remark(String log_Remark) {
		Log_Remark = log_Remark;
	}
	public String getTeacher_Name() {
		return Teacher_Name;
	}
	public void setTeacher_Name(String teacher_Name) {
		Teacher_Name = teacher_Name;
	}

}
