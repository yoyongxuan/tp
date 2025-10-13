package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.person.Exam.FINAL;
import static seedu.address.model.person.Exam.MIDTERM;
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
    public void execute_unsortedList_success() {
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
    public void execute_sortByMidterm_success() {
        // Sort expectedModel manually using its inbuilt method
        expectedModel.sortPersonsByExam(MIDTERM);

        // Make new Sort Command by midterm
        assertCommandSuccess(new SortCommand(PREFIX_EXAM, MIDTERM), model,
                SortCommand.MESSAGE_SUCCESS, expectedModel);
        // Verify that the persons are actually in sorted order
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());
        assertTrue(isSortedByMidterm(persons));
    }

    @Test
    public void execute_sortByFinal_success() {
        // Sort expectedModel manually using its inbuilt method
        expectedModel.sortPersonsByExam(FINAL);

        // Make new Sort Command by final
        assertCommandSuccess(new SortCommand(PREFIX_EXAM, MIDTERM), model,
                SortCommand.MESSAGE_SUCCESS, expectedModel);
        // Verify that the persons are actually in sorted order
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());
        assertTrue(isSortedByFinal(persons));
    }

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
     * @return true if sorted by ascending alphabetical order
     */
    private boolean isSortedByName(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            String prev = persons.get(i - 1).getName().fullName.toLowerCase();
            String curr = persons.get(i).getName().fullName.toLowerCase();
            if (prev.compareTo(curr) >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Utility method to verify midterm score order.
     * @param persons List of persons sorted by midterm.
     * @return true if sorted by ascending midterm score.
     */
    private boolean isSortedByMidterm(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            Integer prev = persons.get(i - 1).getExamScores().getScoreByExam(MIDTERM).orElse(Integer.MAX_VALUE);
            Integer curr = persons.get(i).getExamScores().getScoreByExam(MIDTERM).orElse(Integer.MAX_VALUE);
            if (prev.compareTo(curr) >= 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Utility method to verify final score order.
     * @param persons List of persons sorted by final.
     * @return true if sorted by ascending final score.
     */
    private boolean isSortedByFinal(List<Person> persons) {
        for (int i = 1; i < persons.size(); i++) {
            Integer prev = persons.get(i - 1).getExamScores().getScoreByExam(FINAL).orElse(Integer.MAX_VALUE);
            Integer curr = persons.get(i).getExamScores().getScoreByExam(FINAL).orElse(Integer.MAX_VALUE);
            if (prev.compareTo(curr) >= 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void equals() {
        SortCommand sortByName = new SortCommand(PREFIX_NAME);
        SortCommand sortByMidterm = new SortCommand(PREFIX_EXAM, MIDTERM);
        SortCommand sortByFinal = new SortCommand(PREFIX_EXAM, FINAL);

        // True if same object
        assertTrue(sortByName.equals(sortByName));
        assertTrue(sortByMidterm.equals(sortByMidterm));

        // Same values -> returns true
        SortCommand sortByNameDuplicate = new SortCommand(PREFIX_NAME);
        assertTrue(sortByName.equals(sortByNameDuplicate));

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

