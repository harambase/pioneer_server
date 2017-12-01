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
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import NewCore.Course;
import NewCore.Student;
import NewCore.Transcript;
import SRSDataBase.SRSFunctions;
import javax.swing.border.LineBorder;

import java.awt.Color;

@SuppressWarnings("serial")
public class SRSTeaVieCouSim extends JFrame implements ActionListener {
	
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
	private JRadioButton eButton;
	private JRadioButton cButton;
	private JButton btnReturnToPervious;

	private JLabel lblTeachersName;
	private JTextField teaNameField;
	private JLabel label_2;
	private JButton btnDeactivateThisCourse;
	private Course cou = SRSTeacherGUI.course;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSTeaVieCouSim frame = new SRSTeaVieCouSim();
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
	public SRSTeaVieCouSim() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 613);
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
		
		JLabel lblCinfomationOfThis = new JLabel("Infomation of this selected course Form");
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
		
		btnReturnToPervious = new JButton("Close");
		btnReturnToPervious.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		label_2 = new JLabel("Designed by Shilei Lin Version 2.0");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		btnDeactivateThisCourse = new JButton("Deactivate this Course");
		btnDeactivateThisCourse.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnDeactivateThisCourse.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(230)
									.addComponent(btnDeactivateThisCourse, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnReturnToPervious, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(63)
							.addComponent(lblViewingCourseForm, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
					.addGap(71))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(32)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblViewingCourseForm)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReturnToPervious)
					.addGap(151)
					.addComponent(btnDeactivateThisCourse, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		eButton.addActionListener(this);
		cButton.addActionListener(this);
		btnReturnToPervious.addActionListener(this);
		
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
		eButton.setSelected(false);
		cButton.setSelected(false);
	}

	public void actionPerformed(ActionEvent e) {
		 Object source = e.getSource();
		 if (source == btnReturnToPervious){
			 this.dispose();
		 }
		 else if (source == eButton){
			 cButton.setSelected(false);
		 }
		 else if (source == cButton){
			 eButton.setSelected(false);
		 }
	}

}
