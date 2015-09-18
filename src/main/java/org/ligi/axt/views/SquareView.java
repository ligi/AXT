package org.ligi.axt.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


public class SquareView extends View {

    public SquareView(Context context) {
        super(context);
    }

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int parentWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        final int parentHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        final int size = Math.min(parentWidth, parentHeight);
        this.setMeasuredDimension(size, size);
    }
}
