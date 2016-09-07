 
package com.cimpa.rental.ui;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.cimpa.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalAddOn {

	@PostConstruct
	public void applicationStarted(IEclipseContext ctx) {
		ctx.set(RentalAgency.class, RentalCoreActivator.getAgency());
		
	}

}
