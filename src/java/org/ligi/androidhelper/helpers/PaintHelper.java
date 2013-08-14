package org.ligi.androidhelper.helpers;

import android.graphics.Paint;

public class PaintHelper {

    final Paint paint;

    public PaintHelper(final Paint paint) {
        this.paint = paint;
    }

    /**
     * calculate the width of a text for this paint
     *
     * @param text as String
     * @return the width in pixels
     */
    public float getTextWidth(String text) {
        float[] widths = new float[text.length()];
        paint.getTextWidths(text, widths);
        float res = 0;
        for (int i = 0; i < widths.length; i++) {
            res += widths[i];
        }

        return res;
    }

}
