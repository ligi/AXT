package org.ligi.axt.listeners;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

public class ActivityFinishingOnCancelListener implements OnCancelListener {

    private final Activity activity2finish;

    public ActivityFinishingOnCancelListener(Activity activity2close) {
        this.activity2finish = activity2close;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        activity2finish.finish();
    }

}
