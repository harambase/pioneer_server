package SetUpGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class SRSChoicer extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel welcomePanel;
	private JLabel welcomeLabel;
	private JPanel buttonPanel;
	
	private JButton stuButton;
	private JButton proButton;
	private JButton adButton;
	
	private JLabel desLabel;
	
	private SRSStuLogin stuLogin;
	private SRSProLogin proLogin;
	private SRSAdmLogin adLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SRSChoicer frame = new SRSChoicer();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public SRSChoicer() {
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		welcomePanel = new JPanel();
		contentPane.add(welcomePanel, BorderLayout.NORTH);
		
		welcomeLabel = new JLabel("Welcome to Student Record System");
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		welcomePanel.add(welcomeLabel);
		
		buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		stuButton = new JButton("Student Login");
		stuButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		buttonPanel.add(stuButton);
		stuButton.addActionListener(this);
		
		proButton = new JButton("Professor Login");
		proButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		buttonPanel.add(proButton);
		proButton.addActionListener(this);
		
		adButton = new JButton("Administrator Login");
		adButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		buttonPanel.add(adButton);
		adButton.addActionListener(this);
		
		desLabel = new JLabel("Designed by Shilei Lin Version 2.0");
		desLabel.setHorizontalAlignment(SwingConstants.CENTER);
		desLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		contentPane.add(desLabel, BorderLayout.SOUTH);
	} 
	
	public void actionPerformed(ActionEvent evt) {
	    Object source = evt.getSource();
	    if (source == stuButton) {
	    	this.dispose();
	    	stuLogin = new SRSStuLogin();
	    	stuLogin.setVisible(true);
	    }
	    else if(source == proButton){
	    	this.dispose();
	    	proLogin = new SRSProLogin();
	    	proLogin.setVisible(true);
	    }
	    else {
	    	this.dispose();
	    	adLogin = new SRSAdmLogin();
	    	adLogin.setVisible(true);
	    }
	}
}
