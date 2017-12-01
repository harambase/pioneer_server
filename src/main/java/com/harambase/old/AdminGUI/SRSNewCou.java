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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SRSNewCou extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField crnField;
	private JButton btnReturnToPrevious;
	private JButton btnInformationAboutHow;
	private JButton btnSaveForThis;
	private JTextField idField;

	private static String coursesFile = "AllCourseCatalog.dat";
	private static String newSemFile = "";
	private static String[] info = new String[1000];
	private JButton btnCheckForThis;
	private JButton btnResetForm;
	private JTextField textCourseName;
	private JTextField textLevel;
	private JLabel lblPartiiCourseSchedule;
	private JTextField textStart;
	private JTextField textEnd;
	private JLabel lblTheDaysOf;
	private JTextField textDay;
	private JLabel lblTheTimeOf;
	private JTextField textTimeStart;
	private JTextField textTimeEnds;
	private JLabel lblPartiiiCourseInstruction;
	private JLabel lblThe;
	private JTextField textCapa;
	private JButton btnCheckTheTeacher;
	private JLabel lblCreditesForThis;
	private JTextField textCredit;
	private JLabel lblRequriment;
	private JRadioButton rdbtnCompulsory;
	private JRadioButton rdbtnElective;
	private JLabel lblCourseInformationForm;
	private JLabel label_2;
	private JButton btnCheckTheTeacher_1;
	private JPanel panel_1;
	private JTextField yearField;
	private JRadioButton rdbtnFall;
	private JRadioButton rdbtnSpring;
	private JRadioButton rdbtnSummer;
	private JButton btnCheckThisSemester;
	private JButton btnAddThisNew;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSNewCou frame = new SRSNewCou();
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
	public SRSNewCou() {
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 798);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblCRN = new JLabel("The CRN of this Course:*");
		lblCRN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		crnField = new JTextField();
		crnField.setBackground(SystemColor.menu);
		crnField.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name:*");
		lblCourseName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblNewStudentRegistration = new JLabel("New Course & New Semester Registration Form.");
		lblNewStudentRegistration.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnSaveForThis = new JButton("Save this one");
		btnSaveForThis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		btnReturnToPrevious = new JButton("Return to Previous Menu");
		btnReturnToPrevious.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		btnCheckForThis = new JButton("Check for CRN whether being used");
		btnCheckForThis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		btnCheckTheTeacher = new JButton("Check The Teacher whether being registered");
		btnCheckTheTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		btnInformationAboutHow = new JButton("Information about how to fill this form");
		btnInformationAboutHow.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnInformationAboutHow.addActionListener(this);
		btnInformationAboutHow.addActionListener(this);
		
		btnResetForm = new JButton("Reset Form");
		btnResetForm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnResetForm.addActionListener(this);
		
		btnCheckTheTeacher_1 = new JButton("Check The Teacher whether being Active");
		btnCheckTheTeacher_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addGap(247))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnResetForm, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveForThis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCheckForThis, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCheckTheTeacher, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnReturnToPrevious, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCheckTheTeacher_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)))
					.addGap(70))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewStudentRegistration)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInformationAboutHow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 772, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 772, Short.MAX_VALUE)))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInformationAboutHow)
						.addComponent(lblNewStudentRegistration, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCheckForThis)
						.addComponent(btnCheckTheTeacher))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveForThis)
						.addComponent(btnCheckTheTeacher_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnResetForm)
						.addComponent(btnReturnToPrevious, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(228))
		);
		
		btnCheckThisSemester = new JButton("Check this Semester whether Registered");
		btnCheckThisSemester.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		btnAddThisNew = new JButton("Add this New Semester");
		btnAddThisNew.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		rdbtnFall = new JRadioButton("Fall (03)");
		rdbtnFall.setBackground(Color.WHITE);
		rdbtnFall.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		rdbtnSpring = new JRadioButton("Spring (01)");
		rdbtnSpring.setBackground(Color.WHITE);
		rdbtnSpring.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		rdbtnSummer = new JRadioButton("Summer (02)");
		rdbtnSummer.setBackground(Color.WHITE);
		rdbtnSummer.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblSeason = new JLabel("2.Season:*");
		lblSeason.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JLabel label_4 = new JLabel("Semester Basic Information Form:");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		yearField = new JTextField();
		yearField.setBackground(SystemColor.menu);
		yearField.setText("");
		yearField.setColumns(10);
		
		JLabel lblyearyyyy = new JLabel("1.Year:*(YYYY)");
		lblyearyyyy.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblyearyyyy, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSeason, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(rdbtnFall)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnSpring, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnSummer))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnAddThisNew, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCheckThisSemester, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(109))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(4)
									.addComponent(lblyearyyyy, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(rdbtnSpring, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnSummer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnFall, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblSeason, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddThisNew)
						.addComponent(btnCheckThisSemester))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblStudentNameenglish = new JLabel("The Instructor of this Course (Please specify the ID number instead of the Name) *");
		lblStudentNameenglish.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		idField = new JTextField();
		idField.setBackground(SystemColor.menu);
		idField.setColumns(10);
		
		textCourseName = new JTextField();
		textCourseName.setBackground(SystemColor.menu);
		textCourseName.setColumns(10);
		
		JLabel lblCourseLevelNumber = new JLabel("Course Level Number (111 - 399):");
		lblCourseLevelNumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		textLevel = new JTextField();
		textLevel.setBackground(SystemColor.menu);
		textLevel.setColumns(10);
		
		JLabel lblCourseBasicInformation = new JLabel("PartI: Course Basic Information Form:");
		lblCourseBasicInformation.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblPartiiCourseSchedule = new JLabel("PartII: Course Schedule  Information Form:");
		lblPartiiCourseSchedule.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		textStart = new JTextField();
		textStart.setBackground(SystemColor.menu);
		textStart.setColumns(10);
		
		textEnd = new JTextField();
		textEnd.setBackground(SystemColor.menu);
		textEnd.setColumns(10);
		
		lblTheDaysOf = new JLabel("The days of this Course: (MWF/TR/WEEKDAY/...)");
		lblTheDaysOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		textDay = new JTextField();
		textDay.setBackground(SystemColor.menu);
		textDay.setColumns(10);
		
		lblTheTimeOf = new JLabel("The time of this Course: (Starts[13:00] - Ends)");
		lblTheTimeOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		textTimeStart = new JTextField();
		textTimeStart.setBackground(SystemColor.menu);
		textTimeStart.setColumns(10);
		
		textTimeEnds = new JTextField();
		textTimeEnds.setBackground(SystemColor.menu);
		textTimeEnds.setColumns(10);
		
		lblPartiiiCourseInstruction = new JLabel("PartIII: Course Instruction  Information Form:");
		lblPartiiiCourseInstruction.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		textCapa = new JTextField();
		textCapa.setBackground(SystemColor.menu);
		textCapa.setColumns(10);
		
		textCredit = new JTextField();
		textCredit.setBackground(SystemColor.menu);
		textCredit.setText("");
		textCredit.setColumns(10);
		
		rdbtnCompulsory = new JRadioButton("Compulsory");
		rdbtnCompulsory.setBackground(Color.WHITE);
		rdbtnCompulsory.setForeground(Color.BLACK);
		rdbtnCompulsory.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		rdbtnElective = new JRadioButton("Elective");
		rdbtnElective.setBackground(Color.WHITE);
		rdbtnElective.setForeground(Color.BLACK);
		rdbtnElective.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		lblRequriment = new JLabel("Requirement:*");
		lblRequriment.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		lblCourseInformationForm = new JLabel("Course Registration Information Form");
		lblCourseInformationForm.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		label_2 = new JLabel("The date of this Course: (YYYY/MM - YYYY/MM)");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		lblThe = new JLabel("The Maximum Capacity of this Course:");
		lblThe.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		lblCreditesForThis = new JLabel("Credites for This Course*");
		lblCreditesForThis.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPartiiiCourseInstruction, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(257, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTheDaysOf, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTheTimeOf, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textTimeStart, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textTimeEnds, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
										.addComponent(textDay, 341, 341, 341)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textStart, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textEnd, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCRN)
										.addComponent(lblCourseName)
										.addComponent(lblCourseLevelNumber, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCreditesForThis, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
									.addGap(21)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textCredit, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblRequriment, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnElective, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnCompulsory))
										.addComponent(textLevel, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
										.addComponent(textCourseName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
										.addComponent(crnField, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))))
							.addGap(95))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPartiiCourseSchedule, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(257, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCourseBasicInformation, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(257, Short.MAX_VALUE))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblThe, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textCapa, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
						.addComponent(lblStudentNameenglish, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
						.addComponent(idField, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
					.addContainerGap(95, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCourseInformationForm, GroupLayout.PREFERRED_SIZE, 582, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(260, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(14)
					.addComponent(lblCourseInformationForm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCourseBasicInformation, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(crnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCRN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCourseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCourseName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCourseLevelNumber, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnCompulsory)
						.addComponent(rdbtnElective)
						.addComponent(lblRequriment, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCreditesForThis)
						.addComponent(textCredit, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPartiiCourseSchedule, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textTimeEnds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTimeStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTheDaysOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTheTimeOf, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPartiiiCourseInstruction, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStudentNameenglish)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThe)
						.addComponent(textCapa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		panel.setLayout(gl_panel);
		
		contentPane.setLayout(gl_contentPane);
		btnReturnToPrevious.addActionListener(this);
		btnSaveForThis.addActionListener(this);
		btnCheckForThis.addActionListener(this);
		btnCheckTheTeacher.addActionListener(this);
		btnCheckTheTeacher_1.addActionListener(this);
		btnCheckThisSemester.addActionListener(this);
		rdbtnCompulsory.addActionListener(this);
		rdbtnElective.addActionListener(this);
		rdbtnFall.addActionListener(this);
		rdbtnSpring.addActionListener(this);
		rdbtnSummer.addActionListener(this);
		idField.setText("");
		textCredit.setText("");
    	textCourseName.setText("");
    	textLevel.setText("");
    	textDay.setText("");
    	textStart.setText("");
    	textEnd.setText("");
    	textTimeStart.setText("");
    	textTimeEnds.setText("");
    	textCapa.setText("");
    	crnField.setText("");
	}
	
	public boolean checkForConflict(){
		String newStudentID;
		boolean act = false;
		newStudentID = crnField.getText();
		if (newStudentID.length() < 9 || newStudentID.length() > 9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}	
		else{
				File fl = new File("C" + newStudentID + ".dat");
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
	public boolean checkForSemester(){
		newSemFile = yearField.getText();
		if (rdbtnFall.isSelected()){
			newSemFile = newSemFile + "03" + "CourseCatalog.dat";
		}
		else if (rdbtnSpring.isSelected()){
			newSemFile = newSemFile + "01" + "CourseCatalog.dat";
		}
		else if (rdbtnSummer.isSelected()){
			newSemFile = newSemFile + "02" + "CourseCatalog.dat";
		}

		File fl = new File(newSemFile);
		if (fl.exists()){
			SRSInfoPassed frame = new SRSInfoPassed();
			frame.setVisible(true);
			return true;
		}
		else{
			SRSInfoNotPassed frame = new SRSInfoNotPassed();
			frame.setVisible(true);
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == btnReturnToPrevious){
			this.dispose();
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		}
		else if(source == btnCheckThisSemester){
			checkForSemester();
		}
		else if(source == rdbtnSpring){
			rdbtnFall.setSelected(false);
			rdbtnSummer.setSelected(false);
		}
		else if(source == rdbtnFall){
			rdbtnSpring.setSelected(false);
			rdbtnSummer.setSelected(false);
		}
		else if(source == rdbtnSummer){
			rdbtnFall.setSelected(false);
			rdbtnSpring.setSelected(false);
		}
		else if(source == btnResetForm){
			rdbtnFall.setSelected(false);
			rdbtnSpring.setSelected(false);
			rdbtnSummer.setSelected(false);
			rdbtnElective.setSelected(false);
			rdbtnCompulsory.setSelected(false);
			rdbtnSpring.setSelected(false);
			yearField.setText("");
			idField.setText("");
	    	textCourseName.setText("");
	    	textLevel.setText("");
	    	textDay.setText("");
	    	textStart.setText("");
	    	textEnd.setText("");
	    	textTimeStart.setText("");
	    	textTimeEnds.setText("");
	    	textCapa.setText("");
	    	crnField.setText("");
	    	textCredit.setText("");
		}
		else if(source == btnInformationAboutHow){
			SRSSaveInfo frame = new SRSSaveInfo();
			frame.setVisible(true);
		}
		else if(source == btnCheckTheTeacher){
			if(SRSFunctions.checkForPerson(idField.getText())){
				SRSInfoPassed frame = new SRSInfoPassed();
				frame.setVisible(true);
			}
		}
		else if(source == btnCheckForThis){
			checkForConflict();
		}
		else if(source == rdbtnCompulsory){
			rdbtnElective.setSelected(false);
		}
		else if(source == rdbtnElective){
			rdbtnCompulsory.setSelected(false);
		}
		
		else if(source == btnAddThisNew){
			newSemFile = yearField.getText();
			if (rdbtnFall.isSelected()){
				newSemFile = newSemFile + "03" + "CourseCatalog.dat";
			}
			else if (rdbtnSpring.isSelected()){
				newSemFile = newSemFile + "01" + "CourseCatalog.dat";
			}
			else if (rdbtnSummer.isSelected()){
				newSemFile = newSemFile + "02" + "CourseCatalog.dat";
			}
			try {
				if (checkForSemester()){
					PrintWriter pw = new PrintWriter(new File(newSemFile));
					pw.print("");
					pw.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(source == btnCheckTheTeacher_1){
			if(SRSFunctions.checkForActive(idField.getText())){
				SRSPersonActive frame = new SRSPersonActive();
				frame.setVisible(true);
			}
		}
		else if(source == btnSaveForThis){
			String crn, courseName, courseLevel, credits, require = "Elective";
			String courseS, courseE, courseD, courseTS, courseTE;
			String capa, teacherID;
			String courseInfo;
			crn = crnField.getText();
			courseName = textCourseName.getText();
			courseLevel = textLevel.getText();
			credits = textCredit.getText();
			courseS = textStart.getText();
			courseE = textEnd.getText();
			courseD = textDay.getText();
			courseTS = textTimeStart.getText();
			courseTE = textTimeEnds.getText();
			capa = textCapa.getText();
			teacherID = idField.getText();	
			if (courseS.isEmpty()){
				courseS = "N/A";
			}
			if (courseE.isEmpty()){
				courseE = "N/A";
			}
			if (courseD.isEmpty()){
				courseD = "TBD";
			}
			if (courseTS.isEmpty()){
				courseTS = "N/A";
			}
			if (courseTE.isEmpty()){
				courseTE = "N/A";
			}
			if (capa.isEmpty()){
				capa = "50";
			}
			if (rdbtnCompulsory.isSelected()){
				require = "Compulsory";
			}
			else{
				require = "Elective";
			}
			info[0] = crn + "\t" + courseName + "\t" + credits + "\t"+ require + "\t" + teacherID;
			courseInfo = info[0];
			
			if (courseName.isEmpty() || credits.isEmpty()){
				SRSRegisterError frame = new SRSRegisterError();
				frame.setVisible(true);
			}
			else if (SRSFunctions.checkForActive(teacherID) && SRSFunctions.checkForPerson(idField.getText())
					&& !checkForConflict()){
				try {
				//Course File in/output
					Scanner scan = new Scanner(new File(coursesFile));
					int i = 1, j = 0;
					while (scan.hasNextLine()){
						info[i] = scan.nextLine();
						i++;
					}
					PrintWriter pw = new PrintWriter(coursesFile);
					while (j < i){
						pw.println(info[j]);
						j++;
					}
					String individualFile = "C" + crn + ".dat";
					PrintWriter out = new PrintWriter(individualFile);
					out.println(info[0] + "\t" + courseLevel +"\t" + courseS + "\t"
							+ courseE + "\t" + courseD + "\t" + courseTS + "\t"
							+ courseTE + "\t" + capa + "\t" + "A" + "\t");
					scan.close();
					pw.close();
					out.close();
				//teacher files record
					i = j = 0;
					String teacher = teacherID + ".dat";
					String[] teaInfo = new String[1000];
					scan = new Scanner(new File(teacher));
					while (scan.hasNext()){
						teaInfo[i] = scan.nextLine();
						i++;
					}
					out = new PrintWriter(teacher);
					while (j < i){
						out.println(teaInfo[j]);
						j++;
					}
					String couInfo = crn + "\t" + credits + "\t";
					out.println(couInfo);
					out.close();
					scan.close();
				//output to Semester files
					info = new String[100];
					String semeBelong = "";
					for (i = 0; i < 6; i++){
						semeBelong = semeBelong + crn.charAt(i);
					}
					newSemFile = semeBelong + "CourseCatalog.dat";
					Scanner sc = new Scanner(new File(newSemFile));
					i = 0;
					j = 0;
					
					while (sc.hasNext()){
						info[i] = sc.nextLine();
						i++;
					}
					PrintWriter pwS = new PrintWriter(new File(newSemFile));
					pwS.println(courseInfo);
					while (j < i){
						pwS.println(info[j]);
						j++;
					}
					pwS.close();
					sc.close();
					
					} catch (FileNotFoundException e) {
						String semeBelong = "";
						for (int i = 0; i < 6; i++){
							semeBelong = semeBelong + crn.charAt(i);
						}
						newSemFile = semeBelong + "CourseCatalog.dat";
						try {
							PrintWriter pwS = new PrintWriter(new File(newSemFile));
							pwS.print(courseInfo);
							pwS.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
				}
				SRSSaveSucceed frame = new SRSSaveSucceed();
				frame.setVisible(true);
			}
		}
		
	}
}
