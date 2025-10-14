package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalScores.FINAL_SCORE_B;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ExamList;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Person;

public class EditScoreCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void resetExamList() {
        ExamList.setMaxScore("midterm", 100);
        ExamList.setMaxScore("final", 100);
    }

    @Test
    public void execute_validEdit_success() throws CommandException {
        EditScoreCommand command = new EditScoreCommand("midterm", "80");

        CommandResult result = command.execute(model);

        assertEquals(80, ExamList.getExamFromName("midterm").getMaxScore());

        assertEquals(String.format(EditScoreCommand.MESSAGE_EDIT_PERSON_SUCCESS, "midterm", 80),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateScore_throwsCommandException() {
        EditScoreCommand command = new EditScoreCommand("midterm", "100");

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_negativeScore_throwsCommandException() {
        EditScoreCommand command = new EditScoreCommand("midterm", "-10");

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_scoreLessThanRecorded_throwsCommandException() {
        ExamScores newExamScores = ALICE.getExamScores().updateScore(FINAL_SCORE_B);
        ScoreCommand scoreCommand = new ScoreCommand(IDENTIFIER_FIRST_PERSON, FINAL_SCORE_B);
        Person updatedAlice = new Person.PersonBuilder(ALICE)
                .withExamScores(newExamScores)
                .build();
        model.setPerson(ALICE, updatedAlice);
        EditScoreCommand command = new EditScoreCommand("final", "60");

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_invalidExam_throwsCommandException() {
        EditScoreCommand command = new EditScoreCommand("exam", "105");

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
