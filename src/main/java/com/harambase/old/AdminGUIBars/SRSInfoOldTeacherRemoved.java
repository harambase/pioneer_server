package AdminGUIBars;

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
public class SRSInfoOldTeacherRemoved extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private static boolean act = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try {
					SRSInfoOldTeacherRemoved frame = new SRSInfoOldTeacherRemoved();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public SRSInfoOldTeacherRemoved() {
		setTitle("INFO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel errorInfo = new JLabel("Warning! Ex-Registered Teacher will be replaced!");
		errorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		errorInfo.setForeground(new Color(255, 0, 0));
		errorInfo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(errorInfo);
		
		btnNewButton_1 = new JButton(" Cancel the Replace Process");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(btnNewButton_1);
		
		btnNewButton = new JButton("Confirm to replace the Ex-Registered teacher");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
	}
	/**
	 * 
	 * @param act
	 * @return true confirmed, false canceled
	 */
	public static boolean action(){
		return act;
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnNewButton){
			act = true;
			action();
		    
			this.dispose();
		}
		else if(source == btnNewButton_1){
			act = false;
			action();
			this.dispose();
		}
	}

}
