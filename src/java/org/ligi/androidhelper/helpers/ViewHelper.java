package org.ligi.androidhelper.helpers;

import android.view.View;

/**
 * Created by ligi on 6/23/13.
 */
public class ViewHelper {

    private final View view;

    public ViewHelper(View view) {
        this.view = view;
    }

    public <T extends View> T findById(int id) {
        return (T) view.findViewById(id);
    }

}
