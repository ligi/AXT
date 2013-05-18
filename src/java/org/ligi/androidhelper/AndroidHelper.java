package org.ligi.androidhelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.CheckBox;
import org.ligi.androidhelper.helpers.*;

import java.io.File;
import java.net.URL;

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

    public static FileHelper at(File file) {
        return new FileHelper(file);
    }

    public static <T> ArrayHelper<T> at(T[] arr) {
        return new ArrayHelper<T>(arr);
    }

    public static IntentHelper at(Intent intent) {
        return new IntentHelper(intent);
    }

    public static URLHelper at(URL url) {
        return new URLHelper(url);
    }
}
