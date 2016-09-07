 
package com.cimpa.rental.ui.parts;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TreeViewer;

public class AgencyPart {
	
	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency) {
		RentalProvider p = new RentalProvider();
		TreeViewer tv = new TreeViewer(parent);
		tv.setContentProvider(p);
		tv.setLabelProvider(p);
		
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>(); 
		agencies.add(agency);
		tv.setInput(agencies);
		
		tv.expandAll();
	}
	
	
	
	@Focus
	public void onFocus() {
		
	}
	
	
}