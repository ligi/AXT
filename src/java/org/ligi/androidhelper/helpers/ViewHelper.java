package org.ligi.androidhelper.helpers;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewHelper {

    private final View view;

    public ViewHelper(View view) {
        this.view = view;
    }

    public <T extends View> T findById(int id) {
        return (T) view.findViewById(id);
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } else {
            Log.w("AndroidHelper","could not hide Keyboard as INPUT_METHOD_SERVICE is not available");
        }
    }

}