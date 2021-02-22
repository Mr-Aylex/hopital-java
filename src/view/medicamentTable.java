package view;
import manager.Manager;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MedicamentTable extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public MedicamentTable() throws SQLException {
		setBackground(Color.ORANGE);
		setLayout(null);
		table = new JTable();
		makeTable(table);
		add(table);
		
		Button refreshBtn = new Button("Refresh");
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
		refreshBtn.setBounds(73, 61, 67, 21);
		add(refreshBtn);

	}
	public void makeTable(JTable table) throws SQLException {
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
