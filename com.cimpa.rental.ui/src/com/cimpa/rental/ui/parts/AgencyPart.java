
package com.cimpa.rental.ui.parts;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;

import com.cimpa.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.RentalAgency;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

public class AgencyPart implements RentalUIConstants {

	private TreeViewer tv;

	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency agency, IEclipseContext context,
			ESelectionService selectionService, EMenuService ms) {
		// RentalProvider p = new RentalProvider();
		RentalProvider p = ContextInjectionFactory.make(RentalProvider.class, context);

		tv = new TreeViewer(parent);
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
		ms.registerContextMenu(tv.getControl(), "rental.ui.agency.menu");

	}

	@Focus
	public void onFocus() {

	}

	@Inject
	void refresh(
			@Preference(value = PREF_CUSTOMER_COLOR) String c, 
			@Preference(value = PREF_RENTAL_OBJECT_COLOR) String o,
			@Preference(value = PREF_RENTAL_COLOR) String u) {
		if (tv != null)
			tv.refresh();
	}

}