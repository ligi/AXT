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
        final String filePath = new ImageFromIntentUriToFileConverter(context).extract(uri);
        if (filePath!=null) {
            return new File(filePath);
        }

        return null;
    }
}
