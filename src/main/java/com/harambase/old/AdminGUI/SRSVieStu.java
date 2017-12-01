package AdminGUI;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

import AdminGUIBars.SRSInfoUsedTwice;
import AdminGUIBars.SRSRegisterError;
import AdminGUIBars.SRSSaveSucceed;
import NewCore.Course;
import NewCore.Professor;
import NewCore.Student;
import NewCore.Transcript;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSAdInterface;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextPane;

public class SRSVieStu extends JFrame implements ActionListener, MouseListener {

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
	private JTextField faField;
	private JTextField crnField;
	private JTextField textField_11;
	private JTextField creField;
	private JButton btnResetFormAbove;
	private JList<String> stuList2;
	private JButton btnViewThisStudents;
	private JButton btnCompleteThisCourse;
	private JButton btnChangeGrade;
	private JList<String> couList2;
	private JButton btnVieCou;
	private JButton btnChangeTheCredits;
	private JButton btnDeactiveForDropping_1;
	private JButton btnActiveForAdding_1;
	private JButton btnDeleteStu;
	private JButton btnActiveForDropping;
	private JButton btnDeactiveForDropping;
	private JButton btnActiveForAdding;
	private JButton btnDectiveForAdding;
	private JButton btnReactivateThisStudent;
	private JButton btnDeactivate;
	private JButton btnSaveEdition;
	private JButton returnButton;
	private JButton refButton;
	private JScrollPane couList;
	private JScrollPane stuList;
	private JRadioButton fButton;
	private JRadioButton mButton;
	private static final String studentsFile = "Students.dat";
	private static final String advisorsFile = "Advisors.dat";
	private JTextField passField;
	private Student stu;
	private Course cou;
	private JLabel lblStudentIdStudent;
	private JButton btnActiveForDropping_1;
	private JButton btnDeactiveForAdding;
	private JTextField faNameField;
	private JLabel lblTheIdOf;
	private JTextField reStuIdField;
	private JLabel lblForOnemultipleStudents;
	private JLabel lblStudentInformationForm;
	private JButton btnDropStudentFrom2;
	private JButton btnAddThisStudent;
	private JLabel lblTheCrnOf;
	private JTextField crnField_2;
	private JButton btnIncompleteThisCourse;
	private JTextPane txtpnNotificationsDeactivateA;
	private JButton btnForThistheseStudents;
	private JButton btnCreateAllStudents;
	private JTextField txtAllTheSemester;
	private JList<String> semeList;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSVieStu frame = new SRSVieStu();
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
	public SRSVieStu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1565, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		refButton = new JButton("Refresh ");
		refButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		returnButton = new JButton("Return to Pervious Menu");
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
		nameField.setBackground(SystemColor.menu);
		nameField.setText("");
		nameField.setColumns(10);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setText("");
		idField.setColumns(10);
		
		yyField = new JTextField();
		yyField.setBackground(SystemColor.menu);
		yyField.setText("");
		yyField.setColumns(10);
		
		JLabel label_4 = new JLabel("Student's Birthday (YYYY/MM/DD):*");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_5 = new JLabel("Student's Contact Infomation(Optional)");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_6 = new JLabel("QQ:");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_7 = new JLabel("Telephone:");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_8 = new JLabel("Email:");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		telField = new JTextField();
		telField.setBackground(SystemColor.menu);
		telField.setText("");
		telField.setColumns(10);
		
		qqField = new JTextField();
		qqField.setBackground(SystemColor.menu);
		qqField.setText("");
		qqField.setColumns(10);
		
		eField = new JTextField();
		eField.setBackground(SystemColor.menu);
		eField.setText("");
		eField.setColumns(10);
		
		dormField = new JTextField();
		dormField.setBackground(SystemColor.menu);
		dormField.setText("");
		dormField.setColumns(10);
		
		JLabel label_9 = new JLabel("Student's Assigned Dormitory:");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_10 = new JLabel("Student's Assigned Faculty Advisor:");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_11 = new JLabel("Gender:*");
		label_11.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		mButton = new JRadioButton("Male");
		mButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		fButton = new JRadioButton("Female");
		fButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		faField = new JTextField();
		faField.setBackground(SystemColor.menu);
		faField.setText("");
		faField.setColumns(10);
		
		JLabel label_12 = new JLabel("This Teacher's ID: (leave blank if undetermined)");
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		passField = new JTextField();
		passField.setBackground(SystemColor.menu);
		passField.setText("");
		passField.setColumns(10);
		
		faNameField = new JTextField();
		faNameField.setEditable(false);
		faNameField.setText("");
		faNameField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStudentsenglish, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentsIdNumber)
								.addComponent(lblStudentspassword, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
								.addComponent(idField, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
								.addComponent(passField, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
							.addGap(15))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
							.addGap(17))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(mButton, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(fButton, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(17))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(dormField, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(yyField, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
								.addComponent(label_5, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_7)
										.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(telField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
										.addComponent(qqField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
										.addComponent(eField, Alignment.TRAILING))))
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(faField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(faNameField, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
							.addGap(17))))
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
					.addGap(22)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(yyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8)
						.addComponent(eField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(dormField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(faField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(faNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(mButton)
						.addComponent(fButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel label_13 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		stuList2 = new JList<String>();
		stuList2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		stuList = new JScrollPane(stuList2);
		
		btnViewThisStudents = new JButton("View this Student's infomation");
		btnViewThisStudents.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnSaveEdition = new JButton("Save this Edition");
		btnSaveEdition.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_14 = new JLabel("Viewing Student Mode");
		label_14.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnDeactivate = new JButton("Deactivate for registering");
		btnDeactivate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnReactivateThisStudent = new JButton("Reactivate");
		btnReactivateThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnReactivateThisStudent.addActionListener(this);
		
		btnActiveForAdding = new JButton("Active for adding");
		btnActiveForAdding.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDectiveForAdding = new JButton("Deactive for adding");
		btnDectiveForAdding.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnActiveForDropping = new JButton("Active for dropping");
		btnActiveForDropping.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeactiveForDropping = new JButton("Deactive for dropping");
		btnDeactiveForDropping.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblIndividualFunctionButtons = new JLabel("PartI Individual or Selected students Function Buttons");
		lblIndividualFunctionButtons.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		JLabel lblPartiiAllStudents = new JLabel("");
		lblPartiiAllStudents.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		btnActiveForAdding_1 = new JButton("Active for Adding(All Students)");
		btnActiveForAdding_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeactiveForDropping_1 = new JButton("Deactive for dropping(All Students)");
		btnDeactiveForDropping_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblBstudentInfomation = new JLabel("Student Registration Buttons");
		lblBstudentInfomation.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnVieCou = new JButton("View this Student's Registered Courses");
		btnVieCou.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		couList2 = new JList<String>();
		couList = new JScrollPane(couList2);
		
		btnDropStudentFrom2 = new JButton("Drop Student from this course");
		btnDropStudentFrom2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnAddThisStudent = new JButton("Add this student to this course (CRN only)");
		btnAddThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		crnField = new JTextField();
		crnField.setColumns(10);
		
		JLabel lblAddASpecific = new JLabel("1.Add a course to this student:");
		lblAddASpecific.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblchangeStudentsGrade = new JLabel("2.Change Student's Grade");
		lblchangeStudentsGrade.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label_16 = new JLabel("New Grade:(A/B/C/D/F)");
		label_16.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		textField_11 = new JTextField();
		textField_11.setText("");
		textField_11.setColumns(10);
		
		btnChangeGrade = new JButton("Change the grade");
		btnChangeGrade.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblchangeStudentsCredits = new JLabel("3.Change Student's Credits");
		lblchangeStudentsCredits.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblEarnedCredits = new JLabel("Newly Earned Credits:");
		lblEarnedCredits.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		creField = new JTextField();
		creField.setText("");
		creField.setColumns(10);
		
		btnChangeTheCredits = new JButton("Change the credits");
		btnChangeTheCredits.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblSetCompletion = new JLabel("4. Set Accomplishment for a Course");
		lblSetCompletion.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btnCompleteThisCourse = new JButton("Complete this Course");
		btnCompleteThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnForThistheseStudents = new JButton("Create this students General reports");
		btnForThistheseStudents.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnForThistheseStudents.addActionListener(this);
		
		btnCreateAllStudents = new JButton("Create this student this Semester class reports");
		btnCreateAllStudents.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnCreateAllStudents.addActionListener(this);
		
		btnResetFormAbove = new JButton("Reset Form Above");
		btnResetFormAbove.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		lblStudentIdStudent = new JLabel("Format: Student ID+ Student Name + Student Earned Credits + Student Active (A/D) "
				+ "+ Student Active for adding course (AA/DA) + Student Active for dropping course (AD/DD)");
		lblStudentIdStudent.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentIdStudent.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		btnActiveForDropping_1 = new JButton("Active for dropping(All Students)");
		btnActiveForDropping_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeactiveForAdding = new JButton("Deactive for Adding(All Students)");
		btnDeactiveForAdding.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		lblTheIdOf = new JLabel("The id of student  to reactivate");
		lblTheIdOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheIdOf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		reStuIdField = new JTextField();
		reStuIdField.setColumns(10);
		
		lblForOnemultipleStudents = new JLabel("For one student:");
		lblForOnemultipleStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblForOnemultipleStudents.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		lblStudentInformationForm = new JLabel("Student Information Form");
		lblStudentInformationForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblTheCrnOf = new JLabel("The CRN of a course student registered:");
		lblTheCrnOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		crnField_2 = new JTextField();
		crnField_2.setEditable(false);
		crnField_2.setColumns(10);
		
		btnIncompleteThisCourse = new JButton("Incomplete this Course");
		btnIncompleteThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		txtpnNotificationsDeactivateA = new JTextPane();
		txtpnNotificationsDeactivateA.setText("Notifications: Deactivate a Student will cause:\r\n1.  This student will be removed from Students.dat file, which means you cannot view his/her info here. You may reactivate this student, or you can go to \"View Person Mode\".\r\n2.  This studdent will be removed from all courses he/she is/was studying, which means  when you in \u201CView Course Mode\u201D, you will not find infomation about this student. But after reactivation, you can information in \"View Course Mode\"\r\n3.  This student will be removed from advising list as well");
		txtpnNotificationsDeactivateA.setForeground(Color.RED);
		txtpnNotificationsDeactivateA.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtpnNotificationsDeactivateA.setEditable(false);
		txtpnNotificationsDeactivateA.setBackground(SystemColor.info);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnViewThisStudents, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
										.addComponent(btnVieCou, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
										.addComponent(stuList, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnDropStudentFrom2, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(couList, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(6)
											.addComponent(lblAddASpecific, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(crnField, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
										.addComponent(btnAddThisStudent, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
													.addComponent(lblchangeStudentsGrade, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblchangeStudentsCredits, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEarnedCredits, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(creField, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
												.addComponent(btnChangeTheCredits, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
												.addComponent(textField_11, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
												.addComponent(btnChangeGrade, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTheCrnOf, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSetCompletion, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(crnField_2, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnCompleteThisCourse, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnIncompleteThisCourse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblBstudentInfomation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
										.addComponent(lblIndividualFunctionButtons, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPartiiAllStudents, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
												.addComponent(btnCreateAllStudents, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
												.addComponent(btnForThistheseStudents, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnActiveForAdding_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnActiveForDropping_1, GroupLayout.PREFERRED_SIZE, 195, Short.MAX_VALUE))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(btnDeactiveForAdding, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
														.addComponent(btnDeactiveForDropping_1, GroupLayout.PREFERRED_SIZE, 222, Short.MAX_VALUE)))
												.addComponent(txtpnNotificationsDeactivateA, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblTheIdOf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
																	.addComponent(btnActiveForDropping, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																	.addComponent(btnActiveForAdding, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
																.addComponent(lblForOnemultipleStudents, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(reStuIdField, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(btnReactivateThisStudent, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
														.addComponent(btnDeactivate, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
														.addComponent(btnDeactiveForDropping, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
														.addComponent(btnDectiveForAdding, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))))
											.addGap(6))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(11)
									.addComponent(refButton, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblStudentIdStudent, GroupLayout.PREFERRED_SIZE, 1043, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblStudentInformationForm, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
									.addGap(193))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSaveEdition, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnResetFormAbove, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
									.addGap(93))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(returnButton, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(refButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(returnButton)))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentInformationForm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblStudentIdStudent, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnViewThisStudents)
										.addComponent(btnDropStudentFrom2))
									.addGap(7)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(stuList, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(couList, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(3)
													.addComponent(crnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(lblAddASpecific, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
											.addGap(101)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblchangeStudentsCredits, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnChangeTheCredits))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblEarnedCredits, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
												.addComponent(creField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGap(10)
											.addComponent(lblSetCompletion, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
											.addGap(26)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnCreateAllStudents)
												.addComponent(btnCompleteThisCourse)
												.addComponent(btnIncompleteThisCourse))
											.addGap(1)
											.addComponent(lblPartiiAllStudents, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
									.addGap(43))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(284)
											.addComponent(btnVieCou)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblBstudentInfomation, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(lblIndividualFunctionButtons, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblTheIdOf)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
															.addComponent(reStuIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addComponent(btnReactivateThisStudent)))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblForOnemultipleStudents)
														.addComponent(btnDeactivate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnActiveForAdding)
														.addComponent(btnDectiveForAdding))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnActiveForDropping)
														.addComponent(btnDeactiveForDropping))
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addGap(44)
															.addComponent(btnAddThisStudent)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblchangeStudentsGrade, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnChangeGrade))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
																.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(txtpnNotificationsDeactivateA, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnActiveForDropping_1)
														.addComponent(btnDeactiveForDropping_1))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnActiveForAdding_1)
														.addComponent(btnDeactiveForAdding)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(8)
													.addComponent(panel, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
													.addComponent(btnForThistheseStudents)
													.addComponent(lblTheCrnOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
													.addComponent(crnField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(btnSaveEdition))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnResetFormAbove)))
									.addGap(64)))))
					.addContainerGap())
		);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("All The Courses student has Registered.");
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBackground(SystemColor.menu);
		couList.setColumnHeaderView(textField);
		
		btnDeleteStu = new JButton("Delete this student");
		stuList.setColumnHeaderView(btnDeleteStu);
		btnDeleteStu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDeleteStu.addActionListener(this);
		
		semeList = new JList<String>();
		scrollPane.setViewportView(semeList);
		
		txtAllTheSemester = new JTextField();
		txtAllTheSemester.setEditable(false);
		txtAllTheSemester.setBackground(SystemColor.menu);
		txtAllTheSemester.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtAllTheSemester.setText("All Currently Available Semester");
		scrollPane.setColumnHeaderView(txtAllTheSemester);
		txtAllTheSemester.setColumns(10);
		contentPane.setLayout(gl_contentPane);
		btnResetFormAbove.addActionListener(this);
		btnViewThisStudents.addActionListener(this);
		btnCompleteThisCourse.addActionListener(this);
		btnChangeGrade.addActionListener(this);
		btnVieCou.addActionListener(this);
		btnChangeTheCredits.addActionListener(this);
		btnDeactiveForDropping_1.addActionListener(this);
		btnActiveForAdding_1.addActionListener(this);
		btnActiveForDropping.addActionListener(this);
		btnDeactiveForDropping.addActionListener(this);
		btnActiveForAdding.addActionListener(this);
		btnDectiveForAdding.addActionListener(this);
		btnDeactivate.addActionListener(this);
		btnSaveEdition.addActionListener(this);
		returnButton.addActionListener(this);
		refButton.addActionListener(this);
		fButton.addActionListener(this);
		mButton.addActionListener(this);
		btnDeactiveForAdding.addActionListener(this);
		btnActiveForDropping_1.addActionListener(this);
		btnDropStudentFrom2.addActionListener(this);
		btnAddThisStudent.addActionListener(this);
		btnIncompleteThisCourse.addActionListener(this);
		couList2.addMouseListener(this);
		
		showAllStudents();
		showSemester();
		stu = new Student();
		cou = new Course();
	}
	
	public void showAllStudents(){
		try {
			Scanner sc = new Scanner(new File(studentsFile));
			String[] info = new String[1000];
			String[] split = new String[10];
			int i = 1;
			sc.nextLine();
			info[0] = "Notice:Data strcuture is showing above the view Button.";
			while (sc.hasNextLine()){
				split = sc.nextLine().split("\t");
				stu = SRSFunctions.makeAStudent(split[0]);
				info[i] = stu.getId() + "     " + stu.getName() + "     " + 
						  stu.getCredits() + "     " + stu.getActive() + "      " +
						  stu.getActA() + "     " + stu.getActD();
				i++;
			}
			info[i + 1] = "Total Active Student Amount: " + (i - 1) + ".";
			stuList2.setListData(info);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void showSemester(){
		Scanner sc;
		try {
			sc = new Scanner(new File("AllCourseCatalog.dat"));
			String[] couInfo = new String[20];
			String[] semInfo = new String[50];
			String crn,semeBelong = "", semeOld = "";
			int i,x = 0;
			while (sc.hasNext()){
				semeBelong = "";
				couInfo = sc.nextLine().split("\t");
				crn = couInfo[0];
				for (i = 0; i < 6; i++){
					semeBelong = semeBelong + crn.charAt(i);
				}
				if (!semeBelong.equals(semeOld)){
					String seme = "";
					if(semeBelong.charAt(5) == '1'){
						seme = "Spring";
					}
					else if(semeBelong.charAt(5) == '2'){
						seme = "Summer";
					}
					else if(semeBelong.charAt(5) == '3'){
						seme = "Fall";
					}
					semInfo[x] = semeOld;
					semInfo[x] = semeBelong + "      This refers to " 
					+ semeBelong.substring(0,4) + " " + seme + " Semester.";
					semeOld = semeBelong;
					x++;
				}
				else{
					semeOld = semeBelong;
				}
			}
			semeList.setListData(semInfo);
	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	public void viewCou(){
		String studentID = stuList2.getSelectedValue().substring(0,9);
		String studentFile = studentID + ".dat";
		Course cou = new Course();
		try {
			Scanner scS = new Scanner(new File(studentFile));
			String[] courses = new String[100];		
			String[] split = new String[10];
			int i = 1;
			scS.nextLine();
			courses[0] = "Format: Course's CRN + course's Name + Grade + Completion";
			while(scS.hasNextLine()){
				split = scS.nextLine().split("\t");
				cou = SRSFunctions.makeACourse(split[0]);
				if(cou.getCrn().substring(0,6).equals(semeList.getSelectedValue().substring(0, 6))){
					Transcript tran = SRSFunctions.makeATranscript(stu.getId(), cou.getCrn());
					courses[i] = cou.getCrn() + "     " + cou.getName() + "       " + tran.getGrade() + "     " 
					+ tran.getComplete();
					if(tran.getGrade().equals("W")){
						courses[i] = courses[i] + " INFO: This Student has dropped this course!";
					}
					i++;
				}
			}
			courses[i] = "Total Registered courses number " + (i - 1) + ".";
			couList2.setListData(courses);
			scS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void viewStu(){
		String studentID =  stuList2.getSelectedValue().substring(0, 9);
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
			faField.setText(fa);
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
			scS.close();
			stu = SRSFunctions.makeAStudent(studentID);
			String[] info = new String[10];
			couList2.setListData(info);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}	
	public void changeTheGrade(){
		 String studentID =  stuList2.getSelectedValue().substring(0, 9);
		 int index = stuList2.getSelectedIndex();
		 String crn = couList2.getSelectedValue().substring(0, 9);
		 cou = SRSFunctions.makeACourse(crn);
		 stu = SRSFunctions.makeAStudent(studentID);
		 Transcript tran = SRSFunctions.makeATranscript(studentID, crn);
		 String inSFile = studentID + ".dat";
		 String inCFile = "C" + crn + ".dat";
		 String newGrade = textField_11.getText();
		 String line;
		 String[] split = new String[50];
		 try {
			int i = 0, j = 0;
			int x = 0, y = 0;
			
			if (!crn.isEmpty()){
				Scanner scS = new Scanner(new File(inSFile));
				Scanner scC = new Scanner(new File(inCFile));
				String[] infoS = new String[100];
				String[] infoC = new String[100];
				String infoSS = "", infoCS;
				if (tran.equals("N/A") && newGrade.equals("F")){
					infoSS = crn + "\t" + newGrade +"\t" + "D" + "\t";
				}
				else if (tran.equals("N/A") || tran.equals("F")){
					infoSS = crn + "\t" + newGrade +"\t" + "C" + "\t";
					Double credit = Double.parseDouble(cou.getCredits());
					Double earned = Double.parseDouble(stu.getCredits());
					earned = earned + credit;
					stu.setCredits(earned.toString());
					SRSFunctions.saveAPersonFirstLineInfo(stu);
				}
				else if (!tran.equals("N/A") && newGrade.equals("F")) {
					infoSS = crn + "\t" + newGrade +"\t" + "D" + "\t";
					Double credit = Double.parseDouble(cou.getCredits());
					Double earned = Double.parseDouble(stu.getCredits());
					earned = earned - credit;
					stu.setCredits(earned.toString());
					SRSFunctions.saveAPersonFirstLineInfo(stu);
				}
				while(scS.hasNextLine()){
					line = scS.nextLine();
					split = line.split("\t");
					if(split[0].equals(crn)){
						infoS[i] = infoSS;
						i++;
					}
					else{
						infoS[i] = line;
						i++;
					}
				}
				while(scC.hasNextLine()){
					line = scC.nextLine();
					split = line.split("\t");
					if(split[0].equals(studentID)){
						if (newGrade.equals("F")){
							infoCS = studentID + "\t" + newGrade +"\t" + "D" + "\t";
						}
						else{
							infoCS = studentID + "\t" + newGrade +"\t" + "C" + "\t";
						}
						infoC[j] = infoCS;
						j++;
					}
					else{
						infoC[j] = line;
						j++;
					}
				}
				scS.close();
				scC.close();
							
				PrintWriter pwS = new PrintWriter(new File(inSFile));
				PrintWriter pwC = new PrintWriter(new File(inCFile));
				
				while (x < i){
					pwS.println(infoS[x]);
					x++;
				}
				while (y < j){
					pwC.println(infoC[y]);
					y++;
				}
				pwS.close();
				pwC.close();
			}
			else{
				SRSRegisterError frame = new SRSRegisterError();
				frame.setVisible(true);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 SRSSaveSucceed frame =  new SRSSaveSucceed();
		 frame.setVisible(true);
		 viewCou();
		 showAllStudents();
		 stuList2.setSelectedIndex(index);
		 textField_11.setText("");
	}
	public void changeTheCredits(){
		 String studentID =  stuList2.getSelectedValue().substring(0, 9);
		 String newCredits = creField.getText();
		 int index = stuList2.getSelectedIndex();
		 stu = SRSFunctions.makeAStudent(studentID);
		 stu.setCredits(newCredits);
		 
		 SRSFunctions.saveAPersonFirstLineInfo(stu);
		
		 SRSSaveSucceed frame =  new SRSSaveSucceed();
		 frame.setVisible(true);
		 viewCou();
		 showAllStudents();
		 stuList2.setSelectedIndex(index);
		 creField.setText("");
	}
	public void activateAllStudentsForDropping(){
		 try {	
				Scanner scAS = new Scanner(new File(studentsFile));
				String infoIS = "";

				String[] infoASL = new String[1000];
				int ii = 0 , ij = 0, ai = 1, aj = 0;
				infoASL[0] = scAS.nextLine();
				while(scAS.hasNextLine()){
					infoASL[ai] = scAS.nextLine();
					String studentID = infoASL[ai].split("\t")[0];
					Scanner scIS = new Scanner(new File(studentID + ".dat"));
					String active = "AD";
					stu = SRSFunctions.makeAStudent(studentID);
					stu.setActD(active);
					
					ii = 0; ij = 0;
					String[] infoISL = new String[100];
					infoIS = stu.formingForISF();
					infoASL[ai] = stu.formingForASF();
					
					while(scIS.hasNextLine()){
						infoISL[ii] = scIS.nextLine();
						ii++;
					}
					PrintWriter pwIS = new PrintWriter(new File(studentID + ".dat"));
					infoISL[0] = infoIS;
					while(ij < ii){
						pwIS.println(infoISL[ij]);
						ij++;
					}
					ai++;
					scIS.close();
					pwIS.close();
				}
				
				scAS.close();
				PrintWriter pwAS = new PrintWriter(new File(studentsFile));
				
				while(aj < ai){
					pwAS.println(infoASL[aj]);
					aj++;
				}
				pwAS.close();
			} catch (FileNotFoundException e) {
	
				e.printStackTrace();
			}
			SRSSaveSucceed frame =  new SRSSaveSucceed();
			frame.setVisible(true);
			showAllStudents();
		}
	public void deactivateAllStudentsForDropping(){
		 try {	
				Scanner scAS = new Scanner(new File(studentsFile));
				String infoIS = "";

				String[] infoASL = new String[1000];
				int ii = 0 , ij = 0, ai = 1, aj = 0;
				infoASL[0] = scAS.nextLine();
				while(scAS.hasNextLine()){
					infoASL[ai] = scAS.nextLine();
					String studentID = infoASL[ai].split("\t")[0];
					Scanner scIS = new Scanner(new File(studentID + ".dat"));
					String active = "DD";
					stu = SRSFunctions.makeAStudent(studentID);
					stu.setActD(active);
					
					ii = 0; ij = 0;
					String[] infoISL = new String[100];
					infoIS = stu.formingForISF();
					infoASL[ai] = stu.formingForASF();
					
					while(scIS.hasNextLine()){
						infoISL[ii] = scIS.nextLine();
						ii++;
					}
					PrintWriter pwIS = new PrintWriter(new File(studentID + ".dat"));
					infoISL[0] = infoIS;
					while(ij < ii){
						pwIS.println(infoISL[ij]);
						ij++;
					}
					ai++;
					scIS.close();
					pwIS.close();
				}
				
				scAS.close();
				PrintWriter pwAS = new PrintWriter(new File(studentsFile));
				
				while(aj < ai){
					pwAS.println(infoASL[aj]);
					aj++;
				}
				pwAS.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			SRSSaveSucceed frame =  new SRSSaveSucceed();
			frame.setVisible(true);
			showAllStudents();
		}
	public void activateAllStudentsForAdding(){
		 try {	
				Scanner scAS = new Scanner(new File(studentsFile));
				String infoIS = "";

				String[] infoASL = new String[1000];
				int ii = 0 , ij = 0, ai = 1, aj = 0;
				infoASL[0] = scAS.nextLine();
				while(scAS.hasNextLine()){
					infoASL[ai] = scAS.nextLine();
					String studentID = infoASL[ai].split("\t")[0];
					Scanner scIS = new Scanner(new File(studentID + ".dat"));
					String active = "AA";
					stu = SRSFunctions.makeAStudent(studentID);
					stu.setActA(active);
					
					ii = 0; ij = 0;
					String[] infoISL = new String[100];
					infoIS = stu.formingForISF();
					infoASL[ai] = stu.formingForASF();
					
					while(scIS.hasNextLine()){
						infoISL[ii] = scIS.nextLine();
						ii++;
					}
					PrintWriter pwIS = new PrintWriter(new File(studentID + ".dat"));
					infoISL[0] = infoIS;
					while(ij < ii){
						pwIS.println(infoISL[ij]);
						ij++;
					}
					ai++;
					scIS.close();
					pwIS.close();
				}
				
				scAS.close();
				PrintWriter pwAS = new PrintWriter(new File(studentsFile));
				
				while(aj < ai){
					pwAS.println(infoASL[aj]);
					aj++;
				}
				pwAS.close();
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			}
			SRSSaveSucceed frame =  new SRSSaveSucceed();
			frame.setVisible(true);
			showAllStudents();
		}
	public void deactivateAllStudentsForAdding(){
		 try {	
				Scanner scAS = new Scanner(new File(studentsFile));
				String infoIS = "";

				String[] infoASL = new String[1000];
				int ii = 0 , ij = 0, ai = 1, aj = 0;
				infoASL[0] = scAS.nextLine();
				while(scAS.hasNextLine()){
					infoASL[ai] = scAS.nextLine();
					String studentID = infoASL[ai].split("\t")[0];
					Scanner scIS = new Scanner(new File(studentID + ".dat"));
					String active = "DA";
					stu = SRSFunctions.makeAStudent(studentID);
					stu.setActA(active);
					
					ii = 0; ij = 0;
					String[] infoISL = new String[100];
					infoIS = stu.formingForISF();
					infoASL[ai] = stu.formingForASF();
					
					while(scIS.hasNextLine()){
						infoISL[ii] = scIS.nextLine();
						ii++;
					}
					PrintWriter pwIS = new PrintWriter(new File(studentID + ".dat"));
					infoISL[0] = infoIS;
					while(ij < ii){
						pwIS.println(infoISL[ij]);
						ij++;
					}
					ai++;
					scIS.close();
					pwIS.close();
				}
				
				scAS.close();
				PrintWriter pwAS = new PrintWriter(new File(studentsFile));
				
				while(aj < ai){
					pwAS.println(infoASL[aj]);
					aj++;
				}
				pwAS.close();
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			}
			SRSSaveSucceed frame =  new SRSSaveSucceed();
			frame.setVisible(true);
			showAllStudents();
	}
	public void deactivateSeleStudentForDropping(){
		List<Student> seleStu = new LinkedList<Student>();
		List<String> seleStuInfo = new LinkedList<String>();
		List<String> seleStuID = new LinkedList<String>();
		seleStuInfo = stuList2.getSelectedValuesList();
		int index = 0;
		while(seleStuInfo.listIterator(index).hasNext()){
			seleStuID.add(seleStuInfo.get(index).toString().substring(0, 9));
			seleStu.add(SRSFunctions.makeAStudent(seleStuID.get(index)));
			index++;
		}
		index = 0;
		while(seleStu.listIterator(index).hasNext()){
			stu = seleStu.get(index);
			stu.setActD("DD");
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			index++;
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();
	}
	public void activateSeleStudentForDropping(){
		List<Student> seleStu = new LinkedList<Student>();
		List<String> seleStuInfo = new LinkedList<String>();
		List<String> seleStuID = new LinkedList<String>();
		seleStuInfo = stuList2.getSelectedValuesList();
		int index = 0;
		while(seleStuInfo.listIterator(index).hasNext()){
			seleStuID.add(seleStuInfo.get(index).toString().substring(0, 9));
			seleStu.add(SRSFunctions.makeAStudent(seleStuID.get(index)));
			index++;
		}
		index = 0;
		while(seleStu.listIterator(index).hasNext()){
			stu = seleStu.get(index);
			stu.setActD("AD");
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			index++;
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();
	}
	public void deactivateSeleStudentForAdding(){
		List<Student> seleStu = new LinkedList<Student>();
		List<String> seleStuInfo = new LinkedList<String>();
		List<String> seleStuID = new LinkedList<String>();
		seleStuInfo = stuList2.getSelectedValuesList();
		int index = 0;
		while(seleStuInfo.listIterator(index).hasNext()){
			seleStuID.add(seleStuInfo.get(index).toString().substring(0, 9));
			seleStu.add(SRSFunctions.makeAStudent(seleStuID.get(index)));
			index++;
		}
		index = 0;
		while(seleStu.listIterator(index).hasNext()){
			stu = seleStu.get(index);
			stu.setActA("DA");
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			index++;
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();
	}
	public void activateSeleStudentForAdding(){
		List<Student> seleStu = new LinkedList<Student>();
		List<String> seleStuInfo = new LinkedList<String>();
		List<String> seleStuID = new LinkedList<String>();
		seleStuInfo = stuList2.getSelectedValuesList();
		int index = 0;
		while(seleStuInfo.listIterator(index).hasNext()){
			seleStuID.add(seleStuInfo.get(index).toString().substring(0, 9));
			seleStu.add(SRSFunctions.makeAStudent(seleStuID.get(index)));
			index++;
		}
		index = 0;
		while(seleStu.listIterator(index).hasNext()){
			stu = seleStu.get(index);
			stu.setActA("AA");
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			index++;
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();
	}
	public void completeThisCourse(){
		String studentID =  stuList2.getSelectedValue().substring(0, 9);
		String crn = couList2.getSelectedValue().substring(0, 9);
		int index = stuList2.getSelectedIndex();
		cou = SRSFunctions.makeACourse(crn);
		stu = SRSFunctions.makeAStudent(studentID);
		
		Transcript tran = SRSFunctions.makeATranscript(studentID, crn);
		String infoForS = crn  + "\t" + tran.getGrade() + "\t"  + "C" + "\t";
		String infoForC = studentID + "\t" + tran.getGrade() + "\t" + "C" + "\t";
		
		Double credit = Double.parseDouble(cou.getCredits());
		Double earned = Double.parseDouble(stu.getCredits());
		earned = earned + credit;
		stu.setCredits(earned.toString());
		SRSFunctions.saveAPersonFirstLineInfo(stu);
		
		try {
			Scanner scSF = new Scanner(new File(studentID + ".dat"));
			Scanner scCF = new Scanner(new File("C" + crn + ".dat"));
			String[] infoS = new String[100];
			String[] infoC = new String[100];
			int is = 0,ic = 0,js = 0,jc = 0;
			
			while(scSF.hasNextLine()){
				infoS[is] = scSF.nextLine();
				if(infoS[is] .split("\t")[0].equals(crn)){
					infoS[is] = infoForS;
				}
				is++;
			}
			while(scCF.hasNextLine()){
				infoC[ic] = scCF.nextLine();
				if(infoC[ic].split("\t")[0].equals(studentID)){
					infoC[ic] = infoForC;
				}
				ic++;
			}
			scSF.close();
			scCF.close();
			
			PrintWriter pwS = new PrintWriter(new File(studentID + ".dat"));
			PrintWriter pwC = new PrintWriter(new File("C" + crn + ".dat"));
			
			while(js < is){
				pwS.println(infoS[js]);
				js++;
			}
			while(jc < ic){
				pwC.println(infoC[jc]);
				jc++;
			}
			
			pwS.close();
			pwC.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		viewCou();
		showAllStudents();
		stuList2.setSelectedIndex(index);
	}
	public void incompleteThisCourse(){
		String studentID =  stuList2.getSelectedValue().substring(0, 9);
		String crn = couList2.getSelectedValue().substring(0, 9);
		int index = stuList2.getSelectedIndex();
		cou = SRSFunctions.makeACourse(crn);
		stu = SRSFunctions.makeAStudent(studentID);
		
		Transcript tran = SRSFunctions.makeATranscript(studentID, crn);
		String infoForS = crn + "\t" + tran.getGrade() + "\t"  + "D" + "\t";
		String infoForC = studentID + "\t" + tran.getGrade() + "\t" + "D" + "\t";
		
		Double credit = Double.parseDouble(cou.getCredits());
		Double earned = Double.parseDouble(stu.getCredits());
		earned = earned - credit;
		stu.setCredits(earned.toString());
		SRSFunctions.saveAPersonFirstLineInfo(stu);
		
		try {
			Scanner scSF = new Scanner(new File(studentID + ".dat"));
			Scanner scCF = new Scanner(new File("C" + crn + ".dat"));
			String[] infoS = new String[100];
			String[] infoC = new String[100];
			int is = 0,ic = 0,js = 0,jc = 0;
			
			while(scSF.hasNextLine()){
				infoS[is] = scSF.nextLine();
				if(infoS[is] .split("\t")[0].equals(crn)){
					infoS[is] = infoForS;
				}
				is++;
			}
			while(scCF.hasNextLine()){
				infoC[ic] = scCF.nextLine();
				if(infoC[ic].split("\t")[0].equals(studentID)){
					infoC[ic] = infoForC;
				}
				ic++;
			}
			scSF.close();
			scCF.close();
			
			PrintWriter pwS = new PrintWriter(new File(studentID + ".dat"));
			PrintWriter pwC = new PrintWriter(new File("C" + crn + ".dat"));
			
			while(js < is){
				pwS.println(infoS[js]);
				js++;
			}
			while(jc < ic){
				pwC.println(infoC[jc]);
				jc++;
			}
			
			pwS.close();
			pwC.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		viewCou();
		showAllStudents();
		stuList2.setSelectedIndex(index);
	}
	public void saveInfo(){
		String studentID =  stuList2.getSelectedValue().substring(0, 9);
		//Student file: ID + Password +  Name + Active? + Credits + type + birth + tele + qq + email + dorm
		// 		 + Faculty Advisor + ActiveforDrop(AD/DD) + ActiveforAdd(AA/DA) + Gender	
		int index = stuList2.getSelectedIndex();
		String password = passField.getText();
		String name = nameField.getText();
		String birth = yyField.getText();
		String tele = telField.getText();
		String qq = qqField.getText();
		String email = eField.getText();
		String dorm = dormField.getText();
		String fa = faField.getText();
		String gender;
		if (mButton.isSelected()){
			gender = "Male";
		}
		else{
			gender = "Female";
		}
		stu = SRSFunctions.makeAStudent(studentID);
		stu.setPassword(password);
		stu.setName(name);
		stu.setBirth(birth);
		stu.setTele(tele);
		stu.setQq(qq);
		stu.setEmail(email);
		String oldfa = stu.getFa().getId();
		if (fa.isEmpty() || !SRSFunctions.checkForActive(fa)){
			fa = "000000000";
		}
		stu.setFa(SRSFunctions.makeAProfessor(fa));
		stu.setGender(gender);
		stu.setDorm(dorm);
		
		//Student individual File & All Students File & All Persons File
			SRSFunctions.saveAPersonFirstLineInfo(stu);
		//advisor
		if (!oldfa.equals(fa)){
			try {
				Scanner sc = new Scanner(new File(advisorsFile));
				String[] info = new String[100];
				String[] split = new String[100];
				String line;
				boolean add = false;
				int i = 0, j = 0;
				while(sc.hasNextLine()){
					line = sc.nextLine();
					split = line.split("\t");
					if (split[0].equals(fa)){
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
					else if(split[0].equals(oldfa)){
						int k = 1;
						info[i] = split[0] + "\t";
						while(k < split.length){
							if(!idField.getText().equals(split[k])){
								info[i] = info[i] + split[k] + "\t";
							}
							k++;
						}
					}
					else{
						info[i] = line;
					}
					i++;
				}
				if (!add){
					info[i] = fa + "\t" + idField.getText() + "\t";
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
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();		
		stuList2.setSelectedIndex(index);
	}
	public void dropStudent(){
		 String studentID = stuList2.getSelectedValue().substring(0, 9);
		 String crn = couList2.getSelectedValue().substring(0, 9);
		 String inSFile = studentID + ".dat";
		 String inCFile = "C" + crn + ".dat";
		 String line;
		 String[] split = new String[50];
		 
		 try {
			int i = 0, j = 0;
			int x = 0, y = 0;
		
			if (!crn.isEmpty()){
				Scanner scS = new Scanner(new File(inSFile));
				Scanner scC = new Scanner(new File(inCFile));
				String[] infoS = new String[100];
				String[] infoC = new String[100];
				
				while(scS.hasNext()){
					line = scS.nextLine();
					split = line.split("\t");
					if(!split[0].equals(crn)){
						infoS[i] = line;
						i++;
					}
				}
				while(scC.hasNext()){
					line = scC.nextLine();
					split = line.split("\t");
					if(!split[0].equals(studentID)){
						infoC[j] = line;
						j++;
					}
				}
				scS.close();
				scC.close();
				
				PrintWriter pwS = new PrintWriter(new File(inSFile));
				PrintWriter pwC = new PrintWriter(new File(inCFile));
				
				while (x < i){
					pwS.println(infoS[x]);
					x++;
				}
				while (y < j){
					pwC.println(infoC[y]);
					y++;
				}
				pwS.close();
				pwC.close();
			}
			viewCou();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();
	}
	public void addStudent(){
		String studentID = stuList2.getSelectedValue().substring(0, 9);
		List<Course> stuCouList = SRSFunctions.makeCoursesList(studentID);
		String crn = crnField.getText();
		int index =0 ;
		boolean add = false;
		while(stuCouList.listIterator(index).hasNext()){
			if(stuCouList.get(index).getCrn().equals(crn)){
				add = true;
				SRSInfoUsedTwice frame = new SRSInfoUsedTwice();
				frame.setVisible(true);
				break;
			}
			index++;
		}
		
		if(crn.length()<9 || crn.length()>9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}
		else if(SRSFunctions.checkForCourse(crn) && !add && SRSFunctions.checkForActiveCourse(crn)){

			 String inSFile = studentID + ".dat";
			 String inCFile = "C" + crn + ".dat";
			 String line;		 
			 try {
				int i = 0, j = 0;
				int x = 0, y = 0;
				Scanner scS = new Scanner(new File(inSFile));
				Scanner scC = new Scanner(new File(inCFile));
				String[] infoS = new String[100];
				String[] infoC = new String[100];
					
				while(scS.hasNextLine()){
					line = scS.nextLine();
					infoS[i] = line;
					i++;
				}
				while(scC.hasNextLine()){
					line = scC.nextLine();
					infoC[j] = line;
					j++;
				}
				scS.close();
				scC.close();
			//incomplete this course
				String infoForS = crn + "\t" +"N/A" + "\t" + "D" + "\t";
				String infoForC = studentID + "\t" +"N/A"+ "\t" + "D" + "\t";
					
				PrintWriter pwS = new PrintWriter(new File(inSFile));
				PrintWriter pwC = new PrintWriter(new File(inCFile));
					
				while (x < i){
					pwS.println(infoS[x]);
					x++;
				}
				while (y < j){
					pwC.println(infoC[y]);
					y++;
				}
				pwS.println(infoForS);
				pwC.println(infoForC);
				SRSSaveSucceed frame =  new SRSSaveSucceed();
				frame.setVisible(true);
				pwS.close();
				pwC.close();
				viewCou();
				
			} catch (FileNotFoundException e1) {
			}
		}
	}

	public void reactivate() {
		Student stu = SRSFunctions.makeAStudent(reStuIdField.getText());
		SRSFunctions.reactivateAStudent(stu);
		showAllStudents();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}

	public void deactivate(){
		Student stu = SRSFunctions.makeAStudent(stuList2.getSelectedValue().substring(0, 9));
		SRSFunctions.deactivateAStudent(stu);
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllStudents();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == fButton){
			mButton.setSelected(false);
		}
		else if(source == btnIncompleteThisCourse){
			incompleteThisCourse();
		}
		else if(source == btnAddThisStudent){
			addStudent();
		}
		else if(source == mButton){
			fButton.setSelected(false);
		}
		else if(source == returnButton){
			this.dispose();
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		}
		else if(source == refButton){
			this.dispose();
			SRSVieStu frame = new SRSVieStu();
			frame.setVisible(true);
		}
		else if(source == btnViewThisStudents){
			viewStu();
		}
		else if(source == btnVieCou){
			viewCou();
		}
		else if(source == btnChangeGrade){
			changeTheGrade();
		}
		else if(source == btnChangeTheCredits){
			changeTheCredits();
		}
		else if(source == btnActiveForDropping_1){
			activateAllStudentsForDropping();
		}
		else if(source == btnDeactiveForDropping_1){
			deactivateAllStudentsForDropping();
		}
		else if(source == btnActiveForAdding_1){
			activateAllStudentsForAdding();
		}
		else if(source == btnDeactiveForAdding){
			deactivateAllStudentsForAdding();
		}
		else if(source == btnCompleteThisCourse){
			completeThisCourse();
		}
		else if(source == btnSaveEdition){
			saveInfo();
		}
		else if(source == btnDeactivate){
			deactivate();
		}
		else if(source == btnReactivateThisStudent){
			reactivate();
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
		else if(source == btnDropStudentFrom2){
			dropStudent();
		}
		else if(source == btnForThistheseStudents){
			SRSCreateReports.formStudentGeneralReport(stuList2.getSelectedValue().substring(0,9));
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
		}
		else if(source == btnCreateAllStudents){
			SRSCreateReports.formStudentGradeReport(stu.getId(), semeList.getSelectedValue().substring(0, 6));
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		crnField_2.setText(couList2.getSelectedValue().substring(0, 9));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		crnField_2.setText(couList2.getSelectedValue().substring(0, 9));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
