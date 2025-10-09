package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExamTest {

    @Test
    public void correctValues() {
        assertEquals("midterm", Exam.MIDTERM.getName());
        assertEquals(70, Exam.MIDTERM.getMaxScore());

        assertEquals("final", Exam.FINAL.getName());
        assertEquals(100, Exam.FINAL.getMaxScore());
    }

    @Test
    public void isValidScore() {
        assertTrue(Exam.MIDTERM.isValidScore(Integer.toString(Exam.MIDTERM.getMaxScore())));
        assertTrue(Exam.FINAL.isValidScore(Integer.toString(Exam.FINAL.getMaxScore())));

        assertFalse(Exam.MIDTERM.isValidScore(Integer.toString(Exam.MIDTERM.getMaxScore() + 1)));
        assertFalse(Exam.FINAL.isValidScore(Integer.toString(Exam.FINAL.getMaxScore() + 1)));

        assertFalse(Exam.MIDTERM.isValidScore(Integer.toString(-1)));
        assertFalse(Exam.FINAL.isValidScore(Integer.toString(-1)));
    }

    @Test
    public void getExamFromName() {
        assertEquals(Exam.MIDTERM, Exam.getExamFromName("midterm"));
        assertEquals(Exam.FINAL, Exam.getExamFromName("final"));
    }
}
