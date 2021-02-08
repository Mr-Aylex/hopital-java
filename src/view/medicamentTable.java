package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.TableItem;

public class medicamentTable extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public medicamentTable(Composite parent, int style) {
		super(parent, style);
		
		CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = checkboxTableViewer.getTable();
		table.setHeaderVisible(true);
		table.setBounds(116, 107, 400, 233);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnNom = tableViewerColumn.getColumn();
		tblclmnNom.setWidth(152);
		tblclmnNom.setText("Nom");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnToxicit = tableViewerColumn_1.getColumn();
		tblclmnToxicit.setWidth(113);
		tblclmnToxicit.setText("Toxicit\u00E9");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn_2.getColumn();
		tblclmnNombre.setWidth(119);
		tblclmnNombre.setText("Nombre");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("New TableItem");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
