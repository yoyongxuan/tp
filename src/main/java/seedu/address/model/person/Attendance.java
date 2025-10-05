package seedu.address.model.person;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a Person's attendance in the address book.
 * Guarantees: immutable;
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "Tutorial has to be between 1 and 11 inclusive.";

    public final boolean[] attendance;

    /**
     * Constructs an {@code Attendance}.
     */
    public Attendance() {
        attendance = new boolean[11];
    }

    /**
     * Constructs an {@code Attendance} for JSON formatting
     * @param booleans output from #toJson()
     */
    public Attendance(String booleans) {
        attendance = new boolean[11];
        String[] argsSplit = booleans.split(" ");
        for (int i = 0; i < attendance.length; i++) {
            attendance[i] = Boolean.parseBoolean(argsSplit[i]);
        }
    }

    /**
     * returns if a tutorial number is valid
     */
    public static boolean isValidTutorial(Integer test) {
        return (test > 0) && (test < 12);
    }

    /**
     * returns if an attendance is valid
     */
    public static boolean isValidAttendance(String test) {
        String[] argsSplit = test.split(" ");
        if (argsSplit.length != 11) {
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
     * Updates attendance for given tutorial number
     */
    public Attendance addAttendance(int tutorialNumber) {
        String currentAttendance = this.toJson();
        String[] attendanceSplit = currentAttendance.split(" ");
        attendanceSplit[tutorialNumber - 1] = "true";
        return new Attendance(String.join(" ", attendanceSplit));
    }

    /**
     * converts Attendance to a JsonAdaptedPerson friendly format
     * @return
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
                output.append((i + 1) + ", ");
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
        if (!(other instanceof seedu.address.model.person.Attendance)) {
            return false;
        }

        seedu.address.model.person.Attendance otherAttendance = (seedu.address.model.person.Attendance) other;
        return Arrays.equals(attendance, otherAttendance.attendance);
    }

    @Override
    public int hashCode() {
        return attendance.hashCode();
    }
}


