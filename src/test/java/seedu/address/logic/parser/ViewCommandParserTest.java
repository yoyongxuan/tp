package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY_STR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Identifier;
import seedu.address.logic.commands.ViewCommand;

/**
 * Parses the ViewCommand
 */
public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validStudentId_returnsViewCommand() {
        Identifier identifier = new Identifier(VALID_STUDENT_ID_AMY_STR);
        ViewCommand expectedCommand = new ViewCommand(identifier);
        assertParseSuccess(parser, VALID_STUDENT_ID_AMY_STR, expectedCommand);
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        assertParseFailure(parser, ViewCommand.COMMAND_WORD + " " + INVALID_STUDENT_ID_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
