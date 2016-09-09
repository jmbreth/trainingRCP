package com.cimpa.rental.ui.prefs;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.cimpa.rental.ui.PaletteDesc;
import com.cimpa.rental.ui.RentalUIConstants;

public class PalettePrefPage extends FieldEditorPreferencePage implements RentalUIConstants {

	@Inject @Named(PALETTE_MANAGER)
	Map<String, PaletteDesc> paletteManager;

	public PalettePrefPage() {
		super(GRID);
	}

	@Override
	protected void createFieldEditors() {
		String[][] entryNamesAndValues = new String[paletteManager.size()][2];
		int i = 0;
		for (PaletteDesc pl : paletteManager.values()) {
			entryNamesAndValues[i][0] = pl.getName();
			entryNamesAndValues[i][1] = pl.getId();
			i++;
		}
		addField(new ComboFieldEditor(PREF_PALETTE, "palette", entryNamesAndValues, getFieldEditorParent()));
	}

}
