package AdminGUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JButton;

import AdminGUIBars.SRSSaveSucceed;
import NewCore.Person;
import NewCore.Professor;
import NewCore.Student;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSAdInterface;


@SuppressWarnings("serial")
public class SRSViePerson extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnReturnToPervious;
	private JButton btnRefresh;
	private JButton btnDeactivateStu;
	private JButton btnDeactivateTea;
	private JButton btnReactivate;
	private JList<String> actStuList;
	private JList<String> actTeaList;
	private JList<String> deList;
	private JButton btnDea;
	private JButton btnActTea;
	private JButton btnActStu;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSViePerson frame = new SRSViePerson();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SRSViePerson() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JLabel label_1 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		JLabel lblViewingRegisteredPersons = new JLabel("Viewing Registered Persons Mode");
		lblViewingRegisteredPersons.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		btnDeactivateStu = new JButton("Deactivate");
		btnDeactivateStu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDeactivateStu.addActionListener(this);
		
		btnDeactivateTea = new JButton("Deactivate");
		btnDeactivateTea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDeactivateTea.addActionListener(this);
		
		btnReactivate = new JButton("Reactivate");
		btnReactivate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnReactivate.addActionListener(this);
		
		btnReturnToPervious = new JButton("Return to Pervious Menu");
		btnReturnToPervious.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnReturnToPervious.addActionListener(this);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnRefresh.addActionListener(this);
		
		btnActStu = new JButton("Create All Active Students .csv File");
		btnActStu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnActStu.addActionListener(this);
		
		btnActTea = new JButton("Create All Active Teachers .csv File");
		btnActTea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnActTea.addActionListener(this);
		
		btnDea = new JButton("Create All Deactivate Persons .csv File");
		btnDea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDea.addActionListener(this);
		
		btnNewButton = new JButton("Create All Persons (includes deactivate/activate students and teachers).csv File");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblViewingRegisteredPersons, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDeactivateStu, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
										.addComponent(btnActStu, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnActTea, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(10)
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnDeactivateTea, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReactivate, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
								.addComponent(btnReturnToPervious, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
								.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
								.addComponent(btnDea, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReturnToPervious))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblViewingRegisteredPersons, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnActStu)
						.addComponent(btnActTea)
						.addComponent(btnDea))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReactivate)
						.addComponent(btnDeactivateTea)
						.addComponent(btnDeactivateStu))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1))
		);
		
		JLabel lblDeactivePersons = new JLabel("Deactive Persons");
		lblDeactivePersons.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrollPane_2.setColumnHeaderView(lblDeactivePersons);
		
		deList = new JList<String>();
		scrollPane_2.setViewportView(deList);
		
		JLabel lblActiveTeachers = new JLabel("Active Teachers");
		lblActiveTeachers.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrollPane_1.setColumnHeaderView(lblActiveTeachers);
		
		actTeaList = new JList<String>();
		scrollPane_1.setViewportView(actTeaList);
		
		JLabel lblActiveStudents = new JLabel("Active Students");
		lblActiveStudents.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scrollPane.setColumnHeaderView(lblActiveStudents);
		
		actStuList = new JList<String>();
		scrollPane.setViewportView(actStuList);
		contentPane.setLayout(gl_contentPane);
		showStu();
		showTea();
		showDea()
;	}
	public void showStu(){
		try {
			SRSFunctions.sort("Students.dat");
			Scanner sc = new Scanner(new File("Students.dat"));
			String[] info = new String[1000];
			int i = 1;
			
			info[0] = "Format: student's ID + student's name";
			while(sc.hasNext()){
				String line = sc.nextLine();
				info[i] = line.split("\t")[0] + "     " + line.split("\t")[2];
				i++;
			}
			sc.close();
			info[i+1] = "Total Active Student Amount = " + (i - 1) + ".";
			actStuList.setListData(info);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void showTea(){
		try {
			SRSFunctions.sort("Teachers.dat");
			Scanner sc = new Scanner(new File("Teachers.dat"));
			String[] info = new String[1000];
			int i = 1;
			
			info[0] = "Format: teacher's ID + teacher's name";
			while(sc.hasNext()){
				String line = sc.nextLine();
				info[i] = line.split("\t")[0] + "     " + line.split("\t")[2];
				i++;
			}
			sc.close();
			info[i+1] = "Total Active Teacher Amount = " + (i - 1) + ".";
			actTeaList.setListData(info);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void showDea(){
		try {
			SRSFunctions.sort("DeactivatePerson.dat");
			Scanner sc = new Scanner(new File("DeactivatePerson.dat"));
			String[] info = new String[1000];
			int i = 1;
			
			info[0] = "Format: Person's ID + Person's name" + "Person's Type";
			while(sc.hasNext()){
				String line = sc.nextLine();
				info[i] = line.split("\t")[0] + "     " + line.split("\t")[1];
				if(line.split("\t")[2].equals("T")){
					info[i] = info[i] + "      " + "Teacher";
				}
				else{
					info[i] = info[i] + "      " + "Student";
				}
				i++;
			}
			sc.close();
			info[i+1] = "Total Deactive Persons Amount = " + (i - 1) + ".";
			deList.setListData(info);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void deactivateAStudent(){
		String studentID =  actStuList.getSelectedValue().substring(0, 9);
		Student stu = SRSFunctions.makeAStudent(studentID);
		SRSFunctions.deactivateAStudent(stu);
		showStu();
		showDea();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void deactivateATeacher(){
		String teacherID =  actTeaList.getSelectedValue().substring(0, 9);
		Professor pro = SRSFunctions.makeAProfessor(teacherID);
		SRSFunctions.deactivateATeacher(pro);
		showTea();
		showDea();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void reactivate(){
		String id =  deList.getSelectedValue().substring(0, 9);
		Person per = new Student();
		per.setId(id);
		
		String type = SRSFunctions.findATypeOfAPerson(per);
		System.out.print(type);
		if(type.equals("T")){
			Professor pro = SRSFunctions.makeAProfessor(id);	
			SRSFunctions.reactivateATeacher(pro);
			showTea();
			showDea();
		}
		else{
			Student stu = SRSFunctions.makeAStudent(id);
			SRSFunctions.reactivateAStudent(stu);
			showStu();
			showDea();
		}
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createStuFile(){
		SRSCreateReports.formActiveStudentsReport();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createTeaFile(){
		SRSCreateReports.formActiveTeachersReport();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createPerFile(){
		SRSCreateReports.formAllPersonsReport();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createDePerFile(){
		SRSCreateReports.formDeactivatePersonsReport();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == btnReturnToPervious){
			this.dispose();
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		}
		else if(source == btnRefresh){
			this.dispose();
			SRSViePerson frame = new SRSViePerson();
			frame.setVisible(true);
		}
		else if(source == btnDeactivateTea){
			deactivateATeacher();
		}
		else if(source == btnReactivate){
			reactivate();
		}
		else if(source == btnDeactivateStu){
			deactivateAStudent();
		}
		else if(source == btnActStu){
			createStuFile();
		}
		else if(source == btnActTea){
			createTeaFile();
		}
		else if(source == btnNewButton){
			createPerFile();
		}
		else if(source == btnDea){
			createDePerFile();
		}
	}
}
