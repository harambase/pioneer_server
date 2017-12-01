package NewCore;

public class Transcript {
	private Student student;
	private Course course;
	private Professor professor;
	private String grade, complete;
	
	public Transcript(){
		student = new Student();
		course = new Course();
		grade = "";
		complete = "";
	}
	public Transcript(Student sID, Professor tID, Course crn, String grade, String complete){
		this.student = sID;
		this.professor = tID;
		this.course = crn;
		this.grade = grade;
		this.complete = complete;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void display(){
		String txt = this.student.getId() + " " + this.professor.getId() + " " + this.grade + " " + this.complete + " "
				+ this.course.getCrn();
		System.out.println(txt);
	}
	
}
