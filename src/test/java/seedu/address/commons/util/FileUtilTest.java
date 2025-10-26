package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


public class FileUtilTest {

    @TempDir
    public Path testFolder;

    @Test
    public void isFileExists() throws IOException {
        Path testFile = testFolder.resolve("a");

        // returns false on non-existent file
        assertFalse(FileUtil.isFileExists(testFile));

        // returns false on folders
        assertFalse(FileUtil.isFileExists(testFolder));

        // returns true on file that exists
        Files.createFile(testFile);
        assertTrue(FileUtil.isFileExists(testFile));

    }

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void createFile() throws IOException {
        Path testFile = testFolder.resolve("b");

        // file does not currently exist
        assertFalse(Files.exists(testFile));

        // file exists after calling createFile
        FileUtil.createFile(testFile);
        assertTrue(Files.exists(testFile));

        // calling createFile on existing file has no effect
        FileUtil.createFile(testFile);
        assertTrue(Files.exists(testFile));
    }

}
