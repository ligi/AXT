package org.ligi.androidhelper.helpers;

import android.widget.EditText;

public class EditTextHelper extends ViewHelper{

    private final EditText mEditText;

    public EditTextHelper(EditText editText) {
        super(editText);
        mEditText = editText;
    }

    public void changeTextIfNeeded(String text) {
        if (mEditText.getText().toString().equals(text)) {
            return;
        }

        mEditText.setText(text);
    }
}
