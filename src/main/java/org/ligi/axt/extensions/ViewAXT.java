package org.ligi.axt.extensions;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewAXT {

    private final View view;

    public ViewAXT(View view) {
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
            Log.w("ViewAXT", "could not hide Keyboard as INPUT_METHOD_SERVICE is not available");
        }
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        } else {
            Log.w("ViewAXT", "could not show Keyboard as INPUT_METHOD_SERVICE is not available");
        }
    }

    /**
     * @param visible - when true view is set to VISIBLE - if false GONE
     */
    public void setVisibility(boolean visible) {
        setVisibility(visible, View.GONE);
    }

    /**
     * @param visible - when true view is set to VISIBLE - if false to @param hiddenVisibility
     */
    public void setVisibility(boolean visible, int hiddenVisibility) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(hiddenVisibility);
        }
    }

    public void startIntentOnClick(final Intent intent) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(intent);
            }
        });
    }
}
