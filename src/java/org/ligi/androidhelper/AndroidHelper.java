package org.ligi.androidhelper;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.widget.CheckBox;
import org.ligi.androidhelper.helpers.ActivityHelper;
import org.ligi.androidhelper.helpers.BitmapHelper;
import org.ligi.androidhelper.helpers.CheckBoxHelper;
import org.ligi.androidhelper.helpers.FragmentHelper;

public class AndroidHelper {
    public static CheckBoxHelper at(CheckBox checkBox) {
        return new CheckBoxHelper(checkBox);
    }

    public static BitmapHelper at(Bitmap bitmap) {
        return new BitmapHelper(bitmap);
    }

    public static ActivityHelper at(Activity activity) {
        return new ActivityHelper(activity);
    }

    public static FragmentHelper at(Fragment fragment) {
        return new FragmentHelper(fragment);
    }
}
