package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AttendCommand;
import seedu.address.model.person.StudentId;

public class AttendCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE);

    private AttendCommandParser parser = new AttendCommandParser();
    private AttendCommand attendCommand = new AttendCommand(new StudentId(VALID_STUDENT_ID_AMY), 5);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanThreeArgs_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " arg1" + " arg2" + " arg3" + " arg4",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validStudentId_success() {
        assertParseSuccess(parser, AttendCommand.COMMAND_WORD + " " + VALID_STUDENT_ID_AMY + " 5",
                attendCommand);
    }

    @Test
    public void parse_invalidStudentId_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " " + INVALID_STUDENT_ID_DESC + " 5",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_numberFormatException_failure() {
        assertParseFailure(parser, AttendCommand.COMMAND_WORD + " 1" + " random words instead of number",
                MESSAGE_INVALID_FORMAT);
    }
}
