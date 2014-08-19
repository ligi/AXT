package org.ligi.axt.extensions.misc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CommonIntentStarter {

    public final Context context;
    // defaults to true as a common pitfall is to forget FLAG_ACTIVITY_NEW_TASK when needed
    private boolean asNewTask = true;

    public CommonIntentStarter(final Context context) {
        this.context = context;
    }

    public void openUrl(final String urlString) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlString));
        start(intent);
    }

    public void shareUrl(final String urlString) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, urlString);
        intent.setType("text/plain");
        start(intent);
    }

    public void activityFromClass(Class class2start) {
        final Intent intent = new Intent(context, class2start);
        start(intent);
    }

    public CommonIntentStarter noNewTask() {
        asNewTask = false;
        return this;
    }

    private void start(Intent intent) {
        if (asNewTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
