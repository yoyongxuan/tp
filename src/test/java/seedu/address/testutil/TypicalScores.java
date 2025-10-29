package seedu.address.testutil;

import seedu.address.model.person.examscore.ExamList;
import seedu.address.model.person.examscore.Score;

/**
 * A utility class containing a list of {@code ExamScore} objects to be used in tests.
 */
public class TypicalScores {
    public static final Score MIDTERM_SCORE_UNRECORDED = Score.getUnrecordedScore(ExamList.MIDTERM);
    public static final Score FINAL_SCORE_UNRECORDED = Score.getUnrecordedScore(ExamList.FINAL);
    public static final Score MIDTERM_SCORE_A = Score.getRecordedScore(ExamList.MIDTERM, "50");
    public static final Score MIDTERM_SCORE_B = Score.getRecordedScore(ExamList.MIDTERM, "60");
    public static final Score FINAL_SCORE_A = Score.getRecordedScore(ExamList.FINAL, "50");
    public static final Score FINAL_SCORE_B = Score.getRecordedScore(ExamList.FINAL, "80");
}
