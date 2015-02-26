package org.ligi.axt.extensions;

import android.content.Context;
import android.view.ViewConfiguration;
import java.lang.reflect.Field;
import org.ligi.axt.extensions.misc.CommonIntentStarter;

public class ContextAXT {

    private final Context context;

    public ContextAXT(Context context) {
        this.context = context;
    }

    /**
     * a little hack because I strongly disagree with the style guide here
     * ;-)
     * not having the Actionbar overfow menu also with devices with hardware
     * key really helps discoverability
     * http://stackoverflow.com/questions/9286822/how-to-force-use-of-overflow-menu-on-devices-with-menu-button
     */
    public void forceOverFlowMenuEvenThoughDeviceHasPhysical() {

        try {
            ViewConfiguration config = ViewConfiguration.get(context);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore - but at least we tried ;-)
        }
    }

    public CommonIntentStarter startCommonIntent() {
        return new CommonIntentStarter(context);
    }
}
