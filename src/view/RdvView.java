package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import entity.Medicaments;
import manager.Manager;
import global.variableGlobal;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Map;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.SystemColor;

public class RdvView extends JPanel {

	private ArrayList<ArrayList> heureDispo;
	private int idHeure = 0;
	private JTable table;
	private JTable table_1;
	private JComboBox nomComboBox;
	private JComboBox dateComboBox;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField mailTextField;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public RdvView() throws SQLException {
		setBackground(SystemColor.textHighlight);
		setLayout(null);
		System.out.println("rdvView");
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.removeAll();
				try {
					makeTable(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshBtn.setBounds(23, 81, 89, 23);
		add(refreshBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 115, 344, 306);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		makeTable(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(377, 115, 359, 306);
		add(panel);
		panel.setLayout(null);
		
		JLabel Nomlabel = new JLabel("Medecin");
		Nomlabel.setBounds(159, 25, 86, 14);
		panel.add(Nomlabel);
		Manager manager = new Manager();
		Map<String,String> medecin = manager.selectMedecin();
		ArrayList<String> nomMedecin = new ArrayList<String>();
		if (variableGlobal.getUser().getRole_user()=="medecin") {
			
		}
		int j = 0;
		for (String i : medecin.keySet()) {
			nomMedecin.add(medecin.get(i));
			j++;
		}
		nomComboBox = new JComboBox();
		nomComboBox.setModel(new DefaultComboBoxModel(nomMedecin.toArray()));
		nomComboBox.setBounds(159, 43, 104, 22);
		panel.add(nomComboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 89, 165, 163);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu");
		LocalDate now = LocalDate.now().plusDays(1);
		
		ArrayList<String> date = new ArrayList<String>();
		dateComboBox = new JComboBox();
		dateComboBox.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent  e) {
				table_1.removeAll();
				try {
					makeTableRdv();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		for(int i = 0;i<15;i++) {
			date.add(now.format(format));
			now = now.plusDays(1);
		}
		dateComboBox.setModel(new DefaultComboBoxModel(date.toArray()));
		dateComboBox.setBounds(33, 43, 104, 22);
		panel.add(dateComboBox);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(33, 31, 86, 14);
		panel.add(dateLabel);
		
		JButton rendezVousBtn = new JButton("Valider");
		rendezVousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = new Manager();
				
				int col = table_1.getSelectedColumn();
				int row = table_1.getSelectedRow();
				String o = (String)table_1.getValueAt(row, col);
				idHeure = 0;
				System.out.println(heureDispo);
				heureDispo.forEach((h) -> {
					if(h.get(1) == o) {
						idHeure = Integer.valueOf((String) h.get(0));
					}
				});
				Map<String, String> medecin;
				String id = "0";
				try {
					medecin = manager.selectMedecin();
					ArrayList<String> nomMedecin = new ArrayList<String>();
					
					for (String i : medecin.keySet()) {
						if(nomComboBox.getSelectedItem().toString().equals(medecin.get(i))) {
							id = i;
						}
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				System.out.println(o);
				
				try {
					manager.insertRdv(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText(), Integer.valueOf(id), dateComboBox.getSelectedItem().toString(), idHeure);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.removeAll();
				try {
					makeTable(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rendezVousBtn.setBounds(208, 229, 86, 23);
		panel.add(rendezVousBtn);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(208, 87, 86, 20);
		panel.add(nomTextField);
		nomTextField.setColumns(10);
		
		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setBounds(208, 76, 46, 14);
		panel.add(nomLabel);
		
		prenomTextField = new JTextField();
		prenomTextField.setBounds(208, 130, 86, 20);
		panel.add(prenomTextField);
		prenomTextField.setColumns(10);
		
		JLabel prenomNewLabel = new JLabel("Prenom");
		prenomNewLabel.setBounds(208, 116, 46, 14);
		panel.add(prenomNewLabel);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(208, 172, 86, 20);
		panel.add(mailTextField);
		mailTextField.setColumns(10);
		
		JLabel mailNewLabel = new JLabel("Mail");
		mailNewLabel.setBounds(208, 159, 46, 14);
		panel.add(mailNewLabel);
	}

	
	public void makeTable(JTable table) throws SQLException {
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		Manager manager = new Manager();
		
		Map<String, String> medecin;
		String id = "0";
		medecin = manager.selectMedecin();
		ArrayList<String> nomMedecin = new ArrayList<String>();
		
		for (String i : medecin.keySet()) {
			if(variableGlobal.getUser().getNom().equals(medecin.get(i))) {
				id = i;
			}
		}
		System.out.println(variableGlobal.getUser().getNom());
		
		System.out.println(id);
		ArrayList<ArrayList> array  = manager.getRdv(id);
		
		String header[] = new String[] { "Nom", "Prenom", "Date", "Heure", "Motif" };
		
		dtm.setColumnIdentifiers(header);
		array.forEach((a) -> {
			dtm.addRow(new String[] {(String) a.get(0), (String) a.get(1), (String) a.get(2),  (String) a.get(3), (String) a.get(4)});
		});
		
		
		table.setBounds(44, 103, 430, 249);
		table.setModel(dtm);
		
	}
	public void makeTableRdv() throws SQLException {
		Manager manager = new Manager();
		
		Map<String,String> medecin = manager.selectMedecin();
		ArrayList<String> nomMedecin = new ArrayList<String>();
		String id = "0";
		for (String i : medecin.keySet()) {
			if(nomComboBox.getSelectedItem().toString().equals(medecin.get(i))) {
				id = i;
			}
		}
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		heureDispo  = manager.selectHeureDispo(id,dateComboBox.getSelectedItem().toString());
		//SELECT nom , medecin.id FROM utilisateur INNER JOIN medecin ON medecin.id_user = utilisateur.id
		//SELECT * FROM heure WHERE id not in (SELECT heure_id FROM rdv WHERE id_medecin=:id_medecin AND date_rdv = :date_rdv
		 
		String header[] = new String[] { "Heure" };
		
		dtm.setColumnIdentifiers(header);
		heureDispo.forEach((a) -> {
			dtm.addRow(new String[] {(String) a.get(1)});
		});
		
		
		table_1.setBounds(44, 103, 430, 249);
		table_1.setModel(dtm);
	}
	//public Object getHeureId(ArrayList<ArrayList> array, String o) {
		
	//}
}
