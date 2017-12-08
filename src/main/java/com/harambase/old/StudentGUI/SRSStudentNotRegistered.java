package StudentGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class SRSStudentNotRegistered extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try {
					SRSStudentNotRegistered frame = new SRSStudentNotRegistered();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public SRSStudentNotRegistered() {
		setTitle("Error!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 773, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel errorInfo = new JLabel("You have not registered into this course!");
		errorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		errorInfo.setForeground(new Color(255, 0, 0));
		errorInfo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(errorInfo);
		
		btnNewButton = new JButton("Close");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnNewButton){
			this.dispose();
		}
		
	}

}