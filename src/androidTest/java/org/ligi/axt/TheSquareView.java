package org.ligi.axt;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import static org.assertj.core.api.Assertions.assertThat;

public class TheSquareView extends ActivityInstrumentationTestCase2<SquareViewTestActivity> {

    public TheSquareView() {
        super(SquareViewTestActivity.class);
    }

    @SmallTest
    public void testShouldBeSquare() {
        SquareViewTestActivity activity = getActivity();

        assertThat(activity.squareView.getWidth()).isGreaterThan(1);
        assertThat(activity.squareView.getWidth()).isEqualTo(activity.squareView.getHeight());
    }

}
