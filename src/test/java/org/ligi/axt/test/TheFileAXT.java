package org.ligi.axt.test;

import android.os.Environment;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.AXT;
import org.robolectric.RobolectricTestRunner;
import static junit.framework.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TheFileAXT {

    private File EXT_DIR = Environment.getExternalStorageDirectory();
    private String DEFAULT_DIR = "foo_dir ";

    @Test
    public void delete_recursive_should_behave_correct_when_no_dir_there() {
        File testDir = new File(EXT_DIR, DEFAULT_DIR);
        // should not exist
        assertThat(testDir.exists()).isEqualTo(false);

        // invoke and check return code
        assertThat(AXT.at(testDir).deleteRecursive()).isEqualTo(false);
    }


    @Test
    public void delete_recursive_should_delete_a_single_dir() {
        File testDir = new File(EXT_DIR, DEFAULT_DIR);
        testDir.mkdir();

        // should exist now
        assertThat(testDir.exists()).isEqualTo(true);

        // invoke and check return code
        assertThat(AXT.at(testDir).deleteRecursive()).isEqualTo(true);

        assertThat(testDir.exists()).isEqualTo(false);
    }


    @Test
    public void delete_recursive_should_delete_dirs_recursively() {
        File testDirDeep = new File(EXT_DIR, DEFAULT_DIR + "/" + DEFAULT_DIR + "/" + DEFAULT_DIR);
        testDirDeep.mkdirs();

        File testDir2Remove = new File(EXT_DIR, DEFAULT_DIR);

        // should exist now
        assertThat(testDirDeep.exists()).isEqualTo(true);

        // invoke and check return code
        assertThat(AXT.at(testDir2Remove).deleteRecursive()).isEqualTo(true);

        assertThat(testDirDeep.exists()).isEqualTo(false);
    }

    @Test
    public void read_write_file_cycle_should_keep_data() {
        File testFile = new File(EXT_DIR, DEFAULT_DIR + ".txt");

        AXT.at(testFile).writeString("SAMPLE");

        try {
            assertThat(AXT.at(testFile).readToString()).isEqualTo("SAMPLE");
        } catch (IOException e) {
            fail("the file should be loadable");
        }
    }


    @Test
    public void obj_read_write_roundtrip() throws IOException, ClassNotFoundException {
        File testFile = new File(EXT_DIR, DEFAULT_DIR + ".obj");
        TestSerializable in = new TestSerializable();
        in.test = "TESTING";

        AXT.at(testFile).writeObject(in);

        TestSerializable out = AXT.at(testFile).loadToObject();
        assertThat(in.test).isEqualTo(out.test);
    }


    @Test
    public void string_read_write_roundtrip() throws IOException, ClassNotFoundException {
        File testFile = new File(EXT_DIR, "foo.txt");

        String probe = "foo\r\nbar";

        AXT.at(testFile).writeString(probe);
        final String read = AXT.at(testFile).readToString();

        assertThat(probe).isEqualTo(read);
    }


}
