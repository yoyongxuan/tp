package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalScores.FINAL_SCORE_B;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Exam;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;

public class ScoreCommandTest {

    public static final Score STANDARD_SCORE = Score.getRecordedScore(Exam.getExamFromName(VALID_EXAM), VALID_SCORE);
    public static final ScoreCommand STANDARD_COMMAND = new ScoreCommand(INDEX_FIRST_PERSON, STANDARD_SCORE);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        Person personToEdit = ALICE;
        ExamScores newExamScores = personToEdit.getExamScores().updateScore(FINAL_SCORE_B);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getStudentId(), personToEdit.getTags(), newExamScores);
        ScoreCommand scoreCommand = new ScoreCommand(INDEX_FIRST_PERSON, FINAL_SCORE_B);

        String updateScoreSuccessMessage = "Updated " + editedPerson.getName() + "'s "
                + FINAL_SCORE_B.getExam().getName() + " score";
        String expectedMessage = String.format(updateScoreSuccessMessage, FINAL_SCORE_B.toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(scoreCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ScoreCommand scoreCommand = new ScoreCommand(outOfBoundIndex, STANDARD_SCORE);

        assertCommandFailure(scoreCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {


        // same values -> returns true
        ScoreCommand commandWithSameValues = new ScoreCommand(INDEX_FIRST_PERSON, STANDARD_SCORE);
        assertTrue(STANDARD_COMMAND.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(STANDARD_COMMAND.equals(STANDARD_COMMAND));

        // null -> returns false
        assertFalse(STANDARD_COMMAND.equals(null));

        // different types -> returns false
        assertFalse(STANDARD_COMMAND.equals(new ClearCommand()));

        // different index -> returns false
        ScoreCommand commandWithDifferentIndex = new ScoreCommand(INDEX_SECOND_PERSON, STANDARD_SCORE);
        assertFalse(STANDARD_COMMAND.equals(commandWithDifferentIndex));

        // different score -> returns false
        Score differentScore = Score.getRecordedScore(Exam.getExamFromName(VALID_EXAM2), VALID_SCORE);
        ScoreCommand commandWithDifferenScore = new ScoreCommand(INDEX_SECOND_PERSON, differentScore);
        assertFalse(STANDARD_COMMAND.equals(commandWithDifferenScore));
    }
}
