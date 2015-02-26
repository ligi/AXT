package org.ligi.axt;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import org.ligi.axt.views.SquareView;

public class SquareViewTestActivity extends Activity {
    public SquareView squareView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        squareView = new SquareView(this);
        squareView.setLayoutParams(new ViewGroup.LayoutParams(100, 200));
        setContentView(squareView);
    }
}
