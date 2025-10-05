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
public class AttendCommandParser {
    /**
     * ensures AttendCommand is in correct format
     * @param args user input
     * @return AttendCommand to be executed
     * @throws ParseException
     */
    public AttendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argsSplit = args.split(" ", 3);

        Index index;
        try {
            index = ParserUtil.parseIndex(argsSplit[1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE), ive);
        }

        Integer tutorial = Integer.valueOf(argsSplit[2]);

        return new AttendCommand(index, tutorial);
    }
}
