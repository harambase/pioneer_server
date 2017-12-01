package StudentGUI;

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
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import AdminGUIBars.SRSSaveSucceed;
import NewCore.Course;
import NewCore.Student;
import NewCore.Transcript;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSChoicer;
import SetUpGUI.SRSStuLogin;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SRSStudentGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField faField;
	private JTextField creField;
	private JTextField dormField;
	private JTextField birthField;
	private JTextField teleField;
	private JTextField qqField;
	private JTextField eField;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnLogout;
	private JButton btnNewButton;
	private JList<String> couList2;
	private JButton btnViewNewCourses;
	private JButton btnAddThisCourse;
	private Student stu = SRSStuLogin.student;
	private JButton btnDropThisCourse;
	private JTextField timeField;
	private JTextField dayField;
	private JTextField startField;
	private JTextField creField_2;
	private JTextField couLevField;
	private JTextField courseNameField;
	private JTextField crnField;
	private JTextField capaField;
	private JTextField teaField;
	private JButton btnViewAllCourses;
	private JRadioButton eButton;
	private JRadioButton cButton;
	private JTextPane textPane;
	private JTextPane txtpnNotifictaions;
	private JList<String> semeList;
	private JButton button;
	private JButton btnFormGradeReport;
	private JButton btnGetThisSemester;
	private JButton btnGen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSStudentGUI frame = new SRSStudentGUI();
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
	public SRSStudentGUI() {
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1647, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JLabel lblStudentMode = new JLabel("Student Mode");
		lblStudentMode.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblPartiiStudentCourse = new JLabel("PartII. Course Record Inquring System");
		lblPartiiStudentCourse.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JScrollPane couList = new JScrollPane();
		
		btnNewButton = new JButton("View This Course info -->");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnDropThisCourse = new JButton("<-- Drop This Course(No Transcript Entry )");
		btnDropThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewNewCourses = new JButton("View Courses from this Semester");
		btnViewNewCourses.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnAddThisCourse = new JButton("<-- Add This Course");
		btnAddThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewAllCourses = new JButton("View All Registered Courses");
		btnViewAllCourses.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblAViewCourses = new JLabel("A. View Courses");
		lblAViewCourses.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblBViewA = new JLabel("B. View Selected Course Info");
		lblBViewA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblCViewNew = new JLabel("C. View Semester Courses Info");
		lblCViewNew.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtpnNotifictaions = new JTextPane();
		txtpnNotifictaions.setBackground(SystemColor.info);
		txtpnNotifictaions.setForeground(Color.RED);
		txtpnNotifictaions.setEditable(false);
		txtpnNotifictaions.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		button = new JButton("<-- Drop This Course(\"W\" Grade)");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		button.addActionListener(this);
		
		btnFormGradeReport = new JButton("Create Grade Report of current Semester");
		btnFormGradeReport.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnFormGradeReport.addActionListener(this);
		
		btnGetThisSemester = new JButton("Create this Semester schedule");
		btnGetThisSemester.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnGetThisSemester.addActionListener(this);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(lblPartiiStudentCourse))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(couList, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnFormGradeReport, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(btnViewNewCourses, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(txtpnNotifictaions, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(btnAddThisCourse, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(btnDropThisCourse, GroupLayout.PREFERRED_SIZE, 306, Short.MAX_VALUE)
								.addComponent(btnViewAllCourses, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(lblBViewA, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(lblAViewCourses, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(lblCViewNew, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(btnGetThisSemester, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(23)
					.addComponent(lblPartiiStudentCourse)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(lblAViewCourses, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnViewAllCourses, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblBViewA, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDropThisCourse)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddThisCourse, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCViewNew, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewNewCourses, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFormGradeReport)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGetThisSemester)
							.addGap(11)
							.addComponent(txtpnNotifictaions, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(couList, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		semeList = new JList<String>();
		scrollPane.setViewportView(semeList);
		
		couList2 = new JList<String>();
		couList2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		couList.setViewportView(couList2);
		panel_1.setLayout(gl_panel_1);
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		
		JLabel lblPartiiiCourseInfomation = new JLabel("SectionIII. Course Information Inquring Form");
		lblPartiiiCourseInfomation.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(null);
		
		JLabel lblSectioniiiCourseInstruction = new JLabel("SectionIII: Course Instruction  Information Form:");
		lblSectioniiiCourseInstruction.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblTheDaysOf = new JLabel("The days of this Course:");
		lblTheDaysOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblTheDateOf = new JLabel("The date of this Course: (Start - End)");
		lblTheDateOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblTheTimeOf = new JLabel("The time of this Course: (Starts - Ends)");
		lblTheTimeOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		timeField = new JTextField();
		timeField.setEditable(false);
		timeField.setText("");
		timeField.setColumns(10);
		
		dayField = new JTextField();
		dayField.setEditable(false);
		dayField.setText("");
		dayField.setColumns(10);
		
		startField = new JTextField();
		startField.setEditable(false);
		startField.setText("");
		startField.setColumns(10);
		
		JLabel label_6 = new JLabel("The CRN of this Course:*");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_7 = new JLabel("Course Name:*");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblCourseLevel = new JLabel("Course Level:");
		lblCourseLevel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_9 = new JLabel("Credites for This Course*");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		creField_2 = new JTextField();
		creField_2.setEditable(false);
		creField_2.setText("");
		creField_2.setColumns(10);
		
		JLabel label_10 = new JLabel("Requirement:*");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		eButton = new JRadioButton("Elective");
		eButton.setForeground(Color.BLACK);
		eButton.setBackground(Color.WHITE);
		eButton.setEnabled(false);
		eButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		eButton.addActionListener(this);
		eButton.setSelected(false);
		
		cButton = new JRadioButton("Compulsory");
		cButton.setForeground(Color.BLACK);
		cButton.setBackground(Color.WHITE);
		cButton.setEnabled(false);
		cButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cButton.addActionListener(this);
		cButton.setSelected(false);
		
		couLevField = new JTextField();
		couLevField.setEditable(false);
		couLevField.setText("");
		couLevField.setColumns(10);
		
		courseNameField = new JTextField();
		courseNameField.setEditable(false);
		courseNameField.setText("");
		courseNameField.setColumns(10);
		
		crnField = new JTextField();
		crnField.setEditable(false);
		crnField.setText("");
		crnField.setColumns(10);
		
		JLabel lblSectioniiCourseSchedule = new JLabel("SectionII: Course Schedule  Information Form:");
		lblSectioniiCourseSchedule.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblSectioniCourseBasic = new JLabel("SectionI: Course Basic Information Form:");
		lblSectioniCourseBasic.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_13 = new JLabel("The Maximum Capacity of this Course:");
		label_13.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		capaField = new JTextField();
		capaField.setEditable(false);
		capaField.setText("");
		capaField.setColumns(10);
		
		JLabel lblTheInstructorOf = new JLabel("The Instructor of this Course:");
		lblTheInstructorOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		teaField = new JTextField();
		teaField.setEditable(false);
		teaField.setText("");
		teaField.setColumns(10);
		
		JLabel lblPartivCourseGrade = new JLabel("SectionIV: Course Grade Information Form:");
		lblPartivCourseGrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblYourGradescoreOn = new JLabel("Your Grade/Score on this Course: ");
		lblYourGradescoreOn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.info);
		textPane.setForeground(Color.RED);
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textPane.setEditable(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(10)
							.addComponent(lblYourGradescoreOn, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_3.createSequentialGroup()
										.addGap(10)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
											.addComponent(lblTheInstructorOf, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
											.addComponent(capaField, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
											.addComponent(teaField, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
										.addGap(209))
									.addComponent(lblSectioniiiCourseInstruction, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_3.createSequentialGroup()
										.addGap(10)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_3.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
													.addComponent(lblCourseLevel, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
													.addComponent(label_7)
													.addComponent(label_6)
													.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
													.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_panel_3.createSequentialGroup()
														.addComponent(eButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(cButton))
													.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(creField_2, Alignment.LEADING)
														.addComponent(couLevField, Alignment.LEADING)
														.addComponent(crnField, Alignment.LEADING)
														.addComponent(courseNameField, Alignment.LEADING, 212, 212, Short.MAX_VALUE)))
												.addGap(206))
											.addGroup(gl_panel_3.createSequentialGroup()
												.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
													.addGroup(gl_panel_3.createSequentialGroup()
														.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
															.addComponent(lblTheDaysOf, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
															.addComponent(lblTheDateOf, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
														.addGap(27))
													.addGroup(gl_panel_3.createSequentialGroup()
														.addComponent(lblTheTimeOf, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
														.addGap(18)))
												.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
													.addComponent(timeField, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
													.addComponent(startField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
													.addComponent(dayField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
												.addGap(210))))
									.addComponent(lblSectioniCourseBasic, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblSectioniiCourseSchedule, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE))
								.addGap(385))
							.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(lblPartivCourseGrade, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSectioniCourseBasic, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(crnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(courseNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCourseLevel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(couLevField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(creField_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(cButton)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(eButton))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(lblSectioniiCourseSchedule, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTheDaysOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTheDateOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addComponent(startField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTheTimeOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSectioniiiCourseInstruction, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTheInstructorOf)
						.addComponent(teaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_13)
						.addComponent(capaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblPartivCourseGrade, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYourGradescoreOn, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(12)
							.addComponent(lblPartiiiCourseInfomation))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(23)
					.addComponent(lblPartiiiCourseInfomation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 472, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
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
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStudentMode, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
					.addGap(3))
		);
		
		JLabel lblNewLabel = new JLabel("Student ID: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Student Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Student Faculty Advisor:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblS = new JLabel("Student Earned Credits:");
		lblS.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setColumns(10);
		
		faField = new JTextField();
		faField.setEditable(false);
		faField.setColumns(10);
		
		creField = new JTextField();
		creField.setEditable(false);
		creField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PartI. Student Basic Information Card");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblStudentAssignedDormitory = new JLabel("Student Assigned Dormitory:");
		lblStudentAssignedDormitory.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		dormField = new JTextField();
		dormField.setEditable(false);
		dormField.setColumns(10);
		
		JLabel lblStudentBirthday = new JLabel("Student Birthday:");
		lblStudentBirthday.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		birthField = new JTextField();
		birthField.setEditable(false);
		birthField.setColumns(10);
		
		JLabel lblStudentTelephoneNumber = new JLabel("Student telephone number:");
		lblStudentTelephoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		teleField = new JTextField();
		teleField.setEditable(false);
		teleField.setColumns(10);
		
		JLabel lblStudentQqNumber = new JLabel("Student QQ number:");
		lblStudentQqNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		qqField = new JTextField();
		qqField.setEditable(false);
		qqField.setColumns(10);
		
		JLabel lblStudentEmailAddress = new JLabel("Student Email Address:");
		lblStudentEmailAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		eField = new JTextField();
		eField.setEditable(false);
		eField.setColumns(10);
		
		JLabel lblStudentGender = new JLabel("Student Gender:");
		lblStudentGender.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(Color.BLACK);
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setEnabled(false);
		rdbtnMale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setForeground(Color.BLACK);
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setEnabled(false);
		rdbtnFemale.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JTextPane txtpnPleaseConntactSystem = new JTextPane();
		txtpnPleaseConntactSystem.setBackground(SystemColor.info);
		txtpnPleaseConntactSystem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtpnPleaseConntactSystem.setForeground(Color.RED);
		txtpnPleaseConntactSystem.setEditable(false);
		txtpnPleaseConntactSystem.setText("Notifications for Student:\r\n1.    Please Conntact System Administrator immediately after notcinig any MISTAKE of your personal infomation.\r\n2.    Please do not hesitate to consult with System Administrator if you have any question related to use this System.\r\n3.    If you detact any bug or uncomfortable design of this system, please conntact System Administrator immediately.\r\n\r\nThank you for using SRS.");
		
		btnGen = new JButton("Form General Report");
		btnGen.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnGen.addActionListener(this);
		
		JLabel lblStudentStudyingStatus = new JLabel("Student Study Status:");
		lblStudentStudyingStatus.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(lblNewLabel_3))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_1)
									.addComponent(lblNewLabel_2)
									.addComponent(lblS)
									.addComponent(lblStudentAssignedDormitory, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addComponent(lblStudentBirthday, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addComponent(lblStudentTelephoneNumber, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addComponent(lblStudentQqNumber, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addComponent(lblStudentGender, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addComponent(lblStudentEmailAddress, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
								.addComponent(lblStudentStudyingStatus, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(faField, Alignment.TRAILING, 178, 178, Short.MAX_VALUE)
								.addComponent(creField, Alignment.TRAILING, 178, 178, Short.MAX_VALUE)
								.addComponent(dormField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(birthField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(teleField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(qqField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(eField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(rdbtnMale)
									.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
									.addComponent(rdbtnFemale, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addComponent(idField)
								.addComponent(nameField, 178, 178, Short.MAX_VALUE)
								.addComponent(btnGen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnPleaseConntactSystem, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
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
						.addComponent(lblNewLabel_2)
						.addComponent(faField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblS)
						.addComponent(creField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentGender, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGen)
						.addComponent(lblStudentStudyingStatus, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(txtpnPleaseConntactSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		
		rdbtnMale.addActionListener(this);
		rdbtnFemale.addActionListener(this);
		btnLogout.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnViewNewCourses.addActionListener(this);
		btnAddThisCourse.addActionListener(this);
		btnDropThisCourse.addActionListener(this);
		rdbtnMale.setSelected(false);
		rdbtnFemale.setSelected(false);
		btnViewAllCourses.addActionListener(this);
		
		String actA = stu.getActA();
		String actD = stu.getActD();
		boolean acta = true;
		boolean actd = true;
		String txt = "";
		
		if(actA.equals("DA")){
			btnAddThisCourse.setEnabled(false);
			acta = false;
		}
		if(actD.equals("DD")){
			btnDropThisCourse.setEnabled(false);
			button.setEnabled(true);
			actd = false;
		}
		else{
			button.setEnabled(false);
		}
		
		if(acta){
			txt = "Notifictation1: You are able to add new courses now.";
		}
		else{
			txt = "Notifictation1: You are unable to add new courses now.";
		}
		if(actd){
			txt = txt + "\nNotifictation2: You are able to drop courses now.";
		}
		else{
			txt = txt + "\nNotifictation2: You are unable to drop courses now.";
		}
		txt = txt + "\nNotification3: New Semester will show up When it is available.";
		txtpnNotifictaions.setText(txt);
		showInfo();
		showSemester();
	}
	public void showInfo(){
		idField.setText(stu.getId());
		nameField.setText(stu.getName());
		faField.setText(stu.getFa().getName());
		creField.setText(stu.getCredits());
		dormField.setText(stu.getDorm());
		birthField.setText(stu.getBirth());
		teleField.setText(stu.getTele());
		qqField.setText(stu.getQq());
		eField.setText(stu.getEmail());
		
		if(stu.getGender().equals("Male")){
			rdbtnMale.setSelected(true);
		}
		else{
			rdbtnFemale.setSelected(true);
		}
		showRegisteredCourse();
	}
	public void showRegisteredCourse(){
		String studentID = stu.getId();
		String studentFile = studentID + ".dat";
		Course cou = new Course();
		try {
			Scanner scS = new Scanner(new File(studentFile));
			String[] courses = new String[100];		
			String[] split = new String[10];
			int i = 1;
			scS.nextLine();
			courses[0] = "Format: course's CRN + course's Name + Teacher's Name";
			while(scS.hasNextLine()){
				split = scS.nextLine().split("\t");
				cou = SRSFunctions.makeACourse(split[0]);
				Transcript tran = SRSFunctions.makeATranscript(stu.getId(), cou.getCrn());
				courses[i] = cou.getCrn() + "     " + cou.getName() + "       " + cou.getPro().getName();
				if(tran.getGrade().equals("W")){
					courses[i] = cou.getCrn();
					courses[i] = courses[i] + "     INFO:You have dropped this course!";
				}
				i++;
			}
			courses[i] = "Total Registered courses number " + (i - 1) + ".";
			couList2.setListData(courses);
			scS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void showCourseInfo(){
		String crn = couList2.getSelectedValue().substring(0,9);
		Course cou = SRSFunctions.makeACourse(crn);
		String studentID = stu.getId();
		Transcript tran = SRSFunctions.makeATranscript(studentID, cou.getCrn());
		
		crnField.setText(crn);
		courseNameField.setText(cou.getName());
		capaField.setText(cou.getCapa());
		String txt;
		if(tran.getComplete().equals("D")){
			if (tran.getGrade().equals("W")){
				txt = "You have dropped this Course!" + "\nThis Course is INcomplete!";
			}
			else {
				txt = "Your Grade is: " + tran.getGrade() + "\nThis Course is INcomplete!";
			}
		}
		else if(tran.getComplete().equals("C")){
			txt = "Your Grade is: " + tran.getGrade() + "\nThis Course is Complete!";
		}
		else{
			txt = "Your have not registered in this course.";
		}
		textPane.setText(txt);
		dayField.setText(cou.getDay());
		couLevField.setText(cou.getCouLeve());
		creField_2.setText(cou.getCredits());
		teaField.setText(cou.getPro().getName());
		startField.setText(cou.getStartS() + " --- " + cou.getStartE());
		timeField.setText(cou.getStartTS() + " --- " + cou.getStartTE());
		if(cou.getRequirements().equals("Elective")){
			eButton.setSelected(true);
		}
		else{
			cButton.setSelected(true);
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
				Course newCou = SRSFunctions.makeACourse(split[0]);
				courseInfo[i] = newCou.getCrn() + "     " + newCou.getName() + "     " + newCou.getPro().getName();
				i++;
			}
			courseInfo[i] = "Total courses number " + (i - 2) + ".";
			couList2.setListData(courseInfo);
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 
	}
	public void addCourse(){
		String crn = couList2.getSelectedValue().substring(0, 9);
		List<Student> stuList2 = SRSFunctions.makeStudentsList(crn);
		int index = 0;
		boolean act = false;
		while(stuList2.listIterator(index).hasNext()){
			if(stuList2.get(index).getId().equals(stu.getId())){
				SRSStudentRegistered frame = new SRSStudentRegistered();
				frame.setVisible(true);
				act = true;
				break;
			}
			index++;
		}
		if(!act){
			SRSFunctions.addAStudentToACourse(stu, crn);
			SRSFunctions.addACourseToAStudent(stu, crn);
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
		}
	}
	public void removeCourse(){
		String crn = couList2.getSelectedValue().substring(0, 9);
		List<Student> stuList2 = SRSFunctions.makeStudentsList(crn);
		int index = 0;
		boolean act = false;
		while(stuList2.listIterator(index).hasNext()){
			if(stuList2.get(index).getId().equals(stu.getId())){
				SRSFunctions.removeACourseFromAStudent(stu, crn);
				SRSFunctions.removeAStudentFromACourse(stu, crn);
				SRSSaveSucceed frame = new SRSSaveSucceed();
				frame.setVisible(true);
				showRegisteredCourse();
				act = true;
				break;
			}
			index++;
		}
		if(!act){
			SRSStudentNotRegistered frame = new SRSStudentNotRegistered();
			frame.setVisible(true);
		}
	}
	public void changeGradeToW(){
		String crn = couList2.getSelectedValue().substring(0, 9);
		List<Student> stuList2 = SRSFunctions.makeStudentsList(crn);
		int index = 0;
		boolean act = false;
		while(stuList2.listIterator(index).hasNext()){
			if(stuList2.get(index).getId().equals(stu.getId())){
				Transcript tran = SRSFunctions.makeATranscript(stu.getId(), crn);
				tran.setGrade("W");
				tran.setComplete("D");
				SRSFunctions.saveATranscriptToAStudent(stu, tran);
				SRSSaveSucceed frame = new SRSSaveSucceed();
				frame.setVisible(true);
				showRegisteredCourse();
				act = true;
				break;
			}
			index++;
		}
		if(!act){
			SRSStudentNotRegistered frame = new SRSStudentNotRegistered();
			frame.setVisible(true);
		}
	}
	public void createGradeReport(){
		String selSem = semeList.getSelectedValue().substring(0, 6);
		String id = stu.getId();
		SRSCreateReports.formStudentGradeReport(id, selSem);
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createCourseSc(){
		String selSem = semeList.getSelectedValue().substring(0, 6);
		String id = stu.getId();
		SRSCreateReports.formPersonCourseSchedule(id, selSem);
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	public void createReport(){
		SRSCreateReports.formStudentGeneralReport(stu.getId());
		SRSSaveSucceed frame = new SRSSaveSucceed();
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if(source == rdbtnMale){
			rdbtnFemale.setSelected(false);
		}
		else if(source == btnGen){
			createReport();
		}
		else if(source == rdbtnFemale){
			rdbtnMale.setSelected(false);
		}
		else if(source == cButton){
			eButton.setSelected(false);
		}
		else if(source == eButton){
			cButton.setSelected(false);
		}
		else if(source == btnLogout){
			this.dispose();
			SRSChoicer frame = new SRSChoicer();
			frame.setVisible(true);
		}
		else if(source == btnViewAllCourses){
			showRegisteredCourse();
		}
		else if(source == btnNewButton){
			showCourseInfo();
		}
		else if(source == btnViewNewCourses){
			showAll();
		}
		else if(source == btnAddThisCourse){
			addCourse();
		}
		else if(source == btnDropThisCourse){
			removeCourse();
		}
		else if(source == button){
			changeGradeToW();
		}
		else if(source == btnFormGradeReport){
			createGradeReport();
		}
		else if(source == btnGetThisSemester){
			createCourseSc();
		}
	}
}