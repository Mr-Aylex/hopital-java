package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class rdvTable extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public rdvTable(Composite parent, int style) {
		super(parent, style);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(49, 92, 241, 61);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnTest = new TableColumn(table, SWT.NONE);
		tblclmnTest.setWidth(100);
		tblclmnTest.setText("test1");
		
		TableColumn tblclmnTest_1 = new TableColumn(table, SWT.NONE);
		tblclmnTest_1.setWidth(100);
		tblclmnTest_1.setText("test2");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
