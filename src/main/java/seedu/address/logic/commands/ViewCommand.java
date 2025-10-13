package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds a specific student using the given identifier and displays their information.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the student with the given student ID and displays their information."
            + " Parameters: STUDENT ID (must be a valid student id in contacts)\n"
            + "Example: " + COMMAND_WORD + " A0235410A\n";

    private final Predicate<Person> predicate;
    private final Identifier identifier;

    /**
     * finds and display's a Person that matches the given identifier
     * @param identifier of the person to view
     */
    public ViewCommand(Identifier identifier) {
        requireNonNull(identifier);
        this.identifier = identifier;
        this.predicate = person -> new Identifier(person.getStudentId().toString()).equals(this.identifier);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format("Viewing " + this.identifier.toString()));
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
        if (this.identifier != null) {
            return this.identifier.equals(otherViewCommand.identifier);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentId", this.identifier.toString())
                .toString();
    }
}
