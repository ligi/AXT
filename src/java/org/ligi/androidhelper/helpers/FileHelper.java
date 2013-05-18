package org.ligi.androidhelper.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileHelper {
    private File file;

    public FileHelper(File file) {
        this.file = file;
    }

    /**
     * reads a file to a string
     *
     * @return the content of the file
     * @throws IOException
     */
    public String loadToString() throws IOException {
        FileInputStream stream = new FileInputStream(file);
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }

    }

}
