package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.StudentId;


/**
 * Parses the ViewCommand
 */
public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validStudentId_returnsViewCommand() {
        StudentId studentId = new StudentId("A0235410A");
        ViewCommand expectedCommand = new ViewCommand(studentId);
        assertParseSuccess(parser, "A0235410A", expectedCommand);
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        assertParseFailure(parser, "12345",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
