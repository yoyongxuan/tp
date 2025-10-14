package seedu.address.model.person;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Person's attendance in the address book.
 * Guarantees: immutable;
 */
public class Attendance {
    public static final Integer NUMBER_OF_TUTORIALS = 11;
    public static final String MESSAGE_CONSTRAINTS = "Tutorial has to be between 1 and "
            + NUMBER_OF_TUTORIALS + " inclusive.";

    public final boolean[] attendance;

    /**
     * Constructs an {@code Attendance}.
     */
    public Attendance() {
        attendance = new boolean[NUMBER_OF_TUTORIALS];
    }

    /**
     * Constructs an {@code Attendance} for JSON formatting
     * @param booleans output from #toJson()
     */
    public Attendance(String booleans) {
        attendance = new boolean[NUMBER_OF_TUTORIALS];
        String[] argsSplit = booleans.split(" ");
        for (int i = 0; i < attendance.length; i++) {
            attendance[i] = Boolean.parseBoolean(argsSplit[i]);
        }
    }

    /**
     * returns if a tutorial number is valid
     */
    public static boolean isValidTutorial(Index test) {
        return (test.getZeroBased() >= 0) && (test.getZeroBased() < NUMBER_OF_TUTORIALS);
    }

    /**
     * returns if an attendance is valid
     */
    public static boolean isValidAttendance(String test) {
        String[] argsSplit = test.split(" ");
        if (argsSplit.length != NUMBER_OF_TUTORIALS) {
            return false;
        }
        for (String arg: argsSplit) {
            if (!arg.equals("true") && !arg.equals("false")) {
                return false;
            }
        }
        return true;
    }

    /**
     * gets the current attendance status for a given {@code tutorial}
     */
    public boolean getAttendanceForTutorial(Index tutorial) {
        String currentAttendance = this.toJson();
        String[] attendanceSplit = currentAttendance.split(" ");
        return Boolean.parseBoolean(attendanceSplit[tutorial.getZeroBased()]);
    }

    /**
     * Inverts attendance for given tutorial number
     */
    public Attendance invertAttendanceForTutorial(Index tutorial) {
        String currentAttendance = this.toJson();
        String[] attendanceSplit = currentAttendance.split(" ");
        boolean currentStatus = Boolean.parseBoolean(attendanceSplit[tutorial.getZeroBased()]);
        if (currentStatus) {
            attendanceSplit[tutorial.getZeroBased()] = "false";
        } else {
            attendanceSplit[tutorial.getZeroBased()] = "true";
        }
        return new Attendance(String.join(" ", attendanceSplit));
    }

    /**
     * converts Attendance to a JsonAdaptedPerson friendly format
     */
    public String toJson() {
        return IntStream.range(0, attendance.length)
                .mapToObj(i -> Boolean.toString(attendance[i]))
                .collect(Collectors.joining(" "));
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Tutorials Attended: ");
        for (int i = 0; i < attendance.length; i++) {
            if (attendance[i]) {
                output.append((i + 1)).append(", ");
            }
        }
        if (output.length() > "Tutorials Attended: ".length()) {
            output.delete(output.length() - 2, output.length());
        }
        return output.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return Arrays.equals(attendance, otherAttendance.attendance);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(attendance);
    }
}


