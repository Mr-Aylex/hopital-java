package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

public class rdvNew extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public rdvNew(Composite parent, int style) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(53, 50, 80, 31);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(53, 119, 80, 31);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(53, 194, 80, 31);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
