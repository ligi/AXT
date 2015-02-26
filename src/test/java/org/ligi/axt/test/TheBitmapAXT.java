package org.ligi.axt.test;

import android.graphics.Bitmap;
import android.graphics.Point;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.CustomRobolectricRunner;
import org.ligi.axt.AXT;


@RunWith(CustomRobolectricRunner.class)
public class TheBitmapAXT {

    public static final int SIZE_X_PROBE = 42;
    public static final int SIZE_Y_PROBE = 100;


    @Test
    public void size_as_point_works() {
        Bitmap origBitmap = Bitmap.createBitmap(SIZE_X_PROBE, SIZE_Y_PROBE, Bitmap.Config.ARGB_8888);

        Point sizeAsPoint = AXT.at(origBitmap).getSizeAsPoint();

        Assertions.assertThat(sizeAsPoint).isEqualTo(new Point(SIZE_X_PROBE, SIZE_Y_PROBE));
    }
}
