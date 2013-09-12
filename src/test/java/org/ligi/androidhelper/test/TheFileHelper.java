package org.ligi.androidhelper.test;

import android.os.Environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.androidhelper.AXT;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

@RunWith(RobolectricTestRunner.class)
public class TheFileHelper {

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
        File testDir = new File(EXT_DIR, DEFAULT_DIR + "/" + DEFAULT_DIR);
        testDir.mkdirs();

        // should exist now
        assertThat(testDir.exists()).isEqualTo(true);

        // invoke and check return code
        assertThat(AXT.at(testDir).deleteRecursive()).isEqualTo(true);

        assertThat(testDir.exists()).isEqualTo(false);
    }

    @Test
    public void read_write_file_cycle_should_keep_data() {
        File testFile = new File(EXT_DIR, DEFAULT_DIR + ".txt");

        AXT.at(testFile).writeString("SAMPLE");

        try {
            assertThat(AXT.at(testFile).loadToString()).isEqualTo("SAMPLE");
        } catch (IOException e) {
            fail("the file should be loadable");
        }
    }


}
