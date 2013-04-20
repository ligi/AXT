package org.ligi.androidhelper.helpers;

import android.app.Activity;

public class ActivityHelper {

    private Activity mActivity;

    public ActivityHelper(Activity activity) {
        mActivity =activity;
    }

    public <T> T findById(int id) {
        return (T) mActivity.findViewById(id);
    }
}
