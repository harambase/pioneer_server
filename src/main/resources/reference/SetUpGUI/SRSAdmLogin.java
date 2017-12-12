package SetUpGUI;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class SRSAdmLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton resetButton;
	private JButton returnButton;
	
	private String userName, password;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SRSAdmLogin frame = new SRSAdmLogin();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public SRSAdmLogin() {
		setTitle("Student Registration System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblHeyWelcomeBack = new JLabel("Hey! Welcome back Administrator.");
		lblHeyWelcomeBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeyWelcomeBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblHeyWelcomeBack, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel.add(lblUsername);
		
		userNameField = new JTextField();
		panel.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel.add(passwordField);
		
		loginButton = new JButton("Login");
		panel.add(loginButton);
		loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		loginButton.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel.add(resetButton);
		resetButton.addActionListener(this);
		
		returnButton = new JButton("Return");
		returnButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		contentPane.add(returnButton, BorderLayout.SOUTH);
		returnButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent evt){
	    Object source = evt.getSource();
	    if (source == loginButton) {  	
	    	paramString();			
	    	userName = "001010001";
	    	password = "password";
	    	String passF =  String.copyValueOf(passwordField.getPassword());
	    	
	    	if (userNameField.getText().equals(userName) 
	    			&& (passF.equals(password)))
	    	{
	    			this.dispose();
	    			try {
	    				SRSAdInterface frame= new SRSAdInterface();
	    				frame.setVisible(true);
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    			
	    	}
	    	else{
	    		SRSLoginError frame = new SRSLoginError();
		    	frame.setVisible(true);
		    	userNameField.setText(null);
		    	passwordField.setText(null);
	    	}
	    }
	    else if(source == resetButton){
	    	userNameField.setText(null);
	    	passwordField.setText(null);
	    }
	    else if(source == returnButton){
	    	this.dispose();
	    	SRSChoicer frame = new SRSChoicer();
	    	frame.setVisible(true);
	    }
	}

}
