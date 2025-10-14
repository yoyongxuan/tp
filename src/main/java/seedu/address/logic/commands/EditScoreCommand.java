package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_SCORE;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Exam;
import seedu.address.model.person.ExamList;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Person;

/**
 * Edits the max score of an existing exam.
 */
public class EditScoreCommand extends Command {

    public static final String COMMAND_WORD = "maxscore";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the max score of the exam identified "
            + "to the new given score. "
            + "Existing values will be overwritten by the input values.\n"
            + PREFIX_EXAM + "EXAM "
            + PREFIX_MAX_SCORE + "SCORE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EXAM + "midterm "
            + PREFIX_MAX_SCORE + "50";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Exam %1$s max score to %2$d";
    public static final String MESSAGE_DUPLICATE_SCORE = "Given score is the same as the existing score for this exam.";
    public static final String MESSAGE_MAX_SCORE_INVALID =
            "Max score cannot be less than the recorded score of any student";
    public static final String MESSAGE_SCORE_INVALID_INTEGER = "Max score must be a non-negative integer";


    private final String examName;
    private final String score;

    /**
     * @param examName String name of the exam to edit
     * @param score String new max score
     */
    public EditScoreCommand(String examName, String score) {
        requireAllNonNull(examName, score);
        this.examName = examName;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            int newMaxScore = Integer.parseInt(this.score);
            Exam exam = ExamList.getExamFromName(this.examName);

            if (exam.getMaxScore() == newMaxScore) {
                throw new CommandException(MESSAGE_DUPLICATE_SCORE);
            }

            if (newMaxScore < 0) {
                throw new CommandException(MESSAGE_SCORE_INVALID_INTEGER);
            }

            Predicate<Person> predicate = person -> !person.getExamScores().newMaxScoreValid(exam, newMaxScore);
            if (!model.isNewMaxScoreValid(exam, newMaxScore)) {
                throw new CommandException(MESSAGE_MAX_SCORE_INVALID);
            }

            ExamList.setMaxScore(this.examName, newMaxScore);

            for (Person person : model.getFilteredPersonList()) { //to display the new max scores on the person cards
                ExamScores updateMaxScore = new ExamScores(person.getExamScores().getArrayOfScores());
                Person updatePerson = new Person.PersonBuilder(person).withExamScores(updateMaxScore).build();
                model.setPerson(person, updatePerson);
            }

            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, this.examName, newMaxScore));

        } catch (NumberFormatException e) {
            throw new CommandException(MESSAGE_SCORE_INVALID_INTEGER);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditScoreCommand)) {
            return false;
        }

        EditScoreCommand otherEditScoreCommand = (EditScoreCommand) other;
        return this.examName.equals(otherEditScoreCommand.examName)
                && this.score.equals(otherEditScoreCommand.score);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("examName", examName)
                .add("score", score)
                .toString();
    }
}
