package org.ligi.axt.extensions;

import android.content.Context;
import android.content.pm.ResolveInfo;

public class ResolveInfoAXT {

    public final ResolveInfo resolveInfo;

    public ResolveInfoAXT(ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
    }

    public String getLabelSafely(Context context) {
        CharSequence charSequence = resolveInfo.loadLabel(context.getPackageManager());
        if (charSequence == null) {
            return "";
        }
        return charSequence.toString();
    }
}
