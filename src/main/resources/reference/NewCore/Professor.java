package NewCore;

import java.util.LinkedList;
import java.util.List;

public class Professor extends Person{
	private String id, name, password, info;
	private String active, type, birth, tel, qq, email, dorm, gender;
	private List<Course> couList;
	
	//Teacher file: ID + Password +  Name + Active? + type + birth + tele + qq + email + dorm + Gender	
	public Professor(){
		this.id = "";
		this.name = "";
		this.password = "";
		this.active = "";
		this.type = "";
		this.tel = "";
		this.qq = "";
		this.email = "";
		this.active ="";
		this.dorm = "";
		this.gender = "";
		this.couList = new LinkedList<Course>();
	}
	public Professor(String id, String password, String name, String active, String type,
			String birth, String tele, String qq, String email, String dorm, String gender){
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.qq = qq;
		this.dorm = dorm;
		this.email = email;
		this.active = active;
		this.gender = gender;
		this.birth = birth;
		this.type = type;
		this.tel = tele;
		
	}
	public String formingForISF(){
		info = this.id + "\t" + this.password + "\t" + this.name + "\t" + this.active + "\t" +
				 this.type + "\t" + this.birth + "\t" + this.tel + "\t" + this.qq + "\t" + this.email + "\t" + this.dorm + "\t" +
				 this.gender + "\t"; 
		return info;
	}
	public String formingForASF(){
		info = this.id + "\t" + this.password + "\t" + this.name + "\t" + this.active + "\t";
		return info;
	}
	public String formingForDAF(){
		info = this.id + "\t" + this.name + "\t" + this.type+ "\t" + this.active + "\t";
		return info;
	}
	public void display(){
		String txt = this.id + "\t" + this.password + "\t" + this.name + "\t" + this.active + "\t" + 
					 this.type + "\t" + this.birth + "\t" + this.tel + "\t" + this.qq + "\t" + this.email + "\t" +
					 this.dorm + "\t" + this.gender + "\t";
		System.out.println(txt);
	}
	public List<Course> getCouList() {
		return couList;
	}

	public void setCouList(List<Course> couList) {
		this.couList = couList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
}
