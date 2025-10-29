package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.person.examscore.ExamList.FINAL;
import static seedu.address.model.person.examscore.ExamList.MIDTERM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.examscore.ExamList;

/**
 * Contains unit tests for SortCommandParser
 * @author ndhhh
 */
public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();
    private SortCommand sortByNameCommand = new SortCommand(PREFIX_NAME);
    private SortCommand sortByMidtermCommand = new SortCommand(PREFIX_EXAM, MIDTERM);
    private SortCommand sortByFinalCommand = new SortCommand(PREFIX_EXAM, FINAL);

    /**
     * Tests parsing a sort command by name with the 'n/' prefix.
     * @author ndhhh
     * @throws ParseException
     */
    @Test
    public void parse_name_success() throws ParseException {
        SortCommand c = parser.parse(" n/");
        assertEquals(sortByNameCommand, c);
    }

    /**
     * Tests parsing a sort command by midterm exam scores with the 'ex/midterm' prefix.
     * @author ndhhh
     * @throws ParseException
     */
    @Test
    public void parse_examMidterm_success() throws ParseException {
        SortCommand c = parser.parse(" ex/midterm");
        assertEquals(sortByMidtermCommand, c);
    }

    /**
     * Tests parsing a sort command by final exam scores with the 'ex/final' prefix.
     * @author ndhhh
     * @throws ParseException
     */
    @Test
    public void parse_examFinal_success() throws ParseException {
        SortCommand c = parser.parse(" ex/final");
        assertEquals(sortByFinalCommand, c);
    }

    /**
     * Tests parsing a sort command by exam scores with no argument.
     * Test should throw an ParseException
     * @author ndhhh
     */
    @Test
    public void parse_examFinalNoArg_throwsException() {
        // Make new SortCommand
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(" ex/");
        });

        // Should have same error message
        String expectedMessage = String.format(ExamList.MESSAGE_CONSTRAINTS);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests parsing a sort command by exam scores with invalid argument.
     * Test should throw a ParseException
     * @author ndhhh
     */
    @Test
    public void parse_examFinalInvalidArg_throwsException() {
        // Make new SortCommand
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(" ex/practical");
        });

        // Should have same error message
        String expectedMessage = String.format(ExamList.MESSAGE_CONSTRAINTS);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests parsing a sort command with both name and exam prefixes.
     * Test should throw a ParseException
     * @author ndhhh
     */
    @Test
    public void parse_nameAndExam_throwsException() {
        // Make new SortCommand
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(" n/ ex/midterm");
        });

        // Should have same error message
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests parsing a sort command by both exam and name prefixes.
     * Test should throw a ParseException
     * @author ndhhh
     */
    @Test
    public void parse_examAndName_throwsException() {
        // Make new SortCommand
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(" ex/midterm n/");
        });

        // Should have same error message
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests parsing a sort command by email prefix.
     * Test should throw a ParseException
     * @author ndhhh
     */
    @Test
    public void parse_prefixEmail_throwsException() {
        // Make new SortCommand by email
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(" e/");
        });

        // Should have same error message
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests parsing a sort command with no prefix.
     * Test should throw a ParseException
     * @author ndhhh
     */
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
