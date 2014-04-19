package org.ligi.axt.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamAXT {

    private final InputStream inputStream;

    public InputStreamAXT(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void toFile(File f) throws IOException {
        FileOutputStream fos = new FileOutputStream(f);

        byte[] buffer = new byte[1024];
        int len;

        while ((len = inputStream.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }

    }
}
