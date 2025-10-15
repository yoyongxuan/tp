package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Finds a specific student using the given identifier and displays their information.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the student by student ID or the index.\n"
            + " Parameters: INDEX (must be a positive integer) OR STUDENT ID (must be a valid student id in contacts)\n"
            + "Example: " + COMMAND_WORD + " 4\n"
            + "Example: " + COMMAND_WORD + " A0000000X";

    private final Identifier identifier;

    /**
     * finds and display's a Person that matches the given identifier
     * @param identifier of the person to view
     */
    public ViewCommand(Identifier identifier) {
        requireNonNull(identifier);
        this.identifier = identifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person;
        try {
            person = this.identifier.retrievePersonFromAddressBook(model);
        } catch (PersonNotFoundException e) {
            throw new CommandException(this.identifier.getMessageIdentifierNotFound());
        }
        Predicate<Person> predicate = p -> p.equals(person);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format("Viewing " + person.getName()));
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
        return this.identifier.equals(otherViewCommand.identifier);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("identifier", this.identifier.toString())
                .toString();
    }
}
