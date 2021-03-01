package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import manager.Manager;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
	private JTextField lieuTextField;
	private JTextField telTexfield;

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
		
		JPanel panelMedecin = new JPanel();
		panelMedecin.setBounds(470, 81, 279, 279);
		add(panelMedecin);
		panelMedecin.setLayout(null);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(10, 33, 86, 20);
		panelMedecin.add(nomTextField);
		nomTextField.setColumns(10);
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setBounds(10, 21, 46, 14);
		panelMedecin.add(nomLabel);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(10, 76, 86, 20);
		panelMedecin.add(mailTextField);
		mailTextField.setColumns(10);
		
		JLabel mailLabel = new JLabel("Mail");
		mailLabel.setBounds(10, 64, 46, 14);
		panelMedecin.add(mailLabel);
		
		JLabel prenomLabel = new JLabel("Prenom");
		prenomLabel.setBounds(139, 21, 46, 14);
		panelMedecin.add(prenomLabel);
		
		prenomTextField = new JTextField();
		prenomTextField.setBounds(139, 33, 86, 20);
		panelMedecin.add(prenomTextField);
		prenomTextField.setColumns(10);
		
		JLabel mdpPassword = new JLabel("Mot de passe");
		mdpPassword.setBounds(139, 64, 86, 14);
		panelMedecin.add(mdpPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 76, 86, 20);
		panelMedecin.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(139, 117, 86, 20);
		panelMedecin.add(passwordField2);
		
		JLabel mdp2Label = new JLabel("Mot de passe 2");
		mdp2Label.setBounds(139, 105, 86, 14);
		panelMedecin.add(mdp2Label);
		
		JComboBox roleComboBox = new JComboBox();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"utilisateur", "gestion de stock","medecin"}));
		roleComboBox.setBounds(10, 116, 119, 22);
		panelMedecin.add(roleComboBox);
		
		JButton validerBtn = new JButton("Valider");
		
		validerBtn.setBounds(80, 245, 89, 23);
		panelMedecin.add(validerBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 142, 219, 92);
		//panelMedecin.add(panel_1);
		//panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Sp\u00E9tialit\u00E9");
		lblNewLabel_2.setBounds(0, 11, 122, 14);
		panel_1.add(lblNewLabel_2);
		Map<String, String> spes = manager.selectSpetialite();
		ArrayList<String> spe = new ArrayList<String>();
		int j = 0;
		for (String i : spes.keySet()) {
			spe.add(spes.get(i));
			j++;
		}
		JComboBox spetialiteComboBox = new JComboBox();
		spetialiteComboBox.setModel(new DefaultComboBoxModel(spe.toArray()));
		spetialiteComboBox.setBounds(3, 24, 119, 23);
		panel_1.add(spetialiteComboBox);
		
		lieuTextField = new JTextField();
		lieuTextField.setColumns(10);
		lieuTextField.setBounds(3, 69, 96, 20);
		panel_1.add(lieuTextField);
		
		JLabel lblNewLabel_1 = new JLabel("Lieu");
		lblNewLabel_1.setBounds(3, 55, 49, 14);
		panel_1.add(lblNewLabel_1);
		
		telTexfield = new JTextField();
		telTexfield.setColumns(10);
		telTexfield.setBounds(132, 27, 86, 20);
		panel_1.add(telTexfield);
		
		JLabel lblNewLabel = new JLabel("T\u00E9l\u00E9phone");
		lblNewLabel.setBounds(132, 13, 86, 14);
		panel_1.add(lblNewLabel);
		
		
		this.makeTable();
		roleComboBox.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent  e) {
				if(roleComboBox.getSelectedItem().toString()=="medecin") {
					panelMedecin.add(panel_1);
					panel_1.setLayout(null);
					panelMedecin.repaint();
				}
				else {
					panelMedecin.remove(panel_1);
					panelMedecin.repaint();
				}
				
			}
		});
		
		validerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roleComboBox.getSelectedItem().toString()=="medecin") {
					try {
						Map<String, String> spes = manager.selectSpetialite();
						ArrayList<String> nomMedecin = new ArrayList<String>();
						String id = "0";
						for (String i : spes.keySet()) {
							if(spetialiteComboBox.getSelectedItem().toString().equals(spes.get(i))) {
								id = i;
							}
						}
					 manager.insertMedecin(nomTextField.getText(), prenomTextField.getText(), mailLabel.getText(), passwordField.getText(),telTexfield.getText(), lieuTextField.getText(), id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
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
				
				try {
					makeTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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
