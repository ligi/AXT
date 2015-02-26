package org.ligi.axt;

import android.test.ActivityInstrumentationTestCase2;
import static org.assertj.core.api.Assertions.assertThat;

public class TheSquareImageView extends ActivityInstrumentationTestCase2<SquareViewTestActivity> {

    public TheSquareImageView() {
        super(SquareViewTestActivity.class);
    }

    public void testShouldBeSquare() {
        SquareViewTestActivity activity = getActivity();

        assertThat(activity.squareView.getWidth()).isGreaterThan(1);
        assertThat(activity.squareView.getWidth()).isEqualTo(activity.squareView.getHeight());
    }


}
