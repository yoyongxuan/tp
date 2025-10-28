package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.examscore.Exam;

/**
 * Parses input arguments and creates a new SortCommand object.
 * @author ndhhh
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        // If empty args, just throw exception
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        // If just PREFIX_NAME, return SortCommand
        if (args.trim().equals(PREFIX_NAME.getPrefix())) {
            return new SortCommand(PREFIX_NAME);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EXAM);

        if (isPrefixPresent(argMultimap, PREFIX_NAME) && isPrefixPresent(argMultimap, PREFIX_EXAM)) {
            // Cannot have 2 or more existing prefixes!
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        } else if (isPrefixPresent(argMultimap, PREFIX_EXAM)) {
            String examName = argMultimap.getValue(PREFIX_EXAM).get();
            Exam examType = ParserUtil.parseExam(examName);
            return new SortCommand(PREFIX_EXAM, examType);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if the prefix in check exists in the given {@code ArgumentMultimap}.
     * @author ndhhh
     * @param argumentMultimap map containing prefixes
     * @param prefix to be checked
     * @return true if prefix exists in map.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getAllValues(prefix) != null && !argumentMultimap.getAllValues(prefix).isEmpty();
    }
}
