package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import manager.Manager;

public class medicamentNew extends Composite {
	private Text nomInput;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public medicamentNew(Composite parent, int style) {
		super(parent, style);
		
		nomInput = new Text(this, SWT.BORDER);
		nomInput.setBounds(73, 94, 129, 31);
		
		Label lblNom = new Label(this, SWT.NONE);
		lblNom.setBounds(73, 62, 81, 25);
		lblNom.setText("Nom");
		
		Combo toxiciteCombo = new Combo(this, SWT.NONE);
		toxiciteCombo.setItems(new String[] {"L\u00E9g\u00E8re", "Mod\u00E9r\u00E9", "Intense"});
		toxiciteCombo.setBounds(263, 94, 104, 33);
		
		Label lblToxicit = new Label(this, SWT.NONE);
		lblToxicit.setBounds(267, 62, 81, 25);
		lblToxicit.setText("Toxicit\u00E9");
		
		Spinner nombreSpinner = new Spinner(this, SWT.BORDER);
		nombreSpinner.setBounds(73, 178, 72, 31);
		
		Label lblNombreDunit = new Label(this, SWT.NONE);
		lblNombreDunit.setBounds(73, 147, 129, 25);
		lblNombreDunit.setText("Nombre d'unit\u00E9");
		
		Button btnValider = new Button(this, SWT.NONE);
		btnValider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(nomInput.getText());
				System.out.println(toxiciteCombo.getText());
				System.out.println(Integer.parseInt(nombreSpinner.getText()));
				
				if (toxiciteCombo.getText() != "Choissicé la toxicité" && nomInput.getText() != "" && Integer.parseInt(nombreSpinner.getText()) >= 1) {
					System.out.println("valider");
					Manager manager = new Manager();
					try {
						manager.insertMedic(nomInput.getText(),toxiciteCombo.getText(), Integer.parseInt(nombreSpinner.getText()));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					System.out.println("Non");
				}
			}
		});
		btnValider.setBounds(183, 226, 105, 35);
		btnValider.setText("Valider");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
