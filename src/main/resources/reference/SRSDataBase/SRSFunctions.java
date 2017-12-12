package SRSDataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import AdminGUIBars.SRSInfoNotPassed;
import AdminGUIBars.SRSPersonDeactive;
import AdminGUIBars.SRSRegisterError;
import NewCore.Course;
import NewCore.Person;
import NewCore.Professor;
import NewCore.Student;
import NewCore.Transcript;

public class SRSFunctions {
	public static Course makeACourse(String crn){
		String name, capa, credits, requirements;
		String teacherID, couLeve, startE, startS, startTE;
		String startTS, day, active;
		Course cou = new Course();
		
		try {
			Scanner sc = new Scanner(new File("C"+ crn + ".dat"));
			String[] split = new String[21];
			String line = "";
			//Individual File :crn + name + credits + requirements + teacherID + couLeve + startS + startE
			//	+ day + startTS + startTE + capa + listofStudent + Active
			line = sc.nextLine();
			split = line.split("\t");
			
			name = split[1];
			credits = split[2];
			requirements = split[3];
			teacherID = split[4];
			couLeve = split[5];
			startS = split[6];
			startE = split[7];
			day = split[8];
			startTS = split[9];
			startTE = split[10];
			capa = split[11];
			active = split[12];
			cou = new Course(crn, name, capa, credits,requirements,
					teacherID, couLeve, startE, startS, startTE,
					startTS, day, active);
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cou;
	}
	public static Student makeAStudent(String studentID){
		Student stu = new Student();
		String studentFile = studentID + ".dat";
		//Student file: ID + Password +  Name + Active? + Credits + type + birth + tele + qq + email + dorm
		// 		 + Faculty Advisor + ActiveforDrop(AD/DD) + ActiveforAdd(AA/DA) + Gender	
		try {
			Scanner scS = new Scanner(new File(studentFile));
			String[] infoS = new String[30];			
			infoS = scS.nextLine().split("\t");
			
			String active = infoS[3];
			String credit = infoS[4];
			String ad = infoS[12];
			String aa = infoS[13];	
			String password = infoS[1];
			String name = infoS[2];
			String birth = infoS[6];
			String tele = infoS[7];
			String qq = infoS[8];
			String email = infoS[9];
			String dorm = infoS[10];
			String fa = infoS[11];
			String gender = infoS[14];
			
			Professor fap = makeAProfessor(fa);
			stu = new Student(studentID, password, name, active, "S",
			credit, birth, tele, qq, email, dorm,
			fap, ad, aa, gender);
			scS.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//stu.dispaly();
		return stu;
	}
	public static Professor makeAProfessor(String teacherID){
		Professor pro = new Professor();
		String professorFile = teacherID + ".dat";
		//Teacher file: ID + Password +  Name + Active? + type + birth + tele + qq + email + dorm + Gender	
		try {
			Scanner scS = new Scanner(new File(professorFile));
			String[] infoS = new String[30];			
			infoS = scS.nextLine().split("\t");
			
			String password = infoS[1];
			String name = infoS[2];
			String active = infoS[3];
			String birth = infoS[5];
			String tele = infoS[6];
			String qq = infoS[7];
			String email = infoS[8];
			String dorm = infoS[9];
			String gender = infoS[10];
			
			pro = new Professor(teacherID, password, name, active, "T",
			birth, tele, qq, email, dorm, gender);
			scS.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//stu.dispaly();
		return pro;
	}
	public static Transcript makeATranscript(String studentID, String crn){
		Transcript tran = new Transcript();
		String courseFile = "C" + crn + ".dat";
		try {
			Scanner sc = new Scanner(new File(courseFile));
			while(sc.hasNextLine()){
				String[] info = sc.nextLine().split("\t");
				if(info[0].equals(studentID)){
					Course cou = makeACourse(crn);
					Student stu = makeAStudent(studentID);
					Professor pro = makeAProfessor(cou.getTeacherID());
					String grade = info[1];
					String comp = info[2];
					tran.setCourse(cou);
					tran.setStudent(stu);
					tran.setProfessor(pro);
					tran.setGrade(grade);
					tran.setComplete(comp);
					break;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return tran;	
	}
	public static List<Course> makeCoursesList(String id){
		List<Course> couList =  new LinkedList<Course>();
		try {
			Scanner scIF = new Scanner(new File(id + ".dat"));
			scIF.nextLine();
			while(scIF.hasNextLine()){
				String crn = scIF.nextLine().split("\t")[0];
				Course cou = makeACourse(crn);
				couList.add(cou);
			}
			scIF.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return couList;
	}
	public static List<Student> makeStudentsList(String crn){
		List<Student> stuList =  new LinkedList<Student>();
		try {
			Scanner scIF = new Scanner(new File("C" + crn + ".dat"));
			scIF.nextLine();
			while(scIF.hasNextLine()){
				String id = scIF.nextLine().split("\t")[0];
				Student stu = makeAStudent(id);
				stuList.add(stu);
			}
			scIF.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return stuList;
	}
	public static List<Student> makeAdvisingList(String id){
		List<Student> stuList =  new LinkedList<Student>();
		try {
			Scanner scIF = new Scanner(new File("Advisors.dat"));
			String line;
			while(scIF.hasNextLine()){
				line = scIF.nextLine();
				if(line.split("\t")[0].equals(id)){
					for(int k = 1; k < line.split("\t").length; k++){
						stuList.add(SRSFunctions.makeAStudent(line.split("\t")[k]));
					}
				}
			}
			scIF.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return stuList;
	}
	
	public static String findATypeOfAPerson(Person per){
		String type = "";
		try {
			Scanner sc = new Scanner(new File("AllPersons.dat"));
			while(sc.hasNext()){
				String line = sc.nextLine();
				if(line.split("\t")[0].equals(per.getId())){
					type = line.split("\t")[2];
					break;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	public static void createAllPersonFile(){
		String allPersonFile = "AllPersons.dat";
		String studentsFile  = "Students.dat";
		String teachersFile = "Teachers.dat";
		String deactivaFile = "DeactivatePerson.dat";
		
		try {
			Scanner scDP = new Scanner(new File(deactivaFile));
			Scanner scSP = new Scanner(new File(studentsFile));
			Scanner scTP = new Scanner(new File(teachersFile));
			String[] info = new String[5000];
			String[] split = new String[10];
			String line = "";
			int i = 2;
			info[0] = "000000000" + "\t" + "N/A" + "\t" + "T" + "\t" + "A" + "\t";
			info[1] = "001010001" + "\t" + "Admin" + "\t" + "A" + "\t" + "A" + "\t";
			scSP.nextLine();
			while(scSP.hasNextLine()){
				line = scSP.nextLine();
				split = line.split("\t");
				info[i] = split[0] + "\t" + split[2] + "\t" + "S" + "\t" + split[3] + "\t";
				i++;
			}
			scTP.nextLine();
			while(scTP.hasNextLine()){
				line = scTP.nextLine();
				split = line.split("\t");
				info[i] = split[0] + "\t" + split[2] + "\t" + "T" + "\t" + split[3] + "\t";
				i++;
			}
			while(scDP.hasNextLine()){
				info[i] = scDP.nextLine();
				i++;
			}
			
			scSP.close();
			scDP.close();
			scTP.close();
			
			int j = 0;
			PrintWriter pw = new PrintWriter(new File(allPersonFile));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void sort(String fileName){
		//sorting
		try {
			Scanner scan = new Scanner(new File(fileName));
			int i = 0, j = 0, id = 0, x = 0;
			String line = null;
			String[] alinfo = new String[1000];
			
			while (scan.hasNextLine()){
				alinfo[x] = scan.nextLine();
				x++;
			}
			
			PrintWriter pw = new PrintWriter(fileName);
			for (i = 0; i < x; i++){
				for (j = i + 1; j < x; j++){
					id  = Integer.compare(Integer.parseInt(alinfo[i].split("\t")[0].toString()), 
							Integer.parseInt(alinfo[j].split("\t")[0].toString()));
					if(id > 0){
						line = alinfo[i];
						alinfo[i] = alinfo[j];
						alinfo[j] = line;
					}
				}
			}
			i = 0;

			while(i < x){
				pw.println(alinfo[i].toString());
				i++;
			}
			pw.close();
			scan.close();
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException a){
			a.printStackTrace();
		}
	}
	public static void refreshAdvisors(){
		try {
			Scanner sc = new Scanner(new File("Advisors.dat"));
			String[] split = new String[100];
			String na = "000000000";
			String line = "";			
			while(sc.hasNextLine()){
				line = sc.nextLine();
				split = line.split("\t");
				Professor pro = new Professor();
				if (!split[0].equals(na)){
					pro.setId(split[0]);
				}
				else{
					pro.setId(na);
				}
				//TODO: check for the activation
				for(int k = 1; k < split.length; k++){
					Student stu = makeAStudent(split[k]);
					stu.setFa(pro);
					saveAPersonFirstLineInfo(stu);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void saveATranscriptToAStudent(Student stu, Transcript tran){
		String grade = tran.getGrade();
		String compl = tran.getComplete();
		String crn = tran.getCourse().getCrn();
		String id = stu.getId();
		try {
			Scanner scFS = new Scanner(new File(id + ".dat"));
			Scanner scFC = new Scanner(new File("C" + crn + ".dat"));
			
			String line;
			String[] info = new String[100];
			String[] split = new String[10];
			
			int i = 0, j = 0;
			
			while(scFS.hasNext()){
				line = scFS.nextLine();
				split = line.split("\t");
				if(split[0].equals(crn)){
					info[i] = crn + "\t" + grade + "\t" + compl + "\t";
				}
				else{
					info[i] = line;
				}
				i++;
			}
			scFS.close();
			PrintWriter pwFS = new PrintWriter(new File(id + ".dat"));
			while(j < i){
				pwFS.println(info[j]);
				j++;
			}
			pwFS.close();
			
			info = new String[100];
			split = new String[10];
			i = 0; j = 0;
			while(scFC.hasNext()){
				line = scFC.nextLine();
				split = line.split("\t");
				if(split[0].equals(id)){
					info[i] = id + "\t" + grade + "\t" + compl + "\t";
				}
				else{
					info[i] = line;
				}
				i++;
			}
			scFC.close();
			PrintWriter pwFC = new PrintWriter(new File("C" + crn + ".dat"));
			while(j < i){
				pwFC.println(info[j]);
				j++;
			}
			pwFC.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static void saveAPersonFirstLineInfo(Person per){
		//Individual Files
		try {
			Scanner sc = new Scanner(new File(per.getId() + ".dat"));
			String[] info = new String[100];
			int i = 0, j = 0;
			while(sc.hasNextLine()){
				info[i] = sc.nextLine();
				i++;
			}
			PrintWriter pw = new PrintWriter(new File(per.getId() + ".dat"));
			info[0] = per.formingForISF();
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//All Teachers/Students
		String type = findATypeOfAPerson(per);
		if(type.equals("T")){
			Professor pro = makeAProfessor(per.getId());
			String infoFAF = pro.formingForASF();
			String line = "";
			try {
				Scanner sc = new Scanner(new File("Teachers.dat"));
				String[] info = new String[100];
				int i = 0, j = 0;
				while(sc.hasNextLine()){
					line = sc.nextLine();
					if(!line.split("\t")[0].equals(per.getId())){
						info[i] = line;
					}
					else{
						info[i] = infoFAF;
					}
					i++;
				}
				PrintWriter pw = new PrintWriter(new File("Teachers.dat"));
				info[0] = per.formingForISF();
				while(j < i){
					pw.println(info[j]);
					j++;
				}
				sc.close();
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{
			Student stu = makeAStudent(per.getId());
			String infoFAF = stu.formingForASF();
			String line = "";
			try {
				Scanner sc = new Scanner(new File("Students.dat"));
				String[] info = new String[100];
				int i = 0, j = 0;
				while(sc.hasNextLine()){
					line = sc.nextLine();
					if(!line.split("\t")[0].equals(per.getId())){
						info[i] = line;
					}
					else{
						info[i] = infoFAF;
					}
					i++;
				}
				PrintWriter pw = new PrintWriter(new File("Students.dat"));
				while(j < i){
					pw.println(info[j]);
					j++;
				}
				sc.close();
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		//All Persons
		createAllPersonFile();
	}
	public static void saveACourseFirstLineInfo(Course cou){
		try {
			Scanner sc = new Scanner(new File("C" + cou.getCrn() + ".dat"));
			String[] info = new String[100];
			int i = 0, j = 0;
			while(sc.hasNextLine()){
				info[i] = sc.nextLine();
				i++;
			}
			PrintWriter pw = new PrintWriter(new File("C" + cou.getCrn() + ".dat"));
			info[0] = cou.formingForICF();
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void saveACourse(Course cou){
		try {
			Scanner sc = new Scanner(new File("C" + cou.getCrn() + ".dat"));
			String[] info = new String[100];
			int i = 0, j = 0;
			while(sc.hasNextLine()){
				info[i] = sc.nextLine();
				i++;
			}
			PrintWriter pw = new PrintWriter(new File("C" + cou.getCrn() + ".dat"));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void saveAStudentCourseInfoByList(Student stu, List<Course> couList){
		//Student File: crn + name + grade + completion
		//Teacher File: crn + name + credits
		File fl = new File(stu.getId() + ".dat");
		try {
			Scanner sc = new Scanner(fl);
			String[] info = new String[100];
			String line;
			int i = 1 , index = 0, j = 0;
			line = sc.nextLine();
			info[0] = line;
			while(couList.listIterator(index).hasNext()){
				Transcript tran = makeATranscript(stu.getId(), couList.get(index).getCrn());
				String infoISF = couList.get(index).getCrn() + "\t"
						+ tran.getGrade() + "\t" + tran.getComplete() + "\t";
				info[i] = infoISF;
				i++;
				index++;
			}
			PrintWriter pw = new PrintWriter(fl);
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.close();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static void saveAProfessorCourseInfoByList(Professor pro, List<Course> couList){
		//Student File: crn + name + grade + completion
		//Teacher File: crn + name + credits
		File fl = new File(pro.getId() + ".dat");
		try {
			Scanner sc = new Scanner(fl);
			String[] info = new String[100];
			String line;
			int i = 1 , index = 0, j = 0;
			line = sc.nextLine();
			info[0] = line;
			while(couList.listIterator(index).hasNext()){
				info[i] = couList.get(index).formingForITF();
				i++;
				index++;
			}
			PrintWriter pw = new PrintWriter(fl);
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.close();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void saveACourseStudentInfoByList(Course cou,
			List<Student> stuList) {
		try {
			Scanner sc = new Scanner(new File("C" + cou.getCrn() + ".dat"));
			String[] info = new String[100];
			String line;
			int i = 1 , index = 0, j = 0;
			line = sc.nextLine();
			info[0] = line;
			while(stuList.listIterator(index).hasNext()){
				Transcript tran = makeATranscript(stuList.get(index).getId(), cou.getCrn());
				String infoISF = stuList.get(index).getId() +"\t"
						+ tran.getGrade() + "\t" + tran.getComplete() + "\t";
				info[i] = infoISF;
				i++;
				index++;
			}
			PrintWriter pw = new PrintWriter(new File("C" + cou.getCrn() + ".dat"));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.close();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static void deactivateAStudent(Student stu){
		//Step1: Remove from Students.dat
			String studentsFile = "Students.dat";
			try {
				Scanner scAS = new Scanner(new File(studentsFile));
				String[] infoAS = new String[500];
				String line = "";
				int i = 0, j = 0;
				
				while(scAS.hasNextLine()){
					line = scAS.nextLine();
					if(!stu.getId().equals(line.split("\t")[0])){
						infoAS[i] = line;
						i++;
					}	
				}
				scAS.close();
				
				PrintWriter pwAS = new PrintWriter(new File(studentsFile));
				while(j < i){
					pwAS.println(infoAS[j]);
					j++;
				}
				pwAS.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		//Step2: Remove from individual courses files
			List<Course> selCouList = makeCoursesList(stu.getId());
			String studentID = stu.getId();
			int k = 0;
			while(selCouList.listIterator(k).hasNext()){
				String courseFile = "C" + selCouList.get(k).getCrn() + ".dat";
				try {
					Scanner scIC = new Scanner(new File(courseFile));
					String[] infoIC = new String[100];
					String line = "";
					int i = 0, j = 0;
					while(scIC.hasNextLine()){
						line = scIC.nextLine();
						if(!line.split("\t")[0].equals(studentID)){
							infoIC[i] = line;
							i++;
						}
					}
					scIC.close();
					
					PrintWriter pwIC = new PrintWriter(new File(courseFile));
					while(j < i){
						pwIC.println(infoIC[j]);
						j++;
					}
					pwIC.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				k++;
			}
		//Step3: Remove from advisor List
			try {
				String advisorsFile = "Advisors.dat";
				Scanner sc = new Scanner(new File(advisorsFile));
				String fa = stu.getFa().getId();
				String[] info = new String[100];
				String[] split = new String[100];
				String line;
				int i = 0, j = 0;
				while(sc.hasNextLine()){
					line = sc.nextLine();
					if(!fa.equals(line.split("\t")[0])){
						info[i] = line;
					}
					else{
						split = line.split("\t");
						info[i] = split[0] + "\t";
						for(k = 1; k < split.length; k++){
							if(!split[k].equals(studentID)){
								info[i] = info[i] + split[k] + "\t";
							}
						}

					}
					i++;
				}
					
				PrintWriter pw = new PrintWriter(new File(advisorsFile));
				while(j < i){
					pw.println(info[j]);
					j++;
				}
				pw.close();
				sc.close();
			}
			 catch (FileNotFoundException e) {
			e.printStackTrace();
			 }	
			//Stpe4-6
			deactivateAPerson(stu);
	}
	public static void deactivateATeacher(Professor pro){
		//Step1: Remove from Teachers.dat
		String teachersFile = "Teachers.dat";
		try {
			Scanner scAS = new Scanner(new File(teachersFile));
			String[] infoAS = new String[500];
			String line = "";
			int i = 0, j = 0;
			
			while(scAS.hasNextLine()){
				line = scAS.nextLine();
				if(!pro.getId().equals(line.split("\t")[0])){
					infoAS[i] = line;
					i++;
				}	
			}
			scAS.close();
			
			PrintWriter pwAS = new PrintWriter(new File(teachersFile));
			while(j < i){
				pwAS.println(infoAS[j]);
				j++;
			}
			pwAS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	//Step2: Remove from individual courses files //Step8 Remove from Semester CourseCatalog.dat
		List<Course> selCouList = makeCoursesList(pro.getId());
		int k = 0, i = 0;
		while(selCouList.listIterator(k).hasNext()){
			String semeBelong = "" , crn = selCouList.get(k).getCrn();
			for (i = 0; i < 6; i++){
				semeBelong = semeBelong + crn.charAt(i);
			}
			try {
				Scanner sc = new Scanner(new File(semeBelong + "CourseCatalog.dat"));
				String[] infoAS = new String[500];
				String line = "";
				i = 0; 
				int j = 0;
				
				while(sc.hasNextLine()){
					line = sc.nextLine();
					if(!pro.getId().equals(line.split("\t")[4])){
						infoAS[i] = line;
					}	
					else{
						infoAS[i] = line.split("\t")[0] + "\t" + line.split("\t")[1] + "\t" +
								line.split("\t")[2] + "\t" + line.split("\t")[3] +"\t"+ "000000000";
					}
					i++;
				}
				sc.close();	
				PrintWriter pwAS = new PrintWriter(new File(semeBelong + "CourseCatalog.dat"));
				while(j < i){
					pwAS.println(infoAS[j]);
					j++;
				}
				pwAS.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			removeACourseFromAProfessor(pro, selCouList.get(k).getCrn());
			k++;
		}
	//Step3: Remove from advisor List
		try {
			String advisorsFile = "Advisors.dat";
			Scanner sc = new Scanner(new File(advisorsFile));
			String[] info = new String[100];
			String[] split = new String[100];
			String line;
			i = 0; int j = 0;
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if(!pro.getId().equals(line.split("\t")[0])){
					info[i] = line;
					i++;
				}
				else{
					split = line.split("\t");
					for(k = 1; k < split.length; k++){
						info[0] = info[0] + split[k] + "\t";
						Student stu = makeAStudent(split[k]);
						stu.setFa(makeAProfessor("000000000"));
						saveAPersonFirstLineInfo(stu);
					}
				}		
			}			
			PrintWriter pw = new PrintWriter(new File(advisorsFile));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.close();
			sc.close();
		}
		 catch (FileNotFoundException e) {
		e.printStackTrace();
		 }	
		//Stpe4-6
		deactivateAPerson(pro);
		//Step7 Remove from All CourseCatalog.dat
		try {
			Scanner sc = new Scanner(new File("AllCourseCatalog.dat"));
			String[] infoAS = new String[500];
			String line = "";
			i = 0; 
			int j = 0;
			
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if(!pro.getId().equals(line.split("\t")[4])){
					infoAS[i] = line;
				}	
				else{
					infoAS[i] = line.split("\t")[0] + "\t" + line.split("\t")[1] + "\t" +
							line.split("\t")[2] + "\t" + line.split("\t")[3] +"\t"+ "000000000";
				}
				i++;
			}
			sc.close();
			
			PrintWriter pwAS = new PrintWriter(new File("AllCourseCatalog.dat"));
			while(j < i){
				pwAS.println(infoAS[j]);
				j++;
			}
			pwAS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Step8 Remove courseInfo from Teacher individual file
		try {
			Scanner sc = new Scanner(new File(pro.getId() + ".dat"));
			String info = sc.nextLine();
			
			PrintWriter pw = new PrintWriter(new File(pro.getId() + ".dat"));
			pw.println(info);
			
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void deactivateAPerson(Person per){
		//Step4: change person individual active status from "A" to "D"; 
			per.setActive("D");
			try {
				Scanner sc = new Scanner(new File(per.getId() + ".dat"));
				String[] info = new String[100];
				int i = 0, j = 0;
				while(sc.hasNextLine()){
					info[i] = sc.nextLine();
					i++;
				}
				PrintWriter pw = new PrintWriter(new File(per.getId() + ".dat"));
				info[0] = per.formingForISF();
				while(j < i){
					pw.println(info[j]);
					j++;
				}
				sc.close();
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		//Step5: Add he/she to DeactivatePerson.dat Format:ID + Name + Type + "D"
			File fl = new File("DeactivatePerson.dat");
			String type = findATypeOfAPerson(per);
			try {
				Scanner sc = new Scanner(fl);
				String[] info = new String[1000];
				int i = 0, j = 0;
				while(sc.hasNext()){
					info[i] = sc.nextLine();
					i++;
				}
				String infoS = per.getId() + "\t" + per.getName() + "\t" + type + "\t" + "D";
				PrintWriter pw = new PrintWriter(fl);
				while(j < i){
					pw.println(info[j]);
					j++;
				}
				pw.println(infoS);
				sc.close();
				pw.close();
				
				} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		//Step6: Refresh the AllPerson.dat
			fl = new File("AllPersons.dat");
			try {
				fl.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SRSFunctions.createAllPersonFile();
	}
	public static void deactivateACourse(String crn){
		Course cou = makeACourse(crn);
		List<Student> stuList = makeStudentsList(crn);
		int index = 0;
		//Step1: Remove Course from Individual Student Files
		index = 0;
		while(stuList.listIterator(index).hasNext()){
			Student stu = stuList.get(index);
			removeACourseFromAStudent(stu, crn);
			index++;
		}
		//Step2: Remove Course from Individual Teacher Files
		Professor pro = makeAProfessor(cou.getTeacherID());
		removeACourseFromAProfessor(pro, crn);
		//Step3: Remove Teacher from Individual Course File
		cou.setTeacherID("000000000");
		saveACourseFirstLineInfo(cou);
		//Step4: Remove Course from Semester CourseCatalog
		removeFromSemesterCourseCatalog(crn);
		
	}
	public static void reactivateATeacher(Professor pro){
		//Step1: add to Teachers.dat
		String teachersFile = "Teachers.dat";
		try {
			Scanner scAS = new Scanner(new File(teachersFile));
			String[] infoAS = new String[500];
			String line = "";
			int i = 0, j = 0;
			
			while(scAS.hasNextLine()){
				line = scAS.nextLine();
				infoAS[i] = line;
				i++;
			}
			scAS.close();
			
			PrintWriter pwAS = new PrintWriter(new File(teachersFile));
			while(j < i){
				pwAS.println(infoAS[j]);
				j++;
			}
			pro.setActive("A");
			pwAS.println(pro.formingForASF());
			pwAS.close();
			sort(teachersFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	//Step2:add to individual courses files //Step8 Remove from Semester CourseCatalog.dat
	//Step3:add to advisor List
	//Stpe4-6
		reactivateAPerson(pro);
	}
	public static void reactivateAStudent(Student stu){
	//Step1: Add to Students.dat
		String studentsFile = "Students.dat";
		try {
			Scanner scAS = new Scanner(new File(studentsFile));
			String[] infoAS = new String[500];
			String line = "";
			int i = 0, j = 0;
			
			while(scAS.hasNextLine()){
				line = scAS.nextLine();
				infoAS[i] = line;	
				i++;
			}
			scAS.close();
			PrintWriter pwAS = new PrintWriter(new File(studentsFile));
			while(j < i){
				pwAS.println(infoAS[j]);
				j++;
			}
			stu.setActive("A");
			pwAS.println(stu.formingForASF());
			pwAS.close();
			//sort(studentsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	//Step2: add to individual courses files
		List<Course> selCouList = makeCoursesList(stu.getId());
		int k = 0;
		while(selCouList.listIterator(k).hasNext()){
			String courseFile = "C" + selCouList.get(k).getCrn() + ".dat";
			try {
				Scanner scIC = new Scanner(new File(courseFile));
				String[] infoIC = new String[100];
				String line = "";
				int i = 0, j = 0;
				while(scIC.hasNextLine()){
					line = scIC.nextLine();
					infoIC[i] = line;
					i++;
				}
				scIC.close();
				/*
				 * get transcript!
				 */
				String info = null;
				Scanner scIS = new Scanner(new File(stu.getId() + ".dat"));
				while(scIS.hasNext()){
					line = scIS.nextLine();
					if(line.split("\t")[0].equals(selCouList.get(k).getCrn())){
						info = stu.getId() + "\t" + line.split("\t")[1] + "\t" + line.split("\t")[2]
								+ "\t";
					}
				}
				scIS.close();
				
				PrintWriter pwIC = new PrintWriter(new File(courseFile));
				while(j < i){
					pwIC.println(infoIC[j]);
					j++;
				}
				
				pwIC.println(info);
				pwIC.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			k++;
		}
	//Step3: add to "000000000" advisor List
		try {
			String advisorsFile = "Advisors.dat";
			Scanner sc = new Scanner(new File(advisorsFile));
			String fa = "000000000";
			stu.setFa(SRSFunctions.makeAProfessor(fa));
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			
			String[] info = new String[100];
			String[] split = new String[100];
			String line;
			int i = 0, j = 0;
			while(sc.hasNextLine()){
				line = sc.nextLine();
				split = line.split("\t");
				if (split[0].equals(fa)){
					info[i] = line + stu.getId() + "\t";
				}
				else{
					info[i] = line;
				}
				i++;
			}
			PrintWriter pw = new PrintWriter(new File(advisorsFile));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		//Stpe4-6
		reactivateAPerson(stu);
	}
	public static void reactivateAPerson(Person per){
	//Step4: change person individual active status from "D" to "A"; 
		per.setActive("A");
		try {
			Scanner sc = new Scanner(new File(per.getId() + ".dat"));
			String[] info = new String[100];
			int i = 0, j = 0;
			while(sc.hasNextLine()){
				info[i] = sc.nextLine();
				i++;
			}
			PrintWriter pw = new PrintWriter(new File(per.getId() + ".dat"));
			info[0] = per.formingForISF();
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	//Step5: Remove he/she to DeactivatePerson.dat
		File fl = new File("DeactivatePerson.dat");
		try {
			Scanner sc = new Scanner(fl);
			String[] info = new String[1000];
			String line;
			int i = 0, j = 0;
			while(sc.hasNext()){
				line = sc.nextLine();
				if(!line.split("\t")[0].equals(per.getId())){
					info[i] = line;
					i++;
				}
			}
			PrintWriter pw = new PrintWriter(fl);
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
			
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	//Step6: Refresh the AllPerson.dat
		fl = new File("AllPersons.dat");
		try {
			fl.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SRSFunctions.createAllPersonFile();
	}
	public static void reactivateACourse(String crn){
		
	}
	
	public static void removeFromSemesterCourseCatalog(String crn){
		String semeFileName = crn.substring(0, 6) + "CourseCatalog.dat";
		try {
			Scanner sc = new Scanner(new File(semeFileName));
			String[] info = new String[100];
			String line = "";
			int i = 0, j = 0;
			
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if(!line.split("\t")[0].equals(crn)){
					info[i] = line;
					i++;
				}
			}
			
			PrintWriter pw = new PrintWriter(new File(semeFileName));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void removeFromAllCourseCatalog(String crn){
		try {
			Scanner sc = new Scanner(new File("AllCourseCatalog.dat"));
			String[] info = new String[100];
			String line = "";
			int i = 0, j = 0;
			
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if(!line.split("\t")[0].equals(crn)){
					info[i] = line;
					i++;
				}
			}
			
			PrintWriter pw = new PrintWriter(new File("AllCourseCatalog.dat"));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void removeACourseFromAProfessor(Professor pro, String crn){
		int index = 0;
		int k = 0;
		List<Course> couInTeaList = makeCoursesList(pro.getId());
		while(couInTeaList.listIterator(index).hasNext()){
			if(couInTeaList.get(index).getCrn().equals(crn)){
				k = index;
				break;
			}			
			index++;
		}
		Course cou =  couInTeaList.get(k);
		cou.setTeacherID("000000000");
		saveACourseFirstLineInfo(cou);
		couInTeaList.remove(k);
		saveAProfessorCourseInfoByList(pro, couInTeaList);
	}
	public static void removeACourseFromAStudent(Student stu, String crn){
		int index = 0;
		int k = 0;
		List<Course> couInTeaList = makeCoursesList(stu.getId());
		while(couInTeaList.listIterator(index).hasNext()){
			if(couInTeaList.get(index).getCrn().equals(crn)){
				k = index;
				break;
			}			
			index++;
		}
		couInTeaList.remove(k);
		saveAStudentCourseInfoByList(stu, couInTeaList);
	}
	public static void removeAStudentFromACourse(Student stu, String crn){
		int index = 0;
		int k = 0;
		List<Student> stuInCouList = makeStudentsList(crn);
		while(stuInCouList.listIterator(index).hasNext()){
			if(stuInCouList.get(index).equals(stu.getId())){
				k = index;
			}
			index++;
		}
		stuInCouList.remove(k);
		saveACourseStudentInfoByList(makeACourse(crn), stuInCouList);
	}
	
	public static void addToSemesterCourseCatalog(String crn){
		String semeFileName = crn.substring(0, 6) + "CourseCatalog.dat";
		try {
			File fl = new File(semeFileName);
			if(!fl.exists()){
				PrintWriter pw = new PrintWriter(fl);
				pw.print("");
				pw.close();
			}
			Scanner sc = new Scanner(fl);
			String[] info = new String[100];
			String line = "";
			int i = 0, j = 0;
			
			while(sc.hasNextLine()){
				line = sc.nextLine();
				info[i] = line;
				i++;
			}
			
			PrintWriter pw = new PrintWriter(new File(semeFileName));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.println(makeACourse(crn).formingForACF());
			sort(semeFileName);
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void addToAllCourseCatalog(String crn){
		try {
			Scanner sc = new Scanner(new File("AllCourseCatalog.dat"));
			String[] info = new String[100];
			String line = "";
			int i = 0, j = 0;
			
			while(sc.hasNextLine()){
				line = sc.nextLine();
				info[i] = line;
				i++;
			}
			
			PrintWriter pw = new PrintWriter(new File("AllCourseCatalog.dat"));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.println(makeACourse(crn).formingForACF());
			sort("AllCourseCatalog.dat");
			sc.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void addACourseToAProfessor(Professor pro, String crn){
		List<Course> couInTeaList = makeCoursesList(pro.getId());
		Course cou = makeACourse(crn);
		cou.setTeacherID(pro.getId());
		couInTeaList.add(cou);
		saveACourseFirstLineInfo(cou);
		saveAProfessorCourseInfoByList(pro, couInTeaList);
	}
	public static void addACourseToAStudent(Student stu, String crn){
		List<Course> couInTeaList = makeCoursesList(stu.getId());
		Course cou = makeACourse(crn);
		couInTeaList.add(cou);
		saveAStudentCourseInfoByList(stu, couInTeaList);
	}
	public static void addAStudentToACourse(Student stu, String crn){
		List<Student> stuInCouList = makeStudentsList(crn);
		Course cou = makeACourse(crn);
		stuInCouList.add(stu);
		Transcript tran = new Transcript();
		tran.setCourse(cou);
		tran.setGrade("N/A");
		tran.setComplete("D");
		tran.setStudent(stu);
		cou.setTran(tran);
		try {
			Scanner sc = new Scanner(new File("C" + cou.getCrn() + ".dat"));
			String[] info = new String[100];
			String line;
			int i = 1 , index = 0, j = 0;
			line = sc.nextLine();
			info[0] = line;
			while(stuInCouList.listIterator(index).hasNext()){
				String infoISF = stuInCouList.get(index).getId() +"\t"
						+ tran.getGrade() + "\t" + tran.getComplete() + "\t";
				info[i] = infoISF;
				i++;
				index++;
			}
			PrintWriter pw = new PrintWriter(new File("C" + cou.getCrn() + ".dat"));
			while(j < i){
				pw.println(info[j]);
				j++;
			}
			pw.close();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @return act = true if founded
	 */
	public static boolean checkForPerson(String id){
		boolean act = false;
		if (id.length() < 9 || id.length() > 9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}	
		else{
				File fl = new File(id + ".dat");
				if (!fl.exists()){
					SRSInfoNotPassed frame = new SRSInfoNotPassed();
					frame.setVisible(true);
					act = false;
				}
				else act = true;
			
		}
		return act;
	}
	public static boolean checkForCourse(String id){
		boolean act = false;
		if (id.length() < 9 || id.length() > 9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}	
		else{
				File fl = new File("C" + id + ".dat");
				if (!fl.exists()){
					SRSInfoNotPassed frame = new SRSInfoNotPassed();
					frame.setVisible(true);
					act = false;
				}
				else act = true;
			
		}
		return act;
	}
	public static boolean checkForActive(String id){
		boolean active = false;
		try {
			Scanner sc = new Scanner(new File("AllPersons.dat"));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				if(line.split("\t")[0].equals(id)){
					String act = line.split("\t")[3];
					if(act.equals("A")){
						active = true;
						break;
					}
					else{
						SRSPersonDeactive frame = new SRSPersonDeactive();
						frame.setVisible(true);
						break;
					}
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return active;
	}
	public static boolean checkForActiveCourse(String crn){
		boolean active = false;
		Course cou = makeACourse(crn);
		if(cou.getActive().equals("A")){
			active = true;
		}
		return active;
	}
	
	
	public static void main(String[] arg){
//		System.out.print(checkForActiveCourse("201501001"));
//		checkForActive("901012015");
//		makeACourse("201501001");
//		makeAStudent("900012015");
//		makeATranscript("900012015", "201501001");
//		makeStudentsList("201501001").get(0).dispaly();
//		saveAPersonFirstLineInfo(makeAStudent("900012015"));
//		System.out.println(checkForActive("900012015"));
//		deactivateACourse("201501001");
//		saveAStudentCourseInfoByList(makeAStudent("900012015"), makeCoursesList("900012015"));
//		saveAProfessorCourseInfoByList(makeAProfessor("800010001"), makeCoursesList("800010001"));
		createAllPersonFile();
//		refreshAdvisors();
//		removeFromSemesterCourseCatalog("201501001");
//		System.out.println(findATypeOfAPerson(makeAStudent("900012015")));
//		deactivateATeacher(makeAProfessor("800070007"));
//		deactivateAStudent(makeAStudent("901012015"));
//		reactivateAStudent(makeAStudent("901012015"));
//		reactivateATeacher(makeAProfessor("800070007"));
//		sort("201501CourseCatalog.dat");
//		sort("Students.dat");
//		sort("AllPersons.dat");
	}
}