package org.ligi.axt.extensions;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.view.View;

public class BitmapAXT {

    private final Bitmap bitmap;

    public BitmapAXT(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap scaleRelative2View(View view, float inScaleX, float inScaleY) {
        return scaleRelative(view.getWidth(), view.getHeight(), inScaleX, inScaleY);
    }

    public Bitmap scaleRelative(int w, int h, float inScaleX, float inScaleY) {
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        float outScaleX, outScaleY;
        if (inScaleY != 0f) {
            // take the given y scale
            outScaleY = (h * inScaleY) / bitmap.getHeight();
        } else {
            // take outScaleX
            outScaleY = (w * inScaleX) / bitmap.getWidth();
        }

        if (inScaleX != 0f) {
            // take the given x scale
            outScaleX = (w * inScaleX) / bitmap.getWidth();
        } else {
            // take the given y scale
            outScaleX = (h * inScaleY) / bitmap.getHeight();
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
