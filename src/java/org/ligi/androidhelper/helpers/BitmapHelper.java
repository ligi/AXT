package org.ligi.androidhelper.helpers;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;

public class BitmapHelper {

    private Bitmap mBitmap;

    public BitmapHelper(Bitmap bitmap) {
        mBitmap = bitmap;
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
            y_scale = (view.getHeight() * y_scale_) / mBitmap.getHeight();
        else
            // take x_scale
            y_scale = (view.getWidth() * x_scale_) / mBitmap.getWidth();

        if (x_scale_ != 0f)
            // take the given x scale
            x_scale = (view.getWidth() * x_scale_) / mBitmap.getWidth();
        else
            // take the given y scale
            x_scale = (view.getHeight() * y_scale_) / mBitmap.getHeight();

        matrix.postScale(x_scale, y_scale);

        return Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
    }

}
