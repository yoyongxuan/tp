package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an exam with an exam name and maximum score.
 */
public class Exam {
    public static final String IS_INTEGER_REGEX = "\\d+";
    private final String name;
    private int maxScore;

    /**
     * Constructs an {@code Exam}.
     *
     * @param name A valid name.
     * @param maxScore A valid max score.
     */
    public Exam(String name, int maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public String getName() {
        return this.name;
    }

    public void setMaxScore(int newScore) {
        checkArgument(newScore >= 0, "Max score must be non-negative");
        this.maxScore = newScore;
    }

    /**
     * Returns if a given string represents a valid score.
     */
    public boolean isValidScore(String test) {
        if (!test.matches(IS_INTEGER_REGEX)) {
            return false;
        }

        int intTest = Integer.parseInt(test);
        return (intTest >= 0) && (intTest <= this.maxScore);
    }

    public String getMessageScoreConstraints() {
        return "Grade for " + this.name + " should be an integer between 0 and " + this.maxScore;
    }

}
