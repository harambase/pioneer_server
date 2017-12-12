package AdminGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class SRSSaveInfo extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnClose;
	private JScrollPane scrollPane;
	private JTextPane txtpnPartOneNew;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSSaveInfo frame = new SRSSaveInfo();
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
	public SRSSaveInfo() {
		setFont(null);
		setTitle("Student Record System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblInformationAboutHow = new JLabel("Information about how to make a new student/teacher file");
		lblInformationAboutHow.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblInformationAboutHow, BorderLayout.NORTH);
		
		btnClose = new JButton("Close");
		contentPane.add(btnClose, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		txtpnPartOneNew = new JTextPane();
		txtpnPartOneNew.setEditable(false);
		txtpnPartOneNew.setText("PART ONE  New Student Registration Process\r\nQ1: \tHow to issue an ID number for a new Student?\r\nA:   \tThe first three digits are used as the identificate number or serial number, which will initialize by 9XX. The forth to fifth digits are used as the semester id. If this student first come to Pioneer in fall semester, then it should semester id will be 03. If this student starts during spring term,  semester id should be 01. The last four digits will be the year he/she enters school.\r\n\t\tYou can check all the used ID in \"Person Mode\" or \"View Student Mode\" to determine the proper serial number.\r\ne.g. 901012015, which means he/she register during fall term 2015, and his/her serial number is 901\r\nQ2:\t\tHow to give a student password?\r\nA:\t\tYou can create any password you want. Also, you may change it in \"View Student Mode\".\r\n\r\nPART TWO New Teacher Registratioin Process\r\nQ1: \tHow to issue an ID number for a new Teacher?\r\nA:   \tThe first three digits should be 800. The forth to fifth digits are used are used as the identificate number or serial number, which will start from 00. The last four digits will be 0001.\r\ne.g. 800010001\r\n\r\nFor All:\r\nNotice: The \"*\" sign means you have to fill those forms");
		txtpnPartOneNew.setForeground(Color.RED);
		txtpnPartOneNew.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtpnPartOneNew.setBackground(SystemColor.info);
		scrollPane.setViewportView(txtpnPartOneNew);
		
		btnClose.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnClose){
			this.dispose();
		}
		
	}

}
