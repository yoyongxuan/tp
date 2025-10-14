package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ExamList;

public class EditScoreCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScoreCommand.MESSAGE_USAGE);

    private final EditScoreCommandParser parser = new EditScoreCommandParser();

    @BeforeEach
    public void resetExamList() {
        ExamList.setMaxScore("midterm", 100);
        ExamList.setMaxScore("final", 100);
    }

    @Test
    public void parse_success() {
        EditScoreCommand expectedCommand =
                new EditScoreCommand(VALID_EXAM, "105");
        String userinput = EditScoreCommand.COMMAND_WORD + " "
                + PREFIX_EXAM + VALID_EXAM + " " + PREFIX_MAX_SCORE + "105";
        assertParseSuccess(parser, userinput, expectedCommand);
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, SCORE_DESC, MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, EXAM_DESC, MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicatePrefix_throwsParseException() {
        String inputDuplicateExam = " " + PREFIX_EXAM + "midterm " + PREFIX_EXAM + "final "
                + PREFIX_MAX_SCORE + "80";
        String inputDuplicateScore = " " + PREFIX_EXAM + "midterm " + PREFIX_MAX_SCORE + "80 "
                + PREFIX_MAX_SCORE + "90";

        assertThrows(ParseException.class, () -> parser.parse(inputDuplicateExam));
        assertThrows(ParseException.class, () -> parser.parse(inputDuplicateScore));
    }
}
