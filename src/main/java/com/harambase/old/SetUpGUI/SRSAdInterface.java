package SetUpGUI;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import SRSDataBase.SRSFunctions;
import AdminGUI.SRSNewCou;
import AdminGUI.SRSNewStu;
import AdminGUI.SRSNewTea;
import AdminGUI.SRSVieCou;
import AdminGUI.SRSViePerson;
import AdminGUI.SRSVieStu;
import AdminGUI.SRSVieTea;

import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class SRSAdInterface extends JFrame implements ActionListener{

	private JButton viewTeButton;
	private JButton viewStButton;
	private JButton teReButton;
	private JButton stReButton;
	private JButton btnViewingCoursesInformation;
	private JButton returnButton;
	private JLabel label;
	private JButton btnNewCourseRegistration;
	private JButton btnViewingPersonsInformation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SRSAdInterface frame = new SRSAdInterface();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SRSAdInterface() {
		//JFrame frmStudentRecordSystem = new JFrame();
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 407);
		
		stReButton = new JButton("New Student Registration");
		stReButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		teReButton = new JButton("New Teacher Registration");
		teReButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblNewLabel = new JLabel("Welcome Back to SRS.");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		
		viewTeButton = new JButton("Viewing Teachers information");
		viewTeButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		viewStButton = new JButton("Viewing Students information");
		viewStButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewingCoursesInformation = new JButton("Viewing Courses information");
		btnViewingCoursesInformation.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		returnButton = new JButton("Return to Login menu");
		returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		label = new JLabel("Designed by Shilei Lin Version 2.0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		
		btnNewCourseRegistration = new JButton("New Course Registration");
		btnNewCourseRegistration.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		btnViewingPersonsInformation = new JButton("Viewing Persons information");
		btnViewingPersonsInformation.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnViewingPersonsInformation.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(112, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(139)
					.addComponent(lblNewLabel)
					.addContainerGap(151, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(returnButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
						.addComponent(btnViewingPersonsInformation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(teReButton, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(viewTeButton, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnNewCourseRegistration, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(btnViewingCoursesInformation, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(stReButton, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(viewStButton, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(viewTeButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(teReButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(viewStButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(stReButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewCourseRegistration, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnViewingCoursesInformation, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnViewingPersonsInformation, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(returnButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		returnButton.addActionListener(this);
		viewTeButton.addActionListener(this);
		viewStButton.addActionListener(this);
		stReButton.addActionListener(this);
		teReButton.addActionListener(this);
		btnNewCourseRegistration.addActionListener(this);
		btnViewingCoursesInformation.addActionListener(this);
		SRSFunctions.createAllPersonFile();
	}

	@Override
	public void actionPerformed(ActionEvent evt){
	   Object source = evt.getSource();
	   if (source == stReButton) {  	
		   this.dispose();
		   SRSNewStu frame = new SRSNewStu();
		   frame.setVisible(true);
	   }
	   else if(source == teReButton){
		   this.dispose();
		   SRSNewTea frame = new SRSNewTea();
		   frame.setVisible(true);
	   }
	   else if(source == returnButton){
	    	this.dispose();
	    	SRSChoicer frame = new SRSChoicer();
	    	frame.setVisible(true);
	    }
	   else if(source == btnNewCourseRegistration){
		   this.dispose();
		   SRSNewCou frame = new SRSNewCou();
		   frame.setVisible(true);
	   }
	   else if(source == viewStButton){
		   this.dispose();
		   SRSVieStu frame = new SRSVieStu();
		   frame.setVisible(true);
	   }
	   else if(source == viewTeButton){
		   this.dispose();
		   SRSVieTea frame = new SRSVieTea();
		   frame.setVisible(true);
	   }
	   else if(source == btnViewingPersonsInformation){
		   this.dispose();
		   SRSViePerson frame = new SRSViePerson();
		   frame.setVisible(true);
	   }
	   else if(source == btnViewingCoursesInformation){
		   this.dispose();
		   SRSVieCou frame = null;
		try {
			frame = new SRSVieCou();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		   frame.setVisible(true);
	   }
	}
}
