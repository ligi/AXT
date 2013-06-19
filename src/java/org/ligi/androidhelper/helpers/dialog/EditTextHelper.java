package org.ligi.androidhelper.helpers.dialog;

import android.widget.EditText;

/**
 * Created by ligi on 6/20/13.
 */
public class EditTextHelper {

    private final EditText mEditText;


    public EditTextHelper(EditText editText) {
        mEditText = editText;
    }

    public void changeTextIfNeeded(String text) {
        if (mEditText.getText().toString().equals(text)) {
            return;
        }

        mEditText.setText(text);
    }
}
