package com.cimpa.rental.ui.parts;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.cimpa.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {

	@Inject
	@Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;

	@Inject
	@Named(RENTAL_UI_PREF_STORE)
	private IPreferenceStore prefStore ;

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>)
			return ((Collection<?>) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		/*
		 * if (parentElement instanceof RentalAgency) return ((RentalAgency)
		 * parentElement).getCustomers().toArray();
		 */
		if (parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] { new Node(Node.CUSTOMER, a), new Node(Node.RENTAL, a), new Node(Node.OBJETCS, a),

			};
		} else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}

		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof Node || element instanceof RentalAgency;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency) element).getName();
		if (element instanceof Customer)
			return ((Customer) element).getDisplayName();
		if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);
	}

	class Node {
		public static final String CUSTOMER = "Customer";
		public static final String RENTAL = "Rental";
		public static final String OBJETCS = "Objetcs";
		private String name;
		private RentalAgency a;

		public Node(String name, RentalAgency a) {
			super();
			this.name = name;
			this.a = a;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name;
		}

		public Object[] getChildren() {
			if (CUSTOMER.equals(name))
				return a.getCustomers().toArray();
			else if (RENTAL.equals(name))
				return a.getRentals().toArray();
			else if (OBJETCS.equals(name))
				return a.getObjectsToRent().toArray();
			return null;
		}
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			return getAColor(prefStore.getString(PREF_CUSTOMER_COLOR));
		}if (element instanceof Rental) {
			return getAColor(prefStore.getString(PREF_RENTAL_COLOR));
		}if (element instanceof RentalObject) {
			return getAColor(prefStore.getString(PREF_RENTAL_OBJECT_COLOR));
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Color getAColor (String rgbKey) {
		ColorRegistry cr = JFaceResources.getColorRegistry();
		
		Color col = cr.get(rgbKey);
		if (col == null) {
			cr.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = cr.get(rgbKey);
		}
		return col;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Customer) {
			return registry.get(IMG_CUSTOMER);
		}
		return super.getImage(element);
	}

}
