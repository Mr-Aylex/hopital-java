package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import manager.Manager;
import entity.Utilisateur;
import java.awt.Color;


public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField mailChamp;
	private JTextField mdpChamp;
	static Connexion frame = new Connexion();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public Connexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel identifiantLabel = new JLabel("Identifiant");
		identifiantLabel.setBounds(42, 59, 76, 14);
		contentPane.add(identifiantLabel);
		
		mailChamp = new JTextField();
		mailChamp.setBounds(42, 75, 96, 20);
		contentPane.add(mailChamp);
		mailChamp.setColumns(10);
		
		mdpChamp = new JTextField();
		mdpChamp.setBounds(182, 75, 96, 20);
		contentPane.add(mdpChamp);
		mdpChamp.setColumns(10);
		
		JLabel mdpLabel = new JLabel("Mot de passe");
		mdpLabel.setBounds(182, 59, 107, 14);
		contentPane.add(mdpLabel);
		
		JButton submitBtn = new JButton("Valider");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = new Manager();
				try {
					Utilisateur user = manager.loginUser(mailChamp.getText(),mdpChamp.getText());
					System.out.println(user);
					if(user != null) {
						frame.setVisible(false);
						Main2 prg = new Main2(new JPanel());
						prg.setUser(user);
						System.out.println(prg.getUser());
						prg.main(null);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		submitBtn.setBounds(106, 149, 89, 23);
		contentPane.add(submitBtn);
	}
}
