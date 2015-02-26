package org.ligi.axt.test;

import android.os.Environment;

import org.apache.tools.ant.filters.StringInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.AXT;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheInputStreamAXT {

    public static final String STRING_PROBE = "132QWE";
    private File ROOT = Environment.getExternalStorageDirectory();

    @Test
    public void should_write_file_correctly() throws IOException {
        File file = new File(ROOT, "foo1");

        AXT.at(new StringInputStream(STRING_PROBE)).toFile(file);

        assertThat(STRING_PROBE).isEqualTo(AXT.at(file).readToString());
    }

    @Test
    public void should_read_to_string_correctly() throws IOException {
        String readFromStream = AXT.at(new StringInputStream(STRING_PROBE)).readToString();

        assertThat(readFromStream).isEqualTo(STRING_PROBE);
    }

}
