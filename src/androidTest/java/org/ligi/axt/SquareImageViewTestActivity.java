package org.ligi.axt;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import org.ligi.axt.views.SquareImageView;

public class SquareImageViewTestActivity extends Activity {
    public SquareImageView squareView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        squareView = new SquareImageView(this);
        squareView.setLayoutParams(new ViewGroup.LayoutParams(100, 200));
        setContentView(squareView);
    }
}
