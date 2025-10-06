package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamTest {

    @Test
    public void correctValues() {
        assertEquals("midterm", Exam.MIDTERM.getName());
        assertEquals(70, Exam.MIDTERM.getMaxScore());

        assertEquals("final", Exam.FINAL.getName());
        assertEquals(100, Exam.FINAL.getMaxScore());
    }
}
