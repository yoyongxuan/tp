package seedu.address.commons.core;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * An Object that can be used to uniquely identify a person.
 * Guarantees: immutable; is valid as declared in {@link #isValidIdentifier(String)}
 */
public class Identifier {

    private enum IdentifierType {
        INDEX,
        STUDENT_ID
    }

    public static final String MESSAGE_CONSTRAINTS = "Identifier must be a valid Student ID or index.";

    private final Index index;
    private final StudentId studentId;
    private final IdentifierType source;




    /**
     * Constructs a {@code Identifier} from either a student ID or an index.
     *
     * @param input A String representing either a valid student id or index.
     */
    public Identifier(String input) {
        requireNonNull(input);
        checkArgument(isValidIdentifier(input), MESSAGE_CONSTRAINTS);
        if (Index.isValidOneBasedIndex(input)) {
            source = IdentifierType.INDEX;
        } else {
            source = IdentifierType.STUDENT_ID;
        }

        if (source == IdentifierType.INDEX) {
            index = Index.fromOneBased(Integer.parseInt(input));
            studentId = null;
        } else {
            index = null;
            studentId = new StudentId(input);
        }
    }

    /**
     * Returns if a given string is a valid student id or index.
     */
    public static boolean isValidIdentifier(String test) {
        return Index.isValidOneBasedIndex(test) || StudentId.isValidStudentId(test);
    }

    /**
     * Retrieves the {@code Person} object from the input model matching this {@code Identifier}.
     * @param model The model containing the Address Book to retrieve Person from
     * @return {@code Person} object in Address Book which matches this {@code Identifier}
     * @throws PersonNotFoundException if no {@code Person} in Address Book matches this {@code Identifier}
     */
    public Person retrievePerson(Model model) throws PersonNotFoundException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person out;

        if (source == IdentifierType.INDEX) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new PersonNotFoundException();
            }
            out = lastShownList.get(index.getZeroBased());
        } else {
            out = lastShownList.stream()
                    .filter(person -> person.getStudentId().equals(studentId))
                    .findFirst()
                    .orElseThrow(() -> new PersonNotFoundException());
        }

        return out;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Identifier)) {
            return false;
        }

        Identifier otherIdentifier = (Identifier) other;

        if (otherIdentifier.source != this.source) {
            return false;
        }

        if (source == IdentifierType.INDEX) {
            return otherIdentifier.index.equals(this.index);
        } else {
            return otherIdentifier.studentId.equals(this.studentId);
        }
    }

}
