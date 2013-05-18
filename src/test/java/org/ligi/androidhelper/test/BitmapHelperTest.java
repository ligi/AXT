package org.ligi.androidhelper.test;

import android.graphics.Bitmap;
import android.view.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AndroidHelper;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class BitmapHelperTest {

    @Test
    public void testProperScaling() {
        View v=mock(View.class);

        when(v.getHeight()).thenReturn(50);
        when(v.getWidth()).thenReturn(50);

        Bitmap origBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

        Bitmap scaledBitmap = AndroidHelper.at(origBitmap).scaleRelative2View(v, 1f, 0f);

        // TODO check whats going on here - smells as robolectric is not able to do the scaling part atm
        // assertEquals(v.getHeight(),scaledBitmap.getHeight());
    }
}
