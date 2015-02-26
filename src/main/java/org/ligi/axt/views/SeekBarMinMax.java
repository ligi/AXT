package org.ligi.axt.views;

import android.content.Context;
import android.widget.SeekBar;

/**
 * a extension to SeekBar for having a minimum
 */
public class SeekBarMinMax extends SeekBar {

    private final int min;

    public SeekBarMinMax(Context context, int min, int max) {
        super(context);

        if (min > max) {
            throw new IllegalArgumentException("max must be bigger than min for SeegBarMinMax");
        }

        this.min = min;
        this.setMax(max - min);
    }

    @Override
    public synchronized int getProgress() {
        return super.getProgress() + min;
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress - min);
    }
}
