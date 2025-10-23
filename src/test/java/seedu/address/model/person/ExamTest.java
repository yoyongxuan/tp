package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExamTest {

    @BeforeEach
    public void resetExamsList() {
        ExamList.setMaxScore("midterm", 70);
        ExamList.setMaxScore("final", 100);
    }

    @Test
    public void correctValues() {
        assertEquals("midterm", ExamList.MIDTERM.getName());
        assertEquals(70, ExamList.MIDTERM.getMaxScore());

        assertEquals("final", ExamList.FINAL.getName());
        assertEquals(100, ExamList.FINAL.getMaxScore());
    }

    @Test
    public void isValidScore() {
        assertTrue(ExamList.MIDTERM.isValidScore(Integer.toString(ExamList.MIDTERM.getMaxScore())));
        assertTrue(ExamList.FINAL.isValidScore(Integer.toString(ExamList.FINAL.getMaxScore())));

        assertFalse(ExamList.MIDTERM.isValidScore(Integer.toString(ExamList.MIDTERM.getMaxScore() + 1)));
        assertFalse(ExamList.FINAL.isValidScore(Integer.toString(ExamList.FINAL.getMaxScore() + 1)));

        assertFalse(ExamList.MIDTERM.isValidScore(Integer.toString(-1)));
        assertFalse(ExamList.FINAL.isValidScore(Integer.toString(-1)));
    }

    @Test
    public void updateMaxScore() {
        ExamList.MIDTERM.setMaxScore(120);
        assertEquals(120, ExamList.MIDTERM.getMaxScore());

        ExamList.FINAL.setMaxScore(80);
        assertEquals(80, ExamList.FINAL.getMaxScore());

        assertThrows(IllegalArgumentException.class, () -> ExamList.MIDTERM.setMaxScore(-1));
    }

    @Test
    public void equals() {
        String examName = "Exam";
        int examScore = 80;
        Exam exam = new Exam(examName, examScore);

        // same name and score -> returns true
        assertTrue(exam.equals(new Exam(examName, examScore)));

        // same object -> returns true
        assertTrue(exam.equals(exam));

        // null -> returns false
        assertFalse(exam.equals(null));

        // different types -> returns false
        assertFalse(exam.equals(5.0f));

        // different name -> returns false
        assertFalse(exam.equals(new Exam(examName + "A", examScore)));

        // different score -> returns false
        assertFalse(exam.equals(new Exam(examName, examScore + 5)));
    }
}
