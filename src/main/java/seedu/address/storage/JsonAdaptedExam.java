package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.examscore.Exam;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {

    private final String name;
    private final int maxScore;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code name} and {@code maxScore}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("name") String name, @JsonProperty("maxScore") int maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        this.name = source.getName();
        this.maxScore = source.getMaxScore();
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code Exam} object.
     */
    public Exam toModelType() {
        return new Exam(this.name, this.maxScore);
    }

    public String getName() {
        return this.name;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
