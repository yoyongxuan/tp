package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Sorts the address book based on a parameter
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of people. Use either "
            + PREFIX_NAME + " for sorting by names, or "
            + PREFIX_GRADE + " for sorting by grades (yet to be implemented)\n"
            + "Examples: sort " + PREFIX_NAME + " or sort " + PREFIX_GRADE;

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Sort command not implemented yet.";

    private final Prefix prefix;

    /**
     * Creates a new SortCommand to sort the list
     * @param prefix Prefix to sort by, either PREFIX_NAME or PREFIX_GRADE
     */
    public SortCommand(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (prefix.getPrefix()) {
        case "n/":
            model.sortPersonsByName();
            break;
        case "g/":
            // TODO: model.sortPersonsByGrade();
            throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;

        if (!(other instanceof SortCommand)) return false;

        SortCommand c = (SortCommand) other;
        return this.prefix.equals(c.prefix);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Prefix", prefix)
                .toString();
    }
}
