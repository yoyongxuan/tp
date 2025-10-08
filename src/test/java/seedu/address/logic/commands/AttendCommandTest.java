package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

public class AttendCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals_sameObject_success() {
        AttendCommand attendCommand1 = new AttendCommand(INDEX_FIRST_PERSON, 1);
        AttendCommand attendCommand2 = new AttendCommand(INDEX_FIRST_PERSON, 1);

        assertEquals(attendCommand1, attendCommand2);
    }

    @Test
    public void equals_differentObject_failure() {
        AttendCommand attendCommand1 = new AttendCommand(INDEX_FIRST_PERSON, 1);
        AttendCommand attendCommand2 = new AttendCommand(INDEX_SECOND_PERSON, 2);

        assertNotEquals(attendCommand1, attendCommand2);
    }

    @Test
    public void execute_success() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(personToEdit)
                .withAttendance(CommandTestUtil.VALID_ATTENDANCE_AMY)
                .build();

        AttendCommand attendCommand = new AttendCommand(INDEX_FIRST_PERSON, 1);

        String expectedMessage = String.format(AttendCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(attendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AttendCommand attendCommand = new AttendCommand(outOfBoundIndex, 1);

        assertCommandFailure(attendCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personStudentIdNotInList_failure() {
        StudentId notInList = new StudentId("A8888888Z");
        AttendCommand attendCommand = new AttendCommand(notInList, 1);

        assertCommandFailure(attendCommand, model, Messages.MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTutorial_failure() {
        AttendCommand attendCommand = new AttendCommand(INDEX_FIRST_PERSON, 12);

        assertCommandFailure(attendCommand, model, AttendCommand.MESSAGE_WRONG_TUTORIAL);
    }
}
