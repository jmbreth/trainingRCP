 
package com.cimpa.rental.eap.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class Handler1 {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "Tiiiiitttrree", "Message info");
	}
}