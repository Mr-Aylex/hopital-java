package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import manager.Manager;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.util.Calendar;

public class RdvView extends JPanel {
	private String url = "jdbc:mysql://localhost/hopital?serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	private JTable table;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public RdvView() throws SQLException {
		setBackground(new Color(138, 43, 226));
		setLayout(null);
		
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
		refreshBtn.setBounds(23, 48, 89, 23);
		add(refreshBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 115, 344, 306);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		makeTable(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(412, 126, 231, 266);
		add(panel);
		panel.setLayout(null);
		
		JLabel Nomlabel = new JLabel("Nom");
		Nomlabel.setBounds(15, 27, 23, 14);
		panel.add(Nomlabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(15, 45, 86, 22);
		panel.add(comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1614034800000L), null, null, Calendar.DAY_OF_MONTH));
		spinner.setBounds(15, 78, 133, 20);
		panel.add(spinner);
	}
public Connection getJbdc() {
		
		try {
			Connection cnx = DriverManager.getConnection(this.url,this.user,this.password);
			System.out.print("Etat de la connexion :");
			System.out.print(cnx.isClosed()?"fermée":"ouverte \r\n");
			return cnx;
			
		} catch (SQLException e) {
			System.out.print("erreur");
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ArrayList> getRdv() throws SQLException {
		String sql = "SELECT utilisateur.nom, utilisateur.prenom, heure.nom_heure, date_rdv, motif.nom_motif FROM rdv"
				+ " INNER JOIN utilisateur ON utilisateur.id = rdv.id_patient INNER JOIN medecin ON rdv.id_medecin = medecin.id"
				+ " INNER JOIN motif ON rdv.id_motif = motif.id INNER JOIN heure ON rdv.heure_id = heure.id";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = pstm.executeQuery();
		ArrayList<ArrayList> array = new ArrayList<ArrayList>();
		ArrayList<String> subarray = new ArrayList<String>();
		while (rs.next()) {
			System.out.println(rs.getString("nom"));
			subarray.add(rs.getString("nom"));
			subarray.add(rs.getString("prenom"));
			subarray.add(rs.getString("date_rdv"));
			subarray.add(rs.getString("nom_heure"));
			subarray.add(rs.getString("nom_motif"));
			array.add(subarray);
		}
		return array;
		//
	}
	public void makeTable(JTable table) throws SQLException {
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		
		Manager manager = new Manager();
		ArrayList<ArrayList> array  = this.getRdv();
		
		String header[] = new String[] { "Nom", "Prenom", "Date", "Heure", "Motif" };
		
		dtm.setColumnIdentifiers(header);
		array.forEach((a) -> {
			dtm.addRow(new String[] {(String) a.get(0), (String) a.get(1), (String) a.get(2),  (String) a.get(3), (String) a.get(4)});
		});
		
		
		table.setBounds(44, 103, 430, 249);
		table.setModel(dtm);
		
	}
}
