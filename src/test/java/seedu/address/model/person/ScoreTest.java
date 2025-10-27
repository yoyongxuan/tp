package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void getUnrecordedScore() {
        assertThrows(NullPointerException.class, () -> Score.getUnrecordedScore(null));

        assertFalse(Score.getUnrecordedScore(ExamList.MIDTERM).isRecorded);
        assertFalse(Score.getUnrecordedScore(ExamList.FINAL).isRecorded);
    }

    @Test
    public void getRecordedScore() {
        assertThrows(NullPointerException.class, () -> Score.getRecordedScore(null, "1"));
        assertThrows(NullPointerException.class, () -> Score.getRecordedScore(ExamList.MIDTERM, null));

        assertThrows(IllegalArgumentException.class, () -> Score.getRecordedScore(ExamList.MIDTERM, "-1"));
        assertThrows(IllegalArgumentException.class, () -> Score.getRecordedScore(ExamList.MIDTERM, "hello"));

        assertTrue(Score.getRecordedScore(ExamList.MIDTERM, "0").isRecorded);
        assertTrue(Score.getRecordedScore(ExamList.FINAL, "0").isRecorded);
    }

    @Test
    public void equals() {
        Score unrecordedMidtermScore = Score.getUnrecordedScore(ExamList.MIDTERM);
        Score recordedMidtermScore = Score.getRecordedScore(ExamList.MIDTERM, "50");

        // same object -> returns true
        assertTrue(unrecordedMidtermScore.equals(unrecordedMidtermScore));

        // same exam and score -> returns true
        assertTrue(unrecordedMidtermScore.equals(Score.getUnrecordedScore(ExamList.MIDTERM)));
        assertTrue(recordedMidtermScore.equals(Score.getRecordedScore(ExamList.MIDTERM, "50")));

        // null -> returuns false
        assertFalse(unrecordedMidtermScore.equals(null));
        assertFalse(recordedMidtermScore.equals(null));

        // different exam -> returns false
        assertFalse(unrecordedMidtermScore.equals(Score.getUnrecordedScore(ExamList.FINAL)));
        assertFalse(recordedMidtermScore.equals(Score.getRecordedScore(ExamList.FINAL, "50")));

        // different score -> returns false
        assertFalse(recordedMidtermScore.equals(Score.getRecordedScore(ExamList.MIDTERM, "0")));
    }

    @Test
    public void isRecorded() {
        assertFalse(Score.getUnrecordedScore(ExamList.MIDTERM).isRecorded);
        assertTrue(Score.getRecordedScore(ExamList.MIDTERM, "0").isRecorded);
    }

    @Test
    public void getExam() {
        assertEquals(Score.getUnrecordedScore(ExamList.MIDTERM).getExam(), ExamList.MIDTERM);
        assertEquals(Score.getUnrecordedScore(ExamList.FINAL).getExam(), ExamList.FINAL);

        assertEquals(Score.getRecordedScore(ExamList.MIDTERM, "0").getExam(), ExamList.MIDTERM);
        assertEquals(Score.getRecordedScore(ExamList.FINAL, "0").getExam(), ExamList.FINAL);
    }

    @Test
    public void getScore() {
        assertTrue(Score.getUnrecordedScore(ExamList.MIDTERM).getScore().isEmpty());
        assertTrue(Score.getRecordedScore(ExamList.MIDTERM, "0").getScore().isPresent());
        assertEquals(Score.getRecordedScore(ExamList.MIDTERM, "0").getScore().get(), 0);
    }

    @Test
    public void toStringTest() {
        String unrecordedScoreString = Score.getUnrecordedScore(ExamList.MIDTERM).toString();
        assertEquals("midterm: unrecorded", unrecordedScoreString);

        String recordedScoreString = Score.getRecordedScore(ExamList.MIDTERM, "0").toString();
        assertEquals("midterm: 0/" + String.valueOf(ExamList.MIDTERM.getMaxScore()), recordedScoreString);
    }

}
