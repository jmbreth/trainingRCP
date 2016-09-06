 
package com.cimpa.rental.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.cimpa.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPart {
	private Label rentedObjectLabel;
	private Label loueA;
	private Label custLabel;



	@Inject
	public RentalPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("AAAAAA");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		rentedObjectLabel.setText("Perceuse Electrique");	
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		rentedObjectLabel.setLayoutData(gd);
		
		loueA = new Label(infoGroup, SWT.NONE);
		loueA.setText("Loué a :");
		
		custLabel = new Label(infoGroup, SWT.NONE);
		custLabel.setText("John Wayne");

		
		setRental(RentalCoreActivator.getAgency().getRentals().get(2));
	}
	
	public void setRental (Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		custLabel.setText(r.getCustomer().getDisplayName());
	}
	
	@Focus
	public void onFocus() {
		
	}
	
	
}