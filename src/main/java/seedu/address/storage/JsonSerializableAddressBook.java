package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.examscore.ExamList;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_INVALID_EXAM_NAME = "Exam list contains invalid exam name(s).";
    public static final String MESSAGE_NEGATIVE_EXAM_SCORE = "Exam list contains negative exam score(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedExam> examList = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     * The static ExamList is converted for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        examList.addAll(ExamList.values().stream().map(JsonAdaptedExam::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     * Sets the saved max scores of the static ExamList.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        // json throws InvalidFormatException for non-integer values (e.g. "one", "80.5")
        // before they can reach this method
        for (JsonAdaptedExam jsonAdaptedExam : examList) {
            if (jsonAdaptedExam.getMaxScore() < 0) {
                throw new IllegalValueException(MESSAGE_NEGATIVE_EXAM_SCORE);
            }

            try {
                ExamList.setMaxScore(jsonAdaptedExam.getName(), jsonAdaptedExam.getMaxScore());
            } catch (IllegalArgumentException e) {
                throw new IllegalValueException(MESSAGE_INVALID_EXAM_NAME);
            }
        }

        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }
}
