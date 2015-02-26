package org.ligi.axt.views;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityFinishOnViewClickListener implements OnClickListener {

    private Activity activity2finish;

    public ActivityFinishOnViewClickListener(Activity activity2finish) {
        this.activity2finish = activity2finish;
    }

    @Override
    public void onClick(View v) {
        activity2finish.finish();
    }

}
