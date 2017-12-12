package AdminGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
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

import AdminGUIBars.SRSDeleteSucceed;
import AdminGUIBars.SRSInfoNotPassed;
import AdminGUIBars.SRSInfoPassed;
import AdminGUIBars.SRSRegisterError;
import AdminGUIBars.SRSSaveSucceed;
import NewCore.Course;
import NewCore.Professor;
import NewCore.Student;
import NewCore.Transcript;
import SRSDataBase.SRSCreateReports;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSAdInterface;

import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextPane;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SRSVieCou extends JFrame implements ActionListener, MouseListener {

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
	private JButton btnDeleteThisStudent;
	private JButton btnDeletThisCourse;
	private JButton btnShowAll;
	private JButton btnRefresh;
	private JList<String> acdList2;
	private JList<String> couList2;
	private JList<String> stuList2;
	
	private JScrollPane stuList;
	private JScrollPane acdList;
	private JScrollPane couList;
	private JRadioButton eButton;
	private JRadioButton cButton;
	private JButton btnReturnToPervious;
	private JButton btnViewThisCourse;
	private JButton btnAddThisNew;

	private String oldTeachID = "";
	private String studentsFile = "Students.dat";
	private String coursesFile  = "AllCourseCatalog.dat";
	private JLabel lblTeachersName;
	private JTextField teaNameField;
	private JButton btnResetFormAbove;
	private JButton btnCheckThisStudent;
	private JLabel lblStudentsName;
	private JTextField stuNameField;
	private JLabel label_2;
	private JButton btnDeactivateThisCourse;
	private JButton btnCheckTeachersId;
	private JButton btnEditThisCourse;
	private JLabel lblPartivSaveThe;
	private JLabel lblGDreactivateCourses;
	private JLabel label_5;
	private JTextField crnField_2;
	private JLabel lblTheCrnOf;
	private JTextField textField;
	private JButton btnReactivateThisCourse;
	private JLabel lblFFormCourse;
	private JButton btnNewButton;
	private String crn_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSVieCou frame = new SRSVieCou();
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
	public SRSVieCou() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1302, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JLabel lblViewingCourseForm = new JLabel("Viewing Course Mode");
		lblViewingCourseForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("Welcome Back to SRS.");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		acdList2 = new JList<String>();
		acdList = new JScrollPane(acdList2);
		acdList.setToolTipText("");
		acdList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		couList2 = new JList<String>();
		couList = new JScrollPane(couList2);
		couList.setToolTipText("");
		couList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel label_3 = new JLabel("The time of this Course: (Starts[13:00] - Ends)");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_4 = new JLabel("PartIII: Course Instruction  Information Form:");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblTheInstructorOf = new JLabel("The Instructor of this Course (Please specify the ID number instead of the Name) *");
		lblTheInstructorOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		teaIDField = new JTextField();
		teaIDField.setText("");
		teaIDField.setColumns(10);
		
		JLabel label_6 = new JLabel("The date of this Course: (YYYY/MM - YYYY/MM)");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_7 = new JLabel("The days of this Course: (MWF/TR/WEEKDAY/...)");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		couTSField = new JTextField();
		couTSField.setText("");
		couTSField.setColumns(10);
		
		couTEField = new JTextField();
		couTEField.setText("");
		couTEField.setColumns(10);
		
		couDayField = new JTextField();
		couDayField.setText("");
		couDayField.setColumns(10);
		
		couStaField = new JTextField();
		couStaField.setText("");
		couStaField.setColumns(10);
		
		couEndField = new JTextField();
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
		couLevField.setText("");
		couLevField.setColumns(10);
		
		couNameField = new JTextField();
		couNameField.setText("");
		couNameField.setColumns(10);
		
		JLabel lblCreditesForThis = new JLabel("Credites for This Course*");
		lblCreditesForThis.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		creField = new JTextField();
		creField.setText("");
		creField.setColumns(10);
		
		JLabel label_12 = new JLabel("Requirement:");
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		eButton = new JRadioButton("Elective");
		eButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		cButton = new JRadioButton("Compulsory");
		cButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_13 = new JLabel("PartI: Course Basic Information Form:");
		label_13.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel label_14 = new JLabel("The Maximum Capacity of this Course:");
		label_14.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		capaField = new JTextField();
		capaField.setText("");
		capaField.setColumns(10);
		
		JLabel label_15 = new JLabel("PartII: Course Schedule  Information Form:");
		label_15.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblCinfomationOfThis = new JLabel("C.Infomation of this selected course Form");
		lblCinfomationOfThis.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		lblTeachersName = new JLabel("Teacher's Name:");
		lblTeachersName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		teaNameField = new JTextField();
		teaNameField.setEditable(false);
		teaNameField.setColumns(10);
		
		btnResetFormAbove = new JButton("Reset form Below");
		btnResetFormAbove.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnResetFormAbove.addActionListener(this);
		
		btnCheckTeachersId = new JButton("Check Teacher's ID");
		btnCheckTeachersId.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnCheckTeachersId.addActionListener(this);
		
		btnEditThisCourse = new JButton("Save this edition");
		btnEditThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		lblPartivSaveThe = new JLabel("PartIV: Save the modification of Infomation:");
		lblPartivSaveThe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTheInstructorOf, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(125, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(122, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCinfomationOfThis, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCreditesForThis, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(creField, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
									.addGap(22)
									.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(eButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cButton))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(teaIDField, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
									.addGap(58)
									.addComponent(lblTeachersName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(teaNameField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCourseName)
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_10))
									.addGap(36)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(couNameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
										.addComponent(couLevField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
										.addComponent(crnField, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_13)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnResetFormAbove, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCheckTeachersId, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_6)
										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(couTSField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(couTEField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(couDayField)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(couStaField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(couEndField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPartivSaveThe, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEditThisCourse, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(capaField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
							.addGap(35))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(lblCinfomationOfThis, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnResetFormAbove))
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
						.addComponent(creField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cButton)
						.addComponent(eButton)
						.addComponent(lblCreditesForThis, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(couEndField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(couStaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(couDayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(couTEField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(couTSField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCheckTeachersId))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTheInstructorOf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(teaIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(teaNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTeachersName, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(capaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPartivSaveThe, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditThisCourse))
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblSelectAcademicYear = new JLabel("A.Select A Semester (Year + 01:Spring, 02:Summer, 03:Fall)");
		lblSelectAcademicYear.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblSelectACourse = new JLabel("B.Select A course to view");
		lblSelectACourse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel lblRegisteredStudents = new JLabel("D.Students Registered in this Course");
		lblRegisteredStudents.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		stuList2 = new JList<String>();
		stuList = new JScrollPane(stuList2);
		stuList.setToolTipText("");
		stuList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnDeletThisCourse = new JButton("Delete this course");
		btnDeletThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnShowAll = new JButton("Show all the courses from this semester");
		btnShowAll.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnDeleteThisStudent = new JButton("Drop this student From Current Course");
		btnDeleteThisStudent.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblStudentId = new JLabel("Student ID:*");
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblEaddANew = new JLabel("E.Add a new Student");
		lblEaddANew.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		stuIDField = new JTextField();
		stuIDField.setText("");
		stuIDField.setColumns(10);
		
		btnRefresh = new JButton("Refresh ");
		btnRefresh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnReturnToPervious = new JButton("Return to Pervious Menu");
		btnReturnToPervious.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		btnViewThisCourse = new JButton("View this course infomation");
		btnViewThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
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
		
		label_2 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		btnDeactivateThisCourse = new JButton("Deactivate this Course");
		btnDeactivateThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDeactivateThisCourse.addActionListener(this);
		
		lblGDreactivateCourses = new JLabel("G. D(R)eactivate A Course");
		lblGDreactivateCourses.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		label_5 = new JLabel("The CRN of this Course:");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		crnField_2 = new JTextField();
		crnField_2.setText("");
		crnField_2.setEditable(false);
		crnField_2.setColumns(10);
		
		lblTheCrnOf = new JLabel("The CRN of A Course:");
		lblTheCrnOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		
		btnReactivateThisCourse = new JButton("Reactivate this Course");
		btnReactivateThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JTextPane txtpnNotificationYouCan = new JTextPane();
		txtpnNotificationYouCan.setEditable(false);
		txtpnNotificationYouCan.setText("Notification: You can modify the Grade of this Student in View Student Mode.");
		txtpnNotificationYouCan.setForeground(Color.RED);
		txtpnNotificationYouCan.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtpnNotificationYouCan.setBackground(SystemColor.info);
		
		lblFFormCourse = new JLabel("F. Form Course Report");
		lblFFormCourse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btnNewButton = new JButton("Form Reprot");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSelectAcademicYear, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)
									.addGap(406))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(couList, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
												.addComponent(acdList, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
												.addComponent(btnShowAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblSelectACourse)
												.addComponent(btnDeletThisCourse, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
												.addComponent(btnViewThisCourse, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 637, Short.MAX_VALUE)
												.addComponent(lblGDreactivateCourses, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTheCrnOf, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(crnField_2, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(btnDeactivateThisCourse, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(textField, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(btnReactivateThisCourse, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblViewingCourseForm, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnDeleteThisStudent, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
													.addGap(30))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblStudentsName, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(stuIDField, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
												.addComponent(stuNameField, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblFFormCourse)
													.addGap(18)
													.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
												.addComponent(btnAddThisNew, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addComponent(btnReturnToPervious, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
								.addComponent(lblRegisteredStudents, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
								.addComponent(stuList, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtpnNotificationYouCan, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEaddANew, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnCheckThisStudent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
					.addGap(119))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblViewingCourseForm)
								.addComponent(btnReturnToPervious)
								.addComponent(btnRefresh))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelectAcademicYear, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRegisteredStudents, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnShowAll)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(acdList, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSelectACourse, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(129)
											.addComponent(btnDeleteThisStudent)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtpnNotificationYouCan, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
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
												.addComponent(stuNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(couList, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblGDreactivateCourses, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(89)
							.addComponent(stuList, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDeactivateThisCourse, 0, 0, Short.MAX_VALUE)
							.addComponent(btnAddThisNew))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(crnField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnViewThisCourse)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTheCrnOf, GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReactivateThisCourse, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeletThisCourse)
						.addComponent(lblFFormCourse, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		btnDeleteThisStudent.addActionListener(this);
		btnDeletThisCourse.addActionListener(this);
		btnShowAll.addActionListener(this);
		btnRefresh.addActionListener(this);
		eButton.addActionListener(this);
		cButton.addActionListener(this);
		btnReturnToPervious.addActionListener(this);
		btnViewThisCourse.addActionListener(this);
		btnAddThisNew.addActionListener(this);
		couList2.addMouseListener(this);
		btnEditThisCourse.addActionListener(this);
		
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
		
		showSemester();
	}
	public void showSemester() throws FileNotFoundException{
		Scanner sc = new Scanner(new File(coursesFile));
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
		acdList2.setListData(semInfo);
		sc.close();
	}
	public void showAll(){
		 String selSem = acdList2.getSelectedValue().substring(0, 6);
		 int i = 0;
		 try {
			Scanner sc = new Scanner(new File(selSem + "CourseCatalog.dat"));
			String line;
			String[] split = new String[5];
			String[] courseInfo = new String[20];
			for (i = 0 ; i < 20 ; i++){
				courseInfo[i] = "";
			}
			i = 0;
			while (sc.hasNext()){
				line = sc.nextLine();
				split = line.split("\t");
				for(int j = 0; j < 5; j++){
					courseInfo[i] = courseInfo[i] + "     " + "\t" + split[j];
				}
				i++;
			}
			couList2.setListData(courseInfo);
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 
	 }
	public void viewCourse(){
		String crn = couList2.getSelectedValue().substring(6, 15);
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
		    
		    oldTeachID = teacherID;
		    
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
			SRSFunctions.checkForPerson(teacherID);
			Professor pro = SRSFunctions.makeAProfessor(teacherID);
			teaNameField.setText(pro.getName());
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
	public void editCourse(){
		String crn = crnField.getText();
		String newTea = teaIDField.getText();
		String newCouTS = couTSField.getText();
		String newCouTE = couTEField.getText();
		String newCouD = couDayField.getText();
		String newCouSta = couStaField.getText();
		String newCouEnd = couEndField.getText();
		String newCouLeve = couLevField.getText();
		String newCouNam = couNameField.getText();
		String newCre = creField.getText();
		String newCapa = capaField.getText();;
		String newR = "Elective";
		
		if (newCouSta.isEmpty()){
			newCouSta = "N/A";
		}
		if (newCouEnd.isEmpty()){
			newCouEnd = "N/A";
		}
		if (newCouD.isEmpty()){
			newCouD = "TBD";
		}
		if (newCouTS.isEmpty()){
			newCouTS = "N/A";
		}
		if (newCouTE.isEmpty()){
			newCouTE = "N/A";
		}
		if (newCapa.isEmpty()){
			newCapa = "50";
		}
		
		if (eButton.isSelected()){newR = "Elective";}
		else {newR = "Compulsory";}
		
		if (crn.isEmpty()){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}
		else{
			if (SRSFunctions.checkForPerson(newTea) && SRSFunctions.checkForActive(newTea)){
				String inCouFile = "C" + crn + ".dat";
				String oldTeaFile = oldTeachID + ".dat";
				if(oldTeachID == "000000000"){
					oldTeachID = newTea;
				}
				String newTeaFile = newTea + ".dat";
				String semesterFile = acdList2.getSelectedValue().substring(0,6) + "CourseCatalog.dat";
				String[] inStuFile = new String[100];
				try {
					Scanner scIC = new Scanner(new File(inCouFile));
					Scanner scAC = new Scanner(new File(coursesFile));
					Scanner scSC = new Scanner(new File(semesterFile));
					Scanner scOTC = new Scanner(new File(oldTeaFile));
					Scanner scNTC = new Scanner(new File(newTeaFile));
					
					String courseTInfo = "";
					courseTInfo = crn + "\t" + newCre + "\t";
					
					String courseBInfo = "";
					courseBInfo = crn + "\t" + newCouNam + "\t" + newCre + "\t" + newR +"\t" + newTea;
					
					String courseFInfo = "";
					courseFInfo = courseBInfo + "\t" + newCouLeve + "\t" + newCouSta + "\t" + newCouEnd +
							"\t" + newCouD + "\t" + newCouTS +"\t" + newCouTE + "\t" + newCapa + "\t" + "A" + "\t";
					String[] info = new String[200];
					String[] line = new String[13];
					
					//individual course file
					int sci = 0, scj = 0;
						int i = 0;
						while (scIC.hasNext()){
							info[sci] = scIC.nextLine();
							if (sci > 0){
								inStuFile[i] = info[sci].split("\t")[0];
								i++;
							}
							sci++;
						}
						info[0] = courseFInfo;
						PrintWriter pwIC = new PrintWriter(new File(inCouFile));
						while (scj < sci){
							pwIC.println(info[scj]);
							scj++;
						}
						pwIC.close();
						scIC.close();
					//AllCourseFile
						sci = 0; scj = 0;
						while (scAC.hasNext()){
							info[sci] = scAC.nextLine();
							line = info[sci].split("\t");
							if (line[0].equals(crn)){
								info[sci] = courseBInfo;
							}
							sci++;
						}
						PrintWriter pwAC = new PrintWriter(new File(coursesFile));
						while (scj < sci){
							pwAC.println(info[scj]);
							scj++;
						}
						pwAC.close();
						scAC.close();
					//SemesterFile
						sci = 0; scj = 0;
						while (scSC.hasNext()){
							info[sci] = scSC.nextLine();
							line = info[sci].split("\t");
							if (line[0].equals(crn)){
								info[sci] = courseBInfo;
							}
							sci++;
						}
						PrintWriter pwSC = new PrintWriter(new File(semesterFile));
						while (scj < sci){
							pwSC.println(info[scj]);
							scj++;
						}
						pwSC.close();
						scSC.close();
					//oldTeacherFile
						sci = 0; scj = 0;
						while (scOTC.hasNext()){
							String temp = scOTC.nextLine();
							line = temp.split("\t");
							if (!line[0].equals(crn)){
								info[sci] = temp;
								sci++;
							}
						}
						PrintWriter pwOTC = new PrintWriter(new File(oldTeaFile));
						while (scj < sci){
							pwOTC.println(info[scj]);
							scj++;
						}
						pwOTC.close();
						scOTC.close();
					//newTeacherFile
						sci = 0; scj = 0;
						while (scNTC.hasNext()){
							info[sci] = scNTC.nextLine();
							sci++;
						}
						PrintWriter pwNTC = new PrintWriter(new File(newTeaFile));
						while (scj < sci){
							pwNTC.println(info[scj]);
							scj++;
						}
						pwNTC.println(courseTInfo);
						pwNTC.close();
						scNTC.close();
				
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				SRSSaveSucceed frame = new SRSSaveSucceed();
				frame.setVisible(true);
				oldTeachID = newTea;
			}
			else{
				SRSInfoNotPassed frame = new SRSInfoNotPassed();
				frame.setVisible(true);
			}
			showAll();
		}
	}	
	public void deletThisCourse(){
		String crn = couList2.getSelectedValue().substring(6, 15);
		Course cou = SRSFunctions.makeACourse(crn);
		String inCouFile = "C" + crn + ".dat";
		//Step1: Remove Course from Individual Student Files
				List<Student> stuList = SRSFunctions.makeStudentsList(crn);
				int index = 0;
				while(stuList.listIterator(index).hasNext()){
					Student stu = stuList.get(index);
					SRSFunctions.removeACourseFromAStudent(stu, crn);
					index++;
				}
		//Step2: Remove from AllCourseCatalog.dat File
				SRSFunctions.removeFromAllCourseCatalog(crn);
		//Step3: Remove from Semester's File
				SRSFunctions.removeFromSemesterCourseCatalog(crn);
		//Step4: Remove from Teacher's File
				Professor pro = SRSFunctions.makeAProfessor(cou.getTeacherID());
				SRSFunctions.removeACourseFromAProfessor(pro, crn);
		//Step5: Remove Individual File
				File df = new File(inCouFile);
				df.delete();
		
		showAll();
		reset();
		SRSDeleteSucceed frame = new SRSDeleteSucceed();
		frame.setVisible(true);
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
		crnField_2.setText("");
	}
	public void addAStudent(){
	     String studentID = stuIDField.getText();
		if (SRSFunctions.checkForPerson(studentID) 
				&& SRSFunctions.checkForActive(studentID)){
		 Student stu = SRSFunctions.makeAStudent(studentID);
		 stuNameField.setText(stu.getName());
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
	public void actionPerformed(ActionEvent e) {
		 Object source = e.getSource();
		 if (source == btnReturnToPervious){
			 this.dispose();
			 SRSAdInterface frame = new SRSAdInterface();
			 frame.setVisible(true);
		 }
		 else if (source == btnViewThisCourse){
			 viewCourse();
		 }
		 else if (source == btnDeletThisCourse){
			 deletThisCourse();
		 }
		 else if (source == btnEditThisCourse){
			 editCourse();
		 }
		 else if (source == eButton){
			 cButton.setSelected(false);
		 }
		 else if (source == cButton){
			 eButton.setSelected(false);
		 }
		 else if (source == btnShowAll){
			 reset();
			 showAll(); 
		 }
		 else if (source == btnResetFormAbove){
			 reset();
		 }
		 else if (source == btnDeleteThisStudent){
			 deleteThisStudent();
		 }
		 else if (source == btnCheckTeachersId){
			 String id =  teaIDField.getText();
			 if (SRSFunctions.checkForPerson(id)){
				 SRSInfoPassed frame = new SRSInfoPassed();
				 frame.setVisible(true);
				 Professor pro = SRSFunctions.makeAProfessor(id);
				 teaNameField.setText(pro.getName());
			 } 
		 }
		 else if (source == btnAddThisNew){
			 addAStudent();
		 }
		 else if (source == btnCheckThisStudent){
			 String studentID = stuIDField.getText();
			 if (SRSFunctions.checkForPerson(studentID)){
				 SRSInfoPassed frame = new SRSInfoPassed();
				 frame.setVisible(true);
				 stuNameField.setText(SRSFunctions.makeAStudent(studentID).getName());
			 }
		 }
		 else if (source == btnDeactivateThisCourse){
			 SRSFunctions.deactivateACourse(crnField_2.getText());
		 }
		 else if (source == btnRefresh){
			 this.dispose();
			 SRSVieCou frame = null;
			try {
				frame = new SRSVieCou();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			 frame.setVisible(true);
		 }
		 else if (source == btnNewButton){
			 SRSCreateReports.formCourseReport(crnField.getText());
			 SRSSaveSucceed frame = new SRSSaveSucceed();
			 frame.setVisible(true);
		 }
	}
	@Override
	public void mousePressed(MouseEvent e) {
		crn_1 = couList2.getSelectedValue().substring(6, 15);
		crnField_2.setText(crn_1);	
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
