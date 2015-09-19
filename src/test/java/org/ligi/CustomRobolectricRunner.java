package org.ligi;

import java.io.File;
import java.util.Properties;
import org.junit.runners.model.InitializationError;
import org.ligi.axt.BuildConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

public class CustomRobolectricRunner extends RobolectricTestRunner {
    public CustomRobolectricRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String manifestPath = "AndroidManifest.xml";
        String resPath = "build/intermediates/res/" + BuildConfig.BUILD_TYPE;

        // android studio has a different execution root for tests than pure gradle
        // so we avoid here manual effort to get them running inside android studio
        if (!new File(manifestPath).exists()) {
            manifestPath = "app/" + manifestPath;
        }

        config = overwriteConfig(config, "manifest", manifestPath);
        config = overwriteConfig(config, "resourceDir", resPath);
        return super.getAppManifest(config);
    }

    private Config.Implementation overwriteConfig(Config config, String key, String value) {
        Properties properties = new Properties();
        properties.setProperty(key, value);
        return new Config.Implementation(config, Config.Implementation.fromProperties(properties));
    }

}