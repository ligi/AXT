/*                                                                                                                          
 * This software is free software; you can redistribute it and/or modify                                                     
 * it under the terms of the GNU General Public License as published by                                                     
 * the Free Software Foundation; either version 3 of the License, or                                                        
 * (at your option) any later version.                                                                                      
 *                                                                                                                          
 * This program is distributed in the hope that it will be useful, but                                                      
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY                                               
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License                                                  
 * for more details.                                                                                                        
 *                                                                                                                          
 * You should have received a copy of the GNU General Public License along                                                  
 * with this program; if not, write to the Free Software Foundation, Inc.,                                                  
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA                                                                    
 */

package org.ligi.axt.preferences;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import java.util.Vector;

/**
 * Class to enable or disable preferences depending on the state of a CheckBoxPrefenece
 * If somebody has a idea for a better name for this class just let me know ;-)
 * <p/>
 * usage:
 * you construct this class with a CheckBoxPreference and then add the Preferences
 * where you want to toggle enable/disable depending on the CheckBox
 * via addPreference2SetEnable(Preference p)
 */
public class SetPreferenceEnabledByCheckBoxPreferenceState implements OnPreferenceChangeListener {

    private Vector<Preference> preference_vector;
    private CheckBoxPreference pref;

    public SetPreferenceEnabledByCheckBoxPreferenceState(CheckBoxPreference pref) {
        this.pref = pref;
        pref.setOnPreferenceChangeListener(this);
        preference_vector = new Vector<Preference>();
    }

    public SetPreferenceEnabledByCheckBoxPreferenceState addPreference2SetEnable(Preference new_p) {
        preference_vector.add(new_p);
        new_p.setEnabled(pref.isChecked());
        return this;
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        for (Preference p : preference_vector) {
            p.setEnabled((Boolean) newValue);
        }
        return true;
    }

}
