package org.ligi.axt.test;

import android.widget.CheckBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.AXT;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class TheCheckBoxAXT {

    @Test
    public void should_sync() {

        CheckBox checkBox1 = new CheckBox(RuntimeEnvironment.application);

        AXT.at(checkBox1).careForCheckedStatePersistence("SAME_TAG");
        checkBox1.setChecked(true);

        CheckBox checkBox2 = new CheckBox(RuntimeEnvironment.application);
        checkBox2.setChecked(false);

        AXT.at(checkBox2).careForCheckedStatePersistence("SAME_TAG");

        assertEquals(checkBox1.isChecked(), checkBox2.isChecked());

    }
}
