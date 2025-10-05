package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;

/**
 * Command for adding tutorial Attendance for a person
 */
public class AttendCommand extends Command {

    public static final String COMMAND_WORD = "attend";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the attendance of the person identified "
            + "by the index number identified in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "TUTORIAL (must be between 1 and 11) "
            + "Example: " + COMMAND_WORD + " 4 5";
    public static final String MESSAGE_WRONG_TUTORIAL =
            "Tutorial has to be between 1 and 11";
    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "Added tutorial attendance for Person: %1$s";

    private final Index index;
    private final Integer tutorial;

    /**
     * updates person's Attendance to include this tutorial
     * @param index in the Address Book
     * @param tutorial number attended
     */
    public AttendCommand(Index index, Integer tutorial) {
        requireAllNonNull(index, tutorial);

        this.index = index;
        this.tutorial = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!Attendance.isValidTutorial(tutorial)) {
            throw new CommandException(MESSAGE_WRONG_TUTORIAL);
        }


        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getStudentId(),
                personToEdit.getAttendance().addAttendance(tutorial), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_ATTENDANCE_SUCCESS, Messages.format(personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendCommand)) {
            return false;
        }

        AttendCommand e = (AttendCommand) other;
        return index.equals(e.index)
                && tutorial.equals(e.tutorial);
    }
}
