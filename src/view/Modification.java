package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import manager.Manager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import entity.Medecin;
import entity.Utilisateur;
public class Modification extends JPanel {
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField mailTextField;
	private JTextField telTextField;
	private JTextField lieuTextField;

	/**
	 * Create the panel.
	 */
	public Modification(Utilisateur user) {
		String roleUser = user.getRole_user();
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(82, 61, 719, 437);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(47, 50, 61, 14);
		panel.add(lblNewLabel);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(47, 75, 96, 20);
		panel.add(nomTextField);
		nomTextField.setColumns(10);
		nomTextField.setText(user.getNom());
		
		JLabel lblNewLabel_1 = new JLabel("Prenom");
		lblNewLabel_1.setBounds(180, 50, 49, 14);
		panel.add(lblNewLabel_1);
		
		prenomTextField = new JTextField();
		prenomTextField.setBounds(180, 75, 96, 20);
		panel.add(prenomTextField);
		prenomTextField.setColumns(10);
		prenomTextField.setText(user.getPrenom());
		
		JLabel lblNewLabel_2 = new JLabel("mail");
		lblNewLabel_2.setBounds(47, 122, 49, 14);
		panel.add(lblNewLabel_2);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(47, 147, 96, 20);
		panel.add(mailTextField);
		mailTextField.setColumns(10);
		mailTextField.setText(user.getMail());
		
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = new Manager();
				Utilisateur utilisateur = null;
				try {
					utilisateur = manager.updateUser(nomTextField.getText(),prenomTextField.getText(),mailTextField.getText(),user.getId());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(roleUser.equals("medecin")) {
					manager = new Manager();
					try {
						manager.updateMedecin(lieuTextField.getText(), telTextField.getText(), user.getId());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				setVisible(false);
				Main2 prg = new Main2(new JPanel());
				prg.setUser(utilisateur);
			}
			
		});
		btnNewButton.setBounds(47, 198, 89, 23);
		panel.add(btnNewButton);
		if(roleUser.equals("medecin")) {
			Manager manager = new Manager();
			Medecin medecin = null;
			try {
				medecin = manager.hydratMedecin(user.getId());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(178, 122, 139, 119);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			
			JLabel lblNewLabel_3 = new JLabel("T\u00E9l\u00E9phone");
			lblNewLabel_3.setBounds(10, 11, 51, 14);
			panel_1.add(lblNewLabel_3);
			
			telTextField = new JTextField();
			telTextField.setBounds(10, 36, 96, 20);
			panel_1.add(telTextField);
			telTextField.setColumns(10);
			telTextField.setText(medecin.getTelephone());
			
			JLabel lblNewLabel_4 = new JLabel("Lieu");
			lblNewLabel_4.setBounds(10, 69, 49, 14);
			panel_1.add(lblNewLabel_4);
			
			lieuTextField = new JTextField();
			lieuTextField.setBounds(10, 94, 96, 20);
			panel_1.add(lieuTextField);
			lieuTextField.setColumns(10);
			lieuTextField.setText(medecin.getLieu());
		}
	}
}
