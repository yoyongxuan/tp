package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ExamScores;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;


/**
 * Updates the exam scores of an existing person in the address book with a new score.
 */
public class ScoreCommand extends Command {
    public static final String COMMAND_WORD = "score";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records the exam score of the person identified "
            + "by the index number or student ID used in the displayed person list and the given exam name. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_EXAM + "EXAM "
            + PREFIX_SCORE + "SCORE "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EXAM + "midterm "
            + PREFIX_SCORE + "50";

    private final Index index;
    private final Score score;

    /**
     * @param index        of the person in the filtered person list to update
     * @param score        score to update the person with
     */
    public ScoreCommand(Index index, Score score) {
        requireNonNull(index);
        requireNonNull(score);

        this.index = index;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        ExamScores newExamScores = personToEdit.getExamScores().updateScore(this.score);

        Person editedPerson = new Person.PersonBuilder(personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getStudentId()).withTags(personToEdit.getTags())
                .withExamScores(newExamScores).build();

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        String updateScoreSuccessMessage = "Updated " + editedPerson.getName() + "'s "
                + score.getExam().getName() + " score";
        return new CommandResult(String.format(updateScoreSuccessMessage, score));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScoreCommand)) {
            return false;
        }

        ScoreCommand otherScoreCommand = (ScoreCommand) other;
        return index.equals(otherScoreCommand.index)
                && score.equals(otherScoreCommand.score);
    }
}
