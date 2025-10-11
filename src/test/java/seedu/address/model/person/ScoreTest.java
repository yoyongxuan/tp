package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void equals() {
        assertTrue(Score.getUnrecordedScore(Exam.MIDTERM).equals(Score.getUnrecordedScore(Exam.MIDTERM)));
        assertFalse(Score.getUnrecordedScore(Exam.MIDTERM).equals(Score.getUnrecordedScore(Exam.FINAL)));

        assertFalse(Score.getUnrecordedScore(Exam.MIDTERM).equals(Score.getRecordedScore(Exam.MIDTERM, "0")));
    }

    @Test
    public void isRecorded() {
        assertFalse(Score.getUnrecordedScore(Exam.MIDTERM).isRecorded);
        assertTrue(Score.getRecordedScore(Exam.MIDTERM, "0").isRecorded);
    }

    @Test
    public void getExam() {
        assertEquals(Score.getUnrecordedScore(Exam.MIDTERM).getExam(), Exam.MIDTERM);
        assertEquals(Score.getUnrecordedScore(Exam.FINAL).getExam(), Exam.FINAL);

        assertEquals(Score.getRecordedScore(Exam.MIDTERM, "0").getExam(), Exam.MIDTERM);
        assertEquals(Score.getRecordedScore(Exam.FINAL, "0").getExam(), Exam.FINAL);
    }

    @Test
    public void getScore() {
        assertTrue(Score.getUnrecordedScore(Exam.MIDTERM).getScore().isEmpty());
        assertTrue(Score.getRecordedScore(Exam.MIDTERM, "0").getScore().isPresent());
        assertEquals(Score.getRecordedScore(Exam.MIDTERM, "0").getScore().get(), 0);
    }

}
