package seedu.address.testutil;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

/**
 * A utility class to help with building Person objects for tests.
 */
public class TestPersonBuilder {

    public static final Name DEFAULT_NAME = new Name("Amy Bee");
    public static final Phone DEFAULT_PHONE = new Phone("85355255");
    public static final Email DEFAULT_EMAIL = new Email("amy@u.nus.edu");
    public static final StudentId DEFAULT_STUDENT_ID = new StudentId("A0000099A");
    public static final TelegramHandle DEFAULT_TELEGRAM_HANDLE = new TelegramHandle("@Amy");

    /**
     * Generates a sample Amy person for test cases.
     */
    public static Person generateSamplePerson() {
        return new Person.PersonBuilder(
                DEFAULT_NAME,
                DEFAULT_PHONE,
                DEFAULT_EMAIL,
                DEFAULT_STUDENT_ID,
                DEFAULT_TELEGRAM_HANDLE
        ).build();
    }
}
