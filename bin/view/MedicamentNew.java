package view;

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

public class MedicamentNew extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MedicamentNew() {
		setBackground(Color.GRAY);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(54, 51, 49, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(45, 95, 96, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(197, 51, 49, 14);
		add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(211, 94, 30, 22);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(54, 165, 49, 14);
		add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(54, 200, 30, 22);
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(179, 200, 89, 23);
		add(btnNewButton);

	}
}
