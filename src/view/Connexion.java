package view;
import manager.Manager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Connexion {

	protected Shell shell;
	private Text loginInput;
	private Text passwdInput;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connexion window = new Connexion();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 332);
		shell.setText("SWT Application");
		
		Button Valider = new Button(shell, SWT.NONE);
		Valider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Manager manager = new Manager();
				try {
					System.out.println(loginInput.getText());
					System.out.println(passwdInput.getText());
					if(manager.loginUser(loginInput.getText(),passwdInput.getText())) {
						System.out.println("connécté");
						shell.close();
						Main a = new Main();
						a.main(null);
					}
					else {
						System.out.println("tu peux toujours essayer");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Valider.setBounds(164, 189, 105, 35);
		Valider.setText("Valider");
		
		loginInput = new Text(shell, SWT.BORDER);
		loginInput.setBounds(37, 107, 137, 31);
		
		passwdInput = new Text(shell, SWT.BORDER);
		passwdInput.setBounds(237, 107, 137, 31);
		
		Label lblIdentifiant = new Label(shell, SWT.NONE);
		lblIdentifiant.setBounds(37, 76, 81, 25);
		lblIdentifiant.setText("Identifiant");
		
		Label lblMotDePasse = new Label(shell, SWT.NONE);
		lblMotDePasse.setBounds(237, 76, 137, 25);
		lblMotDePasse.setText("Mot de passe");

	}
}
