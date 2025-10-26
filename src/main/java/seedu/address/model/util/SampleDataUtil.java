package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private SampleDataUtil() {
        //Prevent instantiation
    }

    public static Person[] getSamplePersons() {
        return new Person[]{
                new Person.PersonBuilder(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@u.nus.edu"), new StudentId("A0000000A"), new TelegramHandle("@AlexYeoh"))
                        .withTags(getTagSet("friends"))
                        .build(),
                new Person.PersonBuilder(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@u.nus.edu"), new StudentId("A0000001A"), new TelegramHandle("@BerniceYu"))
                        .withTags(getTagSet("colleagues", "friends"))
                        .build(),
                new Person.PersonBuilder(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@u.nus.edu"), new StudentId("A0000002A"), new TelegramHandle("@CharlotteO"))
                        .withTags(getTagSet("neighbours"))
                        .build(),
                new Person.PersonBuilder(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@u.nus.edu"), new StudentId("A0000003A"), new TelegramHandle("@DavidLi"))
                        .withTags(getTagSet("family"))
                        .build(),
                new Person.PersonBuilder(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@u.nus.edu"), new StudentId("A0000004A"), new TelegramHandle("@IrfanI"))
                        .withTags(getTagSet("classmates"))
                        .build(),
                new Person.PersonBuilder(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@u.nus.edu"), new StudentId("A0000005A"), new TelegramHandle("@RoyBalakrishnan"))
                        .withTags(getTagSet("colleagues"))
                        .build()
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
