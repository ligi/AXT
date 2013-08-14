package org.ligi.androidhelper.helpers;

import android.content.Context;
import android.content.Intent;

public class ContextHelper {

    private final Context context;

    public ContextHelper(Context context) {
        this.context =context;
    }

    public void startActivityForClass(Class class2start) {
        Intent intent = new Intent(context, class2start);
        context.startActivity(intent);
    }
}
