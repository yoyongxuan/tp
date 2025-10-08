package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentId idAmy = new StudentId(VALID_STUDENT_ID_AMY);
        StudentId idBob = new StudentId(VALID_STUDENT_ID_BOB);
        ViewCommand viewAmy = new ViewCommand(idAmy);
        ViewCommand viewAmyCopy = new ViewCommand(idAmy);
        ViewCommand viewBob = new ViewCommand(idBob);

        assertTrue(viewAmy.equals(viewAmy));
        assertTrue(viewAmy.equals(viewAmyCopy));
        assertFalse(viewAmy.equals(viewBob));
    }

    @Test
    public void execute_validStudentId_success() throws CommandException {
        Person targetPerson = model.getFilteredPersonList().get(0);
        StudentId validStudentId = targetPerson.getStudentId();
        ViewCommand viewCommand = new ViewCommand(validStudentId);

        String expectedMessage = "Viewing " + validStudentId.toString();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p -> p.getStudentId().equals(validStudentId));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(targetPerson), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidStudentId_noPersonFound() {
        StudentId invalidId = new StudentId("A9999999Z");
        ViewCommand viewCommand = new ViewCommand(invalidId);

        String expectedMessage = "Viewing " + invalidId.toString();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p -> p.getStudentId().equals(invalidId));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void toStringMethod() {
        StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);
        ViewCommand viewCommand = new ViewCommand(studentId);
        String expected = ViewCommand.class.getCanonicalName() + "{studentId=" + studentId + "}";
        assertEquals(expected, viewCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
