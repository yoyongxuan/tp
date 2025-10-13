package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY_STR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_PERSON;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_STUDENT_ID_NOT_FOUND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Identifier;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
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

        String expectedMessage = "Viewing " + identifier.toString();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p ->
                new Identifier(p.getStudentId().toString()).equals(identifier));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(targetPerson), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidStudentId_noPersonFound() {
        ViewCommand viewCommand = new ViewCommand(IDENTIFIER_STUDENT_ID_NOT_FOUND);

        String expectedMessage = "Viewing " + IDENTIFIER_STUDENT_ID_NOT_FOUND.toString();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p ->
                new Identifier(p.getStudentId().toString()).equals(IDENTIFIER_FIRST_PERSON));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void toStringMethod() {
        Identifier identifier = new Identifier(VALID_STUDENT_ID_AMY_STR);
        ViewCommand viewCommand = new ViewCommand(identifier);
        String expected = ViewCommand.class.getCanonicalName() + "{studentId=" + identifier + "}";
        assertEquals(expected, viewCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
