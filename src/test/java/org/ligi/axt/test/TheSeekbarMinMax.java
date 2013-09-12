package org.ligi.axt.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.views.SeekBarMinMax;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

@RunWith(RobolectricTestRunner.class)
public class TheSeekbarMinMax {

    private SeekBarMinMax tested;
    private int MIN=50;
    private int MAX=100;
    private int PROBE_VALID=75;


    @Before
    public void setup() {
        tested = new SeekBarMinMax(Robolectric.application, MIN, MAX);
    }

    @Test
    public void valid_should_survive_roundrip() {
        tested.setProgress(PROBE_VALID);

        assertThat(tested.getProgress()).isEqualTo(PROBE_VALID);

    }

    @Test
    public void below_min_should_clip_to_min() {
        tested.setProgress(0);

        assertThat(tested.getProgress()).isEqualTo(MIN);

    }

    @Test
    public void above_max_should_clip_to_max() {
        tested.setProgress(200);

        assertThat(tested.getProgress()).isEqualTo(MAX);

    }

    @Test
    public void should_fail_for_min_bigger_max() {
        try {
            tested=new SeekBarMinMax(Robolectric.application,100,0);
            fail("SeekBarMinMax was not throwing IllegalArgument for min>max");
        } catch (IllegalArgumentException e) {}

    }

}
