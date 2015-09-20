package org.ligi.axt.extensions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;

public class IntentAXT {

    private final Intent intent;

    public IntentAXT(Intent intent) {
        this.intent = intent;
    }

    /**
     * Indicates whether the specified action can be used as an intent. This
     * method queries the package manager for installed packages that can
     * respond to an intent with the specified action. If no suitable package is
     * found, this method returns false.
     *
     * @param pm
     * @return
     */
    public boolean isIntentAvailable(PackageManager pm) {
        return isIntentAvailable(pm, PackageManager.MATCH_DEFAULT_ONLY);
    }

    public boolean isIntentAvailable(PackageManager pm, int flags) {
        return pm.queryIntentActivities(intent, flags).size() > 0;
    }

    /**
     * Indicates whether the specified action can be used as an service. This
     * method queries the package manager for installed packages that can
     * respond to an service with the specified action. If no suitable package is
     * found, this method returns false.
     *
     * @param pm
     * @return
     */
    public boolean isServiceAvailable(PackageManager pm) {
        return pm.queryIntentServices(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    public boolean isServiceAvailable(PackageManager pm, int flags) {
        return pm.queryIntentServices(intent, flags).size() > 0;
    }

    public Intent makeExplicit(final Context context) {
        final PackageManager pm = context.getPackageManager();
        final List<ResolveInfo> resolveInfo = pm.queryIntentServices(intent, 0);

        if (resolveInfo == null) {
            return null;
        }

        final ResolveInfo serviceInfo = resolveInfo.get(0);
        final String packageName = serviceInfo.serviceInfo.packageName;
        final String className = serviceInfo.serviceInfo.name;
        final ComponentName component = new ComponentName(packageName, className);

        return new Intent(intent).setComponent(component);
    }


}
