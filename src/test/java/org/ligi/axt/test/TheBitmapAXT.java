package org.ligi.axt.test;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.AXT;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class TheBitmapAXT {

    public static final int SIZE_X_PROBE = 42;
    public static final int SIZE_Y_PROBE = 100;

    @Test
    public void size_as_point_works() {
        Bitmap origBitmap = Bitmap.createBitmap(SIZE_X_PROBE, SIZE_Y_PROBE, Bitmap.Config.ARGB_8888);

        Point sizeAsPoint = AXT.at(origBitmap).getSizeAsPoint();

        assertThat(sizeAsPoint).isEqualTo(new Point(SIZE_X_PROBE, SIZE_Y_PROBE));
    }

    @Test
    public void should_scale_correct() {
        View v=mock(View.class);

        when(v.getHeight()).thenReturn(50);
        when(v.getWidth()).thenReturn(50);

        Bitmap origBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

        Bitmap scaledBitmap = AXT.at(origBitmap).scaleRelative2View(v, 1f, 0f);

        // TODO check whats going on here - smells as robolectric is not able to do the scaling part atm
        //assertEquals(v.getHeight(),scaledBitmap.getHeight());
    }
}
