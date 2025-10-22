package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalScores.FINAL_SCORE_B;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Exam;
import seedu.address.model.person.ExamList;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Person;

public class EditScoreCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void resetExamList() {
        ExamList.setMaxScore("midterm", 70);
        ExamList.setMaxScore("final", 100);
    }

    @Test
    public void execute_validEdit_success() throws CommandException {
        Exam exam = ExamList.getExamFromName("midterm");
        int newMaxScore = exam.getMaxScore() + 5;
        EditScoreCommand command = new EditScoreCommand(exam, newMaxScore);

        CommandResult result = command.execute(model);

        assertEquals(newMaxScore, ExamList.getExamFromName("midterm").getMaxScore());
    }

    @Test
    public void execute_duplicateScore_throwsCommandException() {
        EditScoreCommand command = new EditScoreCommand(ExamList.getExamFromName("midterm"), 70);

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_scoreLessThanRecorded_throwsCommandException() {
        ExamScores newExamScores = ALICE.getExamScores().updateScore(FINAL_SCORE_B);
        Person updatedAlice = new Person.PersonBuilder(ALICE)
                .withExamScores(newExamScores)
                .build();
        model.setPerson(ALICE, updatedAlice);
        EditScoreCommand command = new EditScoreCommand(ExamList.getExamFromName("final"), 60);

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
