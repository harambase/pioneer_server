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
import AdminGUIBars.SRSRegisterError;
import AdminGUIBars.SRSSaveSucceed;
import SRSDataBase.SRSFunctions;
import SetUpGUI.SRSAdInterface;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SRSNewTea extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton btnReturnToPrevious;
	private JButton btnInformationAboutHow;
	private JButton btnSaveForThis;
	private JTextField nameField;

	private static String teachersFile = "Teachers.dat";
	private static String[] info = new String[1000];
	private JButton btnCheckForThis;
	private JLabel label_1;
	private JButton btnResetForm;
	private JTextField yyField;
	private JTextField mmField;
	private JTextField ddField;
	private JLabel label_2;
	private JRadioButton mButton;
	private JRadioButton fButton;
	private JTextField telField;
	private JTextField qqField;
	private JTextField eField;
	private JTextField dormField;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSNewTea frame = new SRSNewTea();
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
	public SRSNewTea() {
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Welcome Back to SRS.");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblStudentNewId = new JLabel("Teacher's New ID number:*");
		lblStudentNewId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		idField = new JTextField();
		idField.setBackground(SystemColor.menu);
		idField.setColumns(10);
		
		JLabel lblStudentNewPassword = new JLabel("Teacher's New Password:*");
		lblStudentNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();
		passwordField.setBackground(SystemColor.menu);
		passwordField.setColumns(10);
		
		JLabel lblNewStudentRegistration = new JLabel("New Teacher Registration Form.");
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnResetForm, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(btnSaveForThis, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnReturnToPrevious, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addComponent(btnCheckForThis, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewStudentRegistration, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
							.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(btnInformationAboutHow, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
					.addContainerGap(153, Short.MAX_VALUE))
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckForThis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveForThis)
						.addComponent(btnReturnToPrevious, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnResetForm)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(194))
		);
		
		JLabel lblStudentNameenglish = new JLabel("Teacher's Name (English):*");
		lblStudentNameenglish.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		nameField = new JTextField();
		nameField.setBackground(SystemColor.menu);
		nameField.setColumns(10);
		
		JLabel lblTeachersBirthdayyyyymmdd = new JLabel("Teacher's Birthday (YYYY/MM/DD):");
		lblTeachersBirthdayyyyymmdd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		yyField = new JTextField();
		yyField.setBackground(SystemColor.menu);
		yyField.setText("");
		yyField.setColumns(10);
		
		mmField = new JTextField();
		mmField.setBackground(SystemColor.menu);
		mmField.setText("");
		mmField.setColumns(10);
		
		ddField = new JTextField();
		ddField.setBackground(SystemColor.menu);
		ddField.setText("");
		ddField.setColumns(10);
		
		JLabel lblTeachersContactInfomationoptional = new JLabel("Teacher's Contact Infomation(Optional)");
		lblTeachersContactInfomationoptional.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel label_4 = new JLabel("Telephone:");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_5 = new JLabel("QQ:");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel label_6 = new JLabel("Email:");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		JLabel lblTeachersAssignedDormitory = new JLabel("Teacher's Assigned Dormitory:");
		lblTeachersAssignedDormitory.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		label_2 = new JLabel("Gender:*");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		mButton = new JRadioButton("Male");
		mButton.setBackground(Color.WHITE);
		mButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		fButton = new JRadioButton("Female");
		fButton.setBackground(Color.WHITE);
		fButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		telField = new JTextField();
		telField.setBackground(SystemColor.menu);
		telField.setText("");
		telField.setColumns(10);
		
		qqField = new JTextField();
		qqField.setBackground(SystemColor.menu);
		qqField.setColumns(10);
		
		eField = new JTextField();
		eField.setBackground(SystemColor.menu);
		eField.setText("");
		eField.setColumns(10);
		
		dormField = new JTextField();
		dormField.setBackground(SystemColor.menu);
		dormField.setText("");
		dormField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(yyField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(mmField, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(ddField, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
										.addComponent(lblTeachersContactInfomationoptional, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTeachersBirthdayyyyymmdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblStudentNewPassword, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
												.addComponent(lblStudentNewId, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStudentNameenglish, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(idField, Alignment.TRAILING)
												.addComponent(passwordField, Alignment.TRAILING)
												.addComponent(nameField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
								.addGap(191))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(dormField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
										.addGap(30)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addGap(13)
												.addComponent(qqField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(eField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(telField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTeachersAssignedDormitory)
					.addContainerGap(305, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentNewId)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentNewPassword))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentNameenglish))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTeachersBirthdayyyyymmdd, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ddField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(lblTeachersContactInfomationoptional, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(telField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(qqField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(eField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTeachersAssignedDormitory, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(dormField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(mButton)
						.addComponent(fButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		nameField.setText("");
    	passwordField.setText("");
    	idField.setText("");
		
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
	public void save(){
		//Teacher file: ID + Password +  Name + Active? + type + birth + tele + qq + email + dorm + Gender	
		String studentID, password, name;
		String active, type, birth, tele, qq, email, dorm, gender;
		
		studentID = idField.getText();
		password = String.copyValueOf(passwordField.getPassword());
		name =  nameField.getText();
		active = "A";
		type = "T";
		birth = yyField.getText() + "/" + mmField.getText() + "/" + ddField.getText();
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
		boolean act = checkForConflict();		

		if(studentID.length()<9 || studentID.length()>9){
			SRSRegisterError frame = new SRSRegisterError();
			frame.setVisible(true);
		}
		else if(!act){
			info[0] = studentID +"\t" +password+"\t" + name + "\t" + active + "\t";
			System.out.println(info[0]);
			try {
				Scanner scan = new Scanner(new File(teachersFile));
				int i = 1;
				while (scan.hasNextLine()){
					info[i] = scan.nextLine();
					i++;
				}
				int j = 0;
				PrintWriter pw = new PrintWriter(teachersFile);
				while(j < i){

					pw.println(info[j]);
					j++;
				}
				String individualFile = studentID + ".dat";
				PrintWriter out = new PrintWriter(individualFile);
				out.println(info[0] + "\t" + type + "\t" + birth + "\t" + 
							tele + "\t" + qq + "\t" + email + "\t" + dorm + "\t" + gender + "\t");
				pw.close();
				out.close();
				scan.close();
				SRSFunctions.sort(teachersFile);
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
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == btnReturnToPrevious){
			this.dispose();
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		}
		else if(source == btnResetForm){
			SRSFunctions.sort(teachersFile);
			nameField.setText("");
	    	passwordField.setText("");
	    	idField.setText("");
	    	yyField.setText("");
	    	mmField.setText("");
	    	ddField.setText("");
	    	mButton.setSelected(false);
	    	fButton.setSelected(false);
	    	telField.setText("");
	    	qqField.setText("");
	    	eField.setText("");
	    	dormField.setText("");
		}
		else if(source == btnInformationAboutHow){
			SRSSaveInfo frame = new SRSSaveInfo();
			frame.setVisible(true);
		}
		
		else if(source == btnCheckForThis){
			boolean act = checkForConflict();
			if (act == false){
				SRSIdNotUsed frame = new SRSIdNotUsed();
				frame.setVisible(true);
			}
		}
		else if(source == btnSaveForThis){
			save();
		}
	}
}
