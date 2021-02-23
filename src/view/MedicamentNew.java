package view;

import manager.Manager;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MedicamentNew extends JPanel {
	private JTextField nomChamp;

	/**
	 * Create the panel.
	 */
	public MedicamentNew() {
		setBackground(Color.GRAY);
		setLayout(null);
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setBounds(54, 70, 49, 14);
		add(nomLabel);
		
		nomChamp = new JTextField();
		nomChamp.setBounds(45, 95, 96, 20);
		add(nomChamp);
		nomChamp.setColumns(10);
		
		JLabel toxiciteLabel = new JLabel("Toxicit\u00E9");
		toxiciteLabel.setBounds(201, 70, 49, 14);
		add(toxiciteLabel);
		
		JComboBox toxiciteCombo = new JComboBox();
		toxiciteCombo.setModel(new DefaultComboBoxModel(new String[] {"Faible", "Mod\u00E9r\u00E9", "\u00E9lev\u00E9", "Extr\u00E8me"}));
		toxiciteCombo.setBounds(197, 94, 89, 22);
		add(toxiciteCombo);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(54, 126, 49, 14);
		add(lblNewLabel_2);
		

		JSpinner nombreSpinner = new JSpinner();
		nombreSpinner.setBounds(64, 151, 30, 20);
		add(nombreSpinner);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = new Manager();
				try {
					manager.insertMedic(nomChamp.getText(), toxiciteCombo.getSelectedItem().toString(), Integer.parseInt(nombreSpinner.getValue().toString()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(197, 147, 89, 23);
		add(btnNewButton);
		

	}
}
