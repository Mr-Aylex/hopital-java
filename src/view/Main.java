package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.forms.widgets.SWTUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class Main {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
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
		shell.setSize(645, 468);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewRadiobutton = new MenuItem(menu, SWT.RADIO);
		mntmNewRadiobutton.setText("New RadioButton");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("New Item");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("New Item");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("New Item");
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem connexionItem = new MenuItem(menu, SWT.NONE);
		connexionItem.setText("New Item");
		connexionItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("coucou");
				//shell.
				
			}
		});
		Composite leftComposite = new Composite(shell, SWT.NONE);
		FormData fd_leftComposite = new FormData();
		fd_leftComposite.bottom = new FormAttachment(100, -10);
		fd_leftComposite.top = new FormAttachment(0, 5);
		fd_leftComposite.left = new FormAttachment(0, 5);
		fd_leftComposite.right = new FormAttachment(0, 120);
		leftComposite.setLayoutData(fd_leftComposite);
		formToolkit.adapt(leftComposite);
		formToolkit.paintBordersFor(leftComposite);
		leftComposite.setLayout(new GridLayout(1, false));
		
		Composite medicamentNew = new medicamentNew(shell, SWT.NONE);
		FormData fd_medicamentNew = new FormData();
		fd_medicamentNew.top = new FormAttachment(leftComposite, 0, SWT.TOP);
		fd_medicamentNew.left = new FormAttachment(leftComposite, 6);
		fd_medicamentNew.right = new FormAttachment(100, -10);
		fd_medicamentNew.bottom = new FormAttachment(100, -10);
		medicamentNew.setLayoutData(fd_medicamentNew);
		formToolkit.adapt(medicamentNew);
		formToolkit.paintBordersFor(medicamentNew);
		medicamentNew.setVisible(false);

		Composite mainComposite = new Composite(shell, SWT.NONE);
		FormData fd_mainComposite = new FormData();
		fd_mainComposite.top = new FormAttachment(leftComposite, 0, SWT.TOP);
		fd_mainComposite.left = new FormAttachment(leftComposite, 6);
		fd_mainComposite.right = new FormAttachment(100, -10);
		fd_mainComposite.bottom = new FormAttachment(100, -10);
		mainComposite.setLayoutData(fd_mainComposite);
		formToolkit.adapt(mainComposite);
		formToolkit.paintBordersFor(mainComposite);
		mainComposite.setVisible(false);
		
		Composite medicamentTable = new medicamentTable(shell, SWT.NONE);
		FormData fd_medicamentTable = new FormData();
		fd_medicamentTable.top = new FormAttachment(leftComposite, 0, SWT.TOP);
		fd_medicamentTable.left = new FormAttachment(leftComposite, 6);
		fd_medicamentTable.right = new FormAttachment(100, -10);
		fd_medicamentTable.bottom = new FormAttachment(100, -10);
		medicamentTable.setLayoutData(fd_medicamentTable);
		formToolkit.adapt(medicamentTable);
		formToolkit.paintBordersFor(medicamentTable);
		medicamentTable.setVisible(false);
		
		Composite rdvNew = new rdvNew(shell, SWT.NONE);
		FormData fd_rdvNew = new FormData();
		fd_rdvNew.top = new FormAttachment(leftComposite, 0, SWT.TOP);
		fd_rdvNew.left = new FormAttachment(leftComposite, 6);
		fd_rdvNew.right = new FormAttachment(100, -10);
		fd_rdvNew.bottom = new FormAttachment(100, -10);
		rdvNew.setLayoutData(fd_rdvNew);
		formToolkit.adapt(rdvNew);
		formToolkit.paintBordersFor(rdvNew);
		rdvNew.setVisible(false);
		
		Composite rdvTable = new rdvTable(shell, SWT.NONE);
		FormData fd_rdvTable = new FormData();
		fd_rdvTable.top = new FormAttachment(leftComposite, 0, SWT.TOP);
		fd_rdvTable.left = new FormAttachment(leftComposite, 6);
		fd_rdvTable.right = new FormAttachment(100, -10);
		fd_rdvTable.bottom = new FormAttachment(100, -10);
		rdvTable.setLayoutData(fd_rdvTable);
		formToolkit.adapt(rdvTable);
		formToolkit.paintBordersFor(rdvTable);
		rdvTable.setVisible(false);
		
		Composite utilisateurUpdate = new utilisateurUpdate(shell, SWT.NONE);
		FormData fd_utilisateurUpdate = new FormData();
		fd_utilisateurUpdate.top = new FormAttachment(leftComposite, 0, SWT.TOP);
		fd_utilisateurUpdate.left = new FormAttachment(leftComposite, 6);
		fd_utilisateurUpdate.right = new FormAttachment(100, -10);
		fd_utilisateurUpdate.bottom = new FormAttachment(100, -10);
		utilisateurUpdate.setLayoutData(fd_utilisateurUpdate);
		formToolkit.adapt(utilisateurUpdate);
		formToolkit.paintBordersFor(utilisateurUpdate);
		utilisateurUpdate.setVisible(false);
		
		

		Button medicamentBouton = new Button(leftComposite, SWT.NONE);
		GridData gd_medicamentBouton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_medicamentBouton.widthHint = 80;
		medicamentBouton.setLayoutData(gd_medicamentBouton);
		medicamentBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mainComposite.setVisible(false);
				rdvNew.setVisible(false);
				medicamentNew.setVisible(false);
				medicamentTable.setVisible(false);
				utilisateurUpdate.setVisible(false);
				rdvTable.setVisible(true);
				rdvNew.setVisible(false);
				
				
			}
		});
		formToolkit.adapt(medicamentBouton, true, true);
		medicamentBouton.setText("M\u00E9dicament");
		
		
		
		Button rdvBouton = new Button(leftComposite, SWT.NONE);
		rdvBouton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mainComposite.setVisible(false);
				medicamentNew.setVisible(false);
				medicamentTable.setVisible(false);
				utilisateurUpdate.setVisible(false);
				rdvTable.setVisible(false);
				rdvNew.setVisible(true);
			}
		});
		formToolkit.adapt(rdvBouton, true, true);
		rdvBouton.setText("Rendez-vous");
		
		Button btnNewButton_2 = new Button(leftComposite, SWT.NONE);
		GridData gd_btnNewButton_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_2.widthHint = 80;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		formToolkit.adapt(btnNewButton_2, true, true);
		btnNewButton_2.setText("New Button");
		
		Button btnNewButton_3 = new Button(leftComposite, SWT.NONE);
		GridData gd_btnNewButton_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_3.widthHint = 80;
		btnNewButton_3.setLayoutData(gd_btnNewButton_3);
		formToolkit.adapt(btnNewButton_3, true, true);
		btnNewButton_3.setText("New Button");
		

	}
}
