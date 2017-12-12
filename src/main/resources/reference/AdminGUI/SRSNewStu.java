package AdminGUI;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import AdminGUIBars.SRSIdNotUsed;
import AdminGUIBars.SRSIdUsed;
import AdminGUIBars.SRSInfoPassed;
import AdminGUIBars.SRSPersonActive;
import AdminGUIBars.SRSRegisterError;
import AdminGUIBars.SRSSaveSucceed;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSAdInterface;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class SRSNewStu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton btnReturnToPrevious;
	private JButton btnInformationAboutHow;
	private JButton btnSaveForThis;
	private JTextField nameField;

	/**
	 * Launch the application.
	 */
	private static String studentsFile = "Students.dat";
	private static String[] info = new String[1000];
	private JButton btnCheckForThis;
	private JLabel label_1;
	private JButton btnResetForm;
	private JTextField yearField;
	private JTextField mmField;
	private JTextField ddField;
	private JLabel lblStudentsContactInfomation;
	private JTextField telField;
	private JLabel lblTelephone;
	private JLabel lblQq;
	private JTextField qqField;
	private JLabel lblEmail;
	private JTextField eField;
	private JLabel lblStudentsAssignedDormitory;
	private JTextField dormField;
	private JTextField faField;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnCheckForTeachers;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSNewStu frame = new SRSNewStu();
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
	public SRSNewStu() {
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		
		JLabel lblStudentNewId = new JLabel("Student's New ID number:*");
		lblStudentNewId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		idField = new JTextField();
		idField.setBackground(SystemColor.menu);
		idField.setColumns(10);
		
		JLabel lblStudentNewPassword = new JLabel("Student's New Password:*");
		lblStudentNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();
		passwordField.setBackground(SystemColor.menu);
		passwordField.setColumns(10);
		
		JLabel lblNewStudentRegistration = new JLabel("New Student Registration Form.");
		lblNewStudentRegistration.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnSaveForThis = new JButton("Save this one");
		btnSaveForThis.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnReturnToPrevious = new JButton("Return to Previous Menu");
		btnReturnToPrevious.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnInformationAboutHow = new JButton("Information about how to fill this form");
		btnInformationAboutHow.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnCheckForThis = new JButton("Check for ID whether being used");
		btnCheckForThis.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		label_1 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		btnResetForm = new JButton("Reset Form");
		btnResetForm.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnCheckForTeachers = new JButton("Check for Teacher's Status");
		btnCheckForTeachers.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnCheckForTeachers.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInformationAboutHow, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewStudentRegistration, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCheckForThis, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnReturnToPrevious, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(btnSaveForThis, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnResetForm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnCheckForTeachers, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(97)
					.addComponent(label_1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(6)
					.addComponent(lblNewStudentRegistration, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInformationAboutHow, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckForThis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckForTeachers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveForThis)
						.addComponent(btnResetForm))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReturnToPrevious, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addGap(125))
		);
		
		JLabel lblStudentNameenglish = new JLabel("Student's Name (English):*");
		lblStudentNameenglish.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		nameField = new JTextField();
		nameField.setBackground(SystemColor.menu);
		nameField.setColumns(10);
		
		JLabel lblStudentsBirthdayyyyymmdd = new JLabel("Student's Birthday (YYYY/MM/DD):*");
		lblStudentsBirthdayyyyymmdd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		yearField = new JTextField();
		yearField.setBackground(SystemColor.menu);
		yearField.setText("");
		yearField.setColumns(10);
		
		mmField = new JTextField();
		mmField.setBackground(SystemColor.menu);
		mmField.setText("");
		mmField.setColumns(10);
		
		ddField = new JTextField();
		ddField.setBackground(SystemColor.menu);
		ddField.setText("");
		ddField.setColumns(10);
		
		lblStudentsContactInfomation = new JLabel("Student's Contact Infomation(Optional)");
		lblStudentsContactInfomation.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		telField = new JTextField();
		telField.setBackground(SystemColor.menu);
		telField.setText("");
		telField.setColumns(10);
		
		lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		lblQq = new JLabel("QQ:");
		lblQq.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		qqField = new JTextField();
		qqField.setBackground(SystemColor.menu);
		qqField.setText("");
		qqField.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		eField = new JTextField();
		eField.setBackground(SystemColor.menu);
		eField.setText("");
		eField.setColumns(10);
		
		lblStudentsAssignedDormitory = new JLabel("Student's Assigned Dormitory:");
		lblStudentsAssignedDormitory.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		dormField = new JTextField();
		dormField.setBackground(SystemColor.menu);
		dormField.setText("");
		dormField.setColumns(10);
		
		JLabel lblStudentsAssignedFaculty = new JLabel("Student's Assigned Faculty Advisor:");
		lblStudentsAssignedFaculty.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		faField = new JTextField();
		faField.setBackground(SystemColor.menu);
		faField.setText("");
		faField.setColumns(10);
		
		JLabel lblThisTeachersId = new JLabel("This Teacher's ID: (leave blank if undetermined)");
		lblThisTeachersId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblGender = new JLabel("Gender:*");
		lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentNameenglish, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentNewPassword, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentNewId))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addComponent(idField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addGap(15))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(yearField, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(mmField, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(ddField, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStudentsBirthdayyyyymmdd, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addGap(17))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStudentsContactInfomation, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblQq, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelephone)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED, 13, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(telField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
										.addComponent(qqField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
										.addComponent(eField, Alignment.TRAILING)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(dormField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addGap(1)))
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStudentsAssignedDormitory, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(17, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentsAssignedFaculty, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rdbtnMale, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 97, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(17))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(faField, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addComponent(lblThisTeachersId, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentNewId)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentNewPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentNameenglish)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(lblStudentsBirthdayyyyymmdd, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ddField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStudentsContactInfomation, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(telField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelephone))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQq)
						.addComponent(qqField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(eField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentsAssignedDormitory, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(dormField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStudentsAssignedFaculty, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblThisTeachersId, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(faField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		nameField.setText("");
    	passwordField.setText("");
    	idField.setText("");
    	yearField.setText("");
    	ddField.setText("");
    	mmField.setText("");
    	qqField.setText("");
    	eField.setText("");
    	dormField.setText("");
    	telField.setText("");
    	
    	rdbtnMale.addActionListener(this);
    	rdbtnFemale.addActionListener(this);
		btnReturnToPrevious.addActionListener(this);
		btnInformationAboutHow.addActionListener(this);
		btnSaveForThis.addActionListener(this);
		btnCheckForThis.addActionListener(this);
		btnResetForm.addActionListener(this);
	}
	
	public boolean checkForConflict(){
		String newStudentID;
		boolean act = false;
		newStudentID = idField.getText();
		if (newStudentID.length() < 9 || newStudentID.length() > 9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}	
		else{
				File fl = new File(newStudentID + ".dat");
				if (!fl.exists()){
					SRSIdNotUsed frame = new SRSIdNotUsed();
					frame.setVisible(true);
					act = false;
				}
				else act = true;
			
		}
		if(act){
			SRSIdUsed frame = new SRSIdUsed();
			frame.setVisible(true);
		}
		return act;
	}
	public void additional(String birthday, String tele, String qq, String email, String dorm, 
			String fa, String studentID, String gender, String password, String name, boolean act) throws FileNotFoundException{
		//Student file: ID + Password +  Name + Active? + Credits + type + birth + tele + qq + email + dorm
				// 		 + Faculty Advisor + ActiveforDrop(AD/DD) + ActiveforAdd(AA/DA) + Gender	
				String additional = birthday + "\t" + tele + "\t" + qq + "\t" + email + "\t"
							+ dorm + "\t" + fa + "\t" +"AD" + "\t" + "AA" + "\t" + gender + "\t";
				if(studentID.length()<9 || studentID.length()>9){
					SRSRegisterError frame = new SRSRegisterError();
					frame.setVisible(true);
				}
				else if(act == false){
						addToAdvisor(fa);
						info = new String[1000];
						info[0] = studentID +"\t" +password+"\t" + name + "\t" + "A";
						try {
							Scanner scan = new Scanner(new File(studentsFile));
							int i = 1;
							while (scan.hasNextLine()){
								info[i] = scan.nextLine();
								i++;
							}
							int j = 0;
							PrintWriter pw = new PrintWriter(studentsFile);
							while(j < i){
								pw.println(info[j]);
								j++;
							}
							String individualFile = studentID + ".dat";
							PrintWriter out = new PrintWriter(individualFile);
							out.println(info[0] + "\t" + "0" + "\t" + "S" + "\t" + additional);
							pw.close();
							out.close();
							scan.close();
							
						}
						catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						catch (ArrayIndexOutOfBoundsException a){
							a.printStackTrace();
						}

						SRSSaveSucceed frame = new SRSSaveSucceed();
						frame.setVisible(true);
				
				}
				SRSFunctions.sort(studentsFile);
	}
	public void save() throws FileNotFoundException{
		String studentID, password, name;
		String birthday = "", tele, qq, email, dorm, fa, gender;
		boolean act;
		
		birthday = yearField.getText() + "/" + mmField.getText() + "/" + ddField.getText();
		tele = telField.getText();
		qq = qqField.getText();
		email = eField.getText();
		dorm = dormField.getText();
		studentID = idField.getText();
		password = String.copyValueOf(passwordField.getPassword());
		name =  nameField.getText();
		fa = faField.getText();
		act = checkForConflict();
		
		if(rdbtnMale.isSelected()){
			gender = "Male";
		}
		else{
			gender = "Female";
		}	
		if (qq.isEmpty()){
			qq = "N/A";
		}
		if (tele.isEmpty()){
			tele = "N/A";
		}
		if (email.isEmpty()){
			email = "N/A";
		}
		if (dorm.isEmpty()){
			dorm = "N/A";
		}
		if (fa.isEmpty()){
			fa = "000000000";
			additional(birthday, tele, qq, email, dorm,  fa, 
				studentID, gender, password, name, act);
		}
		else if (SRSFunctions.checkForPerson(fa)){
			if(!SRSFunctions.checkForActive(fa)){
				fa = "000000000";
			}
			additional(birthday, tele, qq, email, dorm,  fa, 
				studentID, gender, password, name, act);
		}
	}
	
	public void addToAdvisor(String teacherID) throws FileNotFoundException {
		String advisorFile = "Advisors.dat";
		Scanner sc = new Scanner(new File(advisorFile));
		String[] info = new String[100];
		String[] split = new String[100];
		String line;
		boolean add = false;
		int i = 0, j = 0;
		while(sc.hasNextLine()){
			line = sc.nextLine();
			split = line.split("\t");
			if (split[0].equals(teacherID)){
				int k = 1;
				while(k < split.length){
					if(idField.getText().equals(split[k])){
						add = true;
						info[i] = line;
						break;
					}
					k++;
				}
				if(!add){
					info[i] = line + idField.getText() + "\t" ;
					add = true;	
				}
			}
			else{
				info[i] = line;
			}
			i++;
		}
		if (!add){
			info[i] = teacherID + "\t" + idField.getText() + "\t";
			i++;
		}
		PrintWriter pw = new PrintWriter(new File(advisorFile));
		while(j < i){
			pw.println(info[j]);
			j++;
		}
		sc.close();
		pw.close();
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == btnReturnToPrevious){
			this.dispose();
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		}
		else if(source == rdbtnMale){
			rdbtnFemale.setSelected(false);
		}
		else if(source == rdbtnFemale){
			rdbtnMale.setSelected(false);
		}
		else if(source == btnResetForm){	
			SRSFunctions.sort(studentsFile);
			SRSFunctions.sort("Advisors.dat");
			nameField.setText("");
	    	passwordField.setText("");
	    	idField.setText("");
	    	yearField.setText("");
	    	ddField.setText("");
	    	mmField.setText("");
	    	qqField.setText("");
	    	eField.setText("");
	    	dormField.setText("");
	    	telField.setText("");
	    	faField.setText("");
	    	rdbtnFemale.setSelected(false);
	    	rdbtnMale.setSelected(false);
		}
		else if(source == btnInformationAboutHow){
			SRSSaveInfo frame = new SRSSaveInfo();
			frame.setVisible(true);
		}
		
		else if(source == btnCheckForThis){
			checkForConflict();		
		}
		else if(source == btnCheckForTeachers){
			String id = faField.getText(); 
			if(SRSFunctions.checkForPerson(id)){
				SRSInfoPassed frame = new SRSInfoPassed();
				frame.setVisible(true);
			}
			if(SRSFunctions.checkForActive(id)){
				SRSPersonActive frame = new SRSPersonActive();
				frame.setVisible(true);
			}
		}
		else if(source == btnSaveForThis){
			try {
				save();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
}
