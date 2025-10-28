package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Command for adding tutorial Attendance for a person
 */
public class AttendCommand extends Command {

    public static final String COMMAND_WORD = "attend";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the attendance of the person identified "
            + "by the index number used in the displayed person list or by student number\n"
            + "for the provided tutorial number. If attendance for that tutorial has already been taken,\n"
            + "attendance for that tutorial will be removed instead.\n"
            + "Parameters: INDEX (must be a positive integer) OR STUDENT ID (must be a valid student id in contacts)\n"
            + "TUTORIAL (must be between 1 and " + Attendance.NUMBER_OF_TUTORIALS + ") \n"
            + "Example: " + COMMAND_WORD + " 4 5\n"
            + "Example: " + COMMAND_WORD + " A0000000X 5";
    public static final String MESSAGE_WRONG_TUTORIAL =
            "Tutorial has to be between 1 and " + Attendance.NUMBER_OF_TUTORIALS;
    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "%1$s tutorial %2$s attendance for %3$s";

    private final Identifier identifier;
    private final Index tutorial;

    /**
     * updates person's Attendance to include this tutorial
     * @param identifier of the person whose attendance to be updated
     * @param tutorial number attended
     */
    public AttendCommand(Identifier identifier, Index tutorial) {
        requireAllNonNull(identifier, tutorial);

        this.identifier = identifier;
        this.tutorial = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit;

        try {
            personToEdit = identifier.retrievePerson(model);
        } catch (PersonNotFoundException e) {
            throw new CommandException(identifier.getMessageIdentifierNotFound());
        }

        if (!Attendance.isValidTutorial(tutorial)) {
            throw new CommandException(MESSAGE_WRONG_TUTORIAL);
        }

        Person editedPerson = new Person.PersonBuilder(personToEdit)
                .withAttendance(personToEdit.getAttendance().invertAttendanceForTutorial(tutorial))
                .build();
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        boolean isTutorialAttendanceTaken = editedPerson.getAttendance().getAttendanceForTutorial(tutorial);

        return new CommandResult(generateSuccessMessage(editedPerson, tutorial, isTutorialAttendanceTaken));
    }

    private String generateSuccessMessage(Person personToEdit, Index tutorial, boolean isTutorialAttendanceAdded) {
        String prefix = isTutorialAttendanceAdded ? "Added" : "Removed";
        return String.format(
                MESSAGE_ADD_ATTENDANCE_SUCCESS, prefix, tutorial.getOneBased(), Messages.format(personToEdit));
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

        AttendCommand otherAttendCommand = (AttendCommand) other;
        return identifier.equals(otherAttendCommand.identifier)
                && tutorial.equals(otherAttendCommand.tutorial);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("identifier", identifier)
                .add("tutorial", tutorial)
                .toString();
    }
}
