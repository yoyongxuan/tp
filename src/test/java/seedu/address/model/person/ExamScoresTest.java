package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalExamScores.EMPTY_EXAM_SCORES;
import static seedu.address.testutil.TypicalScores.FINAL_SCORE_A;
import static seedu.address.testutil.TypicalScores.FINAL_SCORE_UNRECORDED;
import static seedu.address.testutil.TypicalScores.MIDTERM_SCORE_A;
import static seedu.address.testutil.TypicalScores.MIDTERM_SCORE_B;
import static seedu.address.testutil.TypicalScores.MIDTERM_SCORE_UNRECORDED;

import org.junit.jupiter.api.Test;


public class ExamScoresTest {

    @Test
    public void getEmptyExamScores() {
        assertEquals(MIDTERM_SCORE_UNRECORDED, ExamScores.getEmptyExamScores().arrayOfScores[0]);
        assertEquals(FINAL_SCORE_UNRECORDED, ExamScores.getEmptyExamScores().arrayOfScores[1]);
        assertEquals(MIDTERM_SCORE_UNRECORDED.toString() + "\n"
                        + FINAL_SCORE_UNRECORDED.toString(),
                ExamScores.getEmptyExamScores().toString());
    }

    @Test
    public void updateScore() {
        assertEquals(EMPTY_EXAM_SCORES.updateScore(MIDTERM_SCORE_A).arrayOfScores[0],
                MIDTERM_SCORE_A);
        assertEquals(EMPTY_EXAM_SCORES.updateScore(MIDTERM_SCORE_A).updateScore(MIDTERM_SCORE_B).arrayOfScores[0],
                MIDTERM_SCORE_B);
        assertEquals(EMPTY_EXAM_SCORES.updateScore(MIDTERM_SCORE_A).updateScore(FINAL_SCORE_A).arrayOfScores[0],
                MIDTERM_SCORE_A);
        assertEquals(EMPTY_EXAM_SCORES.updateScore(MIDTERM_SCORE_A).updateScore(FINAL_SCORE_A).arrayOfScores[1],
                FINAL_SCORE_A);
    }

    @Test
    public void isValidExamScores() {
        assertTrue(ExamScores.isValidExamScores(new Score[] {MIDTERM_SCORE_A, FINAL_SCORE_A}));

        assertFalse(ExamScores.isValidExamScores(new Score[]{MIDTERM_SCORE_A, FINAL_SCORE_A, null}));
        assertFalse(ExamScores.isValidExamScores(new Score[]{null, null}));

        assertFalse(ExamScores.isValidExamScores(new Score[]{MIDTERM_SCORE_A, MIDTERM_SCORE_A}));
        assertFalse(ExamScores.isValidExamScores(new Score[]{FINAL_SCORE_A, FINAL_SCORE_A}));
        assertFalse(ExamScores.isValidExamScores(new Score[]{FINAL_SCORE_A, MIDTERM_SCORE_A}));



    }
}
