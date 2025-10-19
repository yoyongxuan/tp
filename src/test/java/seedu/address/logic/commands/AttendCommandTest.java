package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_INDEX_OUT_OF_RANGE;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_STUDENT_ID_NOT_FOUND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;

public class AttendCommandTest {
    private static final Index INDEX_FIRST_TUTORIAL = Index.fromOneBased(1);
    private static final Index INDEX_SECOND_TUTORIAL = Index.fromOneBased(2);
    private static final Index INDEX_INVALID_TUTORIAL = Index.fromZeroBased(Attendance.NUMBER_OF_TUTORIALS);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals_sameObject_success() {
        AttendCommand attendCommand1 = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);
        AttendCommand attendCommand2 = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);

        assertEquals(attendCommand1, attendCommand2);
    }

    @Test
    public void equals_differentObject_failure() {
        AttendCommand attendCommand1 = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);
        AttendCommand attendCommand2 = new AttendCommand(IDENTIFIER_SECOND_PERSON, INDEX_FIRST_TUTORIAL);
        AttendCommand attendCommand3 = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_SECOND_TUTORIAL);

        assertNotEquals(attendCommand1, attendCommand2);
        assertNotEquals(attendCommand1, attendCommand3);
    }

    @Test
    public void equals_notAttendCommand_failure() {
        AttendCommand attendCommand = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);

        assertNotEquals(attendCommand, 10);
    }

    @Test
    public void execute_add_success() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        Person editedPerson = new Person.PersonBuilder(personToEdit)
                .withStudentId(VALID_STUDENT_ID_AMY)
                .withAttendance(VALID_ATTENDANCE_AMY)
                .build();

        AttendCommand attendCommand = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);

        String expectedMessageAdd = String.format(AttendCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, "Added",
                INDEX_FIRST_TUTORIAL.getOneBased(), Messages.format(editedPerson));

        Model modelToAddAttendance = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Model expectedModelAdd = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModelAdd.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(attendCommand, modelToAddAttendance, expectedMessageAdd, expectedModelAdd);
    }

    @Test
    public void execute_remove_success() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        Person editedPerson = new Person.PersonBuilder(personToEdit)
                .withStudentId(VALID_STUDENT_ID_AMY)
                .withAttendance(VALID_ATTENDANCE_AMY)
                .build();

        AttendCommand attendCommand = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);

        String expectedMessageRemove = String.format(AttendCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, "Removed",
                INDEX_FIRST_TUTORIAL.getOneBased(), Messages.format(editedPerson));

        Model modelToRemoveAttendance = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        modelToRemoveAttendance.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        Model expectedModelRemove = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(attendCommand, modelToRemoveAttendance, expectedMessageRemove, expectedModelRemove);
    }

    @Test
    public void execute_invalidIdentifier_failure() {
        AttendCommand attendCommandOutOfRangeIndex =
                new AttendCommand(IDENTIFIER_INDEX_OUT_OF_RANGE, INDEX_FIRST_TUTORIAL);
        AttendCommand attendCommandInvalidStudentId =
                new AttendCommand(IDENTIFIER_STUDENT_ID_NOT_FOUND, INDEX_FIRST_TUTORIAL);

        assertCommandFailure(attendCommandOutOfRangeIndex, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertCommandFailure(attendCommandInvalidStudentId, model, Messages.MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTutorial_failure() {
        AttendCommand attendCommand = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_INVALID_TUTORIAL);

        assertCommandFailure(attendCommand, model, AttendCommand.MESSAGE_WRONG_TUTORIAL);
    }

    @Test
    public void equals() {
        AttendCommand attendCommand = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);
        AttendCommand attendCommandDifferentIdentifier =
                new AttendCommand(IDENTIFIER_SECOND_PERSON, INDEX_FIRST_TUTORIAL);
        AttendCommand attendCommandDifferentTutorial =
                new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_SECOND_TUTORIAL);

        // True if same object
        assertTrue(attendCommand.equals(attendCommand));

        // Same values -> returns true
        assertTrue(attendCommand.equals(new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL)));

        // different types -> returns false
        assertFalse(attendCommand.equals(1));

        // null -> returns false
        assertFalse(attendCommand.equals(null));

        // different identifier -> returns false
        assertFalse(attendCommand.equals(attendCommandDifferentIdentifier));

        // different tutorial -> returns false
        assertFalse(attendCommand.equals(attendCommandDifferentTutorial));
    }

    @Test
    public void toStringMethod() {
        AttendCommand attendCommand = new AttendCommand(IDENTIFIER_FIRST_PERSON, INDEX_FIRST_TUTORIAL);
        String expected = AttendCommand.class.getCanonicalName()
                + "{identifier=" + IDENTIFIER_FIRST_PERSON + ", tutorial=" + INDEX_FIRST_TUTORIAL + "}";
        assertEquals(expected, attendCommand.toString());
    }

}
