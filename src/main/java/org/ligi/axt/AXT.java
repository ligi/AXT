package org.ligi.axt;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import org.ligi.axt.extensions.ActivityAXT;
import org.ligi.axt.extensions.ArrayAXT;
import org.ligi.axt.extensions.BitmapAXT;
import org.ligi.axt.extensions.CheckBoxAXT;
import org.ligi.axt.extensions.ContextAXT;
import org.ligi.axt.extensions.EditTextAXT;
import org.ligi.axt.extensions.FileAXT;
import org.ligi.axt.extensions.FragmentAXT;
import org.ligi.axt.extensions.InputStreamAXT;
import org.ligi.axt.extensions.IntentAXT;
import org.ligi.axt.extensions.PaintAXT;
import org.ligi.axt.extensions.ResolveInfoAXT;
import org.ligi.axt.extensions.StringAXT;
import org.ligi.axt.extensions.URLAXT;
import org.ligi.axt.extensions.UriAXT;
import org.ligi.axt.extensions.ViewAXT;
import org.ligi.axt.extensions.WindowManagerAXT;

public class AXT {
    public static CheckBoxAXT at(CheckBox checkBox) {
        return new CheckBoxAXT(checkBox);
    }

    public static BitmapAXT at(Bitmap bitmap) {
        return new BitmapAXT(bitmap);
    }

    public static ActivityAXT at(Activity activity) {
        return new ActivityAXT(activity);
    }

    public static FragmentAXT at(Fragment fragment) {
        return new FragmentAXT(fragment);
    }

    public static FileAXT at(File file) {
        return new FileAXT(file);
    }

    public static <T> ArrayAXT<T> at(T[] arr) {
        return new ArrayAXT<T>(arr);
    }

    public static IntentAXT at(Intent intent) {
        return new IntentAXT(intent);
    }

    public static URLAXT at(URL url) {
        return new URLAXT(url);
    }

    public static EditTextAXT at(EditText editText) {
        return new EditTextAXT(editText);
    }

    public static ContextAXT at(Context ctx) {
        return new ContextAXT(ctx);
    }

    public static InputStreamAXT at(InputStream inputStream) {
        return new InputStreamAXT(inputStream);
    }

    public static ViewAXT at(View view) {
        return new ViewAXT(view);
    }

    public static PaintAXT at(Paint paint) {
        return new PaintAXT(paint);
    }

    public static WindowManagerAXT at(WindowManager windowManager) {
        return new WindowManagerAXT(windowManager);
    }

    public static ResolveInfoAXT at(ResolveInfo resolveInfo) {
        return new ResolveInfoAXT(resolveInfo);
    }

    public static StringAXT at(String string) {
        return new StringAXT(string);
    }

    public static UriAXT at(Uri uri) {
        return new UriAXT(uri);
    }
}
