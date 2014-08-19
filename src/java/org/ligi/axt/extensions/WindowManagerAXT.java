package org.ligi.axt.extensions;

import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

public class WindowManagerAXT {

    private final WindowManager windowManager;

    public WindowManagerAXT(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    @SuppressWarnings("deprecation")
    public Point getSizeAsPointCorrectly() {
        final Point result = new Point();
        final Display defaultDisplay = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            // new way in 13
            defaultDisplay.getSize(result);
        } else {
            // deprecated with 13
            result.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
        return result;
    }

    public int getLargestSide() {
        Point sizeAsPoint = getSizeAsPointCorrectly();
        return Math.max(sizeAsPoint.x, sizeAsPoint.y);
    }


    public int getSmallestSide() {
        Point sizeAsPoint = getSizeAsPointCorrectly();
        return Math.min(sizeAsPoint.x, sizeAsPoint.y);
    }

}
