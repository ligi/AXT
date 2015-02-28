package org.ligi.axt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.CustomRobolectricRunner;
import org.ligi.axt.AXT;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(CustomRobolectricRunner.class)
public class TheStringAXT {

    @Test
    public void should_parse_rgb_correct() {
        final int result = AXT.at("rgb(204,204,204)").parseColor(23);

        assertThat(result).isEqualTo(0xFFCCCCCC);
    }

    @Test
    public void should_parse_rgb_correct_when_there_is_extra_space() {
        final int result = AXT.at("rgb (204,204,204)").parseColor(23);

        assertThat(result).isEqualTo(0xFFCCCCCC);
    }

    @Test
    public void should_fall_back_to_default_if_rgb_broken() {
        final int result = AXT.at("rgb").parseColor(23);

        assertThat(result).isEqualTo(23);
    }


    @Test
    public void should_fall_back_to_default_if_rgb_broken2() {
        final int result = AXT.at("rgb(").parseColor(23);

        assertThat(result).isEqualTo(23);
    }


    @Test
    public void should_fall_back_to_default_if_rgb_broken3() {
        final int result = AXT.at("rgb()").parseColor(23);

        assertThat(result).isEqualTo(23);
    }

    @Test
    public void should_parse_pound_style_correct() {
        final int result = AXT.at("#CCCCCA").parseColor(23);

        assertThat(result).isEqualTo(0xFFCCCCCA);
    }

    @Test
    public void should_fall_back_to_default_when_pound_style_broken() {
        final int result = AXT.at("#").parseColor(23);

        assertThat(result).isEqualTo(23);
    }


    @Test
    public void should_fall_back_to_default_when_pound_style_broken2() {
        final int result = AXT.at("#y").parseColor(23);

        assertThat(result).isEqualTo(23);
    }

}
