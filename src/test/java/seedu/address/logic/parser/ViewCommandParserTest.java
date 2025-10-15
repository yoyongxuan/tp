package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY_STR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Identifier;
import seedu.address.logic.commands.ViewCommand;

/**
 * Parses the ViewCommand
 */
public class ViewCommandParserTest {

    private static final String INVALID_MESSAGE =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validStudentId_returnsViewCommand() {
        Identifier identifier = new Identifier(VALID_STUDENT_ID_AMY_STR);
        ViewCommand expectedCommand = new ViewCommand(identifier);
        assertParseSuccess(parser, VALID_STUDENT_ID_AMY_STR, expectedCommand);
    }

    @Test
    public void parse_validIndex_returnsViewCommand() {
        ViewCommand expectedCommand = new ViewCommand(IDENTIFIER_FIRST_PERSON);
        assertParseSuccess(parser, "1", expectedCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-5", INVALID_MESSAGE);
        assertParseFailure(parser, "0", INVALID_MESSAGE);
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "", INVALID_MESSAGE);
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        assertParseFailure(parser, ViewCommand.COMMAND_WORD + " " + INVALID_STUDENT_ID_DESC, INVALID_MESSAGE);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", INVALID_MESSAGE);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", INVALID_MESSAGE);
    }

}
