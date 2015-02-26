package org.ligi.axt;

import android.graphics.Bitmap;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * robolectric did not scale bitmaps - so doing it here
 */
public class TheBitmapAXT extends InstrumentationTestCase {

    @SmallTest
    public void testShouldScaleCorrect() {

        Bitmap origBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

        Bitmap scaledBitmap = AXT.at(origBitmap).scaleRelative(50, 50, 1f, 0f);

        assertEquals(50, scaledBitmap.getHeight());
    }

}
