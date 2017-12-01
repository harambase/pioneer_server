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
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import AdminGUIBars.SRSInfoUsedTwice;
import AdminGUIBars.SRSSaveSucceed;
import NewCore.Course;
import NewCore.Professor;
import NewCore.Student;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSChoicer;
import SetUpGUI.SRSProLogin;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SRSTeacherGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField dormField;
	private JTextField birthField;
	private JTextField teleField;
	private JTextField qqField;
	private JTextField eField;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnLogout;
	private JButton btnNewButton;
	private JButton btnViewNewCourses;
	private Professor pro = SRSProLogin.professor;
	private JButton btnViewAllCourses;
	private JList<String> semeList;
	private JButton btnResetTheInfomation;
	private JButton btnSaveTheModifications;
	private JButton btnMod;
	private JList<String> couList2;
	private JTextField textField;
	public static Course course = new Course();
	public static Student student = new Student();
	private JButton btnViewAdvisingStudents;
	private JButton btnRemoveThisStudent;
	private JButton btnAddThisStudent;
	private JButton btnViewThisStudent;
	private JButton btnRefresh;
	private JButton btnCreCouRep;
	private JTextPane textPane;
	private JButton btnCreStuRep;
	private JButton btnCreSch;
	private JButton btnCreateAdvisingList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSTeacherGUI frame = new SRSTeacherGUI();
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
	public SRSTeacherGUI() {
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1186, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JLabel lblStudentMode = new JLabel("Teacher Mode");
		lblStudentMode.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblPartiiStudentCourse = new JLabel("PartII. Record Inquring System");
		lblPartiiStudentCourse.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JScrollPane couList = new JScrollPane();
		couList.setToolTipText("");
		
		btnNewButton = new JButton("View This Course info and Grading");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewNewCourses = new JButton("View Courses from this Semester");
		btnViewNewCourses.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewAllCourses = new JButton("View All Registered Courses");
		btnViewAllCourses.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblAViewCourses = new JLabel("A. View Courses");
		lblAViewCourses.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblBViewA = new JLabel("B. View Selected Course Info and grading");
		lblBViewA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblCViewNew = new JLabel("C. View Semester Courses Info");
		lblCViewNew.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblDViewAdvising = new JLabel("D. View Advising Students");
		lblDViewAdvising.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewAdvisingStudents = new JButton("View Advising Students");
		btnViewAdvisingStudents.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnViewAdvisingStudents.addActionListener(this);
		
		btnRemoveThisStudent = new JButton("<-- Remove this Student");
		btnRemoveThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnRemoveThisStudent.addActionListener(this);
		
		btnAddThisStudent = new JButton("Add this Student");
		btnAddThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAddThisStudent.addActionListener(this);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnViewThisStudent = new JButton("View this Student Infomation");
		btnViewThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnViewThisStudent.addActionListener(this);
		
		btnCreSch = new JButton("Create Course Schedule for this Semester");
		btnCreSch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCreSch.addActionListener(this);
		
		btnCreCouRep = new JButton("Create This Course Report");
		btnCreCouRep.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCreCouRep.addActionListener(this);
		
		textPane = new JTextPane();
		textPane.setText("Notification: New Semester will show up when it is available.");
		textPane.setForeground(Color.RED);
		textPane.setFont(new Font("Times New Roman", Font.BOLD, 13));
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.info);
		
		btnCreStuRep = new JButton("Create Report Of this Student");
		btnCreStuRep.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCreStuRep.addActionListener(this);
		btnCreStuRep.setEnabled(false);
		
		btnCreateAdvisingList = new JButton("Create Advising Report");
		btnCreateAdvisingList.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCreateAdvisingList.addActionListener(this);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(lblPartiiStudentCourse)
							.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(couList, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCreSch, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnViewNewCourses, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(lblCViewNew, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnViewAllCourses, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(lblBViewA, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnCreCouRep, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnRemoveThisStudent, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddThisStudent, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
								.addComponent(btnViewThisStudent, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnViewAdvisingStudents, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(btnCreStuRep, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(lblAViewCourses, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
								.addComponent(lblDViewAdvising, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCreateAdvisingList, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPartiiStudentCourse)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(couList, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAViewCourses, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(btnViewAllCourses, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBViewA, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCreCouRep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCViewNew, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewNewCourses, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreSch)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDViewAdvising, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(btnCreateAdvisingList, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewAdvisingStudents, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveThisStudent, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddThisStudent, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewThisStudent, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreStuRep)))
					.addContainerGap())
		);
		
		
		couList.setViewportView(couList2);
		
		couList2 = new JList<String>();
		couList.setViewportView(couList2);
		
		semeList = new JList<String>();
		scrollPane.setViewportView(semeList);
		panel_1.setLayout(gl_panel_1);
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnRefresh.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblStudentMode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(325, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentMode, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogout))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(3))
		);
		
		JLabel lblNewLabel = new JLabel("Teacher's ID: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Teacher's Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PartI. Teacher Basic Information Card");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblStudentAssignedDormitory = new JLabel("Teacher's Assigned Dormitory:");
		lblStudentAssignedDormitory.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		dormField = new JTextField();
		dormField.setEditable(false);
		dormField.setColumns(10);
		
		JLabel lblStudentBirthday = new JLabel("Teacher's Birthday:");
		lblStudentBirthday.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		birthField = new JTextField();
		birthField.setEditable(false);
		birthField.setColumns(10);
		
		JLabel lblStudentTelephoneNumber = new JLabel("Teacher's telephone number:");
		lblStudentTelephoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		teleField = new JTextField();
		teleField.setEditable(false);
		teleField.setColumns(10);
		
		JLabel lblStudentQqNumber = new JLabel("Teacher's QQ number:");
		lblStudentQqNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		qqField = new JTextField();
		qqField.setEditable(false);
		qqField.setColumns(10);
		
		JLabel lblStudentEmailAddress = new JLabel("Teacher's Email Address:");
		lblStudentEmailAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		eField = new JTextField();
		eField.setEditable(false);
		eField.setColumns(10);
		
		JLabel lblStudentGender = new JLabel("Teacher's Gender:");
		lblStudentGender.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setEnabled(false);
		rdbtnMale.setForeground(Color.BLACK);
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setEnabled(false);
		rdbtnFemale.setForeground(Color.BLACK);
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JTextPane txtpnPleaseConntactSystem = new JTextPane();
		txtpnPleaseConntactSystem.setBackground(SystemColor.info);
		txtpnPleaseConntactSystem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtpnPleaseConntactSystem.setForeground(Color.RED);
		txtpnPleaseConntactSystem.setEditable(false);
		txtpnPleaseConntactSystem.setText("Notifications for Teacher:\r\n1.    Please Conntact System Administrator immediately after notcinig any MISTAKE.\r\n2.    You may change some  personal infomation, but do remeber to click the \"Save\" Button.\r\n3.    Please do not hesitate to consult with System Administrator if you have any question related to use this System.\r\n4.    If you detact any bug or uncomfortable design of this system, please conntact System Administrator immediately.\r\n\r\nThank you for using SRS.");
		
		btnMod = new JButton("Enable Modify Personal infomation");
		btnMod.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnMod.addActionListener(this);
		
		btnSaveTheModifications = new JButton("Save The Modifications");
		btnSaveTheModifications.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnSaveTheModifications.addActionListener(this);
		
		btnResetTheInfomation = new JButton("Reset the infomation");
		btnResetTheInfomation.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnResetTheInfomation.addActionListener(this);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtpnPleaseConntactSystem, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1)
										.addComponent(lblStudentAssignedDormitory, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudentBirthday, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudentTelephoneNumber, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudentQqNumber, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudentGender, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudentEmailAddress, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnMod, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(13)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addComponent(rdbtnMale)
															.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
															.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
														.addComponent(birthField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
														.addComponent(teleField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
														.addComponent(qqField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
														.addComponent(eField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
												.addComponent(idField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
												.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
												.addComponent(dormField, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnResetTheInfomation, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))))
								.addComponent(btnSaveTheModifications, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
							.addGap(32))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel_3)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentAssignedDormitory, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(dormField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentBirthday, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(birthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentTelephoneNumber, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(teleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentQqNumber, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(qqField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentEmailAddress, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(eField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentGender, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMod)
						.addComponent(btnResetTheInfomation))
					.addGap(6)
					.addComponent(btnSaveTheModifications)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtpnPleaseConntactSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		
		rdbtnMale.addActionListener(this);
		rdbtnFemale.addActionListener(this);
		btnLogout.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnViewNewCourses.addActionListener(this);
		rdbtnMale.setSelected(false);
		rdbtnFemale.setSelected(false);
		btnViewAllCourses.addActionListener(this);
		btnCreCouRep.setEnabled(false);
		showInfo();
		showSemester();
	}
	public void showInfo(){
		pro.display();
		idField.setText(pro.getId());
		nameField.setText(pro.getName());
		dormField.setText(pro.getDorm());
		birthField.setText(pro.getBirth());
		teleField.setText(pro.getTel());
		qqField.setText(pro.getQq());
		eField.setText(pro.getEmail());
		if(pro.getGender().equals("Male")){
			rdbtnMale.setSelected(true);
		}
		else{
			rdbtnFemale.setSelected(true);
		}
	}
	public void showTeachingCourse(){
		String teacherID = pro.getId();
		String teacherFile = teacherID + ".dat";
		Course cou = new Course();
		try {
			Scanner scS = new Scanner(new File(teacherFile));
			String[] courses = new String[100];		
			String[] split = new String[10];
			int i = 1;
			scS.nextLine();
			courses[0] = "Format: Course's CRN + Course's Name + Course's Credits";
			while(scS.hasNextLine()){
				split = scS.nextLine().split("\t");
				cou = SRSFunctions.makeACourse(split[0]);
				courses[i] = cou.getCrn() + "     " + cou.getName() + "       " + cou.getCredits();
				i++;
			}
			courses[i] = "Total Registered courses number " + (i - 1) + ".";
			couList2.setListData(courses);
			scS.close();
			btnCreCouRep.setEnabled(true);
			btnCreStuRep.setEnabled(false);
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
			btnCreStuRep.setEnabled(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	public void showAll(){
		String selSem = semeList.getSelectedValue().substring(0, 6);
		 int i = 0;
		 try {
			Scanner sc = new Scanner(new File(selSem + "CourseCatalog.dat"));
			String line;
			String[] split = new String[5];
			String[] courseInfo = new String[50];
	
			i = 2;
			courseInfo[0] = "Format: course's CRN + course's Name + Teacher's Name";
			courseInfo[1] = "These are all courses provided for this semester:";
			while (sc.hasNext()){
				line = sc.nextLine();
				split = line.split("\t");
				Course cou = SRSFunctions.makeACourse(split[0]);
				 courseInfo[i] = cou.getCrn() + "     " + cou.getName() + "       " + cou.getCredits();
				i++;
			}
			 courseInfo[i] = "Total Registered courses " + (i - 1) + ".";
			couList2.setListData( courseInfo);
			sc.close();
			btnCreCouRep.setEnabled(false);
			btnCreStuRep.setEnabled(false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	 
	}
	public void saveInfo(){
		pro.setDorm(dormField.getText());
		pro.setBirth(birthField.getText());
		pro.setTel(teleField.getText());
		pro.setQq(qqField.getText());
		pro.setEmail(eField.getText());
		if(rdbtnMale.isSelected()){
			pro.setGender("Male");
		}
		else{
			pro.setGender("Female");
		}
		SRSFunctions.saveAPersonFirstLineInfo(pro);
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
		showInfo();
		
	}
	public void viewAdv(){
		String teacherID = pro.getId();
		try {
			Scanner sc = new Scanner(new File("Advisors.dat"));
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
						Student stu = SRSFunctions.makeAStudent(split[k]);
						info = stu.getId() + "     " + stu.getName() + "     " + stu.getCredits();
						advList[k] = info;
					}
					k--;
				}
			}
			advList[k + 1] = "Total Amount of advising Students = " + k;
			couList2.setListData(advList);
			btnCreStuRep.setEnabled(true);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void removeStudent(){
		String fa = "000000000";
		Student stu = SRSFunctions.makeAStudent(couList2.getSelectedValue().substring(0, 9));
		String oldfa = stu.getFa().getId();
		if (!oldfa.equals(fa)){
			
			stu.setFa(SRSFunctions.makeAProfessor(fa));
			SRSFunctions.saveAPersonFirstLineInfo(stu);
			stu.dispaly();
			try {
				Scanner sc = new Scanner(new File("Advisors.dat"));
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
				PrintWriter pw = new PrintWriter(new File("Advisors.dat"));
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
	public void addStudent(){
		Student stu = SRSFunctions.makeAStudent(textField.getText());
		String id = stu.getId();
		if(SRSFunctions.checkForPerson(id) && SRSFunctions.checkForActive(id)){
			String fa = pro.getId();	
			String oldfa = stu.getFa().getId();
			
			if (!oldfa.equals(fa)){
				stu.setFa(SRSFunctions.makeAProfessor(fa));
				SRSFunctions.saveAPersonFirstLineInfo(stu);
				stu.dispaly();
				try {
					Scanner sc = new Scanner(new File("Advisors.dat"));
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
					PrintWriter pw = new PrintWriter(new File("Advisors.dat"));
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
	}
	public void createCourseSc(){
		String selSem = semeList.getSelectedValue().substring(0, 6);
		String id = pro.getId();
		SRSCreateReports.formPersonCourseSchedule(id, selSem);
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createCourseReport(){
		SRSCreateReports.formCourseReport(couList2.getSelectedValue().substring(0, 9));
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createStudentReport(){
		SRSCreateReports.formStudentGeneralReport(couList2.getSelectedValue().substring(0, 9));
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createAdvReport(){
		SRSCreateReports.formAdvReport(pro.getId());
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if(source == rdbtnMale){
			rdbtnFemale.setSelected(false);
		}
		else if(source == btnCreateAdvisingList){
			createAdvReport();
		}
		else if(source == rdbtnFemale){
			rdbtnMale.setSelected(false);
		}
		
		else if(source == btnLogout){
			this.dispose();
			SRSChoicer frame = new SRSChoicer();
			frame.setVisible(true);
		}
		else if(source == btnViewAllCourses){
			showTeachingCourse();
		}
		else if(source == btnResetTheInfomation){
			showInfo();
		}
		else if(source == btnCreStuRep){
			createStudentReport();
		}
		else if(source == btnMod){
			dormField.setEditable(true);
			birthField.setEditable(true);
			teleField.setEditable(true);
			qqField.setEditable(true);
			eField.setEditable(true);
			rdbtnMale.setEnabled(true);
			rdbtnFemale.setEnabled(true);
		}
		else if(source == btnSaveTheModifications){
			dormField.setEditable(false);
			birthField.setEditable(false);
			teleField.setEditable(false);
			qqField.setEditable(false);
			eField.setEditable(false);
			rdbtnMale.setEnabled(false);
			rdbtnFemale.setEnabled(false);
			saveInfo();
		}
		else if(source == btnViewNewCourses){
			showAll();
		}
		else if(source == btnViewAdvisingStudents){
			viewAdv();
		}
		else if(source == btnRemoveThisStudent){
			removeStudent();
		}
		else if(source == btnCreSch){
			createCourseSc();
		}
		else if(source == btnAddThisStudent){
			addStudent();
		}
		else if(source == btnViewThisStudent){
			student = SRSFunctions.makeAStudent(couList2.getSelectedValue().substring(0, 9));
			student.dispaly();
			SRSVieAdvStu frame = new SRSVieAdvStu();
			frame.setVisible(true);
		}
		else if(source == btnRefresh){
			this.dispose();
			SRSTeacherGUI frame = new SRSTeacherGUI();
			frame.setVisible(true);
		}
		else if(source == btnCreCouRep){
			createCourseReport();
		}
		else if(source == btnNewButton){
			course = SRSFunctions.makeACourse(couList2.getSelectedValue().substring(0, 9));
			if(course.getPro().getId().equals(pro.getId())){
				SRSTeaVieCou frame;
				try {
					frame = new SRSTeaVieCou();
					frame.setVisible(true);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else{
				SRSTeaVieCouSim frame;
				try {
					frame = new SRSTeaVieCouSim();
					frame.setVisible(true);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}