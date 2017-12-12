package NewCore;

import java.util.LinkedList;
import java.util.List;

import SRSDataBase.SRSFunctions;

public class Course {
	/**
	 * Individual File :crn + name + credits + requirements + teacherID + couLeve + startS + startE
		  				+ day + startTS + startTE + capa + listofStudent + Active
	   All course/semester File :crn + name + credits + requirements + teacherID
	   Student File: crn + name + grade + completion
	   Teacher File: crn + name + credits
	*/
	private String crn, name, credits, requirements, teacherID;
	private String couLeve, startS, startE, day, startTS, startTE;
	private String capa, active;
	private List<Student> stuList;
	private Professor pro;
	private Transcript tran;
	
	
	public Course(){
		this.crn = "";
		this.name = "";
		this.capa = "";
		this.credits = "";
		this.requirements = "";
		this.teacherID = "";
		this.couLeve = "";
		this.startE = "";
		this.startS = "";
		this.startTE = "";
		this.startTS = "";
		this.day = "";
		this.setActive("");
		this.stuList = new LinkedList<Student>();
		this.setPro(new Professor());
	}
	public Course(String crn, String name, String capa, String credits, String requirements,
			String teacherID, String couLeve, String startE, String startS, String startTE,
			String startTS, String day, String active){
		this.crn = crn;
		this.name = name;
		this.capa = capa;
		this.credits = credits;
		this.requirements = requirements;
		this.teacherID = teacherID;
		this.pro = SRSFunctions.makeAProfessor(teacherID);
		this.couLeve = couLeve;
		this.startE = startE;
		this.startS = startS;
		this.startTE = startTE;
		this.startTS = startTS;
		this.day = day;
		this.active = active;
		
	}
	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.pro = SRSFunctions.makeAProfessor(teacherID);
		this.teacherID = teacherID;
	}

	public String getCouLeve() {
		return couLeve;
	}

	public void setCouLeve(String couLeve) {
		this.couLeve = couLeve;
	}

	public String getStartS() {
		return startS;
	}

	public void setStartS(String startS) {
		this.startS = startS;
	}

	public String getStartE() {
		return startE;
	}

	public void setStartE(String startE) {
		this.startE = startE;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTS() {
		return startTS;
	}

	public void setStartTS(String startTS) {
		this.startTS = startTS;
	}

	public String getStartTE() {
		return startTE;
	}

	public void setStartTE(String startTE) {
		this.startTE = startTE;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public List<Student> getStuList() {
		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}
	public void display(){
		/*
		 * Individual File :crn + name + credits + requirements + teacherID + couLeve + startS + startE
		  				+ day + startTS + startTE + capa + listofStudent + active
		 */
		String txt = this.crn + " " + this.name + " " + this.credits + " " + this.requirements + " " + this.teacherID + " " +
				 this.couLeve + " " + this.startS  + " " + this.startE + " " + this.day + " " + this.startTS + " " + this.startTE + " " 
				 + this.capa + "\t" + this.active;
		System.out.println(txt);
	}
	/**
	 * Individual File :crn + name + credits + requirements + teacherID + couLeve + startS + startE
		  				+ day + startTS + startTE + capa + listofStudent + Active
	   All course/semester File :crn + name + credits + requirements + teacherID
	   Student File: crn + name + grade + completion
	   Teacher File: crn + name + credits
	*/
	public String formingForICF(){
		String info;
		info =   this.crn + "\t" + this.name  + "\t" + this.credits  + "\t" + this.requirements  + "\t" + this.teacherID  + "\t" +
				 this.couLeve  + "\t" + this.startS  + "\t" + this.startE  + "\t" + this.day  + "\t" + this.startTS  + "\t" + this.startTE  + "\t"
				 + this.capa + "\t" + this.active;
		return info;
	}
	public String formingForITF(){
		String info;
		info = this.crn + "\t" + this.credits + "\t";
		return info;
	}
	public String formingForACF(){
		String info;
		info = this.crn + "\t" + this.name  + "\t" + this.credits  + "\t" + this.requirements  + "\t" + this.teacherID  + "\t";
		return info;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Professor getPro() {
		return pro;
	}
	public void setPro(Professor pro) {
		this.pro = pro;
	}
	public Transcript getTran() {
		return tran;
	}
	public void setTran(Transcript tran) {
		this.tran = tran;
	}
}
