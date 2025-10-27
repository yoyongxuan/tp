package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AttendCommand.MESSAGE_WRONG_TUTORIAL;

import seedu.address.commons.core.Identifier;
import seedu.address.commons.core.index.Index;
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
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argsSplit = args.split("\\s+", 3);

        if (argsSplit.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE));
        }

        Identifier identifier;
        Index tutorial;
        String tutorialString = argsSplit[2];

        try {
            identifier = ParserUtil.parseIdentifier(argsSplit[1]);
            if (!Index.isValidOneBasedIndex(tutorialString)) {
                throw new ParseException(
                        String.format(MESSAGE_WRONG_TUTORIAL, AttendCommand.MESSAGE_USAGE));
            }
            tutorial = Index.fromOneBased(Integer.parseInt(tutorialString));
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_WRONG_TUTORIAL)) {
                throw new ParseException(
                        String.format(MESSAGE_WRONG_TUTORIAL, AttendCommand.MESSAGE_USAGE), pe);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE), pe);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE), nfe);
        }

        return new AttendCommand(identifier, tutorial);
    }
}
