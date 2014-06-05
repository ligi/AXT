package org.ligi.axt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.AXT;
import org.ligi.axt.helpers.ArrayAXT;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheArrayAXT {

    @Test
    public void should_proper_combine_byte_arrays() {
        Byte[] test = AXT.at(new Byte[]{23, 5}).combineWith(new Byte[]{42, 6});

        assertThat(test).isEqualTo(new Byte[]{23, 5, 42, 6});
    }

    @Test
    public void should_proper_combine_string_arrays() {
        String[] test = AXT.at(new String[]{"foo", "bar"}).combineWith(new String[]{"ftw"});

        assertThat(test).isEqualTo(new String[]{"foo", "bar", "ftw"});
    }

    @Test
    public void should_work_even_on_empty_arrays() {
        Object[] test = AXT.at(new Object[]{}).combineWith(new Object[]{});

        assertThat(test).isEqualTo(new Object[]{});
    }


    @Test
    public void should_work_when_one_side_is_empty() {
        String[] test = AXT.at(new String[]{}).combineWith(new String[]{"lol"});

        assertThat(test).isEqualTo(new String[]{"lol"});
    }

    @Test
    public void returns_last_element_correctly_with_other_elements() {
        ArrayAXT tested = AXT.at(new String[]{"lol", "foo", "last"});

        assertThat(tested.last()).isEqualTo("last");
    }


    @Test
    public void returns_last_element_correctly_when_the_only() {
        ArrayAXT tested = AXT.at(new String[]{"last"});

        assertThat(tested.last()).isEqualTo("last");
    }
}
