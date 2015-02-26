package org.ligi.axt.extensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamAXT {

    private final InputStream inputStream;

    public InputStreamAXT(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void toFile(File f) throws IOException {
        FileOutputStream fos = new FileOutputStream(f);
        try {
            byte[] buffer = new byte[1024];
            int len;

            while ((len = inputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } finally {
            fos.close();
        }
    }

    public String readToString() throws IOException {
        InputStreamReader is = new InputStreamReader(inputStream);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(is);
        String read = br.readLine();

        while (read != null) {
            sb.append(read);
            read = br.readLine();
        }

        return sb.toString();
    }
}
