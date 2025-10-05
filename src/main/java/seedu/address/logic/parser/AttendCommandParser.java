package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the AttendCommand
 */
public class AttendCommandParser implements Parser<AttendCommand> {
    /**
     * ensures AttendCommand is in correct format
     * @param args user input
     * @return AttendCommand to be executed
     * @throws ParseException
     */
    public AttendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argsSplit = args.split(" ", 3);

        if (argsSplit.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE));
        }

        Index index;
        Integer tutorial;

        try {
            index = ParserUtil.parseIndex(argsSplit[1]);
            tutorial = Integer.valueOf(argsSplit[2]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE), ive);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE), nfe);
        }

        return new AttendCommand(index, tutorial);
    }
}
