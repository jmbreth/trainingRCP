package com.cimpa.rental.ui.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;

import com.cimpa.rental.ui.RentalUIConstants;
import com.opcoach.e4.preferences.ScopedPreferenceStore;

public class RentalPrefIni extends AbstractPreferenceInitializer implements RentalUIConstants {

	public RentalPrefIni() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
  IPreferenceStore ps = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
  
  ps.setDefault(PREF_CUSTOMER_COLOR, "200,200,200");
  ps.setDefault(PREF_RENTAL_OBJECT_COLOR, "200,10,28");
  ps.setDefault(PREF_RENTAL_COLOR, "70,50,200");
  
	}

}
