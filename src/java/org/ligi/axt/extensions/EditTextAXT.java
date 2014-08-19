package org.ligi.axt.extensions;

import android.widget.EditText;

public class EditTextAXT extends ViewAXT {

    private final EditText mEditText;

    public EditTextAXT(EditText editText) {
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
