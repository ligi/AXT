package org.ligi.axt.test;

import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.AXT;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheViewAXT {

    @Test
    public void should_correctly_set_visibility() {
        View v = new View(Robolectric.application);

        AXT.at(v).setVisibility(false);

        assertThat(v.getVisibility()).isEqualTo(View.GONE);

        AXT.at(v).setVisibility(true);

        assertThat(v.getVisibility()).isEqualTo(View.VISIBLE);

        AXT.at(v).setVisibility(false, View.INVISIBLE);

        assertThat(v.getVisibility()).isEqualTo(View.INVISIBLE);
    }

}
