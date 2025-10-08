package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Command for adding tutorial Attendance for a person
 */
public class AttendCommand extends Command {

    public static final String COMMAND_WORD = "attend";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the attendance of the person identified "
            + "by the index number used in the displayed person list or by student number.\n"
            + "Parameters: INDEX (must be a positive integer) OR STUDENT ID (must be a valid student id in contacts)\n"
            + "TUTORIAL (must be between 1 and " + Attendance.NUMBER_OF_TUTORIALS + ") \n"
            + "Example: " + COMMAND_WORD + " 4 5\n"
            + "Example: " + COMMAND_WORD + " A0000000X 5";
    public static final String MESSAGE_WRONG_TUTORIAL =
            "Tutorial has to be between 1 and " + Attendance.NUMBER_OF_TUTORIALS;
    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "Added tutorial attendance for Person: %1$s";

    private final Index index;
    private final StudentId studentId;
    private final Index tutorial;

    /**
     * updates person's Attendance to include this tutorial
     * @param index in the Address Book
     * @param tutorial number attended
     */
    public AttendCommand(Index index, Integer tutorial) {
        requireAllNonNull(index, tutorial);

        this.index = index;
        this.studentId = null;
        this.tutorial = Index.fromOneBased(tutorial);
    }

    /**
     * updates person's Attendance to include this tutorial
     * @param studentId in the Address Book
     * @param tutorial number attended
     */
    public AttendCommand(StudentId studentId, Integer tutorial) {
        requireAllNonNull(studentId, tutorial);

        this.index = null;
        this.studentId = studentId;
        this.tutorial = Index.fromOneBased(tutorial);
    }

    /**
     * Creates an AttendCommand to delete the {@code Person} with the specified {@code index}
     * index of the contact to add attendance to.
     */
    public CommandResult attendWithIndex(Model model) throws CommandException {
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

    /**
     * Creates an AttendCommand to delete the {@code Person} with the specified {@code studentId}
     * student id of the contact to add attendance to.
     */
    public CommandResult attendWithStudentId(Model model) throws CommandException {
        Person personToEdit = model.getFilteredPersonList().stream()
                .filter(person -> person.getStudentId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new CommandException(MESSAGE_INVALID_STUDENT_ID_DISPLAYED_INDEX));

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getStudentId(),
                personToEdit.getAttendance().addAttendance(tutorial), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (studentId != null) {
            return attendWithStudentId(model);
        }
        return attendWithIndex(model);
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

        AttendCommand otherAttendCommand = (AttendCommand) other;
        if (index != null) {
            return index.equals(otherAttendCommand.index)
                    && tutorial.equals(otherAttendCommand.tutorial);
        } else if (studentId != null) {
            return studentId.equals(otherAttendCommand.studentId)
                    && tutorial.equals(otherAttendCommand.tutorial);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
