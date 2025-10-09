package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void isInvalidAttendance() {
        String invalidAttendanceNotEnoughTutorials = "true false";
        String invalidAttendanceNotBoolean = "true 1 2 3 4 5 6 7 8 9 10";
        assertFalse(Attendance.isValidAttendance(invalidAttendanceNotEnoughTutorials));
        assertFalse(Attendance.isValidAttendance(invalidAttendanceNotBoolean));
    }
}
