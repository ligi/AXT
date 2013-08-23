package org.ligi.androidhelper.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AndroidHelper;
import org.robolectric.RobolectricTestRunner;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheArrayHelper {

    @Test
    public void should_proper_combine_arrays() {
        Byte[] test=AndroidHelper.at(new Byte[] { 23,5}).combineWith(new Byte[] { 42 ,6});

        assertThat(test).isEqualTo(new Byte[] { 23,5,42,6});
    }

}
