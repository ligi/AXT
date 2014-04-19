package org.ligi.axt.helpers;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.view.View;

public class BitmapAXT {

    private final Bitmap bitmap;

    public BitmapAXT(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap scale2maxSize(int maxSize) {
        return null;
    }

    /**
     * Scale a bitmap relative to some view.
     *
     * @param view
     * @param inScaleX
     * @param inScaleY
     * @return
     */
    public Bitmap scaleRelative2View(View view, float inScaleX, float inScaleY) {
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        float outScaleX, outScaleY;
        if (inScaleY != 0f) {
            // take the given y scale
            outScaleY = (view.getHeight() * inScaleY) / bitmap.getHeight();
        } else {
            // take outScaleX
            outScaleY = (view.getWidth() * inScaleX) / bitmap.getWidth();
        }

        if (inScaleX != 0f) {
            // take the given x scale
            outScaleX = (view.getWidth() * inScaleX) / bitmap.getWidth();
        } else {
            // take the given y scale
            outScaleX = (view.getHeight() * inScaleY) / bitmap.getHeight();
        }

        matrix.postScale(outScaleX, outScaleY);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Point getSizeAsPoint() {
        return new Point(bitmap.getWidth(), bitmap.getHeight());
    }

    public Point scaleToFitMaxPixels(int maxPixels, Point point) {
        float scale;
        if (point.x < maxPixels && point.y < maxPixels) {
            // nothing is over dist px -> we are good with the given value
            scale = 1f;
        } else {

            if (point.x > point.y) {
                scale = (float) point.x / maxPixels;
            } else {
                scale = (float) point.y / maxPixels;
            }
        }

        return new Point((int) (point.x * scale), (int) (point.y * scale));
    }


}
