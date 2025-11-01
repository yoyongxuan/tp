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
        assertFalse(StudentId.isValidStudentId("a000000z")); // digits 1 below boundary
        assertFalse(StudentId.isValidStudentId("a00000000z")); // digits 1 above boundary

        assertFalse(StudentId.isValidStudentId("aa0000000z")); // starting letter 1 above boundary
        assertFalse(StudentId.isValidStudentId("0000000z")); // no starting letter

        assertFalse(StudentId.isValidStudentId("a0000000zz")); // ending letter 1 above boundary
        assertFalse(StudentId.isValidStudentId("a0000000zz")); // no ending letter

        // incorrect character type
        assertFalse(StudentId.isValidStudentId("a00c0000z"));
        assertFalse(StudentId.isValidStudentId("10000000z"));
        assertFalse(StudentId.isValidStudentId("a00000001"));

        // incorrect prefix
        assertFalse(StudentId.isValidStudentId("b0000000z"));
        assertFalse(StudentId.isValidStudentId("@0000000z"));
        assertFalse(StudentId.isValidStudentId("^0000000z"));

        // incorrect suffix
        assertFalse(StudentId.isValidStudentId("a0000000@"));
        assertFalse(StudentId.isValidStudentId("a0000000&"));
        assertFalse(StudentId.isValidStudentId("a00000007"));

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

        // boundary value: 6 numbers
        assertFalse(StudentId.isValidStudentId("a123456a"));

        // boundary value: 8 numbers
        assertFalse(StudentId.isValidStudentId("a12345678a"));

        // boundary value: 2 prefix letters
        assertFalse(StudentId.isValidStudentId("aa1234567a"));

        // boundary value: 0 prefix letters
        assertFalse(StudentId.isValidStudentId("1234567a"));

        // boundary value: 2 suffix letters
        assertFalse(StudentId.isValidStudentId("a1234567aa"));

        // boundary value: 0 prefix letters
        assertFalse(StudentId.isValidStudentId("a1234567"));
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
