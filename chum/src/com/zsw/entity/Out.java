package com.zsw.entity;

public class Out {
    private int Out_ID ;
    private int Out_StudentID ;
    private String Out_Date ;
    private String Out_Remark ;
    private String Student_Name;
    private String Student_Sex;
    private String Student_Class;
    
	public Out() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Out(String out_Date, String student_Name, String student_Sex, String student_Class, String out_Remark) {
		super();
		Out_Date = out_Date;
		Student_Name = student_Name;
		Student_Sex = student_Sex;
		Student_Class = student_Class;
		Out_Remark = out_Remark;
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
	public int getOut_ID() {
		return Out_ID;
	}
	public void setOut_ID(int out_ID) {
		Out_ID = out_ID;
	}
	public int getOut_StudentID() {
		return Out_StudentID;
	}
	public void setOut_StudentID(int out_StudentID) {
		Out_StudentID = out_StudentID;
	}
	public String getOut_Date() {
		return Out_Date;
	}
	public void setOut_Date(String out_Date) {
		Out_Date = out_Date;
	}
	public String getOut_Remark() {
		return Out_Remark;
	}
	public void setOut_Remark(String out_Remark) {
		Out_Remark = out_Remark;
	}
    
}
