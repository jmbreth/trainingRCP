
package com.cimpa.rental.ui.parts;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

public class AgencyPart {

	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency, IEclipseContext context,
			ESelectionService selectionService) {
		// RentalProvider p = new RentalProvider();
		RentalProvider p = ContextInjectionFactory.make(RentalProvider.class, context);

		TreeViewer tv = new TreeViewer(parent);
		tv.setContentProvider(p);
		tv.setLabelProvider(p);

		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(agency);
		tv.setInput(agencies);

		tv.expandAll();
		tv.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {

				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(sel.size() == 1 ? sel.getFirstElement() : sel.toArray());

			}
		});
	}

	@Focus
	public void onFocus() {

	}

}