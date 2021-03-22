package view;
import manager.Manager;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MedicamentTable extends JPanel {
	private JTable table;
	private JTextField champNom;
	private String nomInitial;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public MedicamentTable() throws SQLException {
		setBackground(new Color(135, 206, 250));
		setLayout(null);
		
		Button refreshBtn = new Button("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.removeAll();
				try {
					makeTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshBtn.setBounds(73, 61, 67, 21);
		add(refreshBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 116, 311, 230);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
				
		
		JPanel panel = new JPanel();
		panel.setBounds(357, 170, 122, 176);
		add(panel);
		panel.setLayout(null);
		
		champNom = new JTextField();
		champNom.setBounds(10, 11, 96, 20);
		panel.add(champNom);
		champNom.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Faible", "Modéré", "élevé", "Extrème"}));
		comboBox.setBounds(10, 53, 96, 22);
		panel.add(comboBox);
		
		JButton modifierBtn = new JButton("Modifier");
		modifierBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = new Manager();
				try {
					manager.updateMedic(champNom.getText(), (String) comboBox.getSelectedItem(), nomInitial);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nomInitial = champNom.getText();
			}
		});
		modifierBtn.setBounds(10, 112, 89, 23);
		panel.add(modifierBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(501, 170, 104, 176);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel mediLabel = new JLabel("M\u00E9dicament");
		mediLabel.setBounds(10, 22, 49, 14);
		panel_1.add(mediLabel);
		Manager manager = new Manager();
		ArrayList<String> listeMedic = manager.selectNomMedic();
		
		JComboBox mediComboBox = new JComboBox();
		mediComboBox.setModel(new DefaultComboBoxModel(listeMedic.toArray()));
		mediComboBox.setBounds(10, 36, 74, 22);
		panel_1.add(mediComboBox);
		
		JSpinner nombreSpinner = new JSpinner();
		nombreSpinner.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		nombreSpinner.setBounds(10, 81, 49, 20);
		panel_1.add(nombreSpinner);
		
		JButton btnNewButton = new JButton("Retirer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = new Manager();
				try {
					manager.getMedic(mediComboBox.getSelectedItem().toString(),Integer.parseInt(nombreSpinner.getValue().toString()));
					makeTable();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 124, 74, 23);
		panel_1.add(btnNewButton);
		makeTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) { 
					 JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               ArrayList<String> array = new ArrayList<String>(Arrays.asList(new String[] {"Faible", "Modéré", "élevé", "Extrème"}));
	               String nom = (String) table.getValueAt(row, 0);
	               String tox = (String) table.getValueAt(row, 1);
	               for (int i = 0; i < array.size(); i++) {
	            	   	if(array.get(i).equals(tox)) {
	            	   		comboBox.setSelectedIndex(i);
	            	   }
	               }
	               champNom.setText(nom);
	               nomInitial = nom;
				}
			}
		});
		
		mediComboBox.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent  e) {
				
				nombreSpinner.setModel(new SpinnerNumberModel(0, 0, 50, 1));
			}
		});
		
	}

	public void makeTable() throws SQLException {
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		Manager manager = new Manager();
		ArrayList<ArrayList> array  = manager.selectMedic();
		
		String header[] = new String[] { "Nom", "Toxicité", "Nombre" };
		
		dtm.setColumnIdentifiers(header);
		
		array.forEach((a) -> {
			dtm.addRow(new String[] {(String) a.get(0), (String) a.get(1), (String) String.valueOf(a.get(2))});
			
		});
		table.setBounds(44, 103, 430, 249);
		table.setModel(dtm);
	}
}
