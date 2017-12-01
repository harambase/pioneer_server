package NewCore;
import java.util.LinkedList;
import java.util.List;


public class Student extends Person{
	//Student file: ID + Password +  Name + Active? + Credits + type + birth + tele + qq + email + dorm
	// 		 + Faculty Advisor + ActiveforDrop(AD/DD) + ActiveforAdd(AA/DA) + Gender	
	
	private String id, password, name, active, type;
	private String credits, birth, tele, qq, email;
	private String dorm, actD, actA, gender;
	private Professor fa;
	private List<Course> couList;
	private String info;
	
	public Student(){
		super();
		this.id = "";
		this.password = "";
		this.name = "";
		this.actA = "";
		this.credits = "";
		this.tele = "";
		this.qq = "";
		this.dorm = "";
		this.email = "";
		this.active = "";
		this.actD = "";
		this.gender = "";
		this.birth = "";
		this.fa = new Professor();
		this.type = "S";
		this.info = "";
		this.couList = new LinkedList<Course>();
	}
	public Student(String id, String password, String name, String active, String type,
			String credits, String birth, String tele, String qq, String email, String dorm,
			Professor fa, String actD, String actA, String gender){
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.actA = actA;
		this.credits = credits;
		this.tele = tele;
		this.qq = qq;
		this.dorm = dorm;
		this.email = email;
		this.active = active;
		this.actD = actD;
		this.gender = gender;
		this.birth = birth;
		this.fa = fa;
		this.type = "S";
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
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

	public Professor getFa() {
		return fa;
	}

	public void setFa(Professor fa) {
		this.fa = fa;
	}

	public String getActD() {
		return actD;
	}

	public void setActD(String actD) {
		this.actD = actD;
	}

	public String getActA() {
		return actA;
	}

	public void setActA(String actA) {
		this.actA = actA;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Course> getCouList() {
		return couList;
	}

	public void setCouList(List<Course> couList) {
		this.couList = couList;
	}
	
	public String formingForISF(){
		info = this.id + "\t" + this.password + "\t" + this.name + "\t" + this.active + "\t" + this.credits + "\t" +
				 this.type + "\t" + this.birth + "\t" + this.tele + "\t" + this.qq + "\t" + this.email + "\t" + this.dorm + "\t" 
				+ this.fa.getId() + "\t" + this.actD + "\t" + this.actA + "\t" + this.gender + "\t";
		return info;
	}
	public String formingForASF(){
		info = this.id + "\t" + this.password + "\t" + this.name + "\t" + this.active + "\t" + this.credits + "\t";
		return info;
	}
	public String formingForDAF(){
		info = this.id + "\t" + this.name + "\t" + this.type+ "\t" + this.active + "\t";
		return info;
	}
	
	public void dispaly(){
		//Student file: ID + Password +  Name + Active? + Credits + type + birth + tele + qq + email + dorm
		// 		 + Faculty Advisor + ActiveforDrop(AD/DD) + ActiveforAdd(AA/DA) + Gender	
		String txt = this.id + " " + this.password + " " + this.name + " " + this.active + " " + this.credits + " " +
					 this.type + " " + this.birth + " " + this.tele + " " + this.qq + " " + this.email + " " + this.dorm + " " + 
					 this.fa.getId() + " " + this.actD + " " + this.actA + " " + this.gender;
		System.out.println(txt);
	}
	@Override
	public String toString() {
		
		return null;
	}

	
		
}


