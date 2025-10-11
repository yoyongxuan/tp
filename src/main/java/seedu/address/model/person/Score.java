package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;


/**
 * Represents a Person's score for an exam, may represent an unrecorded score.
 * Guarantees: immutable; recorded scores are valid as declared in {@link #isValidScore(String)}
 */
public abstract class Score {

    public final boolean recorded;

    protected final Exam exam;

    private Score(Exam exam, boolean recorded) {
        requireNonNull(exam);
        this.exam = exam;
        this.recorded = recorded;
    }

    public static Score getUnrecordedScore(Exam exam) {
        return new UnrecordedScore(exam);
    }

    public static Score getRecordedScore(Exam exam, String score) {
        return new RecordedScore(exam, score);
    }

    public Exam getExam() {
        return this.exam;
    }

    private static class RecordedScore extends Score {
        private final int score;

        public RecordedScore(Exam exam, String strScore) {
            super(exam, true);
            checkArgument(exam.isValidScore(strScore), exam.getMessageConstraints());
            this.score = Integer.parseInt(strScore);
        }

        @Override
        public String toString() {
            return exam.getName() + ": " + this.score + "/" + exam.getMaxScore();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RecordedScore otherScore)) {
                return false;
            }

            return (score == otherScore.score) && (exam == otherScore.exam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(recorded, exam, score);
        }
    }

    private static class UnrecordedScore extends Score {

        public UnrecordedScore(Exam exam) {
            super(exam, false);
        }

        @Override
        public String toString() {
            return exam.getName() + ": unrecorded";
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UnrecordedScore otherScore)) {
                return false;
            }

            return (exam == otherScore.exam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(recorded, exam);
        }
    }
}
