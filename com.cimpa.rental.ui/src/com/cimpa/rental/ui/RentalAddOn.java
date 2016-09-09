
package com.cimpa.rental.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.cimpa.rental.core.RentalCoreActivator;
import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalAddOn implements RentalUIConstants{

	private Map<String,PaletteDesc> paletteManager = new HashMap<String,PaletteDesc>();
	
	@PostConstruct
	public void applicationStarted(IEclipseContext ctx, IExtensionRegistry er) {
		ctx.set(RentalAgency.class, RentalCoreActivator.getAgency());
		ctx.set(RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		ctx.set(RENTAL_UI_PREF_STORE, new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID));

		readModExt(er);
		readPalettesExt(er,ctx);
	}
	/**
	 * A method to create and initialize an ImageRegistry
	 * @return a initialized ImageRegistry that can be put in the context
	 */
	ImageRegistry getLocalImageRegistry()
	{
		// Get the bundle using the universal method to get it from the current class
		Bundle b = FrameworkUtil.getBundle(getClass());  
		
		// Create a local image registry
		ImageRegistry reg = new ImageRegistry();

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));

		return reg;
	}

	@Inject @Optional
	void reachOnCustoEvent(@UIEventTopic("CustoCopy") Customer c) {
		System.out.print(">>> CustoCopy ");
		System.out.println(c.getDisplayName());
	}
	
	private void readModExt (IExtensionRegistry er) {
		for (IConfigurationElement e : er.getConfigurationElementsFor("org.eclipse.e4.workbench.model"))
		{
			if (e.getName().equals("fragment"))
			System.out.println("fragment : " + e.getAttribute("uri") + " venant de " + e.getNamespaceIdentifier());
			else if (e.getName().equals("processor"))
			System.out.println("processor : " + e.getAttribute("class")  + " venant de " + e.getNamespaceIdentifier());
		}
	}	
	
	private void readPalettesExt (IExtensionRegistry er, IEclipseContext ctx) {
		for (IConfigurationElement e : er.getConfigurationElementsFor("com.cimpa.rental.ui.palette"))
		{
			try{
			PaletteDesc pl = new PaletteDesc();
			pl.setId(e.getAttribute("id"));
			pl.setName(e.getAttribute("name"));
			
			Bundle b = Platform.getBundle(e.getNamespaceIdentifier());
			Class<?> cz = b.loadClass(e.getAttribute("paletteClass"));
			IColorProvider cp = (IColorProvider) ContextInjectionFactory.make(cz, ctx);
			
			pl.setProvider(cp);
			
			paletteManager.put(pl.getId(), pl);
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
