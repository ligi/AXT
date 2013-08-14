package org.ligi.androidhelper.test;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AndroidHelper;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Fail.fail;

@RunWith(RobolectricTestRunner.class)
public class TheEditTextHelper {
    private final static String TEXT2SET="foobar";

    @Test
    public void should_set_text_when_needed() {

        EditText editText = new EditText(Robolectric.application);

        AndroidHelper.at(editText).changeTextIfNeeded(TEXT2SET);

        assertEquals(editText.getText().toString(),TEXT2SET);
    }


    @Test
    public void should_not_set_text_when_not_needed() {

        EditText editText = new EditText(Robolectric.application);

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
