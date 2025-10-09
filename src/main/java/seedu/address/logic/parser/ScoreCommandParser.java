package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Score;

/**
 * Parses input arguments and creates a new ScoreCommand object
 */
public class ScoreCommandParser implements Parser<ScoreCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScoreCommand
     * and returns an ScoreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScoreCommand parse(String args) throws ParseException {
        System.out.println("ScoreCommandParser ran");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SCORE, PREFIX_EXAM);

        if (!arePrefixesPresent(argMultimap, PREFIX_SCORE, PREFIX_EXAM)
                || argMultimap.getPreamble().isEmpty()) {
            System.out.println(!arePrefixesPresent(argMultimap, PREFIX_SCORE, PREFIX_EXAM));
            System.out.println(argMultimap.getPreamble().isEmpty());
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScoreCommand.MESSAGE_USAGE));
        }



        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScoreCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EXAM, PREFIX_SCORE);

        String examName = argMultimap.getValue(PREFIX_EXAM).get();
        String score = argMultimap.getValue(PREFIX_SCORE).get();

        Score newScore = ParserUtil.parseScore(examName, score);


        return new ScoreCommand(index, newScore);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
