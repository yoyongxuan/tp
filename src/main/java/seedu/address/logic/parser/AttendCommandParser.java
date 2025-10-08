package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses the AttendCommand
 */
public class AttendCommandParser implements Parser<AttendCommand> {
    /**
     * ensures AttendCommand is in correct format
     * @param args user input
     * @return AttendCommand to be executed
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argsSplit = args.split("\\s+", 3);

        if (argsSplit.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE));
        }

        Index index;
        StudentId studentId;
        Integer tutorial;

        try {
            if (argsSplit[1].matches("\\d+")) {
                index = ParserUtil.parseIndex(argsSplit[1]);
                studentId = null;
            } else {
                index = null;
                studentId = ParserUtil.parseStudentId(argsSplit[1]);
            }
            tutorial = Integer.valueOf(argsSplit[2]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE), pe);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE), nfe);
        }

        if (index != null) {
            return new AttendCommand(index, tutorial);
        }
        return new AttendCommand(studentId, tutorial);
    }
}
