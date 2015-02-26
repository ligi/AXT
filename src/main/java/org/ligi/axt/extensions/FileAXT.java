package org.ligi.axt.extensions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileAXT {
    private final File file;

    public FileAXT(File file) {
        this.file = file;
    }

    /**
     * reads a file to a string
     *
     * @return the content of the file
     * @throws IOException
     */
    public String readToString() throws IOException {
        return readToString(Charset.defaultCharset());
    }

    public String readToString(Charset charset) throws IOException {
        final FileInputStream stream = new FileInputStream(file);
        try {
            return readToStringFromFileInputStream(charset, stream);
        } finally {
            stream.close();
        }
    }

    private String readToStringFromFileInputStream(Charset charset, FileInputStream stream) throws IOException {
        final FileChannel fc = stream.getChannel();
        try {
            final MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return charset.decode(bb).toString();
        } finally {
            fc.close();
        }
    }

    public boolean writeString(String string) {
        try {
            writeStringToFile(string);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void writeStringToFile(String string) throws IOException {
        final FileWriter fileWriter = new FileWriter(file);
        try {
            fileWriter.write(string);
        } finally {
            fileWriter.close();
        }
    }

    public boolean deleteRecursive() {
        return deleteRecursive(file);
    }

    public boolean deleteRecursive(File file2delete) {
        if (!file2delete.isDirectory()) {
            return false;
        }

        for (String child : file2delete.list()) {
            final File temp = new File(file2delete, child);
            if (temp.isDirectory()) {
                deleteRecursive(temp);
            } else {
                temp.delete();
            }
        }

        return file2delete.delete();
    }

    public <T extends Serializable> T loadToObject() throws IOException, ClassNotFoundException, ClassCastException {
        final ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
        try {
            return (T) is.readObject();
        } finally {
            is.close();
        }
    }

    public <T extends Serializable> T loadToObjectOrNull() {
        try {
            return loadToObject();
        } catch (IOException e) {
            // causes null return
        } catch (ClassNotFoundException e) {
            // causes null return
        } catch (ClassCastException e) {
            // causes null return
        }

        return null;
    }

    public boolean writeObject(Serializable object) {
        try {
            writeObjectToFile(object);
            return true;
        } catch (FileNotFoundException e) {
            // causes return false
        } catch (IOException e) {
            // causes return false
        }

        return false;
    }

    private void writeObjectToFile(Serializable object) throws IOException {
        final FileOutputStream fos = new FileOutputStream(file);
        try {
            writeObjectToFileOutputStream(object, fos);
        } finally {
            fos.close();
        }
    }

    private void writeObjectToFileOutputStream(Serializable object, FileOutputStream fos) throws IOException {
        final ObjectOutputStream os = new ObjectOutputStream(fos);
        try {
            os.writeObject(object);
        } finally {
            os.close();
        }
    }

}
