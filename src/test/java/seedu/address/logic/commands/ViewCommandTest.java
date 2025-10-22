package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_INDEX_OUT_OF_RANGE;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_STUDENT_ID_NOT_FOUND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Identifier;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ViewCommand viewFirst = new ViewCommand(IDENTIFIER_FIRST_PERSON);
        ViewCommand viewFirstCopy = new ViewCommand(IDENTIFIER_FIRST_PERSON);
        ViewCommand viewSecond = new ViewCommand(IDENTIFIER_SECOND_PERSON);

        assertTrue(viewFirst.equals(viewFirst));
        assertTrue(viewFirst.equals(viewFirstCopy));
        assertFalse(viewFirst.equals(viewSecond));
    }

    @Test
    public void execute_validStudentId_success() throws CommandException {
        Person targetPerson = model.getFilteredPersonList().get(0);
        Identifier identifier = new Identifier(targetPerson.getStudentId().toString());
        ViewCommand viewCommand = new ViewCommand(identifier);

        String expectedMessage = "Viewing " + targetPerson.getName();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p -> p.equals(identifier.retrievePerson(model)));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(targetPerson), model.getFilteredPersonList());
    }

    @Test
    public void execute_validIndex_success() throws CommandException {
        Person targetPerson = model.getFilteredPersonList().get(0);
        ViewCommand viewCommand = new ViewCommand(IDENTIFIER_FIRST_PERSON);

        String expectedMessage = "Viewing " + targetPerson.getName();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p -> p.equals(IDENTIFIER_FIRST_PERSON.retrievePerson(model)));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(targetPerson), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidPersonIdentifierUnfilteredList_failure() {
        ViewCommand viewCommand;

        viewCommand = new ViewCommand(IDENTIFIER_INDEX_OUT_OF_RANGE);
        assertCommandFailure(viewCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        viewCommand = new ViewCommand(IDENTIFIER_STUDENT_ID_NOT_FOUND);
        assertCommandFailure(viewCommand, model, MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX);

    }

    @Test
    public void toStringMethod() {
        Identifier identifier = new Identifier(VALID_STUDENT_ID_AMY_STR);
        ViewCommand viewCommand = new ViewCommand(identifier);
        String expected = ViewCommand.class.getCanonicalName() + "{identifier=" + identifier + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
