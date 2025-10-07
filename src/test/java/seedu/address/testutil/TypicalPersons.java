package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@u.nus.edu")
            .withPhone("94351253")
            .withStudentId("A0000000A")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@u.nus.edu")
            .withPhone("98765432")
            .withStudentId("A0000001A")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withStudentId("A0000002A")
            .withEmail("heinz@u.nus.edu").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withStudentId("A0000003A")
            .withEmail("cornelia@u.nus.edu").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822241")
            .withStudentId("A0000004A")
            .withEmail("werner@u.nus.edu").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withStudentId("A0000005A")
            .withEmail("lydia@u.nus.edu").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824421")
            .withStudentId("A0000006A")
            .withEmail("anna@u.nus.edu").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824241")
            .withStudentId("A0000007A")
            .withEmail("stefan@u.nus.edu").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821311")
            .withStudentId("A0000008A")
            .withEmail("hans@u.nus.edu").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND)
            .withStudentId(VALID_STUDENT_ID_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withStudentId(VALID_STUDENT_ID_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an unsorted {@code AddressBook} with typical persons.
     */
    public static AddressBook getUnsortedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getUnsortedTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getUnsortedTypicalPersons() {
        return new ArrayList<>(Arrays.asList(BENSON, DANIEL, ALICE, FIONA, ELLE, CARL, GEORGE));
    }
}
