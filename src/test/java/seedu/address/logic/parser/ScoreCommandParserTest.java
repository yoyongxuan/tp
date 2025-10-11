package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SCORE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ScoreCommand;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Score;

public class ScoreCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScoreCommand.MESSAGE_USAGE);

    private ScoreCommandParser parser = new ScoreCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, EXAM_DESC + SCORE_DESC, MESSAGE_INVALID_FORMAT);

        // no exam specified
        assertParseFailure(parser, "1" + EXAM_DESC, MESSAGE_INVALID_FORMAT);

        // no score specified
        assertParseFailure(parser, "1" + SCORE_DESC, MESSAGE_INVALID_FORMAT);

        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EXAM_DESC + SCORE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + EXAM_DESC + SCORE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EXAM_DESC + SCORE_DESC,
                Exam.MESSAGE_CONSTRAINTS); // invalid exam
        assertParseFailure(parser, "1" + INVALID_SCORE_DESC + EXAM_DESC,
                Exam.getExamFromName(VALID_EXAM).getMessageScoreConstraints()); // invalid score

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EXAM_DESC + INVALID_SCORE_DESC,
                Exam.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        assertParseFailure(parser, "1" + EXAM_DESC + EXAM_DESC + SCORE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EXAM)); // multiple exam

        assertParseFailure(parser, "1" + EXAM_DESC + SCORE_DESC + SCORE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SCORE)); // multiple score

        assertParseFailure(parser, "1" + EXAM_DESC + EXAM_DESC + SCORE_DESC + SCORE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EXAM, PREFIX_SCORE)); // multiple exam and score
    }

    @Test
    public void parse_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        ScoreCommand expectedCommand = new ScoreCommand(INDEX_FIRST_PERSON,
                Score.getRecordedScore(Exam.getExamFromName(VALID_EXAM), VALID_SCORE));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + EXAM_DESC + SCORE_DESC, expectedCommand);
    }
}
