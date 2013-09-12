package org.ligi.axt.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.views.SquareImageView;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheSquareImageView {

    @Test
    public void should_be_square() {
        SquareViewTestActivity activity = new SquareViewTestActivity();
        activity.onCreate(new Bundle());

        assertThat(activity.squareView.getWidth()).isEqualTo(activity.squareView.getHeight());
    }

    @Test
    public void should_have_min_as_height() {
        SquareViewTestActivity activity = new SquareViewTestActivity();
        activity.onCreate(new Bundle());

        assertThat(activity.squareView.getHeight()).isEqualTo(100);
    }

    class SquareViewTestActivity extends Activity {
        public SquareImageView squareView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            squareView = new SquareImageView(Robolectric.application);
            squareView.setLayoutParams(new ViewGroup.LayoutParams(100,200));
            setContentView(squareView);
        }
    }

}
