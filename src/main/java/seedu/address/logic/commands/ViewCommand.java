package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Finds a specific student using the given student ID and displays their information.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the student with the given student ID and displays their information."
            + " Parameters: STUDENT ID (must be a valid student id in contacts)\n"
            + "Example: " + COMMAND_WORD + " A0235410A\n";

    public static final String MESSAGE_ARGUMENTS = "Student ID: %1$s";
    private final StudentId studentId;
    private final Predicate<Person> predicate;

    /**
     * @param studentId student ID of the person to view
     */
    public ViewCommand(StudentId studentId) {
        requireNonNull(studentId);
        this.studentId = studentId;
        this.predicate = person -> person.getStudentId().equals(studentId);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format("Viewing " + this.studentId.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        if (studentId != null) {
            return studentId.equals(otherViewCommand.studentId);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", studentId)
                .toString();
    }
}
