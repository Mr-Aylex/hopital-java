package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 902, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 845, 25);
		frame.getContentPane().add(menuBar);
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 24, 888, 531);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 58, 781, 396);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(269, 142, 96, 20);
		panel.add(textField);
		
		JLabel identifiantLabel = new JLabel("Identifiant");
		identifiantLabel.setBounds(269, 126, 76, 14);
		panel.add(identifiantLabel);
		
		JLabel mdpLabel = new JLabel("Mot de passe");
		mdpLabel.setBounds(409, 126, 107, 14);
		panel.add(mdpLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(409, 142, 96, 20);
		panel.add(textField_1);
		
		JButton submitBtn = new JButton("Valider");
		submitBtn.setBounds(346, 200, 89, 23);
		panel.add(submitBtn);
		
		JButton btnNewButton_1 = new JButton("Rendez-Vous");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				try {
					mainPanel.add(new RdvView());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("M\u00E9dicament");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				try {
					mainPanel.add(new MedicamentTable());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		menuBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Nouveau M\u00E9dicament");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				mainPanel.add(new MedicamentNew());
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		menuBar.add(btnNewButton_3);
		
		JButton profilBtn = new JButton("Profils");
		profilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				try {
					mainPanel.add(new newProfil());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		});
		menuBar.add(profilBtn);
	}
}
