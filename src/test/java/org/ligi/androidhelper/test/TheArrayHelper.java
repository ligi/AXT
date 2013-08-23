package org.ligi.androidhelper.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AndroidHelper;
import org.robolectric.RobolectricTestRunner;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheArrayHelper {

    @Test
    public void should_proper_combine_byte_arrays() {
        Byte[] test=AndroidHelper.at(new Byte[] { 23,5}).combineWith(new Byte[] { 42 ,6});

        assertThat(test).isEqualTo(new Byte[] { 23,5,42,6});
    }

    @Test
    public void should_proper_combine_string_arrays() {
        String[] test=AndroidHelper.at(new String[] { "foo","bar"}).combineWith(new String [] { "ftw"});

        assertThat(test).isEqualTo(new String[] { "foo" , "bar", "ftw"});
    }

    @Test
    public void should_work_even_on_empty_arrays() {
        Object[] test=AndroidHelper.at(new Object[] {}).combineWith(new Object [] {});

        assertThat(test).isEqualTo(new Object[] {});
    }


    @Test
    public void should_work_when_one_side_is_empty() {
        String[] test=AndroidHelper.at(new String[] {}).combineWith(new String [] { "lol"});

        assertThat(test).isEqualTo(new String[] { "lol"});
    }

}
