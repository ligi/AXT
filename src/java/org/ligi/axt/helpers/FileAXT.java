package org.ligi.axt.helpers;

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
        final FileChannel fc = stream.getChannel();
        final MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        fc.close();
        stream.close();
        return charset.decode(bb).toString();
    }

    public boolean writeString(String string) {
        try {
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            return false;
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
        final T returnClass = (T) is.readObject();
        is.close();
        return returnClass;
    }


    public <T extends Serializable> T loadToObjectOrNull() {
        try {
            final ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            T returnClass = (T) is.readObject();
            is.close();
            return returnClass;
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
            final FileOutputStream fos = new FileOutputStream(file);
            final ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            // causes return false
        } catch (IOException e) {
            // causes return false
        }
        return false;
    }


}
