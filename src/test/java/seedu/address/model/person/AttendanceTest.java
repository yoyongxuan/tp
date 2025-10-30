package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class AttendanceTest {

    @Test
    public void isValidTutorial() {
        Index invalidIndex = Index.fromOneBased(Attendance.NUMBER_OF_TUTORIALS + 1);
        assertFalse(Attendance.isValidTutorial(invalidIndex));

        Index validIndexUpperBound = Index.fromOneBased(Attendance.NUMBER_OF_TUTORIALS);
        assertTrue(Attendance.isValidTutorial(validIndexUpperBound));

        Index validIndexLowerBound = Index.fromOneBased(1);
        assertTrue(Attendance.isValidTutorial(validIndexLowerBound));
    }

    @Test
    public void isValidAttendance() {
        // not boolean value
        String invalidAttendanceNotBoolean = "rue false true false true false true false true false 1";
        assertFalse(Attendance.isValidAttendance(invalidAttendanceNotBoolean));

        // one below boundary -> return false
        String invalidAttendanceNotEnoughTutorials = "true false true false true false true false true false";
        assertFalse(Attendance.isValidAttendance(invalidAttendanceNotEnoughTutorials));

        // one above boundary -> return true
        String invalidAttendanceTooManyTutorials = "true false true false true false true false true false true false";
        assertFalse(Attendance.isValidAttendance(invalidAttendanceTooManyTutorials));

        // valid attendance -> return true
        String validAttendance = "true false true false true false true false true false true";
        assertTrue(Attendance.isValidAttendance(validAttendance));
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
