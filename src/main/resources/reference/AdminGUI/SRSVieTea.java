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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

import AdminGUIBars.SRSInfoNotPassed;
import AdminGUIBars.SRSInfoOldTeacherRemoved;
import AdminGUIBars.SRSInfoUsedTwice;
import AdminGUIBars.SRSRegisterError;
import AdminGUIBars.SRSSaveSucceed;
import NewCore.Course;
import NewCore.Professor;
import NewCore.Student;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSAdInterface;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextPane;

public class SRSVieTea extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField crnField;
	private JList<String> teaList2;
	private JButton btnViewThisTeachers;
	private JButton btnVieCou;
	private JButton btnReactivateThisStudent;
	private JButton btnDeactivate;
	private JButton returnButton;
	private JButton refButton;
	private JScrollPane couList;
	private JScrollPane teaList;
	private JScrollPane stuList;
	private static final String teachersFile = "Teachers.dat";
	private static final String advisorsFile = "Advisors.dat";
	private Student stu;
	private Course cou;
	private Professor pro;
	private JLabel lblStudentIdStudent;
	private JLabel lblTheIdOf;
	private JTextField reTeaIdField;
	private JLabel lblForOnemultipleStudents;
	private JLabel lblStudentInformationForm;
	private JButton btnViewThisAdvising;
	private JLabel lbladdAStudent;
	private JList<String> stuList2;
	private JTextField textField;
	private JLabel lblStudentId;
	private JButton btnAddThisStudent;
	private JPanel panel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField nameField;
	private JTextField idField;
	private JTextField passwordField;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField dormField;
	private JTextField birthField;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JTextField telField;
	private JTextField qqField;
	private JTextField eField;
	private JLabel label_10;
	private JRadioButton mButton;
	private JRadioButton fButton;
	private JButton btnSaveThisEdition;
	private JButton btnDeletTeacher;
	private JButton btnResetFromAbove;
	private JButton btnAddThisTeacher;
	private JButton btnRemoveFrom;
	private JButton btnDeletThisStudent;
	private JTextPane txtpnNotificationsDeactivateA;
	private JButton btnCreAdvList;
	private JScrollPane scrollPane;
	private JList<String> list;
	private JLabel lblTeachingCourse;
	private JList<String> semeList;
	private JButton btnTeaCouSc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSVieTea frame = new SRSVieTea();
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
	public SRSVieTea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1124, 876);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		refButton = new JButton("Refresh ");
		refButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		returnButton = new JButton("Return to Pervious Menu");
		returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_13 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		teaList2 = new JList<String>();
		teaList2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		teaList = new JScrollPane(teaList2);
		
		btnViewThisTeachers = new JButton("View this Teacher's infomation");
		btnViewThisTeachers.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblViewingTeacherMode = new JLabel("Viewing Teacher Mode");
		lblViewingTeacherMode.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnDeactivate = new JButton("Deactivate for using");
		btnDeactivate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnReactivateThisStudent = new JButton("Reactivate");
		btnReactivateThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnReactivateThisStudent.addActionListener(this);
		
		JLabel lblIndividualFunctionButtons = new JLabel("Individual Teacher Function Buttons");
		lblIndividualFunctionButtons.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		JLabel lblBstudentInfomation = new JLabel("Teacher Registration Buttons");
		lblBstudentInfomation.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		btnVieCou = new JButton("View this Teacher's Teaching Courses in this Semseter");
		btnVieCou.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		couList = new JScrollPane();
		
		btnRemoveFrom = new JButton("Remove teacher from teaching this course");
		btnRemoveFrom.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnAddThisTeacher = new JButton("Add this Teacher to this course (CRN only)");
		btnAddThisTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		crnField = new JTextField();
		crnField.setColumns(10);
		
		JLabel lblAddASpecific = new JLabel("1.Add a course to this Teacher:");
		lblAddASpecific.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		lblStudentIdStudent = new JLabel("Teacher's Data Format: Teacher's ID+ Teacher's Name +  Teacher's Activation (A/D)");
		lblStudentIdStudent.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentIdStudent.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		lblTheIdOf = new JLabel("The id of teacher to reactivate");
		lblTheIdOf.setHorizontalAlignment(SwingConstants.LEFT);
		lblTheIdOf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		reTeaIdField = new JTextField();
		reTeaIdField.setColumns(10);
		
		lblForOnemultipleStudents = new JLabel("For one Teacher:");
		lblForOnemultipleStudents.setHorizontalAlignment(SwingConstants.LEFT);
		lblForOnemultipleStudents.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		lblStudentInformationForm = new JLabel("Teacher Information Form");
		lblStudentInformationForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnViewThisAdvising = new JButton("View this Advising Students");
		btnViewThisAdvising.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		lbladdAStudent = new JLabel("2.Add a Student to the Teacher's Advising List");
		lbladdAStudent.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		stuList2 = new JList<String>();
		stuList = new JScrollPane(stuList2);

		textField = new JTextField();
		textField.setColumns(10);
		
		lblStudentId = new JLabel("Student ID:*");
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnAddThisStudent = new JButton("Add this Student");
		btnAddThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		
		label_1 = new JLabel("Teacher's Name (English):*");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		label_2 = new JLabel("Teacher's Password:*");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		label_3 = new JLabel("Teacher's ID number:*");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		nameField = new JTextField();
		nameField.setText("");
		nameField.setColumns(10);
		
		idField = new JTextField();
		idField.setText("");
		idField.setEditable(false);
		idField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setText("");
		passwordField.setColumns(10);
		
		label_4 = new JLabel("Teacher's Birthday (YYYY/MM/DD):*");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		label_5 = new JLabel("Student's Assigned Dormitory:");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		dormField = new JTextField();
		dormField.setText("");
		dormField.setColumns(10);
		
		birthField = new JTextField();
		birthField.setText("");
		birthField.setColumns(10);
		
		label_6 = new JLabel("Student's Contact Infomation(Optional)");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		label_7 = new JLabel("QQ:");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		label_8 = new JLabel("Telephone:");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		label_9 = new JLabel("Email:");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		telField = new JTextField();
		telField.setText("");
		telField.setColumns(10);
		
		qqField = new JTextField();
		qqField.setText("");
		qqField.setColumns(10);
		
		eField = new JTextField();
		eField.setText("");
		eField.setColumns(10);
		
		label_10 = new JLabel("Gender:*");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		mButton = new JRadioButton("Male");
		mButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		fButton = new JRadioButton("Female");
		fButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(idField, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
							.addGap(15))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
							.addGap(17))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(dormField, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(birthField, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
								.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_8)
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED, 90, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(telField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
										.addComponent(qqField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
										.addComponent(eField, Alignment.TRAILING))))
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mButton, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(fButton, GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
							.addGap(17))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(birthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(telField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(qqField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_9)
						.addComponent(eField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(dormField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(mButton)
						.addComponent(fButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		btnSaveThisEdition = new JButton("Save this Edition");
		btnSaveThisEdition.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeletTeacher = new JButton("Delete this Teacher");
		btnDeletTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnResetFromAbove = new JButton("Reset Form Above");
		btnResetFromAbove.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeletThisStudent = new JButton("Remove this Student");
		btnDeletThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		txtpnNotificationsDeactivateA = new JTextPane();
		txtpnNotificationsDeactivateA.setEditable(false);
		txtpnNotificationsDeactivateA.setText("Notifications: Deactivate a teacher will cause:\r\n1.  This teacher will be removed from Teachers.dat file, which means you cannot view his/her info here. You may reactivate this teacher, or you can go to \"View Person Mode\".\r\n2.  This teacher will be removed irreversibly from all courses he/she is/was teaching, which means  when you in \u201CView Course Mode\u201D, you will not find infomation about instructor.\r\n3.  All Students who were under this teacher's advise will be dropped, and set to default.");
		txtpnNotificationsDeactivateA.setForeground(Color.RED);
		txtpnNotificationsDeactivateA.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtpnNotificationsDeactivateA.setBackground(SystemColor.info);
		
		scrollPane = new JScrollPane();
		
		lblTeachingCourse = new JLabel("Teaching Courses:");
		lblTeachingCourse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblViewingTeacherMode, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblStudentIdStudent, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnViewThisTeachers, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
								.addComponent(btnDeletTeacher, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
								.addComponent(teaList, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(stuList, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(couList, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(btnAddThisTeacher, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(btnRemoveFrom, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(lbladdAStudent, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblStudentId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddThisStudent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnDeletThisStudent, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblAddASpecific, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(crnField, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnViewThisAdvising, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(btnVieCou, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
								.addComponent(lblTeachingCourse, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtpnNotificationsDeactivateA, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblForOnemultipleStudents, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblIndividualFunctionButtons, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(refButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(0)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSaveThisEdition, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
										.addComponent(btnResetFromAbove, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(197)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btnDeactivate, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(reTeaIdField, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnReactivateThisStudent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
										.addComponent(lblStudentInformationForm, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)))
								.addComponent(returnButton, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
							.addGap(423))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBstudentInfomation)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTheIdOf, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblViewingTeacherMode, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(1)
							.addComponent(lblStudentIdStudent, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(refButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(returnButton)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblBstudentInfomation, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblIndividualFunctionButtons, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(reTeaIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnReactivateThisStudent)
										.addComponent(lblTheIdOf))
									.addGap(2)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDeactivate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblForOnemultipleStudents))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtpnNotificationsDeactivateA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblStudentInformationForm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(173)
									.addComponent(lblTeachingCourse, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(crnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAddASpecific, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddThisTeacher)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnViewThisAdvising)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(stuList, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeletThisStudent)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbladdAStudent, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSaveThisEdition, Alignment.TRAILING)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnViewThisTeachers)
								.addComponent(btnVieCou))
							.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(teaList, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(couList, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 507, Short.MAX_VALUE)
									.addComponent(btnRemoveFrom)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblStudentId)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddThisStudent)
							.addComponent(btnResetFromAbove))
						.addComponent(btnDeletTeacher))
					.addContainerGap())
		);
		
		btnCreAdvList = new JButton("  Create Adving List Report For Teacher");
		stuList.setColumnHeaderView(btnCreAdvList);
		btnCreAdvList.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnCreAdvList.addActionListener(this);
		
		semeList = new JList<String>();
		couList.setViewportView(semeList);
		
		btnTeaCouSc = new JButton("Create Teaching Courses Schedule");
		btnTeaCouSc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		couList.setColumnHeaderView(btnTeaCouSc);
		btnTeaCouSc.addActionListener(this);
		
		list = new JList<String>();
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
		btnViewThisTeachers.addActionListener(this);
		btnVieCou.addActionListener(this);
		btnReactivateThisStudent.addActionListener(this);
		btnDeactivate.addActionListener(this);
		returnButton.addActionListener(this);
		refButton.addActionListener(this);
		btnResetFromAbove.addActionListener(this);
		btnSaveThisEdition.addActionListener(this);
		btnRemoveFrom.addActionListener(this);
		btnAddThisTeacher.addActionListener(this);
		btnAddThisStudent.addActionListener(this);
		btnDeletThisStudent.addActionListener(this);
		btnViewThisAdvising.addActionListener(this);
		
		showAllTeachers();
		showSemester();
		stu = new Student();
		pro = new Professor();
		cou = new Course();
		new LinkedList<Course>();
		pro = new Professor();
	}
	
	public void showAllTeachers(){
		try {
			Scanner sc = new Scanner(new File(teachersFile));
			String[] info = new String[1000];
			String[] split = new String[10];
			int i = 1;
			sc.nextLine();
			info[0] = "Notice: Data strcuture is showing above the view Button.";
			while (sc.hasNextLine()){
				split = sc.nextLine().split("\t");
				pro = SRSFunctions.makeAProfessor(split[0]);
				info[i] = pro.getId() + "     " + pro.getName() + "      " +
						  pro.getActive();
				i++;
			}
			info[i + 1] = "Total Active Teacher Amount: " + (i - 1) + ".";
			teaList2.setListData(info);
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
		String teacherID =  teaList2.getSelectedValue().substring(0, 9);
		String teacherFile = teacherID + ".dat";
		SRSFunctions.makeCoursesList(teacherID);
		try {
			Scanner scS = new Scanner(new File(teacherFile));
			String[] courses = new String[100];		
			String[] split = new String[10];
			String seme = semeList.getSelectedValue().substring(0,6);
			int i = 1;
			scS.nextLine();
			courses[0] = "Format: CRN + Course Name + Credits";
			while(scS.hasNextLine()){
				split = scS.nextLine().split("\t");
				if(split[0].substring(0, 6).equals(seme)){
					cou = SRSFunctions.makeACourse(split[0]);
					courses[i] = cou.getCrn() + "      " + cou.getName()
							+ "      " + cou.getCredits();
					i++;
				}
			}
			courses[i + 1] = "Total Teaching Courses Amount: " + (i - 1) + ".";
			list.setListData(courses);
			scS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void viewPro(){
		String teacherID =  teaList2.getSelectedValue().substring(0, 9);
		String teacherFile = teacherID + ".dat";
		//Teacher file: ID + Password +  Name + Active? + type + birth + tele + qq + email + dorm + Gender	
		try {
			Scanner scS = new Scanner(new File(teacherFile));
			String[] infoS = new String[30];

			infoS = scS.nextLine().split("\t");
			
			String password = infoS[1];
			String name = infoS[2];
			String birth = infoS[5];
			String tele = infoS[6];
			String qq = infoS[7];
			String email = infoS[8];
			String dorm = infoS[9];
			String gender = infoS[10];

			idField.setText(teacherID);
			passwordField.setText(password);
			nameField.setText(name);
			telField.setText(tele);
			qqField.setText(qq);
			eField.setText(email);
			dormField.setText(dorm);
			birthField.setText(birth);
			
			if(gender.equals("Male")){
				mButton.setSelected(true);
				fButton.setSelected(false);
			}
			else{
				fButton.setSelected(true);
				mButton.setSelected(false);
			}
			scS.close();
			pro = SRSFunctions.makeAProfessor(teacherID);
			String[] info = new String[10];
			list.setListData(info);
			viewAdv();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	
	public void viewAdv(){
		String teacherID = teaList2.getSelectedValue().substring(0, 9);
		try {
			Scanner sc = new Scanner(new File(advisorsFile));
			String line, info;
			String[] split = new String[100];
			String[] advList = new String[100];
			int k = 0;
			advList[0] = "Format: Student ID + Student Name + Student Credits";
			while(sc.hasNextLine()){
				line = sc.nextLine();
				split = line.split("\t");
				if(split[0].equals(teacherID)){
					for(k = 1; k<split.length; k++){
						stu = SRSFunctions.makeAStudent(split[k]);
						info = stu.getId() + "     " + stu.getName() + "     " + stu.getCredits();
						advList[k] = info;
					}
					k--;
				}
			}
			advList[k + 1] = "Total Amount of advising Students = " + k;
			stuList2.setListData(advList);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void deactivate(){
		String teacherID =  teaList2.getSelectedValue().substring(0, 9);
		Professor pro = SRSFunctions.makeAProfessor(teacherID);
		SRSFunctions.deactivateATeacher(pro);
		showAllTeachers();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void reactivate(){
		String teacherID =  reTeaIdField.getText();
		Professor pro = SRSFunctions.makeAProfessor(teacherID);
		SRSFunctions.reactivateATeacher(pro);
		showAllTeachers();
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void addCourse(){
		Professor pro = SRSFunctions.makeAProfessor(teaList2.getSelectedValue().substring(0, 9));
		String crn = crnField.getText();
		if(crn.length()<9 || crn.length()>9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}
		else if(SRSFunctions.checkForCourse(crn) 
				&& SRSFunctions.checkForActiveCourse(crn)){
			Course cou = SRSFunctions.makeACourse(crn);
			if(cou.getTeacherID().equals(pro.getId())){
				SRSInfoUsedTwice frame = new SRSInfoUsedTwice();
				frame.setVisible(true);
			}
			else if(!cou.getTeacherID().equals("000000000")){
				SRSInfoOldTeacherRemoved frame = new SRSInfoOldTeacherRemoved();
				frame.setVisible(true);
				Professor oldPro = SRSFunctions.makeAProfessor(cou.getTeacherID());
				SRSFunctions.removeACourseFromAProfessor(oldPro, crn);
				SRSFunctions.addACourseToAProfessor(pro, crn);	
				SRSSaveSucceed frame3 = new SRSSaveSucceed();
				frame3.setVisible(true);
				viewCou();
			}
				
			else{
				SRSFunctions.addACourseToAProfessor(pro, crn);
				SRSSaveSucceed frame = new SRSSaveSucceed();
				frame.setVisible(true);
				viewCou();
			}
		}
		else{
			SRSInfoNotPassed frame = new SRSInfoNotPassed();
			frame.setVisible(true);
		}

	}
	public void removeStudent(){
		String fa = "000000000";
		Student stu = SRSFunctions.makeAStudent(stuList2.getSelectedValue().substring(0, 9));
		String oldfa = stu.getFa().getId();
		if (!oldfa.equals(fa)){
			
			stu.setFa(SRSFunctions.makeAProfessor(fa));
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			stu.dispaly();
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
							if(stu.getId().equals(split[k])){
								add = true;
								info[i] = line;
								break;
							}
							k++;
						}
						if(!add){
							info[i] = line + stu.getId() + "\t" ;
							add = true;	
						}
					}
					else if(split[0].equals(oldfa)){
						int k = 1;
						info[i] = split[0] + "\t";
						while(k < split.length){
							if(!stu.getId().equals(split[k])){
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
					info[i] = fa + "\t" + textField.getText() + "\t";
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
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
			viewAdv();
		}
		else{
			SRSInfoUsedTwice frame = new SRSInfoUsedTwice();
			frame.setVisible(true);
		}
	}
	public void saveInfo(){
		String teacherID =  teaList2.getSelectedValue().substring(0, 9);
		//Teacher file: ID + Password +  Name + Active? + type + birth + tele + qq + email + dorm + Gender	
		String password = passwordField.getText();
		String name = nameField.getText();
		String birth = birthField.getText();
		String tele = telField.getText();
		String qq = qqField.getText();
		String email = eField.getText();
		String dorm = dormField.getText();
		String gender;
		
		if (mButton.isSelected()){
			gender = "Male";
		}
		else{
			gender = "Female";
		}
		if (telField.getText().isEmpty()){
			tele = "N/A";
		}
		else{
			tele = telField.getText();
		}
		if (qqField.getText().isEmpty()){
			qq = "N/A";
		}
		else{
			qq = qqField.getText();
		}
		if (eField.getText().isEmpty()){
			email = "N/A";
		}
		else{
			email = eField.getText();
		}
		if (dormField.getText().isEmpty()){
			dorm = "N/A";
		}
		else{
			dorm = dormField.getText();
		}
		if (mButton.isSelected()){
			gender = "Male";
		}
		else{
			gender = "Female";
		}
		
		pro = SRSFunctions.makeAProfessor(teacherID);
		pro.setPassword(password);
		pro.setName(name);
		pro.setBirth(birth);
		pro.setTel(tele);
		pro.setQq(qq);
		pro.setEmail(email);
		pro.setGender(gender);
		pro.setDorm(dorm);
		pro.setType("T");
		SRSFunctions.saveAPersonFirstLineInfo(pro);
		
		SRSSaveSucceed frame =  new SRSSaveSucceed();
		frame.setVisible(true);
		showAllTeachers();		
	}
	public void addStudent(){
		String fa = teaList2.getSelectedValue().substring(0, 9);
		Student stu = SRSFunctions.makeAStudent(textField.getText());
		String oldfa = stu.getFa().getId();
		
		if (!oldfa.equals(fa)){
			
			stu.setFa(SRSFunctions.makeAProfessor(fa));
			SRSFunctions.saveAPersonFirstLineInfo(stu);
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
							if(textField.getText().equals(split[k])){
								add = true;
								info[i] = line;
								break;
							}
							k++;
						}
						if(!add){
							info[i] = line + textField.getText() + "\t" ;
							add = true;	
						}
					}
					else if(split[0].equals(oldfa)){
						int k = 1;
						info[i] = split[0] + "\t";
						while(k < split.length){
							if(!textField.getText().equals(split[k])){
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
					info[i] = fa + "\t" + textField.getText() + "\t";
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
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
			viewAdv();
		}
		else{
			SRSInfoUsedTwice frame = new SRSInfoUsedTwice();
			frame.setVisible(true);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == btnViewThisTeachers){
			viewPro();
		}
		else if(source == btnTeaCouSc){
			String id = teaList2.getSelectedValue().substring(0, 9);
			String seme = semeList.getSelectedValue().substring(0,6);
			SRSCreateReports.formPersonCourseSchedule(id, seme);
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
		}
		else if(source == btnReactivateThisStudent){
			reactivate();
		}
		else if(source == btnSaveThisEdition){
			saveInfo();
		}
		else if(source == refButton){
			this.dispose();
			SRSVieTea frame = new SRSVieTea();
			frame.setVisible(true);
		}
		else if(source == returnButton){
			this.dispose();
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		}
		else if(source == btnViewThisAdvising){
			viewAdv();
		}
		else if(source == btnVieCou){
			viewCou();
		}
		else if(source == btnDeletThisStudent){
			removeStudent();
		}
		else if(source == btnRemoveFrom){
			Professor pro = SRSFunctions.makeAProfessor(teaList2.getSelectedValue().substring(0, 9));
			String crn = list.getSelectedValue().substring(0, 9);
			SRSFunctions.removeACourseFromAProfessor(pro, crn);
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
			viewCou();
		}
		else if(source == btnAddThisTeacher){
			addCourse();
		}
		else if(source == btnCreAdvList){
			String id = teaList2.getSelectedValue().substring(0, 9);
			SRSCreateReports.formAdvReport(id);
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
		}
		else if(source == btnAddThisStudent){
			if(textField.getText().length()<9 || textField.getText().length()>9){
				SRSRegisterError frame = new SRSRegisterError();
				frame.setVisible(true);
			}
			else if(SRSFunctions.checkForPerson(textField.getText()) 
					&& SRSFunctions.checkForActive(textField.getText())){
				addStudent();
			}
		}
		else if(source == btnResetFromAbove){
			nameField.setText("");
			birthField.setText("");
			qqField.setText("");
			eField.setText("");
			dormField.setText("");
			telField.setText("");
			mButton.setSelected(false);
			fButton.setSelected(false);
			passwordField.setText("");
		}
	}
}
