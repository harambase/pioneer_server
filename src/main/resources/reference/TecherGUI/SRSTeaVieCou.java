package TecherGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import AdminGUIBars.SRSSaveSucceed;
import NewCore.Course;
import NewCore.Professor;
import NewCore.Student;
import NewCore.Transcript;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;

import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextPane;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SRSTeaVieCou extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JTextField teaIDField;
	private JTextField couTSField;
	private JTextField couTEField;
	private JTextField couDayField;
	private JTextField couStaField;
	private JTextField couEndField;
	private JTextField crnField;
	private JTextField couLevField;
	private JTextField couNameField;
	private JTextField creField;
	private JTextField capaField;
	private JTextField stuIDField;
	private JButton btnRefresh;
	private JList<String> stuList2;
	
	private JScrollPane stuList;
	private JRadioButton eButton;
	private JRadioButton cButton;
	private JButton btnReturnToPervious;
	private JButton btnAddThisNew;

	private String studentsFile = "Students.dat";
	private JLabel lblTeachersName;
	private JTextField teaNameField;
	private JButton btnCheckThisStudent;
	private JLabel lblStudentsName;
	private JTextField stuNameField;
	private JLabel lblEchangeStudentsGrade;
	private JLabel lblNewGrade;
	private JTextField gradeField;
	private JButton btnChangeTheGrade;
	private JLabel label_2;
	private JButton btnDeactivateThisCourse;
	private Course cou = SRSTeacherGUI.course;
	private JTextPane txtpnNotificationsToTeachers;
	private JButton btnCreCouReport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSTeaVieCou frame = new SRSTeaVieCou();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public SRSTeaVieCou() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 955, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JLabel lblViewingCourseForm = new JLabel("Viewing Course Mode");
		lblViewingCourseForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("Welcome Back to SRS.");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblTheTimeOf = new JLabel("The time of this Course: (Starts - Ends)");
		lblTheTimeOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_4 = new JLabel("PartIII: Course Instruction  Information Form:");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblTheInstructorOf = new JLabel("The Instructor of this Course (Please specify the ID number instead of the Name) *");
		lblTheInstructorOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		teaIDField = new JTextField();
		teaIDField.setEditable(false);
		teaIDField.setText("");
		teaIDField.setColumns(10);
		
		JLabel lblTheDateOf = new JLabel("The date of this Course: (Start-End)");
		lblTheDateOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblTheDaysOf = new JLabel("The days of this Course: ");
		lblTheDaysOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		couTSField = new JTextField();
		couTSField.setEditable(false);
		couTSField.setText("");
		couTSField.setColumns(10);
		
		couTEField = new JTextField();
		couTEField.setEditable(false);
		couTEField.setText("");
		couTEField.setColumns(10);
		
		couDayField = new JTextField();
		couDayField.setEditable(false);
		couDayField.setText("");
		couDayField.setColumns(10);
		
		couStaField = new JTextField();
		couStaField.setEditable(false);
		couStaField.setText("");
		couStaField.setColumns(10);
		
		couEndField = new JTextField();
		couEndField.setEditable(false);
		couEndField.setText("");
		couEndField.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name:*");
		lblCourseName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_9 = new JLabel("Course Level Number (111 - 399):");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_10 = new JLabel("The CRN of this Course:");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		crnField = new JTextField();
		crnField.setEditable(false);
		crnField.setText("");
		crnField.setColumns(10);
		
		couLevField = new JTextField();
		couLevField.setEditable(false);
		couLevField.setText("");
		couLevField.setColumns(10);
		
		couNameField = new JTextField();
		couNameField.setEditable(false);
		couNameField.setText("");
		couNameField.setColumns(10);
		
		JLabel lblCreditesForThis = new JLabel("Credites for This Course*");
		lblCreditesForThis.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		creField = new JTextField();
		creField.setEditable(false);
		creField.setText("");
		creField.setColumns(10);
		
		JLabel label_12 = new JLabel("Requirement:");
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		eButton = new JRadioButton("Elective");
		eButton.setForeground(Color.BLACK);
		eButton.setEnabled(false);
		eButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		cButton = new JRadioButton("Compulsory");
		cButton.setForeground(Color.BLACK);
		cButton.setEnabled(false);
		cButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_13 = new JLabel("PartI: Course Basic Information Form:");
		label_13.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_14 = new JLabel("The Maximum Capacity of this Course:");
		label_14.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		capaField = new JTextField();
		capaField.setEditable(false);
		capaField.setText("");
		capaField.setColumns(10);
		
		JLabel label_15 = new JLabel("PartII: Course Schedule  Information Form:");
		label_15.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblCinfomationOfThis = new JLabel("A.Infomation of this selected course Form");
		lblCinfomationOfThis.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		lblTeachersName = new JLabel("Teacher's Name:");
		lblTeachersName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		teaNameField = new JTextField();
		teaNameField.setEditable(false);
		teaNameField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCinfomationOfThis, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(teaIDField, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(capaField, 225, 225, 225)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblTeachersName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(teaNameField, 0, 0, Short.MAX_VALUE)))))
							.addGap(17))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(label_13))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(lblTheInstructorOf, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCourseName)
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_10)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblTheDaysOf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblTheDateOf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(lblTheTimeOf, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(couTSField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(couTEField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(couStaField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(couEndField, 0, 0, Short.MAX_VALUE))
											.addComponent(couDayField, Alignment.LEADING, 226, 226, 226))
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(crnField, Alignment.LEADING)
											.addComponent(couNameField, Alignment.LEADING)
											.addComponent(couLevField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
											.addComponent(cButton))))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCreditesForThis, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(creField, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(1)
									.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(eButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
									.addGap(117))
								.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE))))
					.addGap(8))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(lblCinfomationOfThis, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_10)
						.addComponent(crnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCourseName)
						.addComponent(couNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(couLevField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreditesForThis, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(creField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(eButton)
						.addComponent(cButton))
					.addGap(8)
					.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(couStaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTheDateOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(couEndField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(couDayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTheDaysOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(couTSField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTheTimeOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(couTEField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTheInstructorOf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(teaIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTeachersName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(teaNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(capaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(45))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblRegisteredStudents = new JLabel("B.Students Registered in this Course");
		lblRegisteredStudents.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		stuList2 = new JList<String>();
		stuList = new JScrollPane(stuList2);
		stuList.setToolTipText("");
		stuList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblStudentId = new JLabel("Student ID:*");
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblEaddANew = new JLabel("E.Override a new Student");
		lblEaddANew.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		stuIDField = new JTextField();
		stuIDField.setText("");
		stuIDField.setColumns(10);
		
		btnRefresh = new JButton("Refresh ");
		btnRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnReturnToPervious = new JButton("Close");
		btnReturnToPervious.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnAddThisNew = new JButton("Add this new Student");
		btnAddThisNew.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnCheckThisStudent = new JButton("Check this Student ID");
		btnCheckThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnCheckThisStudent.addActionListener(this);
		
		lblStudentsName = new JLabel("Student's Name:");
		lblStudentsName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		stuNameField = new JTextField();
		stuNameField.setEditable(false);
		stuNameField.setColumns(10);
		
		lblEchangeStudentsGrade = new JLabel("C.Change Student's Grade");
		lblEchangeStudentsGrade.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		lblNewGrade = new JLabel("New Grade:(A/B/C/D/F)");
		lblNewGrade.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		gradeField = new JTextField();
		gradeField.setText("");
		gradeField.setColumns(10);
		
		btnChangeTheGrade = new JButton("Change the grade");
		btnChangeTheGrade.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnChangeTheGrade.addActionListener(this);
		
		label_2 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		btnDeactivateThisCourse = new JButton("Deactivate this Course");
		btnDeactivateThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDeactivateThisCourse.addActionListener(this);
		
		txtpnNotificationsToTeachers = new JTextPane();
		txtpnNotificationsToTeachers.setText("Notifications to user:\r\n1.   If you want to make any modification to the course information, please contact System Manager.\r\n2.   "
				+ "The grade of a student can be either Letter Grade System or Score Grade. If you are using Letter Grade, \"F\" grade will lead student to fail this course, which also means he/she cannot receive any credit on the course. "
				+ "If you are using Score Grade System, please contact System Manager to determine the \"F\" grade, so we can make modifications.\r\n3.   Override a student should be limited. The regular maximum capacity for a course is 50.");
		txtpnNotificationsToTeachers.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtpnNotificationsToTeachers.setForeground(Color.RED);
		txtpnNotificationsToTeachers.setBackground(SystemColor.info);
		txtpnNotificationsToTeachers.setEditable(false);
		
		btnCreCouReport = new JButton("Create This Course Report");
		btnCreCouReport.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnNotificationsToTeachers, GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(230)
											.addComponent(btnDeactivateThisCourse, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblViewingCourseForm, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
											.addGap(7)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnReturnToPervious, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
												.addComponent(btnRefresh, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(panel, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btnCreCouReport, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
												.addComponent(stuList, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
												.addComponent(btnAddThisNew, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
												.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblEaddANew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblNewGrade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblEchangeStudentsGrade, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnCheckThisStudent, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
															.addComponent(gradeField)
															.addComponent(btnChangeTheGrade, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
												.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblStudentsName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(stuIDField, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
														.addComponent(stuNameField, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
												.addComponent(lblRegisteredStudents, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))))))
							.addGap(280)))
					.addGap(119))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(btnRefresh))
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblViewingCourseForm)
								.addComponent(btnReturnToPervious)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(btnCreCouReport)
							.addGap(6)
							.addComponent(lblRegisteredStudents, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(stuList, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEchangeStudentsGrade, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnChangeTheGrade))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewGrade, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(gradeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEaddANew, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCheckThisStudent, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(stuIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStudentsName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(stuNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddThisNew))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtpnNotificationsToTeachers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(87)
					.addComponent(btnDeactivateThisCourse, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		btnRefresh.addActionListener(this);
		eButton.addActionListener(this);
		cButton.addActionListener(this);
		btnReturnToPervious.addActionListener(this);
		btnAddThisNew.addActionListener(this);
		
		teaIDField.setText("");
		couTSField.setText("");
		couTEField.setText("");
		couDayField.setText("");
		couStaField.setText("");
		couEndField.setText("");
		crnField.setText("");
		couLevField.setText("");
		couNameField.setText("");
		creField.setText("");
		capaField.setText("");
		stuIDField.setText("");
		viewCourse();
	}
	
	public void viewCourse(){
		String crn = cou.getCrn();
		String inCouFile = "C" + crn + ".dat";
		int i = 1;
		try {
			Scanner sc = new Scanner(new File(inCouFile));
			String[] courseInfo = new String[13];
			String courseName, courseLevel, credits, require = "Elective";
			String courseS, courseE, courseD, courseTS, courseTE;
			String capa, teacherID,line;
			
			courseInfo = sc.nextLine().split("\t");
			courseName = courseInfo[1];
			credits = courseInfo[2];
			require = courseInfo[3];
			teacherID = courseInfo[4];
			courseLevel = courseInfo[5];
			courseS = courseInfo[6];
			courseE = courseInfo[7];
			courseD = courseInfo[8];
			courseTS = courseInfo[9];
			courseTE = courseInfo[10];
		    capa = courseInfo[11];
		    String[] studentInfo = new String[50];
		    
		    crnField.setText(crn);
		    teaIDField.setText(teacherID);
		   	couTSField.setText(courseTS);
			couTEField.setText(courseTE);
			couDayField.setText(courseD);
			couStaField.setText(courseS);
			couEndField.setText(courseE);
			couLevField.setText(courseLevel);
			couNameField.setText(courseName);
			creField.setText(credits);
			capaField.setText(capa);
			chekForTeacher();
			if (require.equals("Elective")){
				eButton.setSelected(true);
				cButton.setSelected(false);
			}
			else{
				eButton.setSelected(false);
				cButton.setSelected(true);
			}
			String[] split = new String[100];
			studentInfo[0] = "Format: Student ID + Name + Grade + Completion(C/D)";
			while(sc.hasNextLine()){
				line = sc.nextLine();
				split = line.split("\t");
				Student stu = SRSFunctions.makeAStudent(split[0]);
				Transcript tran = SRSFunctions.makeATranscript(stu.getId(), crn);
				studentInfo[i] = stu.getId() + "     " + stu.getName() + "     " +  tran.getGrade() + "    " 
				+ tran.getComplete() ; 
				i++;
			}
			stuList2.setListData(studentInfo);
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public void reset(){
		teaIDField.setText("");
		couTSField.setText("");
		couTEField.setText("");
		couDayField.setText("");
		couStaField.setText("");
		couEndField.setText("");
		crnField.setText("");
		couLevField.setText("");
		couNameField.setText("");
		creField.setText("");
		capaField.setText("");
		stuIDField.setText("");
		stuNameField.setText("");
		eButton.setSelected(false);
		cButton.setSelected(false);
		gradeField.setText("");
	}
	public void addAStudent(){
		String studentID = stuIDField.getText();
		if (SRSFunctions.checkForPerson(studentID) && SRSFunctions.checkForActive(studentID)){ 
		 String crn = crnField.getText();
		 String inSFile = studentID + ".dat";
		 String inCFile = "C" + crn + ".dat";
		 String line;
		 String[] split = new String[20];
		 boolean add = true;
		 
		 try {
			Scanner sc = new Scanner(new File(studentsFile));
			int i = 0, j = 0;
			int x = 0, y = 0;
		
			if (!crn.isEmpty()){
				Scanner scS = new Scanner(new File(inSFile));
				Scanner scC = new Scanner(new File(inCFile));
				String[] infoS = new String[100];
				String[] infoC = new String[100];
				
				while(scS.hasNextLine()){
					line = scS.nextLine();
					split = line.split("\t");
					if (split[0].equals(crn)){
						SRSInfoPassed frame = new SRSInfoPassed();
						frame.setVisible(true);	
						add = false;
					}
					infoS[i] = line;
					i++;
				}
				while(scC.hasNextLine()){
					line = scC.nextLine();
					split = line.split("\t");
					if (split[0].equals(studentID)){
						add = false;
					}
					infoC[j] = line;
					j++;
				}
				scS.close();
				scC.close();
				//D:incomplete this course
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
				if (add == true){
					pwS.println(infoForS);
					pwC.println(infoForC);
				}
				pwS.close();
				pwC.close();
			}
			sc.close();
			viewCourse();
			
		} catch (FileNotFoundException e1) {
		}
		 	stuIDField.setText("");
			stuNameField.setText("");
			SRSSaveSucceed frame = new SRSSaveSucceed();
			frame.setVisible(true);
		}
	}
	public void deleteThisStudent(){
		 String studentID = stuList2.getSelectedValue().substring(0, 9);
		 String crn = crnField.getText();
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
			viewCourse();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public void changeTheGrade(){
		String studentID =  stuList2.getSelectedValue().substring(0, 9);
		 String crn = cou.getCrn();
		 Student stu = SRSFunctions.makeAStudent(studentID);
		 Course cou = SRSFunctions.makeACourse(crn);
		 String inSFile = studentID + ".dat";
		 String inCFile = "C" + crn + ".dat";
		 String newGrade = gradeField.getText();
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
				String infoSS, infoCS;
				if (newGrade.equals("F")){
					infoSS = crn +"\t" + newGrade +"\t" + "D" + "\t";
				}
				else{
					infoSS = crn +"\t" + newGrade +"\t" + "C" + "\t";
					Double credit = Double.parseDouble(cou.getCredits());
					Double earned = Double.parseDouble(stu.getCredits());
					earned = earned + credit;
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
							infoCS = studentID  +"\t" + newGrade +"\t" + "D" + "\t";
						}
						else{
							infoCS = studentID  +"\t" + newGrade +"\t" + "C" + "\t";
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
			viewCourse();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 SRSSaveSucceed frame =  new SRSSaveSucceed();
		 frame.setVisible(true);
		 
	}

	public boolean chekForStudent(){
		//return true if he/she is registered.
		String newStudentID;
		boolean act = false;
		newStudentID = stuIDField.getText();
		if (newStudentID.length() < 9 || newStudentID.length() > 9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
			act = false;
		}	
		else{
			File fl = new File(newStudentID + ".dat");
			if (!fl.exists()){
				SRSInfoNotPassed frame = new SRSInfoNotPassed();
				frame.setVisible(true);
				act = false;
			}
			else{
				act = true;
				Student stu = SRSFunctions.makeAStudent(newStudentID);
				stuNameField.setText(stu.getName());
			}
		}
		return act;
	}
	public boolean chekForTeacher(){
		String newStudentID;
		boolean act = false;
		newStudentID = teaIDField.getText();
		if (newStudentID.equals("") || newStudentID.length() < 9 || newStudentID.length() > 9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}	
		else{
			File fl = new File(newStudentID + ".dat");
			if (!fl.exists()){
				SRSInfoNotPassed frame = new SRSInfoNotPassed();
				frame.setVisible(true);
				act = false;
			}
			else{
				act = true;
				Professor pro = SRSFunctions.makeAProfessor(newStudentID);
				teaNameField.setText(pro.getName());
			}
		}
		return act;
	}

	public void actionPerformed(ActionEvent e) {
		 Object source = e.getSource();
		 if (source == btnReturnToPervious){
			 this.dispose();
		 }
		 else if (source == btnCreCouReport){
				SRSCreateReports.formCourseReport(cou.getCrn());
				SRSSaveSucceed frame = new SRSSaveSucceed();
				frame.setVisible(true);
		 }
		 else if (source == eButton){
			 cButton.setSelected(false);
		 }
		 else if (source == cButton){
			 eButton.setSelected(false);
		 }
		 else if (source == btnAddThisNew){
			 addAStudent();
		 }
		 else if (source == btnCheckThisStudent){
			 if (chekForStudent()){
				 SRSInfoPassed frame = new SRSInfoPassed();
				 frame.setVisible(true);
			 }
		 }
		 else if (source == btnChangeTheGrade){
			 changeTheGrade();
		 }
		 else if (source == btnRefresh){
			 this.dispose();
			 SRSTeaVieCou frame = null;
			try {
				frame = new SRSTeaVieCou();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			 frame.setVisible(true);
		 }
	}

}
