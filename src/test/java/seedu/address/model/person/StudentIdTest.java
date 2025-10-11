package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null studentId
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // blank studentId
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only

        // incorrect length
        assertFalse(StudentId.isValidStudentId("a00000000z"));
        assertFalse(StudentId.isValidStudentId("aa0000000z"));
        assertFalse(StudentId.isValidStudentId("a0000000zz"));

        // incorrect character type
        assertFalse(StudentId.isValidStudentId("a00c0000z"));
        assertFalse(StudentId.isValidStudentId("10000000z"));
        assertFalse(StudentId.isValidStudentId("a00000001"));

        // incorrect prefix
        assertFalse(StudentId.isValidStudentId("b0000000z"));

        // supports upper and lower case
        assertTrue(StudentId.isValidStudentId("a0000000z"));
        assertTrue(StudentId.isValidStudentId("A0000000Z"));
        assertTrue(StudentId.isValidStudentId("A0000000z"));

        // supports any integer
        assertTrue(StudentId.isValidStudentId("a0123456z"));
        assertTrue(StudentId.isValidStudentId("a7890000z"));

        // supports any suffix
        assertTrue(StudentId.isValidStudentId("a0000000a"));
        assertTrue(StudentId.isValidStudentId("a0000000b"));
        assertTrue(StudentId.isValidStudentId("a0000000c"));
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("a0000000z");

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("a0000000z")));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(new Object()));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("a0000000a")));
        assertFalse(studentId.equals(new StudentId("a0123456z")));

        // same value in upper case instead -> return true
        assertTrue(studentId.equals(new StudentId("A0000000Z")));

    }
}
