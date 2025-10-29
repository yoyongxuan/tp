package seedu.address.model.person.examscore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExamListTest {

    @BeforeEach
    public void resetExamList() {
        ExamList.setMaxScore("midterm", 70);
        ExamList.setMaxScore("final", 100);
    }

    @Test
    public void numOfExams_returnsCorrectCount() {
        assertEquals(ExamList.values().size(), ExamList.numOfExams());
    }

    @Test
    public void getExamFromName_returnsCorrectExam() {
        assertEquals(ExamList.MIDTERM, ExamList.getExamFromName("midterm"));
        assertEquals(ExamList.FINAL, ExamList.getExamFromName("final"));
        assertThrows(IllegalArgumentException.class, () -> ExamList.getExamFromName("non-existant exam"));
    }

    @Test
    public void isValidExamName_checksCorrectly() {
        assertTrue(ExamList.isValidExamName("midterm"));
        assertTrue(ExamList.isValidExamName("final"));
    }

    @Test
    public void setMaxScore_updatesMaxScore() {
        ExamList.setMaxScore("midterm", 50);
        ExamList.setMaxScore("final", 75);

        assertEquals(50, ExamList.getExamFromName("midterm").getMaxScore());
        assertEquals(75, ExamList.getExamFromName("final").getMaxScore());
    }
}
