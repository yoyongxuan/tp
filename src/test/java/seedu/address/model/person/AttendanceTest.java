package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class AttendanceTest {

    @Test
    public void isInvalidAttendance() {
        String invalidAttendanceNotEnoughTutorials = "true false";
        String invalidAttendanceTooManyTutorials = "true false true false true false true false true false true false";
        String invalidAttendanceNotBoolean = "true 1 2 3 4 5 6 7 8 9 10";
        assertFalse(Attendance.isValidAttendance(invalidAttendanceNotEnoughTutorials));
        assertFalse(Attendance.isValidAttendance(invalidAttendanceTooManyTutorials));
        assertFalse(Attendance.isValidAttendance(invalidAttendanceNotBoolean));
    }

    @Test
    public void invertAttendance_success() {
        Attendance attendance = new Attendance("false true false false false false false false false false false");
        Attendance expectedAttendance = new Attendance(
                "true false false false false false false false false false false");
        attendance = attendance.invertAttendanceForTutorial(Index.fromOneBased(1));
        attendance = attendance.invertAttendanceForTutorial(Index.fromOneBased(2));
        assertEquals(attendance, expectedAttendance);
    }

    @Test
    public void equals() {
        String attendanceStringA = "false true false false false false false false false false false";
        String attendanceStringB = "false true true false false false true false false false false";
        Attendance attendanceA = new Attendance(attendanceStringA);

        // same values -> returns true
        assertTrue(attendanceA.equals(new Attendance(attendanceStringA)));

        // same object -> returns true
        assertTrue(attendanceA.equals(attendanceA));

        // null -> returns false
        assertFalse(attendanceA.equals(null));

        // different types -> returns false
        assertFalse(attendanceA.equals(5.0f));

        // different values -> returns false
        assertFalse(attendanceA.equals(new Attendance(attendanceStringB)));
    }
}
