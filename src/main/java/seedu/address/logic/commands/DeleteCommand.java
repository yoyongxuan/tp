package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list or by student ID.\n"
            + "Parameters: INDEX (must be a positive integer) OR STUDENT ID (must be a valid student id in contacts)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " A0235410A\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Identifier identifier;
    /**
     * Creates a DeleteCommand to delete the {@code Person} at the specified {@code identifier}.
     * @param identifier The identifier to delete at.
     */
    public DeleteCommand(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Person personToDelete = identifier.retrievePerson(model);
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        } catch (PersonNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        return identifier.equals(otherDeleteCommand.identifier);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", identifier)
                .toString();
    }
}
