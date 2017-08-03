package com.harambase.pioneer.pojo;

public class Course extends Base{

	private String crn;
	private String name;
	private String credits;
	private String precrn;
	private String couLev;
	private String couSec;
	private String startDate;
	private String endDate;
	private String day;
	private String startTime;
	private String endTime;
	private String capa; //capacity
	private String status;
	private String facultyid;

	public String getCouSec() {
		return couSec;
	}

	public void setCouSec(String couSec) {
		this.couSec = couSec;
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

	public String getPrecrn() {
		return precrn;
	}

	public void setPrecrn(String precrn) {
		this.precrn = precrn;
	}

	public String getCouLev() {
		return couLev;
	}

	public void setCouLev(String couLev) {
		this.couLev = couLev;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFacultyid() {
		return facultyid;
	}

	public void setFacultyid(String facultyid) {
		this.facultyid = facultyid;
	}
}
