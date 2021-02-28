package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import manager.Manager;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newProfil extends JPanel {
	private JTable table;
	private JTextField nomTextField;
	private JTextField mailTextField;
	private JTextField prenomTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private Manager manager = new Manager();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public newProfil() throws SQLException {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 81, 426, 261);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(470, 81, 239, 261);
		add(panel);
		panel.setLayout(null);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(10, 33, 86, 20);
		panel.add(nomTextField);
		nomTextField.setColumns(10);
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setBounds(10, 21, 46, 14);
		panel.add(nomLabel);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(10, 76, 86, 20);
		panel.add(mailTextField);
		mailTextField.setColumns(10);
		
		JLabel mailLabel = new JLabel("Mail");
		mailLabel.setBounds(10, 64, 46, 14);
		panel.add(mailLabel);
		
		JLabel prenomLabel = new JLabel("Prenom");
		prenomLabel.setBounds(139, 21, 46, 14);
		panel.add(prenomLabel);
		
		prenomTextField = new JTextField();
		prenomTextField.setBounds(139, 33, 86, 20);
		panel.add(prenomTextField);
		prenomTextField.setColumns(10);
		
		JLabel mdpPassword = new JLabel("Mot de passe");
		mdpPassword.setBounds(139, 64, 86, 14);
		panel.add(mdpPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 76, 86, 20);
		panel.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(139, 117, 86, 20);
		panel.add(passwordField2);
		
		JLabel mdp2Label = new JLabel("Mot de passe 2");
		mdp2Label.setBounds(139, 105, 86, 14);
		panel.add(mdp2Label);
		
		JComboBox roleComboBox = new JComboBox();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"Utilisateur", "Gestion de stock"}));
		roleComboBox.setBounds(10, 116, 119, 22);
		panel.add(roleComboBox);
		
		JButton validerBtn = new JButton("Valider");
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passwordField.getText().equals(passwordField2.getText())) {
					try {
						manager.insertUser(nomTextField.getText(), prenomTextField.getText(), mailLabel.getText(), passwordField.getText(), roleComboBox.getSelectedItem().toString());
						makeTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		validerBtn.setBounds(72, 158, 89, 23);
		panel.add(validerBtn);
		
		
		this.makeTable();
	}
	
	public void makeTable() throws SQLException {
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		
		ArrayList<ArrayList> array  = manager.selectUser();
		
		String header[] = new String[] { "Nom", "Prenom", "Mail", "Type" };
		
		dtm.setColumnIdentifiers(header);
		
		array.forEach((a) -> {
			dtm.addRow(new String[] {(String) a.get(0), (String) a.get(1), (String) a.get(2), (String) a.get(4)});
		});
		table.setBounds(44, 103, 430, 249);
		table.setModel(dtm);
		
	}
}
