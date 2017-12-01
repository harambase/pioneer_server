package TecherGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

import NewCore.Course;
import NewCore.Professor;
import NewCore.Student;
import NewCore.Transcript;
import SRSDataBase.SRSFunctions;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextPane;

public class SRSVieAdvStu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField idField;
	private JTextField yyField;
	private JTextField telField;
	private JTextField qqField;
	private JTextField eField;
	private JTextField dormField;
	private JList<String> couList2;
	private JButton btnActiveForDropping;
	private JButton btnDeactiveForDropping;
	private JButton btnActiveForAdding;
	private JButton btnDectiveForAdding;
	private JButton returnButton;
	private JScrollPane couList;
	private JRadioButton fButton;
	private JRadioButton mButton;
	private JTextField passField;
	private Course cou;
	private JTextField faNameField;
	private JLabel lblStudentInformationForm;
	private JTextPane textPane;
	private Student stu = SRSTeacherGUI.student;
	private JLabel lblStudentRegisteredCourses;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSVieAdvStu frame = new SRSVieAdvStu();
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
	public SRSVieAdvStu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 784, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		returnButton = new JButton("Close");
		returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		
		JLabel lblStudentsenglish = new JLabel("Student's (English):*");
		lblStudentsenglish.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblStudentspassword = new JLabel("Student's ]Password:*");
		lblStudentspassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblStudentsIdNumber = new JLabel("Student's ID number:*");
		lblStudentsIdNumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setText("");
		nameField.setColumns(10);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setText("");
		idField.setColumns(10);
		
		yyField = new JTextField();
		yyField.setEditable(false);
		yyField.setText("");
		yyField.setColumns(10);
		
		JLabel lblStudentsBirthday = new JLabel("Student's Birthday:");
		lblStudentsBirthday.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_5 = new JLabel("Student's Contact Infomation(Optional)");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_6 = new JLabel("QQ:");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_7 = new JLabel("Telephone:");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_8 = new JLabel("Email:");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		telField = new JTextField();
		telField.setEditable(false);
		telField.setText("");
		telField.setColumns(10);
		
		qqField = new JTextField();
		qqField.setEditable(false);
		qqField.setText("");
		qqField.setColumns(10);
		
		eField = new JTextField();
		eField.setEditable(false);
		eField.setText("");
		eField.setColumns(10);
		
		dormField = new JTextField();
		dormField.setEditable(false);
		dormField.setText("");
		dormField.setColumns(10);
		
		JLabel label_9 = new JLabel("Student's Assigned Dormitory:");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_10 = new JLabel("Student's Assigned Faculty Advisor:");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_11 = new JLabel("Gender:*");
		label_11.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		mButton = new JRadioButton("Male");
		mButton.setBackground(Color.WHITE);
		mButton.setForeground(Color.BLACK);
		mButton.setEnabled(false);
		mButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		fButton = new JRadioButton("Female");
		fButton.setBackground(Color.WHITE);
		fButton.setForeground(Color.BLACK);
		fButton.setEnabled(false);
		fButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		passField = new JTextField();
		passField.setEditable(false);
		passField.setText("");
		passField.setColumns(10);
		
		faNameField = new JTextField();
		faNameField.setEditable(false);
		faNameField.setText("");
		faNameField.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Times New Roman", Font.BOLD, 13));
		textPane.setBackground(SystemColor.info);
		textPane.setForeground(Color.RED);
		textPane.setEditable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStudentsenglish, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStudentsIdNumber)
												.addComponent(lblStudentspassword, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
											.addGap(18))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblStudentsBirthday, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(nameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
										.addComponent(idField, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
										.addComponent(passField, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
										.addComponent(yyField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_5, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
									.addGap(19))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(dormField, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(label_7)
												.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
											.addGap(23)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(qqField, 217, 217, 217)
												.addComponent(telField, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
												.addComponent(eField, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(faNameField, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
										.addComponent(fButton, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(12))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(mButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(152))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentsIdNumber)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentspassword)
						.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentsenglish)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentsBirthday, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(yyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(telField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(qqField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(eField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(dormField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(faNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(mButton)
						.addComponent(fButton))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JLabel label_13 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		JLabel label_14 = new JLabel("Viewing Student Mode");
		label_14.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnActiveForAdding = new JButton("Active for adding");
		btnActiveForAdding.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDectiveForAdding = new JButton("Deactive for adding");
		btnDectiveForAdding.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnActiveForDropping = new JButton("Active for dropping");
		btnActiveForDropping.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeactiveForDropping = new JButton("Deactive for dropping");
		btnDeactiveForDropping.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblBstudentInfomation = new JLabel("Student Registration Buttons");
		lblBstudentInfomation.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		couList2 = new JList<String>();
		couList = new JScrollPane(couList2);
		
		lblStudentInformationForm = new JLabel("Student Information Form");
		lblStudentInformationForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblStudentRegisteredCourses = new JLabel("Student All Registered Courses");
		lblStudentRegisteredCourses.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStudentInformationForm, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(couList, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addComponent(lblBstudentInfomation, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnActiveForDropping, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnActiveForAdding, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDeactiveForDropping, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
										.addComponent(btnDectiveForAdding, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
								.addComponent(returnButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addComponent(lblStudentRegisteredCourses, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))))
					.addGap(28))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentInformationForm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentRegisteredCourses, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(couList, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBstudentInfomation, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnActiveForAdding)
								.addComponent(btnDectiveForAdding))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnActiveForDropping)
								.addComponent(btnDeactiveForDropping))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(returnButton))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		btnActiveForDropping.addActionListener(this);
		btnDeactiveForDropping.addActionListener(this);
		btnActiveForAdding.addActionListener(this);
		btnDectiveForAdding.addActionListener(this);
		returnButton.addActionListener(this);
		fButton.addActionListener(this);
		mButton.addActionListener(this);
		viewStu();
	}
	
	
	public void viewCou(){
		String studentID =  stu.getId();
		String studentFile = studentID + ".dat";
		try {
			Scanner scS = new Scanner(new File(studentFile));
			String[] courses = new String[100];		
			String[] split = new String[10];
			int i = 1;
			scS.nextLine();
			courses[0] = "Format: Course crn + Course Name + Instructor's Name + Grade + Complete(C/D)";
			while(scS.hasNextLine()){
				split = scS.nextLine().split("\t");
				cou = SRSFunctions.makeACourse(split[0]);
				Transcript tran = SRSFunctions.makeATranscript(studentID, cou.getCrn());
				courses[i] = cou.getCrn() + "     " + cou.getName() + "     " +  cou.getPro().getName() + "     "
						+  tran.getGrade() + "    " + tran.getComplete() ; 
				i++;
			}
			courses[i] = "Total Registered courses " + (i - 1) + ".";
			couList2.setListData(courses);
			scS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void viewStu(){
		String studentID = stu.getId();
		String studentFile = studentID + ".dat";
		//Student file: ID + Password +  Name + Active? + Credits + type + birth + tele + qq + email + dorm
		// 		 + Faculty Advisor + ActiveforDrop(AD/DD) + ActiveforAdd(AA/DA) + Gender	
		try {
			Scanner scS = new Scanner(new File(studentFile));
			String[] infoS = new String[30];

			infoS = scS.nextLine().split("\t");
			
			String password = infoS[1];
			String name = infoS[2];
			String birth = infoS[6];
			String tele = infoS[7];
			String qq = infoS[8];
			String email = infoS[9];
			String dorm = infoS[10];
			String fa = infoS[11];
			String gender = infoS[14];

			idField.setText(studentID);
			passField.setText(password);
			nameField.setText(name);
			telField.setText(tele);
			qqField.setText(qq);
			eField.setText(email);
			dormField.setText(dorm);
			yyField.setText(birth);
			
			if(!fa.equals("000000000")){
				Professor pro = SRSFunctions.makeAProfessor(fa);
				faNameField.setText(pro.getName());
			}
			else{
				faNameField.setText("N/A");
			}
			if(gender.equals("Male")){
				mButton.setSelected(true);
				fButton.setSelected(false);
			}
			else{
				fButton.setSelected(true);
				mButton.setSelected(false);
			}
			String actA = stu.getActA();
			String actD = stu.getActD();
			boolean acta = true;
			boolean actd = true;
			String txt = "";
			
			if(actA.equals("DA")){
				acta = false;
			}
			if(actD.equals("DD")){
				actd = false;
			}
			
			if(acta){
				txt = "Notifictation1: This Student is ABLE to add new courses.";
			}
			else{
				txt = "Notifictation1: This Student is UNABLE to add new courses now.";
			}
			if(actd){
				txt = txt + "\nNotifictation2: This Student is ABLE to drop courses.";
			}
			else{
				txt = txt + "\nNotifictation2: This Student is UNABLE to drop courses.";
			}
			txt = txt + "\nNotifictation3: This student has earned " + stu.getCredits() + " credits.";
			textPane.setText(txt);
			scS.close();
			stu = SRSFunctions.makeAStudent(studentID);
			viewCou();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}	
	
	public void deactivateSeleStudentForDropping(){
		stu.setActD("DD");
		SRSFunctions.saveAPersonFirstLineInfo(stu);
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		viewStu();
	}
	public void activateSeleStudentForDropping(){
		stu.setActD("AD");
		SRSFunctions.saveAPersonFirstLineInfo(stu);
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		viewStu();
	}
	public void deactivateSeleStudentForAdding(){
		stu.setActA("DA");
		SRSFunctions.saveAPersonFirstLineInfo(stu);
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		viewStu();
	}
	public void activateSeleStudentForAdding(){
		stu.setActA("AA");
		SRSFunctions.saveAPersonFirstLineInfo(stu);
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		viewStu();
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == fButton){
			mButton.setSelected(false);
		}
		else if(source == mButton){
			fButton.setSelected(false);
		}
		else if(source == returnButton){
			this.dispose();
		}
		else if(source == btnDeactiveForDropping){
			deactivateSeleStudentForDropping();
		}
		else if(source == btnActiveForDropping){
			activateSeleStudentForDropping();
		}
		else if(source == btnDectiveForAdding){
			deactivateSeleStudentForAdding();
		}
		else if(source == btnActiveForAdding){
			activateSeleStudentForAdding();
		}
	}

}
