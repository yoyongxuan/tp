package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalPersons.getUnsortedAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getUnsortedAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_unsortedList_sortsSuccessfully() {
        // Sort expectedModel manually using its inbuilt method
        expectedModel.sortPersonsByName();

        // Execute SortCommand on the real model
        assertCommandSuccess(new SortCommand(PREFIX_NAME), model,
                SortCommand.MESSAGE_SUCCESS, expectedModel);

        // Verify that the persons are actually in sorted order
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());
        assertTrue(isSortedByName(persons));
    }

    @Test
    public void execute_alreadySortedList_success() {
        // Sort both models first
        model.sortPersonsByName();
        expectedModel.sortPersonsByName();

        // Then run the command again
        assertCommandSuccess(new SortCommand(PREFIX_NAME), model,
                SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortByGrade_throwsException() {
        // Make new Sort Command by grade
        SortCommand c = new SortCommand(PREFIX_GRADE);
        Exception exception = assertThrows(CommandException.class, () -> {
            c.execute(model);
        });

        // Should have same error message
        String expectedMessage = SortCommand.MESSAGE_NOT_IMPLEMENTED_YET;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void execute_sortByOtherPrefix_throwsError() {
        // Make new Sort Command by email
        SortCommand c = new SortCommand(PREFIX_EMAIL);
        Exception exception = assertThrows(CommandException.class, () -> {
            c.execute(model);
        });

        // Should have the same error message
        String expectedMessage = SortCommand.MESSAGE_USAGE;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Utility method to verify alphabetical order
     */
    private boolean isSortedByName(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            String prev = persons.get(i - 1).getName().fullName.toLowerCase();
            String curr = persons.get(i).getName().fullName.toLowerCase();
            if (prev.compareTo(curr) > 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void equals() {
        SortCommand sortByName = new SortCommand(PREFIX_NAME);
        SortCommand sortByGrade = new SortCommand(PREFIX_GRADE);

        // True if same object
        assertTrue(sortByName.equals(sortByName));
        assertTrue(sortByGrade.equals(sortByGrade));

        // Same values -> returns true
        SortCommand sortByNameDuplicate = new SortCommand(PREFIX_NAME);
        assertTrue(sortByName.equals(sortByNameDuplicate));

        // different types -> returns false
        assertFalse(sortByName.equals(1));

        // null -> returns false
        assertFalse(sortByName.equals(null));

        // different param -> returns false
        assertFalse(sortByName.equals(sortByGrade));
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand(PREFIX_NAME);
        String expected = SortCommand.class.getCanonicalName() + "{Prefix=" + PREFIX_NAME + "}";
        assertEquals(expected, sortCommand.toString());
    }

}

