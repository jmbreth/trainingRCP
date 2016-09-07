 
package com.cimpa.rental.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.cimpa.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPart {
	private Label rentedObjectLabel;
	private Label loueA;
	private Label custLabel;



	@Inject
	public RentalPart(Composite parent, RentalAgency agency) {
		postConstruct(parent, agency);
	}
	
	// @PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency) {
		parent.setLayout(new GridLayout(1, false));
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("AAAAAA");
		infoGroup.setLayout(new GridLayout(2, false));
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		rentedObjectLabel.setText("Perceuse Electrique");	
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace=true;
		gd.horizontalAlignment=SWT.FILL;
		
		rentedObjectLabel.setLayoutData(gd);

		
		loueA = new Label(infoGroup, SWT.NONE);
		loueA.setText("Loué a :");
		
		custLabel = new Label(infoGroup, SWT.NONE);
		custLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		custLabel.setText("John Wayne");

		
		//setRental(RentalCoreActivator.getAgency().getRentals().get(2));
		//setRental(agency.getRentals().get(0));
	}
	
	public void setRental (Rental r) {
		if (r == null) {
			rentedObjectLabel.setText("None");
			custLabel.setText("None");
		}
		else {
			rentedObjectLabel.setText(r.getRentedObject().getName());
			custLabel.setText(r.getCustomer().getDisplayName());
		}
	}
	
	@Focus
	public void onFocus() {
		
	}
	
	@Inject	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		setRental(r);
	}
}