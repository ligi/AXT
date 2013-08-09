package org.ligi.androidhelper.helpers;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;

public class BitmapHelper {

    private final Bitmap bitmap;

    public BitmapHelper(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Scale a bitmap relative to some view.
     *
     * @param view
     * @param x_scale_
     * @param y_scale_
     * @return
     */
    public Bitmap scaleRelative2View(View view, float x_scale_, float y_scale_) {
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        float x_scale, y_scale;
        if (y_scale_ != 0f)
            // take the given y scale
            y_scale = (view.getHeight() * y_scale_) / bitmap.getHeight();
        else
            // take x_scale
            y_scale = (view.getWidth() * x_scale_) / bitmap.getWidth();

        if (x_scale_ != 0f)
            // take the given x scale
            x_scale = (view.getWidth() * x_scale_) / bitmap.getWidth();
        else
            // take the given y scale
            x_scale = (view.getHeight() * y_scale_) / bitmap.getHeight();

        matrix.postScale(x_scale, y_scale);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

}
