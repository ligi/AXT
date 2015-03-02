package org.ligi.axt.extensions;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import org.ligi.axt.converter.ImageFromIntentUriToFileConverter;

public class UriAXT {

    private final Uri uri;

    public UriAXT(Uri uri) {
        this.uri = uri;
    }

    public File loadImage(final Context context) {
        return new ImageFromIntentUriToFileConverter(context).extract(uri);
    }
}
