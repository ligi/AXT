package org.ligi.axt.extensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamAXT {

    private static final int CHARACTER_READ_BUFFER_SIZE = 1024;

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
        try {
            return readFromInputStreamReaderToString(is);
        } finally {
            is.close();
        }
    }

    private String readFromInputStreamReaderToString(InputStreamReader is) throws IOException {
        BufferedReader reader = new BufferedReader(is, CHARACTER_READ_BUFFER_SIZE);
        try {
            char[] buffer = new char[CHARACTER_READ_BUFFER_SIZE];
            StringBuilder sb = new StringBuilder();

            int len;
            while ((len = reader.read(buffer)) >= 0) {
                sb.append(buffer, 0, len);
            }

            return sb.toString();
        } finally {
            reader.close();
        }
    }
}
