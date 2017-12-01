package AdminGUIBars;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SRSTeachReg extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRSTeachReg frame = new SRSTeachReg();
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
	public SRSTeachReg() {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblThisIdHas = new JLabel("This Teacher has already registered into this class.");
		lblThisIdHas.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisIdHas.setForeground(Color.RED);
		lblThisIdHas.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(lblThisIdHas, BorderLayout.NORTH);
		
		button = new JButton("Close");

		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(button, BorderLayout.CENTER);
		
		button.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == button){
			this.dispose();
		}
		
	}

}
