package org.ligi.axt.listeners;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ActivityFinishingOnClickListener implements OnClickListener {

    private final Activity activity2finish;

    public ActivityFinishingOnClickListener(Activity activity2finish) {
        this.activity2finish = activity2finish;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        activity2finish.finish();
    }

}
