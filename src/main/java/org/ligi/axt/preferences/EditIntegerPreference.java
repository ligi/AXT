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

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

/**
 * a Preference to edit a integer !Warning untested!
 */
public class EditIntegerPreference extends EditTextPreference {
    public EditIntegerPreference(Context context) {
        super(context);
    }

    public EditIntegerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditIntegerPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public String getText() {
        return String.valueOf(getSharedPreferences().getInt(getKey(), 0));
    }

    @Override
    public void setText(String text) {
        getSharedPreferences().edit().putInt(getKey(), Integer.parseInt(text)).commit();
    }
}