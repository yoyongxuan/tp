package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ExamList;
import seedu.address.model.person.Score;
import seedu.address.storage.JsonAdaptedScore;
import seedu.address.testutil.SerializableTestClass;
import seedu.address.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void jsonUtil_readJsonStringToObjectInstance_correctObject() throws IOException, IllegalValueException {

        String scoreJsonStringRepresentation = "{\n"
                + "        \"examName\" : \"midterm\",\n"
                + "        \"score\" : \"50\"\n"
                + "      }";
        Score score = Score.getRecordedScore(ExamList.getExamFromName("midterm"), "50");
        JsonAdaptedScore objectInstance = JsonUtil.fromJsonString(
                scoreJsonStringRepresentation, JsonAdaptedScore.class);

        assertEquals(score, objectInstance.toModelType());
    }

    @Test
    public void jsonUtil_writeThenReadObjectToJson_correctObject() throws IOException, IllegalValueException {
        Score score = Score.getRecordedScore(ExamList.getExamFromName("midterm"), "50");
        JsonAdaptedScore jsonAdaptedScore = new JsonAdaptedScore(score);
        String scoreJsonStringRepresentation = JsonUtil.toJsonString(jsonAdaptedScore);
        JsonAdaptedScore objectInstance = JsonUtil.fromJsonString(
                scoreJsonStringRepresentation, JsonAdaptedScore.class);
        assertEquals(score, objectInstance.toModelType());
    }
}
