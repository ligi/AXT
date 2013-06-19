package org.ligi.androidhelper.test;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AndroidHelper;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class EditTextHelperTest {

    private final static String TEXT2SET="foobar";

    @Test
    public void checkIfTextIsSetCorrectly() {

        EditText editText = new EditText(Robolectric.application.getApplicationContext());

        AndroidHelper.at(editText).changeTextIfNeeded(TEXT2SET);

        assertEquals(editText.getText().toString(),TEXT2SET);
    }


    @Test
    public void checkIfSetIsPreventedWhenTextIsAlreadySet() {

        EditText editText = new EditText(Robolectric.application.getApplicationContext());

        editText.setText(TEXT2SET);

        // Assert - should not fire
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fail();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fail();
            }

            @Override
            public void afterTextChanged(Editable s) {
                fail();
            }
        });

        AndroidHelper.at(editText).changeTextIfNeeded(TEXT2SET);

    }
}
