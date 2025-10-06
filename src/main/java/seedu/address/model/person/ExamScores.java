package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's exam scores in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String, String)}
 */
public class ExamScores {

    private static final String IS_INTEGER_REGEX = "\\d+";
    public static final int NUM_OF_EXAM = Exam.values().length;

    public final Score[] arrayOfScores;

    private ExamScores(Score[] arrayOfScores) {
        requireNonNull(arrayOfScores);
        checkArgument(isValidExamScores(arrayOfScores));
        this.arrayOfScores = arrayOfScores;
    }

    public ExamScores updateScore(Score newScore) {
        Score[] arrayOfScoresCopy = this.arrayOfScores.clone();
        for (int i = 0; i < NUM_OF_EXAM; i++) {
            if (arrayOfScoresCopy[i].getExam() == newScore.getExam()) {
                arrayOfScoresCopy[i] = newScore;
                break;
            }
        }
        return new ExamScores(arrayOfScoresCopy);
    }

    @Override
    public String toString() {
        String out = arrayOfScores[0].toString();
        for (int i = 1; i < NUM_OF_EXAM; i++) {
            out += "\n";
            out += arrayOfScores[i].toString();
        }
        return out;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static ExamScores getEmptyExamScores() {
        Score[] arrayOfScores = new Score[NUM_OF_EXAM];
        Exam[] examArray = Exam.values();
        for (int i = 0; i < NUM_OF_EXAM; i++) {
            arrayOfScores[i] = Score.getUnrecordedScore(examArray[i]);
        }
        return new ExamScores(arrayOfScores);
    }

    /**
     * Returns true if a given array of Scores represents a valid set of exam scores.
     *
     * @param arrayOfScores
     */
    public static boolean isValidExamScores(Score[] arrayOfScores) {

        if (arrayOfScores.length != NUM_OF_EXAM) {
            return false;
        }

        Exam[] examArray = Exam.values();
        for (int i = 0; i < NUM_OF_EXAM; i++) {
            if (examArray[i] != arrayOfScores[i].getExam()) {
                return false;
            }
        }

        return true;
    }


}
