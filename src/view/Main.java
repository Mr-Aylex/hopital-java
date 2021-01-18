package view;

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
		
		Composite leftComposite = new Composite(shell, SWT.NONE);
		FormData fd_leftComposite = new FormData();
		fd_leftComposite.left = new FormAttachment(0, 5);
		fd_leftComposite.right = new FormAttachment(0, 120);
		fd_leftComposite.top = new FormAttachment(0, 5);
		leftComposite.setLayoutData(fd_leftComposite);
		formToolkit.adapt(leftComposite);
		formToolkit.paintBordersFor(leftComposite);
		leftComposite.setLayout(new GridLayout(1, false));
		
		Button btnNewButton = new Button(leftComposite, SWT.NONE);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("New Button");
		
		Combo combo = new Combo(leftComposite, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		Composite mainComposite = new Composite(shell, SWT.NONE);
		fd_leftComposite.bottom = new FormAttachment(mainComposite, 0, SWT.BOTTOM);
		FormData fd_mainComposite = new FormData();
		fd_mainComposite.right = new FormAttachment(100, -10);
		fd_mainComposite.left = new FormAttachment(leftComposite, 6);
		fd_mainComposite.top = new FormAttachment(0, 5);
		
		Button btnNewButton_1 = new Button(leftComposite, SWT.NONE);
		formToolkit.adapt(btnNewButton_1, true, true);
		btnNewButton_1.setText("New Button");
		
		Combo combo_1 = new Combo(leftComposite, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		fd_mainComposite.bottom = new FormAttachment(100, -10);
		mainComposite.setLayoutData(fd_mainComposite);
		formToolkit.adapt(mainComposite);
		formToolkit.paintBordersFor(mainComposite);

	}

}
