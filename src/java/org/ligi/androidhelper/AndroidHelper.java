package org.ligi.androidhelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import org.ligi.androidhelper.helpers.ActivityHelper;
import org.ligi.androidhelper.helpers.ArrayHelper;
import org.ligi.androidhelper.helpers.BitmapHelper;
import org.ligi.androidhelper.helpers.CheckBoxHelper;
import org.ligi.androidhelper.helpers.ContextHelper;
import org.ligi.androidhelper.helpers.FileHelper;
import org.ligi.androidhelper.helpers.FragmentHelper;
import org.ligi.androidhelper.helpers.IntentHelper;
import org.ligi.androidhelper.helpers.PaintHelper;
import org.ligi.androidhelper.helpers.ResolveInfoHelper;
import org.ligi.androidhelper.helpers.URLHelper;
import org.ligi.androidhelper.helpers.ViewHelper;
import org.ligi.androidhelper.helpers.EditTextHelper;

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

    public static EditTextHelper at(EditText editText) {
        return new EditTextHelper(editText);
    }

    public static ContextHelper at(Context ctx) {
        return new ContextHelper(ctx);
    }


    public static ViewHelper at(View view) {
        return new ViewHelper(view);
    }

    public static PaintHelper at(Paint paint) {
        return new PaintHelper(paint);
    }

    public static ResolveInfoHelper at(ResolveInfo resolveInfo) {
        return new ResolveInfoHelper(resolveInfo);
    }
}
