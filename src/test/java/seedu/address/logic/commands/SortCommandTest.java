package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.person.examscore.ExamList.FINAL;
import static seedu.address.model.person.examscore.ExamList.MIDTERM;
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

/**
 * Contains unit tests for Sort command,
 * with model stubs to compare before and after execution
 * @author ndhhh
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    /**
     * Sets up the model and expectedModel before each test
     * model is unsorted at the start of each test.
     * expectedModel is sorted and used to compare against
     * model after sort command execution.
     * @author ndhhh
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getUnsortedAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    /**
     * Tests sorting an unsorted list by name.
     * @author ndhhh
     */
    @Test
    public void execute_unsortedList_success() {
        // Sort expectedModel manually using its inbuilt method
        expectedModel.sortPersonsByName();

        // Execute SortCommand on the real model
        assertCommandSuccess(new SortCommand(PREFIX_NAME), model,
                SortCommand.MESSAGE_SUCCESS_NAME, expectedModel);

        // Verify that the persons are actually in sorted order
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());
        assertTrue(isSortedByName(persons));
    }

    /**
     * Tests sorting an already sorted list by name.
     * @author ndhhh
     */
    @Test
    public void execute_alreadySortedList_success() {
        // Sort both models first
        model.sortPersonsByName();
        expectedModel.sortPersonsByName();

        // Then run the command again
        assertCommandSuccess(new SortCommand(PREFIX_NAME), model,
                SortCommand.MESSAGE_SUCCESS_NAME, expectedModel);
    }

    /**
     * Tests sorting by midterm exam scores.
     * @author ndhhh
     */
    @Test
    public void execute_sortByMidterm_success() {
        // Sort expectedModel manually using its inbuilt method
        expectedModel.sortPersonsByExam(MIDTERM);

        // Make new Sort Command by midterm
        assertCommandSuccess(new SortCommand(PREFIX_EXAM, MIDTERM), model,
                SortCommand.MESSAGE_SUCCESS_EXAM, expectedModel);
        // Verify that the persons are actually in sorted order
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());
        assertTrue(isSortedByMidterm(persons));
    }

    /**
     * Tests sorting by final exam scores.
     * @author ndhhh
     */
    @Test
    public void execute_sortByFinal_success() {
        // Sort expectedModel manually using its inbuilt method
        expectedModel.sortPersonsByExam(FINAL);

        // Make new Sort Command by final
        assertCommandSuccess(new SortCommand(PREFIX_EXAM, FINAL), model,
                SortCommand.MESSAGE_SUCCESS_EXAM, expectedModel);
        // Verify that the persons are actually in sorted order
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());
        assertTrue(isSortedByFinal(persons));
    }

    /**
     * Tests sorting by exam with null exam type.
     * @author ndhhh
     */
    @Test
    public void execute_sortByNullExam_throwsError() {
        // Make new Sort Command by exam, with EXAM = null
        SortCommand c = new SortCommand(PREFIX_EXAM, null);
        Exception exception = assertThrows(CommandException.class, () -> {
            c.execute(model);
        });

        // Should have the same error message
        String expectedMessage = SortCommand.MESSAGE_USAGE;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests sorting by unsupported prefix (email).
     * @author ndhhh
     */
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
     * Utility method to verify alphabetical order.
     * @author ndhhh
     * @return true if sorted by ascending alphabetical order
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

    /**
     * Utility method to verify midterm score order.
     * @author ndhhh
     * @param persons List of persons sorted by midterm.
     * @return true if sorted by ascending midterm score.
     */
    private boolean isSortedByMidterm(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            Integer prev = persons.get(i - 1).getExamScores().getScoreByExam(MIDTERM).orElse(Integer.MAX_VALUE);
            Integer curr = persons.get(i).getExamScores().getScoreByExam(MIDTERM).orElse(Integer.MAX_VALUE);
            if (prev.compareTo(curr) > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Utility method to verify final score order.
     * @author ndhhh
     * @param persons List of persons sorted by final.
     * @return true if sorted by ascending final score.
     */
    private boolean isSortedByFinal(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            Integer prev = persons.get(i - 1).getExamScores().getScoreByExam(FINAL).orElse(Integer.MAX_VALUE);
            Integer curr = persons.get(i).getExamScores().getScoreByExam(FINAL).orElse(Integer.MAX_VALUE);
            if (prev.compareTo(curr) > 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void equals_test() {
        SortCommand sortByName = new SortCommand(PREFIX_NAME);
        SortCommand sortByMidterm = new SortCommand(PREFIX_EXAM, MIDTERM);
        SortCommand sortByFinal = new SortCommand(PREFIX_EXAM, FINAL);

        // True if same object
        assertTrue(sortByName.equals(sortByName));
        assertTrue(sortByMidterm.equals(sortByMidterm));

        // Same values -> returns true
        SortCommand sortByNameDuplicate = new SortCommand(PREFIX_NAME);
        assertTrue(sortByName.equals(sortByNameDuplicate));

        SortCommand sortByMidtermDuplicate = new SortCommand(PREFIX_EXAM, MIDTERM);
        assertTrue(sortByMidterm.equals(sortByMidtermDuplicate));

        // different types -> returns false
        assertFalse(sortByName.equals(1));

        // null -> returns false
        assertFalse(sortByName.equals(null));

        // different param -> returns false
        assertFalse(sortByName.equals(sortByFinal));
        assertFalse(sortByName.equals(sortByMidterm));

        // different exam -> returns false
        assertFalse(sortByMidterm.equals(sortByFinal));
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand(PREFIX_NAME);
        String expected = SortCommand.class.getCanonicalName() + "{Prefix=" + PREFIX_NAME + "}";
        assertEquals(expected, sortCommand.toString());
    }

}

