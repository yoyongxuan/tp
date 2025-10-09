package seedu.address.testutil;

import seedu.address.model.person.Exam;
import seedu.address.model.person.Score;

/**
 * A utility class containing a list of {@code ExamScore} objects to be used in tests.
 */
public class TypicalScores {
    public static final Score MIDTERM_SCORE_UNRECORDED = Score.getUnrecordedScore(Exam.MIDTERM);
    public static final Score FINAL_SCORE_UNRECORDED = Score.getUnrecordedScore(Exam.FINAL);
    public static final Score MIDTERM_SCORE_A = Score.getRecordedScore(Exam.MIDTERM, "50");
    public static final Score MIDTERM_SCORE_B = Score.getRecordedScore(Exam.MIDTERM, "60");
    public static final Score FINAL_SCORE_A = Score.getRecordedScore(Exam.FINAL, "50");
    public static final Score FINAL_SCORE_B = Score.getRecordedScore(Exam.FINAL, "80");
}
