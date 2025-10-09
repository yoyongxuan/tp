package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();
    private SortCommand sortByNameCommand = new SortCommand(PREFIX_NAME);
    private SortCommand sortByGradeCommand = new SortCommand(PREFIX_GRADE);

    @Test
    public void parse_prefixName_success() throws ParseException {
        SortCommand c = parser.parse("n/");
        assertEquals(sortByNameCommand, c);
    }

    @Test
    public void parse_prefixGrade_success() throws ParseException {
        SortCommand c = parser.parse("g/");
        assertEquals(sortByGradeCommand, c);
    }

    @Test
    public void parse_prefixEmail_throwsException() {
        // Make new SortCommand by email
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse("e/");
        });

        // Should have same error message
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parse_emptyPrefix_throwsException() {
        // Make new SortCommand with no prefix
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse("");
        });

        // Should have same error message
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
