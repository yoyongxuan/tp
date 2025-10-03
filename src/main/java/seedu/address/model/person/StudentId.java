package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's student id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student IDs should contain A as their first character and be "
            + "followed by 8 numeric characters and ending with an alphabetic character, and it should not be blank.";
    public static final String VALIDATION_REGEX = "^A\\d{7}[a-zA-Z]$";

    public final String value;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param id A valid student id.
     */
    public StudentId(String id) {
        requireNonNull(id);
        checkArgument(isValidStudentId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns if a given string is a valid student id.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherStudentId = (StudentId) other;
        return value.equals(otherStudentId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
