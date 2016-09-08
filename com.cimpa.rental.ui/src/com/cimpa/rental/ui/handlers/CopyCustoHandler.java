
package com.cimpa.rental.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;

public class CopyCustoHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Customer r,
			IEventBroker eb) {

		//System.out.println("customer copie : " + r.getDisplayName());
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String textData = r.getDisplayName();
		String rtfData = "{\\rtf1\\b\\i " + r.getDisplayName() +"}";
		
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
		Object[] data = new Object[]{textData, rtfData};
		clipboard.setContents(data, transfers);
		clipboard.dispose();

		eb.send("CustoCopy", r);
	}


	@CanExecute
	public boolean canexecute(@Named(IServiceConstants.ACTIVE_SELECTION) Object r) {

		return  r instanceof Customer;

	}

}