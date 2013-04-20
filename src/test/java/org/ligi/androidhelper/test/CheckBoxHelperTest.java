package org.ligi.androidhelper.test;

import android.widget.CheckBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AndroidHelper;
import org.robolectric.Robolectric;

import static org.junit.Assert.assertEquals;

@RunWith(Runner.class)
public class CheckBoxHelperTest {

    @Test
    public void checkIfCheckBoxesSync() {

        CheckBox checkBox1 = new CheckBox(Robolectric.application.getApplicationContext());

        AndroidHelper.at(checkBox1).careForCheckedStatePersistence("SAME_TAG");
        checkBox1.setChecked(true);

        CheckBox checkBox2 = new CheckBox(Robolectric.application.getApplicationContext());
        checkBox2.setChecked(false);

        AndroidHelper.at(checkBox2).careForCheckedStatePersistence("SAME_TAG");

        assertEquals(checkBox1.isChecked(), checkBox2.isChecked());

    }
}
