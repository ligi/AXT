package org.ligi.androidhelper.test;

import android.widget.CheckBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AXT;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class TheCheckBoxHelper {

    @Test
    public void should_sync() {

        CheckBox checkBox1 = new CheckBox(Robolectric.application);

        AXT.at(checkBox1).careForCheckedStatePersistence("SAME_TAG");
        checkBox1.setChecked(true);

        CheckBox checkBox2 = new CheckBox(Robolectric.application);
        checkBox2.setChecked(false);

        AXT.at(checkBox2).careForCheckedStatePersistence("SAME_TAG");

        assertEquals(checkBox1.isChecked(), checkBox2.isChecked());

    }
}
