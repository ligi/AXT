package org.ligi.axt.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int size = Math.min(parentWidth, parentHeight);
        this.setMeasuredDimension(size, size);
    }
}
